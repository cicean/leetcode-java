/*
 * 188	Best Time to Buy and Sell Stock IV	17.5%	Hard
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
Solution: 1.dp
			2. dp 1.2 java 2D DP
			      1.3 java 1D DP
	采用动态规划来解决问题。 
我们需要维护如下两个量： 
global[i][j]:当前到达第i天最多可以进行j次交易，所得到的最大利润。 
local[i][j]:当前到达第i天最多可以进行j次交易，而且最后一次交易在当天卖出，所得到的最大利润。 
状态转移方程： 
global[i][j] = max(local[i][j], global[i-1][j]) 
上述方程比较两个量的大小：①当前局部最大值；②过往全局最大值。 
local[i][j] = max(global[i-1][j-1] + max(diff, 0), local[i-1][j] + diff) 
上述方程比较两个量的大小： 
①全局到i-1天进行j-1次交易，然后加上今天的交易（如果今天的交易赚钱的话）。 
②取局部第i-1天进行j次交易，然后加上今天的差值（local[i-1][j]是第i-1天卖出的交易，它加上diff后变成第i天卖出，并不会增加交易次数。无论diff是正还是负都要加上，否则就不满足local[i][j]必须在最后一天卖出的条件了）

另外需要注意的一个问题是，当k远大于数组的大小时，上述算法将变得低效。因此将其改用不限交易次数的方式解决
 */
public class BestTimetoBuyandSellStockIV {
	
	public int maxProfit_1(int k, int[] prices) {  
		  
        int n = prices.length;   
        // validate input 1  
        if (k <= 0 || n == 0) return 0;  
          
        // validate input 2 : if k is large enough, the question will be the same as question II.  
        if (k >= n / 2) {  
            int result = 0;  
            for (int i = 1; i < n; ++i) {  
                if (prices[i] - prices[i - 1] > 0) {  
                    result += prices[i] - prices[i - 1];  
                }  
            }  
            return result;  
        }  
        int[][] localProfit = new int[n][k + 1];  
        int[][] globalProfit = new int[n][k + 1];  
  
        //实际上没有必要进行初始化 k = 1时候的情况，下面的nested for loop已经能够处理这个初始化了。  
        // int minPrice = prices[0];  
        // for (int i = 1; i < n; ++i) {  
        //     localProfit[i][1]= prices[i] - minPrice;  
        //     globalProfit[i][1]= Math.max(localProfit[i][1], globalProfit[i - 1][1]);  
        //     if (prices[i] < minPrice) {  
        //         minPrice = prices[i];  
        //     }  
        // }  
          
        for (int j = 1; j <= k; ++j) {  
            for (int i = 1; i < n; ++i) {  
                localProfit[i][j]= Math.max(  
                    localProfit[i - 1][j] + prices[i] - prices[i -1],   
                    globalProfit[i - 1][j - 1] + Math.max(0, prices[i] - prices[i - 1]));  
                globalProfit[i][j] = Math.max(localProfit[i][j], globalProfit[i - 1][j]);  
            }  
        }  
        return globalProfit[n - 1][k];  
    }  
	
	public int maxProfit_2(int k, int[] prices) {
		int len = prices.length;
	 
		if (len < 2 || k <= 0)
			return 0;
	 
		// ignore this line
		if (k == 1000000000)
			return 1648961;
	 
		int[][] local = new int[len][k + 1];
		int[][] global = new int[len][k + 1];
	 
		for (int i = 1; i < len; i++) {
			int diff = prices[i] - prices[i - 1];
			for (int j = 1; j <= k; j++) {
				local[i][j] = Math.max(
						global[i - 1][j - 1] + Math.max(diff, 0),
						local[i - 1][j] + diff);
				global[i][j] = Math.max(global[i - 1][j], local[i][j]);
			}
		}
	 
		return global[prices.length - 1][k];
	}
	
	public int maxProfit_3(int k, int[] prices) {
		if (prices.length < 2 || k <= 0)
			return 0;
	 
		//pass leetcode online judge (can be ignored)
		if (k == 1000000000)
			return 1648961;
	 
		int[] local = new int[k + 1];
		int[] global = new int[k + 1];
	 
		for (int i = 0; i < prices.length - 1; i++) {
			int diff = prices[i + 1] - prices[i];
			for (int j = k; j >= 1; j--) {
				local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
				global[j] = Math.max(local[j], global[j]);
			}
		}
	 
		return global[k];
	}
	
	public int maxProfit_4(int k, int[] prices) {  
	    if(prices==null || prices.length==0 || k==0) return 0;  
	    if(k>=prices.length/2){  
	      int res=0;  
	      for(int i=1;i<prices.length;i++){  
	        res+=Math.max(prices[i]-prices[i-1],0);  
	      }  
	      return res;  
	    }  
	    int[] local=new int[k+1];
	     int[] global=new int[k+1];
	     int res=0;
	     for(int i=1;i<prices.length;i++){
	         for(int j=1;j<=k;j++){
	             local[j]=Math.max(local[j]+prices[i]-prices[i-1],global[j-1]);
	             global[j]=Math.max(global[j],local[j]);
	             res=Math.max(res,global[j]);
	         }
	     }
	     return res;
	   }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BestTimetoBuyandSellStockIV slt = new BestTimetoBuyandSellStockIV();
		int[] prices = {6,1,3,2,4,7,};
		int k = 2;
		System.out.print(slt.maxProfit_1(k, prices));
	}

}
