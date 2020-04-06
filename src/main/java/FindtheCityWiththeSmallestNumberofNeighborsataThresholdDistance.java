/**
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * Medium
 *
 * 138
 *
 * 12
 *
 * Add to List
 *
 * Share
 * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
 *
 * Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
 *
 * Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * Output: 3
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 4 for each city are:
 * City 0 -> [City 1, City 2]
 * City 1 -> [City 0, City 2, City 3]
 * City 2 -> [City 0, City 1, City 3]
 * City 3 -> [City 1, City 2]
 * Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
 * Example 2:
 *
 *
 *
 * Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * Output: 0
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 2 for each city are:
 * City 0 -> [City 1]
 * City 1 -> [City 0, City 4]
 * City 2 -> [City 3, City 4]
 * City 3 -> [City 2, City 4]
 * City 4 -> [City 1, City 2, City 3]
 * The city 0 has 1 neighboring city at a distanceThreshold = 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti, distanceThreshold <= 10^4
 * All pairs (fromi, toi) are distinct.
 * Accepted
 * 6,161
 * Submissions
 * 14,858
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Uber
 * |
 * LeetCode
 * Use Floyd-Warshall's algorithm to compute any-point to any-point distances. (Or can also do Dijkstra from every node due to the weights are non-negative).
 * For each city calculate the number of reachable cities within the threshold, then search for the optimal city.
 *
 */
public class FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance {
    /**
     * Explanation
     * Becasue O(N^3) is accepted in this problem, we don't need a very fast solution.
     * we can simply use Floyd algorithm to find the minium distance any two cities.
     *
     * Reference Floyd–Warshall algorithm
     *
     * I first saw @awice using it long time ago.
     * It's really easy and makes a lot sense.
     *
     * Iterate all point middle point k,
     * iterate all pairs (i,j).
     * If it go through the middle point k,
     * dis[i][j] = dis[i][k] + dis[k][j].
     *
     *
     * Complexity
     * Time O(N^3)
     * Space O(N^2)
     */




    class Solution {
        /**
         * Solution1: Floyd-Warshall
         * All pair shortest path
         *
         * Time complexity: O(n^3)
         * Space complexity: O(n^2)
         * @param n
         * @param edges
         * @param distanceThreshold
         * @return
         */

        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            int[][] dp = new int[n][Integer.MAX_VALUE / 2];
            for (int [] e : edges) {
                dp[e[0]][e[1]] = dp[e[1]][0] = e[2];
            }

            for (int k = 0; k < n; k++) {
                for (int u = 0; u < n; u++) {
                    for (int v = 0; v < n; v++) {
                        dp[u][v] = Math.min(dp[u][v], dp[u][k] + dp[k][v]);
                    }
                }
            }

            int ans = -1;
            int min_nb =  Integer.MIN_VALUE;
            for (int u = 0; u < n; u++) {
                int nb = 0;
                for (int v = 0; v < n; v++) {
                    if (v != u && dp[u][v] <= distanceThreshold) {
                        nb++;
                    }
                }
                if (nb <= min_nb) {
                    min_nb = nb;
                    ans = u;
                }
            }
            return ans;
        }
    }


    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dis = new int[n][n];
        int res = 0, smallest = n;
        for (int[] row : dis)
            Arrays.fill(row, 10001);
        for (int[] e : edges)
            dis[e[0]][e[1]] = dis[e[1]][e[0]] = e[2];
        for (int i = 0; i < n; ++i)
            dis[i][i] = 0;
        for (int k = 0; k < n; ++k)
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; ++j)
                if (dis[i][j] <= distanceThreshold)
                    ++count;
            if (count <= smallest) {
                res = i;
                smallest = count;
            }
        }
        return res;
    }

    class Solution {
        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            int[][] dMtx = new int[n][n];
            for(int[] edg: edges){
                if(edg[2] > distanceThreshold)continue;
                dMtx[edg[0]][edg[1]] = edg[2];
                dMtx[edg[1]][edg[0]] = edg[2];
            }
            for(int city = 0;city<n;city++){
                for(int i = 0; i<n;i++){
                    for(int j = i+1; j<n;j++){
                        if(city == i || city == j || dMtx[city][i] == 0 || dMtx[city][j] == 0 )continue;
                        int distance = dMtx[city][i] + dMtx[city][j];
                        if(distance <= distanceThreshold && (dMtx[i][j]==0 || (dMtx[i][j] > 0 && dMtx[i][j] > distance ))){
                            dMtx[i][j] = distance;
                            dMtx[j][i] = distance;
                        }
                    }
                }
            }
            int[] res = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
            int i = 0;
            for(int[] mtx : dMtx){
                int tmp = 0;
                for(int m:mtx){
                    if(m>0)tmp++;
                }
                if(tmp<=res[1]){
                    res[0] = i;
                    res[1] = tmp;
                }
                i++;
            }
            return res[0];
        }
    }
}
