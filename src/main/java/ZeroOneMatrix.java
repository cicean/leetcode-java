import java.util.*;

/**
 *  542. 01 Matrix
 DescriptionHintsSubmissionsDiscussSolution

 Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 The distance between two adjacent cells is 1.

 Example 1:
 Input:

 0 0 0
 0 1 0
 0 0 0

 Output:

 0 0 0
 0 1 0
 0 0 0

 Example 2:
 Input:

 0 0 0
 0 1 0
 1 1 1

 Output:

 0 0 0
 0 1 0
 1 2 1

 Note:

 The number of elements of the given matrix will not exceed 10,000.
 There are at least one 0 in the given matrix.
 The cells are adjacent in only four directions: up, down, left and right.

 */

public class ZeroOneMatrix {

  //Java Solution, BFS

  // General idea is BFS. Some small tricks:

  //At beginning, set cell value to Integer.MAX_VALUE if it is not 0.
  //If newly calculated distance >= current distance, then we don't need to explore that cell again.

  public class Solution {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
      int m = matrix.size();
      int n = matrix.get(0).size();

      Queue<int[]> queue = new LinkedList<>();
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (matrix.get(i).get(j) == 0) {
            queue.offer(new int[] {i, j});
          }
          else {
            matrix.get(i).set(j, Integer.MAX_VALUE);
          }
        }
      }

      int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

      while (!queue.isEmpty()) {
        int[] cell = queue.poll();
        for (int[] d : dirs) {
          int r = cell[0] + d[0];
          int c = cell[1] + d[1];
          if (r < 0 || r >= m || c < 0 || c >= n ||
              matrix.get(r).get(c) <= matrix.get(cell[0]).get(cell[1]) + 1) continue;
          queue.add(new int[] {r, c});
          matrix.get(r).set(c, matrix.get(cell[0]).get(cell[1]) + 1);
        }
      }

      return matrix;
    }
  }

  //LeetCode has changed the function signature. Updated code:

  public class Solution2 {
    public int[][] updateMatrix(int[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;

      Queue<int[]> queue = new LinkedList<>();
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (matrix[i][j] == 0) {
            queue.offer(new int[] {i, j});
          }
          else {
            matrix[i][j] = Integer.MAX_VALUE;
          }
        }
      }

      int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

      while (!queue.isEmpty()) {
        int[] cell = queue.poll();
        for (int[] d : dirs) {
          int r = cell[0] + d[0];
          int c = cell[1] + d[1];
          if (r < 0 || r >= m || c < 0 || c >= n ||
              matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) continue;
          queue.add(new int[] {r, c});
          matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
        }
      }

      return matrix;
    }
  }



}
