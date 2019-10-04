import java.util.*;

/**
 * 473. Matchsticks to Square
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

 Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

 Example 1:
 Input: [1,1,2,2,2]
 Output: true

 Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 Example 2:
 Input: [3,3,3,3,4]
 Output: false

 Explanation: You cannot find a way to form a square with all the matchsticks.
 Note:
 The length sum of the given matchsticks is in the range of 0 to 10^9.
 The length of the given matchstick array will not exceed 15.

 */

public class MatchstickstoSquare {

  /**
   * Java DFS Solution with Explanation
   According to https://en.wikipedia.org/wiki/Partition_problem, the partition problem (or number partitioning) is the task of deciding whether a given multiset S of positive integers can be partitioned into two subsets S1 and S2 such that the sum of the numbers in S1 equals the sum of the numbers in S2. The partition problem is NP-complete.

   When I trying to think how to apply dynamic programming solution of above problem to this one (difference is divid S into 4 subsets), I took another look at the constraints of the problem:
   The length sum of the given matchsticks is in the range of 0 to 10^9.
   The length of the given matchstick array will not exceed 15.

   Sounds like the input will not be very large... Then why not just do DFS? In fact, DFS solution passed judges.

   Anyone solved this problem by using DP? Please let me know :)
   */

  public class Solution {
    public boolean makesquare(int[] nums) {
      if (nums == null || nums.length < 4) return false;
      int sum = 0;
      for (int num : nums) sum += num;
      if (sum % 4 != 0) return false;

      return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sums, int index, int target) {
      if (index == nums.length) {
        if (sums[0] == target && sums[1] == target && sums[2] == target) {
          return true;
        }
        return false;
      }

      for (int i = 0; i < 4; i++) {
        if (sums[i] + nums[index] > target) continue;
        sums[i] += nums[index];
        if (dfs(nums, sums, index + 1, target)) return true;
        sums[i] -= nums[index];
      }

      return false;
    }
  }

  /**
   * Updates on 12/19/2016 Thanks @benjamin19890721 for pointing out a very good optimization: Sorting the input array DESC will make the DFS process run much faster. Reason behind this is we always try to put the next matchstick in the first subset. If there is no solution, trying a longer matchstick first will get to negative conclusion earlier. Following is the updated code. Runtime is improved from more than 1000ms to around 40ms. A big improvement.
   */

  public class Solution2 {
    public boolean makesquare(int[] nums) {
      if (nums == null || nums.length < 4) return false;
      int sum = 0;
      for (int num : nums) sum += num;
      if (sum % 4 != 0) return false;

      Arrays.sort(nums);
      reverse(nums);

      return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sums, int index, int target) {
      if (index == nums.length) {
        if (sums[0] == target && sums[1] == target && sums[2] == target) {
          return true;
        }
        return false;
      }

      for (int i = 0; i < 4; i++) {
        if (sums[i] + nums[index] > target) continue;
        sums[i] += nums[index];
        if (dfs(nums, sums, index + 1, target)) return true;
        sums[i] -= nums[index];
      }

      return false;
    }

    private void reverse(int[] nums) {
      int i = 0, j = nums.length - 1;
      while (i < j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        i++; j--;
      }
    }
  }


}
