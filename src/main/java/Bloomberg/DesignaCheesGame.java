package Bloomberg;

/**
 * Created by cicean on 9/18/2016.
 *
 * 1.problem statement
 2.use cases
 analysis & design
 3.identifying objects
 4.identifying interactions among objects
 5.identifying attributes of objects
 6.refining with hierarchy
 at the end of sixth step, we shall get good class diagram,
 after we can proceed for implementation with functional modeling
 */

/**
 * 1. problem statement

 Chess is a board game of two players. Player could be human or computer. There are two sides of pieces in chess game. One is black and another one is white. A player chooses one of them and plays with that side.

 Each side of pieces contains total 16 including 1 king, 1 queen, 2 bishops, 2 knights, 2 rooks and 8 pawns.

 First board is initialized according to its standard initial position of pieces.
 Each player gets his turn and makes valid move of his side piece and gives turn to opponent.

 Checkmate of either side king is considered the end of game. In time limit game, either timeout or checkmate is considered as the end of game. Time limit game requires to maintain time watch of the two players.
 */

/**
 * 2. use cases

 I. chess board is initialized to its standard position
 II. a player chooses his side.
 III. White side is given the first turn and after given turn to the side time-watch starts for the side.
 IV. A player during his turn makes a move.
 V. Validity of the move is checked
 VI. Opponent king checkmate checked
 VII. A piece is moved on the board accordingly.
 VIII. Turn is given to opponent side.
 IX. The game is ended if timeout happens of checkmate found.
 */

/**
 * 3. identifying objects

 After looking at use-cases, objects could be the following
 tangibles/externals:
 chess-board, player, piece,

 internal:
 game, time-watch
 */

/**
 * 4. identifying the interactions among objects

 game->(initializes)->chess-board
 game->(arranges/moves)->piece
 game->(starts/stops)->time-watch
 player->(requests move)->game

 chess-board->(consists of)->piece
 game->(consiss)->players
 game->(discards piece from)->chess-board
 */

/**
 * 5. identifying attributes

 chess-board: matrix of positions, a position may contain piece
 piece: side: white or black
 king: type, queen: type, ....
 game: players, chess-board, turn, time-watch
 time-watch: timer
 player: name, side
 */

/**
 * 6. refining with hierarchy

 Player is inherited by "Human Player" and "Computer Player"
 Piece is inherited by "King", "Queen", "Bishop", "Knight", "Rook", "Pawn"
 */
public class DesignaCheesGame {
}
