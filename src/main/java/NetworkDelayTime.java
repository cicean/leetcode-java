/**
 * 743. Network Delay Time
 * Medium
 *
 * 1059
 *
 * 202
 *
 * Add to List
 *
 * Share
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 *
 *
 * Note:
 *
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 * Accepted
 * 67,274
 * Submissions
 * 148,891
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * zestypanda
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 10
 * We visit each node at some time, and if that time is better than the fastest time we've reached this node,
 * we travel along outgoing edges in sorted order. Alternatively, we could use Dijkstra's algorithm.
 */
public class NetworkDelayTime {
    /**
     * Approach #1: Depth-First Search [Accepted]
     * Intuition
     *
     * Let's record the time dist[node] when the signal reaches the node. If some signal arrived earlier, we don't need to broadcast it anymore. Otherwise, we should broadcast the signal.
     *
     * Algorithm
     *
     * We'll maintain dist[node], the earliest that we arrived at each node. When visiting a node while elapsed time has elapsed, if this is the currently-fastest signal at this node, let's broadcast signals from this node.
     *
     * To speed things up, at each visited node we'll consider signals exiting the node that are faster first, by sorting the edges.
     * Complexity Analysis
     *
     * Time Complexity: O(N^N + E \log E)O(N
     * N
     *  +ElogE) where EE is the length of times. We can only fully visit each node up to N-1N−1 times, one per each other node. Plus, we have to explore every edge and sort them. Sorting each small bucket of outgoing edges is bounded by sorting all of them, because of repeated use of the inequality x \log x + y \log y \leq (x+y) \log (x+y)xlogx+ylogy≤(x+y)log(x+y).
     *
     * Space Complexity: O(N + E)O(N+E), the size of the graph (O(E)O(E)), plus the size of the implicit call stack in our DFS (O(N)O(N)).
     */

    class Solution {
        Map<Integer, Integer> dist;
        public int networkDelayTime(int[][] times, int N, int K) {
            Map<Integer, List<int[]>> graph = new HashMap();
            for (int[] edge: times) {
                if (!graph.containsKey(edge[0]))
                    graph.put(edge[0], new ArrayList<int[]>());
                graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
            }
            for (int node: graph.keySet()) {
                Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
            }
            dist = new HashMap();
            for (int node = 1; node <= N; ++node)
                dist.put(node, Integer.MAX_VALUE);

            dfs(graph, K, 0);
            int ans = 0;
            for (int cand: dist.values()) {
                if (cand == Integer.MAX_VALUE) return -1;
                ans = Math.max(ans, cand);
            }
            return ans;
        }

        public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
            if (elapsed >= dist.get(node)) return;
            dist.put(node, elapsed);
            if (graph.containsKey(node))
                for (int[] info: graph.get(node))
                    dfs(graph, info[1], elapsed + info[0]);
        }
    }

    /**
     * Approach #2: Dijkstra's Algorithm [Accepted]
     * Intuition and Algorithm
     *
     * We use Dijkstra's algorithm to find the shortest path from our source to all targets. This is a textbook algorithm, refer to this link for more details.
     *
     * Dijkstra's algorithm is based on repeatedly making the candidate move that has the least distance travelled.
     *
     * In our implementations below, we showcase both O(N^2)O(N
     * 2
     *  ) (basic) and O(N \log N)O(NlogN) (heap) approaches.
     *
     * Basic Implementation
     * Complexity Analysis
     *
     * Time Complexity: O(N^2 + E)O(N
     * 2
     *  +E)m where EE is the length of times in the basic implementation, and O(E \log E)O(ElogE) in the heap implementation, as potentially every edge gets added to the heap.
     *
     * Space Complexity: O(N + E)O(N+E), the size of the graph (O(E)O(E)), plus the size of the other objects used (O(N)O(N)).
     */

    class Solution {
        Map<Integer, Integer> dist;
        public int networkDelayTime(int[][] times, int N, int K) {
            Map<Integer, List<int[]>> graph = new HashMap();
            for (int[] edge: times) {
                if (!graph.containsKey(edge[0]))
                    graph.put(edge[0], new ArrayList<int[]>());
                graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            }
            dist = new HashMap();
            for (int node = 1; node <= N; ++node)
                dist.put(node, Integer.MAX_VALUE);

            dist.put(K, 0);
            boolean[] seen = new boolean[N+1];

            while (true) {
                int candNode = -1;
                int candDist = Integer.MAX_VALUE;
                for (int i = 1; i <= N; ++i) {
                    if (!seen[i] && dist.get(i) < candDist) {
                        candDist = dist.get(i);
                        candNode = i;
                    }
                }

                if (candNode < 0) break;
                seen[candNode] = true;
                if (graph.containsKey(candNode))
                    for (int[] info: graph.get(candNode))
                        dist.put(info[0],
                                Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
            }

            int ans = 0;
            for (int cand: dist.values()) {
                if (cand == Integer.MAX_VALUE) return -1;
                ans = Math.max(ans, cand);
            }
            return ans;
        }
    }

    class Solution_heap {
        public int networkDelayTime(int[][] times, int N, int K) {
            Map<Integer, List<int[]>> graph = new HashMap();
            for (int[] edge: times) {
                if (!graph.containsKey(edge[0]))
                    graph.put(edge[0], new ArrayList<int[]>());
                graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            }
            PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                    (info1, info2) -> info1[0] - info2[0]);
            heap.offer(new int[]{0, K});

            Map<Integer, Integer> dist = new HashMap();

            while (!heap.isEmpty()) {
                int[] info = heap.poll();
                int d = info[0], node = info[1];
                if (dist.containsKey(node)) continue;
                dist.put(node, d);
                if (graph.containsKey(node))
                    for (int[] edge: graph.get(node)) {
                        int nei = edge[0], d2 = edge[1];
                        if (!dist.containsKey(nei))
                            heap.offer(new int[]{d+d2, nei});
                    }
            }

            if (dist.size() != N) return -1;
            int ans = 0;
            for (int cand: dist.values())
                ans = Math.max(ans, cand);
            return ans;
        }
    }

    class Solution_1 {
        public int networkDelayTime(int[][] times, int N, int K) {
            int[][] dist = new int[N][];
            for (int i = 0; i < dist.length; i++) {
                dist[i] = new int[N];
                for (int j = 0; j < dist.length; j++) {
                    dist[i][j] = -1;
                }
            }

            for (int[] edge : times)
                dist[edge[0] - 1][edge[1] - 1] = edge[2];
            int s = K - 1;
            int min = -1, minIndex = -1;
            for (int i = 0; i < N; i++) {
                if (s == i || dist[s][i] < 0)
                    continue;
                if (min < 0 || dist[s][i] < min) {
                    min = dist[s][i];
                    minIndex = i;
                }
            }

            int t = minIndex;
            boolean[] visited = new boolean[N];
            visited[s] = true;
            while (t >= 0) {
                visited[t] = true;
                for (int i = 0; i < N; i++) {
                    if (visited[i] || dist[t][i] < 0)
                        continue;
                    int d = dist[s][t] + dist[t][i];
                    if (dist[s][i] < 0 || d < dist[s][i]) {
                        dist[s][i] = d;
                    }
                }

                min = -1;
                minIndex = -1;
                for (int i = 0; i < N; i++) {
                    if (visited[i] || dist[s][i] < 0)
                        continue;
                    if (min < 0 || dist[s][i] < min) {
                        min = dist[s][i];
                        minIndex = i;
                    }
                }
                t = minIndex;
            }

            int max = 0;
            for (int i = 0; i < N; i++) {
                if (i == s)
                    continue;
                int d = dist[s][i];
                if (d < 0)
                    return -1;
                max = Math.max(max, d);
            }
            return max;
        }
    }
}
