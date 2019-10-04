import java.util.*;

/**
 * 499. The Maze III
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

 Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

 Example 1

 Input 1: a maze represented by a 2D array

 0 0 0 0 0
 1 1 0 0 1
 0 0 0 0 0
 0 1 0 0 1
 0 1 0 0 0

 Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 Input 3: hole coordinate (rowHole, colHole) = (0, 1)

 Output: "lul"
 Explanation: There are two shortest ways for the ball to drop into the hole.
 The first way is left -> up -> left, represented by "lul".
 The second way is up -> left, represented by 'ul'.
 Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

 Example 2

 Input 1: a maze represented by a 2D array

 0 0 0 0 0
 1 1 0 0 1
 0 0 0 0 0
 0 1 0 0 1
 0 1 0 0 0

 Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 Input 3: hole coordinate (rowHole, colHole) = (3, 0)
 Output: "impossible"
 Explanation: The ball cannot reach the hole.

 Note:
 There is only one ball and one hole in the maze.
 Both the ball and hole exist on an empty space, and they will not be at the same position initially.
 The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

 */

public class TheMazeIII {

  /**
   * Solution of The Maze: https://discuss.leetcode.com/topic/77471/easy-understanding-java-bfs-solution
   Solution of The Maze II: https://discuss.leetcode.com/topic/77472/similar-to-the-maze-easy-understanding-java-bfs-solution

   We just need to implement Comparable of Point, and record the route of every point.
   */

  public class SolutionBFS {
    class Point implements Comparable<Point> {
      int x,y,l;
      String s;
      public Point(int _x, int _y) {x=_x;y=_y;l=Integer.MAX_VALUE;s="";}
      public Point(int _x, int _y, int _l,String _s) {x=_x;y=_y;l=_l;s=_s;}
      public int compareTo(Point p) {return l==p.l?s.compareTo(p.s):l-p.l;}
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
      int m=maze.length, n=maze[0].length;
      Point[][] points=new Point[m][n];
      for (int i=0;i<m*n;i++) points[i/n][i%n]=new Point(i/n, i%n);
      int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
      String[] ds=new String[] {"u","r","d","l"};
      PriorityQueue<Point> list=new PriorityQueue<>(); // using priority queue
      list.offer(new Point(ball[0], ball[1], 0, ""));
      while (!list.isEmpty()) {
        Point p=list.poll();
        if (points[p.x][p.y].compareTo(p)<=0) continue; // if we have already found a route shorter
        points[p.x][p.y]=p;
        for (int i=0;i<4;i++) {
          int xx=p.x, yy=p.y, l=p.l;
          while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0 && (xx!=hole[0] || yy!=hole[1])) {
            xx+=dir[i][0];
            yy+=dir[i][1];
            l++;
          }
          if (xx!=hole[0] || yy!=hole[1]) { // check the hole
            xx-=dir[i][0];
            yy-=dir[i][1];
            l--;
          }
          list.offer(new Point(xx, yy, l, p.s+ds[i]));
        }
      }
      return points[hole[0]][hole[1]].l==Integer.MAX_VALUE?"impossible":points[hole[0]][hole[1]].s;
    }
  }

  /**
   * Clear Java Accepted DFS Solution with Explanation
   * Each time, first add the direction to the path, and then go with that direction,
   * checking for hole along the way. When hit a wall, try to turn, and go with the new direction.
   * For the starting point, don't "go", jump directly to "turn" part.
   */


  public class SolutionDFS {
    int min; //min distance to hole
    String minS; //min distance's path string
    int[] hole;
    int[][] maze;
    int[][] map; //shortest distant traveling from ball to this point
    int[][] dirs = {{0,1},{-1,0},{1,0},{0,-1}}; //r, u, d, l
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
      this.min = Integer.MAX_VALUE;
      this.minS = null;
      this.hole = hole;
      this.maze = maze;
      this.map = new int[maze.length][maze[0].length];
      for(int i = 0; i<map.length; i++) Arrays.fill(map[i], Integer.MAX_VALUE);

      move(ball[0], ball[1], 0, "", -1);
      return (minS==null) ? "impossible" : minS;
    }

    private void move(int r, int c, int cnt, String path, int dir){//dir is a index of dirs
      if(cnt > min || cnt > map[r][c]) return; //not a shortest route for sure
      if(dir!=-1){//if not from start point
        //add path
        if(dir==0) path+='r';
        else if(dir==1) path+='u';
        else if(dir==2) path+='d';
        else path+='l';

        //roll along dir
        while(r>=0 && r<maze.length && c>=0 && c<maze[0].length && maze[r][c]==0){
          map[r][c] = Math.min(map[r][c], cnt);
          if(r==hole[0] && c==hole[1]){//check hole
            if(cnt==min && path.compareTo(minS)<0){
              minS=path;
            }else if(cnt<min){
              min = cnt;
              minS = path;
            }
            return;
          }
          r += dirs[dir][0];
          c += dirs[dir][1];
          cnt++;
        }
        r -= dirs[dir][0];//[r,c] is wall, need to walk back 1 step
        c -= dirs[dir][1];
        cnt--;
      }

      //hit wall (or start) -> try to turn
      for(int i = 0; i<dirs.length; i++){
        if(dir == i) continue;//dont keep going
        if(dir == (3-i)) continue;//dont go back
        int newR = r + dirs[i][0];
        int newC = c + dirs[i][1];
        if(newR>=0 && newR<maze.length && newC>=0 && newC<maze[0].length && maze[newR][newC]==0){//can go
          move(r, c, cnt, path, i);
        }
      }
    }
  }

}
