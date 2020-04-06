/**
 * 862. Shortest Subarray with Sum at Least K
 * Hard
 *
 * 781
 *
 * 23
 *
 * Add to List
 *
 * Share
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 *
 * If there is no non-empty subarray with sum at least K, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1], K = 1
 * Output: 1
 * Example 2:
 *
 * Input: A = [1,2], K = 4
 * Output: -1
 * Example 3:
 *
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 *
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 * Accepted
 * 22,399
 * Submissions
 * 96,469
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ywang172
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Goldman Sachs
 * |
 * 6
 *
 * Google
 * |
 * 6
 */
public class ShortestSubarraywithSumatLeastK {

    /**
     * Solution
     * Approach 1: Sliding Window
     * Intuition
     *
     * We can rephrase this as a problem about the prefix sums of A. Let P[i] = A[0] + A[1] + ... + A[i-1]. We want the smallest y-x such that y > x and P[y] - P[x] >= K.
     *
     * Motivated by that equation, let opt(y) be the largest x such that P[x] <= P[y] - K. We need two key observations:
     *
     * If x1 < x2 and P[x2] <= P[x1], then opt(y) can never be x1, as if P[x1] <= P[y] - K, then P[x2] <= P[x1] <= P[y] - K but y - x2 is smaller. This implies that our candidates x for opt(y) will have increasing values of P[x].
     *
     * If opt(y1) = x, then we do not need to consider this x again. For if we find some y2 > y1 with opt(y2) = x, then it represents an answer of y2 - x which is worse (larger) than y1 - x.
     *
     * Algorithm
     *
     * Maintain a "monoqueue" of indices of P: a deque of indices x_0, x_1, ... such that P[x_0], P[x_1], ... is increasing.
     *
     * When adding a new index y, we'll pop x_i from the end of the deque so that P[x_0], P[x_1], ..., P[y] will be increasing.
     *
     * If P[y] >= P[x_0] + K, then (as previously described), we don't need to consider this x_0 again, and we can pop it from the front of the deque.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the length of A.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution {
        public int shortestSubarray(int[] A, int K) {
            int N = A.length;
            long[] P = new long[N+1];
            for (int i = 0; i < N; ++i)
                P[i+1] = P[i] + (long) A[i];

            // Want smallest y-x with P[y] - P[x] >= K
            int ans = N+1; // N+1 is impossible
            Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P

            for (int y = 0; y < P.length; ++y) {
                // Want opt(y) = largest x with P[x] <= P[y] - K;
                while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])
                    monoq.removeLast();
                while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)
                    ans = Math.min(ans, y - monoq.removeFirst());

                monoq.addLast(y);
            }

            return ans < N+1 ? ans : -1;
        }
    }

    class Solution {
        public int shortestSubarray(int[] A, int K) {
            int[] sum = new int[A.length + 1];
            int[] deque = new int[A.length + 1];
            for (int i = 0; i < A.length; i++) {
                sum[i + 1] = sum[i] + A[i];
            }
            int st = 0, ed = 0, ans = A.length + 1;
            for (int i = 0; i <= A.length; i++) {
                while (st < ed && sum[i] - sum[deque[st]] >= K) {
                    ans = Math.min(ans, i - deque[st++]);
                }
                while (st < ed && sum[i] <= sum[deque[ed - 1]]) {
                    ed--;
                }
                deque[ed++] = i;
            }
            return ans == A.length + 1 ? -1 : ans;
        }
    }
}
