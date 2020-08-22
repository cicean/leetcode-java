/**
 * 1425. Constrained Subsequence Sum
 * Hard
 *
 * 163
 *
 * 9
 *
 * Add to List
 *
 * Share
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 *
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 * Example 2:
 *
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 * Example 3:
 *
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * Accepted
 * 4,426
 * Submissions
 * 10,718
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Akuna Capital
 * |
 * LeetCode
 * Use dynamic programming.
 * Let dp[i] be the solution for the prefix of the array that ends at index i, if the element at index i is in the subsequence.
 * dp[i] = nums[i] + max(0, dp[i-k], dp[i-k+1], ..., dp[i-1])
 * Use a heap with the sliding window technique to optimize the dp.
 */
public class ConstrainedSubsequenceSum {

    //queue
    class Solution {
        public int constrainedSubsetSum(int[] A, int k) {
            int res = A[0];
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < A.length; ++i) {
                A[i] += !q.isEmpty() ? q.peek() : 0;
                res = Math.max(res, A[i]);
                while (!q.isEmpty() && A[i] > q.peekLast())
                    q.pollLast();
                if (A[i] > 0)
                    q.offer(A[i]);
                if (i >= k && !q.isEmpty() && q.peek() == A[i - k])
                    q.poll();
            }
            return res;
        }
    }

    //DP
    class Solution {
        public int constrainedSubsequenceSum(int[] nums, int k) {
            int N = nums.length;
            if(N==0) return 0;
            int[] dp = new int[N];
            dp[N-1] = nums[N-1];
            int ms = dp[N-1];
            int localMax = dp[N-1];
            for(int i=N-2;i>=0;i--){
                if(dp[i+1]>localMax){
                    localMax = dp[i+1];
                } else if(i+k+1<N && dp[i+k+1]==localMax){
                    localMax = Integer.MIN_VALUE;
                    for(int j=1;j<=k && j+i<N;j++){
                        localMax = Math.max(localMax, dp[j+i]);
                    }
                }
                dp[i] = localMax>0?nums[i]+localMax: nums[i];
                ms = Math.max(ms, dp[i]);
            }
            return ms;
        }
    }
}
