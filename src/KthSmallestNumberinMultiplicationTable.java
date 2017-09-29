import java.util.*;

/**
 * 668. Kth Smallest Number in Multiplication Table
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

 Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

 Example 1:
 Input: m = 3, n = 3, k = 5
 Output:
 Explanation:
 The Multiplication Table:
 1	2	3
 2	4	6
 3	6	9

 The 5-th smallest number is 3 (1, 2, 2, 3, 3).
 Example 2:
 Input: m = 2, n = 3, k = 6
 Output:
 Explanation:
 The Multiplication Table:
 1	2	3
 2	4	6

 The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
 Note:
 The m and n will be in the range [1, 30000].
 The k will be in the range [1, m * n]

 */

public class KthSmallestNumberinMultiplicationTable {

  /**
   * Approach #1: Brute Force [Memory Limit Exceeded]

   Intuition and Algorithm

   Create the multiplication table and sort it, then take the k^{th}k
   ​th
   ​​  element.

   Complexity Analysis

   Time Complexity: O(m*n)O(m∗n) to create the table, and O(m*n\log(m*n))O(m∗nlog(m∗n)) to sort it.

   Space Complexity: O(m*n)O(m∗n) to store the table.
   */

  public int findKthNumber(int m, int n, int k) {
    int[] table = new int[m*n];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        table[(i - 1) * n + j - 1] = i * j;
      }
    }
    Arrays.sort(table);
    return table[k-1];
  }

  /**
   *Approach #2: Next Heap [Time Limit Exceeded]

   Intuition

   Maintain a heap of the smallest unused element of each row. Then, finding the next element is a pop operation on the heap.

   Algorithm

   Our heap is going to consist of elements \text{(val, root)}(val, root), where \text{val}val is the next unused value of that row, and \text{root}root was the starting value of that row.

   We will repeatedly find the next lowest element in the table. To do this, we pop from the heap. Then, if there's a next lowest element in that row, we'll put that element back on the heap.

   Time Complexity: O(k * m \log m) = O(m^2 n \log m)O(k∗mlogm)=O(m*nlogm). Our initial heapify operation is O(m)O(m). Afterwards, each pop and push is O(m \log m)O(mlogm), and our outer loop is O(k) = O(m*n)O(k)=O(m∗n)

   Space Complexity: O(m)O(m). Our heap is implemented as an array with mm elements.

   */
  class Node {

    int val;
    int root;

    public Node(int v, int r) {
      val = v;
      root = r;
    }
  }

  public int findKthNumber_2(int m, int n, int k) {
    PriorityQueue<Node> heap = new PriorityQueue<Node>(m,
        Comparator.<Node> comparingInt(node -> node.val));

    for (int i = 1; i <= m; i++) {
      heap.offer(new Node(i, i));
    }

    Node node = null;
    for (int i = 0; i < k; i++) {
      node = heap.poll();
      int nxt = node.val + node.root;
      if (nxt <= node.root * n) {
        heap.offer(new Node(nxt, node.root));
      }
    }
    return node.val;
  }



  /**
   * Approach #3: Binary Search [Accepted]

   Intuition

   As \text{k}k and \text{m*n}m*n are up to 9 * 10^89∗10
   ​8
   ​​ , linear solutions will not work. This motivates solutions with \loglog complexity, such as binary search.

   Algorithm

   Let's do the binary search for the answer \text{A}A.

   Say enough(x) is true if and only if there are \text{k}k or more values in the multiplication table that are less than or equal to \text{x}x. Colloquially, enough describes whether \text{x}x is large enough to be the k^{th}k
   ​th
   ​​  value in the multiplication table.

   Then (for our answer \text{A}A), whenever x ≥ Ax ≥ A, enough(x) is True; and whenever x < Ax < A, enough(x) is False.

   In our binary search, our loop invariant is enough(hi) = True. At the beginning, enough(m*n) = True, and whenever hi is set, it is set to a value that is "enough" (enough(mi) = True). That means hi will be the lowest such value at the end of our binary search.

   This leaves us with the task of counting how many values are less than or equal to \text{x}x. For each of \text{m}m rows, the i^{th}i
   ​th
   ​​  row looks like \text{[i, 2*i, 3*i, ..., n*i]}[i, 2*i, 3*i, ..., n*i]. The largest possible k*i ≤ xk*i ≤ x that could appear is \text{k = x // i}k = x // i. However, if \text{x}x is really big, then perhaps k > nk > n, so in total there are \text{min(k, n) = min(x // i, n)}min(k, n) = min(x // i, n) values in that row that are less than or equal to \text{x}x.

   After we have the count of how many values in the table are less than or equal to \text{x}x, by the definition of enough(x), we want to know if that count is greater than or equal to \text{k}k.

   Complexity Analysis

   Time Complexity: O(m * \log (m*n))O(m∗log(m∗n)). Our binary search divides the interval \text{[lo, hi]}[lo, hi] into half at each step. At each step, we call enough which requires O(m)O(m) time.

   Space Complexity: O(1)O(1). We only keep integers in memory during our intermediate calculations.
   */

  public boolean enough(int x, int m, int n, int k) {
    int count = 0;
    for (int i = 1; i <= m; i++) {
      count += Math.min(x / i, n);
    }
    return count >= k;
  }

  public int findKthNumber_3(int m, int n, int k) {
    int lo = 1, hi = m * n;
    while (lo < hi) {
      int mi = lo + (hi - lo) / 2;
      if (!enough(mi, m, n, k)) lo = mi + 1;
      else hi = mi;
    }
    return lo;
  }

}
