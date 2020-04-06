/**
 * 1395. Count Number of Teams
 * Medium
 *
 * 59
 *
 * 25
 *
 * Add to List
 *
 * Share
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 *
 *
 *
 * Example 1:
 *
 * Input: rating = [2,5,3,4,1]
 * Output: 3
 * Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
 * Example 2:
 *
 * Input: rating = [2,1,3]
 * Output: 0
 * Explanation: We can't form any team given the conditions.
 * Example 3:
 *
 * Input: rating = [1,2,3,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * n == rating.length
 * 1 <= n <= 200
 * 1 <= rating[i] <= 10^5
 * Accepted
 * 7,248
 * Submissions
 * 8,851
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * LeetCode
 * BruteForce, check all possibilities.
 */
public class CountNumberofTeams {

    /**
     * Solution 2: Math
     * For each soldier j, count how many soldiers on his left has smaller ratings as left[j],
     * count how many soldiers his right side has larger ratings as right[j]
     *
     * ans = sum(left[j] * right[j] + (j – left[j]) * (n – j – 1 * right[j])
     *
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */

    class Solution {
        public int numTeams(int[] rating) {
            int n = rating.length;
            int ans = 0;
            for (int j = 0; j < n; j++) {
                int l = 0;
                int r = 0;
                for (int i = 0; i < j; i++) {
                    if (rating[i] < rating[j]) l++;
                }
                for (int k = j + 1; k < n; k++) {
                    if (rating[j] < rating[k]) r++;
                }
                ans += (l * r) + (j - l) * (n - j - 1 - r);
            }
            return ans;
        }
    }
}
