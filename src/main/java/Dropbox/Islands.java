package Dropbox;

/**
 * Created by cicean on 9/27/2018.
 */
public class Islands {

    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        // write your code here
        int result = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return result;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == true) {
                    result++;
                    bfs(grid, i, j);
                }
            }
        }

        return  result;
    }

    private void bfs(boolean[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x > grid.length - 1
                || y > grid[x].length - 1) {
            return;
        }

        if (grid[x][y] != true) {
            return;
        }

        grid[x][y] = false;

        bfs(grid, x + 1, y);
        bfs(grid, x - 1, y);
        bfs(grid, x, y - 1);
        bfs(grid, x, y + 1);
    }

    class UnionFind {

        private int[] father = null;
        private int count;

        private int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }

        public UnionFind(int n) {
            // initialize your data structure here.
            father = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
            }
        }

        public void connect(int a, int b) {
            // Write your code here
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;
                count --;
            }
        }

        public int query() {
            // Write your code here
            return count;
        }

        public void set_count(int total) {
            count = total;
        }
    }

    public class Solution {
        /**
         * @param grid a boolean 2D matrix
         * @return an integer
         */
        public int numIslands(boolean[][] grid) {
            int count = 0;
            int n = grid.length;
            if (n == 0)
                return 0;
            int m = grid[0].length;
            if (m == 0)
                return 0;
            UnionFind union_find = new UnionFind(n * m);

            int total = 0;
            for(int i = 0;i < grid.length; ++i)
                for(int j = 0;j < grid[0].length; ++j)
                    if (grid[i][j])
                        total ++;

            union_find.set_count(total);
            for(int i = 0;i < grid.length; ++i)
                for(int j = 0;j < grid[0].length; ++j)
                    if (grid[i][j]) {
                        if (i > 0 && grid[i - 1][j]) {
                            union_find.connect(i * m + j, (i - 1) * m + j);
                        }
                        if (i <  n - 1 && grid[i + 1][j]) {
                            union_find.connect(i * m + j, (i + 1) * m + j);
                        }
                        if (j > 0 && grid[i][j - 1]) {
                            union_find.connect(i * m + j, i * m + j - 1);
                        }
                        if (j < m - 1 && grid[i][j + 1]) {
                            union_find.connect(i * m + j, i * m + j + 1);
                        }
                    }
            return union_find.query();
        }
    }

}
