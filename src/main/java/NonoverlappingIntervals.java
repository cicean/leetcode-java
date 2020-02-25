import java.util.*;

/**
 * 435. Non-overlapping Intervals
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 Note:
 You may assume the interval's end point is always bigger than its start point.
 Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 Example 1:
 Input: [ [1,2], [2,3], [3,4], [1,3] ]

 Output: 1

 Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 Example 2:
 Input: [ [1,2], [1,2], [1,2] ]

 Output: 2

 Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 Example 3:
 Input: [ [1,2], [2,3] ]

 Output: 0

 Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

 */

public class NonoverlappingIntervals {

  /**
   * Java: Least is Most
   Actually, the problem is the same as "Given a collection of intervals, find the maximum number of intervals that are non-overlapping." (the classic Greedy problem: Interval Scheduling). With the solution to that problem, guess how do we get the minimum number of intervals to remove? : )

   Sorting Interval.end in ascending order is O(nlogn), then traverse intervals array to get the maximum number of non-overlapping intervals is O(n). Total is O(nlogn).
   * @param intervals
   * @return
   */


  public int eraseOverlapIntervals(Interval[] intervals) {
    if (intervals.length == 0)  return 0;

    Arrays.sort(intervals, new myComparator());
    int end = intervals[0].end;
    int count = 1;

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i].start >= end) {
        end = intervals[i].end;
        count++;
      }
    }
    return intervals.length - count;
  }

  class myComparator implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
      return a.end - b.end;
    }
  }

  public int eraseOverlapIntervals_1(int[][] intervals) {
    Arrays.sort(intervals, new Comparator<int[]>(){
      @Override
      public int compare(int[] a, int[] b){
        return a[0] ==b[0] ? a[1]-b[1]:a[0]-b[0];
      }
    });
    int index = 0;
    int count = 0;
    for(int i = 1 ; i < intervals.length; i++){
      if(intervals[i][0] < intervals[index][1]){
        count++;
        if(intervals[i][1] < intervals[index][1]){
          index = i;
        }
      }
      else{
        index = i;
      }
    }
    return count;
  }

  /**
   * Approach #2 Using DP based on starting point [Accepted]
   * Algorithm
   *
   * The given problem can be simplified to a great extent if we sort the given interval list based on the starting points. Once it's done, we can make use of a dpdp array, scuh that dp[i]dp[i] stores the maximum number of valid intervals that can be included in the final list if the intervals upto the i^{th}i
   * th
   *   interval only are considered, including itself. Now, while finding dp[i+1]dp[i+1], we can't consider the value of dp[i]dp[i] only, because it could be possible that the i^{th}i
   * th
   *   or any previous interval could be overlapping with the (i+1)^{th}(i+1)
   * th
   *   interval. Thus, we need to consider the maximum of all dp[j]dp[j]'s such that j \leq ij≤i and j^{th}j
   * th
   *   interval and i^{th}i
   * th
   *   don't overlap, to evaluate dp[i+1]dp[i+1]. Therefore, the equation for dp[i+1]dp[i+1] becomes:
   *
   * dp[i+1]= \max(dp[j]) + 1,dp[i+1]=max(dp[j])+1,
   *
   * such that j^{th}j
   * th
   *   interval and i^{th}i
   * th
   *   don't overlap, for all j \leq ij≤i.
   *
   * In the end, to obtain the maximum number of intervals that can be included in the final list(ansans) we need to find the maximum value in the dpdp array. The final result will be the total number of intervals given less the result just obtained(intervals.length-ansintervals.length−ans).
   *
   * The animation below illustrates the approach more clearly:
   * Complexity Analysis
   *
   * Time complexity : O(n^2)O(n
   * 2
   *  ). Two nested loops are required to fill dpdp array.
   *
   * Space complexity : O(n)O(n). dpdp array of size nn is used.
   */

  class Solution_DP {
    class myComparator implements Comparator<int[]> {
      public int compare(int[] a, int[] b) {
        return a[1] - b[1];
      }
    }
    public boolean isOverlapping(int[] i, int[] j) {
      return i[1] > j[0];
    }
    public int eraseOverlapIntervals(int[][] intervals) {
      if (intervals.length == 0) {
        return 0;
      }
      Arrays.sort(intervals, new myComparator());
      int dp[] = new int[intervals.length];
      dp[0] = 1;
      int ans = 1;
      for (int i = 1; i < dp.length; i++) {
        int max = 0;
        for (int j = i - 1; j >= 0; j--) {
          if (!isOverlapping(intervals[j], intervals[i])) {
            max = Math.max(dp[j], max);
          }
        }
        dp[i] = max + 1;
        ans = Math.max(ans, dp[i]);
      }
      return intervals.length - ans;
    }
  }


}
