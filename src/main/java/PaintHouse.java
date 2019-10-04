/**
 * 	256	Paint House 	36.2%	Medium
 * @author cicean
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

	The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on… Find the minimum cost to paint all houses.

	Note:

	All costs are positive integers.

	递归pass不了，只能用动态规划。
	
	大意:% ~9 \( N2 ~5 q% t5 C/ i2 d
	就是有n个房子, 编号0~n-1. 有3种油漆颜色, 编号0~2. 然後有一个成本阵列 cost[房子编号][颜色编号]显示不同油漆颜色与不同房子的成本, 求总成本最小. 相邻房子不能漆同一种颜色.   [. f5 j: R( ~9 v

	思路:
	使用DP, 关键是这个代码 costs【i】[0] += Math.min(costs[i - 1][1], costs[i - 1][2]):- C: l# F, N* [
	没有使用额外的记忆体, 直接把总成本累加在当前的成本. 
	把每个房子每个颜色的当前成本与前一个房子的最小成本累加(想像往前看前一个房子).
	因为一共有3种颜色, 所以回圈内有3个阵列不停累加到最後一个房子, 并返回最小的.
	时间复杂度O(n), 空间复杂度O(1):
	
 */
public class PaintHouse {

	public int minCost(int[][] costs) {
		if (costs == null || costs.length == 0) {
			return 0;
		}
		
		int h = costs.length;
		int w = costs[0].length;
		
		int[][] map = new int[h+1][w];
		
		for (int i = 0;i < h; i++) {
			for (int j = 0; j < w; j++) {
				map[i+1][j] = Integer.MAX_VALUE;
			}
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				for (int k = 0; k < w; k++) {
					if (j != k) {
						map[i + 1][j] = Math.min(map[i+1][j], map[i][k] + costs[i][j]);
					}
				}
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < w; i++) {
			result = Math.min(result, map[h][i]);
		}
		return result;
	}
	
	public int minCost_2(int[][] costs) {
		int n = costs.length;
		if (n == 0) {
			return 0;
		}
		
		for (int i = 1; i < n; i++) {
			costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
			costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
		}
		
		return Math.min(Math.min(costs[n - 1][0], costs[n - 1][1]), costs[n - 1][2]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
