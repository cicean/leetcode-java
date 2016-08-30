/*
 53	Maximum Subarray	34.5%	Medium
 Problem:    Maximum Subarray
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/maximum-subarray/
 Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 Solution: dp.
 */


public class MaximumSubarray {

	public int maxSubArray_1(int[] A) {
        if (A.length == 0) return 0;
        int minVal = Math.min(A[0],0), res = A[0], sum = A[0];
        for (int i = 1; i < A.length; ++i) {
            sum += A[i];
            res = Math.max(res, sum - minVal);
            minVal = Math.min(minVal, sum);
        }
        return res;
    }
    public int maxSubArray_2(int[] A) {
        if (A.length == 0) return 0;
        int dp = A[0], res = A[0];
        for (int i = 1; i < A.length; ++i) {
            dp = Math.max(A[i], dp + A[i]);
            res = Math.max(res, dp);
        }
        return res;
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	MaximumSubarray slt = new MaximumSubarray();
		int[] A = {-2,1,-3,4,-1,2,1,-5,4};
		int res = slt.maxSubArray_2(A);
		System.out.println(res);
	}
}
