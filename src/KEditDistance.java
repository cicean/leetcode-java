import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 */

class TrieNode {
    // Initialize your data structure here.
    public TrieNode[] children;
    public boolean hasWord;
    public String str;

    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
        for (int i = 0; i < 26; ++i)
            children[i] = null;
        hasWord = false;
    }

    // Adds a word into the data structure.
    public void addWord(TrieNode root, String word) {
        TrieNode now = root;
        for(int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (now.children[c - 'a'] == null) {
                now.children[c - 'a'] = new TrieNode();
            }
            now = now.children[c - 'a'];
        }
        now.str = word;
        now.hasWord = true;
    }
}

public class KEditDistance {



    public List<String> kDistance(String[] words, String target, int k) {
        // Write your code here
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++)
            TrieNode.addWord(root, words[i]);

        List<String> result = new ArrayList<String>();

        int n = target.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            dp[i] = i;

        find(root, result, k, target, dp);
        return result;
    }

    public void find(TrieNode node, List<String> result, int k, String target, int[] dp) {
        int n = target.length();
        // dp[i] ��ʾ��Trie��root�ڵ��ߵ���ǰnode�ڵ㣬�γɵ�Prefix
        // �� target��ǰi���ַ�����С�༭����
        if (node.hasWord && dp[n] <= k) {
            result.add(node.str);
        }
        int[] next = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            next[i] = 0;

        for (int i = 0; i < 26; ++i) {
            if (node.children[i] != null) {
                next[0] = dp[0] + 1;
                for (int j = 1; j <= n; j++) {
                    if (target.charAt(j - 1) - 'a' == i) {
                        next[j] = Math.min(dp[j - 1], Math.min(next[j - 1] + 1, dp[j] + 1));
                    } else {
                        next[j] = Math.min(dp[j - 1] + 1, Math.min(next[j - 1] + 1, dp[j] + 1));
                    }
                }
                find(node.children[i], result, k, target, next);
            }
        }

    }

}
