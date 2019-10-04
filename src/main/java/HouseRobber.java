/**
 * 198	House Robber	28.9%	Easy
 *
 * 0.Problem:
 * You are a professional robber planning to rob houses along 
 * a street. Each house has a certain amount of money stashed, 
 * the only constraint stopping you from robbing each of them 
 * is that adjacent houses have security system connected and 
 * it will automatically contact the police if two adjacent 
 * houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount 
 * of money of each house, determine the maximum amount of money 
 * you can rob tonight without alerting the police.
 * 
 * 1.Refer.: DP:
 * if rob nth: rob(n) = max(norob(n - 1) + nums[n], rob(n - 1)); 
 * if not rob nth: norob(n) = rob(n - 1);
 * 
 * 1.1 DP The key is to find the relation dp[i] = Math.max(dp[i-1], dp[i-2]+num[i-1]).
 * 1.2 We can use two variables, even and odd, to track the maximum value so far as iterating the array. 
 * You can use the following example to walk through the code.
 * 
 */

public class HouseRobber {
	
	public static int rob(int[] nums) {
	    int length = nums.length;
	    if (length <= 0) return 0;
	    int[] robs = new int[length];
	    int[] norobs = new int[length];
	    robs[0] = nums[0];
	    norobs[0] = 0;
	    for (int i = 1; i < length; i++) {
	      norobs[i] = robs[i - 1];
	      robs[i] = norobs[i - 1] + nums[i];
	      if (robs[i] < robs[i - 1]) robs[i] = robs[i - 1]; 
	    }
	    return robs[length - 1] > norobs[length - 1] ? robs[length - 1] : norobs[length - 1];
	  }
	
	public int rob_1(int[] num) {
	    if(num==null || num.length==0)
	        return 0;
	 
	    int n = num.length;
	 
	    int[] dp = new int[n+1];
	    dp[0]=0;
	    dp[1]=num[0];
	 
	    for (int i=2; i<n+1; i++){
	       dp[i] = Math.max(dp[i-1], dp[i-2]+num[i-1]);
	    }
	 
	    return dp[n];
	}
	
	public int rob_2(int[] num) {
		if(num==null || num.length == 0)
			return 0;
	 
		int even = 0;
		int odd = 0;
	 
		for (int i = 0; i < num.length; i++) {
			if (i % 2 == 0) {
				even += num[i];
				even = even > odd ? even : odd;
			} else {
				odd += num[i];
				odd = even > odd ? even : odd;
			}
		}
	 
		return even > odd ? even : odd;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,4,3,4,5,4,3,2,1};
		HouseRobber slt = new HouseRobber();
		System.out.println(rob(nums));
	    System.out.println(slt.rob_1(nums));
	}

}
