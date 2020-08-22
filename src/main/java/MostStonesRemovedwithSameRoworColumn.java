/**
 * 947. Most Stones Removed with Same Row or Column
 * Medium
 *
 * 825
 *
 * 285
 *
 * Add to List
 *
 * Share
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 *
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 *
 * What is the largest possible number of moves we can make?
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 * Accepted
 * 44,916
 * Submissions
 * 81,337
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * lee215
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 4
 */
public class MostStonesRemovedwithSameRoworColumn {

    /**
     * Solution
     * Approach 1: Depth-First Search
     * Intuition
     *
     * Let's say two stones are connected by an edge if they share a row or column, and define a connected component in the usual way for graphs: a subset of stones so that there doesn't exist an edge from a stone in the subset to a stone not in the subset. For convenience, we refer to a component as meaning a connected component.
     *
     * The main insight is that we can always make moves that reduce the number of stones in each component to 1.
     *
     * Firstly, every stone belongs to exactly one component, and moves in one component do not affect another component.
     *
     * Now, consider a spanning tree of our component. We can make moves repeatedly from the leaves of this tree until there is one stone left.
     *
     * Algorithm
     *
     * To count connected components of the above graph, we will use depth-first search.
     *
     * For every stone not yet visited, we will visit it and any stone in the same connected component. Our depth-first search traverses each node in the component.
     *
     * For each component, the answer changes by -1 + component.size.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of stones.
     *
     * Space Complexity: O(N^2)O(N
     * 2
     *  ).
     *
     */

    class Solution {
        public int removeStones(int[][] stones) {
            int N = stones.length;

            // graph[i][0] = the length of the 'list' graph[i][1:]
            int[][] graph = new int[N][N];
            for (int i = 0; i < N; ++i)
                for (int j = i+1; j < N; ++j)
                    if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                        graph[i][++graph[i][0]] = j;
                        graph[j][++graph[j][0]] = i;
                    }

            int ans = 0;
            boolean[] seen = new boolean[N];
            for (int i = 0; i < N; ++i) if (!seen[i]) {
                Stack<Integer> stack = new Stack();
                stack.push(i);
                seen[i] = true;
                ans--;
                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    ans++;
                    for (int k = 1; k <= graph[node][0]; ++k) {
                        int nei = graph[node][k];
                        if (!seen[nei]) {
                            stack.push(nei);
                            seen[nei] = true;
                        }
                    }
                }
            }

            return ans;
        }
    }
     /** Approach 2: Union-Find
     * Intuition
     *
     * As in Approach 1, we will need to consider components of an underlying graph. A "Disjoint Set Union" (DSU) data structure is ideal for this.
     *
     * We will skip the explanation of how a DSU structure is implemented. Please refer to https://leetcode.com/problems/redundant-connection/solution/ for a tutorial on DSU.
     *
     * Algorithm
     *
     * Let's connect row i to column j, which will be represented by j+10000. The answer is the number of components after making all the connections.
     *
     * Note that for brevity, our DSU implementation does not use union-by-rank. This makes the asymptotic time complexity larger.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N \log N)O(NlogN), where NN is the length of stones. (If we used union-by-rank, this can be O(N * \alpha(N))O(N∗α(N)), where \alphaα is the Inverse-Ackermann function.)
     *
     * Space Complexity: O(N)O(N).
     */

     class Solution {
         public int removeStones(int[][] stones) {
             int N = stones.length;
             DSU dsu = new DSU(20000);

             for (int[] stone: stones)
                 dsu.union(stone[0], stone[1] + 10000);

             Set<Integer> seen = new HashSet();
             for (int[] stone: stones)
                 seen.add(dsu.find(stone[0]));

             return N - seen.size();
         }
     }

    class DSU {
        int[] parent;
        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}
