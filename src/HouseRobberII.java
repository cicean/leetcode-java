/*
 * Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Analysis

This is an extension of House Robber. There are two cases here 
1) 1st element is included and last is not included 
2) 1st is not included and last is included. 
Therefore, we can use the similar dynamic programming approach to scan the array twice and get the larger value.

和Leetcode: House Robber I很类似，但是需要遍历两遍，
第一遍加入第一个元素，因此最后一个元素不能用，第二遍不要第一个元素，因此可以使用最后一个元素。很巧妙。

 */
public class HouseRobberII {

	public int rob_1(int[] nums) {
	    if(nums==null||nums.length==0)
	        return 0;
	 
	    int n = nums.length;
	 
	    if(n==1){
	        return nums[0];
	    }    
	    if(n==2){
	        return Math.max(nums[1], nums[0]);
	    }
	 
	    //include 1st element, and not last element
	    int[] dp = new int[n+1];
	    dp[0]=0;
	    dp[1]=nums[0];
	 
	    for(int i=2; i<n; i++){
	        dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i-1]);
	    }
	 
	    //not include frist element, and include last element
	    int[] dr = new int[n+1];
	    dr[0]=0;
	    dr[1]=nums[1];
	 
	    for(int i=2; i<n; i++){
	    	dr[i] = Math.max(dr[i-1], dr[i-2]+nums[i]);
	    }
	 
	    return Math.max(dp[n-1], dr[n-1]);
	}
	
	public int rob_2(int[] nums) {  
        if(nums==null || nums.length==0) return 0;  
        if(nums.length==1) return nums[0];  
        if(nums.length==2) return Math.max(nums[0], nums[1]);  
        return Math.max(robsub(nums, 0, nums.length-2), robsub(nums, 1, nums.length-1));  
    }  
      
    private int robsub(int[] nums, int s, int e) {  
        int n = e - s + 1;  
        int[] d =new int[n];  
        d[0] = nums[s];  
        d[1] = Math.max(nums[s], nums[s+1]);  
          
        for(int i=2; i<n; i++) {  
            d[i] = Math.max(d[i-2]+nums[s+i], d[i-1]);  
        }  
        return d[n-1];  
    }  
    
    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
