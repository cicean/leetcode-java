/**
 63	Unique Paths II	28.0%	Medium
 Problem:    Unique Paths II
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/unique-paths-ii/
 Notes:
 Follow up for "Unique Paths":
 Now consider if some obstacles are added to the grids. How many unique paths would there be?
 An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 For example,
 There is one obstacle in the middle of a 3x3 grid as illustrated below.
 [
  [0,0,0],
  [0,1,0],
  [0,0,0]
 ]
 The total number of unique paths is 2.
 Note: m and n will be at most 100.
 Solution: Dynamic programming.
 */

public class UniquePathsII {
	
	public int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        if (obstacleGrid[0][0] == 1) return 0;
        dp[0][0] = 1;
        for (int i = 1; i < m; i++)
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i-1][0];
        for (int j = 1; j < n; j++)
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j-1];
        
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0: dp[i-1][j] + dp[i][j-1];
        
        return dp[m-1][n-1];  
    }
    public int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) return 0;
        dp[0] = 1;
        for (int i = 0; i < m; ++i) {
            dp[0] = obstacleGrid[i][0] == 1 ? 0 : dp[0];
            for(int j = 1; j < n; ++j) {
                 dp[j] = obstacleGrid[i][j] == 1 ? 0: dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }

    private int res = 0;
    
    public int uniquePathsWithObstacles_3(int[][] obstacleGrid) {
        if (obstacleGrid == null) return 0;
        bfs(obstacleGrid, 0, 0, obstacleGrid.length, obstacleGrid[0].length);
        return res;
    }

    public void bfs(int[][] obstacleGrid, int x, int y, int m, int n) {
    	if (x >= m || y >= n) return;

        if (obstacleGrid[x][y] == 1) return;
        System.out.println("x = " + x + ", y = " + y);
        if (x == m - 1 && y == n - 1)  {
            res++;
            System.out.println(res);
            return;
        }
        
        bfs(obstacleGrid, x + 1 , y, obstacleGrid.length, obstacleGrid[0].length);
        bfs(obstacleGrid, x, y + 1, obstacleGrid.length, obstacleGrid[0].length); 
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	UniquePathsII slt = new UniquePathsII();
		int[][]obstacleGrid = {
				  {0,0,0},
				  {0,1,0},
				  {0,0,0}
		};
		System.out.println(slt.uniquePathsWithObstacles_2(obstacleGrid));
        System.out.println(slt.uniquePathsWithObstacles_3(obstacleGrid));
	}

}
