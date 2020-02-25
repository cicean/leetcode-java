import java.util.*;

/**
 * 516. Longest Palindromic Subsequence
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input:

 "bbbab"
 Output:
 4
 One possible longest palindromic subsequence is "bbbb".
 Example 2:
 Input:

 "cbbd"
 Output:
 2
 One possible longest palindromic subsequence is "bb".

 */

public class LongestPalindromicSubsequence {

  /**
   * Straight forward Java DP solution
   dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
   State transition:
   dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
   otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
   Initialization: dp[i][i] = 1
   */

  public class Solution {
    public int longestPalindromeSubseq(String s) {
      int[][] dp = new int[s.length()][s.length()];

      for (int i = s.length() - 1; i >= 0; i--) {
        dp[i][i] = 1;
        for (int j = i+1; j < s.length(); j++) {
          if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = dp[i+1][j-1] + 2;
          } else {
            dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
          }
        }
      }
      return dp[0][s.length()-1];
    }
  }

  //Top bottom recursive method with memoization

  public class Solution2 {
    public int longestPalindromeSubseq(String s) {
      return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private int helper(String s, int i, int j, Integer[][] memo) {
      if (memo[i][j] != null) {
        return memo[i][j];
      }
      if (i > j)      return 0;
      if (i == j)     return 1;

      if (s.charAt(i) == s.charAt(j)) {
        memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
      } else {
        memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
      }
      return memo[i][j];
    }
  }

  class Solution_3 {
    public int longestPalindromeSubseq(String s) {
      if (s == null || s.length() == 0) {
        return 0;
      }
      char[] ss = s.toCharArray();
      int n = s.length();
      if (n == 1) {
        return 1;
      }
      int[] dp = new int[n];
      Arrays.fill(dp, 1);
      for (int i = 1; i < n; i++) {
        int currMax = 0;
        for (int j = i - 1; j >= 0; j--) {
          int tmp = dp[j];
          if (ss[i] == ss[j]) {
            dp[j] = currMax + 2;
          }
          currMax = Math.max(currMax, tmp);
        }
      }
      int max = 1;
      for (int i = 0; i < n; i++) {
        max = Math.max(max, dp[i]);
      }
      return max;
    }
  }



}
