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

  class Solution17m {
    public List<Integer> fallingSquares(int[][] positions) {
      int n = positions.length, cnt = 0;
      int[] ps = new int[2*n];
      for (int[] p : positions) {
        ps[cnt++] = p[0];
        ps[cnt++] = p[0] + p[1] - 1;
      }
      Arrays.sort(ps);
      cnt = 0;
      for (int i = 0; i < 2*n; i++) {
        if (cnt > 0 && ps[i] == ps[cnt - 1]) continue;
        ps[cnt++] = ps[i];
      }
      int[] hs = new int[2*n];
      List<Integer> ans = new ArrayList<>();
      int max = 0;
      for (int[] p : positions) {
        int ll = Arrays.binarySearch(ps, 0, cnt, p[0]),
            rr = Arrays.binarySearch(ps, 0, cnt, p[0] + p[1] - 1);
        int m = 0;
        for (int i = ll; i <= rr; i++) m = Math.max(m, hs[i]);
        for (int i = ll; i <= rr; i++) hs[i] = m + p[1];
        max = Math.max(max, m + p[1]);
        ans.add(max);
      }
      return ans;
    }
  }

  /**
   * Approach #1: Offline Propagation [Accepted]

   Intuition

   Instead of asking the question "what squares affect this query?", lets ask the question "what queries are affected by this square?"

   Algorithm

   Let qans[i] be the maximum height of the interval specified by positions[i]. At the end, we'll return a running max of qans.

   For each square positions[i], the maximum height will get higher by the size of the square we drop.
   Then, for any future squares that intersect the interval [left, right)
   (where left = positions[i][0], right = positions[i][0] + positions[i][1]), we'll update the maximum height of that interval.
   Complexity Analysis

   Time Complexity: O(N2)O(N^2)O(N​2​​), where NNN is the length of positions. We use two for-loops, each of complexity O(N)O(N)O(N).

   Space Complexity: O(N)O(N)O(N), the space used by qans and ans.

   */

  class Solution1 {
    public List<Integer> fallingSquares(int[][] positions) {
      int[] qans = new int[positions.length];
      for (int i = 0; i < positions.length; i++) {
        int left = positions[i][0];
        int size = positions[i][1];
        int right = left + size;
        qans[i] += size;

        for (int j = i+1; j < positions.length; j++) {
          int left2 = positions[j][0];
          int size2 = positions[j][1];
          int right2 = left2 + size2;
          if (left2 < right && left < right2) { //intersect
            qans[j] = Math.max(qans[j], qans[i]);
          }
        }
      }

      List<Integer> ans = new ArrayList();
      int cur = -1;
      for (int x: qans) {
        cur = Math.max(cur, x);
        ans.add(cur);
      }
      return ans;
    }
  }

  /**
   * Approach #2: Brute Force with Coordinate Compression [Accepted]

   Intuition and Algorithm

   Let N = len(positions). After mapping the board to a board of length at most 2∗N≤20002* N \leq 20002∗N≤2000, we can brute force the answer by simulating each square's drop directly.

   Our answer is either the current answer or the height of the square that was just dropped, and we'll update it appropriately.
   Complexity Analysis

   Time Complexity: O(N2)O(N^2)O(N​2​​), where NNN is the length of positions. We use two for-loops, each of complexity O(N)O(N)O(N) (because of coordinate compression.)

   Space Complexity: O(N)O(N)O(N), the space used by heights.

   */

  class Solution2 {
    int[] heights;

    public int query(int L, int R) {
      int ans = 0;
      for (int i = L; i <= R; i++) {
        ans = Math.max(ans, heights[i]);
      }
      return ans;
    }

    public void update(int L, int R, int h) {
      for (int i = L; i <= R; i++) {
        heights[i] = Math.max(heights[i], h);
      }
    }

    public List<Integer> fallingSquares(int[][] positions) {
      //Coordinate Compression
      //HashMap<Integer, Integer> index = ...;
      //int t = ...;

      Set<Integer> coords = new HashSet();
      for (int[] pos: positions) {
        coords.add(pos[0]);
        coords.add(pos[0] + pos[1] - 1);
      }
      List<Integer> sortedCoords = new ArrayList(coords);
      Collections.sort(sortedCoords);

      Map<Integer, Integer> index = new HashMap();
      int t = 0;
      for (int coord: sortedCoords) index.put(coord, t++);

      heights = new int[t];
      int best = 0;
      List<Integer> ans = new ArrayList();

      for (int[] pos: positions) {
        int L = index.get(pos[0]);
        int R = index.get(pos[0] + pos[1] - 1);
        int h = query(L, R) + pos[1];
        update(L, R, h);
        best = Math.max(best, h);
        ans.add(best);
      }
      return ans;
    }
  }

  /**
   * Approach #3: Block (Square Root) Decomposition [Accepted]

   Intuition

   Whenever we perform operations (like update and query) on some interval in a domain, we could segment that domain with size WWW into blocks of size W\sqrt{W}√​W​​​.

   Then, instead of a typical brute force where we update our array heights representing the board, we will also hold another array blocks, where blocks[i] represents the B=⌊W⌋B = \lfloor \sqrt{W} \rfloorB=⌊√​W​​​⌋ elements heights[B*i], heights[B*i + 1], ..., heights[B*i + B-1]. This allows us to write to the array in O(B)O(B)O(B) operations.

   Algorithm

   Let's get into the details. We actually need another array, blocks_read. When we update some element i in block b = i / B, we'll also update blocks_read[b]. If later we want to read the entire block, we can read from here (and stuff written to the whole block in blocks[b].)

   When we write to a block, we'll write in blocks[b]. Later, when we want to read from an element i in block b = i / B, we'll read from heights[i] and blocks[b].

   Our process for managing query and update will be similar. While left isn't a multiple of B, we'll proceed with a brute-force-like approach, and similarly for right. At the end, [left, right+1) will represent a series of contiguous blocks: the interval will have length which is a multiple of B, and left will also be a multiple of B.
   Complexity Analysis

   Time Complexity: O(NN)O(N\sqrt{N})O(N√​N​​​), where NNN is the length of positions. Each query and update has complexity O(N)O(\sqrt{N})O(√​N​​​).

   Space Complexity: O(N)O(N)O(N), the space used by heights.

   */

  class Solution3 {
    int[] heights;
    int[] blocks;
    int[] blocks_read;
    int B;

    public int query(int left, int right) {
      int ans = 0;
      while (left % B > 0 && left <= right) {
        ans = Math.max(ans, heights[left]);
        ans = Math.max(ans, blocks[left / B]);
        left++;
      }
      while (right % B != B - 1 && left <= right) {
        ans = Math.max(ans, heights[right]);
        ans = Math.max(ans, blocks[right / B]);
        right--;
      }
      while (left <= right) {
        ans = Math.max(ans, blocks[left / B]);
        ans = Math.max(ans, blocks_read[left / B]);
        left += B;
      }
      return ans;
    }

    public void update(int left, int right, int h) {
      while (left % B > 0 && left <= right) {
        heights[left] = Math.max(heights[left], h);
        blocks_read[left / B] = Math.max(blocks_read[left / B], h);
        left++;
      }
      while (right % B != B - 1 && left <= right) {
        heights[right] = Math.max(heights[right], h);
        blocks_read[right / B] = Math.max(blocks_read[right / B], h);
        right--;
      }
      while (left <= right) {
        blocks[left / B] = Math.max(blocks[left / B], h);
        left += B;
      }
    }

    public List<Integer> fallingSquares(int[][] positions) {
      //Coordinate Compression
      //HashMap<Integer, Integer> index = ...;
      //int t = ...;

      Set<Integer> coords = new HashSet();
      for (int[] pos: positions) {
        coords.add(pos[0]);
        coords.add(pos[0] + pos[1] - 1);
      }
      List<Integer> sortedCoords = new ArrayList(coords);
      Collections.sort(sortedCoords);

      Map<Integer, Integer> index = new HashMap();
      int t = 0;
      for (int coord: sortedCoords) index.put(coord, t++);

      heights = new int[t];
      B = (int) Math.sqrt(t);
      blocks = new int[B+2];
      blocks_read = new int[B+2];

      int best = 0;
      List<Integer> ans = new ArrayList();

      for (int[] pos: positions) {
        int L = index.get(pos[0]);
        int R = index.get(pos[0] + pos[1] - 1);
        int h = query(L, R) + pos[1];
        update(L, R, h);
        best = Math.max(best, h);
        ans.add(best);
      }
      return ans;
    }
  }

  /**
   * Approach #4: Segment Tree with Lazy Propagation [Accepted]

   Intuition

   If we were familiar with the idea of a segment tree (which supports queries and updates on intervals), we can immediately crack the problem.

   Algorithm

   Segment trees work by breaking intervals into a disjoint sum of component intervals, whose number is at most log(width). The motivation is that when we change an element, we only need to change log(width) many intervals that aggregate on an interval containing that element.

   When we want to update an interval all at once, we need to use lazy propagation to ensure good run-time complexity. This topic is covered in more depth here.

   With such an implementation in hand, the problem falls out immediately.

   Complexity Analysis

   Time Complexity: O(NlogN)O(N \log N)O(NlogN), where NNN is the length of positions. This is the run-time complexity of using a segment tree.

   Space Complexity: O(N)O(N)O(N), the space used by our tree.


   */

  class Solution4 {
    public List<Integer> fallingSquares(int[][] positions) {
      //Coordinate Compression
      //HashMap<Integer, Integer> index = ...;

      Set<Integer> coords = new HashSet();
      for (int[] pos: positions) {
        coords.add(pos[0]);
        coords.add(pos[0] + pos[1] - 1);
      }
      List<Integer> sortedCoords = new ArrayList(coords);
      Collections.sort(sortedCoords);

      Map<Integer, Integer> index = new HashMap();

      SegmentTree tree = new SegmentTree(sortedCoords.size());
      int best = 0;
      List<Integer> ans = new ArrayList();

      for (int[] pos: positions) {
        int L = index.get(pos[0]);
        int R = index.get(pos[0] + pos[1] - 1);
        int h = tree.query(L, R) + pos[1];
        tree.update(L, R, h);
        best = Math.max(best, h);
        ans.add(best);
      }
      return ans;
    }
  }

  class SegmentTree {
    int N, H;
    int[] tree, lazy;

    SegmentTree(int N) {
      this.N = N;
      H = 1;
      while ((1 << H) < N) H++;
      tree = new int[2 * N];
      lazy = new int[N];
    }

    private void apply(int x, int val) {
      tree[x] = Math.max(tree[x], val);
      if (x < N) lazy[x] = Math.max(lazy[x], val);
    }

    private void pull(int x) {
      while (x > 1) {
        x >>= 1;
        tree[x] = Math.max(tree[x * 2], tree[x * 2 + 1]);
        tree[x] = Math.max(tree[x], lazy[x]);
      }
    }

    private void push(int x) {
      for (int h = H; h > 0; h--) {
        int y = x >> h;
        if (lazy[y] > 0) {
          apply(y * 2, lazy[y]);
          apply(y * 2 + 1, lazy[y]);
          lazy[y] = 0;
        }
      }
    }

    public void update(int L, int R, int h) {
      L += N; R += N;
      int L0 = L, R0 = R, ans = 0;
      while (L <= R) {
        if ((L & 1) == 1) apply(L++, h);
        if ((R & 1) == 0) apply(R--, h);
        L >>= 1; R >>= 1;
      }
      pull(L0); pull(R0);
    }

    public int query(int L, int R) {
      L += N; R += N;
      int ans = 0;
      push(L); push(R);
      while (L <= R) {
        if ((L & 1) == 1) ans = Math.max(ans, tree[L++]);
        if ((R & 1) == 0) ans = Math.max(ans, tree[R--]);
        L >>= 1; R >>= 1;
      }
      return ans;
    }
  }

}
