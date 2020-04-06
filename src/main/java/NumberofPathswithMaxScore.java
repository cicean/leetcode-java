import java.util.List;

/**
 * 1301. Number of Paths with Max Score
 * Hard
 *
 * 82
 *
 * 5
 *
 * Add to List
 *
 * Share
 * You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
 *
 * You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
 *
 * Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
 *
 * In case there is no path, return [0, 0].
 *
 *
 *
 * Example 1:
 *
 * Input: board = ["E23","2X2","12S"]
 * Output: [7,1]
 * Example 2:
 *
 * Input: board = ["E12","1X1","21S"]
 * Output: [4,2]
 * Example 3:
 *
 * Input: board = ["E11","XXX","11S"]
 * Output: [0,0]
 *
 *
 * Constraints:
 *
 * 2 <= board.length == board[i].length <= 100
 * Accepted
 * 3,482
 * Submissions
 * 9,533
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Samsung
 * |
 * LeetCode
 * Use dynamic programming to find the path with the max score.
 * Use another dynamic programming array to count the number of paths with max score.
 */
public class NumberofPathswithMaxScore {

    class Solution {
        public int[] pathsWithMaxScore(List<String> board) {
            int n = board.size();
            final int mod = (int)1e9 + 7;
            char[][] A = new char[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = board.get(i).charAt(j);
                }
            }
            A[0][0] = A[n - 1][n - 1] = '0';

            int[][] dp = new int[n + 1][n + 1];
            int[][] path = new int[n + 1][n + 1];
            path[n - 1][n - 1] = 1;

            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (A[i][j] != 'X') {
                        int max = Math.max(Math.max(dp[i][j + 1], dp[i + 1][j]), dp[i + 1][j + 1]);
                        dp[i][j] = A[i][j] - '0' + max;
                        if (dp[i + 1][j] == max) {
                            path[i][j] = (path[i][j] + path[i + 1][j]) % mod;
                        }
                        if (dp[i][j + 1] == max) {
                            path[i][j] = (path[i][j] + path[i][j + 1]) % mod;
                        }
                        if (dp[i + 1][j + 1] == max) {
                            path[i][j] = (path[i][j] + path[i + 1][j + 1]) % mod;
                        }
                    }
                }
            }
            if (path[0][0] == 0) return new int[]{0, 0};
            else return new int[]{dp[0][0], path[0][0]};
        }
    }

}
