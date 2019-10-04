/**
 * 1170. Compare Strings by Frequency of the Smallest Character
 * Easy
 *
 * 42
 *
 * 100
 *
 * Favorite
 *
 * Share
 * Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
 *
 * Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["cbd"], words = ["zaaaz"]
 * Output: [1]
 * Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
 * Example 2:
 *
 * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * Output: [1,2]
 * Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 *
 *
 * Constraints:
 *
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] are English lowercase letters.
 * Accepted
 * 8,927
 * Submissions
 * 14,598
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
 * 9
 * For each string from queries calculate the leading count "p" and in base of the sorted array calculated on the step 1 do a binary search to count the number of items greater than "p".
 */
public class CompareStringsbyFrequencyoftheSmallestCharacter {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] wordScores = new int[words.length];
        int[] scoreMap = new int[11];
        for (int i = 0;i < words.length;i++) {
            wordScores[i] = f(words[i]);
            scoreMap[wordScores[i]]++;
        }

        int[] prefixSumScore = new int[11];
        prefixSumScore[0] = scoreMap[0];
        for (int i = 1;i < 11;i++) {
            prefixSumScore[i] = prefixSumScore[i - 1] + scoreMap[i];
        }

        int[] ans = new int[queries.length];
        for (int i = 0;i < queries.length;i++) {
            String query = queries[i];
            int score = f(query);
            ans[i] = words.length - prefixSumScore[score];
        }

        return ans;
    }

    private int f(String word) {
        int[] freq = new int[26];
        char minChar = 'z';
        for (char c : word.toCharArray()) {
            if (c <= minChar) {
                freq[c - 'a']++;
                minChar = c;
            }
        }
        return freq[minChar - 'a'];
    }
}
