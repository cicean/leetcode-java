/**
 * 727. Minimum Window Subsequence
 * Hard
 *
 * 503
 *
 * 25
 *
 * Add to List
 *
 * Share
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 *
 *
 * Accepted
 * 28,672
 * Submissions
 * 70,360
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * Talky
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 8
 *
 * Microsoft
 * |
 * 2
 * Minimum Window Substring
 * Hard
 * Longest Continuous Increasing Subsequence
 * Easy
 * Let dp[j][e] = s be the largest index for which S[s:e+1] has T[:j] as a substring.
 */
public class MinimumWindowSubsequence {

    /**
     * Approach #1: Dynamic Programming (Postfix Variation) [Accepted]
     * Intuition
     *
     * Let's work on a simpler problem: T = 'ab'. Whenever we find some 'b' in S, we look for the most recent 'a' that occurred before it, and that forms a candidate window 'a' = S[i], ..., S[j] = 'b'.
     *
     * A weak solution to that problem would be to just search for 'a' every time we find a 'b'. With a string like 'abbb...bb' this would be inefficient. A better approach is to remember the last 'a' seen. Then when we see a 'b', we know the start of the window is where we last saw 'a', and the end of the window is where we are now.
     *
     * How can we extend this approach to say, T = 'abc'? Whenever we find some 'c' in S, such as S[k] = 'c', we can remember the most recent window that ended at 'b', let's say [i, j]. Then our candidate window (that is, the smallest possible window ending at k) would be [i, k].
     *
     * Our approach in general works this way. We add characters to T one at a time, and for every S[k] = T[-1] we always remember the length of the candidate window ending at k. We can calculate this using knowledge of the length of the previous window (so we'll need to remember the last window seen). This leads to a dynamic programming solution.
     *
     * Algorithm
     *
     * As we iterate through T[j], let's maintain cur[e] = s as the largest index such that T[:j] is a subsequence of S[s: e+1], (or -1 if impossible.) Now we want to find new, the largest indexes for T[:j+1].
     *
     * To update our knowledge as j += 1, if S[i] == T[j], then last (the largest s we have seen so far) represents a new valid window [s, i].
     *
     * In Python, we use cur and new, while in Java we use dp[j] and dp[~j] to keep track of the last two rows of our dynamic programming.
     *
     * At the end, we look at all the windows we have and choose the best.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(ST)O(ST), where S, TS,T are the lengths of S, T. We have two for-loops.
     *
     * Space Complexity: O(S)O(S), the length of dp.
     */

    class Solution {
        public String minWindow(String S, String T) {
            int[][] dp = new int[2][S.length()];

            for (int i = 0; i < S.length(); ++i)
                dp[0][i] = S.charAt(i) == T.charAt(0) ? i : -1;

        /*At time j when considering T[:j+1],
          the smallest window [s, e] where S[e] == T[j]
          is represented by dp[j & 1][e] = s, and the
          previous information of the smallest window
          [s, e] where S[e] == T[j-1] is stored as
          dp[~j & 1][e] = s.
        */
            for (int j = 1; j < T.length(); ++j) {
                int last = -1;
                Arrays.fill(dp[j & 1], -1);
                //Now we would like to calculate the candidate windows
                //"dp[j & 1]" for T[:j+1].  'last' is the last window seen.
                for (int i = 0; i < S.length(); ++i) {
                    if (last >= 0 && S.charAt(i) == T.charAt(j))
                        dp[j & 1][i] = last;
                    if (dp[~j & 1][i] >= 0)
                        last = dp[~j & 1][i];
                }
            }

            //Looking at the window data dp[~T.length & 1],
            //choose the smallest length window [s, e].
            int start = 0, end = S.length();
            for (int e = 0; e < S.length(); ++e) {
                int s = dp[~T.length() & 1][e];
                if (s >= 0 && e - s < end - start) {
                    start = s;
                    end = e;
                }
            }
            return end < S.length() ? S.substring(start, end+1) : "";
        }
    }

     /** Approach #2: Dynamic Programming (Next Array Variation) [Accepted]
     * Intuition
     *
     * Let's guess that the minimum window will start at S[i]. We can assume that S[i] = T[0]. Then, we should find the next occurrence of T[1] in S[i+1:], say at S[j]. Then, we should find the next occurrence of T[2] in S[j+1:], and so on.
     *
     * Finding the next occurrence can be precomputed in linear time so that each guess becomes O(T)O(T) work.
     *
     * Algorithm
     *
     * We can precompute (for each i and letter), next[i][letter]: the index of the first occurrence of letter in S[i:], or -1 if it is not found.
     *
     * Then, we'll maintain a set of minimum windows for T[:j] as j increases. At the end, we'll take the best minimum window.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(ST)O(ST), where S, TS,T are the lengths of S, T, and assuming a fixed-sized alphabet. The precomputation of nxt is O(S)O(S), and the other work happens in two for-loops.
     *
     * Space Complexity: O(S)O(S), the size of windows.
     */

     class Solution {
         public String minWindow(String S, String T) {
             int N = S.length();
             int[] last = new int[26];
             int[][] nxt = new int[N][26];
             Arrays.fill(last, -1);

             for (int i = N - 1; i >= 0; --i) {
                 last[S.charAt(i) - 'a'] = i;
                 for (int k = 0; k < 26; ++k) {
                     nxt[i][k] = last[k];
                 }
             }

             List<int[]> windows = new ArrayList();
             for (int i = 0; i < N; ++i) {
                 if (S.charAt(i) == T.charAt(0))
                     windows.add(new int[]{i, i});
             }
             for (int j = 1; j < T.length(); ++j) {
                 int letterIndex = T.charAt(j) - 'a';
                 for (int[] window: windows) {
                     if (window[1] < N-1 && nxt[window[1]+1][letterIndex] >= 0) {
                         window[1] = nxt[window[1]+1][letterIndex];
                     }
                     else {
                         window[0] = window[1] = -1;
                         break;
                     }
                 }
             }

             int[] ans = {-1, S.length()};
             for (int[] window: windows) {
                 if (window[0] == -1) break;
                 if (window[1] - window[0] < ans[1] - ans[0]) {
                     ans = window;
                 }

             }
             return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
         }
     }

    public String minWindow(String S, String T) {
        int N = S.length();
        int[] last = new int[26];
        int[][] nxt = new int[N][26];
        Arrays.fill(last, -1);

        for (int i = N - 1; i >= 0; --i) {
            last[S.charAt(i) - 'a'] = i;
            for (int k = 0; k < 26; ++k) {
                nxt[i][k] = last[k];
            }
        }

        List<int[]> windows = new ArrayList();
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == T.charAt(0))
                windows.add(new int[]{i, i});
        }
        for (int j = 1; j < T.length(); ++j) {
            int letterIndex = T.charAt(j) - 'a';
            for (int[] window: windows) {
                if (window[1] < N-1 && nxt[window[1]+1][letterIndex] >= 0) {
                    window[1] = nxt[window[1]+1][letterIndex];
                }
                else {
                    window[0] = window[1] = -1;
                    break;
                }
            }
        }

        int[] ans = {-1, S.length()};
        for (int[] window: windows) {
            if (window[0] == -1) break;
            if (window[1] - window[0] < ans[1] - ans[0]) {
                ans = window;
            }

        }
        return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
    }
}
