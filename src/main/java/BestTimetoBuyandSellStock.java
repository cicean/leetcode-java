/*
 121	Best Time to Buy and Sell Stock	32.6%	Medium
 Problem:    Best Time to Buy and Sell Stock
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_121
 Notes:
 Say you have an array for which the ith element is the price of a given stock on day i.
 If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
 design an algorithm to find the maximum profit.
 Solution: For each element, calculate the max difference with the former elements.
 思路是维护两个变量，一个是到目前为止最好的交易，另一个是在当前一天卖出的最佳交易（也就是局部最优）。
 递推式是local[i+1]=max(local[i]+prices[i+1]-price[i],0), global[i+1]=max(local[i+1],global[i])。
 这样一次扫描就可以得到结果，时间复杂度是O(n)。而空间只需要两个变量，即O(1)
 */

public class BestTimetoBuyandSellStock {
	
	public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        int res = 0, minVal = prices[0];
        for (int i = 1; i < n; ++i) {
            res = Math.max(res, prices[i] - minVal);
            minVal = Math.min(minVal, prices[i]);
        }
        return res;
    }
	
	public int maxProfit_1(int[] prices) {  
	    if(prices==null || prices.length==0)  
	        return 0;  
	    int local = 0;  
	    int global = 0;  
	    for(int i=0;i<prices.length-1;i++)  
	    {  
	        local = Math.max(local+prices[i+1]-prices[i],0);  
	        global = Math.max(local, global);  
	    }  
	    return global;  
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BestTimetoBuyandSellStock slt = new BestTimetoBuyandSellStock();
		int[] prices = {2,1,4,5,2,9,7};
		System.out.print(slt.maxProfit(prices));
		
	}

}
