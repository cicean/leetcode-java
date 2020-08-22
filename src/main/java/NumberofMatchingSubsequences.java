/**
 * 792. Number of Matching Subsequences
 * Medium
 *
 * 766
 *
 * 55
 *
 * Add to List
 *
 * Share
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 * Accepted
 * 35,329
 * Submissions
 * 75,229
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * Is Subsequence
 * Easy
 * Shortest Way to Form String
 * Medium
 */
public class NumberofMatchingSubsequences {

    /**
     * Approach #1: Brute Force [Time Limit Exceeded]
     * Intuition and Algorithm
     *
     * Let's try to check separately whether each word words[i] is a subsequence of S.
     *
     * For every such word, we try to find the first occurrence of word[0] in S, then from that point on try to find the first occurrence of word[1], and so on.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(\text{words.length} * S\text{.length} + \sum_i \text{words[i].length})O(words.length∗S.length+∑
     * i
     * ​
     *  words[i].length). For each word, our subseq check on word words[i] may take time complexity O(S\text{.length} + \text{words[i].length})O(S.length+words[i].length).
     *
     * Space Complexity: O(1)O(1). (In Java, our space complexity is O(S\text{.length} + \text{max(words[i].length)})O(S.length+max(words[i].length)), but can be made to be O(1)O(1) with a pointer based implementation.)
     */

    class Solution {
        char[] ca, cb;
        public int numMatchingSubseq(String S, String[] words) {
            int ans = 0;
            ca = S.toCharArray();
            for (String word: words)
                if (subseq(word)) ans++;
            return ans;
        }

        public boolean subseq(String word) {
            int i = 0;
            cb = word.toCharArray();
            for (char c: ca) {
                if (i < cb.length && c == cb[i]) i++;
            }
            return (i == cb.length);
        }
    }

     /** Approach #2: Next Letter Pointers [Accepted]
     * Intuition
     *
     * Since the length of S is large, let's think about ways to iterate through S only once, instead of many times as in the brute force solution.
     *
     * We can put words into buckets by starting character. If for example we have words = ['dog', 'cat', 'cop'], then we can group them 'c' : ('cat', 'cop'), 'd' : ('dog',). This groups words by what letter they are currently waiting for. Then, while iterating through letters of S, we will move our words through different buckets.
     *
     * For example, if we have a string like S = 'dcaog':
     *
     * heads = 'c' : ('cat', 'cop'), 'd' : ('dog',) at beginning;
     * heads = 'c' : ('cat', 'cop'), 'o' : ('og',) after S[0] = 'd';
     * heads = 'a' : ('at',), 'o' : ('og', 'op') after S[0] = 'c';
     * heads = 'o' : ('og', 'op'), 't': ('t',) after S[0] = 'a';
     * heads = 'g' : ('g',), 'p': ('p',), 't': ('t',) after S[0] = 'o';
     * heads = 'p': ('p',), 't': ('t',) after S[0] = 'g';
     * Algorithm
     *
     * Instead of a dictionary, we'll use an array heads of length 26, one entry for each letter of the alphabet. For each letter in S, we'll take all the words waiting for that letter, and have them wait for the next letter in that word. If we use the last letter of some word, it adds 1 to the answer.
     *
     * For another description of this algorithm and a more concise implementation, please see @StefanPochmann's excellent forum post here.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(S\text{.length} + \sum_i \text{words[i].length})O(S.length+∑
     * i
     * ​
     *  words[i].length).
     *
     * Space Complexity: O(\text{words.length})O(words.length). (In Java, our additional space complexity is O(\sum_i \text{words[i].length})O(∑
     * i
     * ​
     *  words[i].length), but can be made to be O(\text{words.length})O(words.length) with a pointer based implementation.)
     */

     class Solution {
         public int numMatchingSubseq(String S, String[] words) {
             int ans = 0;
             ArrayList<Node>[] heads = new ArrayList[26];
             for (int i = 0; i < 26; ++i)
                 heads[i] = new ArrayList<Node>();

             for (String word: words)
                 heads[word.charAt(0) - 'a'].add(new Node(word, 0));

             for (char c: S.toCharArray()) {
                 ArrayList<Node> old_bucket = heads[c - 'a'];
                 heads[c - 'a'] = new ArrayList<Node>();

                 for (Node node: old_bucket) {
                     node.index++;
                     if (node.index == node.word.length()) {
                         ans++;
                     } else {
                         heads[node.word.charAt(node.index) - 'a'].add(node);
                     }
                 }
                 old_bucket.clear();
             }
             return ans;
         }

     }

    class Node {
        String word;
        int index;
        public Node(String w, int i) {
            word = w;
            index = i;
        }
    }

    class Solution {
        public int numMatchingSubseq(String S, String[] words) {
            LinkedList<int[]>[] list = new LinkedList[26];
            for(int i=0;i<list.length;i++){
                list[i]= new LinkedList<>();
            }
            for(int i=0;i<words.length;i++){
                String word = words[i];
                list[word.charAt(0)-'a'].add(new int[]{i,0});
            }
            int count = 0;
            for(int i=0;i<S.length();i++){
                int index = S.charAt(i)-'a';
                int size = list[index].size();
                for(int j=0;j<size;j++){
                    int[] item = list[index].poll();
                    item[1]++;
                    String word =words[item[0]];
                    if(item[1]==word.length()){
                        count++;
                    }else{
                        list[word.charAt(item[1])-'a'].add(item);
                    }
                }
            }
            return count;

        }
    }
}
