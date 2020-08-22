import java.util.Arrays;

/**
 * 935. Knight Dialer
 * Medium
 *
 * 439
 *
 * 178
 *
 * Add to List
 *
 * Share
 * A chess knight can move as indicated in the chess diagram below:
 *
 *  .
 *
 *
 *
 * This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
 *
 * Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
 *
 * How many distinct numbers can you dial in this manner?
 *
 * Since the answer may be large, output the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: 10
 * Example 2:
 *
 * Input: 2
 * Output: 20
 * Example 3:
 *
 * Input: 3
 * Output: 46
 *
 *
 * Note:
 *
 * 1 <= N <= 5000
 * Accepted
 * 27,361
 * Submissions
 * 62,465
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
 * 2
 *
 * Facebook
 * |
 * 2
 */
public class KnightDialer {

    /**
     * Solution
     * Approach 1: Dynamic Programming
     * Intuition
     *
     * Let f(start, n) be the number of ways to dial an n digit number, where the knight starts at square start. We can create a recursion, writing this in terms of f(x, n-1)'s.
     *
     * Algorithm
     *
     * By hand or otherwise, have a way to query what moves are available at each square.
     * This implies the exact recursion for f. For example, from 1 we can move to 6, 8, so f(1, n) = f(6, n-1) + f(8, n-1).
     *
     * After, let's keep track of dp[start] = f(start, n), and update it for each n from 1, 2, ..., N.
     *
     * At the end, the answer is f(0, N) + f(1, N) + ... + f(9, N) = sum(dp).
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N).
     *
     * Space Complexity: O(1)O(1).
     */

    class Solution {
        public int knightDialer(int N) {
            int MOD = 1_000_000_007;
            int[][] moves = new int[][]{
                    {4,6},{6,8},{7,9},{4,8},{3,9,0},
                    {},{1,7,0},{2,6},{1,3},{2,4}};

            int[][] dp = new int[2][10];
            Arrays.fill(dp[0], 1);
            for (int hops = 0; hops < N-1; ++hops) {
                Arrays.fill(dp[~hops & 1], 0);
                for (int node = 0; node < 10; ++node)
                    for (int nei: moves[node]) {
                        dp[~hops & 1][nei] += dp[hops & 1][node];
                        dp[~hops & 1][nei] %= MOD;
                    }
            }

            long ans = 0;
            for (int x: dp[~N & 1])
                ans += x;
            return (int) (ans % MOD);
        }
    }
}
