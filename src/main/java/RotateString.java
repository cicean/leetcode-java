/**
 * 796. Rotate String
 * Easy
 *
 * 518
 *
 * 41
 *
 * Add to List
 *
 * Share
 * We are given two strings, A and B.
 *
 * A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.
 *
 * Example 1:
 * Input: A = 'abcde', B = 'cdeab'
 * Output: true
 *
 * Example 2:
 * Input: A = 'abcde', B = 'abced'
 * Output: false
 * Note:
 *
 * A and B will have length at most 100.
 * Accepted
 * 53,651
 * Submissions
 * 108,604
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ngoc_lam
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * 2
 */
public class RotateString {

    /**
     * Approach #1: Brute Force [Accepted]
     * Intuition
     *
     * For each rotation of A, let's check if it equals B.
     *
     * Algorithm
     *
     * More specifically, say we rotate A by s. Then, instead of A[0], A[1], A[2], ..., we have A[s], A[s+1], A[s+2], ...;
     * and we should check that A[s] == B[0], A[s+1] == B[1], A[s+2] == B[2], etc.
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of A. For each rotation s, we check up to NN elements in A and B.
     *
     * Space Complexity: O(1)O(1). We only use pointers to elements of A and B.
     * @param A
     * @param B
     * @return
     */

    public boolean rotateString(String A, String B) {
        if (A.length() != B.length())
            return false;
        if (A.length() == 0)
            return true;

        search:
        for (int s = 0; s < A.length(); ++s) {
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt((s+i) % A.length()) != B.charAt(i))
                    continue search;
            }
            return true;
        }
        return false;
    }

    /**
     * All rotations of A are contained in A+A. Thus, we can simply check whether B is a substring of A+A.
     * We also need to check A.length == B.length, otherwise we will fail cases like A = "a", B = "aa".
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of A.
     *
     * Space Complexity: O(N)O(N), the space used building A+A.
     *
     * @param A
     * @param B
     * @return
     */
    public boolean rotateString_2(String A, String B) {

        if (A == null || B == null) {
            return false;
        }

        int count = 0;
        int alength = A.length();
        int blength = B.length();

        if (alength != blength) {
            return false;
        }
        return A.length() == B.length() && (A + A).contains(B);
    }

}