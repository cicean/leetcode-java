/**
 * 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
 * Medium
 *
 * 56
 *
 * 11
 *
 * Add to List
 *
 * Share
 * Given an array of integers arr and two integers k and threshold.
 *
 * Return the number of sub-arrays of size k and average greater than or equal to threshold.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * Output: 3
 * Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).
 * Example 2:
 *
 * Input: arr = [1,1,1,1,1], k = 1, threshold = 0
 * Output: 5
 * Example 3:
 *
 * Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * Output: 6
 * Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
 * Example 4:
 *
 * Input: arr = [7,7,7,7,7,7,7], k = 7, threshold = 7
 * Output: 1
 * Example 5:
 *
 * Input: arr = [4,4,4,4], k = 4, threshold = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^4
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10^4
 * Accepted
 * 5,352
 * Submissions
 * 8,396
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * abhik1998chakraborty
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * LinkedIn
 * |
 * LeetCode
 * Start with a window of size K and test its average against the threshold.
 * Keep moving the window by one element maintaining its size k until you cover the whole array.
 * count number of windows that satisfy that its average is greater than the threshold.
 */
public class NumberofSubarraysofSizeKandAverageGreaterthanorEqualtoThreshold {
    public int numOfSubarrays(int[] a, int k, int threshold) {
        int n = a.length, count = 0;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; ++i)
            prefixSum[i + 1] = prefixSum[i] + a[i];
        for (int i = 0; i + k <= n; ++i)
            if (prefixSum[i + k] - prefixSum[i] >= k * threshold)
                ++count;
        return count;
    }

    class Solution {
        public int numOfSubarrays(int[] arr, int k, int threshold) {
            int sum = k * threshold;
            int cur = 0;
            int count = 0;
            for (int i = 0; i < k; i++)
            {
                cur += arr[i];
            }

            for (int i = k; i < arr.length; i++)
            {
                if (cur >= sum) count++;
                cur = cur + arr[i] - arr[i-k];
            }

            if (cur >= sum) count++;
            return count;
        }
    }
}
