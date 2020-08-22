/**
 * In a binary matrix (all elements are 0 and 1), every row is sorted in ascending order (0 to the left of 1). Find the leftmost column index with a 1 in it.
 *
 * Example 1:
 *
 * Input:
 * [[0, 0, 0, 1],
 *  [0, 0, 1, 1],
 *  [0, 1, 1, 1],
 *  [0, 0, 0, 0]]
 * Output: 1
 * Example 2:
 *
 * Input:
 * [[0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0]]
 * Output: -1
 * Expected solution better than O(r * c).
 */
public class LeftmostOne {

    class Solution {
        /**
         * @param arr: The 2-dimension array
         * @return: Return the column the leftmost one is located
         */
        public int findLeftMostColumn(int[][] arr) {
            // Write your code here
            int n =  arr.length;
            int m = arr[0].length;
            int ans = 0;
            for (int i = 0; i < m; i++) {
                if (arr[0][i] == 1) {
                    ans = i;
                    break;
                }
            }
            for (int i = 1; i < n; i++) {
                if (arr[i][ans] == 0) {
                    continue;
                }
                while (ans > 0 && arr[i][ans - 1] == 1) {
                    ans--;
                }
            }
            return ans;
        }
    }
}
