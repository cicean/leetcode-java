import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 8/29/2016.
 * 320. Generalized Abbreviation  QuestionEditorial Solution  My Submissions
 Total Accepted: 10075 Total Submissions: 24186 Difficulty: Medium
 Write a function to generate the generalized abbreviations of a word.

 Example:
 Given word = "word", return the following list (order does not matter):
 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 Hide Company Tags Google
 Hide Tags Backtracking Bit Manipulation
 Hide Similar Problems (M) Subsets (E) Unique Word Abbreviation

 */
public class GeneralizedAbbreviation {

    /**
     * 分析
     这道题第一步一定要理解题意，首先要考虑的是会有多少种结果。仔细观察会发现，最终会有Cn0 + Cn1 + Cn2 + ... + Cnn = 2^n种结果。
     然后就很显然应该用DFS, 每次recursion存下当前结果，然后继续DFS。要注意下一步DFS的起始位置要与当前结束位置隔一个，否则就会出现有连续数字的结果，不希望出现连续数字的原因是因为连续数字可以合并成一个数字，已经算进去了，比如ab111就是ab3, 我们要的结果是ab3。

     复杂度
     time: O(2^n), space: O(n)
     */

    public class Solution {
        public List<String> generateAbbreviations(String word) {
            List<String> res = new ArrayList<>();
            dfs(res, "", 0, word);
            return res;
        }

        public void dfs(List<String> res, String curr, int start, String s) {
            res.add(curr + s.substring(start));
            if (start == s.length())
                return;

            // 定义新的起始位置
            int i = 0;

            // 除了最开始，起始位置都要与之前结尾位置隔一个
            if (start > 0) {
                i = start + 1;
            }

            for (; i < s.length(); i++) {
                String prefix = curr + s.substring(start, i);
                // 以ith字符开头，依次替换j个字母成数字。
                for (int j = 1; j <= s.length() - i; j++) {
                    dfs(res,  prefix+ j, i + j, s);
                }
            }
        }
    }

    /**
     * O(m*n) bit manipulation java
     */
    public List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<>();
        int n = word.length();
        for(int mask = 0;mask < (1 << n);mask++) {
            int count = 0;
            StringBuffer sb = new StringBuffer();
            for(int i = 0;i <= n;i++) {
                if(((1 << i) & mask) > 0) {
                    count++;
                } else {
                    if(count != 0) {
                        sb.append(count);
                        count = 0;
                    }
                    if(i < n) sb.append(word.charAt(i));
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }

}
