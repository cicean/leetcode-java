/**
 * 1306. Jump Game III
 * Medium
 *
 * 132
 *
 * 3
 *
 * Add to List
 *
 * Share
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 * Accepted
 * 9,044
 * Submissions
 * 14,816
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * LeetCode
 * Jump Game II
 * Hard
 * Jump Game
 * Medium
 * Think of BFS to solve the problem.
 * When you reach a position with a value = 0 then return true.
 */

public class JumpGameIII {

    /**
     * We can also use the input array to track visited cells. Note that I just add arr.length to the value,
     * in case we want to restore the original values later.
     * Time: O(n); we mark elements as visited and do not process them again.
     * Memory: O(n) for the recursion.
     * @param arr
     * @param st
     * @return
     */

    public boolean canReach(int[] arr, int st) {
        if (st >= 0 && st < arr.length && arr[st] < arr.length) {
            int jump = arr[st];
            arr[st] += arr.length;
            return jump == 0 || canReach(arr, st + jump) || canReach(arr, st - jump);
        }
        return false;
    }
}
