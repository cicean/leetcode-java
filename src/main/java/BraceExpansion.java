/**
 * 1087. Brace Expansion
 * Medium
 *
 * 88
 *
 * 8
 *
 * Favorite
 *
 * Share
 * A string S represents a list of words.
 *
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 *
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 *
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 *
 * Input: "abcd"
 * Output: ["abcd"]
 *
 *
 * Note:
 *
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 * Accepted
 * 6,460
 * Submissions
 * 10,813
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 * Decode String
 * Medium
 * Letter Case Permutation
 * Easy
 * Brace Expansion II
 * Hard
 * All generated strings are of the same size. How can we generate all of these strings?
 * Do a backtracking on which each level of it has to choose one single (e.g. 'a') character or any character of the given parenthesized group (e.g. "{a,b,c}")
 */

import java.util.*;





public class BraceExpansion {

    class Solution_1 {
        List<String> ans;

        public String[] expand(String S) {
            // parse S into sa.
            List<boolean[]> sa = new ArrayList<>();
            for (int i = 0; i < S.length(); i++) {
                sa.add(new boolean[26]);
                if (S.charAt(i) == '{') {
                    i++;
                    while (S.charAt(i) != '}') {
                        if (S.charAt(i) != ',')
                            sa.get(sa.size() - 1)[S.charAt(i) - 'a'] = true;
                        i++;
                    }
                } else {
                    sa.get(sa.size() - 1)[S.charAt(i) - 'a'] = true;
                }
            }

            ans = new ArrayList<>();
            dfs(sa, new StringBuilder());

            return ans.toArray(new String[ans.size()]);
        }

        private void dfs(List<boolean[]> sa, StringBuilder sb) {
            if (sb.length() >= sa.size()) {
                ans.add(sb.toString());
                return;
            }

            boolean[] list = sa.get(sb.length());
            for (int i = 0; i < 26; i++) {
                if (!list[i]) continue;
                sb.append((char)(i + 'a'));
                dfs(sa, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String[] expand(String S) {
        // TreeSet to sort
        TreeSet<String> set = new TreeSet<>();
        if (S.length() == 0) {
            return new String[]{""};
        } else if (S.length() == 1) {
            return new String[]{S};
        }
        if (S.charAt(0) == '{') {
            int i = 0; // keep track of content in the "{content}"
            while (S.charAt(i) != '}') {
                i++;
            }
            String sub = S.substring(1, i);
            String[] subs = sub.split(",");
            String[] strs = expand(S.substring(i + 1)); // dfs
            for (int j = 0; j < subs.length; j++) {
                for (String str : strs) {
                    set.add(subs[j] + str);
                }
            }
        } else {
            String[] strs = expand(S.substring(1));
            for (String str : strs) {
                set.add(S.charAt(0) + str);
            }
        }
        return set.toArray(new String[0]);
    }







}
