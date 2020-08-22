import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 305. Number of Islands II
 * Hard
 *
 * 766
 *
 * 20
 *
 * Add to List
 *
 * Share
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 *
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 *
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * Follow up:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 *
 * Accepted
 * 71,707
 * Submissions
 * 178,147
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Uber
 * |
 * 9
 *
 * Facebook
 * |
 * 2
 *
 * Oracle
 * |
 * 2
 * Number of Islands
 * Medium
 *
 * Problem Description:

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

 Challenge:

 Can you do it in time complexity O(k log mn), where k is the length of the positions?

 Hide Company Tags Google
 Hide Tags Union Find
 Hide Similar Problems (M) Number of Islands


 * @author cicean
 * 
 * Union Find is an abstract data structure supporting find and unite on disjointed sets of objects, typically used to solve the network connectivity problem.

The two operations are defined like this:

find(a,b) : are a and b belong to the same set?

unite(a,b) : if a and b are not in the same set, unite the sets they belong to.

With this data structure, it is very fast for solving our problem. Every position is an new land, if the new land connect two islands a and b, we combine them to form a whole. The answer is then the number of the disjointed sets.

The following algorithm is derived from Princeton's lecture note on Union Find in Algorithms and Data Structures It is a well organized note with clear illustration describing from the naive QuickFind to the one with Weighting and Path compression. With Weighting and Path compression, The algorithm runs in O((M+N) log* N) where M is the number of operations ( unite and find ), N is the number of objects, log* is iterated logarithm while the naive runs in O(MN).

For our problem, If there are N positions, then there are O(N) operations and N objects then total is O(N log*N), when we don't consider the O(mn) for array initialization.

Note that log*N is almost constant (for N = 265536, log*N = 5) in this universe, so the algorithm is almost linear with N.

However, if the map is very big, then the initialization of the arrays can cost a lot of time when mn is much larger than N. In this case we should consider using a hashmap/dictionary for the underlying data structure to avoid this overhead.

Of course, we can put all the functionality into the Solution class which will make the code a lot shorter. But from a design point of view a separate class dedicated to the data sturcture is more readable and reusable.

I implemented the idea with 2D interface to better fit the problem.
 *
 */
public class NumberofIslandsII {
	
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
	    //use an array to hold root number of each island
	    int[] roots = new int[m*n];
	    //initialize the array with -1, so we know non negative number is a root number
	    Arrays.fill(roots, -1);

	    int[] xOffset ={0, 0, 1, -1};
	    int[] yOffset = {1, -1, 0, 0};

	    List<Integer> result = new ArrayList<Integer>();

	    for(int[] position : positions){
	        //for each input cell, its initial root number is itself
	        roots[position[0]*n + position[1]] = position[0]*n + position[1];
	        //count variable is used to count the island in current matrix.
	        //firstly, we assume current input is an isolated island
	        int count = result.isEmpty()? 1 : result.get(result.size()-1) + 1;
	        //check neighbor cells
	        for(int i = 0; i < 4; i++){
	            int newX = xOffset[i] + position[0];
	            int newY = yOffset[i] + position[1];
	            //if we found one neighbor is a part of island
	            if(newX >= 0 && newX < m && newY >= 0 && newY < n && roots[newX * n + newY] != -1){
	                //get the root number of this island
	                int root1 = find(newX * n + newY, roots);
	                //get the root number of input island
	                int root2 = roots[position[0]*n + position[1]];
	                //if root1 and root2 are different, then we can connect two isolated island together,
	                // so the num of island - 1
	                if(root1 != root2) count--;
	                //update root number accordingly
	                roots[root1] = root2;
	            }
	        }
	        result.add(count); 
	    }

	    return result;
	}

	public int find(int target, int[] roots){
	    //found root
	    if(roots[target] == target) return target;
	    //searching for root and update the cell accordingly
	    roots[target] = find(roots[target], roots);
	    //return root number
	    return roots[target];
	} 
	
	///
	
	
	private int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public List<Integer> numIslands2_1(int m, int n, int[][] positions) {
        UnionFind2D islands = new UnionFind2D(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            int p = islands.add(x, y);
            for (int[] d : dir) {
                int q = islands.getID(x + d[0], y + d[1]);
                if (q > 0 && !islands.find(p, q))
                    islands.unite(p, q);
            }
            ans.add(islands.size());
        }
        return ans;
    }
}

class UnionFind2D {
    private int[] id;
    private int[] sz;
    private int m, n, count;

    public UnionFind2D(int m, int n) {
        this.count = 0;
        this.n = n;
        this.m = m;
        this.id = new int[m * n + 1];
        this.sz = new int[m * n + 1];
    }

    public int index(int x, int y) { return x * n + y + 1; }

    public int size() { return this.count; }

    public int getID(int x, int y) {
        if (0 <= x && x < m && 0<= y && y < n)
            return id[index(x, y)];
        return 0;
    }

    public int add(int x, int y) {
        int i = index(x, y);
        id[i] = i; sz[i] = 1;
        ++count;
        return i;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void unite(int p, int q) {
        int i = root(p), j = root(q);
        if (sz[i] < sz[j]) { //weighted quick union
            id[i] = j; sz[j] += sz[i];
        } else {
            id[j] = i; sz[i] += sz[j];
        }
        --count;
    }

    private int root(int i) {
        for (;i != id[i]; i = id[i])
            id[i] = id[id[i]]; //path compression
        return i;
    }
    
    
    private Map<Integer, Integer> rootMap = new HashMap<>();
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2_2(int n, int m, Point[] operators) {
      List<Integer> result = new ArrayList<>();
      if (operators == null || n <= 0 || m <= 0) {
        return result;
      }
      boolean[][] islands = new boolean[n][m];
      int count = 0;
      for (Point p: operators) {
        if (!islands[p.x][p.y]) {
          islands[p.x][p.y] = true;
          count++;
          int id = getId(p.x, p.y, m);
          rootMap.put(id, id);
          for (int i = 0; i < 4; ++i) {
            int newX = p.x + DIRS[i][0];
            int newY = p.y + DIRS[i][1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < m && islands[newX][newY]) {
              int newId = getId(newX, newY, m);
              int root = findRoot(id);
              int newRoot = findRoot(newId);
              if (root != newRoot) {
                --count;
                union(root, newRoot);
              }
            }
          }
        }
        result.add(count);
      }
      return result;
    }

    private int getId(int x, int y, int m) {
      return x * m + y;
    }

    private int findRoot(int id) {
      int root = rootMap.get(id);
      while (root != rootMap.get(root)) {
        root = rootMap.get(root);
      }
      return root;
    }

    private void union(int root1, int root2) {
      rootMap.put(root1, root2);
    }

    /**
     * ����
     �ܵ��͵�union-find�⡣��Ϊ�����Ƕ�̬������land��
     Ҫ����ʱ����ж��ٸ�island����򵥵ķ�������union-find��
     ���ǿ��Զ���һ��counter, ÿ����һ��land, ����counter,
     Ȼ�����������Ǹ�land�ھ����򣬷���root��һ���Ļ���
     ��ζ�ſ���union, ÿunionһ�Σ���ζ������island�ϲ���һ������Сcounter,
     ͳ�����յ�counterֵ����������land�������island�ĸ�����

     Ϊ�˼�Сʱ�临�Ӷȣ�����ʵ����QuickUnion + Path Compression,
     Path CompressionĿ����Ϊ�˵������ĸ߶ȣ����ֺ�ƽ������
     ������Խ��Խ�ߣ�������root�������worst case.

     ���Ӷ�
     time: O(Mlog(N)), space: O(N)
     M��ʾ����land��������N��ʾ�����е�ĸ�����m*n��
     */
    public class Solution {
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            int[] id = new int[m * n]; // ��ʾ����index��Ӧ��root

            List<Integer> res = new ArrayList<>();
            Arrays.fill(id, -1); // ��ʼ��rootΪ-1���������water, ��-1��ʾland
            int count = 0; // ��¼island������

            int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            for (int i = 0; i < positions.length; i++) {
                count++;
                int index = positions[i][0] * n + positions[i][1];
                id[index] = index; // root��ʼ��

                for (int j = 0; j < dirs.length; j++) {
                    int x = positions[i][0] + dirs[j][0];
                    int y = positions[i][1] + dirs[j][1];
                    if (x >= 0 && x < m && y >= 0 && y < n && id[x * n + y] != -1) {
                        int root = root(id, x * n + y);

                        // ����root���ȵ�����£���union, ͬʱ��Сcount
                        if (root != index) {
                            id[root] = index;
                            count--;
                        }
                    }
                }
                res.add(count);
            }
            return res;
        }

        public int root(int[] id, int i) {
            while (i != id[i]) {
                id[i] = id[id[i]]; // �Ż���Ϊ�˼�С���ĸ߶�
                i = id[i];
            }
            return i;
        }
    }
}
