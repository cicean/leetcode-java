import java.util.*;

/**
 * 1291. Sequential Digits
 * Medium
 *
 * 109
 *
 * 11
 *
 * Add to List
 *
 * Share
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 *
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 *
 *
 *
 * Example 1:
 *
 * Input: low = 100, high = 300
 * Output: [123,234]
 * Example 2:
 *
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 *
 *
 * Constraints:
 *
 * 10 <= low <= high <= 10^9
 * Accepted
 * 7,703
 * Submissions
 * 14,868
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * F5 Networks
 * |
 * LeetCode
 * Generate all numbers with sequential digits and check if they are in the given range.
 * Fix the starting digit then do a recursion that tries to append all valid digits.
 */
public class SequentialDigits {

    /**
     * Solution
     * Approach 1: Sliding Window
     * One might notice that all integers that have sequential digits are substrings of string "123456789". Hence to generate all such integers of a given length, just move the window of that length along "123456789" string.
     *
     * The advantage of this method is that it will generate the integers that are already in the sorted order.
     *
     * diff
     *
     * Algorithm
     *
     * Initialize sample string "123456789". This string contains all integers that have sequential digits as substrings. Let's implement sliding window algorithm to generate them.
     *
     * Iterate over all possible string lengths: from the length of low to the length of high.
     *
     * For each length iterate over all possible start indexes: from 0 to 10 - length.
     *
     * Construct the number from digits inside the sliding window of current length.
     *
     * Add this number in the output list nums, if it's greater than low and less than high.
     *
     * Return nums.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity: \mathcal{O}(1)O(1). Length of sample string is 9, and lengths of low and high are between 2 and 9. Hence the nested loops are executed no more than 8 \times 8 = 648×8=64 times.
     *
     * Space complexity: \mathcal{O}(1)O(1) to keep not more than 36 integers with sequential digits.
     */
    class Solution {
        public List<Integer> sequentialDigits(int low, int high) {
            String sample = "123456789";
            int n = 10;
            List<Integer> nums = new ArrayList();

            int lowLen = String.valueOf(low).length();
            int highLen = String.valueOf(high).length();
            for (int length = lowLen; length < highLen + 1; ++length) {
                for (int start = 0; start < n - length; ++start) {
                    int num = Integer.parseInt(sample.substring(start, start + length));
                    if (num >= low && num <= high) nums.add(num);
                }
            }
            return nums;
        }
    }

     /**
     * Approach 2: Precomputation
     * Actually, there are 36 integers with the sequential digits. Here is how we calculate it.
     *
     * Starting from 9 digits in the sample string, one could construct 9 - 2 + 1 = 8 integers of length 2, 9 - 3 + 1 = 7 integers of length 3, and so on and so forth. In total, it would make 8 + 7 + ... + 1 = 36 integers.
     *
     * As one can see, we could precompute the results all at once and then select the integers that are less than high and greater than low.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity: \mathcal{O}(1)O(1) both for precomputation and during runtime. Precomputation: Length of sample string is 9, and the nested loops are executed 8 \times 8 = 648×8=64 times. Runtime: One iterates over an array of 36 integers.
     *
     * Space complexity: \mathcal{O}(1)O(1) to keep 36 integers that have sequential digits.
     */

     class Seq {
         public List<Integer> nums = new ArrayList();
         Seq() {
             String sample = "123456789";
             int n = 10;

             for (int length = 2; length < n; ++length) {
                 for (int start = 0; start < n - length; ++start) {
                     int num = Integer.parseInt(sample.substring(start, start + length));
                     nums.add(num);
                 }
             }
         }
     }

    class Solution_1 {
        public  Seq s = new Seq();
        public List<Integer> sequentialDigits(int low, int high) {
            List<Integer> output = new ArrayList();
            for (int num : s.nums) {
                if (num >= low && num <= high) output.add(num);
            }
            return output;
        }
    }

    class Solution_2 {

        public List<Integer> sequentialDigits(int low, int high) {
            List<Integer> res = new ArrayList<>();
            for (int digit = 1; digit < 9; ++digit) {
                int next = digit, n = next;
                while (n <= high && next < 10) {
                    if (n >= low) {
                        res.add(n);
                    }
                    int n1 = n * 10 + ++next;
                    if (n1 > n) {
                        n = n1;
                    }else { // Integer overflow.
                        break;
                    }
                }
            }
            Collections.sort(res);
            return res;
        }
    }

}
