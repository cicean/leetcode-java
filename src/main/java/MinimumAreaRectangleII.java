/**
 * 963. Minimum Area Rectangle II
 * Medium
 *
 * 118
 *
 * 194
 *
 * Add to List
 *
 * Share
 * Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[1,2],[2,1],[1,0],[0,1]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
 * Example 2:
 *
 *
 *
 * Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
 * Output: 1.00000
 * Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
 * Example 3:
 *
 *
 *
 * Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
 * Output: 0
 * Explanation: There is no possible rectangle to form from these points.
 * Example 4:
 *
 *
 *
 * Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.
 *
 *
 * Note:
 *
 * 1 <= points.length <= 50
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 * Accepted
 * 9,830
 * Submissions
 * 19,853
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 2
 *
 * Amazon
 * |
 * 2
 */
public class MinimumAreaRectangleII {

    /**
     * Solution
     * Approach 1: Iterate Triangles
     * Intuition
     *
     * For each triangle, let's try to find the 4th point and whether it is a rectangle.
     *
     * Algorithm
     *
     * Say the first 3 points are p1, p2, p3, and that p2 and p3 are opposite corners of the final rectangle. The 4th point must be p4 = p2 + p3 - p1 (using vector notation) because p1, p2, p4, p3 must form a parallelogram, and p1 + (p2 - p1) + (p3 - p1) = p4.
     *
     * If this point exists in our collection (we can use a HashSet to check), then we should check that the angles of this parallelogram are 90 degrees. The easiest way is to check the dot product of the two vectors (p2 - p1) and (p3 - p1). (Another way is we could normalize the vectors to length 1, and check that one equals the other rotated by 90 degrees.)
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^3)O(N
     * 3
     *  ), where NN is the length of points.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution {
        public double minAreaFreeRect(int[][] points) {
            int N = points.length;

            Point[] A = new Point[N];
            Set<Point> pointSet = new HashSet();
            for (int i = 0; i < N; ++i) {
                A[i] = new Point(points[i][0], points[i][1]);
                pointSet.add(A[i]);
            }

            double ans = Double.MAX_VALUE;
            for (int i = 0; i < N; ++i) {
                Point p1 = A[i];
                for (int j = 0; j < N; ++j) if (j != i) {
                    Point p2 = A[j];
                    for (int k = j+1; k < N; ++k) if (k != i) {
                        Point p3 = A[k];
                        Point p4 = new Point(p2.x + p3.x - p1.x, p2.y + p3.y - p1.y);

                        if (pointSet.contains(p4)) {
                            int dot = ((p2.x - p1.x) * (p3.x - p1.x) +
                                    (p2.y - p1.y) * (p3.y - p1.y));
                            if (dot == 0) {
                                double area = p1.distance(p2) * p1.distance(p3);
                                if (area < ans)
                                    ans = area;
                            }
                        }
                    }
                }
            }

            return ans < Double.MAX_VALUE ? ans : 0;
        }
    }

     /**
     * Approach 2: Iterate Centers
     * Intuition
     *
     * Consider opposite points AC and BD of a rectangle ABCD. They both have the same center O, which is the midpoint of AC and the midpoint of AB; and they both have the same radius dist(O, A) == dist(O, B) == dist(O, C) == dist(O, D). Notice that a necessary and sufficient condition to form a rectangle with two opposite pairs of points is that the points must have the same center and radius.
     *
     * Motivated by that result, let's classify each pair of points PQ by their center C = the midpoint of PQ, and the radius r = dist(P, C). Our strategy is to brute force on pairs of points with the same classification.
     *
     * Algorithm
     *
     * For each pair of points, classify them by center and radius. We only need to record one of the points P, since the other point is P' = 2 * center - P (using vector notation).
     *
     * For each center and radius, look at every possible rectangle (two pairs of points P, P', Q, Q'). The area of this rectangle dist(P, Q) * dist(P, Q') is a candidate answer.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2 \log N)O(N
     * 2
     *  logN), where NN is the length of points. It can be shown that the number of pairs of points with the same classification is bounded by \log NlogN - see this link for more.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution2 {
        public double minAreaFreeRect(int[][] points) {
            int N = points.length;
            Point[] A = new Point[N];
            for (int i = 0; i < N; ++i)
                A[i] = new Point(points[i][0], points[i][1]);

            Map<Integer, Map<Point, List<Point>>> seen = new HashMap();
            for (int i = 0; i < N; ++i)
                for (int j = i+1; j < N; ++j) {
                    // center is twice actual to keep integer precision
                    Point center = new Point(A[i].x + A[j].x, A[i].y + A[j].y);

                    int r2 = (A[i].x - A[j].x) * (A[i].x - A[j].x);
                    r2 += (A[i].y - A[j].y) * (A[i].y - A[j].y);
                    if (!seen.containsKey(r2))
                        seen.put(r2, new HashMap<Point, List<Point>>());
                    if (!seen.get(r2).containsKey(center))
                        seen.get(r2).put(center, new ArrayList<Point>());
                    seen.get(r2).get(center).add(A[i]);
                }

            double ans = Double.MAX_VALUE;
            for (Map<Point, List<Point>> info: seen.values()) {
                for (Point center: info.keySet()) {  // center is twice actual
                    List<Point> candidates = info.get(center);
                    int clen = candidates.size();
                    for (int i = 0; i < clen; ++i)
                        for (int j = i+1; j < clen; ++j) {
                            Point P = candidates.get(i);
                            Point Q = candidates.get(j);
                            Point Q2 = new Point(center);
                            Q2.translate(-Q.x, -Q.y);
                            double area = P.distance(Q) * P.distance(Q2);
                            if (area < ans)
                                ans = area;
                        }
                }
            }

            return ans < Double.MAX_VALUE ? ans : 0;
        }
    }

    class Solution3 {
        public double minAreaFreeRect(int[][] points) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for(int[] point : points) {
                if(!map.containsKey(point[0])) {
                    map.put(point[0], new HashSet<>());
                }
                map.get(point[0]).add(point[1]);
            }

            int n = points.length;
            double res = Double.MAX_VALUE;

            for(int i=0; i<n; i++) {
                for(int j=i+1; j<n; j++) {
                    // p2-p1
                    int dx1 = points[j][0] - points[i][0];
                    int dy1 = points[j][1] - points[i][1];

                    for(int k=j+1; k<n; k++) {
                        //p3 - p1
                        int dx2 = points[k][0] - points[i][0];
                        int dy2 = points[k][1] - points[i][1];
                        //check two side on rectangle are orthogonal.
                        if(dx1*dx2 + dy1*dy2 != 0) {
                            continue;
                        }
                        // For a rectangle, formed with vertices (x1,y1), (x2,y2),
                        // (x3,y3), (x4,y4), assuming these are adjacent to each other,
                        //  x1 + x3 = x2 + x4 and y1 + y3 = y2 + y4
                        int x4 = dx1 + points[k][0];
                        int y4 = dy1 + points[k][1];
                        //check (x4, y4) exist
                        if(map.containsKey(x4) && map.get(x4).contains(y4)) {
                            double area = Math.sqrt(dx1*dx1 + dy1*dy1) * Math.sqrt(dx2*dx2 + dy2*dy2);
                            res = Math.min(res, area);
                        }
                    }
                }
            }
            return res == Double.MAX_VALUE ? 0 : res;
        }
    }

}
