import java.util.*;

/**
 * 1219. Path with Maximum Gold
 * Medium
 *
 * 252
 *
 * 11
 *
 * Add to List
 *
 * Share
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position you can walk one step to the left, right, up or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * Example 2:
 *
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 *
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 * Accepted
 * 15,903
 * Submissions
 * 25,186
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
 * 6
 *
 * Amazon
 * |
 * 3
 *
 * Facebook
 * |
 * 2
 * Use recursion to try all such paths and find the one with the maximum value.
 */
public class PathwithMaximumGold {

    /**
     * Complexity
     * - Time: O(4 * 3^k), k is the number of cells that have gold. k maximum is 25
     * Because the first cell has up to 4 choices, the (k-1) remain cells have up to 3 choices. And we make k different gold cells as first cell. So k * 4*3^(k-1) = 4 * 3^k
     * Test Number of Operations
     * I tested on worst case 5x5 matrix full 25 golds like below:
     */
    class Solution_DFS {
        public int getMaximumGold(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int maxGold = 0;
            for (int r = 0; r < m; r++)
                for (int c = 0; c < n; c++)
                    maxGold = Math.max(maxGold, findMaxGold(grid, r, c));

            return maxGold;
        }

        private final int[] directions = new int[]{0, 1, 0, -1, 0};
        private int findMaxGold(int[][] grid, int r, int c) {
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return 0;

            int origin = grid[r][c];
            grid[r][c] = 0; // mark as visited

            int maxGold = 0;
            for (int i = 0; i < 4; i++)
                maxGold = Math.max(maxGold, findMaxGold(grid, directions[i] + r, directions[i+1] + c));

            grid[r][c] = origin; // backtrack
            return maxGold + origin;
        }
    }

    /**
     * Analysis:
     *
     * Time & space: O(k * 4 ^ k + m * n), where k = number of gold cells, m = grid.length, n = grid[0].length.
     */

    class Solution_BFS {
        private  final int[] d = {0, 1, 0, -1, 0};
        public int getMaximumGold(int[][] grid) {
            int ans = 0, m = grid.length, n = grid[0].length;
            int[][] oneCellTrace = new int[m][n];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0, goldCellId = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] > 0) {
                        oneCellTrace[i][j] = 1 << goldCellId++;
                        q.offer(new int[]{i, j, grid[i][j], oneCellTrace[i][j]});
                    }
                }
            }
            while (!q.isEmpty()) {
                int i = q.peek()[0], j = q.peek()[1], sum = q.peek()[2], trace = q.poll()[3];
                ans = Math.max(sum, ans);
                for (int k = 0; k < 4; ++k) {
                    int r = i + d[k], c = j + d[k + 1];
                    if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] > 0 && (trace & oneCellTrace[r][c]) == 0) {
                        q.offer(new int[]{r, c, sum + grid[r][c], trace | oneCellTrace[r][c]});
                    }
                }
            }
            return ans;
        }
    }


}
