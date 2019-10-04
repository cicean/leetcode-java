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

/**
 * 1066. Campus Bikes II
 * Medium
 *
 * 171
 *
 * 7
 *
 * Favorite
 *
 * Share
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: 6
 * Explanation:
 * We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: 4
 * Explanation:
 * We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
 *
 *
 * Note:
 *
 * 0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 10
 * Accepted
 * 7,028
 * Submissions
 * 13,528
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
 * 7
 *
 * Amazon
 * |
 * 3
 * Campus Bikes
 * Medium
 * Model the problem with a dp(pos, mask) where pos represents the current bike to be assigned and mask the set of available workers.
 */

public class CampusBikes {

    /*
    As the question states, there are n workers and m bikes, and m > n.
We are able to solve this question using a greedy approach.

initiate a priority queue of bike and worker pairs. The heap order should be Distance ASC, WorkerIndex ASC, Bike ASC
Loop through all workers and bikes, calculate their distance, and then throw it to the queue.
Initiate a set to keep track of the bikes that have been assigned.
initiate a result array and fill it with -1. (unassigned)
poll every possible pair from the priority queue and check if the person already got his bike or the bike has been assigned.
early exist on every people got their bike.
This is my first post on LeetCode.
Let me know if you got any questions :D
     */

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;

        // order by Distance ASC, WorkerIndex ASC, BikeIndex ASC
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> {
            int comp = Integer.compare(a[0], b[0]);
            if (comp == 0) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]);
                }

                return Integer.compare(a[1], b[1]);
            }

            return comp;
        });

        // loop through every possible pairs of bikes and people,
        // calculate their distance, and then throw it to the pq.
        for (int i = 0; i < workers.length; i++) {

            int[] worker = workers[i];
            for (int j = 0; j < bikes.length; j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);
                q.add(new int[]{dist, i, j});
            }
        }

        // init the result array with state of 'unvisited'.
        int[] res = new int[n];
        Arrays.fill(res, -1);

        // assign the bikes.
        Set<Integer> bikeAssigned = new HashSet<>();
        while (bikeAssigned.size() < n) {
            int[] workerAndBikePair = q.poll();
            if (res[workerAndBikePair[1]] == -1
                    && !bikeAssigned.contains(workerAndBikePair[2])) {

                res[workerAndBikePair[1]] = workerAndBikePair[2];
                bikeAssigned.add(workerAndBikePair[2]);
            }
        }

        return res;
    }


    private int min = Integer.MAX_VALUE;
    public int assignBikesII(int[][] workers, int[][] bikes) {
        dfs(new boolean[bikes.length], workers, 0, bikes, 0);
        return min;
    }
    public void dfs(boolean[] visit, int[][] workers, int i, int[][] bikes, int distance) {
        if (i >= workers.length) {
            min = Math.min(distance, min);
            return ;
        }
        if (distance > min) {
            return ;
        }
        for (int j = 0; j < bikes.length; j++) {
            if (visit[j]) {
                continue;
            }
            visit[j] = true;
            dfs(visit, workers, i + 1, bikes, distance + dis(bikes[j], workers[i]));
            visit[j] = false;
        }

    }
    public int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
