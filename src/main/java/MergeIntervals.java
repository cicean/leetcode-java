import java.util.*;

/*
 56	Merge Intervals	22.4%	Hard
 Problem:    Merge Intervals
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/merge-intervals/
 Notes:
 Given a collection of intervals, merge all overlapping intervals.
 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 Solution: 1. Sort in ascending order of 'start'.
           2. Traverse the 'intervals', merge or push...
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 *
 *     Complexity Analysis
 *
 * Time complexity : O(n\log{}n)O(nlogn)
 *
 * Other than the sort invocation, we do a simple linear scan of the list, so the runtime is dominated by the O(nlgn)O(nlgn) complexity of sorting.
 *
 * Space complexity : O(1)O(1) (or O(n)O(n))
 *
 * If we can sort intervals in place, we do not need more than constant additional space. Otherwise, we must allocate linear space to store a copy of intervals and sort that.
 * }
 */

public class MergeIntervals {
	 public List<Interval> merge(List<Interval> intervals) {
	        Comparator<Interval> comp =  new Comparator<Interval>(){  
	            public int compare(Interval a,  Interval b) {  
	                if(a.start < b.start) {  
	                    return -1;  
	                }else if(a.start > b.start){  
	                    return 1;  
	                } else {
	                    if (a.end < b.end) return -1;
	                    else if (a.end > b.end) return 1;
	                    return 0;  
	                }  
	            }  
	        };
	        ArrayList<Interval> res = new ArrayList<Interval>();
	        int N = intervals.size();
	        if (N <= 1) return intervals;
	        Collections.sort(intervals, comp);
	        Interval last = intervals.get(0);
	        for (int i = 0; i < N; ++i) {
	            if (intervals.get(i).start > last.end) {
	                res.add(last);
	                last = intervals.get(i);
	            } else {
	                last.end = Math.max(last.end, intervals.get(i).end);
	            }
	        }
	        res.add(last);
	        return res;
	    }
	 
	 public static void print(List<Interval> intervals) {
			for (int i = 0; i < intervals.size(); i++) {
				System.out.println("[" + intervals.get(i).start + "," + intervals.get(i).end + "]");
			}
		}
	 
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
		 MergeIntervals slt= new MergeIntervals();
			Interval o1 = new Interval(1,3);
			Interval o2 = new Interval(2,6);
			Interval o3 = new Interval(8,10);
			Interval o4 = new Interval(15,18);
			List<Interval> l = new ArrayList<Interval>();
			l.add(o1);
			l.add(o2);
			l.add(o3);
			l.add(o4);
			List<Interval> res = slt.merge(l);
			print(res);
		}
	 
}
