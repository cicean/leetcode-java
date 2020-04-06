import java.util.*;

/**
 * 1245. Tree Diameter
 * Medium
 *
 * 148
 *
 * 4
 *
 * Add to List
 *
 * Share
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
 *
 * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: edges = [[0,1],[0,2]]
 * Output: 2
 * Explanation:
 * A longest path of the tree is the path 1 - 0 - 2.
 * Example 2:
 *
 *
 *
 * Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
 * Output: 4
 * Explanation:
 * A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 *
 *
 * Constraints:
 *
 * 0 <= edges.length < 10^4
 * edges[i][0] != edges[i][1]
 * 0 <= edges[i][j] <= edges.length
 * The given edges form an undirected tree.
 * Accepted
 * 4,741
 * Submissions
 * 8,408
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 2
 * Start at any node A and traverse the tree to find the furthest node from it, let's call it B.
 * Having found the furthest node B, traverse the tree from B to find the furthest node from it, lets call it C.
 * The distance between B and C is the tree diameter.
 *
 */
public class TreeDiameter {

    class Solution {
        int diameter = 0;

        public int treeDiameter(int[][] edges) {
            int n = edges.length + 1;
            LinkedList<Integer>[] adjacencyList = new LinkedList[n];
            for (int i = 0; i < n; ++i) adjacencyList[i] = new LinkedList<>();
            for (int[] edge : edges) {
                adjacencyList[edge[0]].add(edge[1]);
                adjacencyList[edge[1]].add(edge[0]);
            }
            diameter = 0;
            depth(0, -1, adjacencyList);
            return diameter;
        }

        private int depth(int root, int parent, LinkedList<Integer>[] adjacencyList) {
            int maxDepth1st = 0, maxDepth2nd = 0;
            for (int child : adjacencyList[root]) {
                if (child != parent) { // Only one way from root node to child node, don't allow child node go to root node again!
                    int childDepth = depth(child, root, adjacencyList);
                    if (childDepth > maxDepth1st) {
                        maxDepth2nd = maxDepth1st;
                        maxDepth1st = childDepth;
                    } else if (childDepth > maxDepth2nd) {
                        maxDepth2nd = childDepth;
                    }
                }
            }
            int longestPathThroughRoot = maxDepth1st + maxDepth2nd; // Sum of the top 2 highest depths is the longest path through this root
            diameter = Math.max(diameter, longestPathThroughRoot);
            return maxDepth1st + 1;
        }
    }

    class Solution_1 {
        int diameter = 0;
        public int treeDiameter(int[][] edges) {
            int n = edges.length + 1;
            List<Integer>[] adj = new List[n];
            for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for(int[] edge : edges){
                adj[edge[0]].add(edge[1]);
                adj[edge[1]].add(edge[0]);
            }
            depth(0, -1, adj);
            return diameter;
        }

        private int depth(int root, int parent, List<Integer>[] adj){
            int max1 = 0, max2 = 0;
            for(int child : adj[root]){
                if(child != parent){ // go only from parent to child not the other way round
                    int childDepth = depth(child, root, adj);
                    if(childDepth > max1){
                        max2 = max1;
                        max1 = childDepth;
                    }else if(childDepth > max2){
                        max2 = childDepth;
                    }
                }
            }
            diameter = Math.max(diameter, max1 + max2);
            return max1 + 1;
        }
    }


}
