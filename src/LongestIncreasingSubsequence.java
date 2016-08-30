/**
 * Created by cicean on 8/29/2016.
 *
 *300. Longest Increasing Subsequence  QuestionEditorial Solution  My Submissions
 Total Accepted: 42525 Total Submissions: 118187 Difficulty: Medium
 Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?

 Credits:
 Special thanks to @pbrother for adding this problem and creating all test cases.

 Hide Company Tags Microsoft
 Hide Tags Dynamic Programming Binary Search
 Hide Similar Problems (M) Increasing Triplet Subsequence (H) Russian Doll Envelopes

 */
public class LongestIncreasingSubsequence {

    /**
     * DP O(n^2)
     */
    public class Solution {
        public int longestIncreasingSubsequence(int[] nums) {
            int n = nums.length, max = 0;
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    dp[i] = nums[i] >= nums[j] ? Math.max(dp[j]+1, dp[i]) : dp[i];
                    max = Math.max(max, dp[i]);
                }
            }
            return max;
        }
    }

    /**
     * Binary Search O(nlogn)
     */
    public class Solution2 {
        public int longestIncreasingSubsequence(int[] nums) {
            int n = nums.length, max = 0;
            if (n == 0) return 0;
            int[] tails = new int[nums.length];
            tails[0] = nums[0];
            int index = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < tails[0]) tails[0] = nums[i];
                else if (nums[i] >= tails[index]) tails[++index] = nums[i];
                else tails[bisearch(tails, 0, index, nums[i])] = nums[i];
            }
            return index+1;
        }
        public int bisearch(int[] tails, int start, int end, int target) {
            while (start <= end) {
                int mid = start + (end-start)/2;
                if (tails[mid] == target) return mid;
                else if (tails[mid] < target) start = mid+1;
                else end = mid-1;
            }
            return start;
        }
    }


}
