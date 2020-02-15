/**
 * 1060. Missing Element in Sorted Array
 * Medium
 *
 * 233
 *
 * 5
 *
 * Add to List
 *
 * Share
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [4,7,9,10], K = 1
 * Output: 5
 * Explanation:
 * The first missing number is 5.
 * Example 2:
 *
 * Input: A = [4,7,9,10], K = 3
 * Output: 8
 * Explanation:
 * The missing numbers are [5,6,8,...], hence the third missing number is 8.
 * Example 3:
 *
 * Input: A = [1,2,4], K = 3
 * Output: 6
 * Explanation:
 * The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * 1 <= A[i] <= 1e7
 * 1 <= K <= 1e8
 * Accepted
 * 10,064
 * Submissions
 * 18,376
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ctrlcctrlv555
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 7
 *
 * Amazon
 * |
 * 4
 *
 * Bloomberg
 * |
 * 4
 *
 * Apple
 * |
 * 3
 *
 * Google
 * |
 * 2
 * First define a function f(x) that counts the number of missing elements until x.
 * Then use binary search with the given function f(x) to find the kth missing element.
 */
public class MissingElementinSortedArray {

    /**
     * Approach 1: One Pass
     * Intuition
     *
     * The problem is similar to First Missing Positive and the naive idea would be to solve it in a similar way by one pass approach.
     *
     * Let's first assume that one has a function missing(idx) that returns how many numbers are missing until the element at index idx.
     * Algorithm
     *
     * Implement missing(idx) function that returns how many numbers are missing until array element with index idx. Function returns nums[idx] - nums[0] - idx.
     *
     * Find an index such that missing(idx - 1) < k <= missing(idx) by a linear search.
     *
     * Return kth smallest nums[idx - 1] + k - missing(idx - 1).
     *
     * Implementation
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(1)O(1) if the missing number is larger than the last element of the array. \mathcal{O}(N)O(N) otherwise, since it's not more than one pass along the array.
     * Space complexity : \mathcal{O}(1)O(1) since it's a constant space solution.
     */

    class Solution_One_Pass {
        // Return how many numbers are missing until nums[idx]
        int missing(int idx, int[] nums) {
            return nums[idx] - nums[0] - idx;
        }

        public int missingElement(int[] nums, int k) {
            int n = nums.length;
            // If kth missing number is larger than
            // the last element of the array
            if (k > missing(n - 1, nums))
                return nums[n - 1] + k - missing(n - 1, nums);

            int idx = 1;
            // find idx such that
            // missing(idx - 1) < k <= missing(idx)
            while (missing(idx, nums) < k) idx++;

            // kth missing number is larger than nums[idx - 1]
            // and smaller than nums[idx]
            return nums[idx - 1] + k - missing(idx - 1, nums);
        }
    }

    /**
     *Complexity Analysis
     *
     * Time complexity : \mathcal{O}(1)O(1) if the missing number is larger than the last element of the array. \mathcal{O}(\log N)O(logN) otherwise, since it's a binary search algorithm.
     * Space complexity : \mathcal{O}(1)O(1) since it's a constant space solution.
     */

    class Solution_Binary_Search {
        // Return how many numbers are missing until nums[idx]
        int missing(int idx, int[] nums) {
            return nums[idx] - nums[0] - idx;
        }

        public int missingElement(int[] nums, int k) {
            int n = nums.length;
            // If kth missing number is larger than
            // the last element of the array
            if (k > missing(n - 1, nums))
                return nums[n - 1] + k - missing(n - 1, nums);

            int left = 0, right = n - 1, pivot;
            // find left = right index such that
            // missing(left - 1) < k <= missing(left)
            while (left != right) {
                pivot = left + (right - left) / 2;

                if (missing(pivot, nums) < k) left = pivot + 1;
                else right = pivot;
            }

            // kth missing number is larger than nums[idx - 1]
            // and smaller than nums[idx]
            return nums[left - 1] + k - missing(left - 1, nums);
        }
    }

}
