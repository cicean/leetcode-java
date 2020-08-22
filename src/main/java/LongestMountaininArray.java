/**
 * 845. Longest Mountain in Array
 * Medium
 *
 * 492
 *
 * 17
 *
 * Add to List
 *
 * Share
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 *
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 *
 * Given an array A of integers, return the length of the longest mountain.
 *
 * Return 0 if there is no mountain.
 *
 * Example 1:
 *
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 *
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 * Note:
 *
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * Follow up:
 *
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 * Accepted
 * 28,797
 * Submissions
 * 79,375
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 4
 */
public class LongestMountaininArray {

    /**
     * Approach #1: Two Pointer [Accepted]
     * Intuition
     *
     * Without loss of generality, a mountain can only start after the previous one ends.
     *
     * This is because if it starts before the peak, it will be smaller than a mountain starting previous; and it is impossible to start after the peak.
     *
     * Algorithm
     *
     * For a starting index base, let's calculate the length of the longest mountain A[base], A[base+1], ..., A[end].
     *
     * If such a mountain existed, the next possible mountain will start at base = end; if it didn't, then either we reached the end, or we have A[base] > A[base+1] and we can start at base + 1.
     *
     * Example
     *
     * Here is a worked example on the array A = [1, 2, 3, 2, 1, 0, 2, 3, 1]:
     *
     * Worked example of A = [1,2,3,2,1,0,2,3,1]
     *
     * base starts at 0, and end travels using the first while loop to end = 2 (A[end] = 3), the potential peak of this mountain. After, it travels to end = 5 (A[end] = 0) during the second while loop, and a candidate answer of 6 (base = 0, end = 5) is recorded.
     *
     * Afterwards, base is set to 5 and the process starts over again, with end = 7 the peak of the mountain, and end = 8 the right boundary, and the candidate answer of 4 (base = 5, end = 8) being recorded.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the length of A.
     *
     * Space Complexity: O(1)O(1).
     */

    class Solution {
        public int longestMountain(int[] A) {
            int N = A.length;
            int ans = 0, base = 0;
            while (base < N) {
                int end = base;
                // if base is a left-boundary
                if (end + 1 < N && A[end] < A[end + 1]) {
                    // set end to the peak of this potential mountain
                    while (end + 1 < N && A[end] < A[end + 1]) end++;

                    // if end is really a peak..
                    if (end + 1 < N && A[end] > A[end + 1]) {
                        // set end to the right-boundary of mountain
                        while (end + 1 < N && A[end] > A[end + 1]) end++;
                        // record candidate answer
                        ans = Math.max(ans, end - base + 1);
                    }
                }

                base = Math.max(end, base + 1);
            }

            return ans;
        }
    }

    class Solution2 {
        public int longestMountain(int[] A) {
            int N = A.length, res = 0;
            int[] up = new int[N], down = new int[N];
            for (int i = N - 2; i >= 0; --i) if (A[i] > A[i + 1]) down[i] = down[i + 1] + 1;
            for (int i = 0; i < N; ++i) {
                if (i > 0 && A[i] > A[i - 1]) up[i] = up[i - 1] + 1;
                if (up[i] > 0 && down[i] > 0) res = Math.max(res, up[i] + down[i] + 1);
            }
            return res;
        }
    }

    class Solution {
        public int longestMountain(int[] A) {
            if (A == null || A.length == 0) {
                return 0;
            }

            int start = 0, res = 0;
            for (int i = 1; i < A.length;) {
                // skip the flat part and find the next trough
                while (i < A.length && A[i] <= A[i - 1]) {
                    i++;
                }
                start = i - 1; // start of mountain

                while (i < A.length && A[i] > A[i - 1]) {
                    i++;
                }

                if (i < A.length && A[i] < A[i - 1]) {
                    // peak found
                    while (i < A.length && A[i] < A[i - 1]) {
                        i++;
                    }
                    res = Math.max(res, i - start); // end of mountain
                }
                // start = -1;
            }

            return res;
        }
    }

}
