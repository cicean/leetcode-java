/*
 79	Word Search	20.3%	Medium
 Problem:    Word Search
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/word-search/
 Notes:
 Given a 2D board and a word, find if the word exists in the grid.
 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are 
 those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 For example,
 Given board =
 [
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.
 Solution: DFS.
 */

public class WordSearch {
	public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) return false;
        int n = board[0].length;
        if (n == 0) return false;
        if (word.length() == 0) return true;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == word.charAt(0) && existRe(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean existRe(char[][] board, int i, int j, String word, int cur, boolean[][] visited) {
        if (cur == word.length()) return true;
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) return false;
        if (visited[i][j] == true || (board[i][j] != word.charAt(cur))) return false;
        visited[i][j] = true;
        if (existRe(board, i+1, j, word, cur+1,visited)) return true;
        if (existRe(board, i-1, j, word, cur+1,visited)) return true;
        if (existRe(board, i, j+1, word, cur+1,visited)) return true;
        if (existRe(board, i, j-1, word, cur+1,visited)) return true;
        visited[i][j] = false;
        return false;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordSearch slt = new WordSearch();
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word1 = "ABCCED";
		String word2 = "SEE";
		String word3 = "ABCB";
		String word4 = "";
		System.out.println(slt.exist(board, word1));
		System.out.println(slt.exist(board, word2));
		System.out.println(slt.exist(board, word3));
		System.out.println(slt.exist(board, word4));
	}

}
