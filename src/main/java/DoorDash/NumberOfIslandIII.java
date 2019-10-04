package DoorDash;

/**
 * Created by cicean on 8/16/2018.
 */
public class NumberOfIslandIII {
    /**
     * @param matrix, a 2-d array (list of lists) of integers
     * @return the size of the largest contiguous block (horizontally/vertically connected) of numbers with the same value
     */

    public int largestIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        int ans = dp[0][0];
        for (int i = 1; i < m; i++) {
            if (grid[i][0] == grid[i - 1][0]) {
                dp[i][0] = dp[i-1][0] + 1;
            } else {
                dp[i][0] = 1;
            }
            //System.out.print("i = " + dp[i][0] +",");
            ans = Math.max(dp[i][0], ans);
        }
        for (int j = 1; j < n; j++) {
            if (grid[0][j] == grid[0][j - 1]) {
                dp[0][j] = dp[0][j - 1] + 1;
            } else {
                dp[0][j] = 1;
            }
            ans = Math.max(dp[0][j], ans);
            //System.out.print("j = " + dp[0][j] +",");
        }


        for(int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == grid[i - 1][j] && grid[i][j] == grid[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    if (grid[i][j] == grid[i - 1][j]) {
                        dp[i][j] = dp[i - 1][j] + 1;
                    } else if (grid[i][j] == grid[i][j - 1]) {
                        dp[i][j] = dp[i][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                }
                ans = Math.max(dp[i][j], ans);
                //System.out.print(dp[i][j] +",");
            }
        }

        return ans;

    }


}
