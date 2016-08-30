/**
 * 260	Single Number III	36.6%	Medium
 * @author cicean
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Bit Manipulation
Hide Similar Problems (M) Single Number (M) Single Number II

 */

public class SingleNumberIII {
	
	 public int[] singleNumber(int[] nums) {
		 
		 int n = 0;
		 for (int num : nums) {
			 n ^= num;
		 }
		 n = n & ~(n - 1);
		 int[]  res = new int[2];
		 for (int num : nums) {
			 if ((num & n) == 0) {
				 res[0] ^= num;
			 } else {
				 res[1] ^= num;
			 }
		 }
		 return res;
	        
	    }
	 
	 public int[] singleNumber1(int[] nums) {  
	        int A = 0;  
	        int B = 0;  
	        int AXORB = 0;  
	        for(int i = 0; i<nums.length; i++){  
	            AXORB ^= nums[i];  
	        }  
	          
	        AXORB = (AXORB & (AXORB - 1)) ^ AXORB; //find the different bit  
	        for(int i = 0; i<nums.length; i++){  
	            if((AXORB & nums[i]) == 0)  
	                A ^= nums[i];  
	            else  
	                B ^= nums[i];  
	        }  
	        return new int[]{A, B};  
	    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleNumberIII slt = new SingleNumberIII();
		int[] nums = {1, 2, 1, 3, 2, 5};
		int[] res= slt.singleNumber(nums);
		for (int i = 0; i < res.length; i++){
		System.out.print(res[i] + " ");
		}
	}

}
