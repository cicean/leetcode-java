import java.util.*;

/**
 * 507. Perfect Number
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

 Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
 Example:
 Input: 28
 Output: True
 Explanation: 28 = 1 + 2 + 4 + 7 + 14
 Note: The input number n will not exceed 100,000,000. (1e8)
 */

public class PerfectNumber {

  public class Solution {
    public boolean checkPerfectNumber(int num) {
      if (num == 1) return false;

      int sum = 0;
      for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
          sum += i;
          if (i != num / i) sum += num / i;
        }
      }
      sum++;

      return sum == num;
    }
  }

  //Update Enlightened by discussion below by @StefanPochmann and @jdrogin,
  // in the given range we don't need to test if (i != num / i) before add num / i to sum.
  public class Solution2 {
    public boolean checkPerfectNumber(int num) {
      if (num == 1) return false;

      int sum = 0;
      for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
          sum += i + num / i;
        }
      }
      sum++;

      return sum == num;
    }
  }

}
