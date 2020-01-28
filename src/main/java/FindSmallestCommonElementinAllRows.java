/**
 * 1198. Find Smallest Common Element in All Rows
 * Medium
 *
 * 63
 *
 * 6
 *
 * Add to List
 *
 * Share
 * Given a matrix mat where every row is sorted in increasing order, return the smallest common element in all rows.
 *
 * If there is no common element, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= mat.length, mat[i].length <= 500
 * 1 <= mat[i][j] <= 10^4
 * mat[i] is sorted in increasing order.
 * Accepted
 * 5,590
 * Submissions
 * 7,543
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * coding4232
 * Notice that each row has no duplicates.
 * Is counting the frequency of elements enough to find the answer?
 * Use a data structure to count the frequency of elements.
 * Find an element whose frequency equals the number of rows.
 */

public class FindSmallestCommonElementinAllRows {

    class Solution {

        /**
         * nlogn
         * @param mat
         * @return
         */
        public int smallestCommonElement(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            int res = -1;
            for (int j = 0; j < n; j++) {
                int a = mat[0][j];
                int count = 1;
                for(int i = 1; i < m; i++) {
                    if (binarySearch(mat[i], a)) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == m) {
                    return a;
                }
            }
            return res;
        }

        public boolean binarySearch(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return false;
            }

            int start = 0, end = nums.length - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    end = mid;
                } else if (nums[mid] < target) {
                    start = mid;
                    // or start = mid + 1
                } else {
                    end = mid;
                    // or end = mid - 1
                }
            }

            if (nums[start] == target) {
                return true;
            }
            if (nums[end] == target) {
                return true;
            }
            return false;
        }
    }
}
