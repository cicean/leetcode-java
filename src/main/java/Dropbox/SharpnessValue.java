package Dropbox;

/**
 * Created by cicean on 10/17/2016.
 * /* 2-d array of "sharpness" values. Fine the path from left to right which has the highest minimum sharpness.
 * ·�������Ǵ������ң����и���ʼ�㣬Ȼ��ÿ��Ҫ�����ϣ����һ�������һ����Ҳ����˵��·������������n������·��Ϊ(i1,1),(i2,2),...,(in,n)��
 * ·����������ÿ��ik��i{k-1}�ľ��벻�ܴ���1
 * https://mzhang4.gitbooks.io/interview/content/dropbox/sharpness_value.html
 */
public class SharpnessValue {

    /**
     *
     */

    public double maxPath(double[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        double res = 0;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int r = i;
                double t = matrix[r][j];
                if (r - 1 >= 0) t = Math.max(t, matrix[r - 1][j]);
                if (r + 1 < m) t = Math.max(t, matrix[r + 1][j]);
                matrix[i][j] = Math.min(matrix[i][j], t);

                if (j == n - 1) {
                    res = Math.max(res, matrix[i][j]);
                }

            }
        }

        return res;

    }
}
