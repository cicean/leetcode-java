/*
 152	Maximum Product Subarray	19.4%	Medium
 Problem:    Maximum Product Subarray
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/maximum-product-subarray/
 Notes:
 Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.
 
 O£¨n£©
 
 Solution
 
 1.Brute-force The time of the solution is O(n^3).
 2.DP
  When iterating the array, each element has two possibilities: positive number or negative number. 
  We need to track a minimum value,so that when a negative number is given, 
  it can also find the maximum value. We define two local variables, one tracks the maximum and the other tracks the minimum.
 
*/


public class MaximumProductSubarray {

	public int maxProduct_1(int[] A) {
	    int max = Integer.MIN_VALUE;
	 
	    for(int i=0; i<A.length; i++){
	        for(int l=0; l<A.length; l++){
	            if(i+l < A.length){
	                int product = calProduct(A, i, l);
	                max = Math.max(product, max);
	            }
	 
	        }
	 
	    }
	    return max;
	}
	 
	public int calProduct(int[] A, int i, int j){
	    int result = 1;
	    for(int m=i; m<=j; m++){
	        result = result * A[m];
	    }
	    return result;
	}
	
	 public int maxProduct_2(int[] A) {
	        if (A.length <= 0) {
	            return 0;
	        }
	        int maxVal = A[0], minVal = A[0], res = A[0];
	        for (int i = 1; i < A.length; ++i) {
	            int tmpVal = maxVal;
	            maxVal = Math.max(Math.max(maxVal * A[i], minVal * A[i]), A[i]);
	            minVal = Math.min(Math.min(tmpVal * A[i], minVal * A[i]), A[i]);
	            res = Math.max(res, maxVal);
	        }
	        return res;  
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      int[] A = {2,3,-2,4} ;
      
      MaximumProductSubarray slt = new MaximumProductSubarray();
      
      System.out.print(slt.maxProduct_1(A));
      
	}

}
