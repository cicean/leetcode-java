/**
 * 788. Rotated Digits
 * Easy
 *
 * 278
 *
 * 928
 *
 * Add to List
 *
 * Share
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
 *
 * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
 *
 * Now given a positive number N, how many numbers X from 1 to N are good?
 *
 * Example:
 * Input: 10
 * Output: 4
 * Explanation:
 * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 * Note:
 *
 * N  will be in range [1, 10000].
 * Accepted
 * 41,236
 * Submissions
 * 73,157
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
 */
public class RotatedDigits {

    /**
     * Approach #1: Brute Force [Accepted]
     * Intuition
     *
     * For each X from 1 to N, let's analyze whether X is good.
     *
     * If X has a digit that does not have a valid rotation (3, 4, or 7), then it can't be good.
     *
     * If X doesn't have a digit that rotates to a different digit (2, 5, 6, or 9), it can't be good because X will be the same after rotation.
     *
     * Otherwise, X will successfully rotate to a valid different number.
     *
     * Algorithm
     *
     * To handle checking the digits of X, we showcase two implementations. The most obvious way is to parse the string; another way is to recursively check the last digit of X.
     *
     * See the comments in each implementation for more details.
     * Complexity Analysis
     *
     * Time Complexity: O(N \log N)O(NlogN). For each X, we check each digit.
     *
     * Space Complexity: O(\log N)O(logN), the space stored either by the string, or the recursive call stack of the function good.
     */

    class Solution {
        public int rotatedDigits(int N) {
            // Count how many n in [1, N] are good.
            int ans = 0;
            for (int n = 1; n <= N; ++n)
                if (good(n, false)) ans++;
            return ans;
        }

        // Return true if n is good.
        // The flag is true iff we have an occurrence of 2, 5, 6, 9.
        public boolean good(int n, boolean flag) {
            if (n == 0) return flag;

            int d = n % 10;
            if (d == 3 || d == 4 || d == 7) return false;
            if (d == 0 || d == 1 || d == 8) return good(n / 10, flag);
            return good(n / 10, true);
        }
    }

    /**
     * Approach #2: Dynamic Programming On Digits [Accepted]
     * Intuition
     *
     * Say we are writing a good number digit by digit. The necessary and sufficient conditions are: we need to write using only digits from 0125689, write a number less than or equal to N, and write at least one digit from 2569.
     *
     * We can use dynamic programming to solve this efficiently. Our state will be how many digits i we have written, whether we have previously written a jth digit lower than the jth digit of N, and whether we have previously written a digit from 2569. We will represent this state by three variables: i, equality_flag, involution_flag.
     *
     * dp(i, equality_flag, involution_flag) will represent the number of ways to write the suffix of N corresponding to these above conditions. The answer we want is dp(0, True, False).
     *
     * Algorithm
     *
     * If equality_flag is true, the ith digit (0 indexed) will be at most the ith digit of N. For each digit d, we determine if we can write d based on the flags that are currently set.
     *
     * In the below implementations, we showcase both top-down and bottom-up approaches. The four lines in the top-down approach (Python) from for d in xrange(... to before memo[...] = ans clearly illustrates the recursive relationship between states in our dynamic programming.
     * Complexity Analysis
     *
     * Time Complexity: O(\log N)O(logN). We do constant-time work for each digit of N.
     *
     * Space Complexity: O(\log N)O(logN), the space stored by memo.
     */

    class Solution_dp {
        public int rotatedDigits(int N) {
            char[] A = String.valueOf(N).toCharArray();
            int K = A.length;

            int[][][] memo = new int[K+1][2][2];
            memo[K][0][1] = memo[K][1][1] = 1;
            for (int i = K - 1; i >= 0; --i) {
                for (int eqf = 0; eqf <= 1; ++eqf)
                    for (int invf = 0; invf <= 1; ++invf) {
                        // We will compute ans = memo[i][eqf][invf],
                        // the number of good numbers with respect to N = A[i:].
                        // If eqf is true, we must stay below N, otherwise
                        // we can use any digits.
                        // Invf becomes true when we write a 2569, and it
                        // must be true by the end of our writing as all
                        // good numbers have a digit in 2569.
                        int ans = 0;
                        for (char d = '0'; d <= (eqf == 1 ? A[i] : '9'); ++d) {
                            if (d == '3' || d == '4' || d == '7') continue;
                            boolean invo = (d == '2' || d == '5' || d == '6' || d == '9');
                            ans += memo[i+1][d == A[i] ? eqf : 0][invo ? 1 : invf];
                        }
                        memo[i][eqf][invf] = ans;
                    }
            }

            return memo[0][1][0];
        }

    }


}
