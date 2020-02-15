/**
 * 791. Custom Sort String
 * Medium
 *
 * 561
 *
 * 159
 *
 * Add to List
 *
 * Share
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 *
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 *
 * Return any permutation of T (as a string) that satisfies this property.
 *
 * Example :
 * Input:
 * S = "cba"
 * T = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 *
 *
 * Note:
 *
 * S has length at most 26, and no character is repeated in S.
 * T has length at most 200.
 * S and T consist of lowercase letters only.
 * Accepted
 * 50,694
 * Submissions
 * 78,910
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 3
 * Complexity Analysis
 *
 * Time Complexity: O(S.\text{length} + T.\text{length})O(S.length+T.length), the time it takes to iterate through S and T
 *
 * Space Complexity: O(T.\text{length})O(T.length). We count at most 26 different lowercase letters, but the final answer has the same length as T.
 */
public class CustomSortString {
    /**
     * Approach #1: Count and Write [Accepted]
     * Intuition
     *
     * Let's first write to our answer the elements of T that occur in S, in order of S. After, we'll write any elements of T we didn't write. This obviously keeps all the ordering relationships we wanted.
     *
     * In the second write, the order doesn't matter because those elements aren't in S, so there are no ordering relationships these elements have to satisfy.
     *
     * Algorithm
     *
     * The trick is to count the elements of T. After we have some count[char] = (the number of occurrences of char in T), we can write these elements in the order we want. The order is S + (characters not in S in any order).
     *
     * For more details on the algorithm, please see the inlined comments in each implementation.
     *
     */

    class Solution {
        public String customSortString(String S, String T) {
            // count[char] = the number of occurrences of 'char' in T.
            // This is offset so that count[0] = occurrences of 'a', etc.
            // 'count' represents the current state of characters
            // (with multiplicity) we need to write to our answer.
            int[] count = new int[26];
            for (char c: T.toCharArray())
                count[c - 'a']++;

            // ans will be our final answer.  We use StringBuilder to join
            // the answer so that we more efficiently calculate a
            // concatenation of strings.
            StringBuilder ans = new StringBuilder();

            // Write all characters that occur in S, in the order of S.
            for (char c: S.toCharArray()) {
                for (int i = 0; i < count[c - 'a']; ++i)
                    ans.append(c);
                // Setting count[char] to zero to denote that we do
                // not need to write 'char' into our answer anymore.
                count[c - 'a'] = 0;
            }

            // Write all remaining characters that don't occur in S.
            // That information is specified by 'count'.
            for (char c = 'a'; c <= 'z'; ++c)
                for (int i = 0; i < count[c - 'a']; ++i)
                    ans.append(c);

            return ans.toString();
        }
    }

    class Solution_2 {
        public String customSortString(String S, String T) {
            int[] count = new int[26];
            for (char c : T.toCharArray()) { ++count[c - 'a']; }  // count each char in T.
            StringBuilder sb = new StringBuilder();
            for (char c : S.toCharArray()) {
                while (count[c - 'a']-- > 0) { sb.append(c); }    // sort chars both in T and S by the order of S.
            }
            for (char c = 'a'; c <= 'z'; ++c) {
                while (count[c - 'a']-- > 0) { sb.append(c); }    // group chars in T but not in S.
            }
            return sb.toString();
        }
    }

}
