import java.util.Arrays;

/**
 * Created by cicean on 8/29/2016.
 * 322. Coin Change  QuestionEditorial Solution  My Submissions
 Total Accepted: 33386 Total Submissions: 129944 Difficulty: Medium
 You are given coins of different denominations and a total amount of money amount.
 Write a function to compute the fewest number of coins that you need to make up that amount.
 If that amount of money cannot be made up by any combination of the coins, return -1.

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

 ��Ǯ�һ������������ٵ���Ǯ����ȡĿ���������
 ������Ǯ1,2,5������11���ٵ�Ϊ5+5+1 ��3��Ӳ��

 */
public class CoinChange {

    /**
     * dp����dp[i] Ϊ�һ�Ŀ��i���ٵ�Ӳ������

     ���У�dp[i + coins[j] ] = min(dp[i + coins[j] ] , dp[i] + 1��

     ˵���˾����õ�ǰ��Ӳ������ϳ�ɶ��ȡ��С��
     */

    //time: O(n*m), space: O(n), n��ʾamount��m��ʾӲ�Ҹ�����
    public class Solution {
        public int coinChange(int[] coins, int amount) {
            // ��Ч����Ĵ���
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

                // ����min��ֵ�ж��Ƿ��ܶһ�
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

    class Solution3 {
        int res;
        public int coinChange(int[] coins, int amount) {
            if (coins == null || amount < 0) return -1;
            res = Integer.MAX_VALUE;
            Arrays.sort(coins);
            dfs(coins, amount, 0, coins.length - 1);
            return res == Integer.MAX_VALUE ? -1 : res;
        }
        void dfs(int[] coins,int amount, int n, int level) {
            if (level < 0) return;
            for (int i = amount / coins[level]; i >= 0; i--) {
                int newAmount = amount - coins[level] * i;
                int newN = n + i;
                if (newAmount > 0 && newN + 1 < res) dfs(coins, newAmount, newN, level - 1);
                else {
                    if (newAmount == 0) res = Math.min(newN, res);
                    break;
                }
            }
        }
    }
}
