/**
 * 1328. Break a Palindrome
 * Medium
 *
 * 53
 *
 * 41
 *
 * Add to List
 *
 * Share
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.
 *
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * Example 2:
 *
 * Input: palindrome = "a"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= palindrome.length <= 1000
 * palindrome consists of only lowercase English letters.
 * Accepted
 * 4,908
 * Submissions
 * 11,964
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * VMware
 * |
 * 2
 * How to detect if there is impossible to perform the replacement? Only when the length = 1.
 * Change the first non 'a' character to 'a'.
 * What if the string has only 'a'?
 * Change the last character to 'b'.
 */
public class BreakaPalindrome {
    /**
     * Check half of the string,
     * replace a non 'a' character to 'a'.
     *
     * If only one character, return empty string.
     * Otherwise repalce the last character to 'b'
     *
     *
     * Complexity
     * Time O(N)
     * Space O(N)
     */

    public String breakPalindrome(String palindrome) {
        char[] s = palindrome.toCharArray();
        int n = s.length;

        for (int i = 0; i < n / 2; i++) {
            if (s[i] != 'a') {
                s[i] = 'a';
                return String.valueOf(s);
            }
        }
        s[n - 1] = 'b'; //if all 'a'
        return n < 2 ? "" : String.valueOf(s);
    }
}
