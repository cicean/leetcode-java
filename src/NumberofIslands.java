/**
 * Source : https://leetcode.com/problems/number-of-islands/
 * Author : Hexiaoqiao
 * Date   : 2015-05-03
 *
 * 0.Problem:
 * Given a 2d grid map of '1's (land) and '0's (water), count 
 * the number of islands. An island is surrounded by water 
 * and is formed by connecting adjacent lands horizontally or 
 * vertically. You may assume all four edges of the grid are 
 * all surrounded by water.
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * 
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * 
 * 1.Refer.: 
 * 1.1DFS
 * 
 * 2.1The basic idea of the following solution is merging adjacent lands, 
 *       and the merging should be done recursively.
 *       
 * 3.1 
 */

public class NumberofIslands {

	public static int numIslands_1(char[][] grid) {
	    return dfs(grid);
	  }
	 
	public static void cdfs(char[][] grid, int x, int y) {
	    int xlength = grid.length;
	    int ylength = grid[0].length;
	    if (x >= xlength || y > ylength) return;
	    grid[x][y] = 't';
	    if (x + 1 < xlength && grid[x + 1][y] == '1') cdfs(grid, x + 1, y);
	    if (y + 1 < ylength && grid[x][y + 1] == '1') cdfs(grid, x, y + 1);
	    if (y - 1 >= 0 && grid[x][y - 1] == '1') cdfs(grid, x, y - 1);
	    if (x - 1 >= 0 && grid[x - 1][y] == '1') cdfs(grid, x - 1, y);
	  }
	
	  public static int dfs(char[][] grid) {
	    int count = 0;
	    for (int i = 0; i < grid.length; i++) {
	      for (int j = 0; j < grid[i].length; j++) {
	        if ('1' == grid[i][j]) {
	          count = count + 1;
	          cdfs(grid, i, j); 
	        }
	      }
	    }
	    return count;
	  }
	  
	  public int numIslands_2(char[][] grid) {
		    if (grid==null || grid.length==0 || grid[0].length==0) return 0;
		    int count = 0;
		 
		    for (int i=0; i<grid.length; i++) {
		        for (int j=0; j<grid[0].length; j++) {
		            if(grid[i][j] == '1'){
		                count++;
		                dfs_2(grid, i, j);
		            }
		        }
		    }
		    return count;
		}
		 
		public void dfs_2(char[][] grid, int i, int j){
		    //validity checking
		    if(i<0 || j<0 || i>grid.length-1 || j>grid[0].length-1)
		        return;
		 
		    //if current cell is water or visited
		    if(grid[i][j] != '1') return;
		 
		    //set visited cell to '0'
		    grid[i][j] = '0';
		 
		    //merge all adjacent land
		    dfs_2(grid, i-1, j);
		    dfs_2(grid, i+1, j);
		    dfs_2(grid, i, j-1);
		    dfs_2(grid, i, j+1);
		}
	  
	  public static void main(String[] args) {
	    // TODO Auto-generated method stub
	    char[][] grid1 = {
	        {'1','1','1','1','0'},
	        {'1','1','0','1','0'},
	        {'1','1','0','0','0'},
	        {'0','0','0','0','0'}
	    };
	    char[][] grid2 = {
	        {'1','1','0','0','0'},
	        {'1','1','0','0','0'},
	        {'0','0','1','0','0'},
	        {'0','0','0','1','1'}
	    };
	    char[][] grid3 = {
	        {'0','0','0','0','0'},
	        {'0','0','0','0','0'},
	        {'0','0','0','0','0'},
	        {'0','0','0','0','0'}
	    };
	    char[][] grid4 = {
	        {'1','1','1','1','1'},
	        {'1','1','1','1','1'},
	        {'1','1','1','1','1'},
	        {'1','1','1','1','1'}
	    };
	    char[][] grid5 = {
	        {'1','1','1'},
	        {'0','1','0'},
	        {'1','1','1'}
	    };
	    
	    NumberofIslands slt = new NumberofIslands();
	    System.out.println(numIslands_1(grid1));
	    System.out.println(numIslands_1(grid2));
	    System.out.println(slt.numIslands_2(grid3));
	    System.out.println(slt.numIslands_2(grid4));
	    System.out.println(slt.numIslands_2(grid5));
	  }

}
