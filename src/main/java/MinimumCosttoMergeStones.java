/**
 * 1000. Minimum Cost to Merge Stones
 * Hard
 *
 * 500
 *
 * 37
 *
 * Add to List
 *
 * Share
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 *
 * A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.
 *
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 * Example 2:
 *
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
 * Example 3:
 *
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 * Accepted
 * 12,781
 * Submissions
 * 32,515
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * lee215
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 13
 *
 * Google
 * |
 * 2
 * Burst Balloons
 * Hard
 * Minimum Cost to Connect Sticks
 * Medium
 *
 */
public class MinimumCosttoMergeStones {
    class Solution {
        public int mergeStones(int[] stones, int K) {
            int len = stones.length;

            // finding if the input can be merged
            if((len - 1) % (K-1) > 0) {
                return -1;
            }

            int[] sumArr = new int[len + 1];

            // finding the summation of the stones
            for(int i=1; i<=len; i++) {
                sumArr[i] = sumArr[i-1] + stones[i-1];
            }

            // taking a bottom up DP approach where each entry represents the min cost for each sub array
            int[][] dp = new int[len][len];

            for(int span = K; span<=len; span++) {
                for(int left = 0; left + span <= len; left ++) {
                    int right = left + span - 1; // since width is 1 based

                    dp[left][right] = Integer.MAX_VALUE;

                    for(int split = left; split < right; split += (K-1)) { // since K is 1 based and we can merge only K piles.
                        dp[left][right] = Math.min(dp[left][right], dp[left][split] + dp[split + 1][right]);
                    }

                    // take e.g. Stones = [1,2,3,4,5] and K = 3
                    // when span = 5th index, left = 0th index, right = 4th index and split = 2nd index
                    // dp for subarray [1,2,3] will return 6 and for subarray [4,5] it will be 0
                    // this will make dp[left][right] as 6 + 0 = 6;
                    // so visually the given array will be represented from [1,2,3,4,5] to [([1,2,3] merged to 6), 4, 5]
                    // we should merge [6,4,5] as well. This computation is being achieved in the logic below.
                    if((left - right) % (K-1) == 0) {
                        dp[left][right] += (sumArr[right + 1] - sumArr[left]);
                    }
                }
            }

            return dp[0][len - 1];
        }
    }
}
