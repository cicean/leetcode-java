import java.util.*;

/**
 * 1192. Critical Connections in a Network
 * Hard
 *
 * 681
 *
 * 62
 *
 * Add to List
 *
 * Share
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 * Accepted
 * 33,756
 * Submissions
 * 69,589
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 224
 *
 * Microsoft
 * |
 * 2
 * Use Tarjan's algorithm.
 */
public class CriticalConnectionsinaNetwork {

    /**
     * Tarjans Strongly Connected Components algorithm | Graph Theory
     */
    class Solution {
        int edgeIndex = 0;
        int[] to;
        int[] next;
        int[] head;
        int[] low;
        int[] disc;
        int time = -1;
        List<List<Integer>> res = new ArrayList<>();
        private void addEdge(int u, int v) {
            to[edgeIndex] = v;
            next[edgeIndex] = head[u];
            head[u] = edgeIndex ++;
        }

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            low = new int[n];
            disc = new int[n];
            int m = connections.size();
            to = new int[m * 2];
            head = new int[n];
            next = new int[m * 2];
            Arrays.fill(head, -1);
            Arrays.fill(next, -1);
            Arrays.fill(low, -1);
            Arrays.fill(disc, -1);

            for (List<Integer> edge : connections) {
                int u = edge.get(0);
                int v = edge.get(1);
                addEdge(u, v);
                addEdge(v, u);
            }

            dfs(1, -1);
            return res;
        }
        private void dfs(int node, int parent) {
            if (disc[node] != -1) {
                return;
            }
            low[node] = disc[node] = ++ time;
            for (int edge = head[node]; edge != -1; edge = next[edge]) {
                int next = to[edge];
                if (disc[next] == -1) {
                    dfs(next, node);
                    low[node] = Math.min(low[node], low[next]);
                    if (low[next] > disc[node]) {
                        res.add(Arrays.asList(node, next));
                    }
                } else if (next != parent) {
                    low[node] = Math.min(low[node], disc[next]);
                }
            }
        }
    }
}
