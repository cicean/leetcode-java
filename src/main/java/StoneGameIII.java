/**
 * 1406. Stone Game III
 * Hard
 *
 * 119
 *
 * 1
 *
 * Add to List
 *
 * Share
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 *
 * Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.
 *
 * The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
 *
 * The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.
 *
 * Assume Alice and Bob play optimally.
 *
 * Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
 *
 *
 *
 * Example 1:
 *
 * Input: values = [1,2,3,7]
 * Output: "Bob"
 * Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
 * Example 2:
 *
 * Input: values = [1,2,3,-9]
 * Output: "Alice"
 * Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
 * If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. The next move Alice will take the pile with value = -9 and lose.
 * If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. The next move Alice will take the pile with value = -9 and also lose.
 * Remember that both play optimally so here Alice will choose the scenario that makes her win.
 * Example 3:
 *
 * Input: values = [1,2,3,6]
 * Output: "Tie"
 * Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
 * Example 4:
 *
 * Input: values = [1,2,3,-1,-2,-3,7]
 * Output: "Alice"
 * Example 5:
 *
 * Input: values = [-1,-2,-3]
 * Output: "Tie"
 *
 *
 * Constraints:
 *
 * 1 <= values.length <= 50000
 * -1000 <= values[i] <= 1000
 * Accepted
 * 3,443
 * Submissions
 * 6,549
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
 * The game can be mapped to minmax game. Alice tries to maximize the total score and Bob tries to minimize it.
 * Use dynamic programming to simulate the game. If the total score was 0 the game is "Tie",
 * and if it has positive value then "Alice" wins, otherwise "Bob" wins.
 */
public class StoneGameIII {

    /**
     * Solution 1: DP
     * dp[i] means, if we ignore before A[i],
     * what's the highest score that Alex can win over the Bob？
     *
     * There are three option for Alice to choose.
     * Take A[i], win take - dp[i+1]
     * Take A[i] + A[i+1], win take - dp[i+2]
     * Take A[i] + A[i+1] + A[i+2], win take - dp[i+3]
     * dp[i] equals the best outcome of these three solutions.
     *
     *
     * Complexity
     * Time O(N^)
     * Space O(N)
     */

    class Solution {
        public String stoneGameIII(int[] A) {
            int n = A.length, dp[] = new int[n+1];
            for (int i = n - 1; i >= 0; --i) {
                dp[i] = Integer.MIN_VALUE;
                for (int k = 0, take = 0; k < 3 && i + k < n; ++k) {
                    take += A[i + k];
                    dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
                }
            }
            if (dp[0] > 0) return "Alice";
            if (dp[0] < 0) return "Bob";
            return "Tie";
        }
    }

    /**
     * Solution 2: O(1) Space
     * We don't need O(N) space.
     * We only need last 3 results: dp[i+1],dp[i+2] and dp[i+3].
     * Time O(N), space O(1)
     */

    class Solution2 {
        public String stoneGameIII(int[] A) {
            int n = A.length, dp[] = new int[4];
            for (int i = n - 1; i >= 0; --i) {
                dp[i % 4] = Integer.MIN_VALUE;
                for (int k = 0, take = 0; k < 3 && i + k < n; ++k) {
                    take += A[i + k];
                    dp[i % 4] = Math.max(dp[i % 4], take - dp[(i + k + 1) % 4]);
                }
            }
            if (dp[0] > 0) return "Alice";
            if (dp[0] < 0) return "Bob";
            return "Tie";
        }
    }
}
