import java.util.*;

/**
 * 498. Diagonal Traverse
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

 Example:
 Input:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 Output:  [1,2,4,7,5,3,6,8,9]
 Explanation:

 Note:
 The total number of elements of the given matrix will not exceed 10,000.

 */

public class DiagonalTraverse {

  /**
   * I don't think this is a hard problem. It is easy to figure out the walk pattern. Anyway...
   Walk patterns:

   If out of bottom border (row >= m) then row = m - 1; col += 2; change walk direction.
   if out of right border (col >= n) then col = n - 1; row += 2; change walk direction.
   if out of top border (row < 0) then row = 0; change walk direction.
   if out of left border (col < 0) then col = 0; change walk direction.
   Otherwise, just go along with the current direction.
   Time complexity: O(m * n), m = number of rows, n = number of columns.
   Space complexity: O(1).
   */

  public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
      if (matrix == null || matrix.length == 0) return new int[0];
      int m = matrix.length, n = matrix[0].length;

      int[] result = new int[m * n];
      int row = 0, col = 0, d = 0;
      int[][] dirs = {{-1, 1}, {1, -1}};

      for (int i = 0; i < m * n; i++) {
        result[i] = matrix[row][col];
        row += dirs[d][0];
        col += dirs[d][1];

        if (row >= m) { row = m - 1; col += 2; d = 1 - d;}
        if (col >= n) { col = n - 1; row += 2; d = 1 - d;}
        if (row < 0)  { row = 0; d = 1 - d;}
        if (col < 0)  { col = 0; d = 1 - d;}
      }

      return result;
    }
  }

  class Solution_2 {
    public int[] findDiagonalOrder(int[][] matrix) {
      int rowLen = matrix.length;
      if(rowLen == 0) {
        return new int[]{};
      }
      int colLen = matrix[0].length;
      int n = rowLen*colLen;
      int[] res = new int[n];
      boolean up = true;
      int x = 0;
      int y = 0;
      int idx = 0;
      while(idx < n) {
        if(up) {
          if(y >= colLen) {
            x = x+2;
            y = colLen-1;
            up = false;
          } else if(x < 0) {
            x = 0;
            up = false;
          } else {
            res[idx++] = matrix[x][y];
            x--;
            y++;
          }
        } else {
          if(x >= rowLen) {
            y = y + 2;
            x = rowLen-1;
            up = true;
          } else if(y < 0) {
            y = 0;
            up = true;
          } else {
            res[idx++] = matrix[x][y];
            x++;
            y--;
          }
        }
      }
      return res;
    }
  }

  class Solution_3 {
    public int[] findDiagonalOrder(int[][] matrix)
    {
      if (matrix.length == 0) return new int[0];
      int m = matrix.length, n = matrix[0].length;
      int sol[] = new int[m*n];
      int row = 0, col = 0;
      for(int i = 0; i < sol.length; i++) {
        sol[i] = matrix[row][col];
        if((row+col)%2 == 0) {              // go up
          if(col == n-1) row++;           // corner case of last column
          else if(row == 0) col++;        // corner case of first row
          else { row--; col++;}           // normal update to go up diagonally
        } else {                            // go down
          if(row == m-1) col++;           // corner case of last row
          else if(col == 0) row++;        // corner case of first column
          else { row++; col--;}           // normal update to go down diagonally
        }
      }
      return sol;

    }
  }


}
