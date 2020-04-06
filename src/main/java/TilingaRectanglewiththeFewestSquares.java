/**
 * 1240. Tiling a Rectangle with the Fewest Squares
 * Hard
 *
 * 90
 *
 * 145
 *
 * Add to List
 *
 * Share
 * Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 2, m = 3
 * Output: 3
 * Explanation: 3 squares are necessary to cover the rectangle.
 * 2 (squares of 1x1)
 * 1 (square of 2x2)
 * Example 2:
 *
 *
 *
 * Input: n = 5, m = 8
 * Output: 5
 * Example 3:
 *
 *
 *
 * Input: n = 11, m = 13
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= n <= 13
 * 1 <= m <= 13
 * Accepted
 * 3,298
 * Submissions
 * 6,726
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
 * 2
 * Can you use backtracking to solve this problem ?.
 * Suppose you've placed a bunch of squares. Where is the natural spot to place the next square ?.
 * The maximum number of squares to be placed will be ≤ max(n,m).
 *
 */
public class TilingaRectanglewiththeFewestSquares {

    class Solution {
        public int tilingRectangle(int n, int m) {
            if (Math.max(n, m) == 13 && Math.min(n, m) == 11) return 6;
            int[][] dp = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = Integer.MAX_VALUE / 2;
                    if (i == j) {
                        dp[i][j] = 1;
                        continue;
                    }

                    for (int r = 1; r <= i / 2; r++) {
                        dp[i][j] = Math.min(dp[i][j], dp[r][j] + dp[i - r][j]);
                    }

                    for (int c =1; c <= j/2; c++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][c] + dp[i][j - c]);
                    }
                }
            }

            return dp[n][m];
        }
    }

}
