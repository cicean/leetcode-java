package DoorDash;

import java.util.*;

/**
 * Created by cicean on 8/13/2018.
 */
public class UniquePathIII {

    class Point {
        int x;
        int y;

        public Point() {
            this.x = 0;
            this.y = 0;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


    }

    class Solution {

        int[] deltaX = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] deltaY = {0, 0, 1, -1, -1, 1, -1, 1};

        public boolean shortestPath(boolean[][] grid, Point start, Point end, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return false;
            }

            Queue<Point> queue = new LinkedList<>();
            queue.offer(start);

            int steps = 0;
            int path = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Point point = queue.poll();
                    if (point.x == end.x && point.y == end.y && steps <= k) {
                        return true;
                    }

                    for (int move = 0; move < 8; move++) {
                        Point next = new Point(point.x + deltaX[move], point.y + deltaY[move]);

                        if (!inBound(next, grid)) {
                            continue;
                        }
                        queue.offer(next);
                        grid[next.x][next.y] = true;
                    }
                }

                steps++;

                if (steps > k) {
                    break;
                }
            }

            return false;
        }

        private boolean inBound(Point point, boolean[][] grid) {
            if (point.x < 0 || point.x >= grid.length) {
                return false;
            }

            if (point.y < 0 || point.y >= grid[point.x].length) {
                return false;
            }

            return (grid[point.x][point.y] == false);
        }

        // how many path can be from start to end
        public int uniquePathsIII(boolean[][] grid, Point start, Point end, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            Queue<Point> queue = new LinkedList<>();
            queue.offer(start);

            int steps = 0;
            int path = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Point point = queue.poll();
                    if (point.x == end.x && point.y == end.y && steps <= k) {
                        path++;
                    }

                    for (int move = 0; move < 8; move++) {
                        Point next = new Point(point.x + deltaX[move], point.y + deltaY[move]);

                        if (!inBound(next, grid)) {
                            continue;
                        }
                        queue.offer(next);
                        grid[next.x][next.y] = true;
                    }
                }

                steps++;

                if (steps > k) {
                    break;
                }
            }

            return path;
        }

        public int uniquePathsIII_dp(boolean[][] grid, Point start, Point end, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;

            int[][] dp = new int[m][n];
            int[] ans = new int[k+1];
            dp[0][0] = 0;
            for (int i = 1)

            return path;
        }


        public static void main(String[] args) {
            ArrayList<String> strings = new ArrayList<String>();
            strings.add("Hello, World!");
            strings.add("Welcome to CoderPad.");
            strings.add("This pad is running Java 8.");

            for (String string : strings) {
                System.out.println(string);
            }

            Solution test = new Solution();
            boolean[][] grid = {{false,false,false},{false,false,false},{false,false,false}};
            Point start = new Point(0, 0);
            Point end = new Point(2,2);

            System.out.print("Can move in 2 step , " + test.shortestPath(grid, start, end, 2) );
        }
    }

}
