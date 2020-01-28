/**
 * 1318. Minimum Flips to Make a OR b Equal to c
 * Medium
 *
 * 25
 *
 * 4
 *
 * Add to List
 *
 * Share
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: a = 2, b = 6, c = 5
 * Output: 3
 * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 * Example 2:
 *
 * Input: a = 4, b = 2, c = 7
 * Output: 1
 * Example 3:
 *
 * Input: a = 1, b = 2, c = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= a <= 10^9
 * 1 <= b <= 10^9
 * 1 <= c <= 10^9
 * Accepted
 * 4,327
 * Submissions
 * 7,445
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * muven89
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * LeetCode
 * Check the bits one by one whether they need to be flipped.
 */


public class MinimumFlipstoMakeaORbEqualtoc {

    /**
     * if (a | b) ^ c is 0, a | b and c are equal, otherwise not equal and we need to check them bit by bit;
     * For ith bit of (a | b) ^ c, use 1 << i as mask to do & operation to check if the bit is 0;
     * if not, ith bits of a | b and c are not same and we need at least 1 flip; there are 3 cases:
     * i) the ith bit of a | b less than that of c; then ith bit of a | b must be 0,
     * we only need to flip the ith bit of either a or b;
     * ii) the ith bit of a | b bigger than that of c; then ith bit of a | b must be 1,
     * but if only one of a or b's ith bit is 1, we only need to flip one of them;
     * iii) Other case, we need to flip both set bit of a and b, hence need 2 flips.
     * In short, if ith bits of a | b and c are not same, then only if ith bits of a and b are both 1 and that of c is 0,
     * we need 2 flips; otherwise only 1 flip needed.
     *
     * Time: O(L), space: O(1), where L is the number of bits in an integer.
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips(int a, int b, int c) {
        int ans = 0, ab = a | b, equal = ab ^ c;
        for (int i = 0; i < 31; ++i) {
            int mask = 1 << i;
            if ((equal & mask) > 0)  // ith bits of a | b and c are not same, need at least 1 flip.
                // ans += (ab & mask) < (c & mask) || (a & mask) != (b & mask) ? 1 : 2;
                ans += (a & mask) == (b & mask) && (c & mask) == 0 ? 2 : 1; // ith bits of a and b are both 1 and that of c is 0?
        }
        return ans;
    }
}
