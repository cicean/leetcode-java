/**
 * 802. Find Eventual Safe States
 * Medium
 *
 * 610
 *
 * 111
 *
 * Add to List
 *
 * Share
 * In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.
 *
 * Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.
 *
 * Which nodes are eventually safe?  Return them as an array in sorted order.
 *
 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
 *
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Here is a diagram of the above graph.
 *
 * Illustration of graph
 *
 * Note:
 *
 * graph will have length at most 10000.
 * The number of edges in the graph will not exceed 32000.
 * Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
 * Accepted
 * 32,010
 * Submissions
 * 67,238
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
 * 3
 */
public class FindEventualSafeStates {

    /**
     * Approach #1: Reverse Edges [Accepted]
     * Intuition
     *
     * The crux of the problem is whether you can reach a cycle from the node you start in. If you can, then there is a way to avoid stopping indefinitely; and if you can't, then after some finite number of steps you'll stop.
     *
     * Thinking about this property more, a node is eventually safe if all it's outgoing edges are to nodes that are eventually safe.
     *
     * This gives us the following idea: we start with nodes that have no outgoing edges - those are eventually safe. Now, we can update any nodes which only point to eventually safe nodes - those are also eventually safe. Then, we can update again, and so on.
     *
     * However, we'll need a good algorithm to make sure our updates are efficient.
     *
     * Algorithm
     *
     * We'll keep track of graph, a way to know for some node i, what the outgoing edges (i, j) are. We'll also keep track of rgraph, a way to know for some node j, what the incoming edges (i, j) are.
     *
     * Now for every node j which was declared eventually safe, we'll process them in a queue. We'll look at all parents i = rgraph[j] and remove the edge (i, j) from the graph (from graph). If this causes the graph to have no outgoing edges graph[i], then we'll declare it eventually safe and add it to our queue.
     *
     * Also, we'll keep track of everything we ever added to the queue, so we can read off the answer in sorted order later.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N + E)O(N+E), where NN is the number of nodes in the given graph, and EE is the total number of edges.
     *
     * Space Complexity: O(N)O(N) in additional space complexity.
     */
    class Solution {
        public List<Integer> eventualSafeNodes(int[][] G) {
            int N = G.length;
            boolean[] safe = new boolean[N];

            List<Set<Integer>> graph = new ArrayList();
            List<Set<Integer>> rgraph = new ArrayList();
            for (int i = 0; i < N; ++i) {
                graph.add(new HashSet());
                rgraph.add(new HashSet());
            }

            Queue<Integer> queue = new LinkedList();

            for (int i = 0; i < N; ++i) {
                if (G[i].length == 0)
                    queue.offer(i);
                for (int j: G[i]) {
                    graph.get(i).add(j);
                    rgraph.get(j).add(i);
                }
            }

            while (!queue.isEmpty()) {
                int j = queue.poll();
                safe[j] = true;
                for (int i: rgraph.get(j)) {
                    graph.get(i).remove(j);
                    if (graph.get(i).isEmpty())
                        queue.offer(i);
                }
            }

            List<Integer> ans = new ArrayList();
            for (int i = 0; i < N; ++i) if (safe[i])
                ans.add(i);

            return ans;
        }
    }
     /** Approach #2: Depth-First Search [Accepted]
     * Intuition
     *
     * As in Approach #1, the crux of the problem is whether you reach a cycle or not.
     *
     * Let us perform a "brute force": a cycle-finding DFS algorithm on each node individually. This is a classic "white-gray-black" DFS algorithm that would be part of any textbook on DFS. We mark a node gray on entry, and black on exit. If we see a gray node during our DFS, it must be part of a cycle. In a naive view, we'll clear the colors between each search.
     *
     * Algorithm
     *
     * We can improve this approach, by noticing that we don't need to clear the colors between each search.
     *
     * When we visit a node, the only possibilities are that we've marked the entire subtree black (which must be eventually safe), or it has a cycle and we have only marked the members of that cycle gray. So indeed, the invariant that gray nodes are always part of a cycle, and black nodes are always eventually safe is maintained.
     *
     * In order to exit our search quickly when we find a cycle (and not paint other nodes erroneously), we'll say the result of visiting a node is true if it is eventually safe, otherwise false. This allows information that we've reached a cycle to propagate up the call stack so that we can terminate our search early.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N + E)O(N+E), where NN is the number of nodes in the given graph, and EE is the total number of edges.
     *
     * Space Complexity: O(N)O(N) in additional space complexity.
     */

     class Solution {
         public List<Integer> eventualSafeNodes(int[][] graph) {
             int N = graph.length;
             int[] color = new int[N];
             List<Integer> ans = new ArrayList();

             for (int i = 0; i < N; ++i)
                 if (dfs(i, color, graph))
                     ans.add(i);
             return ans;
         }

         // colors: WHITE 0, GRAY 1, BLACK 2;
         public boolean dfs(int node, int[] color, int[][] graph) {
             if (color[node] > 0)
                 return color[node] == 2;

             color[node] = 1;
             for (int nei: graph[node]) {
                 if (color[node] == 2)
                     continue;
                 if (color[nei] == 1 || !dfs(nei, color, graph))
                     return false;
             }

             color[node] = 2;
             return true;
         }
     }
}
