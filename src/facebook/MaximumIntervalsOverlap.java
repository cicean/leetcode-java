package facebook;

import java.util.*;

public class MaximumIntervalsOverlap {
  public static boolean findIntervals(int a1, int b1, int a2, int b2) {
    if ((b1 < a2) || (a1 > b2))
      return false;
    return true;
  }

  public static void main(String[] args) {
    MaximumIntervalsOverlap ob1 = new MaximumIntervalsOverlap();
    int size = 4;

    // int interval[][] = new int[size][size];
    int interval[][] = { { 0, 5 }, { 2, 9 }, { 6, 9 }, { 8, 10 }};
    int intervalCount[] = new int[size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {

        if (!(i == j)) {
          if (findIntervals(interval[i][0], interval[i][1],
              interval[j][0], interval[j][1])) {
            int key = 0;
            key = intervalCount[i];
            key++;
            intervalCount[i] = key++;
          }
        }
      }
    }
    for (int i = 0; i < size; i ++ ) {
      System.out.println(intervalCount[i]);
    }
  }

  public static void overlapping(String[] intervals) {
    boolean[] overlaps = new boolean[100];

    int rangeStart = 0;
    int rangeEnd = 0;
    List<Integer> ll = new ArrayList<Integer>();
    for (String interval : intervals) {
      int start = Integer.parseInt(interval.split("-")[0]);
      int end = Integer.parseInt(interval.split("-")[1]);
      if (rangeEnd != 0) {
        ll.add(rangeEnd + rangeStart);
      }
      rangeStart = 0;
      rangeEnd = 0;
      boolean rangeFlag = false;
      for (int i = start; i <= end; i++) {
        if (!overlaps[i]) {
          overlaps[i] = true;
        } else {
          if (!rangeFlag) {
            rangeStart = i;
            ll.add(rangeStart);
            rangeFlag = true;
          }
          rangeEnd++;
        }
      }
    }

    System.out.println(ll.size() / 2);
  }

}
