/**
 * 1191. K-Concatenation Maximum Sum
 * Medium
 *
 * 207
 *
 * 23
 *
 * Add to List
 *
 * Share
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 *
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 *
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 *
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2], k = 3
 * Output: 9
 * Example 2:
 *
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 * Example 3:
 *
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 * Accepted
 * 8,693
 * Submissions
 * 33,784
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * How to solve the problem for k=1 ?
 * Use Kadane's algorithm for k=1.
 * What are the possible cases for the answer ?
 * The answer is the maximum between, the answer for k=1, the sum of the whole array multiplied by k,
 * or the maximum suffix sum plus the maximum prefix sum plus (k-2) multiplied by the whole array sum for k > 1.
 *
 */
public class KConcatenationMaximumSum {

    class Solution {

        public int kConcatenationMaxSum(int[] arr, int k) {
            int n = arr.length, mod = (int)1e9 + 7;
            int sum = 0, maxSum = 0;
            for (int i = 0; i < n; i++) {
                sum = sum < 0 ? arr[i] : sum + arr[i];
                maxSum = Math.max(maxSum, sum);
            }
            if (k == 1) {
                return maxSum;
            }
            int leftSum = arr[0], leftMaxSum = Math.max(0, arr[0]);
            for (int i = 1; i < n; i++) {
                leftSum += arr[i];
                leftMaxSum = Math.max(leftMaxSum, leftSum);
            }
            int rightSum = arr[n - 1], rightMaxSum = Math.max(0, arr[n - 1]);
            for (int i = n - 2; i >= 0; i--) {
                rightSum += arr[i];
                rightMaxSum = Math.max(rightMaxSum, rightSum);
            }
            int maxSumMiddle = rightMaxSum + leftMaxSum;
            if (leftSum < 0) {
                return Math.max(maxSum, maxSumMiddle);
            } else {
                return Math.max(maxSum, maxSumMiddle + (int)(((k - 2) * (long)(leftSum)) % mod));
            }
        }
    }
}
