/**
 * 925. Long Pressed Name
 * Easy
 *
 * 483
 *
 * 66
 *
 * Add to List
 *
 * Share
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 *
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 * Example 3:
 *
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 * Example 4:
 *
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 *
 *
 * Constraints:
 *
 * 1 <= name.length <= 1000
 * 1 <= typed.length <= 1000
 * The characters of name and typed are lowercase letters.
 * Accepted
 * 34,768
 * Submissions
 * 77,416
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * Hemant_323
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 4
 */
public class LongPressedName {
    /**
     * Solution
     * Approach 1: Group into Blocks
     * Intuition and Algorithm
     *
     * For a string like S = 'aabbbbccc', we can group it into blocks groupify(S) = [('a', 2), ('b', 4), ('c', 3)], that consist of a key 'abc' and a count [2, 4, 3].
     *
     * Then, the necessary and sufficient condition for typed to be a long-pressed version of name is that the keys are the same, and each entry of the count of typed is at least the entry for the count of name.
     *
     * For example, 'aaleex' is a long-pressed version of 'alex': because when considering the groups [('a', 2), ('l', 1), ('e', 2), ('x', 1)] and [('a', 1), ('l', 1), ('e', 1), ('x', 1)], they both have the key 'alex', and the count [2,1,2,1] is at least [1,1,1,1] when making an element-by-element comparison (2 >= 1, 1 >= 1, 2 >= 1, 1 >= 1).
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N+T)O(N+T), where N, TN,T are the lengths of name and typed.
     *
     * Space Complexity: O(N+T)O(N+T).
     *
     */

    class Solution {
        public boolean isLongPressedName(String name, String typed) {
            Group g1 = groupify(name);
            Group g2 = groupify(typed);
            if (!g1.key.equals(g2.key))
                return false;

            for (int i = 0; i < g1.count.size(); ++i)
                if (g1.count.get(i) > g2.count.get(i))
                    return false;
            return true;
        }

        public Group groupify(String S) {
            StringBuilder key = new StringBuilder();
            List<Integer> count = new ArrayList();
            int anchor = 0;
            int N = S.length();
            for (int i = 0; i < N; ++i) {
                if (i == N-1 || S.charAt(i) != S.charAt(i+1)) { // end of group
                    key.append(S.charAt(i));
                    count.add(i - anchor + 1);
                    anchor = i+1;
                }
            }

            return new Group(key.toString(), count);
        }
    }

    class Group {
        String key;
        List<Integer> count;
        Group(String k, List<Integer> c) {
            key = k;
            count = c;
        }
    }

     /** Approach 2: Two Pointer
     * Intuition
     *
     * As in Approach 1, we want to check the key and the count. We can do this on the fly.
     *
     * Suppose we read through the characters name, and eventually it doesn't match typed.
     *
     * There are some cases for when we are allowed to skip characters of typed. Let's use a tuple to denote the case (name, typed):
     *
     * In a case like ('aab', 'aaaaab'), we can skip the 3rd, 4th, and 5th 'a' in typed because we have already processed an 'a' in this block.
     *
     * In a case like ('a', 'b'), we can't skip the 1st 'b' in typed because we haven't processed anything in the current block yet.
     *
     * Algorithm
     *
     * This leads to the following algorithm:
     *
     * For each character in name, if there's a mismatch with the next character in typed:
     * If it's the first character of the block in typed, the answer is False.
     * Else, discard all similar characers of typed coming up. The next (different) character coming must match.
     * Also, we'll keep track on the side of whether we are at the first character of the block.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N+T)O(N+T), where N, TN,T are the lengths of name and typed.
     *
     * Space Complexity: O(1)O(1) in additional space complexity. (In Java, .toCharArray makes this O(N)O(N), but this can be easily remedied.)
     */

     class Solution2 {
         public boolean isLongPressedName(String name, String typed) {
             int i = 0, m = name.length(), n = typed.length();
             for (int j = 0; j < n; ++j)
                 if (i < m && name.charAt(i) == typed.charAt(j))
                     ++i;
                 else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
                     return false;
             return i == m;
         }
     }
}
