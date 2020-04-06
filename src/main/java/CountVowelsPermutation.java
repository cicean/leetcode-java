/**
 * 1220. Count Vowels Permutation
 * Hard
 *
 * 110
 *
 * 31
 *
 * Add to List
 *
 * Share
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 *
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 * Example 2:
 *
 * Input: n = 2
 * Output: 10
 * Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
 * Example 3:
 *
 * Input: n = 5
 * Output: 68
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 10^4
 * Accepted
 * 7,462
 * Submissions
 * 14,160
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * omkarpatil92
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * C3.ai
 * |
 * LeetCode
 * Use dynamic programming.
 * Let dp[i][j] be the number of strings of length i that ends with the j-th vowel.
 * Deduce the recurrence from the given relations between vowels.
 *
 */
public class CountVowelsPermutation {
    class Solution {
        public int countVowelPermutation(int n) {
            int a = 1;
            int e = 1;
            int i = 1;
            int o = 1;
            int u = 1;
            int mod = 1000000007;
            for (int ii = 1; ii < n; ii++) {
                int tempa = ((e + i) % mod + u) % mod;
                int tempe = (a + i) % mod;
                int tempi = (e + o) % mod;
                int tempo = i;
                int tempu = (i + o) % mod;
                a = tempa; e = tempe; i = tempi; o = tempo; u = tempu;
            }
            int sol = 0;
            sol = (sol + a) % mod;
            sol = (sol + e) % mod;
            sol = (sol + i) % mod;
            sol = (sol + o) % mod;
            sol = (sol + u) % mod;
            return sol;
        }
    }
}
