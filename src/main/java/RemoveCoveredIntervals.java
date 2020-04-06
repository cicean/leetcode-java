import java.util.*;

/**
 * 1288. Remove Covered Intervals
 * Medium
 *
 * 110
 *
 * 7
 *
 * Add to List
 *
 * Share
 * Given a list of intervals, remove all intervals that are covered by another interval in the list. Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 *
 * After doing so, return the number of remaining intervals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * intervals[i] != intervals[j] for all i != j
 * Accepted
 * 6,122
 * Submissions
 * 9,969
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * LeetCode
 * How to check if an interval is covered by another?
 * Compare each interval to all others and check if it is covered by any interval.
 */
public class RemoveCoveredIntervals {

    /**
     * Solution
     * Approach 1: Greedy Algorithm
     * Solution Pattern
     *
     * The idea of greedy algorithm is to pick the locally optimal move at each step, which would lead to the globally optimal solution.
     *
     * Typical greedy solution has \mathcal{O}(N \log N)O(NlogN) time complexity and consists of two steps:
     *
     * Figure out how to sort the input data. That would take \mathcal{O}(N \log N)O(NlogN) time, and could be done directly by sorting or indirectly by using heap data structure. Usually sorting is better than heap usage because of gain in space.
     *
     * Parse the sorted input in \mathcal{O}(N)O(N) time to construct a solution.
     *
     * In the case of already sorted input, the greedy solution could have \mathcal{O}(N)O(N) time complexity, here is an example.
     *
     * Intuition
     *
     * Let us figure out how to sort the input. The idea to sort by start point is pretty obvious, because it simplifies further parsing:
     *
     * traversal
     *
     * Let us consider two subsequent intervals after sorting. Since sorting ensures that start1 < start2, it's sufficient to compare the end boundaries:
     *
     * If end1 < end2, the intervals won't completely cover one another, though they have some overlapping.
     * traversal
     *
     * If end1 >= end2, the interval 2 is covered by the interval 1.
     * traversal
     *
     * Edge case: How to treat intervals which share start point
     *
     * We've missed an important edge case in the previous discussion: what if two intervals share the start point, i.e. start1 == start2?
     *
     * The above algorithm will fail because it cannot distinguish these two situations as follows:
     *
     * traversal
     *
     * One of the intervals is covered by another, but if we sort only by the start point, we would not know which one. Hence, we need to sort by the end point as well.
     *
     * If two intervals share the same start point, one has to put the longer interval in front.
     *
     * This way the above algorithm would work fine here as well. Moreover, it can deal with more complex cases, like the one below:
     *
     * traversal
     *
     * Algorithm
     *
     * Sort in the ascending order by the start point. If two intervals share the same start point, put the longer one to be the first.
     *
     * Initiate the number of non-covered intervals: count = 0.
     *
     * Iterate over sorted intervals and compare end points.
     *
     * If the current interval is not covered by the previous one end > prev_end, increase the number of non-covered intervals. Assign the current interval to be previous for the next step.
     *
     * Otherwise, the current interval is covered by the previous one. Do nothing.
     *
     * Return count.
     *
     * Implementation
     *
     * Current
     * 1 / 6
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N \log N)O(NlogN) since the sorting dominates the complexity of the algorithm.
     *
     * Space complexity : \mathcal{O}(1)O(1).
     */
    class Solution {
        public int removeCoveredIntervals(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // Sort by start point.
                    // If two intervals share the same start point,
                    // put the longer one to be the first.
                    return o1[0] == o2[0] ? o2[1] - o1[1]: o1[0] - o2[0];
                }
            });

            int count = 0;
            int end, prev_end = 0;
            for (int[] curr : intervals) {
                end = curr[1];
                // if current interval is not covered
                // by the previous one
                if (prev_end < end) {
                    ++count;
                    prev_end = end;
                }
            }
            return count;
        }
    }

    class Solution_1 {
        public int removeCoveredIntervals(int[][] A) {
            int res = 0, left = -1, right = -1;
            Arrays.sort(A, (a, b) -> a[0] - b[0]);
            for (int[] v : A) {
                if (v[0] > left && v[1] > right) {
                    left = v[0];
                    ++res;
                }
                right = Math.max(right, v[1]);
            }
            return res;
        }
    }
}
