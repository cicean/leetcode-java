import java.util.*;

/**
 * 939. Minimum Area Rectangle
 * Medium
 *
 * 484
 *
 * 92
 *
 * Add to List
 *
 * Share
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 *
 * Note:
 *
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 * Accepted
 * 36,740
 * Submissions
 * 70,587
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
 * 8
 *
 * Amazon
 * |
 * 5
 *
 * Nvidia
 * |
 * 2
 */
public class MinimumAreaRectangle {
    /**
     * Approach 1: Sort by Column
     * Intuition
     *
     * Count each rectangle by right-most edge.
     *
     * Algorithm
     *
     * Group the points by x coordinates, so that we have columns of points.
     * Then, for every pair of points in a column (with coordinates (x,y1) and (x,y2)),
     * check for the smallest rectangle with this pair of points as the rightmost edge.
     * We can do this by keeping memory of what pairs of points we've seen before.
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of points.
     *
     * Space Complexity: O(N)O(N).
     */
    class Solution {
        public int minAreaRect(int[][] points) {
            Map<Integer, List<Integer>> rows = new TreeMap();
            for (int[] point: points) {
                int x = point[0], y = point[1];
                rows.computeIfAbsent(x, z-> new ArrayList()).add(y);
            }

            int ans = Integer.MAX_VALUE;
            Map<Integer, Integer> lastx = new HashMap();
            for (int x: rows.keySet()) {
                List<Integer> row = rows.get(x);
                Collections.sort(row);
                for (int i = 0; i < row.size(); ++i)
                    for (int j = i+1; j < row.size(); ++j) {
                        int y1 = row.get(i), y2 = row.get(j);
                        int code = 40001 * y1 + y2;
                        if (lastx.containsKey(code))
                            ans = Math.min(ans, (x - lastx.get(code)) * (y2-y1));
                        lastx.put(code, x);
                    }
            }

            return ans < Integer.MAX_VALUE ? ans : 0;
        }
    }

    class Solution_1 {
        public int minAreaRect(int[][] points) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (int[] p : points) {
                if (!map.containsKey(p[0])) {
                    map.put(p[0], new HashSet<>());
                }
                map.get(p[0]).add(p[1]);
            }
            int min = Integer.MAX_VALUE;
            for (int[] p1 : points) {
                for (int[] p2 : points) {
                    if (p1[0] == p2[0] || p1[1] == p2[1]) { // if have the same x or y
                        continue;
                    }
                    if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) { // find other two points
                        min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                    }
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }

    /**
     * Approach 2: Count by Diagonal
     * Intuition
     *
     * For each pair of points in the array, consider them to be the long diagonal of a potential rectangle. We can check if all 4 points are there using a Set.
     *
     * For example, if the points are (1, 1) and (5, 5), we check if we also have (1, 5) and (5, 1). If we do, we have a candidate rectangle.
     *
     * Algorithm
     *
     * Put all the points in a set. For each pair of points, if the associated rectangle are 4 distinct points all in the set, then take the area of this rectangle as a candidate answer.
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of points.
     *
     * Space Complexity: O(N)O(N), where HH is the height of the tree.
     */

    class Solution_2 {
        public int minAreaRect(int[][] points) {
            Set<Integer> pointSet = new HashSet();
            for (int[] point: points)
                pointSet.add(40001 * point[0] + point[1]);

            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < points.length; ++i)
                for (int j = i+1; j < points.length; ++j) {
                    if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                        if (pointSet.contains(40001 * points[i][0] + points[j][1]) &&
                                pointSet.contains(40001 * points[j][0] + points[i][1])) {
                            ans = Math.min(ans, Math.abs(points[j][0] - points[i][0]) *
                                    Math.abs(points[j][1] - points[i][1]));
                        }
                    }
                }

            return ans < Integer.MAX_VALUE ? ans : 0;
        }
    }

    //
    public int minAreaRect(int[][] points)
    {
        int min = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points)
        {
            map.putIfAbsent(p[0], new HashSet<>());
            map.get(p[0]).add(p[1]);
        }

        for (int i = 0; i < points.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                int[] p1 = points[i];
                int[] p2 = points[j];

                if (p1[0] == p2[0] || p1[1] == p2[1])
                    continue ;

                // first calculate and then check whether valid
                int tempArea =  Math.abs(p1[0]-p2[0]) * Math.abs(p1[1]-p2[1]);
                if (tempArea < min)
                {
                    if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1]))
                        min = Math.min(min, tempArea);
                }

            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

