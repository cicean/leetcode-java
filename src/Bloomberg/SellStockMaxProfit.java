package Bloomberg;

import java.util.*;

import datastructure.PrintList;

/**
 * Created by cicean on 9/19/2016.
 * �ڶ����Ǹ���һ�����飬
 1, 2, 3, 4, 5, 6,  7,  8
 1, 4, 7, 9,12,14,15,20
 ��һ����index��Ҳ��һ�ʽ����������Ĺ�Ʊ������
 �ڶ����Ƕ�Ӧ�����棬����˵�ҵ�һ�ʽ���������ֻ��Ʊ����ô�������7������ѡ�����������������һֻһֻ����������Ҳ����һ������8ֻ����ĿҪ�����������档¥���õ�recursive�Ľⷨ�����һ����8ֻ��Ʊ����һ��������ֻ֮����һ����������һ����6ֻ����������档��һ��global variable��������棬������ľ͸��¡�
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
