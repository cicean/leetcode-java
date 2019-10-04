import java.util.*;

/**
 * 494. Target Sum
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 Note:
 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.

 */

public class TargetSum {

  /**
   * Approach #1 Brute Force [Accepted]

   Algorithm

   The brute force approach is based on recursion. We need to try to put both the + and - symbols at every location in the given numsnums array and find out the assignments which lead to the required result SS.

   For this, we make use of a recursive function calculate(nums, i, sum, S), which returns the assignments leading to the sum SS, starting from the i^{th}i
   ​th
   ​​  index onwards, provided the sum of elements upto the i^{th}i
   ​th
   ​​  element is sumsum. This function appends a + sign and a - sign both to the element at the current index and calls itself with the updated sumsum as sum + nums[i]sum+nums[i] and sum - nums[i]sum−nums[i] repectively along with the updated current index as i+1i+1. Whenver, we reach the end of the array, we compare the sum obtained with SS. If they are equal, we increment the countcount value to be returned.

   Thus, the function call calculate(nums, 0, 0, S) retuns the required no. of assignments.
   Complexity Analysis

   Time complexity : O(2^n)O(2
   ​n
   ​​ ). Size of recursion tree will be 2^n2
   ​n
   ​​ . nn refers to the size of numsnums array.

   Space complexity : O(n)O(n). The depth of the recursion tree can go upto nn.
   */

  public class Solution {
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
      calculate(nums, 0, 0, S);
      return count;
    }
    public void calculate(int[] nums, int i, int sum, int S) {
      if (i == nums.length) {
        if (sum == S)
          count++;
      } else {
        calculate(nums, i + 1, sum + nums[i], S);
        calculate(nums, i + 1, sum - nums[i], S);
      }
    }
  }

  /**
   * Approach #2 Recursion with memoization [Accepted]

   Algorithm

   It can be easily observed that in the last approach, a lot of redundant function calls could be
   made with the same value of ii as the current index and the same value of sumsum as the current sum,
   since the same values could be obtained through multiple paths in the recursion tree. In order
   to remove this redundancy, we make use of memoization as well to store the results which have
   been calculated earlier.

   Thus, for every call to calculate(nums, i, sum, S), we store the result obtained in
   memo[i][sum + 1000]memo[i][sum+1000]. The factor of 1000 has been added as an offset to the
   sumsum value to map all the sumsums possible to positive integer range. By making use of memoization,
   we can prune the search space to a good extent.

   Complexity Analysis

   Time complexity : O(l*n)O(l∗n). The memomemo array of size l*nl∗n has been filled just once.
   Here, ll refers to the range of sumsum and nn refers to the size of numsnums array.

   Space complexity : O(n)O(n). The depth of recursion tree can go upto nn.
   */

  public class Solution2 {
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
      int[][] memo = new int[nums.length][2001];
      for (int[] row: memo)
        Arrays.fill(row, Integer.MIN_VALUE);
      return calculate(nums, 0, 0, S, memo);
    }
    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
      if (i == nums.length) {
        if (sum == S)
          return 1;
        else
          return 0;
      } else {
        if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
          return memo[i][sum + 1000];
        }
        int add = calculate(nums, i + 1, sum + nums[i], S, memo);
        int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
        memo[i][sum + 1000] = add + subtract;
        return memo[i][sum + 1000];
      }
    }
  }

  /**
   * Approach #3 2D Dynamic Programming [Accepted]

   Algorithm

   The idea behind this approach is as follows. Suppose we can find out the number of times a particular sum, say sum_isum
   ​i
   ​​  is possible upto a particular index, say ii, in the given numsnums array, which is given by say count_icount
   ​i
   ​​ . Now, we can find out the number of times the sum sum_i + nums[i]sum
   ​i
   ​​ +nums[i] can occur easily as count_icount
   ​i
   ​​ . Similarly, the number of times the sum sum_i - nums[i]sum
   ​i
   ​​ −nums[i] occurs is also given by count_icount
   ​i
   ​​ .

   Thus, if we know all the sums sum_jsum
   ​j
   ​​ 's which are possible upto the j^{th}j
   ​th
   ​​  index by using various assignments, along with the corresponding count of assignments, count_jcount
   ​j
   ​​ , leading to the same sum, we can determine all the sums possible upto the (j+1)^{th}(j+1)
   ​th
   ​​  index along with the corresponding count of assignments leading to the new sums.

   Based on this idea, we make use of a dpdp to determine the number of assignments which can lead to the given sum. dp[i][j]dp[i][j] refers to the number of assignments which can lead to a sum of jj upto the i^{th}i
   ​th
   ​​  index. To determine the number of assignments which can lead to a sum of sum + nums[i]sum+nums[i] upto the (i+1)^{th}(i+1)
   ​th
   ​​  index, we can use dp[i][sum + nums[i]] = dp[i][sum + nums[i]] + dp[i-1][sum]dp[i][sum+nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]. Similarly, dp[i][sum - nums[i]] = dp[i][sum + nums[i]] + dp[i-1][sum]dp[i][sum−nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]. We iterate over the dpdp array in a rowwise fashion i.e. Firstly we obtain all the sums which are possible upto a particular index along with the corresponding count of assignments and then proceed for the next element(index) in the numsnums array.

   But, since the $$sum can range from -1000 to +1000, we need to add an offset of 1000 to the sum indices (column number) to map all the sums obtained to positive range only.

   At the end, the value of dp[n-1][S+1000]dp[n−1][S+1000] gives us the required number of assignments. Here, nn refers to the number of elements in the numsnums array.

   The animation below shows the way various sums are generated along with the corresponding indices. The example assumes sumsum values to lie in the range of -6 to +6 just for the purpose of illustration. This animation is inspired by @Chidong

   1 / 7

   Complexity Analysis

   Time complexity : O(l*n)O(l∗n). The entire numsnums array is travesed 2001(constant no.: ll) times. nn refers to the size of numsnums array. ll refers to the range of sumsum possible.

   Space complexity : O(l*n)O(l∗n). dpdp array of size l*nl∗n is used.
   */

  public class Solution3 {
    public int findTargetSumWays(int[] nums, int S) {
      int[][] dp = new int[nums.length][2001];
      dp[0][nums[0] + 1000] = 1;
      dp[0][-nums[0] + 1000] += 1;
      for (int i = 1; i < nums.length; i++) {
        for (int sum = -1000; sum <= 1000; sum++) {
          if (dp[i - 1][sum + 1000] > 0) {
            dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
            dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
          }
        }
      }
      return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }
  }

  /**
   * Approach #4 1D Dynamic Programming [Accepted]:

   Algorithm

   If we look closely at the last solution, we can observe that for the evaluation of the current row of dpdp, only the values of the last row of dpdp are needed. Thus, we can save some space by using a 1D DP array instead of a 2-D DP array. The only difference that needs to be made is that now the same dpdp array will be updated for every row traversed.

   Below code is inspired by @Chidong

   Complexity Analysis

   Time complexity : O(l.n)O(l.n). The entire numsnums array is traversed ll times. nn refers to the size of numsnums array. ll refers to the range of sumsum possible.

   Space complexity : O(n)O(n). dpdp array of size nn is used.
   */


  public class Solution4 {
    public int findTargetSumWays(int[] nums, int S) {
      int[] dp = new int[2001];
      dp[nums[0] + 1000] = 1;
      dp[-nums[0] + 1000] += 1;
      for (int i = 1; i < nums.length; i++) {
        int[] next = new int[2001];
        for (int sum = -1000; sum <= 1000; sum++) {
          if (dp[sum + 1000] > 0) {
            next[sum + nums[i] + 1000] += dp[sum + 1000];
            next[sum - nums[i] + 1000] += dp[sum + 1000];
          }
        }
        dp = next;
      }
      return S > 1000 ? 0 : dp[S + 1000];
    }
  }



}
