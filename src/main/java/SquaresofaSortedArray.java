import java.util.*;

/**
 * 977. Squares of a Sorted Array
 * Easy
 *
 * 745
 *
 * 68
 *
 * Add to List
 *
 * Share
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Example 2:
 *
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 * Accepted
 * 152,254
 * Submissions
 * 210,905
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * sam_hamza
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 5
 *
 * Uber
 * |
 * 5
 *
 * Amazon
 * |
 * 4
 *
 * Google
 * |
 * 2
 *
 * Apple
 * |
 * 2
 *
 * Nutanix
 * |
 * 2
 * Merge Sorted Array
 * Easy
 * Sort Transformed Array
 * Medium
 */
public class SquaresofaSortedArray {

    /**
     * Approach 1: Sort
     * Intuition and Algorithm
     *
     * Create an array of the squares of each element, and sort them.
     * Complexity Analysis
     *
     * Time Complexity: O(N \log N)O(NlogN), where NN is the length of A.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution {
        public int[] sortedSquares(int[] A) {
            int N = A.length;
            int[] ans = new int[N];
            for (int i = 0; i < N; ++i)
                ans[i] = A[i] * A[i];

            Arrays.sort(ans);
            return ans;
        }
    }

    /**
     * Approach 2: Two Pointer
     * Intuition
     *
     * Since the array A is sorted, loosely speaking it has some negative elements with squares in decreasing order, then some non-negative elements with squares in increasing order.
     *
     * For example, with [-3, -2, -1, 4, 5, 6], we have the negative part [-3, -2, -1] with squares [9, 4, 1], and the positive part [4, 5, 6] with squares [16, 25, 36]. Our strategy is to iterate over the negative part in reverse, and the positive part in the forward direction.
     *
     * Algorithm
     *
     * We can use two pointers to read the positive and negative parts of the array - one pointer j in the positive direction, and another i in the negative direction.
     *
     * Now that we are reading two increasing arrays (the squares of the elements), we can merge these arrays together using a two-pointer technique.
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the length of A.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution_2 {
        public int[] sortedSquares(int[] A) {
            int N = A.length;
            int j = 0;
            while (j < N && A[j] < 0)
                j++;
            int i = j-1;

            int[] ans = new int[N];
            int t = 0;

            while (i >= 0 && j < N) {
                if (A[i] * A[i] < A[j] * A[j]) {
                    ans[t++] = A[i] * A[i];
                    i--;
                } else {
                    ans[t++] = A[j] * A[j];
                    j++;
                }
            }

            while (i >= 0) {
                ans[t++] = A[i] * A[i];
                i--;
            }
            while (j < N) {
                ans[t++] = A[j] * A[j];
                j++;
            }

            return ans;
        }
    }

    class Solution_3 {
        public int[] sortedSquares(int[] A) {

            if ( A == null || A.length == 0 ) return A;

            // Solution: Two Pointers
            int[] result = new int[A.length];
            int left = 0, right = A.length - 1, current = result.length - 1;

            while ( left <= right ) {

                if ( Math.abs( A[left] ) < Math.abs( A[right] ) ) {

                    result[current] = A[right] * A[right];
                    right--;

                } else {

                    result[current] = A[left] * A[left];
                    left++;

                }

                current--;

            }

            return result;

        }
    }

}
