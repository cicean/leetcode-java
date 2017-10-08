/**
 *
 674. Longest Continuous Increasing Subsequence
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an unsorted array of integers, find the length of longest continuous increasing subsequence.

 Example 1:
 Input: [1,3,5,4,7]
 Output: 3
 Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 Example 2:
 Input: [2,2,2,2,2]
 Output: 1
 Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 Note: Length of the array will not exceed 10,000.
 */

public class LongestContinuousIncreasingSubsequence {

  /*
  The idea is to use cnt to record the length of the current continuous increasing subsequence
  which ends with nums[i], and use res to record the maximum cnt.
   */
  public int findLengthOfLCIS(int[] nums) {
    int res = 0, cnt = 0;
    for(int i = 0; i < nums.length; i++){
      if(i == 0 || nums[i-1] < nums[i]) res = Math.max(res, ++cnt);
      else cnt = 1;
    }
    return res;
  }

}
