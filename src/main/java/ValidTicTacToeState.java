/**
 *
 */
public class ValidTicTacToeState {

    /**
     * Approach #1: Ad-Hoc [Accepted]
     * Intuition
     *
     * Let's try to think about the necessary conditions for a tic-tac-toe board to be valid.
     *
     * Since players take turns, the number of 'X's must be equal to or one greater than the number of 'O's.
     *
     * A player that wins has to win on the move that they make:
     *
     * If the first player wins, the number of 'X's is one more than the number of 'O's
     * If the second player wins, the number of 'X's is equal to the number of 'O's.
     * The board can't simultaneously have three 'X's and three 'O's in a row: once one player has won (on their move), there are no subsequent moves.
     *
     * It turns out these conditions are also sufficient to check the validity of all boards. This is because we can check these conditions in reverse: in any board, either no player, one player, or both players have won. In the first two cases, we should check the previously stated counting conditions (a relationship between xCount and oCount), and this is sufficient. In the last case, it is not allowed to have both players win simultaneously, so our check was also sufficient.
     *
     * Algorithm
     *
     * We'll count the number of 'X's and 'O's as xCount and oCount.
     *
     * We'll also use a helper function win(player) which checks if that player has won. This checks the 8 horizontal, vertical, or diagonal lines of the board for if there are three in a row equal to that player.
     *
     * After, we just have to check our conditions as stated above.
     *
     *
     * Complexity Analysis
     *
     * Time and Space Complexity: O(1)O(1).
     *
     */

    class Solution {
        public boolean validTicTacToe(String[] board) {
            int xCount = 0, oCount = 0;
            for (String row: board)
                for (char c: row.toCharArray()) {
                    if (c == 'X') xCount++;
                    if (c == 'O') oCount++;
                }

            if (oCount != xCount && oCount != xCount - 1) return false;
            if (win(board, 'X') && oCount != xCount - 1) return false;
            if (win(board, 'O') && oCount != xCount) return false;
            return true;
        }

        public boolean win(String[] B, char P) {
            // B: board, P: player
            for (int i = 0; i < 3; ++i) {
                if (P == B[0].charAt(i) && P == B[1].charAt(i) && P == B[2].charAt(i))
                    return true;
                if (P == B[i].charAt(0) && P == B[i].charAt(1) && P == B[i].charAt(2))
                    return true;
            }
            if (P == B[0].charAt(0) && P == B[1].charAt(1) && P == B[2].charAt(2))
                return true;
            if (P == B[0].charAt(2) && P == B[1].charAt(1) && P == B[2].charAt(0))
                return true;
            return false;
        }
    }
}


