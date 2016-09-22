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

    public int maxSubArray_2(int[] A) {
        if (A.length == 0) return 0;
        int dp = A[0], res = A[0];
        for (int i = 1; i < A.length; ++i) {
            dp = Math.max(A[i], dp + A[i]);
            res = Math.max(res, dp);
        }
        return res;
    }

    // divide and conquer
    public int maxSubArray(int[] A) {
        return helper(A, 0, A.length);
    }

    public int helper(int[] A, int start, int end){
        if(start==end-1) return A[start];
        int middle = (start + end) / 2;
        int leftSub = helper(A, start, middle);
        int rightSub = helper(A, middle, end);
        int i,leftcut=-1,rightcut=-1,leftsum=0,rightsum=0,leftmax=-Integer.MAX_VALUE,rightmax=-Integer.MAX_VALUE;
        for(i=middle;i<end;i++){
            rightsum += A[i];
            if(rightsum>=0 && rightsum>rightmax){
                rightmax = rightsum;
                rightcut = i;
            }
        }
        for(i=middle-1;i>=start;i--){
            leftsum += A[i];
            if(leftsum>=0 && leftsum>leftmax){
                leftmax = leftsum;
                leftcut = i;
            }
        }

        int sub;
        if(leftSub>rightSub){
            sub = leftSub;
        }
        else{
            sub = rightSub;
        }
        if(leftcut==-1 || rightcut==-1){
            return sub;
        }
        else{
            if(sub>leftmax+rightmax){
                return sub;
            }
            else{
                return leftmax + rightmax;
            }
        }
    }


    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	MaximumSubarray slt = new MaximumSubarray();
		int[] A = {-2,1,-3,4,-1,2,1,-5,4};
		int res = slt.maxSubArray_2(A);
		System.out.println(res);
	}
}
