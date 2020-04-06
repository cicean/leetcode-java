/**
 * Number of Dice Rolls With Target Sum
 * Medium
 *
 * 374
 *
 * 25
 *
 * Add to List
 *
 * Share
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 *
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.
 *
 *
 *
 * Example 1:
 *
 * Input: d = 1, f = 6, target = 3
 * Output: 1
 * Explanation:
 * You throw one die with 6 faces.  There is only one way to get a sum of 3.
 * Example 2:
 *
 * Input: d = 2, f = 6, target = 7
 * Output: 6
 * Explanation:
 * You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
 * 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 *
 * Input: d = 2, f = 5, target = 10
 * Output: 1
 * Explanation:
 * You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
 * Example 4:
 *
 * Input: d = 1, f = 2, target = 3
 * Output: 0
 * Explanation:
 * You throw one die with 2 faces.  There is no way to get a sum of 3.
 * Example 5:
 *
 * Input: d = 30, f = 30, target = 500
 * Output: 222616187
 * Explanation:
 * The answer must be returned modulo 10^9 + 7.
 *
 *
 * Constraints:
 *
 * 1 <= d, f <= 30
 * 1 <= target <= 1000
 * Accepted
 * 22,519
 * Submissions
 * 45,448
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * pmanvi123
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 9
 *
 * Google
 * |
 * 3
 * Use dynamic programming. The states are how many dice are remaining, and what sum total you have rolled so far.
 */
public class NumberofDiceRollsWithTargetSum {
    /**
     *
     */

    class Solution {
        public int numRollsToTarget(int d, int f, int target) {
            if (target < d || target > f * d) return 0;
            double[][] dp = new double[d+1][target+1];
            double ex = Math.pow(10, 9) + 7;
            for (int j = 1; j <= f && j <= target; j++) {
                dp[1][j] = 1;
            }
            for (int i = 2; i <= d; i++) {
                for (int j = i; j <= i*f && j <= target; j++) {
                    for (int m = 1; m <= f; m++) {
                        if (j - m >= 0) {
                            dp[i][j] += dp[i-1][j - m];
                        }
                    }
                    dp[i][j] %= ex;
                }
            }

            return (int)dp[d][target];
            //return (int)(dp[d][target] % ex);
        }
    }
}
