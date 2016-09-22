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
 You may assume that row1 �� row2 and col1 �� col2.
 Hide Company Tags Google
 Hide Tags Binary Indexed Tree Segment Tree
 Hide Similar Problems (M) Range Sum Query 2D - Immutable (M) Range Sum Query - Mutable

 */
public class RangeSumQuery2DMutable {

    /**
     * ע�������˵����"calls to update and sumRegion function is distributed evenly"�����ǿ����Ȳ��������������ƣ����������������ʹ�ô�����������ۣ�

     update�࣬sumRegion��
     ��������Ƚϼ򵥣����ǿ���ֱ�Ӹ��ƾ���update��ʱ��ֱ��update��Ӧ���ֵ���ɣ�sumRegionֱ�ӱ���ָ����Χ�ڵ�ֵ�Ϳ����ˡ�
     update: O(1), sumRegion: O(mn)
     update�٣�sumRegion�ࡣ
     ���ǿ��Բ������������󣬶�����ÿ���㴦��(0, 0)���õĳ�����������Ԫ�صĺͣ������Ļ���sumRegion�ĸ��ӶȻ�ܵͣ�update��ʱ��������Ҫupdate������Ӱ���sum��
     update: O(mn), sumRegion: O(1)
     update�࣬sumRegion�ࣨ���������
     ���ǿ���ÿ�������ڸ��У���ʼ�㵽�õ�Ϊֹ��sum����������update�Ļ�������ֻ��Ҫupdate������Ӱ���sum���ɡ�sumRegion�Ļ�������ֻ��Ҫ������Ӧ�У����ϲ�ͬ�еĶ�Ӧsum���ɡ�
     update: O(n), sumRegion: O(m)
     ��Ȼ�������������͵���Ŀ��ʹ��һЩ�߼����ݽṹ���ʱ�临�ӶȻ���ͣ��ܴﵽlogn�����ά�߶���������ֻ�漰���������ݽṹ���������㸴�ӡ�

     ���Ӷ�:
     ע��mָ������nָ����������global�ľ��������������extra space��

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

            // ��rowSums����
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    rowSums[i][j] = matrix[i][j] + (j == 0 ? 0 : rowSums[i][j - 1]);
                }
            }
        }

        public void update(int row, int col, int val) {
            // �����ֵ���ֵ�Ĳ�
            int diff = val - (rowSums[row][col] - (col == 0 ? 0 : rowSums[row][col - 1]));

            // ���¸�����Ӱ���sum
            for (int j = col; j < rowSums[0].length; j++) {
                rowSums[row][j] += diff;
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;

            // ������ͣ�ÿ�е���Ӧ��Ϊ��sum���
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
