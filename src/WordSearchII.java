import java.util.*;

/*
 * 	212	Word Search II	14.8%	Hard
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. 
Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, 
you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? 
Does a hash table work? Why or why not? How about a Trie? 
If you would like to learn how to implement a basic trie, 
please work on this problem: Implement Trie (Prefix Tree) first.

Solution 

1.参考 【LeetCode】Word Search 解题报告 ，题目变成给定一组word，检查哪个word可以由board形成。
如果还按照DFS回溯的方法，逐个检查每个word是否在board里，显然效率是比较低的。我们可以利用Trie数据结构，也就是前缀树。
然后dfs时，如果当前形成的单词不在Trie里，就没必要继续dfs下去了。如果当前字符串在trie里，就说明board可以形成这个word。

2.Trie数据结构的实现在LeetCode上也有对应的题目 Implement Trie (Prefix Tree) 


 */
public class WordSearchII {

	public List<String> findWords(char[][] board, String[] words) {
		ArrayList<String> result = new ArrayList<String>();
	 
		int m = board.length;
		int n = board[0].length;
	 
		for (String word : words) {
			boolean flag = false;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					char[][] newBoard = new char[m][n];
					for (int x = 0; x < m; x++)
						for (int y = 0; y < n; y++)
							newBoard[x][y] = board[x][y];
	 
					if (dfs(newBoard, word, i, j, 0)) {
						flag = true;
					}
				}
			}
			if (flag) {
				result.add(word);
			}
		}
	 
		return result;
	}
	 
	public boolean dfs(char[][] board, String word, int i, int j, int k) {
		int m = board.length;
		int n = board[0].length;
	 
		if (i < 0 || j < 0 || i >= m || j >= n || k > word.length() - 1) {
			return false;
		}
	 
		if (board[i][j] == word.charAt(k)) {
			char temp = board[i][j];
			board[i][j] = '#';
	 
			if (k == word.length() - 1) {
				return true;
			} else if (dfs(board, word, i - 1, j, k + 1)
					|| dfs(board, word, i + 1, j, k + 1)
					|| dfs(board, word, i, j - 1, k + 1)
					|| dfs(board, word, i, j + 1, k + 1)) {
				board[i][j] = temp;
				return true;
			}
	 
		} else {
			return false;
		}
	 
		return false;
	}


	class TrieNode {
		String word;
		Map<Character, TrieNode> children;
		public TrieNode() {
			word = null;
			children = new HashMap<Character, TrieNode>();
		}


	}

	class Trie{
		private TrieNode root;

		public Trie(TrieNode root) {
			this.root = root;
		}

		public void insert(String word) {
			TrieNode cur = root;
			for (int i = 0; i < word.length(); i++) {
				cur.children.putIfAbsent(word.charAt(i), new TrieNode());
				cur = cur.children.get(word.charAt(i));
			}
			cur.word =  word;
		}
	}

	/**
	 * @param board: A list of lists of character
	 * @param words: A list of string
	 * @return: A list of string
	 */

	public int[] dx = {1, 0, -1, 0};
	public int[] dy = {0, 1, 0, -1};

	public List<String> wordSearchII(char[][] board, List<String> words) {
		// write your code here
		List<String> results = new ArrayList<>();
		if (board == null || words == null) {
			return results;
		}
		TrieNode root = new TrieNode();
		Trie tree = new Trie(root);
		for (String word : words) {
			tree.insert(word);
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				search(board, i, j, root, results);
			}
		}

		return results;
	}

	private void search(char[][] board, int x, int y, TrieNode root, List<String> results) {
		if (!root.children.containsKey(board[x][y])) {
			return;
		}

		TrieNode child = root.children.get(board[x][y]);

		if (child.word != null) {
			if (!results.contains(child.word)) {
				results.add(child.word);
			}
		}

		char tmp = board[x][y];
		board[x][y] = 0;

		for (int i = 0; i < 4; i++) {
			if (!isValid(board, x + dx[i], y + dy[i])) {
				continue;
			}
			search(board, x + dx[i], y + dy[i], child, results);
		}

		board[x][y] = tmp;
	}

	private boolean isValid(char[][] board, int x, int y) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
			return false;
		}

		return board[x][y] != 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/*
public class Solution {
    Set<String> result = new HashSet<String>(); 
 
    public List<String> findWords(char[][] board, String[] words) {
        //HashSet<String> result = new HashSet<String>();
 
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }
 
        int m=board.length;
        int n=board[0].length;
 
        boolean[][] visited = new boolean[m][n];
 
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
               dfs(board, visited, "", i, j, trie);
            }
        }
 
        return new ArrayList<String>(result);
    }
 
    public void dfs(char[][] board, boolean[][] visited, String str, int i, int j, Trie trie){
        int m=board.length;
        int n=board[0].length;
 
        if(i<0 || j<0||i>=m||j>=n){
            return;
        }
 
        if(visited[i][j])
            return;
 
        str = str + board[i][j];
 
        if(!trie.startsWith(str))
            return;
 
        if(trie.search(str)){
            result.add(str);
        }
 
        visited[i][j]=true;
        dfs(board, visited, str, i-1, j, trie);
        dfs(board, visited, str, i+1, j, trie);
        dfs(board, visited, str, i, j-1, trie);
        dfs(board, visited, str, i, j+1, trie);
        visited[i][j]=false;
    }
}

*/
