package VMware;

import java.util.*;

public class MapofFruit {

  public List<String> mostKfruit(Map<String,Integer> fruit, int k) {
      PriorityQueue<String> heap = new PriorityQueue<String>(
          (w1, w2) -> fruit.get(w1) != fruit.get(w2) ?
              fruit.get(w1) - fruit.get(w2) : w2.compareTo(w1) );

      for (String word: fruit.keySet()) {
        heap.offer(word);
        if (heap.size() > k) heap.poll();
      }

      List<String> ans = new ArrayList();
      while (!heap.isEmpty()) ans.add(heap.poll());
      Collections.reverse(ans);
      return ans;
  }

}
