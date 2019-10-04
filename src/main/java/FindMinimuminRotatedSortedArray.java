/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Oct 22, 2014
 Problem:    Find Minimum in Rotated Sorted Array
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 Notes:
 Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 Find the minimum element.
 You may assume no duplicate exists in the array.
*/

public class FindMinimuminRotatedSortedArray {
	
	 public int findMin(int[] num) {
	        if (num.length == 0) return 0;
	        int left = 0, right = num.length -1;
	        while (left < right && num[left] > num[right]) {
	            int mid = left + (right - left) / 2;
	            if (num[mid] > num[right]) left = mid + 1;
	            else right = mid;
	        }
	        return num[left];
	    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {0,1,2,4,5,6,7};
		FindMinimuminRotatedSortedArray slt = new FindMinimuminRotatedSortedArray();
		System.out.print(slt.findMin(A));
		
	}

}
