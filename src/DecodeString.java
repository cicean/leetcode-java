import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cicean on 9/8/2016.
 * 394. Decode String  QuestionEditorial Solution  My Submissions
 Total Accepted: 2272 Total Submissions: 6015 Difficulty: Medium
 Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 Hide Company Tags Google
 Hide Tags Depth-first Search Stack

 */
public class DecodeString {

    //Java recursive solution
    public class Solution {
        int helper(char[] cc, int s, StringBuilder str) {
            int i = s;
            for (; i < cc.length; i++) {
                if (cc[i] >= '0' && cc[i] <= '9') { // find repeat pattern
                    int count = (int) (cc[i++] - '0');
                    while (cc[i] != '[') {  // count repeating time
                        count = count * 10 + (int) (cc[i++] - '0');
                    }
                    int start = ++i;
                    StringBuilder inner = new StringBuilder();
                    int end = helper(cc, start, inner); // build the inner string of this repeat pattern
                    while (count-- > 0) {
                        str.append(inner);
                    }
                    i = end; // move index to the next non-visited char
                } else if (cc[i] == ']') { // ending of an inner pattern
                    return i;
                } else { // not a repeat pattern
                    str.append(cc[i]);
                }
            }
            return i; // end of string s
        }

        public String decodeString(String s) {
            if (s == null || s.length() == 0) return "";
            StringBuilder ret = new StringBuilder();
            helper(s.toCharArray(), 0, ret);
            return ret.toString();
        }
    }

    //Java Solution using Stack
    public class Solution2 {
        public String decodeString(String s) {
            String res = "";
            Stack<Integer> countStack = new Stack<>();
            Stack<String> resStack = new Stack<>();
            int idx = 0;
            while (idx < s.length()) {
                if (Character.isDigit(s.charAt(idx))) {
                    int count = 0;
                    while (Character.isDigit(s.charAt(idx))) {
                        count = 10 * count + (s.charAt(idx) - '0');
                        idx++;
                    }
                    countStack.push(count);
                } else if (s.charAt(idx) == '[') {
                    resStack.push(res);
                    res = "";
                    idx++;
                } else if (s.charAt(idx) == ']') {
                    StringBuilder temp = new StringBuilder(resStack.pop());
                    int repeatTimes = countStack.pop();
                    for (int i = 0; i < repeatTimes; i++) {
                        temp.append(res);
                    }
                    res = temp.toString();
                    idx++;
                } else {
                    res += s.charAt(idx++);
                }
            }
            return res;
        }
    }

    BigDecimal

    public String decodeString(String s) { // shorter but less optimized REGEX
        Matcher matcher = Pattern.compile("(\\d*)\\[([a-z]*)\\]").matcher(s);
        while (matcher.find()) {
            StringBuffer sb = new StringBuffer();
            String part = new String(new char[Integer.parseInt(matcher.group(1))]).replace("\0", matcher.group(2));
            matcher.appendReplacement(sb, part).appendTail(sb);
            matcher.reset(s = sb.toString());
        }
        return s;
    }
}
