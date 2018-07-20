package JingChi;

import java.util.*;

public class FastFood {

    /**
     * 题意
     * 给出 n 个餐厅，m 个停车场，现在要将 n 个餐厅中的 m 个变成停车场，使得每个餐厅到最近的停车场的距离之和最短，输出哪个餐厅变成停车场和它服务哪些餐厅，还有最短距离之和。
     *
     * 思路
     * 首先确定在 i 到 j 之间，一定是选择 i 和 j 的中位数那个餐厅作为停车场可以使得i到j的餐厅到停车场的距离最短。
     *
     * 接下来定义状态 dp[i][j] 为前 i 个餐厅中有 j 个改装为停车场的最短距离之和。
     *
     * 设想如果前 j - 1 个停车场服务着前 k-1 个餐厅，那么剩下的最后一个停车场（第 j 个）就要服务 [k, i] 的餐厅了。
     *
     * 所以得到递推式：dp[i][j] = min(dp[i][j], dp[k-1][j-1] + 第k个餐厅到第i个餐厅的花费)。
     *
     * 这个花费可以预处理出来，定义 cost[i][j] 为 [i, j] 中修建一个停车场，其他 [i, j] 中的餐厅到这个停车场的距离之和。
     *
     * 还有一个难点就是输出路径的时候，用三个数组表示 dp[i][j] 的时候，最后一个停车场的位置，服务的餐厅的起点，服务的餐厅的终点，然后用 dfs 回溯的时候输出。
     */

    public int lowestCost(int[] locations) {
        if (locations == null || locations.length == 0) return 0;

        int index = 0;
        int lowestcost = Integer.MAX_VALUE;

        //假设位置为x[]，仓库的位置为avg，距离总和为sum。
        //cost = (x[0] - avg)^2 + (x[1] - avg)^2 + ... + (x[n-1] - avg)^2
        //     = (x[0]^2 + x[1]^2 + ... + x[n-1]^2) - 2 * (x[0] + x[1] + ... + x[n-1]) * avg + n * avg^2
        //     = c(常数) - 2 * b(常数) * avg + a(常数) * avg^2
        //极值 = (-b) / (2*a) = 2 * sum / (2 * n) = sum / n = 平均数
        //
        //作者：_Kazusa
        //链接：https://www.jianshu.com/p/51d07e5a4b7c
        //來源：简书
        //简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。

        for (int i = 0; i < locations.length; i++) {
            int costsum = 0;
            for (int j = 0; j <= i; j++) {
                costsum += Math.pow(locations[i] - locations[j], 2);
            }

            for (int k = locations.length - 1; k >= i; k--) {
                costsum += Math.pow(locations[i] - locations[k], 2);
            }
            if (costsum < lowestcost) {
                index = i;
                lowestcost = costsum;
            }
        }
        return index;
    }


    public double lowestCostOfBuildPost(int n, int m) {
        if ( n  == 0 || m == 0) return 0;
        int[] x = new int[n+1];
        for (int i = 1; i <=n; i++) {
            x[i] = i;
        }
        double[][] dp = new double[n+1][n+1];
        double[][] cost = new double[n+1][n+1];

        //init cost dp
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                double sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += x[k];
                }

                sum /= (j - i + 1);
                cost[i][j] = 0;
                for (int k = i; k <= j; k++) {
                    cost[i][j] += Math.pow (x[k] - sum, 2);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Double.MAX_VALUE);
        }

        //start
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) { // 枚举当前要放多少个包裹
            for (int j = i; j <= i && j <= m; j++) { // 枚举仓库，要求仓库的数量比包裹数量少
                for (int k = j; k <=i; k++) {
                    // 枚举从第k个包裹到第i个包裹全都放到最后一个仓库，
                    // 而且满足前面的j-1个仓库都至少一个包裹
                    dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + cost[k - 1][j - 1]);
                }
            }
        }
        return dp[n][m];
    }


}
