import java.util.*;

/**
 * 496. Next Greater Element I
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

 The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

 Example 1:
 Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 Output: [-1,3,-1]
 Explanation:
 For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 For number 1 in the first array, the next greater number for it in the second array is 3.
 For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 Example 2:
 Input: nums1 = [2,4], nums2 = [1,2,3,4].
 Output: [3,-1]
 Explanation:
 For number 2 in the first array, the next greater number for it in the second array is 3.
 For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 Note:
 All elements in nums1 and nums2 are unique.
 The length of both nums1 and nums2 would not exceed 1000.

 Summary

 You are given two arrays (without duplicates) findNumsfindNums and numsnums where findNumsfindNums’s elements are subset of numsnums.Find all the next greater numbers for findNumsfindNums's elements in the corresponding places of numsnums.

 The Next Greater Number of a number x in findNumsfindNums is the first greater number to its right in numsnums. If it does not exist, output -1 for this number.
 */

public class NextGreaterElementI {

  /**
   * Approach #1 Brute Force [Accepted]

   In this method, we pick up every element of the findNumsfindNums array(say findNums[i]findNums[i])
   and then search for its own occurence in the numsnums array(which is indicated by setting foundfound to True).
   After this, we look linearly for a number in numsnums which is greater than findNums[i]findNums[i],
   which is also added to the resres array to be returned. If no such element is found, we put a \text{-1}-1
   at the corresponding location.
   Complexity Analysis

   Time complexity : O(m*n)O(m∗n). The complete numsnums array(of size nn) needs to be scanned for all the mm elements of findNumsfindNums in the worst case.
   Space complexity : O(m)O(m). resres array of size mm is used, where mm is the length of findNumsfindNums array.
   */

  public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
      int[] res = new int[findNums.length];
      int j;
      for (int i = 0; i < findNums.length; i++) {
        boolean found = false;
        for (j = 0; j < nums.length; j++) {
          if (found && nums[j] > findNums[i]) {
            res[i] = nums[j];
            break;
          }
          if (nums[j] == findNums[i]) {
            found = true;
          }
        }
        if (j == nums.length) {
          res[i] = -1;
        }
      }
      return res;
    }
  }

  /**
   * Approach #2 Better Brute Force [Accepted]

   Instead of searching for the occurence of findNums[i]findNums[i] linearly in the numsnums array,
   we can make use of a hashmap hashhash to store the elements of numsnums in the form of (element,
   index)(element,index). By doing this, we can find findNums[i]findNums[i]'s index in numsnums
   array directly and then continue to search for the next larger element in a linear fashion.
   Complexity Analysis

   Time complexity : O(m*n)O(m∗n). The whole numsnums array, of length nn needs to be scanned for
   all the mm elements of finalNumsfinalNums in the worst case.

   Space complexity : O(m)O(m). resres array of size mm is used. A hashmap hashhash of size mm is
   used, where mm refers to the length of the findNumsfindNums array.
   */

  public class Solution2 {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
      HashMap < Integer, Integer > hash = new HashMap < > ();
      int[] res = new int[findNums.length];
      int j;
      for (int i = 0; i < nums.length; i++) {
        hash.put(nums[i], i);
      }
      for (int i = 0; i < findNums.length; i++) {
        for (j = hash.get(findNums[i]) + 1; j < nums.length; j++) {

          if (findNums[i] < nums[j]) {
            res[i] = nums[j];
            break;
          }
        }
        if (j == nums.length) {
          res[i] = -1;
        }
      }
      return res;
    }
  }

  /**
   * Approach #3 Using Stack [Accepted]

   In this approach, we make use of pre-processing first so as to make the results easily available later on. We make use of a stack(stackstack) and a hashmap(mapmap). mapmap is used to store the result for every posssible number in numsnums in the form of (element, next\_greater\_element)(element,next_greater_element). Now, we look at how to make entries in mapmap.

   We iterate over the numsnums array from the left to right. We push every element nums[i]nums[i] on the stack if it is less than the previous element on the top of the stack (stack[top]stack[top]). No entry is made in mapmap for such nums[i]'snums[i]
   ​′
   ​​ s right now. This happens because the nums[i]'snums[i]
   ​′
   ​​ s encountered so far are coming in a descending order.

   If we encounter an element nums[i]nums[i] such that nums[i] > stack[top]nums[i]>stack[top], we keep on popping all the elements from stack[top]stack[top] until we encounter stack[k]stack[k] such that stack[k]≤nums[i]stack[k]≤nums[i]. For every element popped out of the stack stack[j]stack[j], we put the popped element along with its next greater number(result) into the hashmap mapmap, in the form (stack[j], nums[i])(stack[j],nums[i]) . Now, it is obvious that the next greater element for all elements stack[j]stack[j], such that k<j≤topk<j≤top is nums[i]nums[i](since this larger element caused all the stack[j]stack[j]'s to be popped out). We stop popping the elements at stack[k]stack[k] because this nums[i]nums[i] can't act as the next greater element for the next elements on the stack.

   Thus, an element is popped out of the stack whenever a next greater element is found for it. Thus, the elements remaining in the stack are the ones for which no next greater element exists in the numsnums array. Thus, at the end of the iteration over numsnums, we pop the remaining elements from the stackstack and put their entries in hashhash with a \text{-1}-1 as their corresponding results.

   Then, we can simply iterate over the findNumsfindNums array to find the corresponding results from mapmap directlhy.

   The following animation makes the method clear:
   Complexity Analysis

   Time complexity : O(m+n)O(m+n). The entire numsnums array(of size nn) is scanned only once. The stack's nn elements are popped only once. The findNumsfindNums array is also scanned only once.

   Space complexity : O(m+n)O(m+n). stackstack and mapmap of size nn is used. resres array of size mm is used, where nn refers to the length of the numsnums array and mm refers to the length of the findNumsfindNums array.

   */

  public class Solution3 {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
      Stack < Integer > stack = new Stack < > ();
      HashMap < Integer, Integer > map = new HashMap < > ();
      int[] res = new int[findNums.length];
      for (int i = 0; i < nums.length; i++) {
        while (!stack.empty() && nums[i] > stack.peek())
          map.put(stack.pop(), nums[i]);
        stack.push(nums[i]);
      }
      while (!stack.empty())
        map.put(stack.pop(), -1);
      for (int i = 0; i < findNums.length; i++) {
        res[i] = map.get(findNums[i]);
      }
      return res;
    }
  }

  class Solution_hashmap {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < nums2.length; i++)
      {
        map.put(nums2[i], i);
      }

      int[] ans = new int[nums1.length];
      int idx = 0;
      for (int i : nums1)
      {
        ans[idx++] = getNext(i, map.get(i) + 1, nums2);
      }

      return ans;
    }

    public int getNext(int n, int idx, int[] nums2)
    {
      while (idx < nums2.length)
      {
        if (nums2[idx] > n)
        {
          return nums2[idx];
        }
        idx++;
      }

      return -1;
    }
  }





}
