package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 */
public class CoinChange {

    /**
     * You are given coins of different denominations and
     * a total amount of money amount.
     * Write a function to compute the fewest number of coins
     * that you need to make up that amount. If that amount
     * of money cannot be made up by any combination of the coins, return -1.
     */

    /**
     * For the iterative solution, we think in bottom-up manner. Before calculating F(i)F(i), we have to compute all minimum counts for amounts up to ii. On each iteration ii of the algorithm F(i)F(i) is computed as \min_{j=0 \ldots n-1}{F(i -c_j)} + 1min
     j=0¡­n?1
     ?
     F(i?cj)+1
     */
}   class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     * how many
     */

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i= 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++)
                dp[j] = dp[j - coins[i]] + dp[j];
        }
        return dp[amount];
    }
}
