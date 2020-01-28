/**
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 * Hard
 *
 * 121
 *
 * 4
 *
 * Add to List
 *
 * Share
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 * Example 2:
 *
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 * Example 4:
 *
 * Input: s = "g"
 * Output: 0
 * Example 5:
 *
 * Input: s = "no"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * All characters of s are lower case English letters.
 * Accepted
 * 4,658
 * Submissions
 * 8,426
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * Is dynamic programming suitable for this problem ?
 * If we know the longest palindromic sub-sequence is x and the length of the string is n then, what is the answer to this problem?
 * It is n - x as we need n - x insertions to make the remaining characters also palindrome.
 */

public class MinimumInsertionStepstoMakeaStringPalindrome {

    /**
     * Intuition
     * Split the string s into to two parts,
     * and we try to make them symmetrical by adding letters.
     *
     * The more common symmetrical subsequence they have,
     * the less letters we need to add.
     *
     * Now we change the problem to find the length of longest common sequence.
     * This is a typical dynamic problem.
     *
     *
     * Explanation
     * Step1.
     * Initialize dp[n+1][n+1],
     * wheredp[i][j] means the length of longest common sequence between
     * i first letters in s1 and j first letters in s2.
     *
     * Step2.
     * Find the the longest common sequence between s1 and s2,
     * where s1 = s and s2 = reversed(s)
     *
     * Step3.
     * return n - dp[n][n]
     *
     *
     * Complexity
     * Time O(N^2)
     * Space O(N^2)
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                dp[i + 1][j + 1] = s.charAt(i) == s.charAt(n - 1 - j) ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
        return n - dp[n][n];
    }
}
