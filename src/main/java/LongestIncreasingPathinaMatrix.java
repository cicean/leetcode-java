import java.util.HashSet;

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

 ���⣺����һ�������������ҳ��������·��
 */



public class LongestIncreasingPathinaMatrix {

    /**
     * ���仯������

     ��dis[i][j]Ϊ��ǰ������������·����ֵ����ʼ����Ϊ0����ʾ�õ�δ֪����Ҫ���¡�

     �ٴ�������ʱ��ֻ��Ҫ���ظ�ֵ���ɡ�
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

    class Solution {
    /*
    dfs + Memoization
    这个DFS和island的DFS方向不同，所以用了Memoization。 island类是由i,j拓展到它的周边，这道题是由周边来计算i，j处的值
    https://leetcode.com/articles/longest-increasing-path-matrix/
    这里用的是Approach #2
    对比Approach #1 (Naive DFS)
    其他方法待研究https://leetcode.com/articles/longest-increasing-path-matrix/


    Time complexity : O(mn). Each vertex/cell will be calculated once and only once, and each edge will be visited once and only once. The total time complexity is then O(V+E). V is the total number of vertices and E is the total number of edges. In our problem, O(V) = O(mn), O(E) = O(4V) = O(mn).

Space complexity : O(mn). The cache dominates the space complexity
    */

        private static final int[][] dirs = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        private int m, n;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
                return 0;
            m = matrix.length;
            n = matrix[0].length;
            int res = 0;
            int[][] cache = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res = Math.max(res, dfs(matrix, i, j, cache));
                }
            }
            return res;
        }

        private int dfs(int[][] matrix, int i, int j, int[][] cache) {
            if (cache[i][j] != 0) return cache[i][j];
            int max = 1;
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                    max = Math.max(max, 1 + dfs(matrix, x, y, cache));
                }
            }
            cache[i][j] = max;
            return cache[i][j];
        }

    }

}
