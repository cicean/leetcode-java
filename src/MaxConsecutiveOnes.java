import java.util.*;

/**
 * 485. Max Consecutive Ones
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary array, find the maximum number of consecutive 1s in this array.

 Example 1:
 Input: [1,1,0,1,1,1]
 Output: 3
 Explanation: The first two digits or the last three digits are consecutive 1s.
 The maximum number of consecutive 1s is 3.
 Note:

 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000
 */

public class MaxConsecutiveOnes {

  /**
   * The idea is to reset maxHere to 0 if we see 0, otherwise increase maxHere by 1
   The max of all maxHere is the solution

   110111
   ^ maxHere = 1

   110111
   .^ maxHere = 2

   110111
   ..^ maxHere = 0

   110111
   ...^ maxHere = 1

   110111
   ....^ maxHere = 2

   110111
   .....^ maxHere = 3
   We can also solve this problem by setting k = 0 of Max Consecutive Ones II
   * @param nums
   * @return
   */

  public int findMaxConsecutiveOnes(int[] nums) {
    int maxHere = 0, max = 0;
    for (int n : nums)
      max = Math.max(max, maxHere = n == 0 ? 0 : maxHere + 1);
    return max;
  }

}
