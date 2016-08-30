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
