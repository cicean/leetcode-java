/*
 * 240	Search a 2D Matrix II	28.3%	Medium
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

Hide Tags Divide and Conquer Binary Search
Hide Similar Problems (M) Search a 2D Matrix

 */
public class Searcha2DMatrixII {
	
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
        	return false;
        } 
        int i = 0;
        int j = matrix[0].length -1;
        
        while(i <matrix.length && j>=0) {
        	if (matrix[i][j] == target) {
        		return true;
        	} else if (matrix[i][j] < target) {
        		i++;
        	} else {
        		j--;
        	}
        }
        
        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Searcha2DMatrixII  slt = new Searcha2DMatrixII();
		int[][] matrix ={{1,   4,  7, 11, 15},
				{2,   5,  8, 12, 19},
				{3,   6,  9, 16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}
				};
		int target = 26;
		System.out.println(slt.searchMatrix(matrix, target));
	}
	

}
