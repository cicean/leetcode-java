import javafx.util.Pair;

import java.util.*;

/**
 * Minimum Cost to Make at Least One Valid Path in a Grid
 * Hard
 *
 * 99
 *
 * 3
 *
 * Add to List
 *
 * Share
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
 *
 * You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.
 *
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
 * Output: 3
 * Explanation: You will start at point (0, 0).
 * The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
 * The total cost = 3.
 * Example 2:
 *
 *
 * Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
 * Output: 0
 * Explanation: You can follow the path from (0, 0) to (2, 2).
 * Example 3:
 *
 *
 * Input: grid = [[1,2],[4,3]]
 * Output: 1
 * Example 4:
 *
 * Input: grid = [[2,2,2],[2,2,2]]
 * Output: 3
 * Example 5:
 *
 * Input: grid = [[4]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * Accepted
 * 3,318
 * Submissions
 * 6,576
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
 * LeetCode
 * Build a graph where grid[i][j] is connected to all the four side-adjacent cells with weighted edge. the weight is 0 if the sign is pointing to the adjacent cell or 1 otherwise.
 * Do BFS from (0, 0) visit all edges with weight = 0 first. the answer is the distance to (m -1, n - 1).
 *
 */
public class MinimumCosttoMakeatLeastOneValidPathinaGrid {

    /**
     * Intuition
     * One observation is that, (not sure if it's obvious)
     * we can greedily explore the grid.
     * We will never detour the path to a node that we can already reach.
     *
     * In the view of graph,
     * the fleche indicates a directed edge of weight = 0.
     * The distance between all neighbours are at most 1.
     * Now we want to find out the minimum distance between top-left and bottom-right.
     *
     *
     * Explanation
     * Find out all reachable nodes without changing anything.
     * Save all new visited nodes to a queue bfs.
     * Now iterate the queue
     * 3.1 For each node, try changing it to all 3 other direction
     * 3.2 Save the new reachable and not visited nodes to the queue.
     * 3.3 repeat step 3
     *
     * Complexity
     * Time O(NM)
     * Space O(NM)
     */

    int INF = (int) 1e9;
    int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length, cost = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], INF);
        Queue<int[]> bfs = new LinkedList<>();
        dfs(grid, 0, 0, dp, cost, bfs);
        while (!bfs.isEmpty()) {
            cost++;
            for (int size = bfs.size(); size > 0; size--) {
                int[] top = bfs.poll();
                int r = top[0], c = top[1];
                for (int i = 0; i < 4; i++) dfs(grid, r + DIR[i][0], c + DIR[i][1], dp, cost, bfs);
            }
        }
        return dp[m - 1][n - 1];
    }

    void dfs(int[][] grid, int r, int c, int[][] dp, int cost, Queue<int[]> bfs) {
        int m = grid.length; int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != INF) return;
        dp[r][c] = cost;
        bfs.offer(new int[]{r, c}); // add to try change direction later
        int nextDir = grid[r][c] - 1;
        dfs(grid, r + DIR[nextDir][0], c + DIR[nextDir][1], dp, cost, bfs);
    }

    class Solution {
        public int minCost(int[][] grid) {
            final int[][] DIR = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            final int m = grid.length;
            final int n = grid[0].length;
            //Deque<Pair<Integer, Integer>> q = new LinkedList<>();
            PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
            q.add(new int[]{0, 0});
            boolean[] visited = new boolean[m*n];
            while (!q.isEmpty()) {
                int[] tmp = q.poll();
                int x = tmp[0] % n, y = tmp[0] / n;
                if (x == n - 1 && y == m - 1) {
                    return tmp[1];
                }

                if (visited[tmp[0]]) {
                    continue;
                }

                visited[tmp[0]] = true;
                for (int i  = 0; i < 4; i++) {
                    int tx = x + DIR[i][0], ty = y + DIR[i][1];
                    int tp = ty * n + tx;
                    if (tx < 0 || ty < 0 || tx >= n || ty >= m || visited[tp]) {
                        continue;
                    }
                    System.out.println("x = " + x + ", y = " + y + ", cost = " + tmp[1]);
                    System.out.println("tx = " + tx + ", ty = " + ty);
                    if (grid[y][x] == i + 1) {
                        q.add(new int[]{tp, tmp[1]});
                    } else {
                        q.add(new int[]{tp, tmp[1] + 1});
                    }
                }
            }

            return -1;
        }
    }

    class Solution {
        public int minCost(int[][] grid) {
            final int[][] DIR = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            final int m = grid.length;
            final int n = grid[0].length;
            Deque<Pair<Integer, Integer>> q = new LinkedList<>();
            q.addFirst(new Pair<>(0, 0));
            boolean[] visited = new boolean[m*n];
            while (!q.isEmpty()) {
                Pair<Integer, Integer> tmp = q.pollFirst();
                int x = tmp.getKey() % n, y = tmp.getKey() / n;
                if (x == n - 1 && y == m - 1) {
                    return tmp.getValue();
                }

                if (visited[tmp.getKey()]) {
                    continue;
                }

                visited[tmp.getKey()] = true;
                for (int i  = 0; i < 4; i++) {
                    int tx = x + DIR[i][0], ty = y + DIR[i][1];
                    int tp = ty * n + tx;
                    if (tx < 0 || ty < 0 || tx >= n || ty >= m || visited[tp]) {
                        continue;
                    }
                    System.out.println("x = " + x + ", y = " + y + ", cost = " + tmp.getValue());
                    System.out.println("tx = " + tx + ", ty = " + ty);
                    if (grid[y][x] == i + 1) {
                        q.addFirst(new Pair<Integer, Integer>(tp, tmp.getValue()));
                    } else {
                        q.addLast(new Pair<Integer, Integer>(tp, tmp.getValue() + 1));
                    }
                }

            }

            return -1;
        }
    }


}
