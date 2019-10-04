import java.util.*;

/**
 * 645. Set Mismatch DescriptionHintsSubmissionsDiscussSolution Discuss Pick One The set S
 * originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the
 * numbers in the set got duplicated to another number in the set, which results in repetition of
 * one number and loss of another number.
 *
 * Given an array nums representing the data status of this set after the error. Your task is to
 * firstly find the number occurs twice and then find the number that is missing. Return them in the
 * form of an array.
 *
 * Example 1: Input: nums = [1,2,2,4] Output: [2,3] Note: The given array size will in the range [2,
 * 10000]. The given array's numbers won't have any order.
 *
 * Complexity Analysis
 *
 * Time complexity : O(n^2). We traverse over the numsnums array of size nn for each of the numbers
 * from 1 to n.
 *
 * Space complexity : O(1). Constant extra space is used.
 */

public class SetMismatch {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]
   *
   * The most naive solution is to consider each number from 11 to nn, and traverse over the whole
   * numsnums array to check if the current number occurs twice in numsnums or doesn't occur at all.
   * We need to set the duplicate number, dupdup and the missing number, missingmissing,
   * appropriately in such cases respectively.
   */
  public class Solution {

    public int[] findErrorNums(int[] nums) {
      int dup = -1, missing = -1;
      for (int i = 1; i <= nums.length; i++) {
        int count = 0;
        for (int j = 0; j < nums.length; j++) {
          if (nums[j] == i) {
            count++;
          }
        }
        if (count == 2) {
          dup = i;
        } else if (count == 0) {
          missing = i;
        }
      }
      return new int[]{dup, missing};
    }
  }

  /**
   * Approach #2 Better Brute Force [Time Limit Exceeded]
   *
   * In the last approach, we continued the search process, even when we've already found the
   * duplicate and the missing number. But, as per the problem statement, we know that only one
   * number will be repeated and only one number will be missing. Thus, we can optimize the last
   * approach to some extent, by stopping the search process as soon as we find these two required
   * numbers.
   *
   * Complexity Analysis
   *
   * Time complexity : O(n^2) We traverse over the nums array of size n for each of the numbers from
   * 1 to n, in the worst case.
   *
   * Space complexity : O(1). Constant extra space is used.
   */

  public class Solution_2 {

    public int[] findErrorNums(int[] nums) {
      int dup = -1, missing = -1;
      ;
      for (int i = 1; i <= nums.length; i++) {
        int count = 0;
        for (int j = 0; j < nums.length; j++) {
          if (nums[j] == i) {
            count++;
          }
        }
        if (count == 2) {
          dup = i;
        } else if (count == 0) {
          missing = i;
        }
        if (dup > 0 && missing > 0) {
          break;
        }
      }
      return new int[]{dup, missing};
    }
  }

  /**
   * Approach #3 Using Sorting [Accepted]
   *
   * Algorithm
   *
   * One way to further optimize the last approach is to sort the given numsnums array. This way,
   * the numbers which are equal will always lie together. Further, we can easily identify the
   * missing number by checking if every two consecutive elements in the sorted numsnums array are
   * just one count apart or not.
   *
   * Complexity Analysis
   *
   * Time complexity : O(nlogn). Sorting takes O(nlogn)) time.
   *
   * Space complexity : O(logn). Sorting takes O(logn) space.
   */

  public class Solution_3 {

    public int[] findErrorNums(int[] nums) {
      Arrays.sort(nums);
      int dup = -1, missing = 1;
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i - 1]) {
          dup = nums[i];
        } else if (nums[i] > nums[i - 1] + 1) {
          missing = nums[i - 1] + 1;
        }
      }
      return new int[]{dup, nums[nums.length - 1] != nums.length ? nums.length : missing};
    }
  }

  /**
   * Approach #4 Using map [Accepted]
   *
   * Algorithm
   *
   * The given problem can also be solved easily if we can somehow keep a track of the number of
   * times each element of the numsnums array occurs. One way to do so is to make an entry for each
   * element of numsnums in a HashMap mapmap. This mapmap stores the entries in the form (num_i,
   * count_i). Here, numnum refers to the i^{th} ​​ element in numsnums and count_i refers to the
   * number of times this element occurs in numsnums. Whenever, the same element occurs again, we
   * can increment the count corresponding to the same.
   *
   * After this, we can consider every number from 11 to nn, and check for its presence in mapmap.
   * If it isn't present, we can update the missingmissing variable appropriately. But, if the
   * countcount corresponding to the current number is 22, we can update the dupdup variable with
   * the current number.
   *
   * Java
   *
   * Complexity Analysis
   *
   * Time complexity : O(n). Traversing over nums of size nn takes O(n) time. Considering each
   * number from 1 to n also takes O(n) time.
   *
   * Space complexity : O(n). map can contain atmost nn entries for each of the numbers from 1 to
   * n.
   */

  public class Solution_4 {

    public int[] findErrorNums(int[] nums) {
      Map<Integer, Integer> map = new HashMap();
      int dup = -1, missing = 1;
      for (int n : nums) {
        map.put(n, map.getOrDefault(n, 0) + 1);
      }
      for (int i = 1; i <= nums.length; i++) {
        if (map.containsKey(i)) {
          if (map.get(i) == 2) {
            dup = i;
          }
        } else {
          missing = i;
        }
      }
      return new int[]{dup, missing};
    }
  }

  /**
   * Approach #5 Using Extra Array[Accepted]:
   *
   * Algorithm
   *
   * In the last approach, we make use of a mapmap to store the elements of numsnums along with
   * their corresponding counts. But, we can note, that each entry in mapmap requires two entries.
   * Thus, putting up nn entries requires 2n2n space actually. We can reduce this space required to
   * nn by making use of an array, arrarr instead. Now, the indices of arrarr can be used instead of
   * storing the elements again. Thus, we make use of arrarr in such a way that, arr[i]arr[i] is
   * used to store the number of occurences of the element i+1i+1. The rest of the process remains
   * the same as in the last approach.
   *
   * Complexity Analysis
   *
   * Time complexity : O(n)O(n). Traversing over numsnums of size nn takes O(n)O(n) time.
   * Considering each number from 11 to nn also takes O(n)O(n) time.
   *
   * Space complexity : O(n)O(n). arrarr can contain atmost nn elements for each of the numbers from
   * 11 to nn.
   */

  public class Solution_5 {

    public int[] findErrorNums(int[] nums) {
      int[] arr = new int[nums.length + 1];
      int dup = -1, missing = 1;
      for (int i = 0; i < nums.length; i++) {
        arr[nums[i]] += 1;
      }
      for (int i = 1; i < arr.length; i++) {
        if (arr[i] == 0) {
          missing = i;
        } else if (arr[i] == 2) {
          dup = i;
        }
      }
      return new int[]{dup, missing};
    }
  }


  /**
   * Approach #6 Using Constant Space[Accepted]:
   *
   * Algorithm
   *
   * We can save the space used in the last approach, if we can somehow, include the information
   * regarding the duplicacy of an element or absence of an element in the numsnums array. Let's see
   * how this can be done.
   *
   * We know that all the elements in the given numsnums array are positive, and lie in the range 11
   * to nn only. Thus, we can pick up each element ii from numsnums. For every number ii picked up,
   * we can invert the element at the index \left|i\right|∣i∣. By doing so, if one of the elements
   * jj occurs twice, when this number is encountered the second time, the element
   * nums[\left|i\right|]nums[∣i∣] will be found to be negative. Thus, while doing the inversions,
   * we can check if a number found is already negative, to find the duplicate number.
   *
   * After the inversions have been done, if all the elements in numsnums are present correctly, the
   * resultant numsnums array will have all the elements as negative now. But, if one of the
   * numbers, jj is missing, the element at the j^{th}j ​th ​​  index will be positive. This can be
   * used to determine the missing number.
   *
   * Complexity Analysis
   *
   * Time complexity : O(n)O(n). Two traversals over the numsnums array of size nn are done.
   *
   * Space complexity : O(1)O(1). Constant extra space is used.
   */

  public class Solution_6 {

    public int[] findErrorNums(int[] nums) {
      int dup = -1, missing = 1;
      for (int n : nums) {
        if (nums[Math.abs(n) - 1] < 0) {
          dup = Math.abs(n);
        } else {
          nums[Math.abs(n) - 1] *= -1;
        }
      }
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] > 0) {
          missing = i + 1;
        }
      }
      return new int[]{dup, missing};
    }
  }

  /**
   * Approach #7 Using XOR [Accepted]:
   *
   * Algorithm
   *
   * Before we dive into the solution to this problem, let's consider a simple problem. Consider an
   * array with n-1n−1 elements containing numbers from 11 to nn with one number missing out of
   * them. Now, how to we find out this missing element. One of the solutions is to take the XOR of
   * all the elements of this array with all the numbers from 11 to nn. By doing so, we get the
   * required missing number. This works because XORing a number with itself results in a 0 result.
   * Thus, only the number which is missing can't get cancelled with this XORing.
   *
   * Now, using this idea as the base, let's take it a step forward and use it for the current
   * problem. By taking the XOR of all the elements of the given numsnums array with all the numbers
   * from 11 to nn, we will get a result, xorxor, as x^yx ​y ​​ . Here, xx and yy refer to the
   * repeated and the missing term in the given numsnums array. This happens on the same grounds as
   * in the first problem discussed above.
   *
   * Now, in the resultant xorxor, we'll get a 1 in the binary representation only at those bit
   * positions which have a 1 in one out of the numbers xx and yy, and a 0 at the same bit position
   * in the other one. In the current solution, we consider the rightmost bit which is 1 in the
   * xorxor, although any bit would work. Let's say, this position is called the
   * rightmostbitrightmostbit.
   *
   * If we divide the elements of the given numsnums array into two parts such that the first set
   * contains the elements which have a 1 at the rightmostbitrightmostbit position and the second
   * set contains the elements having a 0 at the same position, we'll get one out of xx or yy in one
   * set and the other one in the second set. Now, our problem has reduced somewhat to the simple
   * problem discussed above.
   *
   * To solve this reduced problem, we can find out the elements from 11 to nn and consider them as
   * a part of the previous sets only, with the allocation of the set depending on a 1 or 0 at the
   * righmostbitrighmostbit position.
   *
   * Now, if we do the XOR of all the elements of the first set, all the elements will result in an
   * XOR of 0, due to cancellation of the similar terms in both numsnums and the numbers (1:n)(1:n),
   * except one term, which is either xx or yy.
   *
   * For the other term, we can do the XOR of all the elements in the second set as well.
   *
   * Consider the example [1 2 4 4 5 6]
   *
   * XOR
   *
   * One more traversal over the numsnums can be used to identify the missing and the repeated
   * number out of the two numbers found.
   *
   * Complexity Analysis
   *
   * Time complexity : O(n)O(n). We iterate over nn elements five times.
   *
   * Space complexity : O(1)O(1). Constant extra space is used.
   */

  public class Solution_7 {

    public int[] findErrorNums(int[] nums) {
      int xor = 0, xor0 = 0, xor1 = 0;
      for (int n : nums) {
        xor ^= n;
      }
      for (int i = 1; i <= nums.length; i++) {
        xor ^= i;
      }
      int rightmostbit = xor & ~(xor - 1);
      for (int n : nums) {
        if ((n & rightmostbit) != 0) {
          xor1 ^= n;
        } else {
          xor0 ^= n;
        }
      }
      for (int i = 1; i <= nums.length; i++) {
        if ((i & rightmostbit) != 0) {
          xor1 ^= i;
        } else {
          xor0 ^= i;
        }
      }
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] == xor0) {
          return new int[]{xor0, xor1};
        }
      }
      return new int[]{xor1, xor0};
    }
  }


}
