import java.util.*;

/**
 * Created by cicean on 8/31/2016.
 * 352. Data Stream as Disjoint Intervals  QuestionEditorial Solution  My Submissions
 * Total Accepted: 5435 Total Submissions: 14373 Difficulty: Hard
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
 * <p>
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * <p>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 * <p>
 * Credits:
 * Special thanks to @yunhong for adding this problem and creating most of the test cases.
 * <p>
 * Hide Tags Binary Search Tree
 * Hide Similar Problems (M) Summary Ranges
 * <p>
 * ��Ҫ��ʵ��һ�����ݽṹ���������㲻��������֣�����������ĸ��¡�
 */
public class DataStreamasDisjointIntervals {

    /**
     * ����ò�ƶ��ֲ���Ҳ���ԣ���дBSTҲ���ԣ�����������java��TreeMapʵ�������ȽϷ��㡣

     ���Ǹ���������ʼλ�ã�����start����ΪBST ��key������Ҫ��ӵ���val���������������䡣��  ceil ���� val < key , floor ���� key <= val

     Ȼ���ж��䷶Χ���кϲ��� �о�ûɶ��˵�ġ���

     PS: C++ֻ��lower_bound��upper_bound�� ����java treemap��ôˬ ( �s���t ) Python����û����ƽ���������ӣ�
     */

    /**
     * Definition for an interval.
     * public class Interval {
     * int start;
     * int end;
     * Interval() { start = 0; end = 0; }
     * Interval(int s, int e) { start = s; end = e; }
     * }
     */

    //Use TreeMap to easily find the lower and higher keys,
    // the key is the start of the interval.
    //Merge the lower and higher intervals when necessary.
    // The time complexity for adding is O(logN) since lowerKey(), higherKey(), put() and remove() are all O(logN).
    // It would be O(N) if you use an ArrayList and remove an interval from it.
    public class SummaryRanges {
        TreeMap<Integer, Interval> map;

        /**
         * Initialize your data structure here.
         */
        public SummaryRanges() {
            map = new TreeMap<>();
        }

        public void addNum(int val) {
            if (map.containsKey(val)) return;
            Integer l = map.lowerKey(val);
            Integer h = map.higherKey(val);
            if (l != null && h != null && map.get(l).end + 1 == val && val + 1 == map.get(h).start) {
                map.get(l).end = map.get(h).end;
                map.remove(h);
            } else if (l != null && val <= map.get(l).end + 1) {
                map.get(l).end = Math.max(map.get(l).end, val);
            } else if (h != null && map.get(h).start - 1 == val) {
                map.put(val, new Interval(val, map.get(h).end));
                map.remove(h);
            } else {
                map.put(val, new Interval(val, val));
            }
        }

        public List<Interval> getIntervals() {
            return new ArrayList<Interval>(map.values());
        }
    }

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */

    /**
     * java binary search code
     * O(logn) for searching. O(1) for checking merging.
     * However, adding and removing intervals may cost O(n).
     */
    class SummaryRanges2 {


        private List<Interval> ranges;

        public SummaryRanges2() {
            ranges = new ArrayList<>();
        }

        public void addNum(int val) {
            // binary search
            int lo = 0, hi = ranges.size();
            int mid = (lo + hi) / 2;
            while (lo < hi) {
                Interval i = ranges.get(mid);
                if (i.start <= val && val <= i.end) {
                    // num already added
                    return;
                } else if (i.start > val) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
                mid = (lo + hi) / 2;
            }
            // does not exist duplicates, then add new interval
            ranges.add(lo, new Interval(val, val));

            // merge with the one after if necessary
            if (lo < ranges.size() - 1 && ranges.get(lo + 1).start == val + 1) {
                ranges.get(lo).end = ranges.get(lo + 1).end;
                ranges.remove(lo + 1);
            }

            // merge with the one before if necessary
            if (lo > 0 && ranges.get(lo - 1).end == val - 1) {
                ranges.get(lo - 1).end = ranges.get(lo).end;
                ranges.remove(lo);
            }
        }

        public List<Interval> getIntervals() {
            return ranges;
        }

    }
}
