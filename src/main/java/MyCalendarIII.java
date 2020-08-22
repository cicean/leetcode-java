/**
 * 732. My Calendar III
 * Hard
 *
 * 317
 *
 * 95
 *
 * Add to List
 *
 * Share
 * Implement a MyCalendarThree class to store your events. A new event can always be added.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
 *
 * For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.
 *
 * Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
 * Example 1:
 *
 * MyCalendarThree();
 * MyCalendarThree.book(10, 20); // returns 1
 * MyCalendarThree.book(50, 60); // returns 1
 * MyCalendarThree.book(10, 40); // returns 2
 * MyCalendarThree.book(5, 15); // returns 3
 * MyCalendarThree.book(5, 10); // returns 3
 * MyCalendarThree.book(25, 55); // returns 3
 * Explanation:
 * The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
 * The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
 * The remaining events cause the maximum K-booking to be only a 3-booking.
 * Note that the last event locally causes a 2-booking, but the answer is still 3 because
 * eg. [10, 20), [10, 40), and [5, 15) are still triple booked.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendarThree.book per test case will be at most 400.
 * In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].
 *
 *
 * Accepted
 * 18,881
 * Submissions
 * 32,085
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ccyjoshua
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 2
 * My Calendar I
 * Medium
 * My Calendar II
 * Medium
 * Treat each interval [start, end) as two events "start" and "end",
 * and process them in sorted order.
 */
public class MyCalendarIII {

    /**
     * Approach #1: Boundary Count [Accepted]
     * Intuition and Algorithm
     *
     * When booking a new event [start, end), count delta[start]++ and delta[end]--. When processing the values of delta in sorted order of their keys, the largest such value is the answer.
     *
     * In Python, we sort the set each time instead, as there is no analog to TreeMap available.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the number of events booked. For each new event, we traverse delta in O(N)O(N) time. In Python, this is O(N^2 \log N)O(N
     * 2
     *  logN) owing to the extra sort step.
     *
     * Space Complexity: O(N)O(N), the size of delta.
     */

    class MyCalendarThree {
        TreeMap<Integer, Integer> delta;

        public MyCalendarThree() {
            delta = new TreeMap();
        }

        public int book(int start, int end) {
            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0, ans = 0;
            for (int d: delta.values()) {
                active += d;
                if (active > ans) ans = active;
            }
            return ans;
        }
    }

    /**
     * SegmentTreeNode
     */

    class SegmentTreeNode {
        int start;
        int end;
        int booked;
        int maxBooked;
        SegmentTreeNode left;
        SegmentTreeNode right;
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.booked = 0;
            this.maxBooked = 0;
            left = null;
            right = null;
        }

        public boolean cover(SegmentTreeNode node) {
            return start >= node.start && end <= node.end;
        }

        public boolean overlapped(SegmentTreeNode node) {
            if (cover(node) || start >= node.end || end <= node.start)
                return false;
            return true;
        }
    }

    class SegmentTree {
        SegmentTreeNode root;
        public SegmentTree() {
            root = new SegmentTreeNode(0, Integer.MAX_VALUE);
        }

        public int getMaxBooked() {
            return root.maxBooked;
        }

        public void add(int start, int end) {
            add(root, start, end);
        }

        public void add(SegmentTreeNode root, int start, int end) {
            SegmentTreeNode newNode = new SegmentTreeNode(start, end);
            newNode.booked = 1;
            newNode.maxBooked = 1;
            //if (root == null) return;

            if (root.cover(newNode)) {
                root.booked++;
                root.maxBooked++;
            }

            if (root.overlapped(newNode)) {
                int mid = root.start + (root.end - root.start) / 2;
                if (root.left == null)
                    root.left = new SegmentTreeNode(root.start, mid);
                add(root.left, start, end);
                if (root.right == null)
                    root.right = new SegmentTreeNode(mid, root.end);
                add(root.right, start, end);
                root.maxBooked = Math.max(root.left.maxBooked, root.right.maxBooked) + root.booked;
            }
        }
    }

    class MyCalendarThree {
        SegmentTree st;

        public MyCalendarThree() {
            st = new SegmentTree();
        }

        public int book(int start, int end) {
            st.add(start, end);
            return st.getMaxBooked();
        }
    }
}
