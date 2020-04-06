import java.util.*;

/**
 * 855. Exam Room
 * Medium
 *
 * 479
 *
 * 199
 *
 * Add to List
 *
 * Share
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
 *
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
 *
 *
 *
 * Example 1:
 *
 * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student sits at the last seat number 5.
 * ​​​​​​​
 *
 * Note:
 *
 * 1 <= N <= 10^9
 * ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 * Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 * Accepted
 * 24.3K
 * Submissions
 * 58.7K
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Quip (Salesforce)
 * |
 * 4
 *
 * Quora
 * |
 * 4
 *
 * Google
 * |
 * 3
 *
 * Uber
 * |
 * 2
 *
 * Facebook
 * |
 * 2
 * Maximize Distance to Closest Person
 * Easy
 */
public class ExamRoom {

    /**
     * Approach 1: Maintain Sorted Positions
     * Intuition
     *
     * We'll maintain ExamRoom.students, a sorted list (or TreeSet in Java) of positions the students are currently seated in.
     *
     * Algorithm
     *
     * The ExamRoom.leave(p) operation is clear - we will just list.remove (or TreeSet.remove) the student from ExamRoom.students.
     *
     * Let's focus on the ExamRoom.seat() : int operation. For each pair of adjacent students i and j, the maximum distance to the closest student is d = (j - i) / 2, achieved in the left-most seat i + d. Otherwise, we could also sit in the left-most seat, or the right-most seat.
     *
     * Finally, we should handle the case when there are no students separately.
     *
     * For more details, please review the comments made in the implementations.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: Each seat operation is O(P)O(P), (where PP is the number of students sitting), as we iterate through every student. Each leave operation is O(P)O(P) (\log PlogP in Java).
     *
     * Space Complexity: O(P)O(P), the space used to store the positions of each student sitting.
     *
     */

    class Solution {
        int N;
        TreeSet<Integer> students;

        public Solution(int N) {
            this.N = N;
            students = new TreeSet();
        }

        public int seat() {
            //Let's determine student, the position of the next
            //student to sit down.
            int student = 0;
            if (students.size() > 0) {
                //Tenatively, dist is the distance to the closest student,
                //which is achieved by sitting in the position 'student'.
                //We start by considering the left-most seat.
                int dist = students.first();
                Integer prev = null;
                for (Integer s: students) {
                    if (prev != null) {
                        //For each pair of adjacent students in positions (prev, s),
                        //d is the distance to the closest student;
                        //achieved at position prev + d.
                        int d = (s - prev) / 2;
                        if (d > dist) {
                            dist = d;
                            student = prev + d;
                        }
                    }
                    prev = s;
                }

                //Considering the right-most seat.
                if (N - 1 - students.last() > dist)
                    student = N - 1;
            }

            //Add the student to our sorted TreeSet of positions.
            students.add(student);
            return student;
        }

        public void leave(int p) {
            students.remove(p);
        }
    }

    class ExamRoom1 {
        class Interval {
            int start;
            int end;
            int length;
            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
                if (start == 0 || end == capacity - 1) length = end - start;
                else length = (end - start) / 2;
            }
        }

        private PriorityQueue<Interval> pq;
        private int capacity;

        public ExamRoom1(int N) {
            this.pq = new PriorityQueue<>((a, b) -> (a.length == b.length ? a.start - b.start : b.length - a.length));
            this.capacity = N;
            pq.offer(new Interval(0, N - 1));
        }

        public int seat() {
            int seat;
            Interval in = pq.poll();
            if (in.start == 0) seat = 0;
            else if (in.end == capacity - 1) seat = capacity - 1;
            else seat = in.start + in.length;
            if (seat > in.start) pq.offer(new Interval(in.start, seat - 1));
            if (seat < in.end) pq.offer(new Interval(seat + 1, in.end));
            return seat;
        }

        public void leave(int p) {
            List<Interval> list = new ArrayList<>(pq);
            Interval prev = null, second = null;
            for (Interval in: list) {
                if (in.end + 1 == p) prev = in;
                if (p + 1 == in.start) second = in;
            }
            pq.remove(prev);
            pq.remove(second);
            pq.offer(new Interval(prev == null ? p : prev.start, second == null ? p : second.end));
        }
    }

}
