/**
 * Created by cicean on 8/30/2016.
 * 336. Palindrome Pairs  QuestionEditorial Solution  My Submissions
 Total Accepted: 11178 Total Submissions: 50396 Difficulty: Hard
 Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]
 Example 2:
 Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google Airbnb
 Hide Tags Hash Table String Trie
 Hide Similar Problems (M) Longest Palindromic Substring (H) Shortest Palindrome
 题意：给定一个单词列表（每个单词均唯一），求所有的i,j(i !=j) 使得words[i] + words[j] 是回文串。
 */
public class PalindromePairs {
    /**
     * 思路：暴力的方法是直接对枚举所有可能的情况，然后看看是否回文串。

     改进的方法是用hash，首先将所有的单词作为Key，相应的value为它的下标。

     接着对于每个单词x，枚举i（从[0,len(word) ），将其分为左右两边（前缀和后缀 ）。

     对于后缀suffix，我们可以把它逆序r_suffix，看看字典中是否存在这个单词y，若存在，则判断 r_suffix + x 是否为回文串（后缀逆序后加原单词前看看是否回文，前缀则加后面，可以看看214题）

     同样对于前缀prefix，逆序为r_prefix，字典中存在的话，则判断 x + r_prefix是否为回文串。

     此外要注意单词为””的情况。
     比如，划分的时候，对于前缀prefix，它是要逆序加在原单词后面的，这部分一定是相同的，没必要重复比较，我们只需要看看后缀是否回文。 后缀的时候也一样，只需看看前缀是否回文

     此外，枚举i的范围从[0,len(word) ) 变为 [0,len(word)]，这样就把 “”的情况考虑到了
     */

    /**
     * HashMap和HashTable的区别：

     HashTable是synchronized，所以对于non-threaded applications，HashMap效率更高；
     HashTable不允许null作为键值，而HashMap允许一个null键和无限个null值；
     HashMap有一个subclass，叫LinkedHashMap，便于查询可预测的迭代顺序。

     为什么Trie比HashMap效率更高

     Trie可以在O(L)（L为word.length）的时间复杂度下进行插入和查询操作；
     HashMap和HashTable只能找到完全匹配的词组，而Trie可以找到有相同前缀的、有不同字符的、有缺失字符的词组。

     这道题依然选择HashMap的话

     1. map.getOrDefault(str, i)

     Method Syntax

     public V getOrDefault(Object key,V defaultValue)
     Method Argument

     Data Type	Parameter	Description
     Object Key	key	the key whose associated value is to be returned
     V	defaultValue	the default mapping of the key
     Method Returns

     The getOrDefault() method returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.

     2. Arrays.asList(i, j)

     Method Syntax

     @SafeVarargs
     public static <T> List<T> asList(T… a)
     Method Argument

     Data Type	Parameter	Description
     T	a	T – the class of the objects in the array
     a – the array by which the list will be backed
     Method Returns

     The asList() method returns a list view of the specified array.
     */

    //Using Trie
    public class Solution {
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
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();
            TrieNode root = new TrieNode();
            for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
            for (int i = 0; i < words.length; i++) search(words, i, root, res);
            return res;
        }
        private void addWord(TrieNode root, String word, int index) {
            for (int i = word.length() - 1; i >= 0; i--) {
                if (root.next[word.charAt(i) - 'a'] == null) root.next[word.charAt(i) - 'a'] = new TrieNode();
                if (isPalindrome(word, 0, i)) root.list.add(index);
                root = root.next[word.charAt(i) - 'a'];
            }
            root.list.add(index);
            root.index = index;
        }
        private void search(String[] words, int i, TrieNode root, List<List<Integer>> list) {
            for (int j = 0; j < words[i].length(); j++) {
                if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) list.add(Arrays.asList(i, root.index));
                root = root.next[words[i].charAt(j) - 'a'];
                if (root == null) return;
            }
            for (int j : root.list) {
                if (i == j) continue;
                list.add(Arrays.asList(i, j));
            }
        }
        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--)) return false;
            }
            return true;
        }
    }

    //HashMap method
    public class Solution2 {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> ret = new ArrayList<>();
            if (words == null || words.length < 2) return ret;
            Map<String, Integer> map = new HashMap<>();
            for (int i=0; i<words.length; i++) map.put(words[i], i);
            for (int i=0; i<words.length; i++) {
                for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                    String str1 = words[i].substring(0, j);
                    String str2 = words[i].substring(j);
                    if (isPalindrome(str1)) {
                        String str2rvs = new StringBuilder(str2).reverse().toString();
                        if (map.getOrDefault(str2rvs, i) != i) ret.add(Arrays.asList(map.get(str2rvs), i));
                    }
                    if (isPalindrome(str2) && str2.length() != 0) {
                        String str1rvs = new StringBuilder(str1).reverse().toString();
                        // check "str.length() != 0" to avoid duplicates
                        if (map.getOrDefault(str1rvs, i) != i) ret.add(Arrays.asList(i, map.get(str1rvs)));
                    }
                }
            }
            return ret;
        }
        private boolean isPalindrome(String str) {
            for (int l = 0, r = str.length() - 1; l <= r; l ++, r --)
                if (str.charAt(l) != str.charAt(r)) return false;
            return true;
        }
    }


}
