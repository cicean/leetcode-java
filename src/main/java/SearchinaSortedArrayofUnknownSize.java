/**
 * 702. Search in a Sorted Array of Unknown Size
 * Medium
 *
 * 275
 *
 * 20
 *
 * Add to List
 *
 * Share
 * Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 *
 * You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.
 *
 *
 *
 * Example 1:
 *
 * Input: array = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: array = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 *
 * Note:
 *
 * You may assume that all elements in the array are unique.
 * The value of each element in the array will be in the range [-9999, 9999].
 * Accepted
 * 21,408
 * Submissions
 * 33,104
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * Binary Search
 * Easy
 */
public class SearchinaSortedArrayofUnknownSize {

    /**
     * Solution
     * Approach 1: Binary Search
     * Split into Two Subproblems
     *
     * The array is sorted, i.e. one could try to fit into a logarithmic time complexity. That means two subproblems, and both should be done in a logarithmic time:
     *
     * Define search limits, i.e. left and right boundaries for the search.
     *
     * Perform binary search in the defined boundaries.
     *
     * limits
     *
     * Define Search Boundaries
     *
     * This is a key subproblem here.
     *
     * The idea is quite simple. Let's take two first indexes, 0 and 1, as left and right boundaries. If the target value is not among these zeroth and the first element, then it's outside the boundaries, on the right.
     *
     * That means that the left boundary could moved to the right, and the right boundary should be extended. To keep logarithmic time complexity, let's extend it twice as far: right = right * 2.
     *
     * limits
     *
     * If the target now is less than the right element, we're done, the boundaries are set. If not, repeat these two steps till the boundaries are established:
     *
     * Move the left boundary to the right: left = right.
     *
     * Extend the right boundary: right = right * 2.
     *
     * limits
     *
     * Binary Search
     *
     * Binary search is a textbook algorithm with a logarithmic time complexity. It's based on the idea to compare the target value to the middle element of the array.
     *
     * If the target value is equal to the middle element - we're done.
     *
     * If the target value is smaller - continue to search on the left.
     *
     * If the target value is larger - continue to search on the right.
     *
     * limits
     *
     * Prerequisites: left and right shifts
     *
     * To speed up, one could use here bitwise shifts:
     *
     * Left shift: x << 1. The same as multiplying by 2: x * 2.
     *
     * Right shift: x >> 1. The same as dividing by 2: x / 2.
     *
     * Algorithm
     *
     * Define boundaries:
     *
     * Initiate left = 0 and right = 1.
     *
     * While target is on the right to the right boundary: reader.get(right) < target:
     *
     * Set left boundary equal to the right one: left = right.
     *
     * Extend right boundary: right *= 2. To speed up, use right shift instead of multiplication: right <<= 1.
     *
     * Now the target is between left and right boundaries.
     *
     * Binary Search:
     *
     * While left <= right:
     *
     * Pick a pivot index in the middle: pivot = (left + right) / 2. To avoid overflow, use the form pivot = left + ((right - left) >> 1) instead of straightforward expression above.
     *
     * Retrieve the element at this index: num = reader.get(pivot).
     *
     * Compare middle element num to the target value.
     *
     * If the middle element is the target num == target: return pivot.
     *
     * If the target is not yet found:
     *
     * If num > target, continue to search on the left right = pivot - 1.
     *
     * Else continue to search on the right left = pivot + 1.
     *
     * We're here because target is not found. Return -1.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(\log T)O(logT), where TT is an index of target value.
     *
     * There are two operations here: to define search boundaries and to perform binary search.
     *
     * Let's first find the number of steps k to setup the boundaries. On the first step, the boundaries are 2^0 .. 2^{0 + 1}2
     * 0
     *  ..2
     * 0+1
     *  , on the second step 2^1 .. 2^{1 + 1}2
     * 1
     *  ..2
     * 1+1
     *  , etc. When everything is done, the boundaries are 2^k .. 2^{k + 1}2
     * k
     *  ..2
     * k+1
     *   and 2^k < T \le 2^{k + 1}2
     * k
     *  <T≤2
     * k+1
     *  . That means one needs k = \log Tk=logT steps to setup the boundaries, that means \mathcal{O}(\log T)O(logT) time complexity.
     *
     * Now let's discuss the complexity of the binary search. There are 2^{k + 1} - 2^k = 2^k2
     * k+1
     *  −2
     * k
     *  =2
     * k
     *   elements in the boundaries, i.e. 2^{\log T} = T2
     * logT
     *  =T elements. As discussed, binary search has logarithmic complexity, that results in \mathcal{O}(\log T)O(logT) time complexity.
     *
     * Space complexity : \mathcal{O}(1)O(1) since it's a constant space solution.
     */

    class Solution {
        public int search(ArrayReader reader, int target) {
            if (reader.get(0) == target) return 0;

            // search boundaries
            int left = 0, right = 1;
            while (reader.get(right) < target) {
                left = right;
                right <<= 1;
            }

            // binary search
            int pivot, num;
            while (left <= right) {
                pivot = left + ((right - left) >> 1);
                num = reader.get(pivot);

                if (num == target) return pivot;
                if (num > target) right = pivot - 1;
                else left = pivot + 1;
            }

            // there is no target element
            return -1;
        }
    }
}
