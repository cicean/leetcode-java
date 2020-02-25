import java.util.*;

/**
 * 983. Minimum Cost For Tickets
 * Medium
 *
 * 947
 *
 * 22
 *
 * Add to List
 *
 * Share
 * In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in 3 different ways:
 *
 * a 1-day pass is sold for costs[0] dollars;
 * a 7-day pass is sold for costs[1] dollars;
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
 *
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 *
 *
 * Example 1:
 *
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total you spent $11 and covered all the days of your travel.
 * Example 2:
 *
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total you spent $17 and covered all the days of your travel.
 *
 *
 * Note:
 *
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 * Accepted
 * 32,138
 * Submissions
 * 55,102
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * pravo23
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 2
 * Coin Change
 * Medium
 */
public class MinimumCostForTickets {


    /**
     * Approach 1: Dynamic Programming (Day Variant)
     * Intuition and Algorithm
     *
     * For each day, if you don't have to travel today, then it's strictly better to wait to buy a pass. If you have to travel today, you have up to 3 choices: you must buy either a 1-day, 7-day, or 30-day pass.
     *
     * We can express those choices as a recursion and use dynamic programming. Let's say dp(i) is the cost to fulfill your travel plan from day i to the end of the plan. Then, if you have to travel today, your cost is:
     *
     * \text{dp}(i) = \min(\text{dp}(i+1) + \text{costs}[0], \text{dp}(i+7) + \text{costs}[1], \text{dp}(i+30) + \text{costs}[2])dp(i)=min(dp(i+1)+costs[0],dp(i+7)+costs[1],dp(i+30)+costs[2])
     *
     * Complexity Analysis
     *
     * Time Complexity: O(W)O(W), where W = 365W=365 is the maximum numbered day in your travel plan.
     *
     * Space Complexity: O(W)O(W).
     */
    class Solution_dp_365 {
        int[] costs;
        Integer[] memo;
        Set<Integer> dayset;

        public int mincostTickets(int[] days, int[] costs) {
            this.costs = costs;
            memo = new Integer[366];
            dayset = new HashSet();
            for (int d: days) dayset.add(d);

            return dp(1);
        }

        public int dp(int i) {
            if (i > 365)
                return 0;
            if (memo[i] != null)
                return memo[i];

            int ans;
            if (dayset.contains(i)) {
                ans = Math.min(dp(i+1) + costs[0],
                        dp(i+7) + costs[1]);
                ans = Math.min(ans, dp(i+30) + costs[2]);
            } else {
                ans = dp(i+1);
            }

            memo[i] = ans;
            return ans;
        }
    }

    /**
     * Approach 2: Dynamic Programming (Window Variant)
     * Intuition and Algorithm
     *
     * As in Approach 1, we only need to buy a travel pass on a day we intend to travel.
     *
     * Now, let dp(i) be the cost to travel from day days[i] to the end of the plan. If say, j1 is the largest index such that days[j1] < days[i] + 1, j7 is the largest index such that days[j7] < days[i] + 7, and j30 is the largest index such that days[j30] < days[i] + 30, then we have:
     *
     * \text{dp}(i) = \min(\text{dp}(j1) + \text{costs}[0], \text{dp}(j7) + \text{costs}[1], \text{dp}(j30) + \text{costs}[2])dp(i)=min(dp(j1)+costs[0],dp(j7)+costs[1],dp(j30)+costs[2])
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the number of unique days in your travel plan.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution_dp_window_variant {
        int[] days, costs;
        Integer[] memo;
        int[] durations = new int[]{1, 7, 30};

        public int mincostTickets(int[] days, int[] costs) {
            this.days = days;
            this.costs = costs;
            memo = new Integer[days.length];

            return dp(0);
        }

        public int dp(int i) {
            if (i >= days.length)
                return 0;
            if (memo[i] != null)
                return memo[i];

            int ans = Integer.MAX_VALUE;
            int j = i;
            for (int k = 0; k < 3; ++k) {
                while (j < days.length && days[j] < days[i] + durations[k])
                    j++;
                ans = Math.min(ans, dp(j) + costs[k]);
            }

            memo[i] = ans;
            return ans;
        }
    }

    class Solution {
        public int mincostTickets(int[] days, int[] costs) {
            // length up to the last travel + 1 day is good enough (no need for 365)
            int lastDay = days[days.length - 1];
            // dp[i] means up to i-th day the minimum cost of the tickets
            int[] dp = new int[lastDay + 1];
            boolean[] isTravelDays = new boolean[lastDay + 1];
            // mark the travel days
            for(int day : days) isTravelDays[day] = true;

            for(int i = 1; i <= lastDay; i++) {
                if(!isTravelDays[i]) { // no need to buy ticket if it is not a travel day
                    dp[i] = dp[i - 1];
                    continue;
                }
                // select which type of ticket to buy
                dp[i] = costs[0] + dp[i - 1]; // 1-day
                dp[i] = Math.min(costs[1] + dp[Math.max(i - 7, 0)], dp[i]); // 7-day
                dp[i] = Math.min(costs[2] + dp[Math.max(i - 30, 0)], dp[i]); // 30-day
            }
            return dp[lastDay];
        }
    }
}
