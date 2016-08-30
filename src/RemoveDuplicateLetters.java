import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by cicean on 8/29/2016.
 * 316. Remove Duplicate Letters  QuestionEditorial Solution  My Submissions
 Total Accepted: 18286 Total Submissions: 67635 Difficulty: Hard
 Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 Example:
 Given "bcabc"
 Return "abc"

 Given "cbacdcbc"
 Return "acdb"

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Stack Greedy

 题意： 给定一个全部由小写组成的字符串，要求删除它重复的元素，使得字典序最小

 */
public class RemoveDuplicateLetters {

    /**
     * 贪心算法（Greedy Algorithm）

     时间复杂度 O(k * n)，其中k为字符串中唯一字符的个数，n为字符串的长度
     time: O(kn), space: O(k), k表示原字符串中unique字符的个数
     */

    public class Solution {
        public String removeDuplicateLetters(String s) {
            if (s == null ||s.length() == 0)
                return s;

            // 记录每个字符出现的次数
            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); i++) {
                cnt[s.charAt(i) - 'a']++;
            }

            // 找出当前最小字符
            int pos = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) < s.charAt(pos))
                    pos = i;

                // 避免无字符可用
                if (--cnt[s.charAt(i) - 'a'] == 0)
                    break;
            }

            // 除去字符串中已经append的字符的所有重复值
            return s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
        }

        //recuricve
        public class Solution3 {
            public String removeDuplicateLetters(String s) {
                int[] cnt = new int[26];
                int pos = 0; // the position for the smallest s[i]
                for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) < s.charAt(pos)) pos = i;
                    if (--cnt[s.charAt(i) - 'a'] == 0) break;
                }
                return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
            }
        }
    }




    /**
     * 用栈。
     首先对字符串出现的个数进行统计。
     然后对字符串扫描，每遇到一个字符串，判断其是否在栈中，如果在则跳过。（计数 – 1）
     如果不在栈中，和栈顶的元素判断，要是当前栈顶的元素比较大而且cnt不为0（也就是说之后还有这个元素），
     就把栈顶弹出。然后把当前的元素入栈。
     使用栈（Stack）数据结构对上述算法进行优化，可以使时间复杂度缩减为O(n)

     算法步骤：

     首先计算字符串s中每一个字符出现的次数，得到字典counter

     遍历字符串s，记当前字符为c，将counter[c] - 1

     如果c已经在栈stack中，继续遍历

     将字符c与栈顶元素top进行比较，若top > c并且counter[top] > 0则弹栈，重复此过程

     将c入栈
     算法执行过程中，栈内元素尽可能的保持递增顺序

     最后，栈中剩余元素即为所求字符串
     Time complexity: O(n), n is the number of chars in string.

     Space complexity: O(n) worst case.
     */

    public class Solution2 {
        public String removeDuplicateLetters(String s) {
            int[] freqs = new int[256];

            // 统计字符频率
            for (int i = 0; i < s.length(); i++) {
                freqs[s.charAt(i)]++;
            }

            boolean[] visited = new boolean[256]; // 用来标记存在stack里的字符
            Deque<Character> q = new ArrayDeque<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                freqs[c]--;
                if (visited[c]) continue;

                // pop出stack当中比当前字符大但后面还存在的的字符，
                while (!q.isEmpty() && q.peek() > c && freqs[q.peek()] > 0) {
                    visited[q.pop()] = false;
                }
                q.push(c);
                visited[c] = true;
            }

            StringBuilder sb = new StringBuilder();
            for (char c : q) {
                sb.append(c);
            }

            return sb.reverse().toString();
        }
    }





}
