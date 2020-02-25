import java.util.*;

/**
 * 442. Find All Duplicates in an Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements that appear twice in this array.

 Could you do it without extra space and in O(n) runtime?

 Example:
 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [2,3]
 */

public class FindAllDuplicatesinanArray {

  public class Solution {
    // when find a number i, flip the number at position i-1 to negative.
    // if the number at position i-1 is already negative, i is the number that occurs twice.

    public List<Integer> findDuplicates(int[] nums) {
      List<Integer> res = new ArrayList<>();
      for (int i = 0; i < nums.length; ++i) {
        int index = Math.abs(nums[i])-1;
        if (nums[index] < 0)
          res.add(Math.abs(index+1));
        nums[index] = -nums[index];
      }
      return res;
    }
  }

  class Solution_2 {
    public List<Integer> findDuplicates(int[] nums) {

      List<Integer> result = new ArrayList<>();
      int[] occurance = new int[nums.length];

      for(int num : nums)
        occurance[num-1]++;

      for(int i=0;i<nums.length;i++){
        if(occurance[i]>1)
          result.add(i+1);
      }
      return result;

    }
  }

}
