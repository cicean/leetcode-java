/**
 * 1329. Sort the Matrix Diagonally
 * Medium
 *
 * 99
 *
 * 37
 *
 * Add to List
 *
 * Share
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 * Accepted
 * 5,930
 * Submissions
 * 7,581
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Quora
 * |
 * 3
 * Use a data structure to store all values of each diagonal.
 * How to index the data structure with the id of the diagonal?
 * All cells in the same diagonal (i,j) have the same difference so we can get the diagonal of a cell using the difference i-j.
 */
public class SorttheMatrixDiagonally {

    /**
     * Explanation
     * A[i][j] on the same diagonal have same value of i - j
     * For each diagonal,
     * put its elements together, sort, and set them back.
     *
     *
     * Complexity
     * Time O(MNlogD), where D is the length of diagonal with D = min(M,N).
     * Space O(MN)
     */

    public int[][] diagonalSort(int[][] A) {
        int m = A.length, n = A[0].length;
        HashMap<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.putIfAbsent(i - j, new PriorityQueue<>());
                d.get(i - j).add(A[i][j]);
            }
        }
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                A[i][j] = d.get(i - j).poll();
        return A;
    }

    class Solution {
        public int[][] diagonalSort(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            for(int i = 1; i < n; i++) {
                helper(mat, m - 1, i);
            }
            for(int i = 1; i < m - 1; i++) {
                helper(mat, i, n - 1);
            }
            return mat;
        }

        private void helper(int[][] mat, int xStart, int yStart) {
            int[] bucket = new int[101];
            for(int x = xStart, y = yStart; x >= 0 && y >= 0; x--, y--) {
                int val = mat[x][y];
                bucket[val]++;
            }
            int val = 100;
            for(int x = xStart, y = yStart; x >= 0 && y >= 0; x--, y--) {
                while(bucket[val] <= 0) {
                    val--;
                }
                mat[x][y] = val;
                bucket[val]--;
            }
        }
    }

}
