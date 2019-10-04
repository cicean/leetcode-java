import java.util.Arrays;
import java.util.Scanner;


/*
 36	Valid Sudoku	27.2%	Easy
 Problem:    Valid Sudoku
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/valid-sudoku/
 Notes:
 Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules (http://sudoku.com.au/TheRules.aspx).
 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 Solution: 1. Traverse the Sudoku only once.
           2. Bit manipulation. Use only one bit to represent a number. Space: sizeof(int) * (1+9+9).
 */

public class ValidSudoku {
	public static   boolean isValidSudoku(char[][] board) {
        boolean[] used = new boolean[9];
        
        for(int i=0;i<9;i++){
            Arrays.fill(used,false);
            for(int j = 0; j<9;j++){
                if(check(board[i][j],used)==false) return false;
            }
            Arrays.fill(used,false);
            for(int j = 0; j<9;j++){
                if(check(board[j][i],used)==false) return false;
            }
        }
        
        for(int r = 0; r<3;r++){
            for(int c = 0; c<3;c++){
            Arrays.fill(used,false);
                for(int i = r*3;i<r*3+3;i++){
                    for(int j = c*3;j<c*3+3;j++){
                        if(check(board[i][j],used)==false) return false;
                    }
                }
            }
        }
        return true;
    }
	static   boolean check(char ch, boolean[] used){
        if(ch=='.') return true;
        if(used[ch-'1']) return false;
        used[ch-'1']=true;
        return true;
    }
    
    public void solveSudoku(char[][] board) {
    	isValidSudoku(board);
    }
    
    public static  void  main(String[] args) {
		// TODO Auto-generated method stub
    	ValidSudoku slt=  new ValidSudoku();
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
    	isValidSudoku(board);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
    	
    	
	
	}
}
