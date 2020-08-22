import java.util.Arrays;

/**
 * 1021. Remove Outermost Parentheses
 * Easy
 *
 * 376
 *
 * 513
 *
 * Add to List
 *
 * Share
 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 *
 * A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
 *
 * Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
 *
 * Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
 *
 *
 *
 * Example 1:
 *
 * Input: "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 * Example 2:
 *
 * Input: "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 * Example 3:
 *
 * Input: "()()"
 * Output: ""
 * Explanation:
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 *
 *
 * Note:
 *
 * S.length <= 10000
 * S[i] is "(" or ")"
 * S is a valid parentheses string
 *
 * Accepted
 * 75,645
 * Submissions
 * 98,236
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * Can you find the primitive decomposition? The number of ( and ) characters must be equal.
 */
public class RemoveOutermostParentheses {
    /**
     * Intuition
     * Quote from @shubhama,
     * Primitive string will have equal number of opened and closed parenthesis.
     *
     * Explanation:
     * opened count the number of opened parenthesis.
     * Add every char to the result,
     * unless the first left parenthesis,
     * and the last right parenthesis.
     *
     * Time Complexity:
     * O(N) Time, O(N) space
     */

    class Solution {
        public String removeOuterParentheses(String S) {
            StringBuilder s = new StringBuilder();
            int opened = 0;
            for (char c : S.toCharArray()) {
                if (c == '(' && opened++ > 0) s.append(c);
                if (c == ')' && opened-- > 1) s.append(c);
            }
            return s.toString();
        }
    }

    class Solution {
        public String removeOuterParentheses(String S) {
            return removeOuterParentheses(S, 0, new char[S.length()], 0);
        }

        public String removeOuterParentheses(String S, int begin, char[] chars, int index) {
            if(begin == S.length()-1) return new String(Arrays.copyOf(chars, index));
            int i = begin;
            int left = 0, right = 0;
            while (i < S.length()){
                if(S.charAt(i) == '(') left++;
                else right++;
                if(left == right) break;
                i++;
            }
            for(int k = begin+1; k < i; ++k)
                chars[index++] = S.charAt(k);
            return removeOuterParentheses(S, i, chars, index);
        }
    }
}
