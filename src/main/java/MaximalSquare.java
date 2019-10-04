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

���Ӷ�

ʱ�� O(MN) �ռ� O(MN)

˼·
�������ж���ĳ����Ϊ���������½�ʱ����������ʱ���������Ϸ����󷽺����Ϸ�������Ҳһ����ĳ�������ε����½ǣ�
����õ�Ϊ���½ǵ����������������Լ��ˡ����Ƕ��Ե��жϣ��Ǿ������������α߳��أ�����֪����
�õ�Ϊ���½ǵ������ε����߳������������Ϸ����󷽺����Ϸ�Ϊ���½ǵ������εı߳���1��
��õ�������������Ϸ����󷽺����Ϸ�Ϊ���½ǵ������εĴ�С��һ���ģ��������ϸõ�Ϳ��Թ���һ������������Ρ�
����������Ϸ����󷽺����Ϸ�Ϊ���½ǵ������εĴ�С��һ�����������ͻ�ȱ��ĳ�����䣬��ʱ��ֻ��ȡ����������������С�������εı߳���1�ˡ�
����dpi��ʾ��i,jΪ���½ǵ������ε����߳�������

dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
��Ȼ������������ԭ�����б������0�Ļ�����dpi�϶�����0�ˡ�


 * 
 * ��⣺
 * 1.1��������Ӧ��֮ǰ�����Largetst Rectangle in Histogramһ�⸨���������������ǣ�
 * ����ÿһ�м���������1�ĸ�������Ϊ�߶ȣ�������0ʱ����һ�и߶Ⱦ�Ϊ0��
 * Ȼ���ÿһ�м��� Largetst Rectangle in Histogram�����õ��ľ��ǽ����
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
        // ��һ�и�ֵ
        for(int i = 0; i < m; i++){
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        // ��һ�и�ֵ
        for(int i = 0; i < n; i++){
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        // ����
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
