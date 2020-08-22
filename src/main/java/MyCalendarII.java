/**
 * 731. My Calendar II
 * Medium
 *
 * 545
 *
 * 75
 *
 * Add to List
 *
 * Share
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 *
 * Accepted
 * 37,147
 * Submissions
 * 76,899
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ccyjoshua
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * My Calendar I
 * Medium
 * My Calendar III
 * Hard
 * Store two sorted lists of intervals: one list will be all times that are at least single booked,
 * and another list will be all times that are definitely double booked.
 * If none of the double bookings conflict, then the booking will succeed,
 * and you should update your single and double bookings accordingly.
 */
public class MyCalendarII {

    /**
     * Approach #1: Brute Force [Accepted]
     * Intuition
     *
     * Maintain a list of bookings and a list of double bookings. When booking a new event [start, end), if it conflicts with a double booking, it will have a triple booking and be invalid. Otherwise, parts that overlap the calendar will be a double booking.
     *
     * Algorithm
     *
     * Evidently, two events [s1, e1) and [s2, e2) do not conflict if and only if one of them starts after the other one ends: either e1 <= s2 OR e2 <= s1. By De Morgan's laws, this means the events conflict when s1 < e2 AND s2 < e1.
     *
     * If our event conflicts with a double booking, it's invalid. Otherwise, we add conflicts with the calendar to our double bookings, and add the event to our calendar.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the number of events booked. For each new event, we process every previous event to decide whether the new event can be booked. This leads to \sum_k^N O(k) = O(N^2)∑
     * k
     * N
     * ​
     *  O(k)=O(N
     * 2
     *  ) complexity.
     *
     * Space Complexity: O(N)O(N), the size of the calendar.
     */

    public class MyCalendarTwo {
        List<int[]> calendar;
        List<int[]> overlaps;

        MyCalendarTwo() {
            calendar = new ArrayList();
        }

        public boolean book(int start, int end) {
            for (int[] iv: overlaps) {
                if (iv[0] < end && start < iv[1]) return false;
            }
            for (int[] iv: calendar) {
                if (iv[0] < end && start < iv[1])
                    overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
            }
            calendar.add(new int[]{start, end});
            return true;
        }
    }
     /** Approach #2: Boundary Count [Accepted]
     * Intuition and Algorithm
     *
     * When booking a new event [start, end), count delta[start]++ and delta[end]--. When processing the values of delta in sorted order of their keys, the running sum active is the number of events open at that time. If the sum is 3 or more, that time is (at least) triple booked.
     *
     * A Python implementation was not included for this approach because there is no analog to TreeMap available.
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the number of events booked. For each new event, we traverse delta in O(N)O(N) time.
     *
     * Space Complexity: O(N)O(N), the size of delta.
     */

     class MyCalendarTwo {
         TreeMap<Integer, Integer> delta;

         public MyCalendarTwo() {
             delta = new TreeMap();
         }

         public boolean book(int start, int end) {
             delta.put(start, delta.getOrDefault(start, 0) + 1);
             delta.put(end, delta.getOrDefault(end, 0) - 1);

             int active = 0;
             for (int d: delta.values()) {
                 active += d;
                 if (active >= 3) {
                     delta.put(start, delta.get(start) - 1);
                     delta.put(end, delta.get(end) + 1);
                     if (delta.get(start) == 0)
                         delta.remove(start);
                     return false;
                 }
             }
             return true;
         }
     }
}
