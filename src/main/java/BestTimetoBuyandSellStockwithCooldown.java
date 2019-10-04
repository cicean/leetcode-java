/**
 * Created by cicean on 8/29/2016.
 *
 * 309. Best Time to Buy and Sell Stock with Cooldown  QuestionEditorial Solution  My Submissions
 Total Accepted: 23172 Total Submissions: 60109 Difficulty: Medium
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 Example:

 prices = [1, 2, 3, 0, 2]
 maxProfit = 3
 transactions = [buy, sell, cooldown, buy, sell]
 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Dynamic Programming
 Hide Similar Problems (E) Best Time to Buy and Sell Stock (M) Best Time to Buy and Sell Stock II

 */
public class BestTimetoBuyandSellStockwithCooldown {

    /**
     * ��Ϊ��ǰ����������Ʊ���ܵ�֮ǰ����������Ʊ��Ϊ��Ӱ�죬���ȿ��ǵ���DP�����

     �����Ƚ��鷳�����и�cooldown�����ƣ���ʵ����Ҳ����������֮������ơ�
     ����ĳһ�죬��Ʊ������״̬: buy, sell, cooldown, sell��cooldown���ǿ��Ժϲ���һ��״̬��
     ��Ϊ�������ն�û��Ʊ��������Ҫ�Ľ����sell���������Ʊ���˻���������
     �������ǿ���������DP����ֱ��¼��ǰ�ֹɸ�δ�ֹɵ�״̬��Ȼ�������Ŀ�е�������������������DP����ı��ʽ��

     ���ڵ�������δ�ֹɵ�״̬������������������ֿ��ܣ�һ�ǽ���û����������δ�ֹ�״̬һ������������ֹ��ˣ��������ˡ�
     ��������ֻҪȡ������֮�����ֵ���ɣ����ʽ���£�

     sellDp[i] = Math.max(sellDp[i - 1], buyDp[i - 1] + prices[i]);
     ���ڵ������ճֹɵ�״̬������������������ֿ��ܣ�һ�ǽ���û����������ֹ�״̬һ��������ǰ�컹û�ֹɣ�
     �������˹�Ʊ����������Ϊcooldown��ԭ�����Խ������Ҫ׷�ݵ�ǰ���״̬������ֻҪȡ������֮�����ֵ���ɣ����ʽ���£�

     buyDp[i] = Math.max(buyDp[i - 1], sellDp[i - 2] - prices[i]);
     ��������Ҫ��Ľ����

     sellDp[n - 1] ��ʾ���һ�����ʱ����û��Ʊʱ���ۻ��������
     ��Ȼ������ռ临�Ӷ��ǿ��Խ���O(1)�ģ�������ڶ��ִ���ʵ�֡�

     ���Ӷ�
     time: O(n), space: O(n)
     */

    public class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }

            // ��ʾ��������δ�ֹɵ�����£������������ۼ��������
            int[] sellDp = new int[prices.length];
            // ��ʾ�������ճֹɵ�����£������������ۼ��������
            int[] buyDp = new int[prices.length];

            // ���ǳ�ʼ���
            buyDp[0] = -prices[0];
            sellDp[0] = 0;
            for (int i = 1; i < prices.length; i++) {
                sellDp[i] = Math.max(sellDp[i - 1], buyDp[i - 1] + prices[i]);
                if (i >= 2) {
                    buyDp[i] = Math.max(buyDp[i - 1], sellDp[i - 2] - prices[i]);
                } else {
                    buyDp[i] = Math.max(buyDp[i - 1], -prices[i]);
                }
            }
            return sellDp[prices.length - 1];
        }
    }

    /**
     * ����(O(1) space)
     */

    public class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }

            int currBuy = -prices[0];
            int currSell = 0;
            int prevSell = 0;
            for (int i = 1; i < prices.length; i++) {
                int temp = currSell;
                currSell = Math.max(currSell, currBuy + prices[i]);
                if (i >= 2) {
                    currBuy = Math.max(currBuy, prevSell - prices[i]);
                } else {
                    currBuy = Math.max(currBuy, -prices[i]);
                }
                prevSell = temp;
            }
            return currSell;
        }
    }

}
