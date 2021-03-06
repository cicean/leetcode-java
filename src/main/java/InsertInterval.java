/*
 57	Insert Interval	21.4%	Hard
 Problem:    Insert Interval
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/insert-interval/
 Notes:
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 You may assume that the intervals were initially sorted according to their start times.
 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 Solution: For example 2:
           1. compare [1,2] with [4,9], then insert [1,2];
           2. merge [3,5] with [4,9], get newInterval = [3,9];
           3. merge [6,7] with [3,9], get newInterval = [3,9];
           4. merge [8,10] with [3,9], get newInterval = [3,10];
           5. compare [12,16] with [3,10], insert newInterval [3,10], then all the remaining intervals...
           Solution 1 : Time O(N).
           Solution 2 : Time O(Log(N)).
*/

import java.util.*;

public class InsertInterval {

	public List<Interval> insert_1(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        boolean inserted = false;
        for (Interval it : intervals) {
            if (inserted || it.end < newInterval.start) {
                res.add(it);
            } else if (it.start > newInterval.end) {
                res.add(newInterval);
                res.add(it);
                inserted = true;
            } else {
                newInterval.start = Math.min(newInterval.start, it.start);
                newInterval.end = Math.max(newInterval.end, it.end);
            }
        }
        if (inserted == false) res.add(newInterval);
        return res;
    }
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        int n = intervals.size();
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals.get(mid).start > newInterval.start) right = mid - 1;
            else left = mid + 1;
        }
        int idxStart = right;
        left = 0; right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals.get(mid).end < newInterval.end) left = mid + 1;
            else right = mid - 1;
        }
        int idxEnd = left;
        if (idxStart >= 0 && newInterval.start <= intervals.get(idxStart).end) {
            newInterval.start = intervals.get(idxStart--).start;
        }
        if (idxEnd < n && newInterval.end >= intervals.get(idxEnd).start) {
            newInterval.end = intervals.get(idxEnd++).end;
        }
        for (int i = 0; i <= idxStart; ++i) {
            res.add(intervals.get(i));
        }
        res.add(newInterval);
        for (int i = idxEnd; i < n; ++i) {
            res.add(intervals.get(i));
        }
        return res;
    }
    
    public static void print(List<Interval> intervals) {
		for (int i = 0; i < intervals.size(); i++) {
			System.out.println("[" + intervals.get(i).start + "," + intervals.get(i).end + "]");
		}
	}
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	InsertInterval slt= new InsertInterval();
		Interval o1 = new Interval(1,3);
		Interval o2 = new Interval(6,9);
		Interval newInterval = new Interval(2,5);
		//Interval o3 = new Interval(8,10);
		//Interval o4 = new Interval(15,18);
		List<Interval> intervals = new ArrayList<Interval>();
		
		intervals.add(o1);
		intervals.add(o2);
	//	intervals.add(o3);
		//intervals.add(o4);
		
		List<Interval> res = slt.insert(intervals, newInterval);
		
		print(res);
	}
}
