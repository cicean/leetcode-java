/**
 * 276 Paint Fence 23.8% Easy Problem Description:
 * 
 * There is a fence with n posts, each post can be painted with one of the k
 * colors.
 * 
 * You have to paint all the posts such that no more than two adjacent fence
 * posts have the same color.
 * 
 * Return the total number of ways you can paint the fence.
 * 
 * Note: n and k are non-negative integers.
 * 
 * A dynamic programming problem. This post shares a nice solution and
 * explanation. Well, this article is simply an organization of this post and
 * its first answer (also posted by me :-)).
 * 
 * I use s (same) to stand for the number of ways when the last two fences are
 * painted with the same color (the last element of dp1 in the above post) and d
 * (different) with d1 and d2 to stand for the last two elements of dp2 in the
 * above post.
 * 
 * In each loop, dp1[i] = dp2[i - 1] turns into s = d2 and dp2[i] = (k - 1) *
 * (dp2[i - 2] + dp2[i - 1]) becomes d2 = (k - 1) * (d1 + d2). Moreover, d1
 * needs to be set to the oldd2, which is recorded in s. So we have d1 = s.
 * 
 * Finally, the return value dp1[n - 1] + dp2[n - 1] is just s + d2.
 * 
 * @author cicean
 *
 *         因为题目要求是不超过两个相邻的栅栏有同样颜色，所以可以把题目分解一下：
 *         设T(n)为符合要求的染色可能总数，S(n)为最后两个相邻元素为相同颜色的染色可能数，D(n)为最后两个相邻元素为不同颜色的染色可能数。
 *         显然D(n) = (k - 1) * (S(n-1) + D(n-1)) S(n) = D(n-1) T(n) = S(n) + D(n)
 *         带入化简一下得出： T(n) = (k - 1) * (T(n-1) + T(n-2)), n > 2
 *
 */
public class PaintFence {

	/**
	 * A Constant Space solution
	 * 
	 * @param n
	 * @param k
	 * @return
	 */

	int num_colors(int n, int k) {
		if (n <= 0 || k <= 0)
			return 0;
		int prev_prev = k, prev = k * k;
		for (int i = 0; i < n - 1; ++i) {
			int old_dif = prev;
			prev = (k - 1) * (prev_prev + prev);
			prev_prev = old_dif;
		}
		return prev_prev;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * We can use a DP solution.
	 * @param n
	 * @param k
	 * @return
	 */
	public int numWays(int n, int k) {
		if (n <= 0 || k <= 0) {
			return 0;
		}

		if (n == 1) {
			return k;
		}

		// i -1 and i -2 with the same color
		int[] dp1 = new int[n];
		// i - 1nad i - 2 with diff. color
		int[] dp2 = new int[n];

		// Initializaiton
		dp1[0] = 0;
		dp2[0] = k;

		for (int i = 1; i < n; i++) {
			dp1[i] = dp2[i - 1];
			dp2[i] = (k - 1) * (dp1[i - 1] + dp2[i - 1]);
		}

		// Final state
		return dp1[n - 1] + dp2[n - 1];
	}

}
