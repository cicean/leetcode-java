/**
 * 562. Longest Line of Consecutive One in Matrix
 * Medium
 *
 * 258
 *
 * 64
 *
 * Add to List
 *
 * Share
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 *
 * Accepted
 * 22,714
 * Submissions
 * 49,861
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * fallcreek
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * One solution is to count ones in each direction separately and find the longest line. Don't you think it will take too much lines of code?
 * Is it possible to use some extra space to make the solution simple?
 * Can we use dynamic programming to make use of intermediate results?
 * Think of a 3D array which can be used to store the longest line obtained so far for each direction.
 */
public class LongestLineofConsecutiveOneinMatrix {

    /**
     * Solution
     * Approach 1: Brute Force
     * Algorithm
     *
     * The brute force approach is really simple. We directly traverse along every valid line in the given matrix: i.e. Horizontal, Vertical, Diagonal aline above and below the middle diagonal, Anti-diagonal line above and below the middle anti-diagonal. Each time during the traversal, we keep on incrementing the countcount if we encounter continuous 1's. We reset the countcount for any discontinuity encountered. While doing this, we also keep a track of the maximum countcount found so far.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n^2)O(n
     * 2
     *  ). We traverse along the entire matrix 4 times.
     * Space complexity : O(1)O(1). Constant space is used.
     */
    class Solution {
        public int longestLine(int[][] M) {
            if (M.length == 0) return 0;
            int ones = 0;
            // horizontal
            for (int i = 0; i < M.length; i++) {
                int count = 0;
                for (int j = 0; j < M[0].length; j++) {
                    if (M[i][j] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else count = 0;
                }
            }
            // vertical
            for (int i = 0; i < M[0].length; i++) {
                int count = 0;
                for (int j = 0; j < M.length; j++) {
                    if (M[j][i] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else count = 0;
                }
            }
            // upper diagonal
            for (int i = 0; i < M[0].length || i < M.length; i++) {
                int count = 0;
                for (int x = 0, y = i; x < M.length && y < M[0].length; x++, y++) {
                    if (M[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else count = 0;
                }
            }
            // lower diagonal
            for (int i = 0; i < M[0].length || i < M.length; i++) {
                int count = 0;
                for (int x = i, y = 0; x < M.length && y < M[0].length; x++, y++) {
                    if (M[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else count = 0;
                }
            }
            // upper anti-diagonal
            for (int i = 0; i < M[0].length || i < M.length; i++) {
                int count = 0;
                for (int x = 0, y = M[0].length - i - 1; x < M.length && y >= 0; x++, y--) {
                    if (M[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else count = 0;
                }
            }
            // lower anti-diagonal
            for (int i = 0; i < M[0].length || i < M.length; i++) {
                int count = 0;
                for (int x = i, y = M[0].length - 1; x < M.length && y >= 0; x++, y--) {
                    // System.out.println(x+" "+y);
                    if (M[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else count = 0;
                }
            }
            return ones;
        }
    }
     /** Approach 2: Using 3D Dynamic Programming
     * Algorithm
     *
     * Instead of traversing over the same matrix multiple times, we can keep a track of the 1' along all the lines possible while traversing the matrix once only. In order to do so, we make use of a 4mn4mn sized dpdp array. Here, dp[0]dp[0], dp[1]dp[1], dp[2]dp[2] ,dp[3]dp[3] are used to store the maximum number of continuous 1's found so far along the Horizontal, Vertical, Diagonal and Anti-diagonal lines respectively. e.g. dp[i][j][0]dp[i][j][0] is used to store the number of continuous 1's found so far(till we reach the element M[i][j]M[i][j]), along the horizontal lines only.
     *
     * Thus, we traverse the matrix MM in a row-wise fashion only but, keep updating the entries for every dpdp appropriately.
     *
     * The following image shows the filled dpdp values for this matrix:
     *
     *  0 1 1 0
     *
     *  0 1 1 0
     *
     *  0 0 1 1
     *
     * Longest_Line
     *
     * While filling up the dpdp, we can keep a track of the length of the longest consecutive line of 1's.
     *
     * Watch this animation for complete process:
     *
     * Current
     * 1 / 14
     *
     * Complexity Analysis
     *
     * Time complexity : O(mn)O(mn). We traverse the entire matrix once only.
     *
     * Space complexity : O(mn)O(mn). dpdp array of size 4mn4mn is used, where mm and nn are the number of rows ans coloumns of the matrix.
     */
     class Solution {
         public int longestLine(int[][] M) {
             if (M.length == 0) return 0;
             int ones = 0;
             int[][][] dp = new int[M.length][M[0].length][4];
             for (int i = 0; i < M.length; i++) {
                 for (int j = 0; j < M[0].length; j++) {
                     if (M[i][j] == 1) {
                         dp[i][j][0] = j > 0 ? dp[i][j - 1][0] + 1 : 1;
                         dp[i][j][1] = i > 0 ? dp[i - 1][j][1] + 1 : 1;
                         dp[i][j][2] = (i > 0 && j > 0) ? dp[i - 1][j - 1][2] + 1 : 1;
                         dp[i][j][3] = (i > 0 && j < M[0].length - 1) ? dp[i - 1][j + 1][3] + 1 : 1;
                         ones =
                                 Math.max(
                                         ones,
                                         Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
                     }
                 }
             }
             return ones;
         }
     }
     /** Approach 3: Using 2D Dynamic Programming
     * Algorithm
     *
     * In the previous approach, we can observe that the current dpdp entry is dependent only on the entries of the just previous corresponding dpdp row. Thus, instead of maintaining a 2-D dpdp matrix for each kind of line of 1's possible, we can use a 1-d array for each one of them, and update the corresponding entries in the same row during each row's traversal. Taking this into account, the previous 3-D dpdp matrix shrinks to a 2-D dpdp matrix now. The rest of the procedure remains same as the previous approach.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(mn)O(mn). The entire matrix is traversed once only.
     *
     * Space complexity : O(n)O(n). dpdp array of size 4n4n is used, where nn is the number of columns of the matrix.
     */

     class Solution {
         public int longestLine(int[][] M) {
             if (M.length == 0) return 0;
             int ones = 0;
             int[][] dp = new int[M[0].length][4];
             for (int i = 0; i < M.length; i++) {
                 int old = 0;
                 for (int j = 0; j < M[0].length; j++) {
                     if (M[i][j] == 1) {
                         dp[j][0] = j > 0 ? dp[j - 1][0] + 1 : 1;
                         dp[j][1] = i > 0 ? dp[j][1] + 1 : 1;
                         int prev = dp[j][2];
                         dp[j][2] = (i > 0 && j > 0) ? old + 1 : 1;
                         old = prev;
                         dp[j][3] = (i > 0 && j < M[0].length - 1) ? dp[j + 1][3] + 1 : 1;
                         ones =
                                 Math.max(ones, Math.max(Math.max(dp[j][0], dp[j][1]), Math.max(dp[j][2], dp[j][3])));
                     } else {
                         old = dp[j][2];
                         dp[j][0] = dp[j][1] = dp[j][2] = dp[j][3] = 0;
                     }
                 }
             }
             return ones;
         }
     }


    class Solution {
        public int longestLine(int[][] M) {
            if (M.length == 0) return 0;
            //int min = Math.max(M.length, M[0].length);
            int[] cols = new int[M[0].length],
                    rows = new int[M.length],
                    diag = new int[M.length+M[0].length],
                    anti = new int[M.length+M[0].length];
            int max = 0;
            for (int row = 0; row < M.length; row++) {
                for (int col = 0; col < M[0].length; col++) {
                    if (M[row][col] == 1) {
                        rows[row]++;
                        cols[col]++;
                        anti[row + col]++;
                        diag[row - col + M[0].length]++;
                        max = Math.max(
                                max,
                                Math.max(
                                        Math.max(rows[row], cols[col]),
                                        Math.max(anti[row + col], diag[row - col + M[0].length])
                                )
                        );
                    } else {
                        rows[row] = 0;
                        cols[col] = 0;
                        anti[row + col] = 0;
                        diag[row - col + M[0].length] = 0;
                    }
                }
            }
            return max;
        }
    }
}
