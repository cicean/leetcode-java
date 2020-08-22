/**
 *
 */
public class PathWithMaximumMinimumValue {


    //Solution
/*
Binary Search + DFS:

Time: O(MN log MN)
Space: O(MN)

*/

    class Solution {
        private boolean isValid(int[][] A, int limit, boolean[][] marked, int[] dirs, int i, int j, int m, int n) {
            marked[i][j] = true;
            if (i == m - 1 && j == n - 1) {
                return true;
            }
            for (int k=0; k<4; k++) {
                int ni = i + dirs[k];
                int nj = j + dirs[k + 1];
                if (ni >= 0 && nj >= 0 && ni < m && nj < n && !marked[ni][nj] && A[ni][nj] >= limit) {
                    if (isValid(A, limit, marked, dirs, ni, nj, m, n)) {

                        return true;
                    }
                }
            }
            return false;
        }

        public int maximumMinimumPath(int[][] A) {
            int R = A.length;
            int C = A[0].length;
            int lo = 1;
            int hi = Math.min(A[0][0], A[R - 1][C - 1]) + 1;
            int[] dirs = new int[]{0, 1, 0, -1, 0};

            while (lo < hi) {
                int mid = (lo + hi) / 2;
                boolean[][] visited = new boolean[R][C];
                if (isValid(A, mid, visited, dirs, 0, 0, R, C)) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo - 1;
        }
    }


    /**
     * PQ + BFS
     */
    class Solution {
        public int maximumMinimumPath(int[][] A) {
            final int[][] DIRS = {{0,1},{1,0},{0,-1},{-1,0}};
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
            pq.add(new int[] {A[0][0], 0, 0});
            int maxscore = A[0][0];
            A[0][0] = -1; // visited
            while(!pq.isEmpty()) {
                int[] top = pq.poll();
                int i = top[1], j = top[2], n = A.length, m = A[0].length;
                maxscore = Math.min(maxscore, top[0]);
                if(i == n - 1 && j == m - 1)
                    break;
                for(int[] d : DIRS) {
                    int newi = d[0] + i, newj = d[1] + j;
                    if(newi >= 0 && newi < n && newj >= 0 && newj < m && A[newi][newj]>=0){
                        pq.add(new int[] {A[newi][newj], newi, newj});
                        A[newi][newj] = -1;
                    }
                }
            }
            return maxscore;
        }
    }
}
