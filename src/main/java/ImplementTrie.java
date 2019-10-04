import java.util.*;

/**
 * 208	Implement Trie (Prefix Tree)	24.9%	Medium
 *
 * 0.Problem:
 * Implement strStr().
 * Returns a pointer to the first occurrence of needle in haystack, 
 * or null if needle is not part of haystack.
 * 
 * 题意：实现字典树的插入，查找，前缀方法
 * 本题考查字典树数据结构的基础知识。
 * Trie使用孩子表示法存储，TrieNode为字典树的节点，包含属性childs和isWord。
 * 其中childs为dict，存储当前节点的后代节点；isWord为布尔值，表示当前节点是否存储了一个单词。
 * 
 * 1.Refer.:
 * 两重循环
 * 1.1 You can use this diagram to walk though the Java solution.
 */

/**
 * 复杂度
时间 插入和查询都是O(K) K是词的长度 空间 O(NK) N是字典里词的个数

思路
前缀树的具体讲解请戳这篇博客。这里我们实现树节点时使用了哈希表来映射字母和子节点的关系。
insert()：对于插入操作，我们遍历字符串同时，根据上一个节点的哈希表来找到下一个节点，直到哈希表中没有相应的字母，我们就新建一个节点。然后从这个新建节点开始，用同样的方法把剩余的字母添加完。记住最后一个字母要添加叶子节点的标记，表明这个词到此已经完整了。
search()：对于搜索操作，我们也是遍历字符串，然后根据每个节点的哈希表找到路径，最后返回该字符串最后一个字母所在节点。如果中途有找不到路径的情况就直接返回null，如果找到了最后的节点，如果它也是叶子结点的话，就说明找到了。
startWith()：使用和search()，一样的方法，只是我们返回的节点不用判断是否是叶子节点。只要找到就行。
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
 * 后续 Follow Up
Q：给定一个标准前缀树，请写一段程序将其压缩。
A：压缩前缀树其实就是将所有只有一个子节点的节点合并成一个，以减少没有意义的类似链表式的链接。

首先我们先将TrieNode稍微改一下。让它能存字符串而不只是字母。
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
    public TrieNode() { //默认根结点  
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
    public boolean isWordEnd; //该字符是一个结尾字符  
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
 * 首先，我们应该了解字典树的性质和结构，就会很容易实现要求的三个相似的功能：插入，查找，前缀查找。
既然叫做字典树，它一定具有顺序存放26个字母的性质。另外，为了实现和区别全词查找和前缀查找，应该有一个标记。所以，在字典树的class里面，添加ch，exist和children三个参数。

插入操作：建立结点pre，复制root。在pre的children[index]存放插入词汇word的第i个字符（用数字0到25表示a~z的26个字母，记作index），依次类推。若当前的children不存在，则建立大小为26的children结点数组。若children结点数组里的第index个TrieNode为空，则放入新的值为word.charAt(i)的TrieNode。然后pre前进到当前结点的children，pre.children[index]，继续循环操作word的下一个字符。直到放入word的最后一个字符以后，修改pre.exist值为true，说明pre之前的分支完整放入了word。

查找操作：同插入一样，复制root到结点pre，然后遍历查找word的每一个字符word.charAt(i)，若循环里某个pre.children[index]不存在，或者word的最后一个字符的exist标记为false，则返回false。否则，循环结束，返回true。

前缀查找操作：唯一和查找操作不同的地方，是不要求word的最后一个字符的exist标记为true。只要遍历完String prefix，就返回true。
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
