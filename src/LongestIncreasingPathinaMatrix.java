/**
 * Created by cicean on 8/30/2016.
 * 329. Longest Increasing Path in a Matrix  QuestionEditorial Solution  My Submissions
 Total Accepted: 19770 Total Submissions: 58884 Difficulty: Hard
 Given an integer matrix, find the length of the longest increasing path.

 From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

 Example 1:

 nums = [
 [9,9,4],
 [6,6,8],
 [2,1,1]
 ]
 Return 4
 The longest increasing path is [1, 2, 6, 9].

 Example 2:

 nums = [
 [3,4,5],
 [3,2,6],
 [2,2,1]
 ]
 Return 4
 The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags

 题意：给定一个矩阵，在里面找出最大上升路径
 */
public class LongestIncreasingPathinaMatrix {

    /**
     * 记忆化搜索。

     设dis[i][j]为当前点出发最大上升路径的值。初始设置为0，表示该点未知，需要更新。

     再次碰到的时候只需要返回该值即可。
     */

    //O(mn) time O(mn) space
    public class Solution {
        int []dx = { 1 , -1, 0 , 0  };
        int []dy = { 0 , 0 , 1 , -1 };
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) return 0;
            int m = matrix.length, n = matrix[0].length;
            int[][] dis = new int [m][n];
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, dfs( i, j, m, n, matrix, dis));
                }
            }
            return ans;
        }

        int dfs(int x, int y, int m,int n,int[][] matrix, int[][] dis) {
            if (dis[x][y] != 0) return dis[x][y];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && matrix[nx][ny] > matrix[x][y]) {
                    dis[x][y] = Math.max(dis[x][y], dfs(nx, ny, m, n, matrix, dis));
                }
            }
            return ++dis[x][y];
        }
    }

    //Graph theory, Java solution, O(v^2), no DFS

    public class solution2 {
        public  class Point{
            int x;
            int y;
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
                return 0;
            int n = matrix.length, m = matrix[0].length, count = m * n, ans = 0;
            while (count > 0) {
                HashSet<Point> remove = new HashSet<Point>();
                // each round, remove the peak number.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (matrix[i][j] == Integer.MIN_VALUE)
                            continue;
                        boolean up = (i == 0 || matrix[i][j] >= matrix[i - 1][j]);
                        boolean bottom = (i == n - 1 || matrix[i][j] >= matrix[i + 1][j]);
                        boolean left = (j == 0 || matrix[i][j] >= matrix[i][j - 1]);
                        boolean right = (j == m - 1 || matrix[i][j] >= matrix[i][j + 1]);
                        if (up && bottom && left && right)
                            remove.add(new Point(i, j));
                    }
                }
                for (Point point : remove) {
                    matrix[point.x][point.y] = Integer.MIN_VALUE;
                    count--;
                }
                ans++;
            }
            return ans;
        }
    }

}
