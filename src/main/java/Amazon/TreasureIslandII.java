package Amazon;

import java.util.*;

/**
 * You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to one of the treasure islands.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from one of the starting point (marked as S) of the map and can move one block up, down, left or right at a time. The treasure island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to get to any of the treasure islands.
 *
 * Example:
 *
 * Input:
 * [['S', 'O', 'O', 'S', 'S'],
 *  ['D', 'O', 'D', 'O', 'D'],
 *  ['O', 'O', 'O', 'O', 'X'],
 *  ['X', 'D', 'D', 'O', 'O'],
 *  ['X', 'D', 'D', 'D', 'O']]
 *
 * Output: 3
 * Explanation:
 * You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0). Here the shortest route is (0, 3), (1, 3), (2, 3), (2, 4).
 * Related problems:
 *
 * https://leetcode.com/problems/01-matrix
 */

public class TreasureIslandII {




    public int shortestPathToTreasureIsland(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int row=grid.length, col=grid[0].length, minStep=Integer.MAX_VALUE;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j]=='S') {
                    minStep=Math.min(minStep, this.bfs(grid, i, j));
                }
            }
        }
        return minStep;
    }

    private int bfs(char[][] grid, int row, int col) {
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{row, col});
        int steps=0;
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        int[][] directions=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        while(!queue.isEmpty()) {
            int size=queue.size();
            while(size>0) {
                int[] coor=queue.poll();
                int x=coor[0], y=coor[1];
                if(grid[x][y]=='X') return steps;
                visited[x][y]=true;
                for(int i=0; i<directions.length; i++) {
                    int dx=x+directions[i][0];
                    int dy=y+directions[i][1];
                    if(dx>=0 && dx<grid.length && dy>=0 && dy<grid[0].length && !visited[dx][dy] && grid[dx][dy]!='D') {
                        queue.offer(new int[]{dx,dy});
                    }
                }
                size--;
            }
            steps++;
        }
        return -1;
    }
}
