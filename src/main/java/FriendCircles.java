import java.util.*;

/**
 *  547. Friend Circles
 DescriptionHintsSubmissionsDiscussSolution

 There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

 Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

 Example 1:

 Input:
 [[1,1,0],
 [1,1,0],
 [0,0,1]]
 Output: 2
 Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 The 2nd student himself is in a friend circle. So return 2.

 Example 2:

 Input:
 [[1,1,0],
 [1,1,1],
 [0,1,1]]
 Output: 1
 Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

 Note:

 N is in range [1,200].
 M[i][i] = 1 for all students.
 If M[i][j] = 1, then M[j][i] = 1.

 */

public class FriendCircles {

  //Neat DFS java solution

  /**
   * Approach #1 Using Depth First Search[Accepted]

   Algorithm

   The given matrix can be viewed as the Adjacency Matrix of a graph. By viewing the matrix in such
   a manner, our problem reduces to the problem of finding the number of connected components in an
   undirected graph. In order to understand the above statement, consider the example matrix below:

   M= [1 1 0 0 0 0

   1 1 0 0 0 0

   0 0 1 1 1 0

   0 0 1 1 0 0

   0 0 1 0 1 0

   0 0 0 0 0 1]
   If we view this matrix M as the adjancency matrix of a graph, the following graph is formed:

   Friend_Circles

   In this graph, the node numbers represent the indices in the matrix M and an edge exists between
   the nodes numbered ii and jj, if there is a 1 at the corresponding M[i][j]M[i][j].

   In order to find the number of connected components in an undirected graph, one of the simplest
   methods is to make use of Depth First Search starting from every node. We make use of visitedvisited
   array of size NN(MM is of size NxNNxN). This visited[i]visited[i] element is used to indicate that the i^{th}i
   ​th
   ​​  node has already been visited while undergoing a Depth First Search from some node.

   To undergo DFS, we pick up a node and visit all its directly connected nodes. But, as soon as we
   visit any of those nodes, we recursively apply the same process to them as well. Thus, we try to
   go as deeper into the levels of the graph as possible starting from a current node first, leaving
   the other direct neighbour nodes to be visited later on.

   The depth first search for an arbitrary graph is shown below:
   Time complexity : O(n^2). The complete matrix of size n^2 is traversed.

   Space complexity : O(n)O(n). A queuequeue and visitedvisited array of size nn is used.
   */

  public class SolutionDFS {
    public void dfs(int[][] M, int[] visited, int i) {
      for (int j = 0; j < M.length; j++) {
        if (M[i][j] == 1 && visited[j] == 0) {
          visited[j] = 1;
          dfs(M, visited, j);
        }
      }
    }
    public int findCircleNum(int[][] M) {
      int[] visited = new int[M.length];
      int count = 0;
      for (int i = 0; i < M.length; i++) {
        if (visited[i] == 0) {
          dfs(M, visited, i);
          count++;
        }
      }
      return count;
    }
  }

  /**
   * Approach #2 Using Breadth First Search[Accepted]

   Algorithm

   As discussed in the above method, if we view the given matrix as an adjacency matrix of a graph,
   we can use graph algorithms easily to find the number of connected components. This approach makes
   use of Breadth First Search for a graph.

   In case of Breadth First Search, we start from a particular node and visit all its directly
   connected nodes first. After all the direct neighbours have been visited, we apply the
   same process to the neighbour nodes as well. Thus, we exhaust the nodes of a graph on a level by
   level basis. An example of Breadth First Search is shown below:
   In this case also, we apply BFS starting from one of the nodes. We make use of a visitedvisited
   array to keep a track of the already visited nodes. We increment the countcount of connected
   components whenever we need to start off with a new node as the root node for applying BFS which hasn't been already visited.
   Time complexity : O(n^2). The complete matrix of size n^2 is traversed.

   Space complexity : O(n)O(n). A queuequeue and visitedvisited array of size nn is used.
   */



  // Java solution, Union Find

  //This is a typical Union Find problem. I abstracted it as a standalone class. Remember the template, you will be able to use it later.

  /**
   * Approach #3 Using Union-Find Method[Accepted]

   Algorithm

   Another method that can be used to determine the number of connected components in a graph is
   the union find method. The method is simple.

   We make use of a parentparent array of size NN. We traverse over all the nodes of the graph.
   For every node traversed, we traverse over all the nodes directly connected to it and assign
   them to a single group which is represented by their parentparent node. This process is called
   forming a unionunion. Every group has a single parentparent node, whose own parent is given by \text{-1}-1.

   For every new pair of nodes found, we look for the parents of both the nodes. If the parents
   nodes are the same, it indicates that they have already been united into the same group.
   If the parent nodes differ, it means they are yet to be united. Thus, for the pair of nodes
   (x, y)(x,y), while forming the union, we assign parent\big[parent[x]\big]=parent[y]parent[parent[x]]=parent[y],
   which ultimately combines them into the same group.

   The following animation depicts the process for a simple matrix:
   Time complexity : O(n^3). We traverse over the complete matrix once. Union and find operations take O(n)O(n) time in the worst case.
   Space complexity : O(n)O(n). parentparent array of size nn is used.
   */

  public class SolutionUF {
    class UnionFind {
      private int count = 0;
      private int[] parent, rank;

      public UnionFind(int n) {
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
          parent[i] = i;
        }
      }

      public int find(int p) {
        while (p != parent[p]) {
          parent[p] = parent[parent[p]];    // path compression by halving
          p = parent[p];
        }
        return p;
      }

      public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (rank[rootQ] > rank[rootP]) {
          parent[rootP] = rootQ;
        }
        else {
          parent[rootQ] = rootP;
          if (rank[rootP] == rank[rootQ]) {
            rank[rootP]++;
          }
        }
        count--;
      }

      public int count() {
        return count;
      }
    }

    public int findCircleNum(int[][] M) {
      int n = M.length;
      UnionFind uf = new UnionFind(n);
      for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          if (M[i][j] == 1) uf.union(i, j);
        }
      }
      return uf.count();
    }
  }





}
