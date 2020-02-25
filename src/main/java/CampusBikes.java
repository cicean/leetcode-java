/**
 * 1057. Campus Bikes
 * Medium
 *
 * 163
 *
 * 30
 *
 * Favorite
 *
 * Share
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation:
 * Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation:
 * Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 *
 *
 * Note:
 *
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 1000
 * Accepted
 * 9,769
 * Submissions
 * 16,933
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * karthikbuddha
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 21
 * Campus Bikes II
 * Medium
 * Sort the elements by distance. In case of a tie, sort them by the index of the worker. After that, if there are still ties, sort them by the index of the bike.
 *
 * Can you do this in less than O(nlogn) time, where n is the total number of pairs between workers and bikes?
 *
 * Loop the sorted elements and match each pair of worker and bike if the given worker and bike where not used.
 */

import java.util.*;



public class CampusBikes {

    class Solution {
        public int[] assignBikes(int[][] workers, int[][] bikes) {
            // Note max dist is 2000, so it is good to use bucket
            List<int[]>[] buckets = new ArrayList[2001];   // int[]{worker_id, bike_id}
            int N = workers.length, M = bikes.length;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int dist = Math.abs(workers[i][0]-bikes[j][0]) + Math.abs(workers[i][1]-bikes[j][1]);
                    if (buckets[dist] == null)
                        buckets[dist] = new ArrayList<int[]>();
                    buckets[dist].add(new int[]{i, j});
                }
            }

            int[] res = new int[N];
            Arrays.fill(res, -1);
            boolean[] bikesAssigned = new boolean[M];
            for (int d = 0; d <= 2000; d++) {
                if (buckets[d] == null) continue;
                for (int k = 0; k < buckets[d].size(); k++) {
                    int[] pair = buckets[d].get(k);
                    int workerId = pair[0], bikeId = pair[1];
                    if (res[workerId] == -1 && bikesAssigned[bikeId] == false) {
                        res[workerId] = bikeId;
                        bikesAssigned[bikeId] = true;
                    }
                }
            }

            return res;
        }
    }


}
