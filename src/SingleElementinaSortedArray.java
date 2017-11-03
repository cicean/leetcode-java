import java.util.*;

/**
 *  540. Single Element in a Sorted Array
 DescriptionHintsSubmissionsDiscussSolution

 Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

 Example 1:

 Input: [1,1,2,3,3,4,4,8,8]
 Output: 2

 Example 2:

 Input: [3,3,7,7,10,11,11]
 Output: 10

 Note: Your solution should run in O(log n) time and O(1) space.
 */

public class SingleElementinaSortedArray {

  //Java Binary Search, short (7l), O(log(n)) w/ explanations

  //All credits go to @Penghuan who thought of using the pairs wisely.

  //This method seems to be a bit simpler to understand, since it doesn't start with the left half and stays a little bit closer to the conventional solutions.

  public static int singleNonDuplicate(int[] nums) {
    int start = 0, end = nums.length - 1;

    while (start < end) {
      // We want the first element of the middle pair,
      // which should be at an even index if the left part is sorted.
      // Example:
      // Index: 0 1 2 3 4 5 6
      // Array: 1 1 3 3 4 8 8
      //            ^
      int mid = (start + end) / 2;
      if (mid % 2 == 1) mid--;

      // We didn't find a pair. The single element must be on the left.
      // (pipes mean start & end)
      // Example: |0 1 1 3 3 6 6|
      //               ^ ^
      // Next:    |0 1 1|3 3 6 6
      if (nums[mid] != nums[mid + 1]) end = mid;

        // We found a pair. The single element must be on the right.
        // Example: |1 1 3 3 5 6 6|
        //               ^ ^
        // Next:     1 1 3 3|5 6 6|
      else start = mid + 2;
    }

    // 'start' should always be at the beginning of a pair.
    // When 'start > end', start must be the single element.
    return nums[start];
  }



}
