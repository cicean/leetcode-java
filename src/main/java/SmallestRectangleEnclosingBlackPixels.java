/**
 * Created by cicean on 8/29/2016.
 * 02. Smallest Rectangle Enclosing Black Pixels  QuestionEditorial Solution  My Submissions
 Total Accepted: 7203 Total Submissions: 17445 Difficulty: Hard
 An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

 For example, given the following image:

 [
 "0010",
 "0110",
 "0100"
 ]
 and x = 0, y = 2,
 Return 6.

 Hide Company Tags Google
 Hide Tags Binary Search

 */
public class SmallestRectangleEnclosingBlackPixels {

    /**
     * 这道题我第一眼看到就想到dfs把每个点都扫一遍，找到最小最大边界值，
     * 然后作差相乘。但是我知道这是有冗余的，只需要边界值的话，
     * 并不需要扫每一个点，查找的话，最快还是binary search,
     * 所以有个第二种解法，但是边界还是很容易出错，要分清取舍。
     */

    //DFS
    private class Interval{
        int min, max;
        public Interval(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public void search(char[][] image, int x, int y, Interval row, Interval col, boolean[][] visited) {
        visited[x][y] = true;
        row.max = Math.max(row.max, x);
        row.min = Math.min(row.min, x);
        col.max = Math.max(col.max, y);
        col.min = Math.min(col.min, y);
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int k = 0; k < dir.length; k++) {
            int i = x + dir[k][0], j = y + dir[k][1];
            if (i < 0 || i > image.length - 1 || j < 0 || j > image[0].length - 1) {
                continue;
            }
            if (!visited[i][j] && image[i][j] == '1') {
                search(image, i, j, row, col, visited);
            }
        }
    }

    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) return 0;

        int m = image.length, n = image[0].length;

        Interval row = new Interval(m - 1, 0);
        Interval col = new Interval(n - 1, 0);
        boolean[][] visited = new boolean[m][n];

        search(image, x, y, row, col, visited);

        return (row.max - row.min + 1) * (col.max - col.min + 1);
    }

    /**
     * 2.Binary Search
     */
    public int searchColumns(char[][] image, int i, int j, int top, int bottom, String def) {
        while (i != j) {
            int k = top, mid = (i + j) / 2;
            while (k < bottom && image[k][mid] == '0') ++k;
            if (k < bottom && def.equals("min") || k >= bottom && def.equals("max")) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    public int searchRows(char[][] image, int i, int j, int left, int right, String def) {
        while (i != j) {
            int k = left, mid = (i + j) / 2;
            while (k < right && image[mid][k] == '0') k++;
            if (k < right && def.equals("min") || k >= right && def.equals("max")) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    public int minArea_bs(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) return 0;
        int m = image.length, n = image[0].length;
        int left = searchColumns(image, 0, y, 0, m, "min");
        int right = searchColumns(image, y + 1, n, 0, m, "max");
        int top = searchRows(image, 0, x, left, right, "min");
        int bottom = searchRows(image, x + 1, m, left, right, "max");

        return (right - left) * (bottom - top);
    }


}
