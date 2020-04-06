/**
 * 1320. Minimum Distance to Type a Word Using Two Fingers
 * Hard
 *
 * 191
 *
 * 6
 *
 * Add to List
 *
 * Share
 *
 *
 * You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
 *
 * Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 *
 * Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "CAKE"
 * Output: 3
 * Explanation:
 * Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'C' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 * Example 2:
 *
 * Input: word = "HAPPY"
 * Output: 6
 * Explanation:
 * Using two fingers, one optimal way to type "HAPPY" is:
 * Finger 1 on letter 'H' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
 * Finger 2 on letter 'P' -> cost = 0
 * Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
 * Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
 * Total distance = 6
 * Example 3:
 *
 * Input: word = "NEW"
 * Output: 3
 * Example 4:
 *
 * Input: word = "YEAR"
 * Output: 7
 *
 *
 * Constraints:
 *
 * 2 <= word.length <= 300
 * Each word[i] is an English uppercase letter.
 * Accepted
 * 5,445
 * Submissions
 * 9,685
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
 * 4
 * Use dynamic programming.
 * dp[i][j][k]: smallest movements when you have one finger on i-th char and the other one on j-th char already having written k first characters from word.
 */
public class MinimumDistancetoTypeaWordUsingTwoFingers {

    /**
     * Solution 2: 1D DP
     * 3 dimensions is absolutely super easy to understand.
     * Though for me is not easy to write (hate to brackets).
     *
     * 2 dimension dynamic programming is a good optimisation and not hard to come up with.
     * By 2 dimension, I mean to recorde the positions of both fingers.
     *
     * But either 2D or 3D, We actually don't really need at all.
     * We only need to record the position of the left finger.
     *
     * One important observation is that,
     * out right finger will always stay at A[i - 1] after the last move.
     *
     * This is key idea that I want to express in this solution.
     *
     *
     * Explanation
     * Imagine that we tap all letters with only one finger.
     * The res distance we get is the maximum distance we will need.
     *
     * In our dynamic programming, dp[a] means that,
     * if our left finger ends at character a,
     * the maximum we can save is dp[a].
     *
     * Now our right finger tapped all letters, and left finger did nothing.
     * We iterate through the whole string one by one
     * and select some letter to tap with the left finger.
     * By doing this, we want to find out the maximum distance that we can save from the tapping with one finger.
     *
     * Assume that our left finger is at a now,
     * our right finger is at b,
     * and we the right finger will tap c next.
     *
     * Instead of moving right finger from b to c with distance d(b, c),
     * we try moving left finger from a to c with distance d(a, c).
     * Hopely this will save d(b, c) - d(a, c).
     *
     * And finaly, we have one fingers at b and the other at c now.
     * The finger at b will be new left finger, and the other will be the rihgt.
     */
    class Solution {
        public int minimumDistance(String word) {
            int dp[] = new int[26], res = 0, save = 0, n = word.length();
            for (int i = 0; i < n - 1; ++i) {
                int b = word.charAt(i) - 'A', c = word.charAt(i + 1) - 'A';
                for (int a = 0; a < 26; ++a)
                    dp[b] = Math.max(dp[b], dp[a] + d(b, c) - d(a, c));
                save = Math.max(save, dp[b]);
                res += d(b, c);
            }
            return res - save;

        }

        private int d(int a, int b) {
            return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
        }
    }

}
