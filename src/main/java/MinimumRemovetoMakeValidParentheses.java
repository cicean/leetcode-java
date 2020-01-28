import java.util.*;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Medium
 *
 * 254
 *
 * 5
 *
 * Add to List
 *
 * Share
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 *
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 * Accepted
 * 18,794
 * Submissions
 * 31,560
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 45
 *
 * Bloomberg
 * |
 * 2
 *
 * Amazon
 * |
 * 2
 * Each prefix of a balanced parentheses has a number of open parentheses greater or equal than closed parentheses, similar idea with each suffix.
 * Check the array from left to right, remove characters that do not meet the property mentioned above, same idea in backward way.
 */
public class MinimumRemovetoMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public String minRemoveToMakeValid_stackUseInt(String s) {
        // run through in both directions
        char[] chars = s.toCharArray();
        int bal = 0;
        for(int i = 0; i< chars.length;i++){
            if(chars[i] == '(') {
                bal += 1;
            } else if (chars[i] == ')') {
                bal -= 1;
                if(bal < 0) {
                    bal = 0;
                    chars[i] = '\0';
                }
            }
        }
        bal = 0;
        for(int i = chars.length-1; i>=0;i--){
            if(chars[i] == '(') {
                bal += 1;
                if(bal > 0) {
                    bal = 0;
                    chars[i] = '\0';
                }
            } else if (chars[i] == ')') {
                bal -= 1;

            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c: chars){
            if(c != '\0')
                sb.append(c);
        }
        return sb.toString();
    }
}
