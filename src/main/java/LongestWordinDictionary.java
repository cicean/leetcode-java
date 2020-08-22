/**
 * 720. Longest Word in Dictionary
 * Easy
 *
 * 531
 *
 * 654
 *
 * Add to List
 *
 * Share
 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 *
 * If there is no answer, return the empty string.
 * Example 1:
 * Input:
 * words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation:
 * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 * Input:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation:
 * Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of words will be in the range [1, 1000].
 * The length of words[i] will be in the range [1, 30].
 * Accepted
 * 55,112
 * Submissions
 * 116,093
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * zestypanda
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * Longest Word in Dictionary through Deleting
 * Medium
 * Implement Magic Dictionary
 * Medium
 * For every word in the input list, we can check whether all prefixes of that word are in the input list by using a Set.
 */
public class LongestWordinDictionary {

    /**
     * Approach #1: Brute Force [Accepted]
     * Intuition
     *
     * For each word, check if all prefixes word[:k] are present. We can use a Set structure to check this quickly.
     *
     * Algorithm
     *
     * Whenever our found word would be superior, we check if all it's prefixes are present, then replace our answer.
     *
     * Alternatively, we could have sorted the words beforehand, so that we know the word we are considering would be the answer if all it's prefixes are present.
     *
     * Complexity Analysis
     *
     * Time complexity : O(\sum w_i^2)O(∑w
     * i
     * 2
     * ​
     *  ), where w_iw
     * i
     * ​
     *   is the length of words[i]. Checking whether all prefixes of words[i] are in the set is O(\sum w_i^2)O(∑w
     * i
     * 2
     * ​
     *  ).
     *
     * Space complexity : O(\sum w_i^2)O(∑w
     * i
     * 2
     * ​
     *  ) to create the substrings.
     */

    class Solution {
        public String longestWord(String[] words) {
            String ans = "";
            Set<String> wordset = new HashSet();
            for (String word: words) wordset.add(word);
            for (String word: words) {
                if (word.length() > ans.length() ||
                        word.length() == ans.length() && word.compareTo(ans) < 0) {
                    boolean good = true;
                    for (int k = 1; k < word.length(); ++k) {
                        if (!wordset.contains(word.substring(0, k))) {
                            good = false;
                            break;
                        }
                    }
                    if (good) ans = word;
                }
            }
            return ans;
        }
    }

    class Solution {
        public String longestWord(String[] words) {
            Set<String> wordset = new HashSet();
            for (String word: words) wordset.add(word);
            Arrays.sort(words, (a, b) -> a.length() == b.length()
                    ? a.compareTo(b) : b.length() - a.length());
            for (String word: words) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) return word;
            }

            return "";
        }
    }

    class Solution {
        public String longestWord(String[] words) {
            Arrays.sort(words);
            Set<String> built = new HashSet<String>();
            String res = "";
            for (String w : words) {
                if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                    res = w.length() > res.length() ? w : res;
                    built.add(w);
                }
            }
            return res;
        }
    }

     /** Approach #2: Trie + Depth-First Search [Accepted]
     * Intuition
     *
     * As prefixes of strings are involved, this is usually a natural fit for a trie (a prefix tree.)
     *
     * Algorithm
     *
     * Put every word in a trie, then depth-first-search from the start of the trie, only searching nodes that ended a word. Every node found (except the root, which is a special case) then represents a word with all it's prefixes present. We take the best such word.
     *
     * In Python, we showcase a method using defaultdict, while in Java, we stick to a more general object-oriented approach.
     *
     * Python
     *
     * Complexity Analysis
     *
     * Time Complexity: O(\sum w_i)O(∑w
     * i
     * ​
     *  ), where w_iw
     * i
     * ​
     *   is the length of words[i]. This is the complexity to build the trie and to search it.
     *
     * If we used a BFS instead of a DFS, and ordered the children in an array, we could drop the need to check whether the candidate word at each node is better than the answer, by forcing that the last node visited will be the best answer.
     *
     * Space Complexity: O(\sum w_i)O(∑w
     * i
     * ​
     *  ), the space used by our trie.
     */

     class Solution {
         public String longestWord(String[] words) {
             Trie trie = new Trie();
             int index = 0;
             for (String word: words) {
                 trie.insert(word, ++index); //indexed by 1
             }
             trie.words = words;
             return trie.dfs();
         }
     }
    class Node {
        char c;
        HashMap<Character, Node> children = new HashMap();
        int end;
        public Node(char c){
            this.c = c;
        }
    }

    class Trie {
        Node root;
        String[] words;
        public Trie() {
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node cur = root;
            for (char c: word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
            cur.end = index;
        }

        public String dfs() {
            String ans = "";
            Stack<Node> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                Node node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > ans.length() ||
                                word.length() == ans.length() && word.compareTo(ans) < 0) {
                            ans = word;
                        }
                    }
                    for (Node nei: node.children.values()) {
                        stack.push(nei);
                    }
                }
            }
            return ans;
        }
    }

    class Solution {
        private static final int R = 26;
        private TrieNode root;

        private static class TrieNode {
            private final TrieNode[] children;
            private String word;

            private TrieNode() {
                children = new TrieNode[R];
                word = null;
            }
        }

        private void insertWord(String word) {
            int len = word.length();
            TrieNode curr = root;
            char c;
            int idx;
            for (int i = 0; i < len; ++i) {
                c = word.charAt(i);
                idx = c - 'a';

                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];
            }

            curr.word = word;
        }

        private String findLongestWord(TrieNode root) {
            String rst = root.word == null ? "" : root.word;
            for (TrieNode child : root.children) {
                if (child == null || child.word == null) continue;
                String s = findLongestWord(child);
                if (s.length() > rst.length()) rst = s;
            }
            return rst;
        }


        public String longestWord(String[] words) {
            // initialize a new trie
            root = new TrieNode();

            // construct the trie based on input dictionary words
            for (String word : words) {
                insertWord(word);
            }

            // find the longest dictionary word in this trie according to given rules
            return findLongestWord(root);
        }
    }
}
