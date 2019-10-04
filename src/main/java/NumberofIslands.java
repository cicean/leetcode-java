import java.util.ArrayList;
import java.util.List;

/**
 * Source : https://leetcode.com/problems/number-of-islands/ Author : Hexiaoqiao
 * Date : 2015-05-03
 * <p>
 * 0.Problem: Given a 2d grid map of '1's (land) and '0's (water), count the
 * number of islands. An island is surrounded by water and is formed by
 * connecting adjacent lands horizontally or vertically. You may assume all four
 * edges of the grid are all surrounded by water. Example 1: 11110 11010 11000
 * 00000 Answer: 1
 * <p>
 * Example 2: 11000 11000 00100 00011 Answer: 3
 * <p>
 * 1.Refer.: 1.1DFS
 * <p>
 * 2.1The basic idea of the following solution is merging adjacent lands, and
 * the merging should be done recursively.
 * <p>
 * 3.1
 */

public class NumberofIslands {

	public static int numIslands_1(char[][] grid) {
		return dfs(grid);
	}

	public static void cdfs(char[][] grid, int x, int y) {
		int xlength = grid.length;
		int ylength = grid[0].length;
		if (x >= xlength || y > ylength)
			return;
		grid[x][y] = 't';
		if (x + 1 < xlength && grid[x + 1][y] == '1')
			cdfs(grid, x + 1, y);
		if (y + 1 < ylength && grid[x][y + 1] == '1')
			cdfs(grid, x, y + 1);
		if (y - 1 >= 0 && grid[x][y - 1] == '1')
			cdfs(grid, x, y - 1);
		if (x - 1 >= 0 && grid[x - 1][y] == '1')
			cdfs(grid, x - 1, y);
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
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		int count = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					count++;
					dfs_2(grid, i, j);
				}
			}
		}
		return count;
	}

	public void dfs_2(char[][] grid, int i, int j) {
		// validity checking
		if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1)
			return;

		// if current cell is water or visited
		if (grid[i][j] != '1')
			return;

		// set visited cell to '0'
		grid[i][j] = '0';

		// merge all adjacent land
		dfs_2(grid, i - 1, j);
		dfs_2(grid, i + 1, j);
		dfs_2(grid, i, j - 1);
		dfs_2(grid, i, j + 1);
	}

	class UnionFind {
		int[] father;
		int m, n;
		int count = 0;
		UnionFind(char[][] grid) {
			m = grid.length;
			n = grid[0].length;
			father = new int[m*n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == '1') {
						int id = i * n + j;
						father[id] = id;
						count++;
					}
				}
			}
		}
		public void union(int node1, int node2) {
			int find1 = find(node1);
			int find2 = find(node2);
			if(find1 != find2) {
				father[find1] = find2;
				count--;
			}
		}
		public int find (int node) {
			if (father[node] == node) {
				return node;
			}
			father[node] = find(father[node]);
			return father[node];
		}
	}

	// FaceBook return linked island area follow up

	// �����������
	private int connected(int[][] matrix, int a, int b) {
		if (a < 0 || a >= matrix.length || b < 0 || b >= matrix[a].length
				|| matrix[a][b] != 1) {
			return 0;
		}
		matrix[a][b] = 2;
		return 1 + connected(matrix, a - 1, b) + connected(matrix, a, b - 1)
				+ connected(matrix, a + 1, b) + connected(matrix, a, b + 1);
	}

	public List<Integer> numIslands_area2(int[][] grid) {
		List<Integer> res = new ArrayList<>();
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return res;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					res.add(connected(grid, i, j));
				}
			}
		}
		return res;
	}

	public List<Integer> numIslands_area(char[][] grid) {
		List<Integer> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return res;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					System.out.println(i + ", " + j);
					dfs_area(grid, i, j, 0, path);
					res.add(path.get(path.size() - 1));
				}
			}
		}
		return res;
	}

	private void dfs_area(char[][] grid, int i, int j, int area,
			List<Integer> path) {
		if (i >= grid.length || j > grid[i].length) {
			return;
		}

		grid[i][j] = 't';
		area++;
		path.add(area);

		System.out.print(area + ", ");
		if (i + 1 < grid.length && grid[i + 1][j] == '1')
			dfs_area(grid, i + 1, j, area, path);
		if (j + 1 < grid[i].length && grid[i][j + 1] == '1')
			dfs_area(grid, i, j + 1, area, path);
		if (j - 1 >= 0 && grid[i][j - 1] == '1')
			dfs_area(grid, i, j - 1, area, path);
		if (i - 1 >= 0 && grid[i - 1][j] == '1')
			dfs_area(grid, i - 1, j, area, path);
		// res.add(area);
	}

	// return the Maxmin area of islands
	public int numIslands_Maxarea(int[][] grid) {
		int max = -1;
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return max;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					System.out.println(connected(grid, i, j));
					max = Math.max(max, connected(grid, i, j));
					System.out.println(max);
				}
			}
		}
		return max;
	}

	public void printList(List<Integer> result) {
		if (result == null || result.size() == 0)
			System.out.println("Empty List!");
		System.out.print(" [");
		for (Integer l : result) {
			if (result.lastIndexOf(l) == result.size() - 1) {
				System.out.println(l + "]");
			} else {
				System.out.print(l + ",");
			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] grid1 = { { '1', '1', '1', '1', '0' },
				{ '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' } };
		char[][] grid2 = { { '1', '1', '0', '0', '0' },
				{ '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };
		char[][] grid3 = { { '0', '0', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' }, { '0', '0', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' } };
		char[][] grid4 = { { '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1' } };
		char[][] grid5 = { { '1', '1', '1' }, { '0', '1', '0' },
				{ '1', '1', '1' } };

		int[][] grid_area = { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 1, 1 } };

		NumberofIslands slt = new NumberofIslands();
		// System.out.println(numIslands_1(grid1));
		// System.out.println(numIslands_1(grid2));
		// System.out.println(slt.numIslands_2(grid3));
		// System.out.println(slt.numIslands_2(grid4));
		// System.out.println(slt.numIslands_2(grid5));
		// slt.printList(slt.numIslands_area(grid2));
		slt.printList(slt.numIslands_area2(grid_area));
		// System.out.println(slt.numIslands_Maxarea(grid_area));

	}

}
