import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by cicean on 8/29/2016.
 *
 * 310. Minimum Height Trees  QuestionEditorial Solution  My Submissions
 Total Accepted: 18283 Total Submissions: 66040 Difficulty: Medium
 For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

 Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 Example 1:

 Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

 0
 |
 1
 / \
 2   3
 return [1]

 Example 2:

 Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

 0  1  2
 \ | /
 3
 |
 4
 |
 5
 return [3, 4]

 Hint:

 How many MHTs can a graph have at most?
 Note:

 (1) According to the definition of tree on Wikipedia: ¡°a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.¡±

 (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Breadth-first Search Graph
 Hide Similar Problems (M) Course Schedule (M) Course Schedule II

 */
public class MinimumHeightTrees {


    /**
     * I am sharing two of my solutions, one is based on the longest path, and the other is related to Tree DP.

     Longest Path

     It is easy to see that the root of an MHT has to be the middle point (or two middle points) of the longest path of the tree.
     Though multiple longest paths can appear in an unrooted tree, they must share the same middle point(s).

     Computing the longest path of a unrooted tree can be done, in O(n) time, by tree dp, or simply 2 tree traversals (dfs or bfs).
     The following is some thought of the latter.

     Randomly select a node x as the root, do a dfs/bfs to find the node y that has the longest distance from x.
     Then y must be one of the endpoints on some longest path.
     Let y the new root, and do another dfs/bfs. Find the node z that has the longest distance from y.

     Now, the path from y to z is the longest one, and thus its middle point(s) is the answer.
     */

    public class Solution {
        int n;
        List<Integer>[] e;

        private void bfs(int start, int[] dist, int[] pre) {
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            dist[start] = 0;
            visited[start] = true;
            pre[start] = -1;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : e[u])
                    if (!visited[v]) {
                        visited[v] = true;
                        dist[v] = dist[u] + 1;
                        queue.add(v);
                        pre[v] = u;
                    }
            }
        }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n <= 0) return new ArrayList<>();
            this.n = n;
            e = new List[n];
            for (int i = 0; i < n; i++)
                e[i] = new ArrayList<>();
            for (int[] pair : edges) {
                int u = pair[0];
                int v = pair[1];
                e[u].add(v);
                e[v].add(u);
            }

            int[] d1 = new int[n];
            int[] d2 = new int[n];
            int[] pre = new int[n];
            bfs(0, d1, pre);
            int u = 0;
            for (int i = 0; i < n; i++)
                if (d1[i] > d1[u]) u = i;

            bfs(u, d2, pre);
            int v = 0;
            for (int i = 0; i < n; i++)
                if (d2[i] > d2[v]) v = i;

            List<Integer> list = new ArrayList<>();
            while (v != -1) {
                list.add(v);
                v = pre[v];
            }

            if (list.size() % 2 == 1) return Arrays.asList(list.get(list.size() / 2));
            else return Arrays.asList(list.get(list.size() / 2 - 1), list.get(list.size() / 2));
        }
    }

}
