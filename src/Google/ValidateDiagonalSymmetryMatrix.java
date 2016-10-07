package Google;

/**
 * Created by cicean on 9/16/2016.
 *
 */
public class ValidateDiagonalSymmetryMatrix {

    public boolean isDiagonalSymmetryMatrix(int[][] matrix) {
        if (matrix == null) return false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++){
                if (matrix[i][j] != matrix[matrix[i].length - 1 - j][matrix.length - 1 - i]) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
		ValidateDiagonalSymmetryMatrix slt = new ValidateDiagonalSymmetryMatrix();
		int[][] matrix = {{1,5,3,4},{0,1,5,3},{9,0,1,5},{2,9,0,1}};
		System.out.println(slt.isDiagonalSymmetryMatrix(matrix));
	}
}
