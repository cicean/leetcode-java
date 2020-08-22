/**
 * 850. Rectangle Area II
 * Hard
 *
 * 290
 *
 * 28
 *
 * Add to List
 *
 * Share
 * We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.
 *
 * Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * Output: 6
 * Explanation: As illustrated in the picture.
 * Example 2:
 *
 * Input: [[0,0,1000000000,1000000000]]
 * Output: 49
 * Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
 * Note:
 *
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= rectangles[i][j] <= 10^9
 * The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.
 * Accepted
 * 10,440
 * Submissions
 * 22,314
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * lee215
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 *
 * Sumologic
 * |
 * 2
 */
public class RectangleAreaII {

    /**
     * Approach #1: Principle of Inclusion-Exclusion
     * Intuition
     *
     * Say we have two rectangles, AA and BB. The area of their union is:
     *
     * |A \cup B| = |A| + |B| - |A \cap B|∣A∪B∣=∣A∣+∣B∣−∣A∩B∣
     *
     * Say we have three rectangles, A, B, CA,B,C. The area of their union is:
     *
     * |A \cup B \cup C| = |A| + |B| + |C| - |A \cap B| - |A \cap C| - |B \cap C| + |A \cap B \cap C|∣A∪B∪C∣=∣A∣+∣B∣+∣C∣−∣A∩B∣−∣A∩C∣−∣B∩C∣+∣A∩B∩C∣
     *
     * This can be seen by drawing a Venn diagram.
     *
     * Say we have four rectangles, A, B, C, DA,B,C,D. The area of their union is:
     *
     * \begin{aligned} |A \cup B \cup C \cup D| =\,&\left( |A| + |B| + |C| + |D| \right) - \\ \,&\left(|A \cap B| + |A \cap C| + |A \cap D| + |B \cap C| + |B \cap D| + |C \cap D|\right) +\\ \,&\left(|A \cap B \cap C| + |A \cap B \cap D| + |A \cap C \cap D| + |B \cap C \cap D|\right) -\\ \,&\left(|A \cap B \cap C \cap D|\right) \end{aligned}
     * ∣A∪B∪C∪D∣=
     * ​
     *
     * (∣A∣+∣B∣+∣C∣+∣D∣)−
     * (∣A∩B∣+∣A∩C∣+∣A∩D∣+∣B∩C∣+∣B∩D∣+∣C∩D∣)+
     * (∣A∩B∩C∣+∣A∩B∩D∣+∣A∩C∩D∣+∣B∩C∩D∣)−
     * (∣A∩B∩C∩D∣)
     * ​
     *
     *
     * In general, the area of the union of nn rectangles A_1, A_2, \cdots , A_nA
     * 1
     * ​
     *  ,A
     * 2
     * ​
     *  ,⋯,A
     * n
     * ​
     *   will be:
     *
     * \bigg|\bigcup_{i=1}^n A_i\bigg| = \sum_{\emptyset \neq S \subseteq [n]} (-1)^{|S| + 1} \bigg| \bigcap_{i \in S} A_i \bigg|
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  ⋃
     * i=1
     * n
     * ​
     *  A
     * i
     * ​
     *
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  =∑
     * ∅
     * 
     * ​
     *  =S⊆[n]
     * ​
     *  (−1)
     * ∣S∣+1
     *
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  ⋂
     * i∈S
     * ​
     *  A
     * i
     * ​
     *
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *
     *
     * Algorithm
     *
     * If we do not know the above fact, we can prove it by considering any point in \bigg|\bigcup_{i=1}^n A_i\bigg|
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  ⋃
     * i=1
     * n
     * ​
     *  A
     * i
     * ​
     *
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  . Say this point occurs in all A_i (i \in S)A
     * i
     * ​
     *  (i∈S), and let |S| = n∣S∣=n. Then on the right side, it is counted \binom{n}{1} - \binom{n}{2} + \binom{n}{3} - \cdots \pm \binom{n}{n}(
     * 1
     * n
     * ​
     *  )−(
     * 2
     * n
     * ​
     *  )+(
     * 3
     * n
     * ​
     *  )−⋯±(
     * n
     * n
     * ​
     *  ) times. By considering the binomial expansion of (1 - 1)^n(1−1)
     * n
     *  , this is in fact equal to 11.
     *
     * From Rectangle Area I, we know that the intersection of two axis-aligned rectangles is another axis-aligned rectangle (or nothing). So in particular, the intersection \bigcap_{i \in S} A_i⋂
     * i∈S
     * ​
     *  A
     * i
     * ​
     *   is always a rectangle (or nothing).
     *
     * Now our algorithm proceeds as follows: for every subset SS of \{1, 2, 3, \cdots, N\}{1,2,3,⋯,N} (where NN is the number of rectangles), we'll calculate the intersection of the rectangles in that subset \bigcap_{i \in S} A_i⋂
     * i∈S
     * ​
     *  A
     * i
     * ​
     *  , and then the area of that rectangle. This allows us to calculate algorithmically the right-hand side of the general equation we wrote earlier.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N * 2^N)O(N∗2
     * N
     *  ), where NN is the number of rectangles.
     *
     * Space Complexity: O(N)O(N).
     *
     */
    class Solution {
        public int rectangleArea(int[][] rectangles) {
            int N = rectangles.length;

            long ans = 0;
            for (int subset = 1; subset < (1<<N); ++subset) {
                int[] rec = new int[]{0, 0, 1_000_000_000, 1_000_000_000};
                int parity = -1;
                for (int bit = 0; bit < N; ++bit)
                    if (((subset >> bit) & 1) != 0) {
                        rec = intersect(rec, rectangles[bit]);
                        parity *= -1;
                    }
                ans += parity * area(rec);
            }

            long MOD = 1_000_000_007;
            ans %= MOD;
            if (ans < 0) ans += MOD;
            return (int) ans;
        }

        public long area(int[] rec) {
            long dx = Math.max(0, rec[2] - rec[0]);
            long dy = Math.max(0, rec[3] - rec[1]);
            return dx * dy;
        }

        public int[] intersect(int[] rec1, int[] rec2) {
            return new int[]{
                    Math.max(rec1[0], rec2[0]),
                    Math.max(rec1[1], rec2[1]),
                    Math.min(rec1[2], rec2[2]),
                    Math.min(rec1[3], rec2[3]),
            };
        }
    }
     /** Approach #2: Coordinate Compression
     * Intuition
     *
     * Image from problem description
     * Suppose instead of rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]], we had [[0,0,200,200],[100,0,200,300],[100,0,300,100]]. The answer would just be 100 times bigger.
     *
     * What about if rectangles = [[0,0,2,2],[1,0,2,3],[1,0,30002,1]] ? Only the blue region would have area 30000 instead of 1.
     *
     * Our idea is this: we'll take all the x and y coordinates, and re-map them to 0, 1, 2, ... etc. For example, if rectangles = [[0,0,200,200],[100,0,200,300],[100,0,300,100]], we could re-map it to [[0,0,2,2],[1,0,2,3],[1,0,3,1]]. Then, we can solve the problem with brute force. However, each region may actually represent some larger area, so we'll need to adjust for that at the end.
     *
     * Algorithm
     *
     * Re-map each x coordinate to 0, 1, 2, .... Independently, re-map all y coordinates too.
     *
     * We then have a problem that can be solved by brute force: for each rectangle with re-mapped coordinates (rx1, ry1, rx2, ry2), we can fill the grid grid[x][y] = True for rx1 <= x < rx2 and ry1 <= y < ry2.
     *
     * Afterwards, each grid[rx][ry] represents the area (imapx(rx+1) - imapx(rx)) * (imapy(ry+1) - imapy(ry)), where if x got remapped to rx, then imapx(rx) = x ("inverse-map-x of remapped-x equals x"), and similarly for imapy.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^3)O(N
     * 3
     *  ), where NN is the number of rectangles.
     *
     * Space Complexity: O(N^2)O(N
     * 2
     *  ).
     *
     */
     class Solution {
         public int rectangleArea(int[][] rectangles) {
             int N = rectangles.length;
             Set<Integer> Xvals = new HashSet();
             Set<Integer> Yvals = new HashSet();

             for (int[] rec: rectangles) {
                 Xvals.add(rec[0]);
                 Xvals.add(rec[2]);
                 Yvals.add(rec[1]);
                 Yvals.add(rec[3]);
             }

             Integer[] imapx = Xvals.toArray(new Integer[0]);
             Arrays.sort(imapx);
             Integer[] imapy = Yvals.toArray(new Integer[0]);
             Arrays.sort(imapy);

             Map<Integer, Integer> mapx = new HashMap();
             Map<Integer, Integer> mapy = new HashMap();
             for (int i = 0; i < imapx.length; ++i)
                 mapx.put(imapx[i], i);
             for (int i = 0; i < imapy.length; ++i)
                 mapy.put(imapy[i], i);

             boolean[][] grid = new boolean[imapx.length][imapy.length];
             for (int[] rec: rectangles)
                 for (int x = mapx.get(rec[0]); x < mapx.get(rec[2]); ++x)
                     for (int y = mapy.get(rec[1]); y < mapy.get(rec[3]); ++y)
                         grid[x][y] = true;

             long ans = 0;
             for (int x = 0; x < grid.length; ++x)
                 for (int y = 0; y < grid[0].length; ++y)
                     if (grid[x][y])
                         ans += (long) (imapx[x+1] - imapx[x]) * (imapy[y+1] - imapy[y]);

             ans %= 1_000_000_007;
             return (int) ans;
         }
     }

     /** Approach #3: Line Sweep
     * Intuition
     *
     * Imagine we pass a horizontal line from bottom to top over the shape. We have some active intervals on this horizontal line, which gets updated twice for each rectangle. In total, there are 2 * N2∗N events, and we can update our (up to NN) active horizontal intervals for each update.
     *
     * Algorithm
     *
     * For a rectangle like rec = [1,0,3,1], the first update is to add [1, 3] to the active set at y = 0, and the second update is to remove [1, 3] at y = 1. Note that adding and removing respects multiplicity - if we also added [0, 2] at y = 0, then removing [1, 3] at y = 1 will still leave us with [0, 2] active.
     *
     * This gives us a plan: create these two events for each rectangle, then process all the events in sorted order of y. The issue now is deciding how to process the events add(x1, x2) and remove(x1, x2) such that we are able to query() the total horizontal length of our active intervals.
     *
     * We can use the fact that our remove(...) operation will always be on an interval that was previously added. Let's store all the (x1, x2) intervals in sorted order. Then, we can query() in linear time using a technique similar to a classic LeetCode problem, Merge Intervals.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2 \log N)O(N
     * 2
     *  logN), where NN is the number of rectangles.
     *
     * Space Complexity: O(N)O(N).
     *
     */

     class Solution {
         public int rectangleArea(int[][] rectangles) {
             int OPEN = 0, CLOSE = 1;
             int[][] events = new int[rectangles.length * 2][];
             int t = 0;
             for (int[] rec: rectangles) {
                 events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
                 events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
             }

             Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

             List<int[]> active = new ArrayList();
             int cur_y = events[0][0];
             long ans = 0;
             for (int[] event: events) {
                 int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

                 // Calculate query
                 long query = 0;
                 int cur = -1;
                 for (int[] xs: active) {
                     cur = Math.max(cur, xs[0]);
                     query += Math.max(xs[1] - cur, 0);
                     cur = Math.max(cur, xs[1]);
                 }

                 ans += query * (y - cur_y);

                 if (typ == OPEN) {
                     active.add(new int[]{x1, x2});
                     Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
                 } else {
                     for (int i = 0; i < active.size(); ++i)
                         if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                             active.remove(i);
                             break;
                         }
                 }

                 cur_y = y;
             }

             ans %= 1_000_000_007;
             return (int) ans;
         }
     }

     /** Approach #4: Segment Tree
     * Intuition and Algorithm
     *
     * As in Approach #3, we want to support add(x1, x2), remove(x1, x2), and query(). While outside the scope of a typical interview, this is the perfect setting for using a segment tree. For completeness, we include the following implementation.
     *
     * You can learn more about Segment Trees by visiting the articles of these problems: Falling Squares, Number of Longest Increasing Subsequence.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N \log N)O(NlogN), where NN is the number of rectangles.
     *
     * Space Complexity: O(N)O(N).
     */

     class Solution {
         public int rectangleArea(int[][] rectangles) {
             int OPEN = 1, CLOSE = -1;
             int[][] events = new int[rectangles.length * 2][];
             Set<Integer> Xvals = new HashSet();
             int t = 0;
             for (int[] rec: rectangles) {
                 events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
                 events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
                 Xvals.add(rec[0]);
                 Xvals.add(rec[2]);
             }

             Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

             Integer[] X = Xvals.toArray(new Integer[0]);
             Arrays.sort(X);
             Map<Integer, Integer> Xi = new HashMap();
             for (int i = 0; i < X.length; ++i)
                 Xi.put(X[i], i);

             Node active = new Node(0, X.length - 1, X);
             long ans = 0;
             long cur_x_sum = 0;
             int cur_y = events[0][0];

             for (int[] event: events) {
                 int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
                 ans += cur_x_sum * (y - cur_y);
                 cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
                 cur_y = y;

             }

             ans %= 1_000_000_007;
             return (int) ans;
         }
     }

    class Node {
        int start, end;
        Integer[] X;
        Node left, right;
        int count;
        long total;

        public Node(int start, int end, Integer[] X) {
            this.start = start;
            this.end = end;
            this.X = X;
            left = null;
            right = null;
            count = 0;
            total = 0;
        }

        public int getRangeMid() {
            return start + (end - start) / 2;
        }

        public Node getLeft() {
            if (left == null) left = new Node(start, getRangeMid(), X);
            return left;
        }

        public Node getRight() {
            if (right == null) right = new Node(getRangeMid(), end, X);
            return right;
        }

        public long update(int i, int j, int val) {
            if (i >= j) return 0;
            if (start == i && end == j) {
                count += val;
            } else {
                getLeft().update(i, Math.min(getRangeMid(), j), val);
                getRight().update(Math.max(getRangeMid(), i), j, val);
            }

            if (count > 0) total = X[end] - X[start];
            else total = getLeft().total + getRight().total;

            return total;
        }
    }


    // recursively - drop overlapping areas if we already seen them, calc area of resulted unique rectangles

// maintain a list of non-overlapping rectangles to calculate final area.
// If a new rectangle does not overlap with any of the existing rectanlges, add it to the list.
// If there is an overlap, split the non-overlapping regions of the rectangle into smaller rectangles and compare with the rest of the list.

// O(n^2) time (we can use Segment Tree to make it nlogn)

    class Solution {
        long M= (long)1e9+7;
        public int rectangleArea(int[][] rectangles) {
            long res= 0;
            for (int[] r: rectangles) res+=areaOf(r[0], r[1], r[2], r[3]);
            for (int i=0; i<rectangles.length; i++){
                long overlap= overlap(rectangles, rectangles[i], i+1);
                res=(res-overlap+M)%M;
            }
            return (int)res;
        }
        public long areaOf(int x1, int y1, int x2, int y2){
            return (long)(x2-x1)*(y2-y1);
        }
        public long overlap(int[][] recs, int[] a, int idx){
            if (idx==recs.length) return 0;
            int[] b= recs[idx++];
            int left= Math.max(a[0], b[0]);
            int right= Math.min(a[2], b[2]);
            int down= Math.max(a[1], b[1]);
            int up= Math.min(a[3], b[3]);
            if (left>=right || up<=down) return overlap(recs, a, idx);
            long res=areaOf(left, down, right, up);
            if (a[0]<b[0]) res=(res+overlap(recs, new int[]{a[0], a[1], b[0], a[3]}, idx))%M;
            if (b[2]<a[2]) res=(res+overlap(recs, new int[]{b[2], a[1], a[2], a[3]}, idx))%M;
            if (a[1]<b[1]) res=(res+overlap(recs, new int[]{left, a[1], right, b[1]}, idx))%M;
            if (b[3]<a[3]) res=(res+overlap(recs, new int[]{left, b[3], right, a[3]}, idx))%M;
            return res;
        }
    }
}
