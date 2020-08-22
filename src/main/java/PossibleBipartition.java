/**
 * 886. Possible Bipartition
 * Medium
 *
 * 427
 *
 * 17
 *
 * Add to List
 *
 * Share
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * Example 2:
 *
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Note:
 *
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 * Accepted
 * 20,666
 * Submissions
 * 48,399
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
 */
public class PossibleBipartition {

    /**
     * Solution
     * Approach 1: Depth-First Search
     * Intuition
     *
     * It's natural to try to assign everyone to a group. Let's say people in the first group are red, and people in the second group are blue.
     *
     * If the first person is red, anyone disliked by this person must be blue. Then, anyone disliked by a blue person is red, then anyone disliked by a red person is blue, and so on.
     *
     * If at any point there is a conflict, the task is impossible, as every step logically follows from the first step. If there isn't a conflict, then the coloring was valid, so the answer would be true.
     *
     * Algorithm
     *
     * Consider the graph on N people formed by the given "dislike" edges. We want to check that each connected component of this graph is bipartite.
     *
     * For each connected component, we can check whether it is bipartite by just trying to coloring it with two colors. How to do this is as follows: color any node red, then all of it's neighbors blue, then all of those neighbors red, and so on. If we ever color a red node blue (or a blue node red), then we've reached a conflict.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N + E)O(N+E), where EE is the length of dislikes.
     *
     * Space Complexity: O(N + E)O(N+E).
     */

    class Solution {
        ArrayList<Integer>[] graph;
        Map<Integer, Integer> color;

        public boolean possibleBipartition(int N, int[][] dislikes) {
            graph = new ArrayList[N+1];
            for (int i = 1; i <= N; ++i)
                graph[i] = new ArrayList();

            for (int[] edge: dislikes) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }

            color = new HashMap();
            for (int node = 1; node <= N; ++node)
                if (!color.containsKey(node) && !dfs(node, 0))
                    return false;
            return true;
        }

        public boolean dfs(int node, int c) {
            if (color.containsKey(node))
                return color.get(node) == c;
            color.put(node, c);

            for (int nei: graph[node])
                if (!dfs(nei, c ^ 1))
                    return false;
            return true;
        }
    }

    class Solution {
        public boolean possibleBipartition(int N, int[][] dislikes) {
            int[] colors = new int[N + 1];
            for(int i = 1; i <= N; ++i) colors[i] = i;

            for(int i = 0; i < dislikes.length; ++i) {
                int p1 = dislikes[i][0], p2 = dislikes[i][1];
                if(colors[p2] == p2) colors[p2] = p1;
                else {
                    int[] uf1 = find(p1, colors), uf2 = find(p2, colors);
                    if(uf1[0] == uf2[0] && uf1[1] == uf2[1]) return false;
                }
            }
            return true;
        }

        private int[] find(int p, int[] colors) {
            int color = 0;
            while(colors[p] != p) {
                p = colors[p];
                color = color == 0 ? 1 : 0;
            }
            return new int[] {p, color};
        }
    }

    class Solution {

        public boolean possibleBipartition(int N, int[][] dislikes) {
            int[] another = new int[N+1];
            for (int i = 0; i <= N; i++) {
                another[i] = i; // initial
            }

            for (int[] dislike : dislikes) {
                int a = dislike[0];
                int b = dislike[1];

                if (another[a] == a && another[b] == b) {
                    another[a] = b;
                    another[b] = a;
                } else if (another[a] == a && another[b] != b) {
                    another[a] = another[another[b]];
                } else if (another[b] ==b && another[a] != a) {
                    another[b] = another[another[a]];
                } else if (another[b] == another[a]) {
                    return false;
                }
            }

            return true;
        }
    }

}
