package Uber;

import datastructure.Interval;

import java.util.*;

/**
 * Created by cicean on 9/11/2018.
 */

public class MeetingRoom {
    /**
     * ]���Ż����ң���ޢ������������ޢ�ϲ�ͬ���ǣ�
     * ���Ҫ���������Ƹ���Ӧ�����һ�𷵻أ������ǽ�������ܵķ�����Ŀ
     */


    class Room {
        int room_name;
        int endtime;

        public Room(int name, int endtime) {
            this.room_name = name;
            this.endtime = endtime;
        }
    }


    public List<List<Interval>> minMeetingRooms(Interval[] intervals) {
        List<List<Interval>> results = new ArrayList<>();
        if (intervals == null || intervals.length == 0) return results;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Room> endTimes = new PriorityQueue<Room>(new Comparator<Room>() {
            @Override
    public int compare(Room o1, Room o2) {
        return o1.endtime - o2.endtime;
    }
});
        //endTimes.offer(intervals[0].end);
        List<Interval> begin = new ArrayList<>();
        begin.add(intervals[0]);
        results.add(begin);
        Room r = new Room(results.size() - 1, intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            Room tmp;
            if (intervals[i].start >= endTimes.peek().endtime) {
                 tmp = endTimes.poll();
                results.get(tmp.room_name).add(intervals[i]);
                tmp.endtime = intervals[i].end;
            } else {
                List<Interval> room = new ArrayList<>();
                room.add(intervals[i]);
                results.add(room);
                tmp = new Room(results.size() - 1, intervals[i].end);
            }

            endTimes.offer(tmp);
        }

        return results;
    }

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
