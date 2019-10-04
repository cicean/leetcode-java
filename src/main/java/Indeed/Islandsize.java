package Indeed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/28/2018.
 */

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Area {
    Point a;
    Point b;
    int length;
    int width;
    public Area(Point a, Point b, int len, int w) {
        this.a = a;
        this.b = b;
        this.length = len;
        this.width = w;
    }
}
public class Islandsize {

    public Area findInMatrix(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }

        int m = matrix.length;
        int n = matrix[0].length;
//        Queue<Point> queue = new LinkedList<>();
//        Set<Point> set = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == true) {
                    return bfs(matrix, new Point(i,j));
                }
            }
        }

        return null;
    }

    private Area bfs(boolean[][] matrix, Point p){
        int i = p.x, j = p.y;
        while (i < matrix.length) {
            if (!matrix[i][p.y]) {
                break;
            }
            i++;
        }
        while (j < matrix[p.x].length) {
            if (!matrix[p.x][j]) {
                break;
            }
            j++;
        }

        Area res = new Area(p, new Point(i - 1, j - 1), (j  - p.y), (i - p.x));

        return res;
    }

    public List<List<Point>> numIslands(boolean[][] grid) {
        // write your code here
        int result = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new ArrayList<>();

        }
        List<List<Point>> res = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == true) {
                    result++;
                    List<Point> area = new ArrayList<>();
                    bfs(grid, i, j, area);
                    res.add(area);
                }
            }
        }

        return  res;
    }

    private void bfs(boolean[][] grid, int x, int y, List<Point> res) {
        if (x < 0 || y < 0 || x > grid.length - 1
                || y > grid[x].length - 1) {
            return;
        }

        if (grid[x][y] != true) {
            return;
        }

        grid[x][y] = false;
        res.add(new Point(x, y));
        bfs(grid, x + 1, y, res);
        bfs(grid, x - 1, y, res);
        bfs(grid, x, y - 1, res);
        bfs(grid, x, y + 1, res);
    }
}
