/**
 * 1269. Number of Ways to Stay in the Same Place After Some Steps
 * Hard
 *
 * 171
 *
 * 8
 *
 * Add to List
 *
 * Share
 * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).
 *
 * Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 * Example 2:
 *
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 * Example 3:
 *
 * Input: steps = 4, arrLen = 2
 * Output: 8
 *
 *
 * Constraints:
 *
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 * Accepted
 * 7,417
 * Submissions
 * 18,005
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
 * Try with Dynamic programming, dp(pos,steps): number of ways to back pos = 0 using exactly "steps" moves.
 * Notice that the computational complexity does not depend of "arrlen".
 */
public class NumberofWaystoStayintheSamePlaceAfterSomeSteps {

    class Solution {

        int mod = (int)Math.pow(10, 9) + 7;

        public int numWays(int steps, int n) {

            int[] arr = new int[n];
            if(n <= 1) return n;
            arr[0] = 1;
            arr[1] = 1;

            for(int j = 1; j < steps; j++){

                int[] temp = new int[n];

                for(int i = 0; i <= Math.min(n - 1, steps - j); i++){
                    long ans = arr[i];
                    if(i > 0) ans = (ans + arr[i - 1]) % mod;
                    if(i < n - 1) ans = (ans + arr[i + 1]) % mod;
                    temp[i] = (int)ans;
                }

                arr = temp;
            }

            return arr[0];

        }
    }

    class Solution2 {
        public int numWays(int steps, int arrLen) {
            int MOD = 1000000007;
            if (arrLen == 1) return 1;
            arrLen = Math.min(arrLen, (steps + 2) / 2);
            int[] dp = new int[arrLen + 1];
            dp[0] = 1;
            for (int t = 0; t < steps; t++) {
                int prev = (dp[0] + dp[1]) % MOD;
                for (int i = 1; prev > 0 && i < arrLen; i++) {
                    int cur = ((dp[i - 1] + dp[i]) % MOD + dp[i + 1]) % MOD;
                    dp[i - 1] = prev;
                    prev = cur;
                }
                dp[arrLen - 1] = prev;
            }
            return dp[0];
        }
    }
}
