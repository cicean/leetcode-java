/**
 * Created by cicean on 9/13/2018.
 */
public class CoinChangeII {

    /**
     * Firstly, We have been familiar with this transfer function
     f[0] = true
     f[j] = f[j - coins[i]] | f[j] {coins[i] <= j <= amount and j is in decreasing order}
     this is the solution to 0/1 problems
     Secondly, transfer function to this problem as follows:
     f[0] = 1
     f[j] = f[j - coins[i]] + f[j] {coins[i] <= j <= amount }
     for example:
     coin [1, 2] aoumt = 4;
     f[1] = 1 f[2] = 1 f[3] = 1 f[4] = 1
     f[2] = f[2] + f[0] = 2 f[3] = f[1] + f[3] = 2 f[4] = f[2] + f[4] = 3
     Caution:enumerate j in increasing order, if you enumerate j in decreasing order, that means the coins[i] will be just used one time
     Plus if each coin does not only has value, but also has weight. So if we want to use a certain amount to chose the maximum sum of weight of all of these combinations. And each coin could be chosen one time. The transfer funtion willl be:
     f[0] = 0
     f[j] = max(f[j - coins[i]] + weight[i], f[j]} {coins[i] <= j <= amount decreasing}
     Finally, if each coin could be chosen many times, the function will be shifted to :
     f[0] = 0
     f[j] = max(f[j - coins[i]] + weight[i], f[j]} {coins[i] <= j <= amount inrceasing}

     and here is my concise code to this problem
     * @param amount
     * @param coins
     * @return
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
