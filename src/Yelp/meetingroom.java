package Yelp;

import datastructure.Interval;

import java.util.*;

/**
 * Created by cicean on 9/20/2018.
 */
public class meetingroom {

    /**
     * �ܷ�μ�����
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        int end = intervals[0].end;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start < end) {
                return false;
            }
            end = Math.max(end, intervals[i].end);
        }
        return true;
    }

    /**
     * ��Ҫ����������
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

}
