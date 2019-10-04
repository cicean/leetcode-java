import java.util.*;

/**
 * 457. Circular Array Loop
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is forward next to the last element, and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts and ends at a particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.

 Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.

 Example 2: Given the array [-1, 2], there is no loop.

 Note: The given array is guaranteed to contain no element "0".

 Can you do it in O(n) time complexity and O(1) space complexity?
 */

public class CircularArrayLoop {

  /**
   * Java Slow/Fast Pointer Solution
   Just think it as finding a loop in Linkedlist, except that loops with only 1 element do not count. Use a slow and fast pointer, slow pointer moves 1 step a time while fast pointer moves 2 steps a time. If there is a loop (fast == slow), we return true, else if we meet element with different directions, then the search fail, we set all elements along the way to 0. Because 0 is fail for sure so when later search meet 0 we know the search will fail.
   */

  public class Solution {
    public boolean circularArrayLoop(int[] nums) {
      int n = nums.length;
      for (int i = 0; i < n; i++) {
        if (nums[i] == 0) {
          continue;
        }
        // slow/fast pointer
        int j = i, k = getIndex(i, nums);
        while (nums[k] * nums[i] > 0 && nums[getIndex(k, nums)] * nums[i] > 0) {
          if (j == k) {
            // check for loop with only one element
            if (j == getIndex(j, nums)) {
              break;
            }
            return true;
          }
          j = getIndex(j, nums);
          k = getIndex(getIndex(k, nums), nums);
        }
        // loop not found, set all element along the way to 0
        j = i;
        int val = nums[i];
        while (nums[j] * val > 0) {
          int next = getIndex(j, nums);
          nums[j] = 0;
          j = next;
        }
      }
      return false;
    }

    public int getIndex(int i, int[] nums) {
      int n = nums.length;
      return i + nums[i] >= 0? (i + nums[i]) % n: n + ((i + nums[i]) % n);
    }
  }

}
