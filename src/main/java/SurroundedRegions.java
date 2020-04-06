import java.util.LinkedList;
import java.util.Queue;

/*
 130	Surrounded Regions	14.6%	Medium
 Problem:    Surrounded Regions
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_130
 Notes:
 Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 A region is captured by flipping all 'O's into 'X's in that surrounded region .
 For example,
 X X X X
 X O O X
 X X O X
 X O X X
 After running your function, the board should be:
 X X X X
 X X X X
 X X X X
 X O X X
 Solution: Traverse from the boarder to the inside and mark all the 'O's that are not surrounded by 'X' as 'V' (visited).
           1. BFS (queue).
 */

public class SurroundedRegions {

    class Solution {
        public void solve(char[][] board) {

            if(board.length ==0 ) {
                return;
            }

            for(int i =0; i< board.length; i++) {
                if(board[i][0] == 'O') {
                    doDfs(board, i , 0);
                }
                if(board[i][board[0].length -1] == 'O') {
                    doDfs(board, i, board[i].length -1);
                }
            }

            for(int i =0 ; i< board[0].length; i++) {
                if(board[0][i] == 'O') {
                    doDfs(board, 0 , i);
                }
                if(board[board.length -1][i] == 'O') {
                    doDfs(board,board.length -1, i);
                }

            }

            for(int i =0; i < board.length; i++){
                for(int j =0; j< board[i].length; j++) {
                    if(board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    else if( board[i][j] == '*') {
                        board[i][j] = 'O';
                    }
                }
            }

        }



        public static void doDfs(char[][] b, int i, int j) {
            if(i < 0 || i >=b.length || j < 0 || j > b[i].length){
                return ;
            }

            b[i][j] = '*';

            if(i >0 && b[i -1][j] == 'O') {
                doDfs(b, i-1, j);
            }
            if(j >0 && b[i ][j-1] == 'O') {
                doDfs(b, i, j -1);
            }
            if(i < b.length -1 && b[i +1][j] == 'O') {
                doDfs(b, i+1, j);
            }
            if(j < b[0].length -1 && b[i][j +1] == 'O') {
                doDfs(b, i, j +1);
            }

            return ;
        }
    }


    class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }


    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int M = board.length, N = board[0].length;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == 0 || j == 0 || i == M - 1 || j == N -1) {
                    bfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < M; ++i)
            for (int j = 0; j < N; ++j)
                board[i][j] = (board[i][j] == 'V') ? 'O' : 'X';
    }
    public void bfs(char[][] board, int row, int col) {
        if (board[row][col] != 'O') return;
        int M = board.length, N = board[0].length;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(row); q.offer(col);
        while (!q.isEmpty()) {
            int i = q.poll(); int j = q.poll();
            if (i < 0 || i == M || j < 0 || j == N) continue;
            if (board[i][j] != 'O') continue;// important to recheck!
            board[i][j] = 'V';
            q.offer(i-1); q.offer(j);
            q.offer(i+1); q.offer(j);
            q.offer(i); q.offer(j-1);
            q.offer(i); q.offer(j+1);
        }
    }

    public class Solution {
        int rows, cols;

        public void solve(char[][] board) {
            if(board == null || board.length == 0) return;

            rows = board.length;
            cols = board[0].length;

            // last one is dummy, all outer O are connected to this dummy
            UnionFind uf = new UnionFind(rows * cols + 1);
            int dummyNode = rows * cols;

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if(board[i][j] == 'O') {
                        if(i == 0 || i == rows-1 || j == 0 || j == cols-1) {
                            uf.union(node(i,j), dummyNode);
                        }
                        else {
                            if(i > 0 && board[i-1][j] == 'O')  uf.union(node(i,j), node(i-1,j));
                            if(i < rows && board[i+1][j] == 'O')  uf.union(node(i,j), node(i+1,j));
                            if(j > 0 && board[i][j-1] == 'O')  uf.union(node(i,j), node(i, j-1));
                            if(j < cols && board[i][j+1] == 'O')  uf.union(node(i,j), node(i, j+1));
                        }
                    }
                }
            }

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if(uf.isConnected(node(i,j), dummyNode)) {
                        board[i][j] = 'O';
                    }
                    else {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        int node(int i, int j) {
            return i * cols + j;
        }
    }

    class UnionFind {
        int [] parents;
        public UnionFind(int totalNodes) {
            parents = new int[totalNodes];
            for(int i = 0; i < totalNodes; i++) {
                parents[i] = i;
            }
        }

        void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if(root1 != root2) {
                parents[root2] = root1;
            }
        }

        int find(int node) {
            while(parents[node] != node) {
                parents[node] = parents[parents[node]]; // path compression
                node = parents[node];
            }

            return node;
        }

        boolean isConnected(int node1, int node2) {
            return find(node1) == find(node2);
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = {};
		SurroundedRegions slt = new SurroundedRegions();
		slt.solve(board);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}
