/**
 * Created by cicean on 8/30/2016.
 * 348. Design Tic-Tac-Toe  QuestionEditorial Solution  My Submissions
 Total Accepted: 4137
 Total Submissions: 9383
 Difficulty: Medium
 Design a Tic-tac-toe game that is played between two players on a n x n grid.

 You may assume the following rules:

 A move is guaranteed to be valid and is placed on an empty block.
 Once a winning condition is reached, no more moves is allowed.
 A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 Example:
 Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

 TicTacToe toe = new TicTacToe(3);

 toe.move(0, 0, 1); -> Returns 0 (no one wins)
 |X| | |
 | | | |    // Player 1 makes a move at (0, 0).
 | | | |

 toe.move(0, 2, 2); -> Returns 0 (no one wins)
 |X| |O|
 | | | |    // Player 2 makes a move at (0, 2).
 | | | |

 toe.move(2, 2, 1); -> Returns 0 (no one wins)
 |X| |O|
 | | | |    // Player 1 makes a move at (2, 2).
 | | |X|

 toe.move(1, 1, 2); -> Returns 0 (no one wins)
 |X| |O|
 | |O| |    // Player 2 makes a move at (1, 1).
 | | |X|

 toe.move(2, 0, 1); -> Returns 0 (no one wins)
 |X| |O|
 | |O| |    // Player 1 makes a move at (2, 0).
 |X| |X|

 toe.move(1, 0, 2); -> Returns 0 (no one wins)
 |X| |O|
 |O|O| |    // Player 2 makes a move at (1, 0).
 |X| |X|

 toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 |X| |O|
 |O|O| |    // Player 1 makes a move at (2, 1).
 |X|X|X|
 Follow up:
 Could you do better than O(n2) per move() operation?

 Hint:

 Could you trade extra space such that move() operation can be done in O(1)?
 You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
 Hide Company Tags Microsoft Google
 Hide Tags Design

 */
public class DesignTicTacToe {

    /**
     * 复杂度
     O(1) 时间 O(N) 空间

     思路
     数据结构：
     两个大小为n的一维数组计数器rows和cols，对角线计数器diag和逆对角线计数器anti_diag
     思路：
     如果玩家1在第一行某一列放了一个子，那么rows[row]自增1，cols[col]自增1，
     如果玩家2在第一行某一列放了一个子，则rows[row]自减1，cols[col]自减1，
     于是当rows[row]等于n或者-n的时候，或者cols[col]等于n或者-n，则游戏结束返回该玩家即可，对角线和逆对角线如法炮制

     注意
     注意判断对角线的时候，是两个if并列的，不是if else，因为一个点有可能既是正对角线，也是反对角线，那就是中间的点。
     */

    public class TicTacToe {
        int n;
        int[] rows;
        int[] cols;
        int diagonal;
        int anti_diagonal;
        int wins;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            this.n = n;
            rows = new int[n];
            cols = new int[n];
            diagonal = 0;
            anti_diagonal = 0;
            wins = 0;
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            if (player == 1) {
                rows[row]++;
                cols[col]++;
                if (row == col)
                    diagonal++;
                if (row + col == n - 1)
                    anti_diagonal++;
            }
            else if (player == 2) {
                rows[row]--;
                cols[col]--;
                if (row == col)
                    diagonal--;
                if (row + col == n - 1)
                    anti_diagonal--;
            }
            if (rows[row] == n || cols[col] == n || diagonal == n || anti_diagonal == n)
                wins = 1;
            else if (rows[row] == -n || cols[col] == -n || diagonal == -n || anti_diagonal == -n)
                wins = 2;
            return wins;
        }
    }
}
