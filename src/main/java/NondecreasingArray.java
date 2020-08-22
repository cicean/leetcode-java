import java.util.Arrays;

/**
 * 665. Non-decreasing Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

 We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

 Example 1:
 Input: [4,2,3]
 Output: True
 Explanation: You could modify the first
 4
 to
 1
 to get a non-decreasing array.
 Example 2:
 Input: [4,2,1]
 Output: False
 Explanation: You can't get a non-decreasing array by modify at most one element.
 Note: The n belongs to [1, 10,000].

 */

public class NondecreasingArray {

  /*
  Approach #1: Brute Force [Time Limit Exceeded]

Intuition

For the given array \text{A}A, if we are changing at most one element \text{A[i]}A[i],
we should change \text{A[i]}A[i] to \text{A[i-1]}A[i-1], as it would be guaranteed that A[i-1]≤A[i]A[i-1]≤A[i],
and \text{A[i]}A[i] would be the smallest possible to try and achieve A[i]≤A[i+1]A[i]≤A[i+1].

Algorithm

For each possible change \text{A[i]}A[i], check if the sequence is monotone increasing.
We'll modify \text{new}new, a copy of the array \text{A}A.

Complexity Analysis

Time Complexity: Let N be the length of the given array. For each element \text{A[i]}A[i], we check if some sequence is monotone increasing, which takes O(N)O(N) steps. In total, this is a complexity of O(N^2)O(N
​2
​​ ).

Space Complexity: To hold our array \text{new}new, we need O(N)O(N) space.
   */

  public boolean checkPossibility(int[] nums) {

    int[] newa = new int[nums.length];

    for (int i =0; i < nums.length; i++) {
      int old_ai = nums[i];
      if (i > 0) {
        newa[i] = newa[i - 1];
      }

      if (monotone_increasing(newa)) {
        return true;
      }

      newa[i] = old_ai;

    }

    return false;

  }

  private boolean monotone_increasing(int[] a) {

    for (int i = 0; i < a.length-1; i++) {
      if (a[i] > a[i+1]) {
        return false;
      }

    }
    return true;
  }

  /*
  Approach #2: Reduce to Smaller Problem [Accepted]

Intuition

If A[0]≤A[1]≤A[2]A[0]≤A[1]≤A[2], then we may remove \text{A[0]}A[0] without changing the answer. Similarly, if A[len(A)-3]≤A[len(A)-2]≤A[len(A)-1]A[len(A)-3]≤A[len(A)-2]≤A[len(A)-1], we may remove \text{A[len(A)-1]}A[len(A)-1] without changing the answer.

If the problem is solvable, then after these removals, very few numbers will remain.

Algorithm

Consider the interval \text{[i, j]}[i, j] corresponding to the subarray \big[\text{A[i], A[i+1], ..., A[j]}\big][A[i], A[i+1], ..., A[j]]. When A[i]≤A[i+1]≤A[i+2]A[i]≤A[i+1]≤A[i+2], we know we do not need to modify \text{A[i]}A[i], and we can consider solving the problem on the interval \text{[i+1, j]}[i+1, j] instead. We use a similar approach for jj.

Afterwards, with the length of the interval under consideration being \text{j - i + 1}j - i + 1, if the interval has size 2 or less, then we did not find any problem.

If our interval under consideration has 5 or more elements, then there are two disjoint problems that cannot be fixed with one replacement.

Otherwise, our problem size is now at most 4 elements, which we can easily brute force.

Complexity Analysis

Time Complexity: Let NN be the length of the given array. Our pointers ii and jj move at most O(N)O(N) times. Our brute force is constant time as there are at most 4 elements in the array. Hence, the complexity is O(N)O(N).

Space Complexity: The extra array \text{A[i: j+1]}A[i: j+1] only has at most 4 elements, so it is constant space, and so is the space used by our auxillary brute force algorithm. In total, the space complexity is O(1)O(1).
   */

  public boolean checkPossibility_2(int[] nums) {

    int i = 0;
    int j = nums.length -1;
    while (i+2 < nums.length && nums[i] <= nums[i+1]
        && nums[i+1] <= nums[i+2]) {
      i++;
    }
    while (j-2 >=0 && nums[j-2] <= nums[j-1]
        && nums[j-1] <= nums[j]) {
      j--;
    }
    if (j-i+1 <= 2) return true;
    if (j-i+1 >= 5) return false;

    int[] a = Arrays.copyOfRange(nums, i, j+1);

    return checkPossibility(a);
  }

  /*
  Approach #3: Locate and Analyze Problem Index [Accepted]

Intuition

Consider all indices pp for which \text{A[p]} > \text{A[p+1]}A[p]>A[p+1]. If there are zero, the answer is True. If there are 2 or more, the answer is False, as more than one element of the array must be changed for \text{A}A to be monotone increasing.

At the problem index pp, we only care about the surrounding elements. Thus, immediately the problem is reduced to a very small size that can be analyzed by casework.

Algorithm

As before, let pp be the unique problem index for which \text{A[p]} > \text{A[p+1]}A[p]>A[p+1]. If this is not unique or doesn't exist, the answer is False or True respectively. We analyze the following cases:

If \text{p = 0}p = 0, then we could make the array good by setting \text{A[p] = A[p+1]}A[p] = A[p+1].
If \text{p = len(A) - 2}p = len(A) - 2, then we could make the array good by setting \text{A[p+1] = A[p]}A[p+1] = A[p].
Otherwise, \text{A[p-1], A[p], A[p+1], A[p+2]}A[p-1], A[p], A[p+1], A[p+2] all exist, and:
We could change \text{A[p]}A[p] to be between \text{A[p-1]}A[p-1] and \text{A[p+1]}A[p+1] if possible, or;
We could change \text{A[p+1]}A[p+1] to be between \text{A[p]}A[p] and \text{A[p+2]}A[p+2] if possible.

Complexity Analysis

Time Complexity: Let NN be the length of the given array. We loop through the array once, so our time complexity is O(N)O(N).

Space Complexity: We only use pp and ii, and the answer itself as the additional space. The additional space complexity is O(1)O(1).
   */


  class Solution {
    public boolean checkPossibility(int[] a) {
      int modified = 0;
      for (int i = 1; i < a.length; i++) {
        if (a[i] < a[i - 1]) {
          if (modified++ > 0) return false;
          if (i - 2 < 0 || a[i - 2] <= a[i]) a[i - 1] = a[i]; // lower a[i - 1]
          else a[i] = a[i - 1]; // rise a[i]
        }
      }
      return true;
    }
  }


}
