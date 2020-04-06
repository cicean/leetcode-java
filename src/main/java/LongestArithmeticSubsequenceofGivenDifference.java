/**
 * 1218. Longest Arithmetic Subsequence of Given Difference
 * Medium
 *
 * 205
 *
 * 19
 *
 * Add to List
 *
 * Share
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 * Example 2:
 *
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 * Example 3:
 *
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 * Accepted
 * 11,530
 * Submissions
 * 27,254
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * zubaidullo
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * LeetCode
 * Use dynamic programming.
 * Let dp[i] be the maximum length of a subsequence of the given difference whose last element is i.
 * dp[i] = 1 + dp[i-k]
 */
public class LongestArithmeticSubsequenceofGivenDifference {

    class Solution {
        public int longestSubsequence(int[] arr, int difference) {
            int len = arr.length;
            int ans = 0;
            // Map<Integer, Integer> count = new HashMap<>();
            int[] count = new int[40000];
            for(int i = 0; i < len; i++) {
                arr[i] += 20000;
            }
            count[arr[len-1]] = 1;
            for(int i = len-2; i >= 0; i--) {
                int tempAns = count[arr[i] + difference] + 1;
                count[arr[i]] = tempAns;
                ans = Math.max(ans, tempAns);
            }
            return ans;
        }
    }

    class Solution {
        public int longestSubsequence(int[] arr, int difference) {
            if(arr == null || arr.length == 0)
                return 0;

            if(arr.length == 1)
                return 1;

            int N = arr.length;
            Map<Integer, Integer> chainSize = new HashMap<Integer, Integer>(N);
            int max = 1, size, i;

            for (i = 0; i < N; i++) {
                size = chainSize.getOrDefault(arr[i] - difference, 0) + 1;
                chainSize.put(arr[i], size);
                max = Math.max(size, max);
            }

            return max;
        }
    }

}
