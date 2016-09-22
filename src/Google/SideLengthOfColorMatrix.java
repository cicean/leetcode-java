package Google;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author cicean
 * 一个矩阵，每一块都被涂了颜色，找到给定点所在的那一块的颜色的边长
 */

public class SideLengthOfColorMatrix {

	private class Position {
		public int x;
		public int y;
		
		public Position(int i, int j) {
			this.x = i;
			this.y = j;
		}
		
	}

	private int length = 0;

	public int lengthOfCorlorMatrix(int i, int j, int[][] matrix) {

		List<Position> res = new ArrayList<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		colorMatrixre(matrix, matrix[i][j], i, j, res, visited);
		return length;
		
	}
	
	private void colorMatrixre(int[][] matrix, int target, int i, int j, List<Position> res, boolean[][] visited) {
		if (matrix == null) return;
		
		int m = matrix.length;
		int n = matrix[0].length;
		
		System.out.println("i = " + i + ", j = " + j);
		
		if (i < 0 || i >= m || j < 0 || j >= n)  {
			length++;
			return;
		}

		if (visited[i][j]) return;
			
		if (matrix[i][j] != target) {
			length++;
			return;
		}
		
		visited[i][j] = true;
		Position p = new Position(i, j);
		res.add(p);
		colorMatrixre(matrix, target, i + 1, j, res, visited);
		colorMatrixre(matrix, target, i - 1, j, res, visited);
		colorMatrixre(matrix, target, i, j + 1, res, visited);
		colorMatrixre(matrix, target, i, j - 1, res, visited);
		
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,1, 1, 2},{1, 1, 2, 2},{1, 1, 3,3}};
		SideLengthOfColorMatrix slt = new SideLengthOfColorMatrix();
		System.out.println(slt.lengthOfCorlorMatrix(0, 0, matrix));
	}
	
}
