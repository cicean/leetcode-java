import java.util.Arrays;

/**
 * 252	Meeting Rooms 	33.8%	Easy
 * @author cicean
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,

Given [[0, 30],[5, 10],[15, 20]],
return false.
 */
public class MeetingRooms {

	public boolean canAttendMeetings(Interval[] intervals) {
		assert intervals != null : "null input";
		Arrays.sort(intervals, (o1,o2) -> {
			int r = o1.start - o2.start;
			return r == 0 ? o1.end - o2.end : r;
		});
		
		for (int i = 1; i < intervals.length; i++) {
			Interval i1 = intervals[i - 1];
			Interval i2 = intervals[i];
			if (i1.end > i2.start) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interval i1 = new Interval(0,30);
		Interval i2 = new Interval(5,10);
		Interval i3 = new Interval(15,20);
		Interval[] intervals = new Interval[] {i1,i2,i3};
		MeetingRooms slt = new MeetingRooms();
		System.out.println(slt.canAttendMeetings(intervals));
		
	}

}
