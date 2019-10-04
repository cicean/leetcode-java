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
     * ����
     ������һ��һ��Ҫ������⣬����Ҫ���ǵ��ǻ��ж����ֽ������ϸ�۲�ᷢ�֣����ջ���Cn0 + Cn1 + Cn2 + ... + Cnn = 2^n�ֽ����
     Ȼ��ͺ���ȻӦ����DFS, ÿ��recursion���µ�ǰ�����Ȼ�����DFS��Ҫע����һ��DFS����ʼλ��Ҫ�뵱ǰ����λ�ø�һ��������ͻ�������������ֵĽ������ϣ�������������ֵ�ԭ������Ϊ�������ֿ��Ժϲ���һ�����֣��Ѿ����ȥ�ˣ�����ab111����ab3, ����Ҫ�Ľ����ab3��

     ���Ӷ�
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

            // �����µ���ʼλ��
            int i = 0;

            // �����ʼ����ʼλ�ö�Ҫ��֮ǰ��βλ�ø�һ��
            if (start > 0) {
                i = start + 1;
            }

            for (; i < s.length(); i++) {
                String prefix = curr + s.substring(start, i);
                // ��ith�ַ���ͷ�������滻j����ĸ�����֡�
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
