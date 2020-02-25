import java.util.*;

/**
 * 1048. Longest String Chain
 * Medium
 *
 * 506
 *
 * 36
 *
 * Add to List
 *
 * Share
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: one of the longest word chain is "a","ba","bda","bdca".
 *
 *
 * Note:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of English lowercase letters.
 *
 *
 * Accepted
 * 34,982
 * Submissions
 * 66,040
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Two Sigma
 * |
 * 21
 *
 * Citadel
 * |
 * 9
 *
 * Google
 * |
 * 8
 *
 * Akuna Capital
 * |
 * 2
 *
 * VMware
 * |
 * 2
 *
 * SAP
 * |
 * 2
 * Instead of adding a character, try deleting a character to form a chain in reverse.
 * For each word in order of length, for each word2 which is word with one character removed, length[word2] = max(length[word2], length[word] + 1).
 */
public class LongestStringChain {

    class Solution {
        public int longestStrChain(String[] words) {
            Map<String, Integer> dp = new HashMap<>();
            Arrays.sort(words, (a, b)->a.length() - b.length());
            int res = 0;
            for (String word : words) {
                int best = 0;
                for (int i = 0; i < word.length(); ++i) {
                    String prev = word.substring(0, i) + word.substring(i + 1);
                    best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
                }
                dp.put(word, best);
                res = Math.max(res, best);
            }
            return res;
        }
    }


    class Solution_2 {
        public int longestStrChain(String[] words) {
            if(words == null || words.length == 0) return 0;
            int res = 0;
            Arrays.sort(words, (a,b)-> a.length()-b.length());

            HashMap<String, Integer> map = new HashMap();

            for(String cur : words){
                if(map.containsKey(cur)){
                    continue;
                }
                map.put(cur,1);


                for(int i = 0 ; i<cur.length() ; i++){
                    StringBuilder sb = new StringBuilder(cur);
                    String temp = sb.deleteCharAt(i).toString();
                    if(map.containsKey(temp)){
                        if(map.get(temp)+1 > map.get(cur)){
                            map.put(cur, map.get(temp)+1);
                        }
                    }
                }

                res = Math.max(res, map.get(cur));
            }


            return res;



        }
    }
}
