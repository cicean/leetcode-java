/*
 	122	Best Time to Buy and Sell Stock II	38.3%	Medium
 Problem:    Best Time to Buy and Sell Stock II
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 Notes:
 Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
 (ie, buy one and sell one share of the stock multiple times). 
 However, you may not engage in multiple transactions at the same time 
 (ie, you must sell the stock before you buy again).
 Solution: 1. At the beginning of the ascending order: buy.
              At the ending of the ascending order: sell.
 */

public class BestTimetoBuyandSellStockII {
	public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            res += Math.max(0, prices[i] - prices[i-1]);
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BestTimetoBuyandSellStockII slt = new BestTimetoBuyandSellStockII();
		int[] prices = {2,1,4,5,2,9,7};
		System.out.print(slt.maxProfit(prices));
	}

}
