import java.util.*;

/**
 * 1272. Remove Interval
 * Medium
 *
 * 61
 *
 * 6
 *
 * Add to List
 *
 * Share
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.
 *
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 *
 * Return a sorted list of intervals after all such removals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 * Example 2:
 *
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^4
 * -10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
 * Accepted
 * 3,640
 * Submissions
 * 6,472
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
 * LeetCode
 * Solve the problem for every interval alone.
 * Divide the problem into cases according to the position of the two intervals.
 *
 */
public class RemoveInterval {

    /**
     * Solution
     * Approach 1: Sweep Line, One Pass.
     * Best Possible Time Complexity
     *
     * What is the best possible time complexity here?
     *
     * The input is sorted, that usually means at least linear time complexity. Is it possible to do \mathcal{O}(\log N)O(logN)? No, because to copy input elements into output still requires \mathcal{O}(N)O(N) time.
     *
     * Sweep Line
     *
     * Sweep Line algorithm is a sort of geometrical visualisation. Let's imagine a vertical line which is swept across the plane, stopping at some points. That could create various situations, and the decision to make depends on the stop point.
     *
     * line
     *
     * Algorithm
     *
     * Let's sweep the line by iterating over input intervals and consider what it could bring to us.
     *
     * Current interval has no overlaps with toBeRemoved one. That means there is nothing to take care about, just update the output.
     * line
     *
     * Second situation is when toBeRemoved interval is inside of the current interval. Then one has to add two non-overlapping parts of the current interval in the output.
     * line
     *
     * "Left" overlap.
     * line
     *
     * "Right" overlap.
     * line
     *
     * And here we are, all situations are covered, the job is done.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) since it's one pass along the input array.
     *
     * Space complexity : \mathcal{O}(N)O(N) to keep the output.
     */

    class Solution {
        public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
            int removeStart = toBeRemoved[0], removeEnd = toBeRemoved[1];
            List<List<Integer>> output = new ArrayList<List<Integer>>();

            for (int[] interval : intervals) {
                int start = interval[0], end = interval[1];

                if (end <= removeStart || start >= removeEnd) {
                    // if current interval ends before toBeRemoved
                    // or starts after
                    output.add(new ArrayList<Integer>() {{add(start); add(end); }});

                } else if (start < removeStart && end > removeEnd) {
                    // if the interval to be removed is inside
                    // of the current interval
                    output.add(new ArrayList<Integer>() {{add(start); add(removeStart); }});
                    output.add(new ArrayList<Integer>() {{add(removeEnd); add(end); }});

                } else if (start < removeStart && end <= removeEnd) {
                    // "left" overlap
                    output.add(new ArrayList<Integer>() {{add(start); add(removeStart); }});

                } else if (start >= removeStart && end > removeEnd) {
                    // "right" overlap
                    output.add(new ArrayList<Integer>() {{add(removeEnd); add(end); }});
                }
            }
            return output;
        }
    }
}
