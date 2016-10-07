/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time:
 You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite,
 which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 * @author cicean
 *
 */
public class GameofLife {

	/**
	 * ������������Ŀ���������Ϸ, �������ǵ�һ����˵�������������һ��ϸ���Զ�����ÿһ��λ��������״̬��1Ϊ��ϸ����0Ϊ��ϸ����
	 * ����ÿ��λ�ö��������µ�������

	 1. �����ϸ����Χ�˸�λ�õĻ�ϸ�����������������λ�û�ϸ������

	 2. �����ϸ����Χ�˸�λ����������������ϸ�������λ�û�ϸ����Ȼ���

	 3. �����ϸ����Χ�˸�λ���г���������ϸ�������λ�û�ϸ������

	 4. �����ϸ����Χ������������ϸ�������λ����ϸ������

	 ������Ŀ��Ҫ���������û�����in-place�����⣬�������ǾͲ����½�һ����ͬ��С�����飬��ô����ֻ�ܸ���ԭ�����飬
	 ������Ŀ��Ҫ�����е�λ�ñ��뱻ͬʱ���£�������ѭ�����������ǻ���һ��λ��һ��λ�ø��µģ���ô��һ��λ�ø����ˣ�
	 ���λ�ó�Ϊ����λ�õ�neighborʱ��������ô֪����δ���µ�״̬�أ����ǿ���ʹ��״̬��ת����

	 ״̬0�� ��ϸ��תΪ��ϸ��

	 ״̬1�� ��ϸ��תΪ��ϸ��

	 ״̬2�� ��ϸ��תΪ��ϸ��

	 ״̬3�� ��ϸ��תΪ��ϸ��

	 ������Ƕ�����״̬��2ȡ�࣬��ô״̬0��2�ͱ����ϸ����״̬1��3���ǻ�ϸ�������Ŀ�ġ�
	 �����ȶ�ԭ����������ɨ�裬����ÿһ��λ�ã�ɨ������Χ�˸�λ�ã��������״̬1��2���ͼ������ۼ�1��ɨ��8���ھӣ�
	 �������������ϸ�����ߴ���������ϸ�������ҵ�ǰλ���ǻ�ϸ���Ļ������״̬2�����������������ϸ���ҵ�ǰ����ϸ���Ļ������״̬3��
	 ���һ��ɨ����ٶ�����ɨ��һ�飬��2ȡ����������Ҫ�Ľ�����μ��������£�
	 */

	int[][] dir ={{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
	public void gameOfLife(int[][] board) {
	    for(int i=0;i<board.length;i++){
	        for(int j=0;j<board[0].length;j++){
	            int live=0;
	            for(int[] d:dir){
	                if(d[0]+i<0 || d[0]+i>=board.length || d[1]+j<0 || d[1]+j>=board[0].length) continue;
	                if(board[d[0]+i][d[1]+j]==1 || board[d[0]+i][d[1]+j]==2) live++;
	            }
	            if(board[i][j]==0 && live==3) board[i][j]=3;
	            if(board[i][j]==1 && (live<2 || live>3)) board[i][j]=2;
	        }
	    }
	    for(int i=0;i<board.length;i++){
	        for(int j=0;j<board[0].length;j++){
	            board[i][j] %=2;
	        }
	    }
	}
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
