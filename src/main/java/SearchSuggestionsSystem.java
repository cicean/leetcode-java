import java.util.*;

/**
 * Search Suggestions System
 * Medium
 *
 * 208
 *
 * 63
 *
 * Add to List
 *
 * Share
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 *
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 *
 *
 * Constraints:
 *
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 * Accepted
 * 17,895
 * Submissions
 * 29,915
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 27
 * Brute force is a good choice because length of the string is ≤ 1000.
 * Binary search the answer.
 * Use Trie data structure to store the best three matching. Traverse the Trie.
 */
public class SearchSuggestionsSystem {

    /**
     * Intuition
     * In a sorted list of words,
     * for any word A[i],
     * all its sugested words must following this word in the list.
     *
     * For example, if A[i] is a prefix of A[j],
     * A[i] must be the prefix of A[i + 1], A[i + 2], ..., A[j]
     *
     * Explanation
     * With this observation,
     * we can binary search the position of each prefix of search word,
     * and check if the next 3 words is a valid suggestion.
     *
     *
     * Complexity
     * Time O(NlogN) for sorting
     * Space O(logN) for quick sort.
     *
     * Time O(logN) for each query
     * Space O(query) for each query
     * where I take word operation as O(1)
     */

    class Solution_BinarySearch {
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            List<List<String>> res = new ArrayList<>();
            Arrays.sort(products); // time complexity: O(k * mlogm) k is the longest word length, m is the products length
            int len = products.length;
            int n = searchWord.length();
            int lo = 0;
            int hi = len - 1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) { // time complexity is  O(m * n) m is the products length, and n is the searchWord length
                while ((lo <= hi) && (products[lo].length() <= i || products[lo].charAt(i) != searchWord.charAt(i))) {  //从左到右，lo的位置的product如果不符合条件，就跳过判断
                    lo++;
                }
                while ((lo <= hi) && (products[hi].length() <= i || products[hi].charAt(i) != searchWord.charAt(i))) {//从右到左，hi的位置的product如果不符合条件，就跳过判断
                    hi--;
                }
                min = Math.min(lo + 3, hi + 1);
                List<String> productList = new ArrayList<>();
                for (int j = lo; j < min; j++) {
                    productList.add(products[j]);
                }
                res.add(productList);
            }
            return res;
        }
    }

    class Solution_Trie {
        TrieNode root;

        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            List<List<String>> res = new ArrayList<>();
            if(products == null || products.length == 0) return res;
            this.root = new TrieNode();
            Arrays.sort(products);
            for(String product : products) {
                buildTrie(product);
            }
            for(int i = 0; i < searchWord.length(); i++) {
                char c = searchWord.charAt(i);
                if(root.next[c - 'a'] != null) {
                    root = root.next[c - 'a'];
                    res.add(root.words);
                } else {
                    for(int j = i; j < searchWord.length(); j++) {
                        res.add(new ArrayList<>());
                    }
                    break;
                }
            }
            return res;
        }

        private void buildTrie(String s) {
            TrieNode node = root;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
                if(node.words.size() < 3) {
                    node.words.add(s);
                }
            }
        }

        class TrieNode {
            TrieNode[] next;
            List<String> words;

            public TrieNode() {
                this.next = new TrieNode[26];
                this.words = new ArrayList<>();
            }
        }

    }
}
