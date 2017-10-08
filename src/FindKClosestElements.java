import java.util.*;

/**
 *
 658. Find K Closest Elements
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

 Example 1:
 Input: [1,2,3,4,5], k=4, x=3
 Output: [1,2,3,4]
 Example 2:
 Input: [1,2,3,4,5], k=4, x=-1
 Output: [1,2,3,4]
 Note:
 The value k is positive and will always be smaller than the length of the sorted array.
 Length of the given array is positive and will not exceed 104
 Absolute value of elements in the array and x will not exceed 104
 UPDATE (2017/9/19):
 The arr parameter had been changed to an array of integers (instead of a list of integers).
 Please reload the code definition to get the latest changes.
 */

public class FindKClosestElements {

  /**Solution

  Approach #1 Using Collection.sort( ) [Accepted]

  Algorithm

  Intuitively, we can sort the elements in list arr by their absolute difference values to the target x. Then the sublist of the first k elements is the result after sorting the elements by the natural order.

   Note: This solution is inspired by @compton_scatter.

   Complexity Analysis

   Time complexity : O(n*log(n))O(n∗log(n)). Collections.sort() uses binary sort so it has a O(n*log(n))O(n∗log(n)) complexity.

   Space complexity : O(k)O(k). The in-place sorting does not consume any extra space. However, generating a k length sublist will take some space.

   */
  public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
    Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
    arr = arr.subList(0, k);
    Collections.sort(arr);
    return arr;
  }

  /**
      Approach #2 Using Binary Search and Two Pointers [Accepted]

  Algorithm

  The original array has been sorted so we can take this advantage by the following steps.
   1. If the target x is less or equal than the first element in the sorted array, the first k elements are the result.
   2. Similarly, if the target x is more or equal than the last element in the sorted array, the last k elements are the result.
   3. Otherwise, we can use binary search to find the index of the element, which is equal
   (when this list has x) or a little bit larger than x (when this list does not have it).
   Then set low to its left k-1 position, and high to the right k-1 position of this index as a start.
   The desired k numbers must in this rang [index-k-1, index+k-1]. So we can shrink this range to
   get the result using the following rules. * If low reaches the lowest index 0 or the low element
   is closer to x than the high element, decrease the high index. * If high reaches to the highest
   index arr.size()-1 or it is nearer to x than the low element, increase the low index.
   * The looping ends when there are exactly k elements in [low, high], the subList of which is the result.

   Complexity Analysis

   Time complexity : O(log(n)+k)O(log(n)+k). O(log(n))O(log(n)) is for the time of binary search, while O(k)O(k) is for shrinking the index range to k elements.

   Space complexity : O(k)O(k). It is to generate the required sublist.

   */
  public class Solution {

    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
      int n = arr.size();
      if (x <= arr.get(0)) {
        return arr.subList(0, k);
      } else if (arr.get(n - 1) <= x) {
        return arr.subList(n - k, n);
      } else {
        int index = Collections.binarySearch(arr, x);
        if (index < 0)
          index = -index - 1;
        int low = Math.max(0, index - k - 1), high = Math.min(arr.size() - 1, index + k - 1);

        while (high - low > k - 1) {
          if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x))
            high--;
          else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x))
            low++;
          else
            System.out.println("unhandled case: " + low + " " + high);
        }
        return arr.subList(low, high + 1);
      }
    }
  }

}
