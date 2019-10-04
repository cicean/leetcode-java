/**
 * Created by cicean on 8/29/2016.
 * 322. Coin Change  QuestionEditorial Solution  My Submissions
 Total Accepted: 33386 Total Submissions: 129944 Difficulty: Medium
 You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:
 coins = [1, 2, 5], amount = 11
 return 3 (11 = 5 + 5 + 1)

 Example 2:
 coins = [2], amount = 3
 return -1.

 Note:
 You may assume that you have an infinite number of each kind of coin.

 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

 Hide Tags Dynamic Programming

 零钱兑换，让你用最少的零钱数换取目标的数量。
 如有零钱1,2,5，换成11最少的为5+5+1 ，3个硬币

 */
public class CoinChange {

    /**
     * dp，设dp[i] 为兑换目标i最少的硬币数。

     则有：dp[i + coins[j] ] = min(dp[i + coins[j] ] , dp[i] + 1）

     说白了就是用当前的硬币能组合成啥，取最小。
     */

    //time: O(n*m), space: O(n), n表示amount，m表示硬币个数。
    public class Solution {
        public int coinChange(int[] coins, int amount) {
            // 无效输入的处理
            if (amount == 0)
                return 0;
            if (coins == null || coins.length == 0)
                return -1;

            int[] dp = new int[amount + 1];
            for (int i = 1; i <= amount; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length; j++) {
                    if (i >= coins[j] && dp[i - coins[j]] != -1)
                        min = Math.min(min, dp[i - coins[j]] + 1);
                }

                // 根据min的值判断是否能兑换
                dp[i] = min == Integer.MAX_VALUE ? -1 : min;
            }
            return dp[amount];
        }
    }

    public class Solution2 {
        public int coinChange(int[] coins, int amount) {
            int dp[] = new int[amount + 1];
            final int INF = 0x7fffffff;
            for (int i = 1; i <= amount; i++) dp[i] = INF;
            for (int i = 0; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (i + coins[j] <= amount && dp[i]!= INF)
                        dp[i + coins[j]] = Math.min(dp[i + coins[j]], dp[i] + 1);
                }
            }
            return dp[amount] == INF ? -1 : dp[amount];
        }
    }

}
