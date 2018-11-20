package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 *
 */

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode('0');
    }

    public void insert(String word) {
        if(word == null || word.length() == 0) {
            return;
        }
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode(ch);
            }
            node = node.children[ch - 'a'];
        }
        node.isWord = true;
    }
}

class TrieNode {
    char value;
    boolean isWord;
    TrieNode[] children;

    TrieNode(char v) {
        value = v;
        isWord = false;
        children = new TrieNode[26];
    }
}

public class Solution {
    /**
     * @param board a list of lists of character
     * @param words a list of string
     * @return an integer
     */
    public int boggleGame(char[][] board, String[] words) {
        // Write your code here
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        List<String> result = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        List<String> path = new ArrayList<>();
        findWords(result, board, visited, path, 0, 0, trie.root);
        return result.size();
    }

    public void findWords(List<String> result, char[][] board, boolean[][] visited, List<String> words, int x, int y, TrieNode root) {

        int m = board.length;
        int n = board[0].length;
        for (int i = x; i < m; i++) {
            for (int j = y; j < n; j++) {
                List<List<Integer>> nextWordIndexes = new ArrayList<>();
                List<Integer> path = new ArrayList<>();
                getNextWords(nextWordIndexes, board, visited, path, i, j, root);
                for (List<Integer> indexes : nextWordIndexes) {
                    String word = "";
                    for (int index : indexes) {
                        int row = index / n;
                        int col = index % n;
                        visited[row][col] = true;
                        word += board[row][col];
                    }

                    words.add(word);
                    if (words.size() > result.size()) {
                        result.clear();
                        result.addAll(words);
                    }
                    findWords(result, board, visited, words, i, j, root);
                    for (int index : indexes) {
                        int row = index / n;
                        int col = index % n;
                        visited[row][col] = false;
                    }
                    words.remove(words.size() - 1);
                }
            }
            y = 0;
        }
    }

    int []dx = {0, 1, 0, -1};
    int []dy = {1, 0, -1, 0};
    private void getNextWords(List<List<Integer>> words, char[][] board,
                              boolean[][] visited, List<Integer> path, int i, int j, TrieNode root) {
        if(i < 0 | i >= board.length || j < 0 || j >= board[0].length
                || visited[i][j] == true || root.children[board[i][j] - 'a'] == null) {
            return;
        }

        root = root.children[board[i][j] - 'a'];
        if(root.isWord) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(i * board[0].length + j);
            words.add(newPath);
            return;
        }

        visited[i][j] = true;
        path.add(i * board[0].length + j);
        for (int k = 0; k < 4; k ++) {
            getNextWords(words, board, visited, path, i + dx[k], j + dy[k], root);
        }
        path.remove(path.size() - 1);
        visited[i][j] = false;
    }
}

public class BoggleGame {

    public class Solution {

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
    }

}
