import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by cicean on 9/19/2018.
 */
public class EmployeeFreeTime {

    /**
     * 759. Employee Free Time
     * We are given a list schedule of employees, which represents the working time for each employee.

     Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

     Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

     Example 1:
     Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
     Output: [[3,4]]
     Explanation:
     There are a total of three employees, and all common
     free time intervals would be [-inf, 1], [3, 4], [10, inf].
     We discard any intervals that contain inf as they aren't finite.
     Example 2:
     Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
     Output: [[5,6],[7,9]]
     (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

     Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

     Note:

     schedule and schedule[i] are lists with lengths in range [1, 50].
     0 <= schedule[i].start < schedule[i].end <= 10^8.
     */

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        if (schedule == null || schedule.size() < 2 ) {
            return new ArrayList<Interval>();
        }

        List<Interval> results = new ArrayList<>();
        PriorityQueue<Interval> queue = new PriorityQueue<Interval>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });


        for (int i = 0; i < schedule.size(); i++) {
            for (int j = 0; j < schedule.get(i).size(); j++){
                queue.add(schedule.get(i).get(j));
            }
        }
       Interval last = queue.poll();
       while (!queue.isEmpty()) {
           Interval current = queue.poll();
           if (current.start > last.end ) {
               results.add(new Interval(last.end, current.start));
               last = current;
           } else {
               last = new Interval(last.start, Math.max(last.end, current.end));
           }
       }

        return results;
    }

}
