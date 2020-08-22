import java.util.*;

/**
 * 505. The Maze II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 Example 1

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (4, 4)

 Output: 12
 Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

 Example 2

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (3, 2)

 Output: -1
 Explanation: There is no way for the ball to stop at the destination.

 Note:
 There is only one ball and one destination in the maze.
 Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */

public class TheMazeII {

  /**
   * Similar to The Maze. Easy-understanding Java bfs solution.
   Solution of The Maze: https://discuss.leetcode.com/topic/77471/easy-understanding-java-bfs-solution
   Solution of The Maze III: https://discuss.leetcode.com/topic/77474/similar-to-the-maze-ii-easy-understanding-java-bfs-solution

   We need to use PriorityQueue instead of standard queue, and record the minimal length of each point.
   */

  public class Solution {
    class Point {
      int x,y,l;
      public Point(int _x, int _y, int _l) {x=_x;y=_y;l=_l;}
    }
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
      int m=maze.length, n=maze[0].length;
      int[][] length=new int[m][n]; // record length
      for (int i=0;i<m*n;i++) length[i/n][i%n]=Integer.MAX_VALUE;
      int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
      PriorityQueue<Point> list=new PriorityQueue<>((o1,o2)->o1.l-o2.l); // using priority queue
      list.offer(new Point(start[0], start[1], 0));
      while (!list.isEmpty()) {
        Point p=list.poll();
        if (length[p.x][p.y]<=p.l) continue; // if we have already found a route shorter
        length[p.x][p.y]=p.l;
        for (int i=0;i<4;i++) {
          int xx=p.x, yy=p.y, l=p.l;
          while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0) {
            xx+=dir[i][0];
            yy+=dir[i][1];
            l++;
          }
          xx-=dir[i][0];
          yy-=dir[i][1];
          l--;
          list.offer(new Point(xx, yy, l));
        }
      }
      return length[destination[0]][destination[1]]==Integer.MAX_VALUE?-1:length[destination[0]][destination[1]];
    }
  }

  //The idea is similar to the Dijkstra's shortest Path Algorithm.
  public class Solution2 {
    private int[][] maze;
    private int[][] minSteps;//memorize the minimus steps to reach each position
    private int[][] dirs={{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
      this.maze=maze;
      this.minSteps=new int[maze.length][maze[0].length];
        /*Initiallize minSteps matrix*/
      for(int i=0; i<maze.length; i++){
        for(int j=0; j<maze[0].length; j++){
          minSteps[i][j]=Integer.MAX_VALUE;
        }
      }
        /*Optimization: check if the destination is impossible to Reach*/
      boolean desL=canRoll(destination[0], destination[1], dirs[0]);
      boolean desR=canRoll(destination[0], destination[1], dirs[1]);
      boolean desD=canRoll(destination[0], destination[1], dirs[2]);
      boolean desU=canRoll(destination[0], destination[1], dirs[3]);
      if(desL && desR && desD && desU) return -1; //all neighbors are walls
      else if(!(desL||desR||desD||desU)) return -1; //all neighbors are empty spaces
      else if(desL && desR && !desU && !desD) return -1; //two opposite neigbhors are walls, and the other two are empty spaces
      else if(!desL && !desR && desU && desD) return -1;//two opposite neigbhors are walls, and the other two are empty spaces

      minSteps[start[0]][start[1]]=0;
        /*BFS; Optimization: use PriorityQueue based on the steps instead of Queue*/
      PriorityQueue<Position> pq=new PriorityQueue<>();
      pq.offer(new Position(start[0], start[1], 0));
      while(!pq.isEmpty()){
        Position pos=pq.poll();
            /*optimization: if the destination is at the head of the queue, we are done*/
        if(pos.r==destination[0] && pos.c==destination[1]) return pos.steps;
        for(int[] dir: dirs){
          int r=pos.r, c=pos.c, currSteps=0;
          while(canRoll(r, c, dir)){
            r+=dir[0];
            c+=dir[1];
            currSteps++;
          }
          int totalSteps=pos.steps+currSteps;
          if(totalSteps<minSteps[r][c] && totalSteps<minSteps[destination[0]][destination[1]]){
            minSteps[r][c]=totalSteps;
            pq.offer(new Position(r, c, totalSteps));
          }
        }
      }
        /*May not be able to reach the destination*/
      return minSteps[destination[0]][destination[1]]==Integer.MAX_VALUE? -1: minSteps[destination[0]][destination[1]];
    }

    private boolean canRoll(int r, int c, int[] dir){
      r+=dir[0];
      c+=dir[1];
      if(r<0 || c<0 || r>=maze.length || c>=maze[0].length || maze[r][c]==1) return false;
      return true;
    }
  }

  class Position implements Comparable<Position>{
    public int r;
    public int c;
    public int steps;

    public Position(int r, int c, int s){
      this.r=r;
      this.c=c;
      this.steps=s;
    }
    @Override
    public int compareTo(Position other){
      return this.steps-other.steps;
    }
  }

  /**
   * Approach #1 Depth First Search [Accepted]
   * We can view the given search space in the form of a tree. The root node of the tree represents the starting position. Four different routes are possible from each position i.e. left, right, up or down. These four options can be represented by 4 branches of each node in the given tree. Thus, the new node reached from the root traversing over the branch represents the new position occupied by the ball after choosing the corresponding direction of travel.
   *
   * Maze_Tree
   *
   * In order to do this traversal, one of the simplest schemes is to undergo depth first search. We make use of a recursive function dfs for this. From every current position, we try to go as deep as possible into the levels of a tree taking a particular branch traversal direction as possible. When one of the deepest levels is exhausted, we continue the process by reaching the next deepest levels of the tree. In order to travel in the various directions from the current position, we make use of a dirsdirs array. dirsdirs is an array with 4 elements, where each of the elements represents a single step of a one-dimensional movement. For travelling in a particular direction, we keep on adding the appropriate dirsdirs element in the current position till the ball hits a boundary or a wall.
   *
   * We start with the given startstart position, and try to explore these directions represented by the dirsdirs array one by one. For every element dirdir of the dirsdirs chosen for the current travelling direction, we determine how far can the ball travel in this direction prior to hitting a wall or a boundary. We keep a track of the number of steps using countcount variable.
   *
   * Apart from this, we also make use of a 2-D distancedistance array. distance[i][j]distance[i][j] represents the minimum number of steps required to reach the positon (i, j)(i,j) starting from the startstart position. This array is initialized with largest integer values in the beginning.
   *
   * When we reach any position next to a boundary or a wall during the traversal in a particular direction, as discussed earlier, we keep a track of the number of steps taken in the last direction in countcount variable. Suppose, we reach the position (i,j)(i,j) starting from the last position (k,l)(k,l). Now, for this position, we need to determine the minimum number of steps taken to reach this position starting from the startstart position. For this, we check if the current path takes lesser steps to reach (i,j)(i,j) than any other previous path taken to reach the same position i.e. we check if distance[k][l] + countdistance[k][l]+count is lesser than distance[i][j]distance[i][j]. If not, we continue the process of traversal from the position (k,l)(k,l) in the next direction.
   *
   * If distance[k][l] + countdistance[k][l]+count is lesser than distance[i][j]distance[i][j], we can reach the position (i,j)(i,j) from the current route in lesser number of steps. Thus, we need to update the value of distance[i][j]distance[i][j] as distance[k][l] + countdistance[k][l]+count. Further, now we need to try to reach the destination, destdest, from the end position (i,j)(i,j), since this could lead to a shorter path to destdest. Thus, we again call the same function dfs but with the position (i,j)(i,j) acting as the current position.
   *
   * After this, we try to explore the routes possible by choosing all the other directions of travel from the current position (k,l)(k,l) as well.
   *
   * At the end, the entry in distance array corresponding to the destination, destdest's coordinates gives the required minimum distance to reach the destination. If the destination can't be reached, the corresponding entry will contain \text{Integer.MAX_VALUE}.
   *
   * The following animation depicts the process.
   * Complexity Analysis
   *
   * Time complexity : O(m*n*\text{max}(m,n))O(m∗n∗max(m,n)). Complete traversal of maze will be done in the worst case. Here, mm and nn refers to the number of rows and columns of the maze. Further, for every current node chosen, we can travel upto a maximum depth of \text{max}(m,n)max(m,n) in any direction.
   *
   * Space complexity : O(mn)O(mn). distancedistance array of size m*nm∗n is used.
   */

  public class Solution_dfs {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
      int[][] distance = new int[maze.length][maze[0].length];
      for (int[] row: distance)
        Arrays.fill(row, Integer.MAX_VALUE);
      distance[start[0]][start[1]] = 0;
      dfs(maze, start, distance);
      return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dfs(int[][] maze, int[] start, int[][] distance) {
      int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
      for (int[] dir: dirs) {
        int x = start[0] + dir[0];
        int y = start[1] + dir[1];
        int count = 0;
        while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
          x += dir[0];
          y += dir[1];
          count++;
        }
        if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
          distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
          dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
        }
      }
    }
  }

  /**
   * Approach #2 Using Breadth First Search [Accepted]
   * Algorithm
   *
   * Instead of making use of Depth First Search for exploring the search space, we can make use of Breadth First Search as well. In this, instead of exploring the search space on a depth basis, we traverse the search space(tree) on a level by level basis i.e. we explore all the new positions that can be reached starting from the current position first, before moving onto the next positions that can be reached from these new positions.
   *
   * In order to make a traversal in any direction, we again make use of dirsdirs array as in the DFS approach. Again, whenever we make a traversal in any direction, we keep a track of the number of steps taken while moving in this direction in countcount variable as done in the last approach. We also make use of distancedistance array initialized with very large values in the beginning. distance[i][j]distance[i][j] again represents the minimum number of steps required to reach the position (i,j)(i,j) from the startstart position.
   *
   * This approach differs from the last approach only in the way the search space is explored. In order to reach the new positions in a Breadth First Search order, we make use of a queuequeue, which contains the new positions to be explored in the future. We start from the current position (k,l)(k,l), try to traverse in a particular direction, obtain the corresponding countcount for that direction, reaching an end position of (i,j)(i,j) just near the boundary or a wall. If the position (i,j)(i,j) can be reached in a lesser number of steps from the current route than any other previous route checked, indicated by distance[k][l] + count < distance[i][j]distance[k][l]+count<distance[i][j], we need to update distance[i][j]distance[i][j] as distance[k][l] + countdistance[k][l]+count.
   *
   * After this, we add the new position obtained, (i,j)(i,j) to the back of a queuequeue, so that the various paths possible from this new position will be explored later on when all the directions possible from the current position (k,l)(k,l) have been explored. After exploring all the directions from the current position, we remove an element from the front of the queuequeue and continue checking the routes possible through all the directions now taking the new position(obtained from the queuequeue) as the current position.
   *
   * Again, the entry in distance array corresponding to the destination, destdest's coordinates gives the required minimum distance to reach the destination. If the destination can't be reached, the corresponding entry will contain \text{Integer.MAX_VALUE}.
   * Complexity Analysis
   *
   * Time complexity : O(m*n*max(m,n))O(m∗n∗max(m,n)). Time complexity : O(m*n*\text{max}(m,n))O(m∗n∗max(m,n)). Complete traversal of maze will be done in the worst case. Here, mm and nn refers to the number of rows and columns of the maze. Further, for every current node chosen, we can travel upto a maximum depth of \text{max}(m,n)max(m,n) in any direction.
   *
   * Space complexity : O(mn)O(mn). queuequeue size can grow upto m*nm∗n in the worst case.
   */

  public class Solution_bfs {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
      int[][] distance = new int[maze.length][maze[0].length];
      for (int[] row: distance)
        Arrays.fill(row, Integer.MAX_VALUE);
      distance[start[0]][start[1]] = 0;
      int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
      Queue < int[] > queue = new LinkedList < > ();
      queue.add(start);
      while (!queue.isEmpty()) {
        int[] s = queue.remove();
        for (int[] dir: dirs) {
          int x = s[0] + dir[0];
          int y = s[1] + dir[1];
          int count = 0;
          while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
            x += dir[0];
            y += dir[1];
            count++;
          }
          if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
            distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
            queue.add(new int[] {x - dir[0], y - dir[1]});
          }
        }
      }
      return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
  }

  /**
   * Approach #3 Using Dijkstra Algorithm [Accepted]
   * Algorithm
   *
   * Before we look into this approach, we take a quick overview of Dijkstra's Algorithm.
   *
   * Dijkstra's Algorithm is a very famous graph algorithm, which is used to find the shortest path from any startstart node to any destinationdestination node in the given weighted graph(the edges of the graph represent the distance between the nodes).
   *
   * The algorithm consists of the following steps:
   *
   * Assign a tentative distance value to every node: set it to zero for our startstart node and to infinity for all other nodes.
   *
   * Set the startstart node as currentcurrent node. Mark it as visited.
   *
   * For the currentcurrent node, consider all of its neighbors and calculate their tentative distances. Compare the newly calculated tentative distance to the current assigned value and assign the smaller one to all the neighbors. For example, if the current node A is marked with a distance of 6, and the edge connecting it with a neighbor B has length 2, then the distance to B (through A) will be 6 + 2 = 8. If B was previously marked with a distance greater than 8 then change it to 8. Otherwise, keep the current value.
   *
   * When we are done considering all of the neighbors of the current node, mark the currentcurrent node as visited. A visited node will never be checked again.
   *
   * If the destinationdestination node has been marked visited or if the smallest tentative distance among all the nodes left is infinity(indicating that the destinationdestination can't be reached), then stop. The algorithm has finished.
   *
   * Otherwise, select the unvisited node that is marked with the smallest tentative distance, set it as the new currentcurrent node, and go back to step 3.
   *
   * The working of this algorithm can be understood by taking two simple examples. Consider the first set of nodes as shown below.
   *
   * Dijkstra_Graph
   *
   * Suppose that the node bb is at a shorter distance from the startstart node aa as compared to cc, but the distance from aa to the destinationdestination node, ee, is shorter through the node cc itself. In this case, we need to check if the Dijkstra's algorithm works correctly, since the node bb is considered first while selecting the nodes being at a shorter distance from aa. Let's look into this.
   *
   * Firstly, we choose aa as the startstart node, mark it as visited and update the distance_bdistance
   * b
   * ​
   *   and distance_cdistance
   * c
   * ​
   *   values. Here, distance_idistance
   * i
   * ​
   *   represents the distance of node ii from the startstart node.
   *
   * Since distance_b < distance_cdistance
   * b
   * ​
   *  <distance
   * c
   * ​
   *  , bb is chosen as the next node for calculating the distances. We mark bb as visited. Now, we update the distance_edistance
   * e
   * ​
   *   value as distance_b + weight_{be}distance
   * b
   * ​
   *  +weight
   * be
   * ​
   *  .
   *
   * Now, cc is obviously the next node to be chosen as per the conditions of the assumptions taken above. (For path to ee through cc to be shorter than path to ee through cc, distance_c < distance_b + weight_{be}distance
   * c
   * ​
   *  <distance
   * b
   * ​
   *  +weight
   * be
   * ​
   *  . From cc, we determine the distance to node ee. Since distance_c + weight_{ce}distance
   * c
   * ​
   *  +weight
   * ce
   * ​
   *   is shorter than the previous value of distance_edistance
   * e
   * ​
   *  , we update distance_edistance
   * e
   * ​
   *   with the correct shorter value.
   *
   * We choose ee as the current node. No other distances need to be updated. Thus, we mark ee as visited. distance_edistance
   * e
   * ​
   *   now gives the required shortest distance.
   *
   * The above example proves that even if a locally closer node is chosen as the current node first, the ultimate shortest distance to any node is calculated correctly.
   *
   * Let's take another example to demonstrate that the visited node needs not be chosen again as the current node.
   *
   * Dijkstra_Graph
   *
   * Suppose aa is the startstart node and ee is the destinationdestination node. Now, suppose we visit bb first and mark it as visited, but later on we find that another path exists through cc to bb, which makes the distance_bdistance
   * b
   * ​
   *   shorter than the previous value. But, because of this, we need to consider bb as the current node again, since it would affect the value of distance_edistance
   * e
   * ​
   *  . But, if we observe closely, such a situation would never occur, because for weight_{ac} + weight_{cb}weight
   * ac
   * ​
   *  +weight
   * cb
   * ​
   *   to be lesser than weight_{ab}weight
   * ab
   * ​
   *  , weight_{ac} < weight_{ab}weight
   * ac
   * ​
   *  <weight
   * ab
   * ​
   *   in the first place. Thus, bb would never be marked visitedvisited before cc, which contradicts the first assumption. This proves that the visitedvisited node needs not be chosen as the current node again.
   *
   * The given problem is also a shortest distance finding problem with a slightly different set of rules. Thus, we can make use of Dijkstra's Algorithm to determine the minimum number of steps to reach the destination.
   *
   * The methodology remains the same as the DFS or BFS Approach discussed above, except the order in which the current positions are chosen. We again make use of a distancedistance array to keep a track of the minimum number of steps needed to reach every position from the startstart position. At every step, we choose a position which hasn't been marked as visited and which is at the shortest distance from the startstart position to be the current position. We mark this position as visited so that we don't consider this position as the current position again.
   *
   * From the current position, we determine the number of steps required to reach all the positions possible travelling from the current position(in all the four directions possible till hitting a wall/boundary). If it is possible to reach any position through the current route with a lesser number of steps than the earlier routes considered, we update the corresponding distancedistance entry. We continue the same process for the other directions as well for the current position.
   *
   * In order to determine the current node, we make use of minDistance function, in which we traverse over the whole distancedistance array and find out an unvisited node at the shortest distance from the startstart node.
   *
   * At the end, the entry in distancedistance array corresponding to the destinationdestination position gives the required minimum number of steps. If the destination can't be reached, the corresponding entry will contain \text{Integer.MAX_VALUE}.
   *
   * **Complexity Analysis**
   * Time complexity : O((mn)^2)O((mn)
   * 2
   *  ). Complete traversal of maze will be done in the worst case and function minDistance takes O(mn)O(mn) time.
   *
   * Space complexity : O(mn)O(mn). distancedistance array of size m*nm∗n is used.
   */

  public class Solution_dijkstra {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
      int[][] distance = new int[maze.length][maze[0].length];
      boolean[][] visited = new boolean[maze.length][maze[0].length];
      for (int[] row: distance)
        Arrays.fill(row, Integer.MAX_VALUE);
      distance[start[0]][start[1]] = 0;
      dijkstra(maze, distance, visited);
      return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
    public int[] minDistance(int[][] distance, boolean[][] visited) {
      int[] min={-1,-1};
      int min_val = Integer.MAX_VALUE;
      for (int i = 0; i < distance.length; i++) {
        for (int j = 0; j < distance[0].length; j++) {
          if (!visited[i][j] && distance[i][j] < min_val) {
            min = new int[] {i, j};
            min_val = distance[i][j];
          }
        }
      }
      return min;
    }
    public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
      int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
      while (true) {
        int[] s = minDistance(distance, visited);
        if (s[0] < 0)
          break;
        visited[s[0]][s[1]] = true;
        for (int[] dir: dirs) {
          int x = s[0] + dir[0];
          int y = s[1] + dir[1];
          int count = 0;
          while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
            x += dir[0];
            y += dir[1];
            count++;
          }
          if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
            distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
          }
        }
      }
    }
  }

  /**
   * Approach #4 Using Dijkstra Algorithm and Priority Queue[Accepted]
   * Algorithm
   *
   * In the last approach, in order to choose the current node, we traversed over the whole distancedistance array and found out an unvisited node at the shortest distance from the startstart node. Rather than doing this, we can do the same task much efficiently by making use of a Priority Queue, queuequeue. This priority queue is implemented internally in the form of a heap. The criteria used for heapifying is that the node which is unvisited and at the smallest distance from the startstart node, is always present on the top of the heap. Thus, the node to be chosen as the current node, is always present at the front of the queuequeue.
   *
   * For every current node, we again try to traverse in all the possible directions. We determine the minimum number of steps(till now) required to reach all the end points possible from the current node. If any such end point can be reached in a lesser number of steps through the current path than the paths previously considered, we need to update its distancedistance entry.
   *
   * Further, we add an entry corresponding to this node in the queuequeue, since its distancedistance entry has been updated and we need to consider this node as the competitors for the next current node choice. Thus, the process remains the same as the last approach, except the way in which the pick out the current node(which is the unvisited node at the shortest distance from the startstart node).
   * **Complexity Analysis**
   * Time complexity : O\big(mn*log(mn)\big)O(mn∗log(mn)). Complete traversal of maze will be done in the worst case giving a factor of mnmn. Further, poll method is a combination of heapifying(O\big(log(n)\big)O(log(n))) and removing the top element(O(1)O(1)) from the priority queue, and it takes O(n)O(n) time for nn elements. In the current case, poll introduces a factor of log(mn)log(mn).
   *
   * Space complexity : O(mn)O(mn). distancedistance array of size m*nm∗n is used and queuequeue size can grow upto m*nm∗n in worst case.
   */


  public class Solution_dijkstra_PQ {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
      int[][] distance = new int[maze.length][maze[0].length];
      for (int[] row: distance)
        Arrays.fill(row, Integer.MAX_VALUE);
      distance[start[0]][start[1]] = 0;
      dijkstra(maze, start, distance);
      return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
      int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
      PriorityQueue < int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
      queue.offer(new int[]{start[0],start[1],0});
      while (!queue.isEmpty()) {
        int[] s = queue.poll();
        if(distance[s[0]][s[1]] < s[2])
          continue;
        for (int[] dir: dirs) {
          int x = s[0] + dir[0];
          int y = s[1] + dir[1];
          int count = 0;
          while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
            x += dir[0];
            y += dir[1];
            count++;
          }
          if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
            distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
            queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
          }
        }
      }
    }
  }

  class Solution {
    //
    // reference:
    // https://www.cnblogs.com/grandyang/p/6725380.html
    //
    // DFS
    //
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {

      if (maze == null || maze.length == 0 || maze[0].length == 0) return -1;

      if (Arrays.equals(start, destination))  return 0;

      int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

      int m = maze.length;
      int n = maze[0].length;

      int[][] distance = new int[m][n];
      for (int[] dist : distance) {
        Arrays.fill(dist, Integer.MAX_VALUE);
      }
      distance[start[0]][start[1]] = 0;

      Queue<int[]> q = new LinkedList<>();
      q.offer(start);

      while (!q.isEmpty()) {
        int[] grid = q.poll();

        for (int[] dir : dirs) {
          int count = 0;

          int r = grid[0];
          int c = grid[1];

          while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] == 0) {
            r += dir[0];
            c += dir[1];
            count++;
          }
          r -= dir[0];
          c -= dir[1];
          count--;

          if (distance[grid[0]][grid[1]] + count < distance[r][c]) {
            distance[r][c] = distance[grid[0]][grid[1]] + count;
            if (r != destination[0] || c != destination[1]) {
              q.offer(new int[] {r, c});
            }
          }
        }
      }

      return distance[destination[0]][destination[1]] == Integer.MAX_VALUE
              ? -1 : distance[destination[0]][destination[1]];
    }
  }

}
