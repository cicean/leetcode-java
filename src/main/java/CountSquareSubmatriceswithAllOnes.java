/**
 * 1277. Count Square Submatrices with All Ones
 * Medium
 *
 * 299
 *
 * 10
 *
 * Add to List
 *
 * Share
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 *
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * Accepted
 * 11,828
 * Submissions
 * 17,295
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
 * Create an additive table that counts the sum of elements of submatrix with the superior corner at (0,0).
 * Loop over all subsquares in O(n^3) and check if the sum make the whole array to be ones, if it checks then add 1 to the answer.
 */
public class CountSquareSubmatriceswithAllOnes {
    /**
     * Explanation
     * dp[i][j] means the size of biggest square with A[i][j] as bottom-right corner.
     * dp[i][j] also means the number of squares with A[i][j] as bottom-right corner.
     *
     * If A[i][j] == 0, no possible square.
     * If A[i][j] == 1,
     * we compare the size of square dp[i-1][j-1], dp[i-1][j] and dp[i][j-1].
     * min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1 is the maximum size of square that we can find.
     *
     *
     * Complexity
     * Time O(MN)
     * Space O(1)
     *
     */

    public int countSquares(int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] > 0 && i > 0 && j > 0) {
                    A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                }
                res += A[i][j];
            }
        }
        return res;
    }
}
