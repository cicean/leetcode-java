import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 311. Sparse Matrix Multiplication
 * Medium
 *
 * 352
 *
 * 141
 *
 * Add to List
 *
 * Share
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 *
 * Input:
 *
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 *
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *
 * Output:
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 * Accepted
 * 77,404
 * Submissions
 * 131,587
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 3
 *
 * Google
 * |
 * 2

 * @author cicean
 *
 */

public class SparseMatrixMultiplication {

	// sparse matrices means the 0 is more 
	// so key point is the find 0 and skip it, 
	//becuase the stander matices Multipy the runtime will out of Oj
	
	public int[][] multiply(int[][] A, int[][] B) {
		int m = A.length;
		int n = A[0].length;
		int bn = B[0].length;
		int[][] C = new int[m][bn];

		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (A[i][k] == 0)
					continue;
				for (int j = 0; j < bn; j++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}

	//A sparse matrix can be represented as a sequence of rows, 
	//each of which is a sequence of (column-number, value) pairs of the nonzero values in the row.
	public int[][] multiply_1(int[][] A, int[][] B) {
		if (A == null || A[0] == null || B == null || B[0] == null)
			return null;
		int m = A.length, n = A[0].length, l = B[0].length;
		int[][] C = new int[m][l];
		Map<Integer, HashMap<Integer, Integer>> tableB = new HashMap<>();

		for (int k = 0; k < n; k++) {
			tableB.put(k, new HashMap<Integer, Integer>());
			for (int j = 0; j < l; j++) {
				if (B[k][j] != 0) {
					tableB.get(k).put(j, B[k][j]);
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (A[i][k] != 0) {
					for (Integer j : tableB.get(k).keySet()) {
						C[i][j] += A[i][k] * tableB.get(k).get(j);
					}
				}
			}
		}
		return C;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
