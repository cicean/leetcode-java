/**
 * 	256	Paint House 	36.2%	Medium
 * @author cicean
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

	The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on�� Find the minimum cost to paint all houses.

	Note:

	All costs are positive integers.

	�ݹ�pass���ˣ�ֻ���ö�̬�滮��
	
	����:% ~9 \( N2 ~5 q% t5 C/ i2 d
	������n������, ���0~n-1. ��3��������ɫ, ���0~2. Ȼ����һ���ɱ����� cost[���ӱ��][��ɫ���]��ʾ��ͬ������ɫ�벻ͬ���ӵĳɱ�, ���ܳɱ���С. ���ڷ��Ӳ�����ͬһ����ɫ.   [. f5 j: R( ~9 v

	˼·:
	ʹ��DP, �ؼ���������� costs��i��[0] += Math.min(costs[i - 1][1], costs[i - 1][2]):- C: l# F, N* [
	û��ʹ�ö���ļ�����, ֱ�Ӱ��ܳɱ��ۼ��ڵ�ǰ�ĳɱ�. 
	��ÿ������ÿ����ɫ�ĵ�ǰ�ɱ���ǰһ�����ӵ���С�ɱ��ۼ�(������ǰ��ǰһ������).
	��Ϊһ����3����ɫ, ���Ի�Ȧ����3�����в�ͣ�ۼӵ�����һ������, ��������С��.
	ʱ�临�Ӷ�O(n), �ռ临�Ӷ�O(1):
	
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
