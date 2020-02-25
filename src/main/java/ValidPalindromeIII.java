import java.util.*;

/**
 * 1216. Valid Palindrome III
 * Hard
 *
 * 103
 *
 * 3
 *
 * Add to List
 *
 * Share
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 *
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s has only lowercase English letters.
 * 1 <= k <= s.length
 * Accepted
 * 5,030
 * Submissions
 * 11,002
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 3
 * Can you reduce this problem to a classic problem?
 * The problem is equivalent to finding any palindromic subsequence of length at least N-K where N is the length of the string.
 * Try to find the longest palindromic subsequence.
 * Use DP to do that.
 */
public class ValidPalindromeIII {

    class Solution {
        public boolean isValidPalindrome(String s, int k) {
            if (s.length() - 1 <= k) return true;
            int n = s.length();
            char[] arr = s.toCharArray();
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int max = 0;
            for (int i = 0; i < n; i++) {
                //  dp[i] = 1;
                int curMax = 0;
                for (int j = i - 1; j >= 0; j--) {//find max seq start with j, end with i, put into dp[j]
                    int prev = dp[j];
                    if (arr[i] == arr[j]) {
                        dp[j] = curMax + 2; //only need update dp[j] if start and end matchs
                    }
                    curMax = Math.max(curMax, prev);
                }
            }
            for (int i = 0; i < n; i++) max = Math.max(max, dp[i]);
            return n - max <= k;
        }
    }
}
