import java.util.*;

/**
 * Created by cicean on 8/29/2016.
 * 323. Number of Connected Components in an Undirected Graph  QuestionEditorial Solution  My Submissions
 Total Accepted: 11788 Total Submissions: 26579 Difficulty: Medium
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

 Example 1:
 0          3
 |          |
 1 --- 2    4
 Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

 Example 2:
 0           4
 |           |
 1 --- 2 --- 3
 Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

 Note:
 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 Hide Company Tags Google Twitter
 Hide Tags Depth-first Search Breadth-first Search Union Find Graph
 Hide Similar Problems (M) Number of Islands (M) Graph Valid Tree

 */
public class NumberofConnectedComponentsinanUndirectedGraph {

    /**
     * 典型且很基础的union find题。用一个数组记录各个数字的父节点，
     * 然后遍历图，对edge中两个端点做union。最后扫一遍数组，找到根节点个数即可。
     */

    //time: O(m*h), space: O(n), m表示edge的数量。
    public class Solution {
        public int countComponents(int n, int[][] edges) {
            int[] id = new int[n];

            // 初始化
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }

            // union
            for (int[] edge : edges) {
                int i = root(id, edge[0]);
                int j = root(id, edge[1]);
                id[i] = j;
            }

            // 统计根节点个数
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (id[i] == i)
                    count++;
            }
            return count;
        }

        // 找根节点
        public int root(int[] id, int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
    }

    //dfs
    public class Solutiondfs {
        public int countComponents(int n, int[][] edges) {
            if (n <= 1)
                return n;
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(i, new ArrayList<>());
            }
            for (int[] edge : edges) {
                map.get(edge[0]).add(edge[1]);
                map.get(edge[1]).add(edge[0]);
            }
            Set<Integer> visited = new HashSet<>();
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (visited.add(i)) {
                    dfsVisit(i, map, visited);
                    count++;
                }
            }
            return count;
        }

        private void dfsVisit(int i, Map<Integer, List<Integer>> map, Set<Integer> visited) {
            for (int j : map.get(i)) {
                if (visited.add(j))
                    dfsVisit(j, map, visited);
            }
        }
    }
}
