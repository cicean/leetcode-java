/**
 * Created by cicean on 8/29/2016.
 *  308. Range Sum Query 2D - Mutable  QuestionEditorial Solution  My Submissions
 Total Accepted: 5384 Total Submissions: 25797 Difficulty: Hard
 Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Range Sum Query 2D
 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

 Example:
 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 update(3, 2, 2)
 sumRegion(2, 1, 4, 3) -> 10
 Note:
 The matrix is only modifiable by the update function.
 You may assume the number of calls to update and sumRegion function is distributed evenly.
 You may assume that row1 ≤ row2 and col1 ≤ col2.
 Hide Company Tags Google
 Hide Tags Binary Indexed Tree Segment Tree
 Hide Similar Problems (M) Range Sum Query 2D - Immutable (M) Range Sum Query - Mutable

 */
public class RangeSumQuery2DMutable {

    /**
     * 注意这道题说明了"calls to update and sumRegion function is distributed evenly"。我们可以先不考虑这道题的限制，根据这个两个方法使用次数分情况讨论：

     update多，sumRegion少
     这种情况比较简单，我们可以直接复制矩阵，update的时候直接update对应点的值即可，sumRegion直接遍历指定范围内的值就可以了。
     update: O(1), sumRegion: O(mn)
     update少，sumRegion多。
     我们可以不复制整个矩阵，而是在每个点处存(0, 0)到该的长方形内所有元素的和，这样的话，sumRegion的复杂度会很低，update的时候我们需要update所有受影响的sum。
     update: O(mn), sumRegion: O(1)
     update多，sumRegion多（本题情况）
     我们可以每个点存对于该行，起始点到该点为止的sum。这样话，update的话，我们只需要update该行受影响的sum即可。sumRegion的话，我们只需要遍历相应行，加上不同行的对应sum即可。
     update: O(n), sumRegion: O(m)
     当然，对于这种类型的题目，使用一些高级数据结构会更时间复杂度会更低，能达到logn，如二维线段树。这里只涉及基本的数据结构，尽量不搞复杂。

     复杂度:
     注：m指行数，n指列数，这里global的矩阵不算各个方法的extra space。

     update
     time: O(n), space: O(1)

     sumRegion
     time: O(m), space: O(1)
     */

    public class NumMatrix {
        int[][] rowSums;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0)
                return;
            rowSums = new int[matrix.length][matrix[0].length];

            // 建rowSums矩阵
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    rowSums[i][j] = matrix[i][j] + (j == 0 ? 0 : rowSums[i][j - 1]);
                }
            }
        }

        public void update(int row, int col, int val) {
            // 求出新值与旧值的差
            int diff = val - (rowSums[row][col] - (col == 0 ? 0 : rowSums[row][col - 1]));

            // 更新该行受影响的sum
            for (int j = col; j < rowSums[0].length; j++) {
                rowSums[row][j] += diff;
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;

            // 逐行求和，每行的相应和为两sum相减
            for (int i = row1; i <= row2; i++) {
                res += rowSums[i][col2] - (col1 == 0 ? 0 :rowSums[i][col1 - 1]);
            }
            return res;
        }
    }


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);

    public class NumMatrix {

        private int[][] rowsum;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0) return;

            rowsum = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    rowsum[i][j] = matrix[i][j] + ((j == 0) ? 0 : matrix[i][j - 1]);
                }
            }
        }

        public void update(int row, int col, int val) {
            if (row < 0 || col < 0 || row > rowsum.length - 1 || col > rowsum[0].length - 1) return;

            int diff = val - (rowsum[row][col] - (col == 0 ? 0 : rowsum[row][col - 1]));
            for (int j = col; j < rowsum[0].length; j++) {
                rowsum[row][j] += diff;
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (col1 < 0 || col1 > rowsum[0].length - 1 || col1 < 0 || col2 > rowsum[0].length - 1 || col1 > col2) return 0;

            int res = 0;
            for (int i = row1; i <= row2; i++) { // you can add the difine row1 < row2
                res += rowsum[i][col2] - (col1 == 0 ? 0 : rowsum[i][col1 - 1]);
            }

            return res;
        }
    }

}
