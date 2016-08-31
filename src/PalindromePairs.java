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
 ���⣺����һ�������б�ÿ�����ʾ�Ψһ���������е�i,j(i !=j) ʹ��words[i] + words[j] �ǻ��Ĵ���
 */
public class PalindromePairs {
    /**
     * ˼·�������ķ�����ֱ�Ӷ�ö�����п��ܵ������Ȼ�󿴿��Ƿ���Ĵ���

     �Ľ��ķ�������hash�����Ƚ����еĵ�����ΪKey����Ӧ��valueΪ�����±ꡣ

     ���Ŷ���ÿ������x��ö��i����[0,len(word) ���������Ϊ�������ߣ�ǰ׺�ͺ�׺ ����

     ���ں�׺suffix�����ǿ��԰�������r_suffix�������ֵ����Ƿ�����������y�������ڣ����ж� r_suffix + x �Ƿ�Ϊ���Ĵ�����׺������ԭ����ǰ�����Ƿ���ģ�ǰ׺��Ӻ��棬���Կ���214�⣩

     ͬ������ǰ׺prefix������Ϊr_prefix���ֵ��д��ڵĻ������ж� x + r_prefix�Ƿ�Ϊ���Ĵ���

     ����Ҫע�ⵥ��Ϊ�����������
     ���磬���ֵ�ʱ�򣬶���ǰ׺prefix������Ҫ�������ԭ���ʺ���ģ��ⲿ��һ������ͬ�ģ�û��Ҫ�ظ��Ƚϣ�����ֻ��Ҫ������׺�Ƿ���ġ� ��׺��ʱ��Ҳһ����ֻ�迴��ǰ׺�Ƿ����

     ���⣬ö��i�ķ�Χ��[0,len(word) ) ��Ϊ [0,len(word)]�������Ͱ� ������������ǵ���
     */

    /**
     * HashMap��HashTable������

     HashTable��synchronized�����Զ���non-threaded applications��HashMapЧ�ʸ��ߣ�
     HashTable������null��Ϊ��ֵ����HashMap����һ��null�������޸�nullֵ��
     HashMap��һ��subclass����LinkedHashMap�����ڲ�ѯ��Ԥ��ĵ���˳��

     ΪʲôTrie��HashMapЧ�ʸ���

     Trie������O(L)��LΪword.length����ʱ�临�Ӷ��½��в���Ͳ�ѯ������
     HashMap��HashTableֻ���ҵ���ȫƥ��Ĵ��飬��Trie�����ҵ�����ͬǰ׺�ġ��в�ͬ�ַ��ġ���ȱʧ�ַ��Ĵ��顣

     �������Ȼѡ��HashMap�Ļ�

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
     public static <T> List<T> asList(T�� a)
     Method Argument

     Data Type	Parameter	Description
     T	a	T �C the class of the objects in the array
     a �C the array by which the list will be backed
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
