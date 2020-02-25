/**
 * 689. Maximum Sum of 3 Non-Overlapping Subarrays
 * Hard
 *
 * 754
 *
 * 40
 *
 * Add to List
 *
 * Share
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 *
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 *
 * Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
 *
 * Example:
 *
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 *
 *
 * Note:
 *
 * nums.length will be between 1 and 20000.
 * nums[i] will be between 1 and 65535.
 * k will be between 1 and floor(nums.length / 3).
 *
 *
 * Accepted
 * 33,379
 * Submissions
 * 74,169
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * Best Time to Buy and Sell Stock III
 */
public class MaximumSumof3NonOverlappingSubarrays {

    /**
     * Approach #1: Ad-Hoc [Accepted]
     * Intuition
     *
     * It is natural to consider an array W of each interval's sum, where each interval is the given length K. To create W, we can either use prefix sums, or manage the sum of the interval as a window slides along the array.
     *
     * From there, we approach the reduced problem: Given some array W and an integer K, what is the lexicographically smallest tuple of indices (i, j, k) with i + K <= j and j + K <= k that maximizes W[i] + W[j] + W[k]?
     *
     * Algorithm
     *
     * Suppose we fixed j. We would like to know on the intervals i \in [0, j-K]i∈[0,j−K] and k \in [j+K, \text{len}(W)-1]k∈[j+K,len(W)−1], where the largest value of W[i]W[i] (and respectively W[k]W[k]) occurs first. (Here, first means the smaller index.)
     *
     * We can solve these problems with dynamic programming. For example, if we know that ii is where the largest value of W[i]W[i] occurs first on [0, 5][0,5], then on [0, 6][0,6] the first occurrence of the largest W[i]W[i] must be either ii or 66. If say, 66 is better, then we set best = 6.
     *
     * At the end, left[z] will be the first occurrence of the largest value of W[i] on the interval i \in [0, z]i∈[0,z], and right[z] will be the same but on the interval i \in [z, \text{len}(W) - 1]i∈[z,len(W)−1]. This means that for some choice j, the candidate answer must be (left[j-K], j, right[j+K]). We take the candidate that produces the maximum W[i] + W[j] + W[k].
     *
     */

    class Solution {
        public int[] maxSumOfThreeSubarrays(int[] nums, int K) {
            //W is an array of sums of windows
            int[] W = new int[nums.length - K + 1];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (i >= K) sum -= nums[i-K];
                if (i >= K-1) W[i-K+1] = sum;
            }

            int[] left = new int[W.length];
            int best = 0;
            for (int i = 0; i < W.length; i++) {
                if (W[i] > W[best]) best = i;
                left[i] = best;
            }

            int[] right = new int[W.length];
            best = W.length - 1;
            for (int i = W.length - 1; i >= 0; i--) {
                if (W[i] >= W[best]) best = i;
                right[i] = best;
            }

            int[] ans = new int[]{-1, -1, -1};
            for (int j = K; j < W.length - K; j++) {
                int i = left[j - K], k = right[j + K];
                if (ans[0] == -1 || W[i] + W[j] + W[k] >
                        W[ans[0]] + W[ans[1]] + W[ans[2]]) {

                    ans[0] = i;
                    ans[1] = j;
                    ans[2] = k;
                }
            }
            return ans;
        }

        class Solution_dp {
            public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
                int n = nums.length;
                int m = n - k + 1;
                int[] sum = new int[m];
                for (int i = 0; i < k; i++) sum[0] += nums[i];
                for (int i = 1; i < m; i++) sum[i] = sum[i-1] - nums[i-1] + nums[i+k-1];

                int[]res = new int[]{0,k,2*k-1};
                int[]temp = new int[]{0,k,2*k-1};
                int max = Integer.MIN_VALUE;
                for (int middle = k; middle < m-k; middle++){
                    //mid
                    temp[1] = middle;
                    //left
                    if (sum[middle-k] > sum[temp[0]]) temp[0] = middle-k;
                    //right
                    if (temp[2]<=middle+k-1){
                        int tempMax = Integer.MIN_VALUE;
                        for (int right = middle+k; right < m; right++){
                            if (sum[right] > tempMax){
                                tempMax = sum[right];
                                temp[2] = right;
                            }
                        }
                    }
                    //check
                    int check = sum[temp[0]] + sum[temp[1]] + sum[temp[2]];
                    if (check > max){
                        max = check;
                        res[0] = temp[0];
                        res[1] = temp[1];
                        res[2] = temp[2];
                    }
                }
                return res;

            }
        }
    }

}
