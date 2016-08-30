import java.util.*;

/*
 54	Spiral Matrix	20.8%	Medium
 Problem:    Spiral Matrix
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/spiral-matrix/
 Notes:
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 For example,
 Given the following matrix:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].
 Solution: ...
 1.Refer.:
 * 1.0
 * 0.设置遍历范围；
 * 1.顺时针遍历四个方向；
 * 2.重复step0&1直到所有元素遍历；
 * 
 * 1.1
 * 同1.0
 */
public class SpiralMatrix {
	 public List<Integer> spiralOrder(int[][] matrix) {
	        ArrayList<Integer> res = new ArrayList<Integer>();
	        if (matrix.length == 0 || matrix[0].length == 0) return res;
	        int n = matrix.length, m = matrix[0].length, row = 0, col = -1;
	        while (true) {
	            for (int i = 0; i < m; ++i) res.add(matrix[row][++col]);
	            if (--n == 0) break;
	            for (int i = 0; i < n; ++i) res.add(matrix[++row][col]);
	            if (--m == 0) break;
	            for (int i = 0; i < m; ++i) res.add(matrix[row][--col]);
	            if (--n == 0) break;
	            for (int i = 0; i < n; ++i) res.add(matrix[--row][col]);
	            if (--m == 0) break;
	        }
	        return res;
	    }
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
		 SpiralMatrix slt= new SpiralMatrix();
			int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};// inster a function to creat a general matrix
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
			
			List<Integer> res=slt.spiralOrder(matrix);
			for (Integer i : res) {
				System.out.print(i + " ");
			}
		}
}
