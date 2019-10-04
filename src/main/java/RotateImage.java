/**
 * 48	Rotate Image	31.9%	Medium
 * Source : http://oj.leetcode.com/problems/rotate-image

 *
 * 0.Problem:
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 * 
 * 1.Refer.:
 * 倒三角内数据顺时针方向向下一个三角内交换，四次完成
 * Solution: 1. 123   ->  147   ->   741    (preferable)
              456       258        852
              789       369        963
           2. Rotate one-fourth of the image clockwise.
 */

public class RotateImage {
	public void rotate_1(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n/2;j++){
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }
    public void rotate_2(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;
        int level = 0;
        while (level < n / 2) {
            for (int i = level; i < n - 1 - level; ++i) {
                int tmp = matrix[i][level];
                matrix[i][level] = matrix[n - 1 - level][i];
                matrix[n - 1 - level][i] = matrix[n - 1 - i][n - 1 - level];
                matrix[n - 1 - i][n - 1 - level] = matrix[level][n - 1 - i];
                matrix[level][n - 1 - i] = tmp;
            }
            ++level;
        }
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    	RotateImage slt=new RotateImage();
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		slt.rotate_1(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
