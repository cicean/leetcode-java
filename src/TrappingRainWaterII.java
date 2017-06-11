import java.util.*;

/**
 * Created by cicean on 9/25/2016.
 * 407. Trapping Rain Water II  QuestionEditorial Solution  My Submissions
 Total Accepted: 236
 Total Submissions: 1041
 Difficulty: Hard
 Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

 Note:
 Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

 Example:

 Given the following 3x6 height map:
 [
 [1,4,3,1,3,2],
 [3,2,1,3,2,4],
 [2,3,3,2,3,1]
 ]

 Return 4.

 The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.


 After the rain, water are trapped between the blocks. The total volume of water trapped is 4.
 */
public class TrappingRainWaterII {

    public class Cell {
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    //Java solution using PriorityQueue
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return 0;

        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>(){
            public int compare(Cell a, Cell b) {
                return a.height - b.height;
            }
        });

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];

        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }

        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }

        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    res += Math.max(0, cell.height - heights[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
                }
            }
        }

        return res;
    }

    /**
     * 另外一种解法：

     蓄积雨水的单元格存在两种情况：

     1. 单元格的高度严格小于其上、下、左、右方向的4个单元格高度

     2. 单元格的高度小于或等于其上、下、左、右方向的4个单元格高度
     对于情况1，可以利用“木桶原理”将其高度调整为四周单元格中的最小高度

     对于情况2，可以通过DFS，寻找与其邻接的等高节点的四周高度的最小值

     算法步骤如下：

     首先，计算heightMap的和，记为sum0

     然后，初始化队列queue，将所有邻接高度大于等于自己的点加入队列。

     循环直到队列为空：

     记队头为h

     使用BFS寻找一个点集vs，其中的点与h等高，并且与h直接或者间接相邻。从而得到vs的最小临近点高度，记为minh。

     将vs中的所有点从队列移除；如果minh > h，则将vs中所有点的高度更新为minh，并将h加入队尾；

     再次计算heightMap的和，记为sum1，sum1 - sum0即为最终答案。
     */

    private int m, n;
    private int[][] heightMap;
    private int dx[] = {1, 0, -1, 0};
    private int dy[] = {0, 1, 0, -1};

    public int trapRainWater(int[][] heightMap) {
        this.heightMap = heightMap;
        this.m = heightMap.length;
        if (this.m > 0) this.n = heightMap[0].length;

        int sum = calcHeightMapSum();

        LinkedHashSet<Point> queue = new LinkedHashSet<Point>();
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (minNeighborHeight(i, j) >= heightMap[i][j]) {
                    queue.add(new Point(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point h = queue.iterator().next();
            HashSet<Point> vs = new HashSet<Point>();
            int minh = bfs(h, vs);
            if (minh > heightMap[h.x][h.y]) {
                for (Point e : vs) {
                    heightMap[e.x][e.y] = minh;
                    queue.remove(e);
                }
                queue.add(h);
            } else {
                for (Point e : vs) {
                    queue.remove(e);
                }
            }
        }

        return calcHeightMapSum() - sum;
    }

    private int bfs(Point p, HashSet<Point> vs) {
        int ans = Integer.MAX_VALUE;
        int height = heightMap[p.x][p.y];

        LinkedList<Point> queue = new LinkedList<Point>();
        vs.add(p);
        queue.add(p);

        while (!queue.isEmpty()) {
            Point h = queue.removeFirst();
            if (h.x == 0 || h.y == 0 || h.x == m - 1 || h.y == n - 1) {
                ans = Math.min(heightMap[h.x][h.y], ans);
                continue;
            }
            int minh = minNeighborHeight(h.x, h.y);
            if (minh != height) {
                ans = Math.min(minh, ans);
                continue;
            }
            for (int k = 0; k < dx.length; k++) {
                Point np = new Point(h.x + dx[k], h.y + dy[k]);
                if (heightMap[np.x][np.y] != height) {
                    ans = Math.min(ans, heightMap[np.x][np.y]);
                } else if (!vs.contains(np)) {
                    vs.add(np);
                    queue.add(np);
                }
            }
        }
        return ans;
    }

    private int minNeighborHeight(int i, int j) {
        int minh = Integer.MAX_VALUE;
        for (int k = 0; k < dx.length; k++) {
            int di = i + dx[k];
            int dj = j + dy[k];
            minh = Math.min(minh, heightMap[di][dj]);
        }
        return minh;
    }

    public int calcHeightMapSum() {
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += heightMap[i][j];
            }
        }
        return sum;
    }

}
