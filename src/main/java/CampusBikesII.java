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
public class CampusBikesII {

    class Solution {
        public int assignBikes(int[][] workers, int[][] bikes) {
            int[] memo = new int[1 << bikes.length];
            return dfs(workers, bikes, memo, 0, 0);
        }

        private int dfs(int[][] workers, int[][] bikes, int[] memo, int index, int mask) {
            if (index == workers.length) {
                return 0;
            }

            if (memo[mask] != 0) {
                return memo[mask];
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < bikes.length; i++) {
                if ((mask & 1 << i) == 0) {
                    min = Math.min(min, dist(workers[index], bikes[i]) + dfs(workers, bikes, memo, index + 1, mask | 1 << i));
                }
            }

            memo[mask] = min;

            return min;
        }

        private int dist(int[] a, int[] b) {
            return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
        }
    }
}
