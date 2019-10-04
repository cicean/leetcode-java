import java.util.*;

/*
 59	Spiral Matrix II	31.7%	Medium
 Problem:    Spiral Matrix II
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/spiral-matrix-ii/
 Notes:
 Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 For example,
 Given n = 3,
 You should return the following matrix:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
 Solution: ...
 */

public class SpiralMatrixII {
	
	public int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];
        int[][] res = new int[n][n];
        int m = n, row = 0, col = -1, num = 0;
        while (true) {
            for (int i = 0; i < m; ++i) res[row][++col] = ++num;
            if (--n == 0) break;
            for (int i = 0; i < n; ++i) res[++row][col] = ++num;
            if (--m == 0) break;
            for (int i = 0; i < m; ++i) res[row][--col] = ++num;
            if (--n == 0) break;
            for (int i = 0; i < n; ++i) res[--row][col] = ++num;
            if (--m == 0) break;
        }
        return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpiralMatrixII slt = new SpiralMatrixII();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input an integer to create a genneral matrix");
		int num=sc.nextInt();
		int [][] res = slt.generateMatrix(num);
		
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}

}
