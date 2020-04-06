/**
 * 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
 * Medium
 *
 * 169
 *
 * 8
 *
 * Add to List
 *
 * Share
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 * Example 2:
 *
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * Output: 0
 * Example 3:
 *
 * Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * Output: 3
 * Example 4:
 *
 * Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 * Accepted
 * 6,653
 * Submissions
 * 14,739
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
 * 4
 * Store prefix sum of all grids in another 2D array.
 * Try all possible solutions and if you cannot find one return -1.
 * If x is a valid answer then any y < x is also valid answer. Use binary search to find answer.
 */
public class MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold {

    class Solution {
        public int maxSideLength(int[][] mat, int threshold) {
            int m = mat.length;
            int n = mat[0].length;
            int[][] sum = new int[m + 1][n + 1];

            int res = 0;
            int len = 1; // square side length

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];

                    if (i >= len && j >= len && sum[i][j] - sum[i-len][j] - sum[i][j-len] + sum[i-len][j-len] <= threshold)
                        res = len++;
                }
            }

            return res;
        }
    }

    class Solution_presum {
        public int maxSideLength(int[][] mat, int threshold) {
            int h = mat.length, l = mat[0].length;
            int[][] presum = new int[h + 1][l + 1];
            for (int i = 0; i < h; i++) {
                int sum = 0;
                for (int j = 0; j < l; j++) {
                    sum += mat[i][j];
                    presum[i + 1][j + 1] = presum[i][j + 1] + sum;
                }
            }
            int res = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < l; j++) {
                    while (i - res >= 0 && j - res >= 0 &&
                            presum[i+1][j+1] - presum[i-res][j+1] - presum[i+1][j-res] + presum[i-res][j-res] <= threshold) {
                        res++;
                    }
                }
            }
            return res;
        }
    }
}
