/*
11	Container With Most Water	32.0%	Medium
 Problem:    Container With Most Water
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/container-with-most-water/
 Notes:
 Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 Note: You may not slant the container.
 Solution: From both sides to the center.
*/

public class ContainerWithMostWater {
	
	 public int maxArea_1(int[] height) {
	        int left = 0, right = height.length - 1;
	        int res = 0;
	        while (left < right) {
	            res = Math.max(res, Math.min(height[left],height[right]) * (right - left));
	            if (height[left] > height[right]) --right;
	            else ++left;
	        }
	        return res;
	    }
	
	public int maxArea(int[] height) {
		if (height == null || height.length < 2) {
			return 0;
		}
	 
		int max = 0;
		int left = 0;
		int right = height.length - 1;
	 
		while (left < right) {
			max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}
	 
		return max;
	}
	
	 public static void main(String[] args)
		{
		 ContainerWithMostWater slt = new ContainerWithMostWater();
	    	int[] h={1,2,4,5,3,8,9};
	    	int res = slt.maxArea_1(h);
	    	System.out.print(res);
			
		}  
}
