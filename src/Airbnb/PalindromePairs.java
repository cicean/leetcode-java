package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 */

class TrieNode {
    TrieNode[] next;
    int index;
    List<Integer> list;

    TrieNode() {
        next = new TrieNode[26];
        index = -1;
        list = new ArrayList<>();
    }
}

public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
        for (int i = 0; i < words.length; i++) search(words, i, root, res);

        return res;
    }

    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (root.next[j] == null) root.next[j] = new TrieNode();
            if (isPalindrome(word, 0, i)) root.list.add(index);
            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }

}

//Search twice in the trie, the second time reverse the string.
// Keep in mind that: The info in the trie is always equal or shorter than the matching string.
// We deal with the case that info in the trie is longer than matching str in the reverse pass.

class Solution_JiuZhang {

    class TrieNode{
        TrieNode[] sons = new TrieNode[26];
        String word;
        int idx;
    }

    TrieNode root;

    void insert(String str, int idx){
        TrieNode d = root;
        for (char c : str.toCharArray()){
            if (d.sons[c - 'a'] == null){
                d.sons[c - 'a'] = new TrieNode();
            }
            d = d.sons[c - 'a'];
        }
        d.word = str;
        d.idx = idx;
    }

    boolean checkPalin(char[] A, int lo, int hi){
        if (reverse && lo > hi){
            return false;
        }
        while (lo < hi){
            if (A[lo++] != A[hi--]){
                return false;
            }
        }
        return true;
    }

    List<List<Integer>> find(String str, int idx){
        List<List<Integer>> ans = new ArrayList<>();
        char[] A = str.toCharArray();
        TrieNode d = root;
        if (d.word != null && d.idx != idx && checkPalin(A, 0, A.length - 1)){
            // deal with empty string as input
            ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
        }
        for (int i = A.length - 1; i >= 0; i--){
            d = d.sons[A[i] - 'a'];
            if (d == null){
                break;
            }
            if (d.word != null && d.idx != idx && checkPalin(A, 0, i - 1)){
                // found a word, how check whether the remainder of str is palin
                ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
            }
        }
        return ans;
    }

    /**
     * @param words: a list of unique words
     * @return: all pairs of distinct indices
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        // Write your code here
        List<List<Integer>> ans = helper(words);
        for (int i = 0; i < words.length; i++){
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }

        reverse = true;

        ans.addAll(helper(words));

        return ans;
    }

    boolean reverse = false;

    List<List<Integer>> helper(String[] words){
        List<List<Integer>> ans = new ArrayList<>();
        root = new TrieNode();
        for (int i = 0; i < words.length; i++){
            insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++){
            ans.addAll(find(words[i], i));
        }
        return ans;
    }
}
