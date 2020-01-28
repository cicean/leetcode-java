import java.util.ArrayList;
import java.util.List;

/**
 * 1324. Print Words Vertically
 * Medium
 *
 * 27
 *
 * 17
 *
 * Add to List
 *
 * Share
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "HOW ARE YOU"
 * Output: ["HAY","ORO","WEU"]
 * Explanation: Each word is printed vertically.
 *  "HAY"
 *  "ORO"
 *  "WEU"
 * Example 2:
 *
 * Input: s = "TO BE OR NOT TO BE"
 * Output: ["TBONTB","OEROOE","   T"]
 * Explanation: Trailing spaces is not allowed.
 * "TBONTB"
 * "OEROOE"
 * "   T"
 * Example 3:
 *
 * Input: s = "CONTEST IS COMING"
 * Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 200
 * s contains only upper case English letters.
 * It's guaranteed that there is only one space between 2 words.
 * Accepted
 * 5,318
 * Submissions
 * 9,010
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * muven89
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * LeetCode
 * Use the maximum length of words to determine the length of the returned answer. However, don't forget to remove trailing spaces.
 */

public class PrintWordsVertically {

    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int mx = 0;
        for (int i = 0; i < words.length; ++i)
            mx = Math.max(mx, words[i].length());
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < mx; ++i) {
            StringBuilder sb = new StringBuilder();
            for (String word : words)
                sb.append(i < word.length() ? word.charAt(i) : " ");
            while (sb.charAt(sb.length() - 1) == ' ')
                sb.deleteCharAt(sb.length() - 1); // remove trailing space.
            ans.add(sb.toString());
        }
        return ans;
    }
}
