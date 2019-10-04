package Airbnb;

/**
 * Created by cicean on 9/13/2018.
 */
public class RegularExperssion {

    //和 Wildcard Matching 同样模板的代码。
    //使用了记忆化搜索（Memoization Search）
    class Solution {
        /**
         * @param s: A string
         * @param p: A string includes "." and "*"
         * @return: A boolean
         */
        public boolean isMatch(String s, String p) {
            if (s == null || p == null) {
                return false;
            }

            boolean[][] memo = new boolean[s.length()][p.length()];
            boolean[][] visited = new boolean[s.length()][p.length()];

            return isMatchHelper(s, 0, p, 0, memo, visited);
        }

        private boolean isMatchHelper(String s, int sIndex,
                                      String p, int pIndex,
                                      boolean[][] memo,
                                      boolean[][] visited) {
            // "" == ""
            if (pIndex == p.length()) {
                return sIndex == s.length();
            }

            if (sIndex == s.length()) {
                return isEmpty(p, pIndex);
            }

            if (visited[sIndex][pIndex]) {
                return memo[sIndex][pIndex];
            }

            char sChar = s.charAt(sIndex);
            char pChar = p.charAt(pIndex);
            boolean match;

            // consider a* as a bundle
            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                match = isMatchHelper(s, sIndex, p, pIndex + 2, memo, visited) ||
                        charMatch(sChar, pChar) && isMatchHelper(s, sIndex + 1, p, pIndex, memo, visited);
            } else {
                match = charMatch(sChar, pChar) &&
                        isMatchHelper(s, sIndex + 1, p, pIndex + 1, memo, visited);
            }

            visited[sIndex][pIndex] = true;
            memo[sIndex][pIndex] = match;
            return match;
        }

        private boolean charMatch(char sChar, char pChar) {
            return sChar == pChar || pChar == '.';
        }

        private boolean isEmpty(String p, int pIndex) {
            for (int i = pIndex; i < p.length(); i += 2) {
                if (i + 1 >= p.length() || p.charAt(i + 1) != '*') {
                    return false;
                }
            }
            return true;
        }
    }
}
