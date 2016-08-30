import java.util.Arrays;
import java.util.Stack;


/*
 * 221	Maximal Square	20.2%	Medium
 * Given a 2D binary matrix filled with 0's and 1's, 
 * find the largest square containing all 1's and return its area.
 * 
 * For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

复杂度

时间 O(MN) 空间 O(MN)

思路
当我们判断以某个点为正方形右下角时最大的正方形时，那它的上方，左方和左上方三个点也一定是某个正方形的右下角，
否则该点为右下角的正方形最大就是它自己了。这是定性的判断，那具体的最大正方形边长呢？我们知道，
该点为右下角的正方形的最大边长，最多比它的上方，左方和左上方为右下角的正方形的边长多1，
最好的情况是是它的上方，左方和左上方为右下角的正方形的大小都一样的，这样加上该点就可以构成一个更大的正方形。
但如果它的上方，左方和左上方为右下角的正方形的大小不一样，合起来就会缺了某个角落，这时候只能取那三个正方形中最小的正方形的边长加1了。
假设dpi表示以i,j为右下角的正方形的最大边长，则有

dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
当然，如果这个点在原矩阵中本身就是0的话，那dpi肯定就是0了。


 * 
 * 题解：
 * 1.1这道题可以应用之前解过的Largetst Rectangle in Histogram一题辅助解决。解决方法是：
 * 按照每一行计算列中有1的个数，作为高度，当遇见0时，这一列高度就为0。
 * 然后对每一行计算 Largetst Rectangle in Histogram，最后得到的就是结果。
 * 2.1 
 */

public class MaximalSquare {

	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
	 
		int m = matrix.length;
		int n = matrix[0].length;
	 
		int[][] t = new int[m][n];
	 
		//top row
		for (int i = 0; i < m; i++) {
			t[i][0] = Character.getNumericValue(matrix[i][0]);
		}
	 
		//left column
		for (int j = 0; j < n; j++) {
			t[0][j] = Character.getNumericValue(matrix[0][j]);
		}
	 
		//cells inside
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == '1') {
					int min = Math.min(t[i - 1][j], t[i][j - 1]);
					min = Math.min(min,t[i][j - 1]);
					t[i][j] = min + 1;
				} else {
					t[i][j] = 0;
				}
			}
		}
	 
		int max = 0;
		//get maximal length
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (t[i][j] > max) {
					max = t[i][j];
				}
			}
		}
	 
		return max * max;
	}
	
	// short 
	public int maximalSquare_1(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m][n];
        // 第一列赋值
        for(int i = 0; i < m; i++){
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        // 第一行赋值
        for(int i = 0; i < n; i++){
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        // 递推
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = matrix[i][j] == '1' ? Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1 : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     char[][] matrix = {{'1','0','1','0','0'},
    		 			{'1','0','1','1','1'},
    		 			{'1','1','1','1','1'},
    		 			{'1','0','0','1','0'}
     					};
     MaximalSquare slt = new MaximalSquare();
     System.out.print(slt.maximalSquare(matrix));
	}

}
