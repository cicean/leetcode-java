import java.util.*;

/**
 * 679. 24 Game
 * Hard
 *
 * 569
 *
 * 120
 *
 * Add to List
 *
 * Share
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 *
 * Example 1:
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 * Accepted
 * 31.5K
 * Submissions
 * 69.9K
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * just_not_over_12
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 8
 *
 * Microsoft
 * |
 * 4
 *
 * Facebook
 * |
 * 2
 */
public class FwentyFourGame {

    /**
     * Approach #1: Backtracking [Accepted]
     * Intuition and Algorithm
     *
     * There are only 4 cards and only 4 operations that can be performed. Even when all operations do not commute, that gives us an upper bound of 12 * 6 * 2 * 4 * 4 * 4 = 921612∗6∗2∗4∗4∗4=9216 possibilities, which makes it feasible to just try them all. Specifically, we choose two numbers (with order) in 12 ways and perform one of 4 operations (12 * 4). Then, with 3 remaining numbers, we choose 2 of them and perform one of 4 operations (6 * 4). Finally we have two numbers left and make a final choice of 2 * 4 possibilities.
     *
     * We will perform 3 binary operations (+, -, *, / are the operations) on either our numbers or resulting numbers. Because - and / do not commute, we must be careful to consider both a / b and b / a.
     *
     * For every way to remove two numbers a, b in our list, and for each possible result they can make, like a+b, a/b, etc., we will recursively solve the problem on this smaller list of numbers.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(1)O(1). There is a hard limit of 9216 possibilities, and we do O(1)O(1) work for each of them.
     *
     * Space Complexity: O(1)O(1). Our intermediate arrays are at most 4 elements, and the number made is bounded by an O(1)O(1) factor.
     *
     */
    class Solution {
        public boolean judgePoint24(int[] nums) {
            ArrayList A = new ArrayList<Double>();
            for (int v: nums) A.add((double) v);
            return solve(A);
        }
        private boolean solve(ArrayList<Double> nums) {
            if (nums.size() == 0) return false;
            if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;

            for (int i = 0; i < nums.size(); i++) {
                for (int j = 0; j < nums.size(); j++) {
                    if (i != j) {
                        ArrayList<Double> nums2 = new ArrayList<Double>();
                        for (int k = 0; k < nums.size(); k++) if (k != i && k != j) {
                            nums2.add(nums.get(k));
                        }
                        for (int k = 0; k < 4; k++) {
                            if (k < 2 && j > i) continue;
                            if (k == 0) nums2.add(nums.get(i) + nums.get(j));
                            if (k == 1) nums2.add(nums.get(i) * nums.get(j));
                            if (k == 2) nums2.add(nums.get(i) - nums.get(j));
                            if (k == 3) {
                                if (nums.get(j) != 0) {
                                    nums2.add(nums.get(i) / nums.get(j));
                                } else {
                                    continue;
                                }
                            }
                            if (solve(nums2)) return true;
                            nums2.remove(nums2.size() - 1);
                        }
                    }
                }
            }
            return false;
        }
    }

    class Solution_2 {
        public boolean judgePoint24(int[] nums) {
            double[] A = new double[4];
            for (int i = 0; i < 4; i++) {
                A[i] = nums[i];
            }
            return helper(A);
        }

        public boolean helper(double[] A) {
            int length = A.length;
            if (length == 1) return Math.abs(A[0] - 24) < 0.0001;
            for(int i = 0; i < length; i++) {
                for(int j = i + 1; j < length; j++) {
                    double[] B = new double[length - 1];
                    int id = 0;
                    for (int k = 0; k < A.length; k++) {
                        if (k != i && k != j) {
                            B[id++] = A[k];
                        }
                    }
                    for (double d : compute(A[i], A[j])) {
                        B[id] = d;
                        if (helper(B)) return true;
                    }
                }
            }
            return false;
        }

        public double[] compute(double a, double b) {
            return new double[]{a - b, b - a, a * b, a + b, a / b, b / a};
        }
    }
}

