/**
 * 70. Climbing Stairs
 * Easy
 *
 * 3446
 *
 * 112
 *
 * Add to List
 *
 * Share
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * Accepted
 * 579,482
 * Submissions
 * 1,254,923
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 11
 *
 * Uber
 * |
 * 5
 *
 * Adobe
 * |
 * 5
 *
 * Microsoft
 * |
 * 5
 *
 * Oracle
 * |
 * 3
 *
 * Bloomberg
 * |
 * 2
 *
 * Apple
 * |
 * 2
 *
 * Facebook
 * |
 * 2
 *
 * Huawei
 * |
 * 2
 *
 * TripAdvisor
 * |
 * 2
 * Min Cost Climbing Stairs
 * Easy
 * Fibonacci Number
 * Easy
 * N-th Tribonacci Number
 * Easy
 * To reach nth step, what could have been your previous steps? (Think about the step sizes)
 */

public class ClimbingStairs {
	public int climbStairs(int n) {
        int[] f = new int[n+1];
        f[0] = 1; f[1] = 1;
        for (int i = 2; i <= n; ++i)
            f[i] = f[i-1] + f[i-2];
        return f[n];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClimbingStairs slt = new ClimbingStairs();
		int n = 10;
		int res = slt.climbStairs(n);
		System.out.println(res);
	}

	/**
	 * Google similar questions
	 * xxxxx在偶数轮可以滚落1, 3或者4个stairs，奇数轮可以滚1, 2或者4 stairs，然后中间会有一些sticky stairs，
	 * 掉到sticky stairs的时候不能move小球直接挂掉。问的是从这个n个stairs高的楼梯，小球有多少种方法能够到达ground，
	 * 也就是stair为0的这一层，超过的话，比如-3，-2，-1也算是到达ground。一开始用dfs的暴力解，后面想用dp做，做到一半的时候时间没了。
	 * 画了个图，大概长这样。
	 */
	public int climbStairs(int n) {
		int[] f = new int[n+1];
		f[1] = 1; f[2] = 2; f[3] =
		for (int i = 2; i <= n; ++i)
			f[i] = f[i-1] + f[i-2];
		return f[n];
	}


}
