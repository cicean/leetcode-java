/**
 * Created by cicean on 10/23/2016.
 * 419. Battleships in a Board   QuestionEditorial Solution  My Submissions
 Total Accepted: 2521
 Total Submissions: 4203
 Difficulty: Medium
 Contributors: ben65
 Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

 You receive a valid board, made of only battleships or empty slots.
 Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 Example:
 X..X
 ...X
 ...X
 In the above board there are 2 battleships.
 Invalid Example:
 ...X
 XXXX
 ...X
 This is not a valid board - as battleships will always have a cell separating between them.
 Your algorithm should not modify the value of the board.

 Hide Company Tags Microsoft

 */
public class BattleshipsinaBoard {

    //Going over all cells, we can count only those that are the "first" cell of the battleship. First cell will be defined as the most top-left cell.
    // We can check for first cells by only counting cells that do not have an 'X' to the left and do not have an 'X' above them.
    public int countBattleships(char[][] board) {
        int m = board.length;
        if (m==0) return 0;
        int n = board[0].length;

        int count=0;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i-1][j] == 'X') continue;
                if (j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }

        return count;
    }

    //dfs
    public int countBattleships_dfs(char[][] board) {
        int M = board.length;
        int N = board[0].length;
        boolean[][] marked = new boolean[M][N];
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'X' && !marked[i][j] && dfs(board, i, j, marked)) cnt++;
            }
        }
        return cnt;
    }

    public boolean dfs(char[][] board, int r, int c, boolean[][] marked) {
        marked[r][c] = true;
        int[] direct = {1, 0, -1, 0, 1};
        int cnt = 0;
        boolean res = true;
        for (int i = 0; i < 4; i++) {
            int newR = r + direct[i];
            int newC = c + direct[i + 1];
            if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length && !marked[newR][newC] && board[newR][newC] == 'X') {
                cnt++;
                if (!dfs(board, newR, newC, marked)) res = false;
            }
        }
        return cnt > 1 || res;
    }
}
