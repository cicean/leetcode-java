import java.util.*;

/**
 * 646. Maximum Length of Pair Chain
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs
 can be formed in this fashion.

 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all
 the given pairs. You can select pairs in any order.

 Example 1:
 Input: [[1,2], [2,3], [3,4]]
 Output: 2
 Explanation: The longest chain is [1,2] -> [3,4]
 Note:
 The number of given pairs will be in the range [1, 1000].
 */

public class MaximumLengthofPairChain {

  /**
   * dp
   * @param pairs
   * @return
   */
  public int findLongestChain(int[][] pairs) {
    if (pairs == null || pairs.length == 0) return 0;
    Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
    int[] dp = new int[pairs.length];
    Arrays.fill(dp, 1);
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] = Math.max(dp[i], pairs[i][0] > pairs[j][1]? dp[j] + 1 : dp[j]);
      }
    }
    return dp[pairs.length - 1];
  }

}
