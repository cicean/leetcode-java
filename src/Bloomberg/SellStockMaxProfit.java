package Bloomberg;

import java.util.*;

import datastructure.PrintList;

/**
 * Created by cicean on 9/19/2016.
 * 第二题是给了一个数组，
 1, 2, 3, 4, 5, 6,  7,  8
 1, 4, 7, 9,12,14,15,20
 第一排是index，也是一笔交易中卖出的股票数量，
 第二排是对应的收益，比如说我第一笔交易卖了三只股票，那么收益就是7。可以选择任意的卖法，比如一只一只的卖，或者也可以一次性卖8只。题目要求求得最大收益。楼主用的recursive的解法，如果一共有8只股票，这一次卖了两只之后，下一层的问题就是一共有6只，求最大收益。用一个global variable存最大收益，碰到大的就更新。
 */
public class SellStockMaxProfit {

    private int maxprofit = 0;

    public int maxProfit(int[] stocks, int[] index) {
        if (stocks == null) return 0;
        dfs(stocks, index, 0 , index.length, 0);
        return maxprofit;
    }

    private void dfs (int[] stocks, int[] index, int start, int target, int sum) {
    	
        if (target == 0) {
            maxprofit = Math.max(maxprofit, sum);
           
            return;
        }

        
        for (int i = start; i < index.length && target >= index[i]; i++) {
        	sum += stocks[i];
        	System.out.println("sum = " + sum);
        	dfs(stocks, index, i, target - index[i], sum);
        	sum -=stocks[i];	
        }
        
    }
    
    public static void main(String[] args) {
    	SellStockMaxProfit  slt = new SellStockMaxProfit();
    	int[] stocks = { 1, 4, 8, 11,12,14,15,20};
    	int[] index = {1,2,3,4,5,6,7,8};
    	System.out.println(slt.maxProfit(stocks, index));
	}
}
