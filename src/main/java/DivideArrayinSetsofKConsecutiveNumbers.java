import java.util.Arrays;

/**
 * 1296. Divide Array in Sets of K Consecutive Numbers
 * Medium
 *
 * 146
 *
 * 13
 *
 * Add to List
 *
 * Share
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 *
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 *
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * Example 4:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 * Accepted
 * 9.4K
 * Submissions
 * 19.5K
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * LeetCode
 * Split Array into Consecutive Subsequences
 * Medium
 * If the smallest number in the possible-to-split array is V, then numbers V+1, V+2, ... V+k-1 must contain there as well.
 * You can iteratively find k sets and remove them from array until it becomes empty.
 * Failure to do so would mean that array is unsplittable.
 */
public class DivideArrayinSetsofKConsecutiveNumbers {

    class Solution {
        public boolean isPossibleDivide(int[] nums, int k) {
            int n = nums.length;

            if (n % k != 0) return false;

            int[] cnt = new int[k];
            int size = n / k;

            for (int num : nums) {
                cnt[num % k]++;
            }

            for (int c : cnt) {
                if (c != size) return false;
            }

            return true;
        }
    }

}
