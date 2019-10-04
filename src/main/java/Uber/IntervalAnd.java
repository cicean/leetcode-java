package Uber;

import datastructure.Interval;

import java.util.*;

/**
 * Created by cicean on 9/11/2018.
 */
public class IntervalAnd {

    /**
     * Uber AND operation of intervals
     */

    public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        Comparator<Interval> comp =  new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
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
        Collections.sort(seqA, comp);

        List<Interval> intervals = new ArrayList<>();

        List<Interval> results = new ArrayList<>();

        if (seqA != null) {
            intervals.addAll(seqA);
        }

        if (seqB != null) {
            intervals.addAll(seqB);
        }

        int N = intervals.size();
        if (N <= 1) {
            return intervals;
        }

        Collections.sort(intervals,comp);
        Interval last = intervals.get(0);
        for (int i = 0; i < N; i++) {
            if ((seqA.contains(last) && seqB.contains(intervals.get(i)))
                    || (seqA.contains(intervals.get(i)) && seqB.contains(last))) {
                if (intervals.get(i).start <= last.end) {
                    Interval and = new Interval();
                    and.start = intervals.get(i).start;
                    and.end = Math.min(last.end, intervals.get(i).end);
                    results.add(and);
                }
            }
            last = intervals.get(i);
        }
        return results;
    }

    public List<Interval> timeIntersection2(List<Interval> seqA, List<Interval> seqB) {
        List<Interval> results = new ArrayList<>();
        if (seqA == null || seqB == null
                || seqA.size() == 0 || seqB.size() == 0) {
            return results;
        }


        int i = 0, j = 0;
        while (i < seqA.size() && j < seqB.size()) {
            if (seqA.get(i).start < seqB.get(j).start) {
                if (seqB.get(j).start > seqA.get(i).end) {
                    Interval result = new Interval(seqA.get(i).end, seqB.get(j).start);
                    results.add(result);
                }
                i++;
            } else {
                if (seqA.get(i).start > seqB.get(j).end) {
                    Interval result = new Interval(seqB.get(j).end, seqA.get(i).start);
                    results.add(result);
                }
                j++;
            }
        }

        return results;
    }

}
