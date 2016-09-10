import java.util.HashMap;
import java.util.Map;

/*
 * 211. Add and Search Word - Data structure design  QuestionEditorial Solution  My Submissions
Total Accepted: 32317
Total Submissions: 160136
Difficulty: Medium
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
Hide Company Tags Facebook
Hide Tags Backtracking Trie Design
Hide Similar Problems (M) Implement Trie (Prefix Tree)

Analysis

This problem is similar with Implement Trie. The solution 1 below uses the same definition of a trie node. 
To handle the "." case for this problem, we need to search all possible paths, instead of one path.
 * Trie + dfs 
 */

/*class TrieNode{
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
 
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
}*/



public class AddandSearchWord {
	
	public class WordDictionary {
	    class TrieNode {
	        char val;
	        boolean isWord;
	        TrieNode[] children;
	        TrieNode() {
	            children = new TrieNode[26];
	        }
	        TrieNode(char ch) {
	            children = new TrieNode[26];
	            val = ch;
	        }
	    }
	    TrieNode root = new TrieNode();
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode node = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            if (node.children[ch-'a'] == null) node.children[ch-'a'] = new TrieNode(ch);
	            node = node.children[ch-'a'];
	        }
	        node.isWord = true;
	    }
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        return helper(word, 0, root);
	    }
	    public boolean helper(String word, int pos, TrieNode node) {
	        if (pos == word.length()) return node.isWord;
	        char ch = word.charAt(pos);
	        if (ch != '.') return node.children[ch-'a'] != null && helper(word, pos+1, node.children[ch-'a']);
	        else {
	            for (int i = 0; i < 26; i++) {
	                if (node.children[i] != null && helper(word, pos+1, node.children[i])) return true;
	            }
	            return false;
	        }
	    }
	}

	

	 private TrieNode root;
	 
	    public void WordDictionary(){
	        root = new TrieNode();
	    }
	 
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        HashMap<Character, TrieNode> children = root.children;
	 
	        for(int i=0; i<word.length(); i++){
	            char c = word.charAt(i);
	 
	            TrieNode t = null;
	            if(children.containsKey(c)){
	                t = children.get(c);
	            }else{
	                t = new TrieNode(c);
	                children.put(c,t);
	            }
	 
	            children = t.children;
	 
	            if(i == word.length()-1){
	                t.isLeaf = true;
	            }
	        }
	    }
	 
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	       return dfsSearch(root.children, word, 0);
	 
	    }
	 
	     public boolean dfsSearch(HashMap<Character, TrieNode> children, String word, int start) {
	        if(start == word.length()){
	            if(children.size()==0) 
	                return true; 
	            else
	                return false;
	        }
	 
	        char c = word.charAt(start);    
	 
	        if(children.containsKey(c)){
	            if(start == word.length()-1 && children.get(c).isLeaf){
	                return true;
	            }
	 
	            return dfsSearch(children.get(c).children, word, start+1);
	        }else if(c == '.'){
	            boolean result = false;
	            for(Map.Entry<Character, TrieNode> child: children.entrySet()){
	                if(start == word.length()-1 && child.getValue().isLeaf){
	                    return true;
	                } 
	 
	                //if any path is true, set result to be true; 
	                if(dfsSearch(child.getValue().children, word, start+1)){
	                    result = true;
	                }
	            }
	 
	            return result;
	        }else{
	            return false;
	        }
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
