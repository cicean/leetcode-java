/*
 123	Best Time to Buy and Sell Stock III	23.8%	Hard
 Problem:    Best Time to Buy and Sell Stock III
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 Notes:
 Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete at most two transactions.
 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 Solution: dp. max profit =  max { l2r[0...i] + r2l[i+1...N-1] }.
                         0 <= i <= N-1
 */

public class BestTimetoBuyandSellStockIII {
	public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        int[] l2r = new int[n];
        int[] r2l = new int[n];
        l2r[0] = 0; r2l[n-1] = 0;
        int minVal = prices[0], maxVal = prices[n-1];
        for (int i = 1; i < n; ++i) {
            l2r[i] = Math.max(l2r[i-1], prices[i] - minVal);
            minVal = Math.min(minVal, prices[i]);
        }
        for (int i = n - 2; i >= 0; --i) {
            r2l[i] = Math.max(r2l[i+1], maxVal - prices[i]);
            maxVal = Math.max(maxVal, prices[i]);
        }
        int res = l2r[n-1];
        for (int i = 0; i < n - 1; ++i) {
            res = Math.max(res, l2r[i] + r2l[i+1]);
        }
        return res;
    }

    public int maxProfit_1(int[] prices) {
        if (prices == null)  return 0;
        
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far. 
        }
        
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BestTimetoBuyandSellStockIII slt = new BestTimetoBuyandSellStockIII();
		int[] prices = {2,1,4,5,2,9,7};
		System.out.println(slt.maxProfit(prices));
		System.out.println("methord2 = " + slt.maxProfit(prices));
	}

}
