/**
 * 1131. Maximum of Absolute Value Expression
 * Medium
 *
 * 123
 *
 * 139
 *
 * Add to List
 *
 * Share
 * Given two arrays of integers with equal lengths, return the maximum value of:
 *
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 *
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * Output: 13
 * Example 2:
 *
 * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * Output: 20
 *
 *
 * Constraints:
 *
 * 2 <= arr1.length == arr2.length <= 40000
 * -10^6 <= arr1[i], arr2[i] <= 10^6
 * Accepted
 * 6,365
 * Submissions
 * 11,940
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * piyushbhatia993
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * Use the idea that abs(A) + abs(B) = max(A+B, A-B, -A+B, -A-B).
 */
public class MaximumofAbsoluteValueExpression {

    class Solution {
        public int maxAbsValExpr(int[] x, int[] y) {
            int res = 0, n = x.length, P[] = {-1,1};
            for (int p : P) {
                for (int q : P) {
                    int closest = p * x[0] + q * y[0] + 0;
                    for (int i = 1; i < n; ++i) {
                        int cur = p * x[i] + q * y[i] + i;
                        res = Math.max(res, cur - closest);
                        closest = Math.min(closest, cur);
                    }
                }
            }
            return res;
        }
    }

}
