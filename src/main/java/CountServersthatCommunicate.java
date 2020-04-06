/**
 * 1267. Count Servers that Communicate
 * Medium
 *
 * 151
 *
 * 15
 *
 * Add to List
 *
 * Share
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
 *
 * Return the number of servers that communicate with any other server.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 0
 * Explanation: No servers can communicate with others.
 * Example 2:
 *
 *
 *
 * Input: grid = [[1,0],[1,1]]
 * Output: 3
 * Explanation: All three servers can communicate with at least one other server.
 * Example 3:
 *
 *
 *
 * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * Output: 4
 * Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 * Accepted
 * 11,036
 * Submissions
 * 18,888
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
 * Store number of computer in each row and column.
 * Count all servers that are not isolated.
 */
public class CountServersthatCommunicate {

    class Solution {
        public int countServers(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            int numRows = grid.length;
            int numCols = grid[0].length;
            int rowCount[] = new int[numRows];
            int colCount[] = new int[numCols];
            int totalServers = 0;
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    if (grid[row][col] == 1) {
                        rowCount[row]++;
                        colCount[col]++;
                        totalServers++;
                    }
                }
            }
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    if (grid[row][col] == 1) {
                        if (rowCount[row] == 1 && colCount[col] == 1) {
                            totalServers--;
                        }
                    }
                }
            }
            return totalServers;
        }
    }
}
