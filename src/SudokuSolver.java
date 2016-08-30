import java.util.HashSet;
import java.util.Set;

/*
 37	Sudoku Solver	21.7%	Hard
 Problem:    Sudoku Solver
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/sudoku-solver/
 Notes:
 Write a program to solve a Sudoku puzzle by filling the empty cells.
 Empty cells are indicated by the character '.'.
 You may assume that there will be only one unique solution.
 Solution: back-tracking..
 */

public class SudokuSolver {
	
	
    public boolean isValid_1(char[][] board, int a, int b){
        boolean[] flag = new boolean[9];
        
        
        Set<Character> contained = new HashSet<Character>();
        for(int j=0;j<9;j++){
            if(contained.contains(board[a][j])) return false;
            if(board[a][j]>'0' && board[a][j]<='9')
                contained.add(board[a][j]);
        }
            
        
    
        contained = new HashSet<Character>();
        for(int j=0;j<9;j++){
            if(contained.contains(board[j][b])) return false;
            if(board[j][b]>'0' && board[j][b]<='9')
                contained.add(board[j][b]);
        }
        
    
        contained = new HashSet<Character>();
        for(int m=0;m<3;m++){
            for(int n=0;n<3;n++){
                int x=a/3*3+m, y=b/3*3+n;
                if(contained.contains(board[x][y])) return false;
                    if(board[x][y]>'0' && board[x][y]<='9')
                        contained.add(board[x][y]);
            }  
        }
    
        return true;
    }
    
    public boolean solveSudoku_1(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(int k=0;k<9;k++){
                        board[i][j]=(char)('1'+k);
                        if(isValid_1(board,i,j) && solveSudoku_1(board)) return true;
                        board[i][j]='.';
                    }
                    return false;
                }
            }
        }
        return true;   
    }
	
	
	public void solveSudoku(char[][] board) {
        solve(board);
    }
    boolean solve(char[][] board){
        for(int i = 0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(char ch = '1';ch<='9';ch++){
                        board[i][j]=ch;
                        if(isValidSudoKu(board,i,j)&&solve(board)) return true;
                        board[i][j]='.';
                    }
                    return false;
                }
            }
        }
        return true;
    }
    boolean isValidSudoKu(char[][] board, int x, int y){
        for(int i = 0; i<9; i++){
            if(i!=y&&board[x][i]==board[x][y]) return false;
        }
        for(int i=0;i<9;i++){
            if(i!=x&&board[i][y]==board[x][y]) return false;
        }
        for(int i=3*(x/3);i<3*(x/3)+3;i++){
            for(int j=3*(y/3);j<3*(y/3)+3;j++){
                if(i!=x&&j!=y&&board[i][j]==board[x][y]) return false;
            }
        }
        return true;
    }
    
    
    
    public  static void main(String[] args) {
		// TODO Auto-generated method stub
    	SudokuSolver slt=  new SudokuSolver();
    	char [][] board ={
				{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}
    	};
    	
    	slt.solveSudoku_1(board);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		
		}
		
		
    
    
}
