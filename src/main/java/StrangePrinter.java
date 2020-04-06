/**
 * 664. Strange Printer
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 There is a strange printer with the following two special requirements:

 The printer can only print a sequence of the same character each time.
 At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

 Example 1:
 Input: "aaabbb"
 Output: 2
 Explanation: Print "aaa" first and then print "bbb".
 Example 2:
 Input: "aba"
 Output: 2
 Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 Hint: Length of the given string will not exceed 100.
 */
public class StrangePrinter {

  /*
   O(n^3)
   */
  public int strangePrinter(String s) {
    int n = s.length();
    if (n == 0) return 0;

    int[][] dp = new int[101][101];
    for (int i = 0; i < n; i++) dp[i][i] = 1;

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < n - i; j++) {
        dp[j][j + i] = i + 1;
        for (int k = j + 1; k <= j + i; k++) {
          int temp = dp[j][k - 1] + dp[k][j + i];
          if (s.charAt(k - 1) == s.charAt(j + i)) temp--;
          dp[j][j + i] = Math.min(dp[j][j + i], temp);
        }
      }
    }
    return dp[0][n - 1];
  }

  class Solution {
    public int strangePrinter(String s) {
      int n = s.length();
      return helper(s.toCharArray(), 0, n - 1, new Integer[n][n]);
    }

    private int helper(char[] A, int i, int j, Integer[][] memo) {
      if (i > j) return 0;
      if (memo[i][j] != null) return memo[i][j];

      int res = helper(A, i, j - 1, memo) + 1;
      for (int k = i; k < j; k++) {
        if (A[k] == A[j]) {
          res = Math.min(res, helper(A, i, k, memo) + helper(A, k + 1, j - 1, memo));
        }
      }
      memo[i][j] = res;
      return memo[i][j];
    }
  }

}
