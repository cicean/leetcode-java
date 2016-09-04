import java.util.*;

/**
 * Created by cicean on 8/31/2016.
 * 353. Design Snake Game  QuestionEditorial Solution  My Submissions
 * Total Accepted: 2644 Total Submissions: 11007 Difficulty: Medium
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 * <p>
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * <p>
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
 * <p>
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 * <p>
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 * <p>
 * Example:
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * <p>
 * Snake snake = new Snake(width, height, food);
 * <p>
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * <p>
 * |S| | |
 * | | |F|
 * <p>
 * snake.move("R"); -> Returns 0
 * <p>
 * | |S| |
 * | | |F|
 * <p>
 * snake.move("D"); -> Returns 0
 * <p>
 * | | | |
 * | |S|F|
 * <p>
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 * <p>
 * | |F| |
 * | |S|S|
 * <p>
 * snake.move("U"); -> Returns 1
 * <p>
 * | |F|S|
 * | | |S|
 * <p>
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * <p>
 * | |S|S|
 * | | |S|
 * <p>
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 * <p>
 * Credits:
 * Special thanks to @elmirap for adding this problem and creating all test cases.
 * <p>
 * Hide Company Tags Google
 * Hide Tags Design Queue
 */
public class DesignSnakeGame {
    /**
     * ，这道题让我们设计一个贪吃蛇的游戏，这是个简化版的，
     * 但是游戏规则还是保持不变，蛇可以往上下左右四个方向走，
     * 吃到食物就会变长1个，如果碰到墙壁或者自己的躯体，
     * 游戏就会结束。我们需要一个一维数组来保存蛇身的位置，
     * 由于蛇移动的过程的蛇头向前走一步，蛇尾也跟着往前，
     * 中间的躯体还在原来的位置，所以移动的结果就是，
     * 蛇头变到新位置，去掉蛇尾的位置即可。
     * 需要注意的是去掉蛇尾的位置是在检测和蛇身的碰撞之前还是之后，
     * 如果是之后则无法通过这个test case：[[3,3,[[2,0],[0,0]]],["D"],["D"],["U"]]，
     * 如果是之前就没有问题了，总体来说不算一道难题，
     */

    //方法：使用双端队列。
    public class SnakeGame {
        private Set<String> board = new HashSet<>();
        private int[][] food;
        private int eat = 0;
        private LinkedList<Position> snake = new LinkedList<>();
        private int width, height;

        private boolean eat(int y, int x) {
            if (eat >= food.length) return false;
            if (food[eat][0] < 0 || food[eat][0] >= height || food[eat][1] < 0 || food[eat][1] >= width) return false;
            if (y == food[eat][0] && x == food[eat][1]) return true;
            return false;
        }

        /**
         * Initialize your data structure here.
         *
         * @param width  - screen width
         * @param height - screen height
         * @param food   - A list of food positions
         *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
         */
        public SnakeGame(int width, int height, int[][] food) {
            this.food = food;
            Position head = new Position(0, 0);
            this.snake.add(head);
            board.add(head.toString());
            this.height = height;
            this.width = width;
        }

        /**
         * Moves the snake.
         *
         * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         * @return The game's score after the move. Return -1 if game over.
         * Game over when snake crosses the screen boundary or bites its body.
         */
        public int move(String direction) {
            Position head = snake.getFirst();
            Position next = new Position(head.y, head.x);
            if ("U".equals(direction)) {
                next.y--;
            } else if ("D".equals(direction)) {
                next.y++;
            } else if ("L".equals(direction)) {
                next.x--;
            } else if ("R".equals(direction)) {
                next.x++;
            } else {
                return -1;
            }
            if (next.y < 0 || next.y >= height || next.x < 0 || next.x >= width) return -1;
            String ns = next.toString();
            if (eat(next.y, next.x)) {
                snake.addFirst(next);
                board.add(ns);
                return ++eat;
            }
            Position tail = snake.getLast();
            board.remove(tail.toString());
            snake.removeLast();
            if (board.contains(ns)) return -1;
            snake.addFirst(next);
            board.add(ns);
            return eat;
        }
    }

    class Position {
        int y, x;

        Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public String toString() {
            return y + "," + x;
        }
    }

    /**
     * Your SnakeGame object will be instantiated and called as such:
     * SnakeGame obj = new SnakeGame(width, height, food);
     * int param_1 = obj.move(direction);
     */

//Java Deque and HashSet design
    public class SnakeGame {

        //2D position info is encoded to 1D and stored as two copies
        Set<Integer> set; // this copy is good for fast loop-up for eating body case
        Deque<Integer> body; // this copy is good for updating tail
        int score;
        int[][] food;
        int foodIndex;
        int width;
        int height;

        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            set = new HashSet<>();
            set.add(0); //intially at [0][0]
            body = new LinkedList<>();
            body.offerLast(0);
        }


        public int move(String direction) {
            //case 0: game already over: do nothing
            if (score == -1) {
                return -1;
            }

            // compute new head
            int rowHead = body.peekFirst() / width;
            int colHead = body.peekFirst() % width;
            switch (direction) {
                case "U":
                    rowHead--;
                    break;
                case "D":
                    rowHead++;
                    break;
                case "L":
                    colHead--;
                    break;
                default:
                    colHead++;
            }
            int head = rowHead * width + colHead;

            //case 1: out of boundary or eating body
            set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily
            if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
                return score = -1;
            }

            // add head for case2 and case3
            set.add(head);
            body.offerFirst(head);

            //case2: eating food, keep tail, add head
            if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
                set.add(body.peekLast()); // old tail does not change, so add it back to set
                foodIndex++;
                return ++score;
            }

            //case3: normal move, remove tail, add head
            body.pollLast();
            return score;

        }
    }
}
