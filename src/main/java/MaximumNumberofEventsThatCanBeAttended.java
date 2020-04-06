import java.util.*;

/**
 * 1353. Maximum Number of Events That Can Be Attended
 * Medium
 *
 * 209
 *
 * 29
 *
 * Add to List
 *
 * Share
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 *
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.
 *
 * Return the maximum number of events you can attend.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * Example 2:
 *
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * Example 3:
 *
 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * Output: 4
 * Example 4:
 *
 * Input: events = [[1,100000]]
 * Output: 1
 * Example 5:
 *
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 *
 *
 * Constraints:
 *
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= events[i][0] <= events[i][1] <= 10^5
 * Accepted
 * 6,549
 * Submissions
 * 20,740
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Visa
 * |
 * LeetCode
 * Sort the events by the start time and in case of tie by the end time in ascending order.
 * Loop over the sorted events. Attend as much as you can and keep the last day occupied.
 * When you try to attend new event keep in mind the first day you can attend a new event in.
 */
public class MaximumNumberofEventsThatCanBeAttended {

    public int maxEvents(int[][] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(A, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0, res = 0, n = A.length;
        for (int d = 1; d <= 100000; ++d) {
            while (!pq.isEmpty() && pq.peek() < d)
                pq.poll();
            while (i < n && A[i][0] == d)
                pq.offer(A[i++][1]);
            if (!pq.isEmpty()) {
                pq.poll();
                ++res;
            }
        }
        return res;
    }

    class Solution {
        public int maxEvents(int[][] events) {

            Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            int i = 0, n = events.length, d = 0, ans = 0;

            while(!pq.isEmpty() || i < n){

                if(pq.isEmpty())
                    d = events[i][0];

                while(i < n && events[i][0] == d)
                    pq.offer(events[i++][1]);

                pq.poll();
                ans++;
                d++;

                while(!pq.isEmpty() && pq.peek() < d){      //events are closed so remove from queue
                    pq.poll();
                }

            }
            return ans;
        }
    }

}
