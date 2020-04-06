import java.util.*;

/**
 * 1297. Maximum Number of Occurrences of a Substring
 * Medium
 *
 * 74
 *
 * 85
 *
 * Add to List
 *
 * Share
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 * Example 3:
 *
 * Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * Output: 3
 * Example 4:
 *
 * Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s only contains lowercase English letters.
 * Accepted
 * 6.1K
 * Submissions
 * 13.7K
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Roblox
 * |
 * LeetCode
 * Check out the constraints, (maxSize <=26).
 * This means you can explore all substrings in O(n * 26).
 * Find the Maximum Number of Occurrences of a Substring with bruteforce.
 */
public class MaximumNumberofOccurrencesofaSubstring {

    class Solution {
        String s;
        HashMap<String, Integer> map = new HashMap<>();

        public int maxFreq(String t, int k, int min, int max) {
            s = t;
            char[] arr = s.toCharArray();
            int[] profile = new int[26];
            int count = 0, n = arr.length;
            for(int i=0; i<min; ++i) {
                int c = arr[i] - 'a';
                if(profile[c] == 0) ++count;
                ++profile[c];
            }
            if(count <= k) add(0, min);
            for(int i=1; i<=n-min; ++i) {
                int c = arr[i-1]-'a', d = arr[i+min-1]-'a';
                if(c != d) {
                    if(profile[c] == 1) --count;
                    --profile[c];
                    if(profile[d] == 0) ++count;
                    ++profile[d];
                }
                if(count <= k) add(i, i+min);
            }
            int ans = 0;
            for(int x: map.values()) ans = Math.max(ans, x);
            return ans;
        }

        private void add(int i, int j) {
            String t = s.substring(i, j);
            map.put(t, map.getOrDefault(t, 0)+1);
        }
    }

    class Solution_2 {
        public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
            Map<String, Integer> subStringCount = new HashMap<>();
            int[] letterCount = new int[26];
            char[] sArr = s.toCharArray();

            int start = 0;
            int end = start + minSize - 1;
            int letters = 0;
            int maxCount = 0;
            for (int i = start; i <= end; i++) {
                if (letterCount[sArr[i] - 'a'] == 0) {
                    letters++;
                }
                letterCount[sArr[i] - 'a']++;
            }

            if (letters <= maxLetters) {
                subStringCount.put(s.substring(start, end + 1), 1);
                maxCount = 1;
            }

            end++;
            while (end <= s.length() - 1) {
                if (letterCount[sArr[end] - 'a']++ == 0) {
                    letters++;
                }
                if (letterCount[sArr[start] - 'a']-- == 1) {
                    letters--;
                }
                start++;
                if (letters <= maxLetters) {
                    String subS = s.substring(start, end + 1);
                    subStringCount.put(subS, subStringCount.getOrDefault(subS, 0) + 1);
                    maxCount = Math.max(maxCount, subStringCount.get(subS));
                }
                end++;
            }
            return maxCount;
        }
    }
    }
