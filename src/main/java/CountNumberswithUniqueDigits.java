/**
 * Created by cicean on 8/31/2016.
 * 357. Count Numbers with Unique Digits  QuestionEditorial Solution  My Submissions
 Total Accepted: 13974 Total Submissions: 32033 Difficulty: Medium
 Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

 Example:
 Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])

 Hint:

 A direct way is to use the backtracking approach.
 Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which represent which number is marked as visited so far in the current number). Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
 This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
 Let f(k) = count of numbers with unique digits with length equals k.
 f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 Credits:
 Special thanks to @memoryless for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Dynamic Programming Backtracking Math
 给定非负的整数n，求在 0 ≤ x < 10n  中，有多少每个位上的数字互不相同的数？
 如 n =2 时，范围为[0,100]， 共有91个数（除了11,22,33,44,55,66,77,88,99）
 */
public class CountNumberswithUniqueDigits {

    /**
     *测试用例如题目描述。

     提示：

     一个直接的办法是使用回溯法。
     回溯应当包含三个状态（当前数字，得到该数字需要的步数，以及一个比特掩码用来表示当前已经访问过哪些位）。从状态（0,0,0）开始，并计算直到10^n为止的有效数字个数。
     这个问题也可以使用动态规划方法与一些组合的知识来求解。
     f(k) = 长度为k的不重复数位的数字个数。
     f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [首个因数是9，因为数字不能从0开始].
     */
    public class Solution {
        public int countNumbersWithUniqueDigits(int n) {
            n = Math.min(n,10);
            int[] dp = new int[n+1];
            dp[0] = 1;
            for(int i = 1;i<=n;i++){
                dp[i] = 9;
                for(int x = 9; x >= 9 - i + 2;x--){
                    dp[i] *= x;
                }
            }
            int ans = 0;
            for(int i= 0;i<dp.length;i++) ans += dp[i];
            return ans;
        }
    }

    /**
     * general term formula
     * n=0  f(0) = 2(0,1)
     * n=1  f(1) = 10
     * n=2  f(2) = 10 + 9 * 9
     * n=3  f(3) = 10 + 9 * 9 + 9 * 9 * 8
     * n=4  f(4) = 10 + 9 * 9 + 9 * 9 * 8 + 9 * 9 * 8 * 7
     * ...
     * n=10 f(10)= f(9) + 9*(9!)
     * ...
     * f(n) = f(10), when n>10

*/
    public class Solution2 {
        public int countNumbersWithUniqueDigits(int n) {

            if(n==0) return 1;
            if(n==1) return 10;
            n = Math.min(10, n);
            int ans = 10;
            int base = 9;
            for(int i = 1; i < n; i++) {
                base *= 10-i;
                ans += base;
            }
            return ans;
        }
    }
}
