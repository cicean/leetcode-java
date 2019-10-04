package Dropbox;

/**
 * Created by cicean on 9/10/2018.
 */
import java.util.*;


public class Main {

    static final String[] PLAYER_SYMBOLS = new String[]{"X", "O", "A", "B", "C", "D", "E", "Y", "Z", "H"};

    public final int numRows;
    public final int numCols;
    private final int[][] board;
    private final int humans;
    private final int robots;
    private final int players;

    class Move {
        public final int playerIndex;
        public final int row;
        public final int col;

        public Move(int playerIndex, int row, int col) {
            this.playerIndex = playerIndex;
            this.row = row;
            this.col = col;
        }
    }

    public Main(int rows, int cols, int humans, int robots) {
        this.board = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], -1);
            colToIndex.put(i,i);
            String col = String.valueOf(i+1);
            boardindex.add(i,col);
        }
        this.numRows = rows;
        this.numCols = cols;
        this.humans = humans;
        this.robots = robots;
        this.players = humans + robots;
    }
//    public Main() {
//    }


    /**
     * This method is called after each move.
     *
     * @param board = 2 dimensional index of player indexes which are 0 or greater
     * @param lastMove = Move object representing the last move made
     * @return index of winning player; -1 if no winner detected
     */
    public int winningPlayerIndex(int[][] board, Move lastMove) {
        // check vertical
        int total = 1 + countSame(board, lastMove, 1, 0, 3) + countSame(board, lastMove, -1, 0, 3);
        if( total >= 4 ) {
            return lastMove.playerIndex;
        }

        // check horizontal
        total = 1 + countSame(board, lastMove, 0, 1, 3) + countSame(board, lastMove, 0, -1, 3);
        if( total >= 4 ) {
            return lastMove.playerIndex;
        }

        // check up-and-left diagonal
        total = 1 + countSame(board, lastMove, -1, -1, 3) + countSame(board, lastMove, 1, 1, 3);
        if( total >= 4 ) {
            return lastMove.playerIndex;
        }

        // check up-and-right diagonal
        total = 1 + countSame(board, lastMove, -1, 1, 3) + countSame(board, lastMove, 1, -1, 3);
        if( total >= 4 ) {
            return lastMove.playerIndex;
        }
        return -1;
    }


    public int countSame(int[][] board, Move lastMove, int rowDelta, int colDelta, int maxCount) {
        int count = 0;
        int currentRow = lastMove.row;
        int currentCol = lastMove.col;
        int maxRow = board.length;
        int maxCol = board[0].length;
        while( true ) {
            currentRow = currentRow + rowDelta;
            currentCol = currentCol + colDelta;
            boolean invalidRow = currentRow < 0 || currentRow >= maxRow;
            boolean invalidCol = currentCol < 0 || currentCol >= maxCol;
            if (invalidRow || invalidCol) {
                return count;
            }
            if (board[currentRow][currentCol] == lastMove.playerIndex) {
                count += 1;
                if( count >= maxCount ) {
                    return count;
                }
            } else {
                break;
            }
        }
        return count;
    }

    private Map<Integer, Integer> colToIndex = new HashMap<>();
    private List<String> boardindex = new ArrayList<>();
    //init in constructor
    //
    public String getRobots() {
        Random random = new Random();
        for (int y = 0; y < board[0].length; y++) {
            if (board[0][y] > -1) {
                int index = colToIndex.get(y);
                if (index != boardindex.size() - 1) {
                    boardindex.add(index, boardindex.get(boardindex.size() - 1));
                    int col = Integer.parseInt(boardindex.get(boardindex.size() - 1));
                    colToIndex.put(col - 1,index);
                    boardindex.remove(boardindex.size() - 1);
                    colToIndex.remove(y);
                }
            }
        }

        int index = random.nextInt(boardindex.size());
        return boardindex.get(index);
    }


    public String getUserInput(String prompt) {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print(prompt);

        // get their input as a String
        String userInput = scanner.next();

        return userInput;
    }

    public void printBoard(int[][] board, String[] playerSymbols) {
        System.out.println("\n" + boardTerminalString(board, PLAYER_SYMBOLS) + "\n");
    }

    public String boardTerminalString(int[][] board, String[] playerSymbols) {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int col = 0; col < numCols; col++) {
            sb.append(String.format("%1$" + 3 + "s", col + 1 + ""));
        }
        sb.append("\n");
        for (int row = 0; row < numRows; row++) {
            sb.append(String.format("%1$" + 3 + "s", row + 1 + ""));
            for (int col = 0; col < numCols; col++) {
                int playerIndex = board[row][col];
                String playerSymbol = playerIndex > -1 ? PLAYER_SYMBOLS[playerIndex] : ".";
                playerSymbol = String.format("%1$" + 3 + "s", playerSymbol);
                sb.append(playerSymbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isValidMove(Move move) {
        if (move == null || move.row < 0 || move.col < 0) {
            return false;
        }

        if (move.row >= numRows || move.col >= numCols) {
            return false;
        }

        // can't place over existing piece
        if (board[move.row][move.col] > -1) {
            return false;
        }

        // check that we are stacking if not on bottom row
        if (move.row < numRows - 1 && board[move.row + 1][move.col] < 0) {
            return false;
        }

        return true;
    }

    public void updateBoard(int[][] board, Move move) {
        board[move.row][move.col] = move.playerIndex;
    }

    /**
     * @param playerIndex
     * @param moveString
     * @return Move object or null if unable to parse
     */
    public Move parseMove(int playerIndex, String moveString) {
        try {
            //String[] tokens = moveString.split(",");
//            if (tokens.length < 2) {
//                return null;
//            }


            //int row = Integer.parseInt(tokens[0].trim());
            int col = Integer.parseInt(moveString);
            int row = 0;
            for (int x = board.length - 1; x >= 0; x--) {
                if (board[x][col - 1] == -1) {
                    row = x;
                    break;
                }
            }
            System.out.println("playerIndex = " + playerIndex + "," + "row = " + row);
            return new Move(playerIndex, row, col - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public void start() {
        int moveCount = 0;
        while (true) {
            for (int currentPlayerIndex = 0; currentPlayerIndex < players; currentPlayerIndex++) {
                printBoard(board, PLAYER_SYMBOLS);
                Move move = null;
                while (true) {

                    String moveString;
                    if (currentPlayerIndex == 1 ) {
                        moveString = getRobots();
                    } else {
                        moveString = getUserInput("Player #" + (currentPlayerIndex + 1) + " Move [ row, col ]:  ");
                    }
                    move = parseMove(currentPlayerIndex, moveString);
                    if (isValidMove(move)) {
                        updateBoard(board, move);
                        moveCount++;
                        break;
                    } else {
                        System.out.println("INVALID MOVE");
                    }
                }// while
                if (currentPlayerIndex == winningPlayerIndex(board, move)) {
                    printBoard(board, PLAYER_SYMBOLS);
                    System.out.println("\n\nPlayer #" + (currentPlayerIndex + 1) + " wins!\n\n");
                    System.exit(0);
                }
                if (moveCount == numRows * numCols) {
                    printBoard(board, PLAYER_SYMBOLS);
                    System.out.println("\n\nIt's a draw!");
                    System.exit(0);
                }
            }// for
        }// while
    }

    public List<String> buildOrder(String target, Map<String, List<String>> dependences) {
        if (target == null || dependences == null) return null;

        Map<String, Integer> indegree = new HashMap<>();
        Map<String, List<String>> prebuildtotarget = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> uniquedependence = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : dependences.entrySet()) {
            indegree.put(entry.getKey(), entry.getValue().size());
            uniquedependence.add(entry.getKey());
            for (String prebuilddependence : entry.getValue()) {
                prebuildtotarget.computeIfAbsent(prebuilddependence, x -> new ArrayList<String>()).add(entry.getKey());
                uniquedependence.add(prebuilddependence);
            }
        }

        for (String dep : uniquedependence) {
            if (!indegree.containsKey(dep)) {
                queue.offer(dep);
            }
        }

        //System.out.print("length of " + uniquedependence.size());
        List<String> results = new ArrayList<>();

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            results.add(curr);
            System.out.println("curr = " + curr);
            if (!prebuildtotarget.containsKey(curr)) {
                continue;
            }

            for (String next : prebuildtotarget.get(curr)) {
                if (indegree.get(next) - 1 == 0) {
                    queue.offer(next);
                }
                indegree.put(next, indegree.get(next) - 1);
            }


        }

        //System.out.print("result" + results.size());
        return results.size() != uniquedependence.size() ? new ArrayList<>() : results;

    }

    public static void main(String[] args) throws Throwable {
        int rows = int32OrDefault(System.getProperty("rows"), 6);
        int cols = int32OrDefault(System.getProperty("cols"), 7);
        int humans = int32OrDefault(System.getProperty("humans"), 2);
        int robots = int32OrDefault(System.getProperty("robots"), 0);

        System.out.println("\n>>>>> Board = " + rows + " rows x " + cols + " cols; Human Players = " + humans + "; Robot Players = " + robots + "\n\n");

        Main game = new Main(rows, cols, humans, robots);

        game.start();

//         Main object = new Main();
//         String target = "4";
//         Map<String, List<String>> deps = new HashMap<>();
//         List<String> a = new ArrayList<>();
//         a.add("3");
//         a.add("2");
//         deps.put("4", a);
//         List<String> b = new ArrayList<>();
//         b.add("1");
//        deps.put("3",b);
//        deps.put("2",b);
//         List<String> res = object.buildOrder("4",deps);
//        String col = String.valueOf(1+1);
//        System.out.print(col);
//        System.out.print("res" + Arrays.toString(res.toArray()));

    }

    public static int int32OrDefault(String in, int defaultValue) {
        if (in == null || in.trim().length() == 0) {
            return defaultValue;
        }
        return Integer.parseInt(in);
    }
}
