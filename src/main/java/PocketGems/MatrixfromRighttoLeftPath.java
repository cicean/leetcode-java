package PocketGems;

/**
 * Created by cicean on 9/22/2016.
 */
public class MatrixfromRighttoLeftPath {

    public void findleftZero(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 1) {
        	System.out.println("i = " + i + ", j = " + j);
            if (matrix[i][j - 1] == 1) j--;
            else i++;

            System.out.println("i = " + i + ", j = " + j);
        }
    }
    
    public static void main(String[] args) {
		MatrixfromRighttoLeftPath slt = new MatrixfromRighttoLeftPath();
		int[][] matrix = {{0,1,1},{0,1,1},{1,0,1}};
		slt.findleftZero(matrix);
	}

}
