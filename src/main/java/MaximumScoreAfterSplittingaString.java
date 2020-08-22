/**
 * substrings are:
 * left = "0" and right = "11101", score = 1 + 4 = 5
 * left = "01" and right = "1101", score = 1 + 3 = 4
 * left = "011" and right = "101", score = 1 + 2 = 3
 * left = "0111" and right = "01", score = 1 + 1 = 2
 * left = "01110" and right = "1", score = 2 + 1 = 3
 * Example 2:
 *
 * Input: s = "00111"
 * Output: 5
 * Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
 * Example 3:
 *
 * Input: s = "1111"
 * Output: 3
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 500
 * The string s consists of characters '0' and '1' only.
 * Accepted
 * 13,301
 * Submissions
 * 24,615
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * LeetCode
 * Precompute a prefix sum of ones ('1').
 * Iterate from left to right counting the number of zeros ('0'), then use the precomputed prefix sum for counting ones ('1'). Update the answer.
 */
public class MaximumScoreAfterSplittingaString {

    class Solution {
        public int maxScore(String s) {
            int zeros = 0, ones = 0, max = Integer.MIN_VALUE;
            for(int i=0;i<s.length();i++) {
                if(s.charAt(i) == '0') zeros++; else ones++;
                if(i != s.length()-1) max = Math.max(zeros - ones, max);
            }
            return max + ones;
        }
    }
}
