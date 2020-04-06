/**
 * 746. Min Cost Climbing Stairs
 * Easy
 *
 * 1580
 *
 * 362
 *
 * Add to List
 *
 * Share
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 *
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
 *
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 * Accepted
 * 125,996
 * Submissions
 * 256,827
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * TopCoder111
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 3
 *
 * Yahoo
 * |
 * 2
 * Climbing Stairs
 * Easy
 * Say f[i] is the final cost to climb to the top from step i. Then f[i] = cost[i] + min(f[i+1], f[i+2]).
 *
 */
public class MinCostClimbingStairs {

    /**
     * Approach #1: Dynamic Programming [Accepted]
     * Intuition
     *
     * There is a clear recursion available: the final cost f[i] to climb the staircase from some step i is f[i] = cost[i] + min(f[i+1], f[i+2]). This motivates dynamic programming.
     *
     * Algorithm
     *
     * Let's evaluate f backwards in order. That way, when we are deciding what f[i] will be, we've already figured out f[i+1] and f[i+2].
     *
     * We can do even better than that. At the i-th step, let f1, f2 be the old value of f[i+1], f[i+2], and update them to be the new values f[i], f[i+1]. We keep these updated as we iterate through i backwards. At the end, we want min(f1, f2).
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N) where NN is the length of cost.
     *
     * Space Complexity: O(1)O(1), the space used by f1, f2.
     */

    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int f1 = 0, f2 = 0;
            for (int i = cost.length - 1; i >= 0; --i) {
                int f0 = cost[i] + Math.min(f1, f2);
                f2 = f1;
                f1 = f0;
            }
            return Math.min(f1, f2);
        }
    }

}
