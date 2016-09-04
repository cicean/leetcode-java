/**
 * Created by cicean on 9/1/2016.
 * 361. Bomb Enemy  QuestionEditorial Solution  My Submissions
 Total Accepted: 3303
 Total Submissions: 9148
 Difficulty: Medium
 Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 Note that you can only put the bomb at an empty cell.

 Example:
 For the given grid

 0 E 0 0
 E 0 W E
 0 E 0 0

 return 3. (Placing a bomb at (1,1) kills 3 enemies)
 Credits:
 Special thanks to @memoryless for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Dynamic Programming

 */
public class BombEnemy {
    /**
     * ��ȷ�������ǿ�����O(m*n)��������, �Ƚ�naive��������ÿ����λ����ǰ����������, �������������������˴������ظ�����,
     * ������������Ѿ�����������Ϣ���Ǳ���Ŀ�����.
     * ����ÿһ����˵, ���ǿ����ڵ�0�л��ߵ�ǰλ��ǰһ��Ϊǽ��ʱ��ӵڵ�ǰ�п�ʼ��������ֱ��ײ��ǽ.
     * ����ÿһ����˵, �����ڵ�0�е�ʱ������ڵ�ǰ��ǰһ��Ϊǽ��ʱ��ӵ�ǰ����������, ֱ������ǽΪֹ.
     * �����Ϳ���һ�μ����һ��ֱ������ǽ֮ǰ�м�������, һ����û������ǽ֮ǰ�м�������.
     * ֱ����ĳ��ĳλ֮ǰλ��ǽ��ʱ��Ż����¼���
     */


    //Pretty straight forward O(mn2) solution. Cacheing the last search result could save a lot of time.
    public class Solution {
        public int maxKilledEnemies(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int ret = 0;
            int row = grid.length;
            int col = grid[0].length;
            int rowCache = 0;
            int[] colCache = new int[col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (j == 0 || grid[i][j - 1] == 'W') {
                        rowCache = 0;
                        for (int k = j; k < col && grid[i][k] != 'W'; k++) {
                            rowCache += grid[i][k] == 'E' ? 1 : 0;
                        }
                    }
                    if (i == 0 || grid[i - 1][j] == 'W') {
                        colCache[j] = 0;
                        for (int k = i; k < row && grid[k][j] != 'W'; k++) {
                            colCache[j] += grid[k][j] == 'E' ? 1 : 0;
                        }
                    }
                    if (grid[i][j] == '0') {
                        ret = Math.max(ret, rowCache + colCache[j]);
                    }
                }
            }
            return ret;
        }
    }

    /**
     * Using only O(mn) memory for additional grid (not as perfect as (O(m) space solutions given by other users but anyway..)
     * we can solve this problem through O(4mn) iterations.
     * We just need to scan the grid from left to right and accumulate the sum of enemies by dp[i][j] from left since the last wall.
     * Then we do the same from top to bottom, from right to left and finally from bottom to top.
     * On each iteration we increase the dp[i][j] value by cumulative sum.
     */

    class solution {
        public int maxKilledEnemies(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];

            // from left to right
            for (int i = 0; i < m; i++) {
                int current = 0;
                for (int j = 0; j < n; j++)
                    current = process(grid, dp, i, current, j);
            }
            // from top to bottom
            for (int j = 0; j < n; j++) {
                int current = 0;
                for (int i = 0; i < m; i++)
                    current = process(grid, dp, i, current, j);
            }
            // from right to left
            for (int i = 0; i < m; i++) {
                int current = 0;
                for (int j = n - 1; j >= 0; j--)
                    current = process(grid, dp, i, current, j);
            }
            int ans = 0;
            // from bottom to top
            for (int j = 0; j < n; j++) {
                int current = 0;
                for (int i = m - 1; i >= 0; i--) {
                    current = process(grid, dp, i, current, j);
                    if (grid[i][j] == '0')  ans = Math.max(ans, dp[i][j]);
                }
            }

            return ans;
        }

        private int process(char[][] c, int[][] dp, int i, int current, int j) {
            if (c[i][j] == 'W') current = 0;
            if (c[i][j] == 'E') current++;
            dp[i][j] += current;
            return current;
        }
    }

}
