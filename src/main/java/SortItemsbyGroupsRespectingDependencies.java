/**
 * 1203. Sort Items by Groups Respecting Dependencies
 * Hard
 *
 * 160
 *
 * 35
 *
 * Add to List
 *
 * Share
 * There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.
 *
 * Return a sorted list of the items such that:
 *
 * The items that belong to the same group are next to each other in the sorted list.
 * There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
 * Return any solution if there is more than one solution and return an empty list if there is no solution.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * Output: [6,3,4,1,5,2,0,7]
 * Example 2:
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * Output: []
 * Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
 *
 *
 * Constraints:
 *
 * 1 <= m <= n <= 3*10^4
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m-1
 * 0 <= beforeItems[i].length <= n-1
 * 0 <= beforeItems[i][j] <= n-1
 * i != beforeItems[i][j]
 * beforeItems[i] does not contain duplicates elements.
 * Accepted
 * 4,203
 * Submissions
 * 9,032
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * Think of it as a graph problem.
 * We need to find a topological order on the dependency graph.
 * Build two graphs, one for the groups and another for the items.
 */
public class SortItemsbyGroupsRespectingDependencies {
    class Solution {
        public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
            List<Integer> itemResult = new ArrayList<Integer>();
            int[] visited = new int[n];
            for (int i = 0; i < n; i++) {
                if (!dfs(beforeItems, visited, itemResult, i)) return new int[0];
            }

            for (int i = 0; i < n; i++) {
                if(group[i] == -1) group[i] = m++;
            }

            List<List<Integer>> groupGraph = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                groupGraph.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                for (int j : beforeItems.get(i)) {
                    if (group[i] != group[j]) {
                        groupGraph.get(group[i]).add(group[j]);
                    }
                }
            }
            List<Integer> groupResult = new ArrayList<>();
            int[] groupVisited = new int[m];
            for (int i = 0; i < m; i++) {
                if (!dfs(groupGraph, groupVisited, groupResult, i)) return new int[0];
            }

            List<Integer>[] organizedItems = new ArrayList[m];
            for (int i = 0; i < m; i++) {
                organizedItems[i] = new ArrayList<>();
            }
            for (int i : itemResult) {
                organizedItems[group[i]].add(i);
            }

            int[] result = new int[n];
            int index = 0;
            while (index < n) {
                for (int groupId : groupResult) {
                    for (int item : organizedItems[groupId]) {
                        result[index] = item;
                        index++;
                    }
                }
            }
            return result;
        }

        private boolean dfs(List<List<Integer>> graph, int[] visited, List<Integer> res, int index) {
            if (visited[index] == 1) return false;
            if (visited[index] == 2) return true;

            visited[index] = 1;//visiting
            List<Integer> cur = graph.get(index);
            for(int i:cur){
                if(visited[i] == 1) return false;
                if(visited[i] == 0)
                    if(!dfs(graph, visited, res, i)) return false;
            }
            visited[index] = 2;//finished visiting

            res.add(index);//all index before this one was added

            return true;
        }

    }
}
