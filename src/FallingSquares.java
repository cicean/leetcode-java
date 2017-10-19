import java.util.*;

/**
 * 699. Falling Squares
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 On an infinite number line (x-axis), we drop given squares in the order they are given.

 The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].

 The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.

 The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


 Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].

 Example 1:
 Input: [[1, 2], [2, 3], [6, 1]]
 Output: [2, 5, 5]
 Explanation:

 After the first drop of
 positions[0] = [1, 2]:
 _aa
 _aa
 -------
 The maximum height of any square is 2.


 After the second drop of
 positions[1] = [2, 3]:
 __aaa
 __aaa
 __aaa
 _aa__
 _aa__
 --------------
 The maximum height of any square is 5.
 The larger square stays on top of the smaller square despite where its center
 of gravity is, because squares are infinitely sticky on their bottom edge.


 After the third drop of
 positions[1] = [6, 1]:
 __aaa
 __aaa
 __aaa
 _aa
 _aa___a
 --------------
 The maximum height of any square is still 5.

 Thus, we return an answer of
 [2, 5, 5]
 .


 Example 2:
 Input: [[100, 100], [200, 100]]
 Output: [100, 100]
 Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.
 Note:

 1 <= positions.length <= 1000.
 1 <= positions[i][0] <= 10^8.
 1 <= positions[i][1] <= 10^6.

 */

public class FallingSquares {

  /**
   * Easy Understood O(n^2) Solution with explanation
   The idea is quite simple, we use intervals to represent the square. the initial height we set to the square cur is pos[1]. That means we assume that all the square will fall down to the land. we iterate the previous squares, check is there any square i beneath my cur square. If we found that we have squares i intersect with us, which means my current square will go above to that square i. My target is to find the highest square and put square cur onto square i, and set the height of the square cur as

   cur.height = cur.height + previousMaxHeight;
   Actually, you do not need to use the interval class to be faster, I just use it to make my code cleaner
   */

  class Solution {
    private class Interval {
      int start, end, height;
      public Interval(int start, int end, int height) {
        this.start = start;
        this.end = end;
        this.height = height;
      }
    }
    public List<Integer> fallingSquares(int[][] positions) {
      List<Interval> intervals = new ArrayList<>();
      List<Integer> res = new ArrayList<>();
      int h = 0;
      for (int[] pos : positions) {
        Interval cur = new Interval(pos[0], pos[0] + pos[1] - 1, pos[1]);
        h = Math.max(h, getHeight(intervals, cur));
        res.add(h);
      }
      return res;
    }
    private int getHeight(List<Interval> intervals, Interval cur) {
      int preMaxHeight = 0;
      for (Interval i : intervals) {
        // Interval i does not intersect with cur
        if (i.end < cur.start) continue;
        if (i.start > cur.end) continue;
        // find the max height beneath cur
        preMaxHeight = Math.max(preMaxHeight, i.height);
      }
      cur.height += preMaxHeight;
      intervals.add(cur);
      return cur.height;
    }
  }

}
