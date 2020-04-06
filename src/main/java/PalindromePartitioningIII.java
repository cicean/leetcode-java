import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1278. Palindrome Partitioning III
 * Hard
 *
 * 159
 *
 * 2
 *
 * Add to List
 *
 * Share
 * You are given a string s containing lowercase letters and an integer k. You need to :
 *
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 *
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 *
 * Input: s = "leetcode", k = 8
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 * Accepted
 * 5,021
 * Submissions
 * 8,690
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * lm010
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Apple
 * |
 * 2
 * For each substring calculate the minimum number of steps to make it palindrome and store it in a table.
 * Create a dp(pos, cnt) which means the minimum number of characters changed for the suffix of
 * s starting on pos splitting the suffix on cnt chunks.
 */
public class PalindromePartitioningIII {
    class Solution {
        public int palindromePartition(String s, int K) {
            int N = s.length();
            int[][] dp = new int[N][K + 1];
            Arrays.fill(dp[0], N);
            Arrays.fill(dp, dp[0]);

            int[][] cost = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    cost[i][j] = help(s, i, j);
                }
            }

            for (int i = 0; i < N; i++) {
                dp[i][1] = cost[0][i];
                for (int k = 1; k <= K; k++) {
                    for (int j = 0; j < i; j++) {
                        dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + cost[j + 1][i]);
                    }
                }
            }
            return dp[N - 1][K];
        }

        private int help(String s, int i, int j) {
            int c = 0;
            while (i < j) {
                if (s.charAt(i++) != s.charAt(j--)) {
                    ++c;
                }
            }
            return c;
        }
    }

    class Solution {
        public int palindromePartition(String s, int k) {
            int[][] toPal = new int[s.length()][s.length()];
            int[][] dp = new int[k+1][s.length()];
            for (int i = 0; i < s.length(); i++) {
                toPal[i][i] = 0;
            }
            for (int i = s.length()-1; i >= 0; i--) {
                for (int j = i + 1; j < s.length(); j++) {
                    toPal[i][j] = getChanges(s, i, j);
                }
            }
            for (int i = 0; i < s.length(); i++) {
                dp[1][i] = toPal[0][i];
            }
            for (int i = 2; i <= k; i++) {
                for (int end = i-1; end < s.length(); end++) {
                    int min = s.length();
                    for (int start = end-1; start >= 0; start--) {
                        min = Math.min(min, dp[i-1][start] + toPal[start+1][end]);
                    }
                    dp[i][end] = min;
                }
            }
            return dp[k][s.length()-1];
        }


        private int getChanges(String s, int start, int end) {
            int changes = 0;
            while(start < end) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    changes++;
                }
            }
            return changes;
        }
    }
}
