import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cicean on 8/29/2016.
 * 317. Shortest Distance from All Buildings  QuestionEditorial Solution  My Submissions
 Total Accepted: 6643 Total Submissions: 20583 Difficulty: Hard
 You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

 Each 0 marks an empty land which you can pass by freely.
 Each 1 marks a building which you cannot pass through.
 Each 2 marks an obstacle which you cannot pass through.
 For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

 1 - 0 - 2 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0
 The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

 Note:
 There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

 Hide Company Tags Google Zenefits
 Hide Tags Breadth-first Search
 Hide Similar Problems (M) Walls and Gates (H) Best Meeting Point


 */
public class ShortestDistancefromAllBuildings {

    /**
     * 分析
     这道理类似于Walls ang Gates, 解决方法也应该是从building出发进行BFS，
     不过这里不同的是这里需要返回最小距离和，所以我们应该一个一个的对building的点BFS，
     用一个二维矩阵存每个点到所有building的距离和，每次BFS，都更新相应的距离和。
     最后遍历那个距离和矩阵，找出最小值即可。
     需要注意的是，这道题还有个条件就是empty room 必须 reach all buildings，所以我们可以用另外一个矩阵存对应empty room到building的个数，如果最终个数不等于总的building数，对应点存的距离和无效。

     复杂度
     time: O(kMN), space: O(MN), k表示building数量
     */

    public class Solution {
        public int shortestDistance(int[][] grid) {
            int rows = grid.length;
            if (rows == 0) {
                return -1;
            }
            int cols = grid[0].length;

            // 记录到各个building距离和
            int[][] dist = new int[rows][cols];

            // 记录到能到达的building的数量
            int[][] nums = new int[rows][cols];
            int buildingNum = 0;

            // 从每个building开始BFS
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        buildingNum++;
                        bfs(grid, i, j, dist, nums);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0 && dist[i][j] != 0 && nums[i][j] == buildingNum)
                        min = Math.min(min, dist[i][j]);
                }
            }
            if (min < Integer.MAX_VALUE)
                return min;
            return -1;
        }

        public void bfs(int[][] grid, int row, int col, int[][] dist, int[][] nums) {
            int rows = grid.length;
            int cols = grid[0].length;

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{row, col});
            int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

            // 记录访问过的点
            boolean[][] visited = new boolean[rows][cols];
            int level = 0;
            while (!q.isEmpty()) {
                level++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] coords = q.remove();
                    for (int k = 0; k < dirs.length; k++) {
                        int x = coords[0] + dirs[k][0];
                        int y = coords[1] + dirs[k][1];
                        if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && grid[x][y] == 0) {
                            visited[x][y] = true;
                            dist[x][y] += level;
                            nums[x][y]++;
                            q.add(new int[]{x, y});
                        }
                    }
                }
            }
        }
    }



}
