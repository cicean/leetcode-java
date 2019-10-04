import java.util.*;

/**
 * 523. Continuous Subarray Sum
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 Note:
 The length of the array won't exceed 10,000.
 You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */

public class ContinuousSubarraySum {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]

   The brute force approach is trivial. We consider every possible subarray of size greater than or equal to 2,
   find out its sum by iterating over the elements of the subarray,
   and then we check if the sum obtained is an integer multiple of the given kk.

   Complexity Analysis

   Time complexity : O(n^3). Three for loops iterating over the array are used.
   Space complexity : O(1)O(1). Constant extra space is used.

   */

  public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {

      for (int start = 0; start < nums.length - 1; start++) {
        for (int end = start + 1; end < nums.length; end++) {
          int sum = 0;
          for (int i = start; i <= end; i++)
            sum += nums[i];
          if (sum == k || (k != 0 && sum % k == 0))
            return true;
        }
      }
      return false;
    }
  }

  /**
   * Approach #2 Better Brute Force [Accepted]

   Algorithm

   We can optimize the brute force approach to some extent, if we make use of an array sumsum that stores the cumulative sum of the elements of the array,
   such that sum[i]sum[i] stores the sum of the elements upto the i^{th}element of the array.

   Thus, now as before, we consider every possible subarray for checking its sum. But,
   instead of iterating over a new subarray everytime to determine its sum, we make use of the
   cumulative sum array. Thus, to determine the sum of elements from the i^{th} index to the j^{th} index,
   including both the corners, we can use: sum[j] - sum[i] + nums[i]sum[j]−sum[i]+nums[i].
   Complexity Analysis

   Time complexity : O(n^2). Two for loops are used for considering every subarray possible.

   Space complexity : O(n)O(n). sumsum array of size nn is used.
   */

  public class Solution2 {
    public boolean checkSubarraySum(int[] nums, int k) {
      int[] sum = new int[nums.length];
      sum[0] = nums[0];
      for (int i = 1; i < nums.length; i++)
        sum[i] = sum[i - 1] + nums[i];
      for (int start = 0; start < nums.length - 1; start++) {
        for (int end = start + 1; end < nums.length; end++) {
          int summ = sum[end] - sum[start] + nums[start];
          if (summ == k || (k != 0 && summ % k == 0))
            return true;
        }
      }
      return false;
    }
  }

  /**
   * Approach #3 Using HashMap [Accepted]

   Algorithm

   In this solution, we make use of a HashMap that is used to store the cumulative sums upto the i^{th}i
   ​th
   ​​  index after some processing along with the index ii. The processing done is taking the modulus of the the sum upto the i^{th}i
   ​th
   ​​  index with the given kk. The reasoning behind this will become clear soon.

   We traverse over the given array, and keep on calculating the sumsum values upto the current index. Whenever we find a new sumsum value, which isn't present in the HashMap already, we make an entry in the HashMap of the form, (sum(sum.

   Now, assume that the given sumsum value at the i^{th}i
   ​th
   ​​  index be equal to remrem. Now, if any subarray follows the i^{th}i
   ​th
   ​​  element, which has a sum equal to the integer multiple of kk, say extending upto the j^{th}j
   ​th
   ​​  index, the sum value to be stored in the HashMap for the j^{th}j
   ​th
   ​​  index will be: (rem+n∗k)(rem+n∗k), where nn is some integer > 0. We can observe that (rem+n∗k)(rem+n∗k), which is the same value as stored corresponding to the i^{th}i
   ​th
   ​​  index.

   From this observation, we come to the conclusion that whenever the same sumsum value is obtained corresponding to two indices ii and jj, it implies that sum of elements betweeen those indices is an integer multiple of kk. Thus, if the same sumsum value is encountered again during the traversal, we return a \text{True}True directly.

   The slideshow below depicts the process for the array nums: [2, 5, 33, 6, 7, 25, 15] and k=13.
   Complexity Analysis

   Time complexity : O(n)O(n). Only one traversal of the array numsnums is done.

   Space complexity : O(min(n,k))O(min(n,k)). The HashMap can contain upto min(n,k)min(n,k) different pairings.
   */

  public class Solution3 {
    public boolean checkSubarraySum(int[] nums, int k) {
      int sum = 0;
      HashMap < Integer, Integer > map = new HashMap < > ();
      map.put(0, -1);
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (k != 0)
          sum = sum % k;
        if (map.containsKey(sum)) {
          if (i - map.get(sum) > 1)
            return true;
        } else
          map.put(sum, i);
      }
      return false;
    }
  }

}
