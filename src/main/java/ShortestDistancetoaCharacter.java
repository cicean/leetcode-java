/**
 * 821. Shortest Distance to a Character
 * Easy
 *
 * 914
 *
 * 74
 *
 * Add to List
 *
 * Share
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 *
 * Note:
 *
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 * Accepted
 * 58,729
 * Submissions
 * 88,885
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * milu
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 *
 * Apple
 * |
 * 2
 */
public class ShortestDistancetoaCharacter {
    /**
     * Quick Navigation
     * View in Article
     *
     *
     *
     *
     *
     * Average Rating: 4.71 (24 votes)
     *
     * Approach #1: Min Array [Accepted]
     * Intuition
     *
     * For each index S[i], let's try to find the distance to the next character C going left, and going right. The answer is the minimum of these two values.
     *
     * Algorithm
     *
     * When going left to right, we'll remember the index prev of the last character C we've seen. Then the answer is i - prev.
     *
     * When going right to left, we'll remember the index prev of the last character C we've seen. Then the answer is prev - i.
     *
     * We take the minimum of these two answers to create our final answer.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the length of S. We scan through the string twice.
     *
     * Space Complexity: O(N)O(N), the size of ans.
     */

    class Solution {
        public int[] shortestToChar(String S, char C) {
            int N = S.length();
            int[] ans = new int[N];
            int prev = Integer.MIN_VALUE / 2;

            for (int i = 0; i < N; ++i) {
                if (S.charAt(i) == C) prev = i;
                ans[i] = i - prev;
            }

            prev = Integer.MAX_VALUE / 2;
            for (int i = N-1; i >= 0; --i) {
                if (S.charAt(i) == C) prev = i;
                ans[i] = Math.min(ans[i], prev - i);
            }

            return ans;
        }
    }

    class Solution {
        public int[] shortestToChar(String S, char C) {
            int n = S.length(), pos = -n, res[] = new int[n];
            for (int i = 0; i < n; ++i) {
                if (S.charAt(i) == C) pos = i;
                res[i] = i - pos;
            }
            for (int i = pos - 1; i >= 0; --i) {
                if (S.charAt(i) == C)  pos = i;
                res[i] = Math.min(res[i], pos - i);
            }
            return res;
        }
    }
}
