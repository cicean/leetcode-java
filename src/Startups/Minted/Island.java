package Minted;

/**
 * Created by cicean on 9/8/2018.
 */
public class Island {

    public int[][] islandStartEnd(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return new int[][]{};
        }

        int m = board.length;
        int n = board[0].length;
        int[][] res = new int[2][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    res[0][0] = i;
                    res[0][1] = j;
                }
            }
        }

        int x = res[0][0];
        int y = res[0][1];
        if (res.length != 0) {
            for (int i = res[0][0] + 1; i < board.length;i++) {
                if (board[i][y] == 0) {
                    break;
                } else {
                    x = i;
                }
            }

            for (int j = res[0][1] + 1; j < board[x].length;j++) {
                if (board[x][j] == 0) {
                    break;
                } else {
                    y = j;
                }
            }
        }

        if (x != res[0][0] && y != res[0][1]) {
            res[1][0] = x;
            res[1][1] = y;
            return res;
        }

        return new int[][]{{res[0][0],res[0][1]}};
    }
}
