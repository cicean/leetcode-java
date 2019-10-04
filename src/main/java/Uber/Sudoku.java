package Uber;

import java.util.*;

/**
 * Created by cicean on 9/18/2018.
 */
public class Sudoku {

    /**
     * 编写一个程序，通过填充空单元来解决数独难题。
     空单元由数字0表示。
     你可以认为只有一个唯一的解决方案。
     * @param board
     */
    public void solveSudoku(char[][] board){
        solve(board);
    }

    public boolean solve(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    continue;
                }
                for(int k = 1; k <= 9; k++){
                    board[i][j] = (char) (k + '0');
                    if (isValid(board, i, j) && solve(board)){
                        return true;
                    }
                    board[i][j] = '.';
                }
                return false;
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int a, int b){
        Set<Character> contained = new HashSet<Character>();
        for(int j=0;j<9;j++){
            if(contained.contains(board[a][j])) return false;
            if(board[a][j]>'0' && board[a][j]<='9')
                contained.add(board[a][j]);
        }



        contained = new HashSet<Character>();
        for(int j=0;j<9;j++){
            if (contained.contains(board[j][b])) {
                return false;
            }
            if (board[j][b]>'0' && board[j][b]<='9') {
                contained.add(board[j][b]);
            }
        }


        contained = new HashSet<Character>();
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++){
                int x = a / 3 * 3 + m, y = b / 3 * 3 + n;
                if (contained.contains(board[x][y])) {
                    return false;
                }
                if (board[x][y] > '0' && board[x][y] <= '9') {
                    contained.add(board[x][y]);
                }
            }
        }

        return true;
    }

    /**
     * 请判定一个数独是否有效。

     该数独可能只填充了部分数字，其中缺少的数字用 . 表示。
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];

        // row
        for(int i = 0; i<9; i++){
            Arrays.fill(visited, false);
            for(int j = 0; j<9; j++){
                if(!process(visited, board[i][j]))
                    return false;
            }
        }

        //col
        for(int i = 0; i<9; i++){
            Arrays.fill(visited, false);
            for(int j = 0; j<9; j++){
                if(!process(visited, board[j][i]))
                    return false;
            }
        }

        // sub matrix
        for(int i = 0; i<9; i+= 3){
            for(int j = 0; j<9; j+= 3){
                Arrays.fill(visited, false);
                for(int k = 0; k<9; k++){
                    if(!process(visited, board[i + k/3][ j + k%3]))
                        return false;
                }
            }
        }
        return true;

    }

    private boolean process(boolean[] visited, char digit){
        if(digit == '.'){
            return true;
        }

        int num = digit - '0';
        if ( num < 1 || num > 9 || visited[num-1]){
            return false;
        }

        visited[num-1] = true;
        return true;
    }
}
