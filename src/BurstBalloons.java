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
 * you can not burst them. (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
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
	 * 这道题提出了一种打气球的游戏，每个气球都对应着一个数字， 我们每次打爆一个气球，得到的金币数是被打爆的气球的数字和其两边的气球上的数字相乘，
	 * 如果旁边没有气球了，则按1算，以此类推，求能得到的最多金币数。 像这种求极值问题，我们一般都要考虑用动态规划Dynamic
	 * Programming来做， 我们维护一个二维动态数组dp，其中dp[i][j]表示打爆区间[i,j]中的所有气球能得到的最多金币。
	 * 题目中说明了边界情况，当气球周围没有气球的时候，旁边的数字按1算，
	 * 这样我们可以在原数组两边各填充一个1，这样方便于计算。这道题的最难点就是找递归式，如下所示：
	 * 
	 * dp[i][j] = max(dp[i][j], nums[i - 1]*nums[k]*nums[j + 1] + dp[i][k - 1] +
	 * dp[k + 1][j]) ( i ≤ k ≤ j )
	 * 
	 * 有了递推式，我们可以写代码，我们其实只是更新了dp数组的右上三角区域，我们最终要返回的值存在dp[1][n]中，
	 * 其中n是两端添加1之前数组nums的个数。参见代码如下：
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
	 * Divide and Conquer 记忆化搜索
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
