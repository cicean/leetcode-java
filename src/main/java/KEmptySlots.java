import java.util.*;

/**
 * Approach #1: Insert Into Sorted Structure [Accepted]

 Intuition

 Let's add flowers in the order they bloom. When each flower blooms, we check it's neighbors
 to see if they can satisfy the condition with the current flower.

 Algorithm

 We'll maintain active, a sorted data structure containing every flower that has currently bloomed.
 When we add a flower to active, we should check it's lower and higher neighbors. If some neighbor
 satisfies the condition, we know the condition occurred first on this day.
 */

public class KEmptySlots {

  /**
   * Complexity Analysis

   Time Complexity (Java): O(N \log N)O(NlogN), where NN is the length of flowers. Every insertion and search is O(\log N)O(logN).

   Time Complexity (Python): O(N^2)O(N
   ​2
   ​​ ). As above, except list.insert is O(N)O(N).

   Space Complexity: O(N)O(N), the size of active.
   */

  class Solution {
    public int kEmptySlots(int[] flowers, int k) {
      TreeSet<Integer> active = new TreeSet();
      int day = 0;
      for (int flower: flowers) {
        day++;
        active.add(flower);
        Integer lower = active.lower(flower);
        Integer higher = active.higher(flower);
        if (lower != null && flower - lower - 1 == k ||
            higher != null && higher - flower - 1 == k)
          return day;
      }
      return -1;
    }
  }

  /**
   * Approach #2: Sliding Window [Accepted]

   Intuition

   For each contiguous block ("window") of k positions in the flower bed, we know it satisfies the
   condition in the problem statement if the minimum blooming date of this window is larger than the blooming date of the left and right neighbors.

   Because these windows overlap, we can calculate these minimum queries more efficiently using a sliding window structure.

   Algorithm

   Let days[x] = i be the time that the flower at position x blooms. For each window of k days,
   let's query the minimum of this window in (amortized) constant time using a MinQueue, a data
   structure built just for this task. If this minimum is larger than it's two neighbors,
   then we know this is a place where "k empty slots" occurs, and we record this candidate answer.

   To operate a MinQueue, the key invariant is that mins will be an increasing list of candidate
   answers to the query MinQueue.min.

   For example, if our queue is [1, 3, 6, 2, 4, 8], then mins will be [1, 2, 4, 8]. As we MinQueue.
   popleft, mins will become [2, 4, 8], then after 3 more popleft's will become [4, 8], then after 1 more popleft will become [8].

   As we MinQueue.append, we should maintain this invariant. We do it by popping any elements larger
   than the one we are inserting. For example, if we appended 5 to [1, 3, 6, 2, 4, 8], then mins which was [1, 2, 4, 8] becomes [1, 2, 4, 5].

   Note that we used a simpler variant of MinQueue that requires every inserted element to be unique
   to ensure correctness. Also, the operations are amortized constant time because every element
   will be inserted and removed exactly once from each queue.
   Complexity Analysis

   Time Complexity: O(N)O(N), where NN is the length of flowers. In enumerating through the O(N)O(N)
   outer loop, we do constant work as MinQueue.popleft and MinQueue.min operations are (amortized) constant time.

   Space Complexity: O(N)O(N), the size of our window.
   */

  class Solution2 {
    public int kEmptySlots(int[] flowers, int k) {
      int[] days = new int[flowers.length];
      for (int i = 0; i < flowers.length; i++) {
        days[flowers[i] - 1] = i + 1;
      }

      MinQueue<Integer> window = new MinQueue();
      int ans = days.length;

      for (int i = 0; i < days.length; i++) {
        int day = days[i];
        window.addLast(day);
        if (k <= i && i < days.length - 1) {
          window.pollFirst();
          if (k == 0 || days[i-k] < window.min() && days[i+1] < window.min()) {
            ans = Math.min(ans, Math.max(days[i-k], days[i+1]));
          }
        }
      }

      return ans < days.length ? ans : -1;
    }
  }

  class MinQueue<E extends Comparable<E>> extends ArrayDeque<E> {
    Deque<E> mins;

    public MinQueue() {
      mins = new ArrayDeque<E>();
    }

    @Override
    public void addLast(E x) {
      super.addLast(x);
      while (mins.peekLast() != null &&
          x.compareTo(mins.peekLast()) < 0) {
        mins.pollLast();
      }
      mins.addLast(x);
    }

    @Override
    public E pollFirst() {
      E x = super.pollFirst();
      if (x == mins.peekFirst()) mins.pollFirst();
      return x;
    }

    public E min() {
      return mins.peekFirst();
    }
  }

  class Solution3 {
    public int kEmptySlots(int[] flowers, int k) {
      int n = flowers.length;
      boolean[] bloom = new boolean[n];

      for(int j = 0; j < n; j++) {
        int pos = flowers[j];
        bloom[pos - 1] = true;
        if (pos - 1 - k - 1 >= 0 && bloom[pos - 1 - k - 1] == true) {
          boolean found = true;
          for(int i = pos - 2; i > pos - 1 - k - 1; i--) {
            if (bloom[i]) {
              found = false;
              break;
            }
          }
          if (found) return j+1;
        }
        if (pos + k < n && bloom[pos + k] == true) {
          boolean found = true;
          for(int i = pos; i < pos + k; i++) {
            if (bloom[i]) {
              found = false;
              break;
            }
          }
          if (found) return j+1;
        }
      }

      return -1;
    }
  }

}
