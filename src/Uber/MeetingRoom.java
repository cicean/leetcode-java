package Uber;

import datastructure.Interval;

import java.util.*;

/**
 * Created by cicean on 9/11/2018.
 */

public class MeetingRoom {
    /**
     * ]安排会议室，莉蔻二五三。跟莉蔻上不同的是，
     * 结果要将会议名称跟对应房间号一起返回，而不是仅仅算个总的房间数目
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



}
