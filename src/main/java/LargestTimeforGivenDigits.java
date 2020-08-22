/**
 * 949. Largest Time for Given Digits
 * Easy
 *
 * 153
 *
 * 331
 *
 * Add to List
 *
 * Share
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 *
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 *
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4]
 * Output: "23:41"
 * Example 2:
 *
 * Input: [5,5,5,5]
 * Output: ""
 *
 *
 * Note:
 *
 * A.length == 4
 * 0 <= A[i] <= 9
 * Accepted
 * 15,389
 * Submissions
 * 42,459
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * Survivor_11
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 */
public class LargestTimeforGivenDigits {
    /**
     * Solution
     * Approach 1: Brute Force
     * Intuition
     *
     * Try all possible times, and remember the largest one.
     *
     * Algorithm (Java)
     *
     * Iterate over all permutations (i, j, k, l) of (0, 1, 2, 3). For each permutation, we can try the time A[i]A[j] : A[k]A[l].
     *
     * This is a valid time if and only if the number of hours 10*A[i] + A[j] is less than 24; and the number of minutes 10*A[k] + A[l] is less than 60.
     *
     * We will output the largest valid time.
     *
     * Algorithm (Python)
     *
     * For each possible ordering of the 4 digits, if it's a legal time and the time is greater than the one we have stored, update the answer.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(1)O(1).
     *
     * Space Complexity: O(1)O(1).
     */

    // Solution inspired by @rock
    class Solution {
        public String largestTimeFromDigits(int[] A) {
            int ans = -1;

            // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 4; ++j) if (j != i)
                    for (int k = 0; k < 4; ++k) if (k != i && k != j) {
                        int l = 6 - i - j - k;

                        // For each permutation of A[i], read out the time and
                        // record the largest legal time.
                        int hours = 10 * A[i] + A[j];
                        int mins = 10 * A[k] + A[l];
                        if (hours < 24 && mins < 60)
                            ans = Math.max(ans, hours * 60 + mins);
                    }

            return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
        }
    }

    class Solution {
        public String largestTimeFromDigits(int[] A) {
            int hour = -1;
            int minute = -1;
            for(int i = 0; i < 4; ++i){
                for(int j = 0; j < 4; ++j){
                    if(i == j) continue;
                    for(int k = 0; k < 4; ++k){
                        if(k == i || k == j) continue;
                        int l = 6 - i - j - k;

                        int h = (A[i]*10+A[j]);
                        int m = (A[k]*10 + A[l]);
                        if(h > 23 || m > 59) continue;
                        if(h > hour || (h == hour && m > minute)){
                            hour = h;
                            minute = m;
                        }
                    }
                }
            }

            if(hour == -1) return "";  ;
            return new String(new char[]{(char) (hour/10 + 48), (char) (hour%10 + 48), ':', (char) (minute/10 + 48), (char) (minute%10 + 48)});
        }
    }

}
