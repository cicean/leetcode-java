/**
 * Created by cicean on 8/29/2016. 312. Burst Balloons QuestionEditorial
 * Solution My Submissions Total Accepted: 14014 Total Submissions: 35115
 * Difficulty: Hard Given n balloons, indexed from 0 to n-1. Each balloon is
 * painted with a number on it represented by array nums. You are asked to burst
 * all the balloons. If the you burst balloon i you will get nums[left] *
 * nums[i] * nums[right] coins. Here left and right are adjacent indices of i.
 * After the burst, the left and right then becomes adjacent.
 * 
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note: (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore
 * you can not burst them. (2) 0 �� n �� 500, 0 �� nums[i] �� 100
 * 
 * Example:
 * 
 * Given [3, 1, 5, 8]
 * 
 * Return 167
 * 
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> [] coins = 3*1*5 + 3*5*8 +
 * 1*3*8 + 1*8*1 = 167 Credits: Special thanks to @dietpepsi for adding this
 * problem and creating all test cases.
 * 
 * Hide Company Tags Google Hide Tags Divide and Conquer Dynamic Programming
 */
public class BurstBalloons {

	/**
	 * ����������һ�ִ��������Ϸ��ÿ�����򶼶�Ӧ��һ�����֣� ����ÿ�δ�һ�����򣬵õ��Ľ�����Ǳ��򱬵���������ֺ������ߵ������ϵ�������ˣ�
	 * ����Ա�û�������ˣ���1�㣬�Դ����ƣ����ܵõ������������ ��������ֵ���⣬����һ�㶼Ҫ�����ö�̬�滮Dynamic
	 * Programming������ ����ά��һ����ά��̬����dp������dp[i][j]��ʾ������[i,j]�е����������ܵõ�������ҡ�
	 * ��Ŀ��˵���˱߽��������������Χû�������ʱ���Աߵ����ְ�1�㣬
	 * �������ǿ�����ԭ�������߸����һ��1�����������ڼ��㡣���������ѵ�����ҵݹ�ʽ��������ʾ��
	 * 
	 * dp[i][j] = max(dp[i][j], nums[i - 1]*nums[k]*nums[j + 1] + dp[i][k - 1] +
	 * dp[k + 1][j]) ( i �� k �� j )
	 * 
	 * ���˵���ʽ�����ǿ���д���룬������ʵֻ�Ǹ�����dp�������������������������Ҫ���ص�ֵ����dp[1][n]�У�
	 * ����n���������1֮ǰ����nums�ĸ������μ��������£�
	 */

	// O(n^3)
	public class Solution {
		public int maxCoins(int[] iNums) {
			int n = iNums.length;
			int[] nums = new int[n + 2];
			for (int i = 0; i < n; i++)
				nums[i + 1] = iNums[i];
			nums[0] = nums[n + 1] = 1;
			int[][] dp = new int[n + 2][n + 2];
			for (int k = 1; k <= n; k++) {
				for (int i = 1; i <= n - k + 1; i++) {
					int j = i + k - 1;
					for (int x = i; x <= j; x++) {
						dp[i][j] = Math.max(dp[i][j], dp[i][x - 1]
								+ nums[i - 1] * nums[x] * nums[j + 1]
								+ dp[x + 1][j]);
					}
				}
			}
			return dp[1][n];
		}
	}

	/**
	 * Divide and Conquer ���仯����
	 */
	public class Solution2 {
		public int DP(int i, int j, int[] nums, int[][] dp) {
			if (dp[i][j] > 0)
				return dp[i][j];
			for (int x = i; x <= j; x++) {
				dp[i][j] = Math.max(dp[i][j],
						DP(i, x - 1, nums, dp) + nums[i - 1] * nums[x]
								* nums[j + 1] + DP(x + 1, j, nums, dp));
			}
			return dp[i][j];
		}

		public int maxCoins(int[] iNums) {
			int n = iNums.length;
			int[] nums = new int[n + 2];
			for (int i = 0; i < n; i++)
				nums[i + 1] = iNums[i];
			nums[0] = nums[n + 1] = 1;
			int[][] dp = new int[n + 2][n + 2];
			return DP(1, n, nums, dp);
		}
	}

}
