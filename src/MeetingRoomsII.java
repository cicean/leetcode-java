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
	 * ���Ӷ�
ʱ�� O(NlogN) �ռ� O(1)

˼·
�����˼·��Rearrange array to certain distance����
����Ҫ��̰�ķ������ӵ�һ��ʱ��ο�ʼ��ѡ����һ���������ͻ��ʱ��Σ�
��ѡ����һ���������ͻ��ʱ��Σ�ֱ��û�и��ࡣ
Ȼ�������ʣ��ʱ��Σ���ʼΪ�ڶ������䰲�ţ�ѡ�������ʱ��Σ�
��ѡ����һ���������ͻ��ʱ��Σ�ֱ��û�и��࣬�������ʣ��ʱ��Σ�
�򿪱ٵ��������䣬�Դ����ơ�����ļ��������ǲ�һ��Ҫ������ô��飬
����ʵ���Ͽ���һ�α�����ʱ��ͼ�¼�£������һ��ʱ������Ƿ��뷿��1��
Ȼ��ڶ���ʱ��Σ�����ͷ���1�Ľ���ʱ�䲻��ͻ���ͷ��뷿��1�����򿪱�һ������2��
Ȼ�������ʱ��Σ�����ͷ���1���߷���2�Ľ���ʱ�䲻��ͻ���ͷ��뷿��1����2�����򿪱�һ������3���������ƣ����ͳ�ƿ����˶��ٷ��䡣
����ÿ�����䣬����ֻҪ��¼�����ʱ������ˣ��������ǲ��Ҳ���ͻ����ʱ��ֻҪ�ҽ���ʱ��������Ǹ����䡣
���ﻹ��һ�����ɣ�������ǰ���Щ���䵱��List������ÿ�β�ѯ��ҪO(N)ʱ�䣬��������ö�������������logNʱ���ҵ�ʱ����������ķ��䡣
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
        // �ö���������Ľ���ʱ��
        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();
        endTimes.offer(intervals[0].end);
        for(int i = 1; i < intervals.length; i++){
            // �����ǰʱ��εĿ�ʼʱ��������������ʱ�䣬����Ը����������Ľ���ʱ��Ϊ��ǰʱ��εĽ���ʱ�䣬���С�ڵĻ����ͼ���һ���µĽ���ʱ�䣬��ʾ�µķ���
            if(intervals[i].start >= endTimes.peek()){
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }
        // �ж��ٽ���ʱ����ж��ٷ���
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
