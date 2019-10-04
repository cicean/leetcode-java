/**
 * 	265	Paint House II 	30.1%	Hard
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color. 

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses. 

Note: 
All costs are positive integers. 

Follow up: 
Could you solve it in O(nk) runtime? 

[分析] 
使用color[j]刷house[i]时我们希望知道刷house[0,i-1]且house[i-1]刷的不是color[j]时的最小cost，于是求解过程中我们需要保留的就是dp[i][j],表示刷完house[0, i]的最小cost且house[i]使用color[j]，递推式就是dp[i][j] = min(dp[i - 1][0]...dp[i-1][k-1]) + costs[i][j] 

思路1：直接实现上面的递推式，复杂度是O(n*k*k)，非最优。 
思路2：受Product of Array Except Self启发，使用两个额外数组： 
minPrior[i][j]表示使用color[0,j]刷house[i]时刷house[0,i]需要的最小cost， 
minAfter[i][j]表示使用color[0,j]刷house[i]时刷house[i,n-1]需要的最小cost. 
则递推式变为dp[i][j] = min(minPrior[i-1][j-1], minAfter[i-1][j+1]) + costs[i][j] 
复杂度是O(n*k)。因为变量较多且需要处理边界元素，很快bug free难度较高。 
思路3：每次迭代house i前先计算出dp[i-1][]的最小值和次小值， 
则递推式简化为dp[i][j]=(dp[i-1][j] == preMin ? preSecondMin : preMin) + costs[i][j] 
如果dp[i-1][j]正好是刷前面所有house的最小值，由于相邻house不能同色，那这次我们就要用次小值（运气好，可能和最小值相等~） 这个思路很清晰容易实现，且空间复杂度还低， 
谢谢yb君分享的巧妙方案~ 
 * @author cicean
 *
 */
public class PaintHouseII {
	
	public int minCostII(int[][] costs) {  
        if (costs == null || costs.length == 0)   
            return 0;  
        int n = costs.length, k = costs[0].length;  
        if (n > 1 && k == 1) {  
            return 0; // ask interviewer whether return 0 or Integer.MAX  
        }  
        //dp[i][j]: minmum cost painting house 0-i with house i painted with color j  
        int[][] dp = new int[n][k];  
        for (int j = 0; j < k; j++)  
            dp[0][j] = costs[0][j];  
        for (int i = 1; i < n; i++) {  
            int preMin = Integer.MAX_VALUE;  
            int preSecondMin = Integer.MAX_VALUE;  
            for (int j = 0; j < k; j++) {  
                if (dp[i - 1][j] <= preMin) {  
                    preSecondMin = preMin;  
                    preMin = dp[i - 1][j];  
                } else if (dp[i - 1][j] < preSecondMin) {  
                    preSecondMin = dp[i - 1][j];  
                }  
            }  
              
            for (int j = 0; j < k; j++) {  
                if (dp[i - 1][j] == preMin) {  
                    dp[i][j] = preSecondMin + costs[i][j];  
                } else {  
                    dp[i][j] = preMin + costs[i][j];  
                }  
            }  
        }  
        int min = Integer.MAX_VALUE;  
        for (int j = 0; j < k; j++) {  
            min = Math.min(min, dp[n - 1][j]);  
        }  
        return min;  
    }  
      
    public int minCostII_Method1(int[][] costs) {  
        if (costs == null || costs.length == 0)   
            return 0;  
        int n = costs.length, k = costs[0].length;  
        if (n > 1 && k == 1) {  
            return 0;  
        }  
        if (n == 1) {  
            int min = costs[0][0];  
            for (int j = 1; j < k; j++) {  
                  
                min = Math.min(min, costs[0][j]);  
            }  
            return min;  
        }  
          
        int[][] dp = new int[n][k];  
        int[][] minPrior = new int[n][k];  
        int[][] minAfter = new int[n][k];  
        dp[0][0] = costs[0][0];  
        minPrior[0][0] = dp[0][0];  
        for (int j = 1; j < k; j++) {  
            dp[0][j] = costs[0][j];  
            minPrior[0][j] = Math.min(minPrior[0][j - 1], dp[0][j]);  
        }  
        minAfter[0][k - 1] = dp[0][k - 1];  
        for (int j = k - 2; j >= 0; j--) {  
            minAfter[0][j] = Math.min(minAfter[0][j + 1], dp[0][j]);  
        }  
        for (int i = 1; i < n; i++) {  
            dp[i][0] = minAfter[i - 1][1] + costs[i][0];  
            minPrior[i][0] = dp[i][0];  
            for (int j = 1; j < k - 1; j++) {  
                dp[i][j] = Math.min(minPrior[i - 1][j - 1], minAfter[i - 1][j + 1]) + costs[i][j];  
                minPrior[i][j] = Math.min(minPrior[i][j - 1], dp[i][j]);  
            }  
            dp[i][k - 1] = minPrior[i- 1][k - 2] + costs[i][k - 1];// at first minPrior[i][k - 2], make me crazy, cyb kill the bug  
            minPrior[i][k - 1] = Math.min(minPrior[i][k - 2], dp[i][k - 1]);  
            minAfter[i][k - 1] = dp[i][k - 1];   
            for (int j = k - 2; j >= 0; j--) {  
                minAfter[i][j] = Math.min(minAfter[i][j + 1], dp[i][j]);  
            }  
        }  
        return minPrior[n - 1][k - 1];  
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
