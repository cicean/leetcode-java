import java.util.*;


/*
1	Two Sum	17.6%	Medium

Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

O(n2) runtime, O(1) space �C Brute force:
The brute force approach is simple. Loop through each element x and find if there is another value that equals to target �C x. As finding another value requires looping through the rest of array, its runtime complexity is O(n2).

O(n) runtime, O(n) space �C Hash table:
We could reduce the runtime complexity of looking up a value to O(1) using a hash map that maps a value to its index.

Solution: 1. Hash table. O(n)
Solution: 2.  Brute force O(n2) runtime, O(1) space //clone array 
           
 Note:  Hash Table solution has been updated.  In case that the two elements are the same, 
        all the indices should be stored in the map.

*/

//Solution: 1. Hash table. O(n)
public class TwoSum {
	
	public int[] twoSum(int[] nums, int target) {
       HashMap<Integer, Integer> map = new HashMap <Integer, Integer>();
       List<List<Integer>> res = new ArrayList<>();
       res.s
       for (int i=0; i<nums.length; i++)
       {
    	   int b=target - nums[i];
    	   if(map.get(b) !=null)
    	   {
    		   return new int[]{map.get(b),i};
       	   }else map.put(nums[i],i);
    	  
    	}
       
       return null;
    }
	

	public static void main(String[] args)
	{
		TwoSum slt = new TwoSum();
		int[] nums = {2,7,11,15};
		int[] index = new int[2];
		index = slt.twoSum(nums, 9);
		
		System.out.println("index1 = " + index[0] + ", " + "index2 = " + index[1]);
	}
}

//Solution: 2.  Brute force O(n2) runtime, O(1) space //clone array 

/*

public class TwoSum {
	
	public int[] twoSum(int[] nums, int target) {
     int[] num = nums.colne();
     Array.sort(num);
     
     int length = nums.length;
     int left = 0;
     int right = length - 1;
     int sum = 0;
     
     ArrayList<Integer> index =  new ArrayList<Integer>();
     
     while(left<right)
     {
    	 sum = num[left]+num[right];
    	 if(sum == target)
    	 {
    		 for(int i=0;i<length; ++i)
    		 {
    			 if(nums[i] == num[left])
    			 {
    				 index.add(i+1);
    			 }else if(nums[i] == num[right])
    			 {
    				 index.add(i+1);
    			 }
    			 if(index.size() == 2)
    			 {
    				 break;
    			 }
    		 }
    		 break;
    	 }else if (sum > target)
    	 {
    		 --right;
    	 }else
    	 {
    		 ++left;
    	 }
     }
     
     int[] result = new int[2];
     result[0] = index.get[0];
     result[1] = index.get[1];
     
     return result;
   
    }
	

	public static void main(String[] args)
	{
		TwoSum slt = new TwoSum();
		int[] nums = {2,7,11,15};
		int[] index = new int[2];
		index = slt.twoSum(nums, 9);
		
		System.out.println("index1 = " + index[0] + ", " + "index2 = " + index[1]);
	}
}

*/