/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * Hard
 *
 * 248
 *
 * 2
 *
 * Add to List
 *
 * Share
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * grid =
 * [[0,0,0],
 *  [1,1,0],
 *  [0,0,0],
 *  [0,1,1],
 *  [0,0,0]],
 * k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 *
 * Example 2:
 *
 * Input:
 * grid =
 * [[0,1,1],
 *  [1,1,1],
 *  [1,0,0]],
 * k = 1
 * Output: -1
 * Explanation:
 * We need to eliminate at least two obstacles to find such a walk.
 *
 *
 * Constraints:
 *
 * grid.length == m
 * grid[0].length == n
 * 1 <= m, n <= 40
 * 1 <= k <= m*n
 * grid[i][j] == 0 or 1
 * grid[0][0] == grid[m-1][n-1] == 0
 * Accepted
 * 7,453
 * Submissions
 * 17,597
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
 * Use BFS.
 * BFS on (x,y,r) x,y is coordinate, r is remain number of obstacles you can remove.
 */
public class ShortestPathinaGridwithObstaclesElimination {

    /**
     * BFS
     *
     */

    class Solution {
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        public int shortestPath(int[][] grid, int k) {
            int n = grid.length;
            int m = grid[0].length;
            Queue<int[]> q = new LinkedList();
            boolean[][][] visited = new boolean[n][m][k+1];
            visited[0][0][0] = true;
            q.offer(new int[]{0,0,0});
            int res = 0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i=0; i<size; i++){
                    int[] info = q.poll();
                    int r = info[0], c = info[1], curK = info[2];
                    if(r==n-1 && c==m-1){
                        return res;
                    }
                    for(int[] dir : dirs){
                        int nextR = dir[0] + r;
                        int nextC = dir[1] + c;
                        int nextK = curK;
                        if(nextR>=0 && nextR<n && nextC>=0 && nextC<m){
                            if(grid[nextR][nextC]==1){
                                nextK++;
                            }
                            if(nextK<=k && !visited[nextR][nextC][nextK]){
                                visited[nextR][nextC][nextK] = true;
                                q.offer(new int[]{nextR, nextC, nextK});
                            }
                        }
                    }
                }
                res++;
            }
            return -1;
        }
    }

    /**
     * DFS
     */

    class Solution {
        int min = Integer.MAX_VALUE;
        boolean[][] visited;
        public int shortestPath(int[][] grid, int k) {
            visited = new boolean[grid.length][grid[0].length];
            dfs(grid, 0, 0, k, 0);
            return min != Integer.MAX_VALUE ? min : -1;
        }

        boolean dfs(int[][] grid, int i, int j, int k, int steps) {
            if (steps > min) return false;
            if (i < 0 || j < 0 || i == grid.length || j == grid[0].length) {
                return false;
            }
            if (visited[i][j]) return false;
            if (grid[i][j] == 1) {
                if (k == 0) return false;
                k--;
            }
            if (grid.length == i+1 && grid[0].length == j+1) {
                min = Math.min(min, steps);
                System.out.println(min);
                return steps == grid.length+grid[0].length-2;
            }
            visited[i][j] = true;
            if (dfs(grid, i, j+1, k, steps+1)) return true;
            if (dfs(grid, i+1, j, k, steps+1)) return true;
            if (dfs(grid, i, j-1, k, steps+1)) return true;
            if (dfs(grid, i-1, j, k, steps+1)) return true;

            visited[i][j] = false;
            return false;
        }
    }
}
