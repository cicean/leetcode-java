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
     * ���Ӷ�
     O(1) ʱ�� O(N) �ռ�

     ˼·
     ���ݽṹ��
     ������СΪn��һά���������rows��cols���Խ��߼�����diag����Խ��߼�����anti_diag
     ˼·��
     ������1�ڵ�һ��ĳһ�з���һ���ӣ���ôrows[row]����1��cols[col]����1��
     ������2�ڵ�һ��ĳһ�з���һ���ӣ���rows[row]�Լ�1��cols[col]�Լ�1��
     ���ǵ�rows[row]����n����-n��ʱ�򣬻���cols[col]����n����-n������Ϸ�������ظ���Ҽ��ɣ��Խ��ߺ���Խ����編����

     ע��
     ע���ж϶Խ��ߵ�ʱ��������if���еģ�����if else����Ϊһ�����п��ܼ������Խ��ߣ�Ҳ�Ƿ��Խ��ߣ��Ǿ����м�ĵ㡣
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
