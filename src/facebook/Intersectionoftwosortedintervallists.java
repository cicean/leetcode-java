package facebook;

import datastructure.Interval;
import java.util.*;



/**
 *
 */

public class Intersectionoftwosortedintervallists {

  public List<Interval> interselection(List<Interval> A, List<Interval> B) {
    List<Interval> res = new ArrayList<>();
    Map<Integer,Integer> changes = new HashMap<>();

    for (Interval a : A) {
      if (!changes.containsKey(a.start)) {
        changes.put(a.start, 1);
      } else {
        changes.put(a.start, changes.get(a.start) + 1);
      }

      if (!changes.containsKey(a.end)) {
        changes.put(a.end, -1);
      } else {
        changes.put(a.end, changes.get(a.end) - 1);
      }
    }

    for (Interval b : B) {
      if (!changes.containsKey(b.start)) {
        changes.put(b.start, 1);
      } else {
        changes.put(b.start, changes.get(b.start) + 1);
      }

      if (!changes.containsKey(b.end)) {
        changes.put(b.end, -1);
      } else {
        changes.put(b.end, changes.get(b.end) - 1);
      }

    }

    Interval pivot = new Interval();
    int curr = 0;
    for (Map.Entry<Integer,Integer> chg : changes.entrySet()) {
      curr += chg.getValue();
      System.out.println("Time : " + chg.getKey() + ", Value: " + chg.getValue());
      if (curr == 2) {
        pivot.start = chg.getKey();
      } else if (pivot.start != pivot.end) {
        pivot.end = chg.getKey();
        res.add(pivot);
        pivot = new Interval();
      }
    }

    return res;
  }

}
