/**
 * Created by cicean on 8/31/2016.
 * 353. Design Snake Game  QuestionEditorial Solution  My Submissions
 Total Accepted: 2644 Total Submissions: 11007 Difficulty: Medium
 Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

 The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

 You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

 Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

 When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

 Example:
 Given width = 3, height = 2, and food = [[1,2],[0,1]].

 Snake snake = new Snake(width, height, food);

 Initially the snake appears at position (0,0) and the food at (1,2).

 |S| | |
 | | |F|

 snake.move("R"); -> Returns 0

 | |S| |
 | | |F|

 snake.move("D"); -> Returns 0

 | | | |
 | |S|F|

 snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

 | |F| |
 | |S|S|

 snake.move("U"); -> Returns 1

 | |F|S|
 | | |S|

 snake.move("L"); -> Returns 2 (Snake eats the second food)

 | |S|S|
 | | |S|

 snake.move("U"); -> Returns -1 (Game over because snake collides with border)

 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Design Queue

 */
public class DesignSnakeGame {
}
