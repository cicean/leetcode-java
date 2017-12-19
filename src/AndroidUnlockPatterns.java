/**
 * Created by cicean on 8/31/2016.
 * 351. Android Unlock Patterns  QuestionEditorial Solution  My Submissions
 Total Accepted: 3782
 Total Submissions: 9872
 Difficulty: Medium
 Given an Android 3x3 key lock screen and two integers m and n, where 1 �� m �� n �� 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

 Rules for a valid pattern:
 Each pattern must connect at least m keys and at most n keys.
 All the keys must be distinct.
 If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 The order of keys used matters.

 Explanation:
 | 1 | 2 | 3 |
 | 4 | 5 | 6 |
 | 7 | 8 | 9 |
 Invalid move: 4 - 1 - 3 - 6
 Line 1 - 3 passes through key 2 which had not been selected in the pattern.

 Invalid move: 4 - 1 - 9 - 2
 Line 1 - 9 passes through key 5 which had not been selected in the pattern.

 Valid move: 2 - 4 - 1 - 3 - 6
 Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

 Valid move: 6 - 5 - 4 - 1 - 9 - 2
 Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

 Example:
 Given m = 1, n = 1, return 9.

 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.
 */
public class AndroidUnlockPatterns {

    /**
     * �����էһ����Ŀ��ô����Ϊ��һ������⣬��ʵ���ǣ�����⻹�ǱȽ�����˼�ģ�
     * �����ʵ�ʽ�ϵıȽϽ��ܡ������˵���ǰ�׿���ӵĽ���������
     * ��9�����ּ����������ĳ��ȷ�Χ��[m, n]֮�䣬�����еĽ���ģʽ���ж����֣�
     * ע����Ŀ�и�����һЩ�Ƿ��Ļ���ģʽ����ô����������һ����Щ�ǷǷ��ģ�
     * ����1����ֱ�ӵ�3�����뾭��2��ͬ�����4��6��7��9��1��7��2��8��3��9��
     * ���о��ǶԽ��߱��뾭��5������1��9��3��7�ȡ�
     * ���ǽ���һ����ά����jumps��������¼�������ּ�֮���Ƿ����м����
     * Ȼ������һ��һλ����visited����¼ĳ�����Ƿ񱻷��ʹ���
     * Ȼ�������õݹ����⣬�����ȶ�1���õݹ麯����
     * �ڵݹ麯���У����Ǳ���1��9ÿ������next��
     * Ȼ��������֮���Ƿ���jump���֣����nextû�����ʹ���
     * ����jumpΪ0������jump�����ʹ������Ƕ�next���õݹ麯����
     * ����1��ģʽ���������������1,3,7,9�ǶԳƵģ��������ǳ�4���ɣ�
     * Ȼ���ٶ�����2���õݹ麯����2,4,6,9Ҳ�ǶԳƵģ��ٳ�4��
     * ��󵥶���5����һ�Σ�Ȼ������еļ������������ս���ˣ�
     */

    public class Solution {
        // cur: the current position
        // remain: the steps remaining
        int DFS(boolean vis[], int[][] skip, int cur, int remain) {
            if(remain < 0) return 0;
            if(remain == 0) return 1;
            vis[cur] = true;
            int rst = 0;
            for(int i = 1; i <= 9; ++i) {
                // If vis[i] is not visited and (two numbers are adjacent or skip number is already visited)
                if(!vis[i] && (skip[cur][i] == 0 || (vis[skip[cur][i]]))) {
                    rst += DFS(vis, skip, i, remain - 1);
                }
            }
            vis[cur] = false;
            return rst;
        }

        public int numberOfPatterns(int m, int n) {
            // Skip array represents number to skip between two pairs
            int skip[][] = new int[10][10];
            skip[1][3] = skip[3][1] = 2;
            skip[1][7] = skip[7][1] = 4;
            skip[3][9] = skip[9][3] = 6;
            skip[7][9] = skip[9][7] = 8;
            skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
            boolean vis[] = new boolean[10];
            int rst = 0;
            // DFS search each length from m to n
            for(int i = m; i <= n; ++i) {
                rst += DFS(vis, skip, 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
                rst += DFS(vis, skip, 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
                rst += DFS(vis, skip, 5, i - 1);        // 5
            }
            return rst;
        }
    }

    /**
     * ����used��һ��9λ��mask��ÿλ��Ӧһ�����֣����Ϊ1��ʾ���ڣ�0��ʾ�����ڣ�(i1, j1)��֮ǰ��λ�ã�(i, j)�ǵ�ǰ��λ�ã����Ի����Ǵ�(i1, j1)��(i, j)���м��Ϊ((i1+i)/2, (j1+j)/2), �����I��J�ֱ�Ϊi1+i��j1+j����û�г���2������I��J�������������I%2����J%2��Ϊ0��˵���м������겻�����������м�㲻���ڣ�����м����ڣ�����м�㱻ʹ���ˣ���������Ҳ�ǳ����ģ����Ե��õݹ�


     used is the 9-bit bitmask telling which keys have already been used and (i1,j1) and (i2,j2)are the previous two key coordinates. A step is valid if...
     I % 2: It goes to a neighbor row or
     J % 2: It goes to a neighbor column or
     used2 & (1 << (I/2*3 + J/2))): The key in the middle of the step has already been used.
     (i2,j2) are the coordinates of the previous key, (i,j) are the coordinates of the new key. So the new line goes from (i2,j2) to (i,j). The middle point of the line is at ((i2+i)/2, (j2+j)/2). My Iand J are those middle coordinates, except I didn't divide by 2 yet, so I can stay in integers.
     Now if I % 2 isn't zero, then that means I/2 and thus (i2+i)/2 is no integer. Which means the middle point of the line is not a key. Same with the other coordinate. If both those checks fail, then the middle point of the new line is a key, and thus I need to check that that key has been used already.
    c
     */

    class Solution2 {
        public:
        int numberOfPatterns(int m, int n) {
            return count(m, n, 0, 1, 1);
        }
        int count(int m, int n, int used, int i1, int j1) {
            int res = m <= 0;
            if (!n) return 1;
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    int I = i1 + i, J = j1 + j, used2 = used | (1 << (i * 3 + j));
                    if (used2 > used && (I % 2 || J % 2 || used2 & (1 << (I / 2 * 3 + J / 2)))) {
                        res += count(m - 1, n - 1, used2, i, j);
                    }
                }
            }
            return res;
        }
    };

    /**
     * Summary

     After Android launched its "unlock pattern" system to protect our smart phones from unauthorized access, the most
     common question that comes to one's mind is: How secure exactly are these patterns? The current article gives an
     answer to this question, as presenting an algorithm, which computes the number of all valid pattern combinations.
     It is intended for intermediate users and introduces the following ideas: Backtracking, Arrays.

     Solution

     Approach #1: (Backtracking) [Accepted]

     Algorithm

     The algorithm uses backtracking technique to enumerate all possible kk combinations of numbers [1… 9][1… 9] where
     m \leq k \leq nm≤k≤n. During the generation of the recursive solution tree, the algorithm cuts all the branches
     which lead to patterns which doesn't satisfy the rules and counts only the valid patterns. In order to compute a
     valid pattern, the algorithm performs the following steps:

     Select a digit ii which is not used in the pattern till this moment. This is done with the help of a usedused
     array which stores all available digits.

     We need to keep last inserted digit lastlast. The algorithm makes a check whether one of the following conditions
     is valid.

     There is a knight move (as in chess) from lastlast towards ii or lastlast and ii are adjacent digits in a row, in
     a column. In this case the sum of both digits should be an odd number.

     The middle element midmid in the line which connects ii and lastlast was previously selected. In case ii and
     lastlast are positioned at both ends of the diagonal, digit midmid = 5 should be previously selected.

     lastlast and ii are adjacent digits in a diagonal

     In case one of the conditions above is satisfied, digit ii becomes part of partially generated valid pattern and
     the algorithm continues with the next candidate digit till the pattern is fully generated. Then it counts it.
     In case none of the conditions are satisfied, the algorithm rejects the current digit ii, backtracks and continues
     to search for other valid digits among the unused ones.

     Complexity Analysis

     Time complexity : O( n!)O(n!), where nn is maximum pattern length

     The algorithm computes each pattern once and no element can appear in the pattern twice. The time complexity is proportional to the number of the computed patterns. One upper bound of the number of all possible combinations is :

     \ \sum_{i=m}^{n} {_9}P_i = \ \sum_{i=m}^{n} \frac{9!}{(9 - i)!}
     ​​
     Space complexity : O(n)O(n), where nn is maximum pattern length In the worst case the maximum depth of recursion is nn. Therefore we need O( n)O(n) space used by the system recursive stack
     */

    public class Solution1 {

        private boolean used[] = new boolean[9];

        public int numberOfPatterns(int m, int n) {
            int res = 0;
            for (int len = m; len <= n; len++) {
                res += calcPatterns(-1, len);
                for (int i = 0; i < 9; i++) {
                    used[i] = false;
                }
            }
            return res;
        }

        private boolean isValid(int index, int last) {
            if (used[index])
                return false;
            // first digit of the pattern
            if (last == -1)
                return true;
            // knight moves or adjacent cells (in a row or in a column)
            if ((index + last) % 2 == 1)
                return true;
            // indexes are at both end of the diagonals for example 0,0, and 8,8
            int mid = (index + last)/2;
            if (mid == 4)
                return used[mid];
            // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
            if ((index%3 != last%3) && (index/3 != last/3)) {
                return true;
            }
            // all other cells which are not adjacent
            return used[mid];
        }

        private int calcPatterns(int last, int len) {
            if (len == 0)
                return 1;
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                if (isValid(i, last)) {
                    used[i] = true;
                    sum += calcPatterns(i, len - 1);
                    used[i] = false;
                }
            }
            return sum;
        }
    }

}
