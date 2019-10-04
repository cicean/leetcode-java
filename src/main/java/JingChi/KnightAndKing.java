package JingChi;

import java.util.*;

  class Point {
     int x;
     int y;
     Point() { x = 0; y = 0; }
     Point(int a, int b) { x = a; y = b; }
}

public class KnightAndKing {

    int n, m; // size of the chessboard
    int[] deltaX = {1, 1, 2, 2, -1, -1, -2, -2};
    int[] deltaY = {2, -2, 1, -1, 2, -2, 1, -1};

    int[] kingX = {1, -1, 0, 0};
    int[] kingY = {0, 0, 1, -1};

    public int shortestPath(boolean[][] grid, Point source, Point king) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        n = grid.length;
        m = grid[0].length;

        Queue<Point> queueKnight = new LinkedList<>();
        Queue<Point> queueKing = new LinkedList<>();
        queueKnight.offer(source);
        queueKing.offer(king);

        int steps = 0;
        while (!queueKnight.isEmpty() || !queueKing.isEmpty()) {
            int size = queueKnight.size();
            for (int i = 0; i < size; i++) {
                Point pointKinight = queueKnight.poll();
                Point pointKing = queueKing.peek();
                if ( grid[pointKinight.x][pointKinight.y] = true) {
                    continue;
                }

                if (pointKinight.x == pointKing.x && pointKinight.y == pointKing.y) {
                    return steps;
                }

                for (int direction = 0; direction < 8; direction++) {
                    Point nextPoint = new Point(
                            pointKinight.x + deltaX[direction],
                            pointKinight.y + deltaY[direction]
                    );

                    if (!inBound(nextPoint, grid)) {
                        continue;
                    }

                    queueKnight.offer(nextPoint);
                    // mark the point not accessible
                    grid[nextPoint.x][nextPoint.y] = true;
                }
            }
            steps++;

            int size2 = queueKing.size();
            for (int i = 0; i < size2; i++) {
                Point pointKnight = queueKnight.peek();
                Point pointKing = queueKing.poll();
                if ( grid[pointKing.x][pointKing.y] = true) {
                    continue;
                }
                if (pointKnight.x == pointKing.x && pointKnight.y == pointKing.y) {
                    return steps;
                }
                for (int direction = 0; direction < 4; direction++) {
                    Point nextPoint = new Point(
                            pointKing.x + kingX[direction],
                            pointKing.y + kingY[direction]
                    );

                    if (!inBound(nextPoint, grid)) {
                        continue;
                    }

                    queueKing.offer(nextPoint);
                    // mark the point not accessible
                    grid[nextPoint.x][nextPoint.y] = true;
                }
            }
            steps++;
        }

        return -1;
    }

    private boolean inBound(Point point, boolean[][] grid) {
        if (point.x < 0 || point.x >= n) {
            return false;
        }
        if (point.y < 0 || point.y >= m) {
            return false;
        }
        return (grid[point.x][point.y] == false);
    }
}
