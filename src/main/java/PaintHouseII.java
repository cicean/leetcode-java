/**
 * 	265	Paint House II 	30.1%	Hard
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color. 

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses. 

Note: 
All costs are positive integers. 

Follow up: 
Could you solve it in O(nk) runtime? 

[����] 
ʹ��color[j]ˢhouse[i]ʱ����ϣ��֪��ˢhouse[0,i-1]��house[i-1]ˢ�Ĳ���color[j]ʱ����Сcost��������������������Ҫ�����ľ���dp[i][j],��ʾˢ��house[0, i]����Сcost��house[i]ʹ��color[j]������ʽ����dp[i][j] = min(dp[i - 1][0]...dp[i-1][k-1]) + costs[i][j] 

˼·1��ֱ��ʵ������ĵ���ʽ�����Ӷ���O(n*k*k)�������š� 
˼·2����Product of Array Except Self������ʹ�������������飺 
minPrior[i][j]��ʾʹ��color[0,j]ˢhouse[i]ʱˢhouse[0,i]��Ҫ����Сcost�� 
minAfter[i][j]��ʾʹ��color[0,j]ˢhouse[i]ʱˢhouse[i,n-1]��Ҫ����Сcost. 
�����ʽ��Ϊdp[i][j] = min(minPrior[i-1][j-1], minAfter[i-1][j+1]) + costs[i][j] 
���Ӷ���O(n*k)����Ϊ�����϶�����Ҫ����߽�Ԫ�أ��ܿ�bug free�ѶȽϸߡ� 
˼·3��ÿ�ε���house iǰ�ȼ����dp[i-1][]����Сֵ�ʹ�Сֵ�� 
�����ʽ��Ϊdp[i][j]=(dp[i-1][j] == preMin ? preSecondMin : preMin) + costs[i][j] 
���dp[i-1][j]������ˢǰ������house����Сֵ����������house����ͬɫ����������Ǿ�Ҫ�ô�Сֵ�������ã����ܺ���Сֵ���~�� ���˼·����������ʵ�֣��ҿռ临�ӶȻ��ͣ� 
ллyb������������~ 
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
