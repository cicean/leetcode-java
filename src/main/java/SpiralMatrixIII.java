/**
 * 885. Spiral Matrix III
 * Medium
 *
 * 160
 *
 * 228
 *
 * Add to List
 *
 * Share
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
 *
 * Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
 *
 * Now, we walk in a clockwise spiral shape to visit every position in this grid.
 *
 * Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
 *
 * Eventually, we reach all R * C spaces of the grid.
 *
 * Return a list of coordinates representing the positions of the grid in the order they were visited.
 *
 *
 *
 * Example 1:
 *
 * Input: R = 1, C = 4, r0 = 0, c0 = 0
 * Output: [[0,0],[0,1],[0,2],[0,3]]
 *
 *
 *
 *
 * Example 2:
 *
 * Input: R = 5, C = 6, r0 = 1, c0 = 4
 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 *
 *
 *
 *
 * Note:
 *
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 * Accepted
 * 15,756
 * Submissions
 * 23,322
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * dmgcodevil
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 2
 * Spiral Matrix
 * Medium
 * Spiral Matrix II
 * Medium
 */

public class SpiralMatrixIII {

    /**
     * Solution
     * Approach 1: Walk in a Spiral
     * Intuition
     *
     * We can walk in a spiral shape from the starting square, ignoring whether we stay in the grid or not. Eventually, we must have reached every square in the grid.
     *
     * Algorithm
     *
     * Examining the lengths of our walk in each direction, we find the following pattern: 1, 1, 2, 2, 3, 3, 4, 4, ... That is, we walk 1 unit east, then 1 unit south, then 2 units west, then 2 units north, then 3 units east, etc. Because our walk is self-similar, this pattern repeats in the way we expect.
     *
     * After, the algorithm is straightforward: perform the walk and record positions of the grid in the order we visit them. Please read the inline comments for more details.
     * Complexity Analysis
     *
     * Time Complexity: O((\max(R, C))^2)O((max(R,C))
     * 2
     *  ). Potentially, our walk needs to spiral until we move RR in one direction, and CC in another direction, so as to reach every cell of the grid.
     *
     * Space Complexity: O(R * C)O(R∗C), the space used by the answer.
     */

    class Solution {
        public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
            int[] dr = new int[]{0, 1, 0, -1};
            int[] dc = new int[]{1, 0, -1, 0};

            int[][] ans = new int[R*C][2];
            int t = 0;

            ans[t++] = new int[]{r0, c0};
            if (R * C == 1) return ans;

            for (int k = 1; k < 2*(R+C); k += 2)
                for (int i = 0; i < 4; ++i) {  // i: direction index
                    int dk = k + (i / 2);  // number of steps in this direction
                    for (int j = 0; j < dk; ++j) {  // for each step in this direction...
                        // step in the i-th direction
                        r0 += dr[i];
                        c0 += dc[i];
                        if (0 <= r0 && r0 < R && 0 <= c0 && c0 < C) {
                            ans[t++] = new int[]{r0, c0};
                            if (t == R * C) return ans;
                        }
                    }
                }

            throw null;
        }
    }

    class Solution_2 {
        public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
            int[][] result = new int[R * C][];
            result[0] = new int[] { r0, c0 };
            for (int i = 1, k = 0, r = r0, c = c0, dr = 0, dc = 1; i < R * C; ++k) {
                for (int j = 0; j < k / 2 + 1; ++j) {
                    r += dr;
                    c += dc;
                    if (r >= 0 && r < R && c >= 0 && c < C) {
                        result[i++] = new int[] { r, c };
                    }
                }
                int temp = dr;
                dr = dc;
                dc = -temp;
            }
            return result;
        }
    }
}
