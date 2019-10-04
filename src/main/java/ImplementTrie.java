import java.util.*;

/**
 * 208	Implement Trie (Prefix Tree)	24.9%	Medium
 *
 * 0.Problem:
 * Implement strStr().
 * Returns a pointer to the first occurrence of needle in haystack, 
 * or null if needle is not part of haystack.
 * 
 * ���⣺ʵ���ֵ����Ĳ��룬���ң�ǰ׺����
 * ���⿼���ֵ������ݽṹ�Ļ���֪ʶ��
 * Trieʹ�ú��ӱ�ʾ���洢��TrieNodeΪ�ֵ����Ľڵ㣬��������childs��isWord��
 * ����childsΪdict���洢��ǰ�ڵ�ĺ���ڵ㣻isWordΪ����ֵ����ʾ��ǰ�ڵ��Ƿ�洢��һ�����ʡ�
 * 
 * 1.Refer.:
 * ����ѭ��
 * 1.1 You can use this diagram to walk though the Java solution.
 */

/**
 * ���Ӷ�
ʱ�� ����Ͳ�ѯ����O(K) K�Ǵʵĳ��� �ռ� O(NK) N���ֵ���ʵĸ���

˼·
ǰ׺���ľ��彲�������ƪ���͡���������ʵ�����ڵ�ʱʹ���˹�ϣ����ӳ����ĸ���ӽڵ�Ĺ�ϵ��
insert()�����ڲ�����������Ǳ����ַ���ͬʱ��������һ���ڵ�Ĺ�ϣ�����ҵ���һ���ڵ㣬ֱ����ϣ����û����Ӧ����ĸ�����Ǿ��½�һ���ڵ㡣Ȼ�������½��ڵ㿪ʼ����ͬ���ķ�����ʣ�����ĸ����ꡣ��ס���һ����ĸҪ���Ҷ�ӽڵ�ı�ǣ���������ʵ����Ѿ������ˡ�
search()��������������������Ҳ�Ǳ����ַ�����Ȼ�����ÿ���ڵ�Ĺ�ϣ���ҵ�·������󷵻ظ��ַ������һ����ĸ���ڽڵ㡣�����;���Ҳ���·���������ֱ�ӷ���null������ҵ������Ľڵ㣬�����Ҳ��Ҷ�ӽ��Ļ�����˵���ҵ��ˡ�
startWith()��ʹ�ú�search()��һ���ķ�����ֻ�����Ƿ��صĽڵ㲻���ж��Ƿ���Ҷ�ӽڵ㡣ֻҪ�ҵ����С�
 * @author cicean
 *
 */

class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
 
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
}

public class ImplementTrie {

	private TrieNode root;
	 
    public void Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
 
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
 
            TrieNode t;
            if(children.containsKey(c)){
                    t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
 
            children = t.children;
 
            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;    
        }
    }
 
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
 
        if(t != null && t.isLeaf) 
            return true;
        else
            return false;
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null) 
            return false;
        else
            return true;
    }
 
    public TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
 
        return t;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * ���� Follow Up
Q������һ����׼ǰ׺������дһ�γ�����ѹ����
A��ѹ��ǰ׺����ʵ���ǽ�����ֻ��һ���ӽڵ�Ľڵ�ϲ���һ�����Լ���û���������������ʽ�����ӡ�

���������Ƚ�TrieNode��΢��һ�¡������ܴ��ַ�������ֻ����ĸ��
 */

class TrieNode {
    // Initialize your data structure here.
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf = false;
    String str;
    public TrieNode(){}
    public TrieNode(char c) {
        this.str = String.valueOf(c);
    }
}


/*
 * 
 * class TrieNode {  
    // Initialize your data structure here.  
    public TrieNode() { //Ĭ�ϸ����  
        value = '\0';  
        next = new TrieNode[26];  
        isWordEnd = false;  
    }  
    public TrieNode(char c){  
        value = c;  
        next = new TrieNode[26];  
        isWordEnd = false;  
    }  
    public TrieNode(char c, boolean endFlg){  
        value = c;  
        next = new TrieNode[26];  
        isWordEnd = endFlg;  
    }  
      
    public TrieNode[] next;  
    public char value;  
    public boolean isWordEnd; //���ַ���һ����β�ַ�  
}  
  
public class Trie {  
    private TrieNode root;  
  
    public Trie() {  
        root = new TrieNode();  
    }  
  
    // Inserts a word into the trie.  
    public void insert(String word) {  
        TrieNode tmpRoot = root;  
        for(int i = 0; i < word.length(); i++){  
            char c = word.charAt(i);  
            if(tmpRoot.next[c - 'a'] == null){  
                tmpRoot.next[c - 'a'] = new TrieNode(c);    
            }  
            tmpRoot = tmpRoot.next[c - 'a'];  
        }  
        tmpRoot.isWordEnd = true;  
    }  
  
    // Returns if the word is in the trie.  
    public boolean search(String word) {  
        int i = 0;  
        TrieNode tmpRoot = root;  
        for(; i < word.length(); i++){  
            char c = word.charAt(i);  
            tmpRoot = tmpRoot.next[c - 'a'];  
            if(tmpRoot == null) break;  
        }  
        if(i == word.length() && tmpRoot.isWordEnd) return true;  
        return false;  
    }  
  
    // Returns if there is any word in the trie  
    // that starts with the given prefix.  
    public boolean startsWith(String prefix) {  
        int i = 0;  
        TrieNode tmpRoot = root;  
        for(; i < prefix.length(); i++){  
            char c = prefix.charAt(i);  
              
            tmpRoot = tmpRoot.next[c - 'a'];  
            if(tmpRoot == null) break;  
        }  
        if(i == prefix.length()) return true;  
        return false;        
    }  
}  
  
// Your Trie object will be instantiated and called as such:  
// Trie trie = new Trie();  
// trie.insert("somestring");  
// trie.search("key");  
 * 
 */

/**
 * ���ȣ�����Ӧ���˽��ֵ��������ʺͽṹ���ͻ������ʵ��Ҫ����������ƵĹ��ܣ����룬���ң�ǰ׺���ҡ�
��Ȼ�����ֵ�������һ������˳����26����ĸ�����ʡ����⣬Ϊ��ʵ�ֺ�����ȫ�ʲ��Һ�ǰ׺���ң�Ӧ����һ����ǡ����ԣ����ֵ�����class���棬���ch��exist��children����������

����������������pre������root����pre��children[index]��Ų���ʻ�word�ĵ�i���ַ���������0��25��ʾa~z��26����ĸ������index�����������ơ�����ǰ��children�����ڣ�������СΪ26��children������顣��children���������ĵ�index��TrieNodeΪ�գ�������µ�ֵΪword.charAt(i)��TrieNode��Ȼ��preǰ������ǰ����children��pre.children[index]������ѭ������word����һ���ַ���ֱ������word�����һ���ַ��Ժ��޸�pre.existֵΪtrue��˵��pre֮ǰ�ķ�֧����������word��

���Ҳ�����ͬ����һ��������root�����pre��Ȼ���������word��ÿһ���ַ�word.charAt(i)����ѭ����ĳ��pre.children[index]�����ڣ�����word�����һ���ַ���exist���Ϊfalse���򷵻�false������ѭ������������true��

ǰ׺���Ҳ�����Ψһ�Ͳ��Ҳ�����ͬ�ĵط����ǲ�Ҫ��word�����һ���ַ���exist���Ϊtrue��ֻҪ������String prefix���ͷ���true��
 * @author cicean
 *
 */

class TrieNode {
    // Initialize your data structure here.
    boolean exist;
    char ch;
    TrieNode[] children;
    public TrieNode() {
        
    }
    public TrieNode(char ch) {
        this.ch = ch;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode pre = root;
        for (int i = 0; i < word.length(); i++) {
            if (pre.children == null) pre.children = new TrieNode[26];
            int index = word.charAt(i) - 'a';
            if (pre.children[index] == null) {
                pre.children[index] = new TrieNode(word.charAt(i));
            }
            pre = pre.children[index];
            if (i == word.length()-1) pre.exist = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        TrieNode pre = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (pre.children == null || pre.children[index] == null) return false;
            if (i == word.length()-1 && pre.children[index].exist == false) return false;
            pre = pre.children[index];
        }
        return true;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return false;
        TrieNode pre = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (pre.children == null || pre.children[index] == null) return false;
            pre = pre.children[index];
        }
        return true;
    }
}

/**
 * Date 04/25/2016
 * @author Tushar Roy
 *
 * Insert/delete/search into trie data structure
 *
 * Reference
 * https://en.wikipedia.org/wiki/Trie
 */
public class Trie {

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Iterative implementation of insert into trie
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        //mark the current nodes endOfWord as true
        current.endOfWord = true;
    }

    /**
     * Recursive implementation of insert into trie
     */
    public void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }


    private void insertRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //if end of word is reached then mark endOfWord as true on current node
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        //if node does not exists in map then create one and put it into map
        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertRecursive(node, word, index + 1);
    }

    /**
     * Iterative implementation of search into trie.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            //if node does not exist for given char then return false
            if (node == null) {
                return false;
            }
            current = node;
        }
        //return true of current's endOfWord is true else return false.
        return current.endOfWord;
    }

    /**
     * Recursive implementation of search into trie.
     */
    public boolean searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }
    private boolean searchRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //return true of current's endOfWord is true else return false.
            return current.endOfWord;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        //if node does not exist for given char then return false
        if (node == null) {
            return false;
        }
        return searchRecursive(node, word, index + 1);
    }

    /**
     * Delete word from trie.
     */
    public void delete(String word) {
        delete(root, word, 0);
    }

    /**
     * Returns true if parent should delete the mapping
     */
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //when end of word is reached only delete if currrent.endOfWord is true.
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        //if true is returned then delete the mapping of character and trienode reference from map.
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            //return true if no mappings are left in the map.
            return current.children.size() == 0;
        }
        return false;
    }
}
