/**
 * 1444. Number of Ways of Cutting a Pizza
 * Hard
 *
 * 108
 *
 * 4
 *
 * Add to List
 *
 * Share
 * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
 *
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 *
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3
 * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
 * Example 2:
 *
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 * Example 3:
 *
 * Input: pizza = ["A..","A..","..."], k = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 50
 * rows == pizza.length
 * cols == pizza[i].length
 * 1 <= k <= 10
 * pizza consists of characters 'A' and '.' only.
 * Accepted
 * 3,151
 * Submissions
 * 6,151
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
 * LeetCode
 * Note that after each cut the remaining piece of pizza always has the lower right coordinate at (rows-1,cols-1).
 * Use dynamic programming approach with states (row1, col1, c) which computes the number of ways of cutting the pizza
 * using "c" cuts where the current piece of pizza has upper left coordinate at (row1,col1) and lower right coordinate
 * at (rows-1,cols-1).
 * For the transitions try all vertical and horizontal cuts such that the piece of pizza you have to give a person
 * must contain at least one apple. The base case is when c=k-1.
 * Additionally use a 2D dynamic programming to respond in O(1) if a piece of pizza contains at least one apple.
 */
public class NumberofWaysofCuttingaPizza {
    class Solution {
        public int ways(String[] pizza, int k) {
            int m = pizza.length, n = pizza[0].length();
            Integer[][][] dp = new Integer[k][m][n];
            int[][] preSum = new int[m+1][n+1]; // preSum[r][c] is the total number of apples in pizza[r:][c:]
            for (int r = m - 1; r >= 0; r--)
                for (int c = n - 1; c >= 0; c--)
                    preSum[r][c] = preSum[r][c+1] + preSum[r+1][c] - preSum[r+1][c+1] + (pizza[r].charAt(c) == 'A' ? 1 : 0);
            return dfs(m, n, k-1, 0, 0, dp, preSum);
        }
        int dfs(int m, int n, int k, int r, int c, Integer[][][] dp, int[][] preSum) {
            if (preSum[r][c] == 0) return 0; // if the remain piece has no apple -> invalid
            if (k == 0) return 1; // found valid way after using k-1 cuts
            if (dp[k][r][c] != null) return dp[k][r][c];
            int ans = 0;
            // cut in horizontal
            for (int nr = r + 1; nr < m; nr++)
                if (preSum[r][c] - preSum[nr][c] > 0) // cut if the upper piece contains at least one apple
                    ans = (ans + dfs(m, n, k - 1, nr, c, dp, preSum)) % 1_000_000_007;
            // cut in vertical
            for (int nc = c + 1; nc < n; nc++)
                if (preSum[r][c] - preSum[r][nc] > 0) // cut if the left piece contains at least one apple
                    ans = (ans + dfs(m, n, k - 1, r, nc, dp, preSum)) % 1_000_000_007;
            return dp[k][r][c] = ans;
        }
    }
}
