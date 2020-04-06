/**
 * 1209. Remove All Adjacent Duplicates in String II
 * Medium
 *
 * 287
 *
 * 9
 *
 * Add to List
 *
 * Share
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 *
 * It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lower case English letters.
 * Accepted
 * 17,308
 * Submissions
 * 30,524
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * devendrakota21
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Bloomberg
 * |
 * 5
 *
 * Google
 * |
 * 4
 *
 * VMware
 * |
 * 2
 * Remove All Adjacent Duplicates In String
 * Easy
 * Use a stack to store the characters, when there are k same characters, delete them.
 * To make it more efficient, use a pair to store the value and the count of each character.
 */
public class RemoveAllAdjacentDuplicatesinStringII {

    /**
     * Solution
     * Approach 1: Brute Force
     * We can do exactly what the problem asks: count repeating adjacent letters and remove them when the count reaches k. Then, we do it again until there is nothing to remove.
     *
     * Brute Force Illustration
     *
     * Algorithm
     *
     * Remember the length of the string.
     *
     * Iterate through the string:
     *
     * If the current character is the same as the one before, increase the count.
     *
     * Otherwise, reset the count to 1.
     * If the count equals k, erase last k characters.
     *
     * If the length of the string was changed, repeat starting from the first step.
     *
     *
     * Complexity Analysis
     *
     * Time complexity: \mathcal{O}(n^2 / k)O(n
     * 2
     *  /k), where nn is a string length. We scan the string no more than n / kn/k times.
     *
     * Space complexity: \mathcal{O}(1)O(1). A copy of a string may be created in some languages, however, the algorithm itself only uses the current string.
     *
     */
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int length = -1;
        while (length != sb.length()) {
            length = sb.length();
            for (int i = 0, count = 1; i < sb.length(); ++i) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count = 1;
                } else if (++count == k) {
                    sb.delete(i - k + 1, i + 1);
                    break;
                }
            }
        }
        return sb.toString();
    }
     /**
     * Approach 2: Memoise Count
     * If you observe how the count changes in the approach above, you may have an idea to store it for each character. That way, we do not have to start from the beginning each time we remove a substring. This approach will give us linear time complexity, and the trade-off is the extra memory to store counts.
     *
     * Algorithm
     *
     * Initialize counts array of size n.
     *
     * Iterate through the string:
     *
     * If the current character is the same as the one before, set counts[i] to counts[i - 1] + 1.
     *
     * Otherwise, set counts[i] to 1.
     * If counts[i] equals k, erase last k characters and decrease i by k.
     *
     *
     * Complexity Analysis
     *
     * Time complexity: \mathcal{O}(n)O(n), where nn is a string length. We process each character in the string once.
     *
     * Space complexity: \mathcal{O}(n)O(n) to store the count for each character.
     */

     public String removeDuplicates(String s, int k) {
         StringBuilder sb = new StringBuilder(s);
         int count[] = new int[sb.length()];
         for (int i = 0; i < sb.length(); ++i) {
             if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                 count[i] = 1;
             } else {
                 count[i] = count[i - 1] + 1;
                 if (count[i] == k) {
                     sb.delete(i - k + 1, i + 1);
                     i = i - k;
                 }
             }
         }
         return sb.toString();
     }

     /** Approach 3: Stack
     * Instead of storing counts for each character, we can use a stack. When a character does not match the previous one, we push 1 to the stack. Otherwise, we increment the count on the top of the stack.
     *
     * Stack Illustration
     *
     * Algorithm
     *
     * Iterate through the string:
     *
     * If the current character is the same as the one before, increment the count on the top of the stack.
     *
     * Otherwise, push 1 to the stack.
     * If the count on the top of the stack equals k, erase last k characters and pop from the stack.
     *
     * Note that, since Integer is immutable in Java, we need to pop the value first, increment it, and then push it back (if it's less than k).
     *
     *
     * Complexity Analysis
     *
     * Time complexity: \mathcal{O}(n)O(n), where nn is a string length. We process each character in the string once.
     *
     * Space complexity: \mathcal{O}(n)O(n) for the stack.
     */

     public String removeDuplicates(String s, int k) {
         StringBuilder sb = new StringBuilder(s);
         Stack<Integer> counts = new Stack<>();
         for (int i = 0; i < sb.length(); ++i) {
             if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                 counts.push(1);
             } else {
                 int incremented = counts.pop() + 1;
                 if (incremented == k) {
                     sb.delete(i - k + 1, i + 1);
                     i = i - k;
                 } else {
                     counts.push(incremented);
                 }
             }
         }
         return sb.toString();
     }

     /** Approach 4: Stack with Reconstruction
     * If we store both the count and the character in the stack, we do not have to modify the string. Instead, we can reconstruct the result from characters and counts in the stack.
     *
     * Algorithm
     *
     * Iterate through the string:
     *
     * If the current character is the same as on the top of the stack, increment the count.
     *
     * Otherwise, push 1 and the current character to the stack.
     * If the count on the top of the stack equals k, pop from the stack.
     *
     * Build the result string using characters and counts in the stack.
     *
     *
     * Complexity Analysis
     *
     * Same as for approach 3 above.
      */
     class Pair {
         int cnt;
         char ch;
         public Pair(int cnt, char ch) {
             this.ch = ch;
             this.cnt = cnt;
         }
     }
    public String removeDuplicates(String s, int k) {
        Stack<Pair> counts = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (counts.empty() || s.charAt(i) != counts.peek().ch) {
                counts.push(new Pair(1, s.charAt(i)));
            } else {
                if (++counts.peek().cnt == k) {
                    counts.pop();
                }
            }
        }
        StringBuilder b = new StringBuilder();
        while (!counts.empty()) {
            Pair p = counts.pop();
            for (int i = 0; i < p.cnt; i++) {
                b.append(p.ch);
            }
        }
        return b.reverse().toString();
    }

     /** Approach 5: Two Pointers
     * This method was proposed by @lee215, and we can use it to optimize string operations in approaches 2 and 3. Here, we copy characters within the same string using the fast and slow pointers. Each time we need to erase k characters, we just move the slow pointer k positions back.
     *
     * Two Pointers Illustration
     *
     * Algorithm
     *
     * Initialize the slow pointer (j) with zero.
     *
     * Move the fast pointer (i) through the string:
     *
     * Copy s[i] into s[j].
     *
     * If s[j] is the same as s[j - 1], increment the count on the top of the stack.
     *
     * Otherwise, push 1 to the stack.
     * If the count equals k, decrease j by k and pop from the stack.
     *
     * Return j first characters of the string.
     *
     *
     * Complexity Analysis
     *
     * Same as for approach 3 above.
     */

     public String removeDuplicates(String s, int k) {
         Stack<Integer> counts = new Stack<>();
         char[] sa = s.toCharArray();
         int j = 0;
         for (int i = 0; i < s.length(); ++i, ++j) {
             sa[j] = sa[i];
             if (j == 0 || sa[j] != sa[j - 1]) {
                 counts.push(1);
             } else {
                 int incremented = counts.pop() + 1;
                 if (incremented == k) {
                     j = j - k;
                 } else {
                     counts.push(incremented);
                 }
             }
         }
         return new String(sa, 0, j);
     }

    class Solution {
        public String removeDuplicates(String s, int k) {
            int i = 0, n = s.length(), count[] = new int[n];
            char[] stack = s.toCharArray();
            for (int j = 0; j < n; ++j, ++i) {
                stack[i] = stack[j];
                count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
                if (count[i] == k) i -= k;
            }
            return new String(stack, 0, i);
        }
    }
}


