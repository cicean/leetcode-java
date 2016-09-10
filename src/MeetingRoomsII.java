import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253	Meeting Rooms II 	26.6%	Medium
 * @author cicean
 * LeetCode: Meeting Rooms
AUG 7 2015

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,

Given [[0, 30],[5, 10],[15, 20]],
return 2.
 */
public class MeetingRoomsII {
	
	/**
	 * 复杂度
时间 O(NlogN) 空间 O(1)

思路
这题的思路和Rearrange array to certain distance很像，
我们要用贪心法，即从第一个时间段开始，选择下一个最近不冲突的时间段，
再选择下一个最近不冲突的时间段，直到没有更多。
然后如果有剩余时间段，开始为第二个房间安排，选择最早的时间段，
再选择下一个最近不冲突的时间段，直到没有更多，如果还有剩余时间段，
则开辟第三个房间，以此类推。这里的技巧是我们不一定要遍历这么多遍，
我们实际上可以一次遍历的时候就记录下，比如第一个时间段我们放入房间1，
然后第二个时间段，如果和房间1的结束时间不冲突，就放入房间1，否则开辟一个房间2。
然后第三个时间段，如果和房间1或者房间2的结束时间不冲突，就放入房间1或者2，否则开辟一个房间3，依次类推，最后统计开辟了多少房间。
对于每个房间，我们只要记录其结束时间就行了，这里我们查找不冲突房间时，只要找结束时间最早的那个房间。
这里还有一个技巧，如果我们把这些房间当作List来管理，每次查询需要O(N)时间，如果我们用堆来管理，可以用logN时间找到时间最早结束的房间。
	 * @param intervals
	 * @return
	 */
	//时间 O(NlogN) 空间 O(1)
	public int minMeetingRooms_1(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        // 用堆来管理房间的结束时间
        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();
        endTimes.offer(intervals[0].end);
        for(int i = 1; i < intervals.length; i++){
            // 如果当前时间段的开始时间大于最早结束的时间，则可以更新这个最早的结束时间为当前时间段的结束时间，如果小于的话，就加入一个新的结束时间，表示新的房间
            if(intervals[i].start >= endTimes.peek()){
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }
        // 有多少结束时间就有多少房间
        return endTimes.size();
    }
	
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, (o1,o2) -> {
			int r = o1.start - o2.start;
			return r == 0 ? o1.end - o2.end : r;
		});
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		queue.add(intervals[0].end);
		
		for (int i = 1; i < intervals.length; i++) {
			int val = queue.peek();
			Interval in = intervals[i];
			if (in.start >= val) {
				queue.remove(val);
			}
			queue.add(in.end);
			
		}
		
		return queue.size();
		
	}

	/**
	 *To understand why it works, first let’s define two events:
	 Meeting Starts
	 Meeting Ends

	 Next, we acknowledge three facts:
	 The numbers of the intervals give chronological orders
	 When an ending event occurs, there must be a starting event has happened before that, where “happen before” is defined by the chronological orders given by the intervals
	 Meetings that started which haven’t ended yet have to be put into different meeting rooms, and the number of rooms needed is the number of such meetings

	 So, what this algorithm works as follows:

	 for example, we have meetings that span along time as follows:

	 |_____|
	 |______|
	 |________|
	 |_______|
	 Then, the start time array and end time array after sorting appear like follows:

	 ||    ||
	 |   |   |  |
	 Initially, endsItr points to the first end event, and we move i which is the start event pointer. As we examine the start events, we’ll find the first two start events happen before the end event that endsItr points to, so we need two rooms (we magically created two rooms), as shown by the variable rooms. Then, as i points to the third start event, we’ll find that this event happens after the end event pointed by endsItr, then we increment endsItr so that it points to the next end event. What happens here can be thought of as one of the two previous meetings ended, and we moved the newly started meeting into that vacant room, thus we don’t need to increment rooms at this time and move both of the pointers forward.
	 Next, because endsItr moves to the next end event, we’ll find that the start event pointed by i happens before the end event pointed by endsItr. Thus, now we have 4 meetings started but only one ended, so we need one more room. And it goes on as this.
	 */

	public class Solution {
		public int minMeetingRooms(Interval[] intervals) {
			int[] starts = new int[intervals.length];
			int[] ends = new int[intervals.length];
			for(int i=0; i<intervals.length; i++) {
				starts[i] = intervals[i].start;
				ends[i] = intervals[i].end;
			}
			Arrays.sort(starts);
			Arrays.sort(ends);
			int rooms = 0;
			int endsItr = 0;
			for(int i=0; i<starts.length; i++) {
				if(starts[i]<ends[endsItr])
					rooms++;
				else
					endsItr++;
			}
			return rooms;
		}
	}

	/**
	 * so the start and end lists are:

	 st: 0, 0, 2, 4, 5

	 ed: 3, 4, 7, 8, 9

	 Suppose i is the index of st list, which is the start time of meeting you want to add in to the schedule. Suppose firstEnd is the index of ed list, which is the first end time of in your meeting rooms.
	 You first add a meeting that finishes earliest in a room. But wait, you may ask, what meeting are you talking about? what about the start time of the meeing? You have saperated start and end time of a meeting so that meeting is lost! Damn, I had the same confusion too. First imagine that you know which meeting it is, so, for the first meeting that finishes the earliest, you iterate throught the meeting in the start list, you see: @ i = 0, this meeting starts at 0, before the earliest-end meeting (which is pointed by index firstEnd, which ends at time 3), what do you do? You can do nothing else but to add another room, room B. When does room B finishes? It finishes at a time that is behind index firstEnd in the end list. Which one? I don’t know. But the only thing I know is the earlist meeting that finishes is at 3. You then increase i = 1, you see it starts 0, before the earliest-end meeting. Even the learliest-end meeting finishes after this meeting, so you have no way to use a existing room for it because it will have time collision. So as long as you have a meeting whose start time is earlier than the earliest-end meeting finish time, you add a room, room C. Then you go to i = 2, which finishes at 2 < 3 (pointed by firstEnd in end list), add a room, room A. Why room A? I just want to give it a name A ok? Alright, you remember you don’t know the meeting which ends the earliest, know you encounter it, actually, this room A is the first room that provides the meeting room for the earliest-end meeting. You see this whole process happens silently and internally. Hence, up to i=2, you added three room, and you know the earliest-end meeting finishes at 3. Before you encouter a meeting that starts no earlier than earliest-end meeting, you must have encountered the earliest-end meeting’s start time and silently put that meeting in one of the room you added.

	 You then increase i=3, found start time = 4 which is no earlier than earliest-end meeting. Now your time is 4, you have passed the earliest-end meeting, so you increase the firstEnd index to find the next earliest-end meeting among all rooms, which ends at 4. And you now found you can put this meeting right after this earliest-end meeting with no need to add new room. And then you update firstEnd=3 and go to i=5 and found the next meeting starts earlier than all the meeings among the existing rooms, you add a room.
	 * @param l
	 * @return
     */
	public int minMeetingRooms_4m(Interval[] l) {
		int n = l.length, i = 0, firstEnd = 0;
		int[] st = new int[n], ed = new int[n];
		for (int j = 0; j < n; st[j] = l[j].start, ed[j] = l[j].end, j++);
		Arrays.sort(st);
		Arrays.sort(ed);
		for (; i < n; i++)
			if (st[i] >= ed[firstEnd]) firstEnd++;
		return i - firstEnd;
	}

	//facebook

	/**
	 *
	 interval [startTime, stoptime)   ----integral  time stamps
	 给这样的一串区间 I1, I2......In
	 找出 一个 time stamp  出现在interval的次数最多。
	 startTime <= t< stopTime 代表这个数在区间里面出现过。
	 * @param args
     */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interval i1 = new Interval(0,30);
		Interval i2 = new Interval(5,10);
		Interval i3 = new Interval(15,20);
		Interval[] intervals = new Interval[] {i1,i2,i3};
		MeetingRoomsII slt = new MeetingRoomsII();
		System.out.println(slt.minMeetingRooms(intervals));
	}

}
