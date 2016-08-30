/**
 * Given two strings, find the longest common substring.

Return the length of it.

 Notice

The characters in substring should occur continuously in original string. This is different with subsequence.

Have you met this question in a real interview? Yes
Example
Given A = "ABCD", B = "CBCE", return 2.

Challenge 
O(n x m) time and memory.
 * @author cicean
 *
 */
public class LongestCommonSubstring {

	public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A == null || B == null){
            return 0;
        }
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m][n];
        int longest = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A.charAt(i) == B.charAt(j)){
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    longest = Math.max(longest, dp[i][j]);
                }
            }
        }
        return longest;
    }
}
