/**
 * Created by cicean on 8/29/2016.
 * LintCode 397
 *
 * Give an integer array，find the longest increasing continuous subsequence in this array.

 An increasing continuous subsequence:

 Can be from right to left or from left to right.
 Indices of the integers in the subsequence should be continuous.
 Notice

 O(n) time and O(1) extra space.

 Have you met this question in a real interview? Yes
 Example
 For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

 For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.

 Tags
 Enumeration Dynamic Programming Array
 */
public class LongestIncreasingContinuousSubsequence {

    /**
     * 设置正向计数器，后一位增加则计数器加1，否则置1。反向计数器亦然。
     每一次比较后将较大值存入max。

     Solution
     O(1) space, O(n) time
     */
    public class Solution {
        public int longestIncreasingContinuousSubsequence(int[] A) {
            if (A == null || A.length == 0) return 0;
            int n = A.length;
            int count = 1, countn = 1, max = 1;
            int i = 1;
            while (i != n) {
                if (A[i] >= A[i-1]) {
                    count++;
                    countn = 1;
                    max = Math.max(max, count);
                }
                else {
                    countn++;
                    count = 1;
                    max = Math.max(max, countn);
                }
                i++;
            }
            return max;
        }
    }

    /**
     * DP using one dp arrays, O(n) space
     */
    public class Solution2 {
        public int longestIncreasingContinuousSubsequence(int[] A) {
            if (A == null || A.length == 0) return 0;
            int n = A.length;
            if (n == 1) return 1;
            int[] dp = new int[n];
            int maxdp = 0, maxpd = 0;
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                dp[i] = A[i] >= A[i-1] ? dp[i-1]+1 : 1;
                maxdp = Math.max(maxdp, dp[i]);
            }
            for (int i = 1; i < n; i++) {
                dp[i] = A[i] <= A[i-1] ? dp[i-1]+1 : 1;
                maxpd = Math.max(maxpd, dp[i]);
            }
            return Math.max(maxdp, maxpd);
        }
    }


}
