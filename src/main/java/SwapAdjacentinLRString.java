/**
 * 777. Swap Adjacent in LR String
 * Medium
 *
 * 340
 *
 * 288
 *
 * Add to List
 *
 * Share
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: True
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 *
 * Constraints:
 *
 * 1 <= len(start) == len(end) <= 10000.
 * Both start and end will only consist of characters in {'L', 'R', 'X'}.
 * Accepted
 * 22,811
 * Submissions
 * 65,823
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 *
 * ByteDance
 * |
 * 2
 * Think of the L and R's as people on a horizontal line, where X is a space.
 * The people can't cross each other, and also you can't go from XRX to RXX.
 */
public class SwapAdjacentinLRString {
    /**
     * Approach #1: Invariant [Accepted]
     * Intuition
     *
     * Let's think of 'L' and 'R' as people facing left and right standing on one line, and 'X' as an empty space on that line.
     *
     * We can ask: what invariants (conditions that remain true after making any move) there are. This is natural for any question that involves transforming some state and asking whether a final state is possible.
     *
     * Algorithm
     *
     * One invariant is that 'L' and 'R' characters in the string can never cross each other - people walking on the line cannot pass through each other. That means the starting and target strings must be the same when reading only the 'L' and 'R's. With respect to some starting string, let's call such a target string "solid" (since the people don't pass through each other).
     *
     * Additionally, the n-th 'L' can never go to the right of it's original position, and similarly the n-th 'R' can never go to the left of it's original position. We'll call this "accessibility". Formally, if (i_1, i_2, \cdots )(i
     * 1
     * ​
     *  ,i
     * 2
     * ​
     *  ,⋯) and (i^{'}_1, i^{'}_2, \cdots )(i
     * 1
     * ′
     *
     * ​
     *  ,i
     * 2
     * ′
     *
     * ​
     *  ,⋯) are the positions of each 'L' character in the source and target string, and similarly for (j_i \cdots ), (j^{'}_1 \cdots )(j
     * i
     * ​
     *  ⋯),(j
     * 1
     * ′
     *
     * ​
     *  ⋯) and positions of 'R' characters, then we will say a target string is accessible if i_k \geq i^{'}_ki
     * k
     * ​
     *  ≥i
     * k
     * ′
     *
     * ​
     *   and j_k \leq j^{'}_kj
     * k
     * ​
     *  ≤j
     * k
     * ′
     *
     * ​
     *  .
     *
     * This shows being solid and accessible are necessary conditions. With an induction on the number of people, we can show that they are sufficient conditions. The basic idea of the proof is this: A person on the end either walks away from the others, or walks towards. If they walk away, then let them walk first and it is true; if they walk towards, then let them walk last and it is true (by virtue of the target being solid).
     *
     * With these ideas in hand, we'll investigate the two properties individually. If they are both true, then the answer is True, otherwise it is False.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the length of start and end.
     *
     * Space Complexity: O(N)O(N). The replacement operation is O(N)O(N), while the remaining operations use O(1)O(1) additional space. We could amend the replace part of our algorithm to use pointers so as to reduce the total complexity to O(1)O(1).
     */
    class Solution {
        public boolean canTransform(String start, String end) {
            if (!start.replace("X", "").equals(end.replace("X", "")))
                return false;

            int t = 0;
            for (int i = 0; i < start.length(); ++i)
                if (start.charAt(i) == 'L') {
                    while (end.charAt(t) != 'L') t++;
                    if (i < t++) return false;
                }

            t = 0;
            for (int i = 0; i < start.length(); ++i)
                if (start.charAt(i) == 'R') {
                    while (end.charAt(t) != 'R') t++;
                    if (i > t++) return false;
                }

            return true;
        }
    }
     /** Approach #2: Two Pointers [Accepted]
     * Intuition and Algorithm
     *
     * Following the explanation in Approach #1, the target string must be solid and accessible.
     *
     * We use two pointers to solve it. Each pointer i, j points to an index of start, end with start[i] != 'X', end[j] != 'X'.
     *
     * Then, if start[i] != end[j], the target string isn't solid. Also, if start[i] == 'L' and i < j, (or start[i] == 'R' and i > j), the string is not accessible.
     *
     * In our Python implementation, we use generators to handle moving i, j to the next index where start[i] != 'X', end[j] != 'X'.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the length of start and end.
     *
     * Space Complexity: O(1)O(1).
     */
     class Solution {
         public boolean canTransform(String start, String end) {
             int N = start.length();
             char[] S = start.toCharArray(), T = end.toCharArray();
             int i = -1, j = -1;
             while (++i < N && ++j < N) {
                 while (i < N && S[i] == 'X') i++;
                 while (j < N && T[j] == 'X') j++;
            /* At this point, i == N or S[i] != 'X',
               and j == N or T[j] != 'X'.  i and j
               are the indices representing the next
               occurrences of non-X characters in S and T.
            */

                 // If only one of i < N and j < N, then it isn't solid-
                 // there's more people in one of the strings.
                 if ((i < N) ^ (j < N)) return false;

                 if (i < N && j < N) {
                     // If the person isn't the same, it isn't solid.
                     // Or, if the person moved backwards, it isn't accessible.
                     if (S[i] != T[j] || (S[i] == 'L' && i < j) ||
                             (S[i] == 'R' && i > j) )
                         return false;
                 }
             }
             return true;
         }
     }

    class Solution {
        public boolean canTransform(String start, String end) {
            char[] s = start.toCharArray(), t = end.toCharArray();
            if (s.length != t.length) return false;
            int R = 0, L = 0; // need R from end
            for (int i=0; i<s.length; i++) {
                if (s[i] == 'R') {
                    if (L > 0)
                        return false;
                    R++;
                } else if (s[i] == 'L') {
                    L--;
                }

                if (t[i] == 'R') R--;
                else if (t[i] == 'L') {
                    if (R > 0)
                        return false;
                    L++;
                }

                if (L > 0 && R > 0 || L < 0 || R < 0) return false;
            }
            return R == 0 && L == 0;
        }
    }
}
