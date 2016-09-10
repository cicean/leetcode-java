package facebook;

/**
 * Created by cicean on 9/4/2016.
 *
 */
public class SubRectangleMatrixAreaEqualsK {
    public boolean isContainsSubArea(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        return subMatrixSum(matrix, 0, 0, 0, 0, k);
    }

    private boolean subMatrixSum(int[][] cum, int headi, int headj, int taili, int tailj, int target) {
        if (headi < taili || headj < tailj) {
            return false;
        }
        int sum = subsum(cum, headi, headj, taili, tailj);
        if (sum == target) {
            return true;
        } else if (sum > target) {
            return subMatrixSum(cum, headi, headj, taili + 1, tailj, target) || subMatrixSum(cum, headi, headj, taili, tailj + 1, target);
        } else {
            return subMatrixSum(cum, headi + 1, headj, taili, tailj, target) || subMatrixSum(cum, headi, headj + 1, taili, tailj, target);
        }
    }

    private int subsum(int[][] matrix, int x2, int y2, int x1, int y1) {
        int res = 0;

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; i <= y2; j++) {
                res += matrix[i][j];
            }
        }
        return res;
    }
}
