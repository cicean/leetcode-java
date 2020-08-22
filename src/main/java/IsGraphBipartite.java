import java.util.*;

/**
 * 785. Is Graph Bipartite?
 * Medium
 *
 * 981
 *
 * 127
 *
 * Add to List
 *
 * Share
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 * Note:
 *
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 * Accepted
 * 79,223
 * Submissions
 * 172,996
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 13
 *
 * Amazon
 * |
 * 4
 *
 * Google
 * |
 * 2
 *
 * Microsoft
 * |
 * 2
 */
public class IsGraphBipartite {

    /**
     * Approach #1: Coloring by Depth-First Search [Accepted]
     * Intuition
     *
     * Color a node blue if it is part of the first set, else red. We should be able to greedily color the graph if and only if it is bipartite: one node being blue implies all it's neighbors are red, all those neighbors are blue, and so on.
     *
     *
     * Diagram of coloring neighbors of nodes
     *
     * Algorithm
     *
     * We'll keep an array (or hashmap) to lookup the color of each node: color[node]. The colors could be 0, 1, or uncolored (-1 or null).
     *
     * We should be careful to consider disconnected components of the graph, by searching each node. For each uncolored node, we'll start the coloring process by doing a depth-first-search on that node. Every neighbor gets colored the opposite color from the current node. If we find a neighbor colored the same color as the current node, then our coloring was impossible.
     *
     * To perform the depth-first search, we use a stack. For each uncolored neighbor in graph[node], we'll color it and add it to our stack, which acts as a sort of "todo list" of nodes to visit next. Our larger loop for start... ensures that we color every node.
     * Complexity Analysis
     *
     * Time Complexity: O(N + E)O(N+E), where NN is the number of nodes in the graph, and EE is the number of edges. We explore each node once when we transform it from uncolored to colored, traversing all its edges in the process.
     *
     * Space Complexity: O(N)O(N), the space used to store the color.
     */

    class Solution {
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            int[] color = new int[n];
            Arrays.fill(color, -1);

            for (int start = 0; start < n; ++start) {
                if (color[start] == -1) {
                    Stack<Integer> stack = new Stack();
                    stack.push(start);
                    color[start] = 0;

                    while (!stack.empty()) {
                        Integer node = stack.pop();
                        for (int nei: graph[node]) {
                            if (color[nei] == -1) {
                                stack.push(nei);
                                color[nei] = color[node] ^ 1;
                            } else if (color[nei] == color[node]) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    /**
     *
     */

    class Solution2 {

        public boolean isBipartie(int[][] graph) {
            int[] visited = new int[graph.length];

            for (int i = 0; i < graph.length - 1; i++) {
                if (visited[1] != 0) {
                    continue;
                }

                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = 1;
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int curLable = visited[cur];
                    int neighborLable = curLable == 1 ? 2 : 1;
                    for (int neighbor : graph[cur]) {
                        if (visited[neighbor] == 0) {
                            visited[neighbor] = neighborLable;
                            queue.add(neighbor);
                        } else if (visited[neighbor] != neighborLable) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }


    class Solution3 {


        int colors[], graph[][];

        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            this.graph = graph;
            colors = new int[n];


            for (int i = 0; i < n; i++) {
                if (colors[i] == 0) {
                    if (!dfs(i, 1)) return false;
                }
            }
            return true;
        }


        boolean dfs(int node, int expectedColor) {
            if (colors[node] != 0) return colors[node] == expectedColor;
            colors[node] = expectedColor;

            for (int neighbor : graph[node]) {
                if (!dfs(neighbor, -expectedColor)) return false;
            }
            return true;
        }



    }

}
