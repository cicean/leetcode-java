import java.util.*;

/**
 * Created by cicean on 10/16/2016.
 * 425. Word Squares   QuestionEditorial Solution  My Submissions
 Total Accepted: 294
 Total Submissions: 853
 Difficulty: Hard
 Contributors: Admin
 Given a set of words (without duplicates), find all word squares you can build from them.

 A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 �� k < max(numRows, numColumns).

 For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

 b a l l
 a r e a
 l e a d
 l a d y
 Note:
 There are at least 1 and at most 1000 words.
 All words will have the exact same length.
 Word length is at least 1 and at most 5.
 Each word contains only lowercase English alphabet a-z.
 Example 1:

 Input:
 ["area","lead","wall","lady","ball"]

 Output:
 [
 [ "wall",
 "area",
 "lead",
 "lady"
 ],
 [ "ball",
 "area",
 "lead",
 "lady"
 ]
 ]

 Explanation:
 The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 Example 2:

 Input:
 ["abat","baba","atan","atal"]

 Output:
 [
 [ "baba",
 "abat",
 "baba",
 "atan"
 ],
 [ "baba",
 "abat",
 "baba",
 "atal"
 ]
 ]

 Explanation:
 The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 Hide Company Tags Google
 Hide Tags Backtracking Trie
 Hide Similar Problems (E) Valid Word Square

 */
public class WordSquares {
    class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String w : words) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null)
                        cur.children[idx] = new TrieNode();
                    cur.children[idx].startWith.add(w);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    return ans;

                cur = cur.children[idx];
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0)
            return ans;
        int len = words[0].length();
        Trie trie = new Trie(words);
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(len, trie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }

    private void search(int len, Trie tr, List<List<String>> ans,
                        List<String> ansBuilder) {
        if (ansBuilder.size() == len) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : ansBuilder)
            prefixBuilder.append(s.charAt(idx));
        List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(len, tr, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }

    /**
     *
     */
    public class Solution {
        class Node{
            Node[] nodes;
            String word;
            Node(){
                this.nodes = new Node[26];
                this.word = null;
            }
        }
        void add(Node root, String word){
            Node node = root;
            for (char c : word.toCharArray() ) {
                int idx = c-'a';
                if (node.nodes[idx] == null) node.nodes[idx] = new Node();
                node = node.nodes[idx];
            }
            node.word = word;
        }
        void helper(int row, int col, int len, Node[] rows, List<List<String>> ret) {
            if ( (col == row) && (row == len) ) { // last char
                List<String> res = new ArrayList<String>();
                for (int i=0; i<len; i++) {
                    res.add(new String(rows[i].word) );
                }
                ret.add( res );
            } else { // from left to right and then go down to the next row
                if ( col < len  ) { // left to right first
                    Node pre_row = rows[row];
                    Node pre_col = rows[col];
                    for (int i=0; i<26; i++) { // find all the possible next char
                        if ( (rows[row].nodes[i] != null) && (rows[col].nodes[i] != null) ) {
                            rows[row] = rows[row].nodes[i];
                            if (col != row) rows[col] = rows[col].nodes[i];
                            helper(row, col+1, len, rows, ret);
                            rows[row] = pre_row;
                            if (col != row) rows[col] = pre_col;
                        }
                    }
                } else { // reach the end of column, go to the next row
                    helper(row+1, row+1, len, rows, ret);
                }
            }
        }
        public List<List<String>> wordSquares(String[] words) {
            List<List<String>> ret = new ArrayList();
            if (words==null || words.length==0) return ret;
            Node root = new Node();
            int len = words[0].length();
            for (String word: words) add(root, word);
            Node[] rows = new Node[len];
            for (int i=0; i<len; i++) rows[i]=root;
            helper(0, 0, len, rows, ret);
            return ret;
        }
    }

    /**
     *
     */

    class Solution {
        Node root = new Node();
        public List<List<String>> wordSquares(String[] words) {
            List<List<String>> ret = new ArrayList<List<String>>();
            if(words == null || words.length == 0){
                return ret;
            }

            for(String s: words){
                add(s, root);
            }

            Node[] rows = new Node[words[0].length()];
            Arrays.fill(rows, root);
            findAllSquares(0, 0, rows, ret);
            return ret;
        }

        public void add(String s, Node root){
            for(int i=0; i<s.length(); i++){
                int ind = s.charAt(i) - 'a';
                if(root.kids[ind] == null){
                    root.kids[ind] = new Node();
                }
                root = root.kids[ind];
            }
            root.val = s;
        }

        private static void findAllSquares(int row, int col, Node[] rows, List<List<String>> res){
            if(row == rows.length){
                List<String> temp = new ArrayList<>(rows.length);
                for(int i = 0; i < rows.length; i++) temp.add(rows[i].val);
                res.add(temp);
            }else if(col < rows.length){
                Node currow = rows[row];
                Node curcol = rows[col];
                for(int i = 0; i < 26; i++){
                    if(currow.kids[i] != null && curcol.kids[i] != null){
                        rows[row] = currow.kids[i];
                        rows[col] = curcol.kids[i];
                        findAllSquares(row, col + 1, rows, res);
                    }
                }
                rows[row] = currow;
                rows[col] = curcol;
            }else{
                findAllSquares(row + 1, row + 1, rows, res);
            }
        }
    }

    class Node{
        Node[] kids = new Node[26];
        String val = null;
    }
}
