import jdk.internal.util.xml.impl.Pair;
import sun.jvm.hotspot.utilities.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 729. My Calendar I
 * Medium
 *
 * 540
 *
 * 30
 *
 * Add to List
 *
 * Share
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 *
 * Accepted
 * 48,010
 * Submissions
 * 95,436
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
 * 5
 *
 * Microsoft
 * |
 * 2
 *
 * Intuit
 * |
 * 2
 *
 * Uber
 * |
 * 2
 * My Calendar II
 * Medium
 * My Calendar III
 * Hard
 * Store the events as a sorted list of intervals. If none of the events conflict, then the new event can be added.
 *
 */
public class MyCalendarI {

    /**
     * Approach #1: Brute Force [Accepted]
     * Intuition
     *
     * When booking a new event [start, end), check if every current event conflicts with the new event. If none of them do, we can book the event.
     *
     * Algorithm
     *
     * We will maintain a list of interval events (not necessarily sorted). Evidently, two events [s1, e1) and [s2, e2) do not conflict if and only if one of them starts after the other one ends: either e1 <= s2 OR e2 <= s1. By De Morgan's laws, this means the events conflict when s1 < e2 AND s2 < e1.
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

    public class MyCalendar {
        List<int[]> calendar;

        MyCalendar() {
            calendar = new ArrayList();
        }

        public boolean book(int start, int end) {
            for (int[] iv: calendar) {
                if (iv[0] < end && start < iv[1]) return false;
            }
            calendar.add(new int[]{start, end});
            return true;
        }
    }

     /** Approach #2: Balanced Tree [Accepted]
     * Intuition
     *
     * If we maintained our events in sorted order, we could check whether an event could be booked in O(\log N)O(logN) time (where NN is the number of events already booked) by binary searching for where the event should be placed. We would also have to insert the event in our sorted structure.
     *
     * Algorithm
     *
     * We need a data structure that keeps elements sorted and supports fast insertion. In Java, a TreeMap is the perfect candidate. In Python, we can build our own binary tree structure.
     *
     * For Java, we will have a TreeMap where the keys are the start of each interval, and the values are the ends of those intervals. When inserting the interval [start, end), we check if there is a conflict on each side with neighboring intervals: we would like calendar.get(prev)) <= start <= end <= next for the booking to be valid (or for prev or next to be null respectively.)
     *
     * For Python, we will create a binary tree. Each node represents some interval [self.start, self.end) while self.left, self.right represents nodes that are smaller or larger than the current node.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity (Java): O(N \log N)O(NlogN), where NN is the number of events booked. For each new event, we search that the event is legal in O(\log N)O(logN) time, then insert it in O(1)O(1) time.
     *
     * Time Complexity (Python): O(N^2)O(N
     * 2
     *  ) worst case, with O(N \log N)O(NlogN) on random data. For each new event, we insert the event into our binary tree. As this tree may not be balanced, it may take a linear number of steps to add each event.
     *
     * Space Complexity: O(N)O(N), the size of the data structures used.
     */

     class MyCalendar {
         TreeMap<Integer, Integer> calendar;

         MyCalendar() {
             calendar = new TreeMap();
         }

         public boolean book(int start, int end) {
             Integer prev = calendar.floorKey(start),
                     next = calendar.ceilingKey(start);
             if ((prev == null || calendar.get(prev) <= start) &&
                     (next == null || end <= next)) {
                 calendar.put(start, end);
                 return true;
             }
             return false;
         }
     }



}
