/**
 * 803. Bricks Falling When Hit
 * Hard
 *
 * 394
 *
 * 117
 *
 * Add to List
 *
 * Share
 * We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.
 *
 * We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.
 *
 * Return an array representing the number of bricks that will drop after each erasure in sequence.
 *
 * Example 1:
 * Input:
 * grid = [[1,0,0,0],[1,1,1,0]]
 * hits = [[1,0]]
 * Output: [2]
 * Explanation:
 * If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
 * Example 2:
 * Input:
 * grid = [[1,0,0,0],[1,1,0,0]]
 * hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation:
 * When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 *
 *
 * Note:
 *
 * The number of rows and columns in the grid will be in the range [1, 200].
 * The number of erasures will not exceed the area of the grid.
 * It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
 * An erasure may refer to a location with no brick - if it does, no bricks drop.
 * Accepted
 * 13,780
 * Submissions
 * 45,645
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 */
public class BricksFallingWhenHit {

    /**
     * Approach #1: Reverse Time and Union-Find [Accepted]
     * Intuition
     *
     * The problem is about knowing information about the connected components of a graph as we cut vertices. In particular, we'll like to know the size of the "roof" (component touching the top edge) between each cut. Here, a cut refers to the erasure of a vertex.
     *
     * As we may know, a useful data structure for joining connected components is a disjoint set union structure. The key idea in this problem is that we can use this structure if we work in reverse: instead of looking at the graph as a series of sequential cuts, we'll look at the graph after all the cuts, and reverse each cut.
     *
     * Algorithm
     *
     * We'll modify our typical disjoint-set-union structure to include a dsu.size operation, that tells us the size of this component. The way we do this is whenever we make a component point to a new parent, we'll also send it's size to that parent.
     *
     * We'll also include dsu.top, which tells us the size of the "roof", or the component connected to the top edge. We use an ephemeral "source" node with label R * C where all nodes on the top edge (with row number 0) are connected to the source node.
     *
     * For more information on DSU, please look at Approach #2 in the article here.
     *
     * Next, we'll introduce A, the grid after all the cuts have happened, and initialize our disjoint union structure on the graph induced by A (nodes are grid squares with a brick; edges between 4-directionally adjacent nodes).
     *
     * After, if we get an cut at (r, c) but the original grid[r][c] was always 0, then we couldn't have had a meaningful cut - the number of dropped bricks is 0.
     *
     * Otherwise, we'll look at the size of the new roof after adding this brick at (r, c), and compare them to find the number of dropped bricks.
     *
     * Since we were working in reverse time order, we should reverse our working answer to arrive at our final answer.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N*Q*\alpha(N * Q))O(N∗Q∗α(N∗Q)), where N = R*CN=R∗C is the number of grid squares, QQ is the length of hits, and \alphaα is the Inverse-Ackermann function.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution {
        public int[] hitBricks(int[][] grid, int[][] hits) {
            int R = grid.length, C = grid[0].length;
            int[] dr = {1, 0, -1, 0};
            int[] dc = {0, 1, 0, -1};

            int[][] A = new int[R][C];
            for (int r = 0; r < R; ++r)
                A[r] = grid[r].clone();
            for (int[] hit: hits)
                A[hit[0]][hit[1]] = 0;

            DSU dsu = new DSU(R*C + 1);
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (A[r][c] == 1) {
                        int i = r * C + c;
                        if (r == 0)
                            dsu.union(i, R*C);
                        if (r > 0 && A[r-1][c] == 1)
                            dsu.union(i, (r-1) *C + c);
                        if (c > 0 && A[r][c-1] == 1)
                            dsu.union(i, r * C + c-1);
                    }
                }
            }
            int t = hits.length;
            int[] ans = new int[t--];

            while (t >= 0) {
                int r = hits[t][0];
                int c = hits[t][1];
                int preRoof = dsu.top();
                if (grid[r][c] == 0) {
                    t--;
                } else {
                    int i = r * C + c;
                    for (int k = 0; k < 4; ++k) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if (0 <= nr && nr < R && 0 <= nc && nc < C && A[nr][nc] == 1)
                            dsu.union(i, nr * C + nc);
                    }
                    if (r == 0)
                        dsu.union(i, R*C);
                    A[r][c] = 1;
                    ans[t--] = Math.max(0, dsu.top() - preRoof - 1);
                }
            }

            return ans;
        }
    }

    class DSU {
        int[] parent;
        int[] rank;
        int[] sz;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
            rank = new int[N];
            sz = new int[N];
            Arrays.fill(sz, 1);
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) return;

            if (rank[xr] < rank[yr]) {
                int tmp = yr;
                yr = xr;
                xr = tmp;
            }
            if (rank[xr] == rank[yr])
                rank[xr]++;

            parent[yr] = xr;
            sz[xr] += sz[yr];
        }

        public int size(int x) {
            return sz[find(x)];
        }

        public int top() {
            return size(sz.length - 1) - 1;
        }
    }

    /**
     * DFS
     */
    class Solution {
        public int[] hitBricks(int[][] grid, int[][] hits) {
            if (hits == null || hits.length == 0) return null;
            int[] res = new int[hits.length];
            if (grid == null || grid.length == 0) return res;
            int m = grid.length, n = grid[0].length;
            if (n == 0) return res;

            for (int k = 0; k < hits.length; k++){
                int x = hits[k][0], y = hits[k][1];
                if (grid[x][y] == 1) grid[x][y] = -1;
            }
            // set all cells attached to the roof to 2
            int count = countBricks(grid);

            for (int k = hits.length-1; k >= 0; k--){
                int x = hits[k][0], y = hits[k][1];
                if (grid[x][y] == 0) res[k] = 0;
                else {
                    grid[x][y] = 1;
                    if (!attachedToRoof(grid, x, y)) continue;
                    res[k] = dfs(grid, x, y) - 1;
                }
            }
            return res;
        }

        public int countBricks(int[][] grid){
            int m = grid.length, n = grid[0].length;
            int count = 0;
            for (int j = 0; j < n; j++){
                count += dfs(grid, 0, j);
            }
            return count;
        }

        // count all cells starting from (i, j)
        public int dfs(int[][] grid, int i, int j){
            if (grid[i][j] != 1) return 0;
            int m = grid.length, n = grid[0].length;
            int count = 1;
            grid[i][j] = 2;
            int[] shift = new int[]{-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; k++){
                int p = i + shift[k];
                int q = j + shift[k+1];
                if (p < 0 || p >= m || q < 0 || q >= n) continue;
                if (grid[p][q] != 1) continue;
                count += dfs(grid, p, q);
            }
            return count;
        }

        public boolean attachedToRoof(int[][] grid, int i, int j){
            if (i == 0) return true;
            int m = grid.length, n = grid[0].length;
            int[] shift = new int[]{-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; k++){
                int p = i + shift[k];
                int q = j + shift[k+1];
                if (p < 0 || p >= m || q < 0 || q >= n) continue;
                if (grid[p][q] == 2) return true;
            }
            return false;
        }
    }

    static class Solution2 {

        private static int BRICK = 1;
        private static int HIT = -1;
        private static int VISITED = 2;

        private int n, m;
        private int[] dir = {-1, 0, 1, 0, -1};
        public int[] hitBricks(int[][] grid, int[][] hits) {
            if (hits.length == 0) return new int[0];

            int[] ans = new int[hits.length];
            n = grid.length;
            m = grid[0].length;
            for (int[] hit: hits) {
                if (grid[hit[0]][hit[1]] == BRICK)
                    grid[hit[0]][hit[1]] = HIT;
            }

            for (int j = 0; j < m; ++j) {
                if (grid[0][j] == BRICK) {
                    grid[0][j] = VISITED;
                    dfs(grid, 0, j);
                }
            }

            for (int k = ans.length - 1; k >=0; --k) {
                int i = hits[k][0], j = hits[k][1];

                if (grid[i][j] == HIT) {
                    grid[i][j] = BRICK;
                    if (isConnected(grid, i, j)) {
                        grid[i][j] = VISITED;
                        ans[k] = dfs(grid, i, j) - 1;
                    }
                }
            }
            return ans;
        }

        private int dfs(int[][] grid, int i, int j) {
            int sum = 1;
            for (int d = 0; d < 4; ++d) {
                int x = i + dir[d];
                int y = j + dir[d + 1];
                if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == BRICK) {
                    grid[x][y] = VISITED;
                    sum += dfs(grid, x, y);
                }
            }
            return sum;
        }

        private boolean isConnected(int[][] grid, int i, int j) {
            return i == 0 || grid[i - 1][j] == VISITED || (j - 1 >= 0 && grid[i][j - 1] == VISITED)
                    || (j + 1 < m && grid[i][j + 1] == VISITED) || (i + 1 < n && grid[i + 1][j] == VISITED);
        }
    }
}
