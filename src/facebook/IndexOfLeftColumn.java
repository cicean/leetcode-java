package facebook;

/**
 *
 * 给一个矩阵
 * 类似这样的
 * [[0, 0, 1, 1, 1],
 * [0, 1, 1, 1, 1],
 * [0, 0, 1, 1, 1],
 * [0, 0, 0, 0, 0],
 * [0, 0, 0, 1, 1]]
 * 1. 每一个cell 要不是0 要不是1
 * 2. 每一行只要发现一个1， 剩下的都是1
 * 3. 这个数组是正方形的
 * 问题：找到最左边的有1的列
 *
 *     这个例子的结果是 2
 *
 *
 * 我的方法：对每一个row 用binary search
 */

public class IndexOfLeftColumn {

    public int indexOfLeftColumn(int[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        };

        int left = 0;

        for (int i = 0; i < matrix.length; i++) {

            int tmp = binarySeachOfTheRow(matrix[i]);

            if (tmp == 0) {
                return 1;
            }

            left = Math.min(left, tmp);

        }

        return left + 1;
    }

    private int binarySeachOfTheRow(int[] a) {
        int start = 0, end = a.length;

        if (a[start] == 1) {
            return start;
        }

        while (start + 1< end) {
            int mid = start + (end - start) / 2;
            if (a[mid] == 1) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return end;

    }
}
