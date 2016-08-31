/**
 * Created by cicean on 8/31/2016.
 * 351. Android Unlock Patterns  QuestionEditorial Solution  My Submissions
 Total Accepted: 3782
 Total Submissions: 9872
 Difficulty: Medium
 Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

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
     * 这道题乍一看题目这么长以为是一个设计题，其实不是，这道题还是比较有意思的，
     * 起码跟实际结合的比较紧密。这道题说的是安卓机子的解锁方法，
     * 有9个数字键，如果密码的长度范围在[m, n]之间，问所有的解锁模式共有多少种，
     * 注意题目中给出的一些非法的滑动模式。那么我们先来看一下哪些是非法的，
     * 首先1不能直接到3，必须经过2，同理的有4到6，7到9，1到7，2到8，3到9，
     * 还有就是对角线必须经过5，例如1到9，3到7等。
     * 我们建立一个二维数组jumps，用来记录两个数字键之间是否有中间键，
     * 然后再用一个一位数组visited来记录某个键是否被访问过，
     * 然后我们用递归来解，我们先对1调用递归函数，
     * 在递归函数中，我们遍历1到9每个数字next，
     * 然后找他们之间是否有jump数字，如果next没被访问过，
     * 并且jump为0，或者jump被访问过，我们对next调用递归函数。
     * 数字1的模式个数算出来后，由于1,3,7,9是对称的，所以我们乘4即可，
     * 然后再对数字2调用递归函数，2,4,6,9也是对称的，再乘4，
     * 最后单独对5调用一次，然后把所有的加起来就是最终结果了，
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
     * 其中used是一个9位的mask，每位对应一个数字，如果为1表示存在，0表示不存在，(i1, j1)是之前的位置，(i, j)是当前的位置，所以滑动是从(i1, j1)到(i, j)，中间点为((i1+i)/2, (j1+j)/2), 这里的I和J分别为i1+i和j1+j，还没有除以2，所以I和J都是整数。如果I%2或者J%2不为0，说明中间点的坐标不是整数，即中间点不存在，如果中间点存在，如果中间点被使用了，则这条线也是成立的，可以调用递归


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

}
