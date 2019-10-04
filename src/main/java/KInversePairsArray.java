/**
 * 629. K Inverse Pairs Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.

 We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.

 Since the answer may be very large, the answer should be modulo 109 + 7.

 Example 1:
 Input: n = 3, k = 0
 Output: 1
 Explanation:
 Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.
 Example 2:
 Input: n = 3, k = 1
 Output: 2
 Explanation:
 The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 Note:
 The integer n is in the range [1, 1000] and k is in the range [0, 1000].
 */

public class KInversePairsArray {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]

   The most naive solution is to generate every permutation of the array consisting of numbers from 11 to nn. Then, we can find out the number of inverse pairs in every array to determine if it is equal to 1. We can find out the count of permutations with the required number of inverse pairs. But, this solution is very terrible in terms of time complexity. Thus, we move on to the better approaches directly.

   Complexity Analysis

   Time complexity : O\big(n!*nlog(n)\big)O(n!∗nlog(n)). A total of n!n! permutations will be generated. We need O\big(nlog(n)\big)O(nlog(n)) time to find the number of inverse pairs in every such permutation, by making use of merge sort. Here, nn refers to the given integer nn.

   Space complexity : O(n)O(n). Each array generated during the permutations will require nn space.
   */

  /**
   * Approach #2 Using Recursion with memoization [Time Limit Exceeded]

   Before we discuss the solution, let's look at the idea behind it. Let's say, nn represents the given number defining the upper limit of the elements in the arrays being considered and kk represents the number of inverse pairs in the current array.

   Let's start with a simple example with n=4n=4, no kk is defined right now. Now, for k=0k=0, the only possible arrangement for the given array a_0a
   ​0
   ​​  will be [1,2,3,4], since all the greater elements lie after the smaller elements. Now, in order to generate an arrangement with any arbitrary kk value, we need to shift, an arbitrary number of elements(let's say xx elements) in the array a_0a
   ​0
   ​​  towards the left, with each displacement(shift) being s_1, s_2, ...., s_xs
   ​1
   ​​ ,s
   ​2
   ​​ ,....,s
   ​x
   ​​ , such that the sum of these shifts equals kk.

   To see what we mean by the above statement, let's look at the case for [1,2,4,3]. The number of inverse pairs in this array is 1. This array is obtained by shifting the number 4 by one position towards the left.

   Similarly, consider the case for [2,4,1,3]. This array can be obtained from a_0a
   ​0
   ​​  by shifting 2 by one position towards the left first and then shifting 4 by 2 positions towards the left. Thus, the total number of displacements is 3, which is equal to the number of inverse pairs in the new array.

   This rule of displacements holds true because, whenever a number is shifted yy times towards the left starting from the array a_0a
   ​0
   ​​ , after the shift, yy numbers smaller than it lie towards its right, giving a total of yy inverse pairs.

   Now, let's say, we start with the one of the arrangements a_3a
   ​3
   ​​  [2,4,1,3], with k=3k=3. Now, if we want to add a new number 5 to this array to consider an array with n=5n=5, let's say, initially, we append it to the end of a_3a
   ​3
   ​​ . Now, the new array will be [2,4,1,3,5]. Since, the largest number is added at the end, the new number 5 doesn't add any new inverse pair to the total set of inverse pairs relative to the ones in a_3a
   ​3
   ​​ (3).

   Now, all the numbers in a_3a
   ​3
   ​​  are smaller than 5. Thus, if we add 5 at a position yy steps from the right, yy smaller numbers will lie towards its right. Thus, a total of yy inverse pairs will exist with 5 being one of the elements in these pairs.

   Thus, adding 5 at yy steps from the right adds a total of yy inverse pairs to the total set of inverse pairs in a_3a
   ​3
   ​​  giving a total of 3+y3+y inverse pairs now.

   Looking at the same statement from another point of view, we can say that, if we know the number of inverse pairs(say xx) in any arbitrary array bb with some nn, we can add a new element n+1n+1 to this array bb at a position pp steps from the right, such that x+p=kx+p=k to generate an array with a total of kk inverse pairs.

   Extending this idea further, suppose we know the number of arrangements of an array with n-1n−1 elements, with the number of inverse pairs being 0, 1, 2,..., k0,1,2,...,k, let's say being equal to count_0, count_1, count_2,.., count_kcount
   ​0
   ​​ ,count
   ​1
   ​​ ,count
   ​2
   ​​ ,..,count
   ​k
   ​​ . Now, we can determine the number of arrangements of an array with nn elements with exactly kk inverse pairs easily.

   To generate the arrangements with exactly kk inverse pairs and nn elements, we can add the new number nn to all the arrangements with kk inverse pairs at the last position. For the arrangements with k-1k−1 inverse pairs , we can add nn at a position 1 step from the right.

   Similarly, for an element with k-ik−i number of inverse pairs, we can add this new number nn at a position ii steps from the right. Each of these updations to the arrays leads to a new arrangement, each with the number of inverse pairs being equal to kk.

   The following image shows an example of how this is done for n=5 and k=4:

   Inversions

   Thus, to obtain the number of arrangements with exactly kk inverse pairs and nn numbers will be given by count_0 + count_1 + ... + count_kcount
   ​0
   ​​ +count
   ​1
   ​​ +...+count
   ​k
   ​​ .

   From the above discussion, we can obtain the recursive formula for finding the number of arrangements with exactly kk inverse pairs as follows. Let's say count(i,j)count(i,j) represents the number of arrangements with ii elements and exactly jj inverse pairs.

   If n=0n=0, no inverse pairs exist. Thus, count(0,k)=0count(0,k)=0.

   If k=0k=0, only one arrangement is possible, which is all numbers sorted in ascending order. Thus, count(n,0)=1count(n,0)=1.

   Otherwise, count(n,k) = \sum_{i=0}^{min(k,n-1)} count(n-1, k-i)count(n,k)=∑
   ​i=0
   ​min(k,n−1)
   ​​ count(n−1,k−i).

   Note that the upper limit on the summation is \text{min}(k,n-1)min(k,n−1). This is because for i>ki>k, k-i<0k−i<0. No arrangement exists with negative number of inverse pairs. The reason for the other factor can be seen as follows.

   To generate a new arrangement adding k-ik−i new inverse pairs after adding the n^{th}n
   ​th
   ​​  number, we need to add this number at the i^{th}i
   ​th
   ​​  position from the right. For an array with size nn, only n-1n−1 maximum shifts are possible.

   We need to take the modulus at every step to keep the answer within integral limits.

   We can see that a lot of duplicate function calls are made in the normal recursive solution. We can remove this redundancy by making use of a memoization array which stores the result for any function call kInversePairs(i,j) in memo[i][j]memo[i][j]. Thus, whenver a duplicate function call is made again, we can return the result directly from this memoization array.
   This prunes the search space to a great extent.

   Complexity Analysis

   Time complexity : O(n^2*k). The function kInversePairs is called n^2 times to fill the memomemo array of size nnxkk. Each function call itself takes O(n)O(n) time.

   Space complexity : O(n)O(n). memomemo array of constant size 10011001x10011001 is used. The depth of recursion tree can go upto nn.

   */

  public class Solution2 {
    Integer[][] memo = new Integer[1001][1001];
    public int kInversePairs(int n, int k) {
      if (n == 0)
        return 0;
      if (k == 0)
        return 1;
      if (memo[n][k] != null)
        return memo[n][k];
      int inv = 0;
      for (int i = 0; i <= Math.min(k, n - 1); i++)
        inv = (inv + kInversePairs(n - 1, k - i)) % 1000000007;
      memo[n][k] = inv;
      return inv;
    }
  }

  /**
   * Approach #3 Dynamic Programming [Time Limit Exceeded]

   Algorithm

   As we've seen in the discussion above, the solution for if we know the solutions for count(n-1,0)count(n−1,0), count(n-1, 1)count(n−1,1)..., count(n-1,k)count(n−1,k), we can directly obtain the solution for count(n,k)count(n,k) as count(n,k)=\sum_{0}^{min(k,n-1)} count(n-1, k-i)count(n,k)=∑
   ​0
   ​min(k,n−1)
   ​​ count(n−1,k−i).

   From this, we deduce that we can make use of Dynamic Programming to solve the given problem. To solve the given problem, we make use of a 2-D dpdp, where dp[i][j]dp[i][j] is used to store the number of arrangements with ii elements and exactly jj inverse pairs. Based on the discussions above, the dpdp updation equations become:

   If n=0n=0, no inverse pairs exist. Thus, dp[0][k]=0dp[0][k]=0.

   If k=0k=0, only one arrangement is possible, which is all numbers sorted in ascending order. Thus, dp[n][0]=1dp[n][0]=1.

   Otherwise, dp[i,j] = \sum_{p=0}^{min(j,i-1)} count(i-1, j-p)dp[i,j]=∑
   ​p=0
   ​min(j,i−1)
   ​​ count(i−1,j−p).

   Again, the limit \text{min}(j, i-1)min(j,i−1) is used to account for the cases where the number of inverse pairs needed becomes negative(p>jp>j) or the case where the new inverse pairs needed by adding the n^{th}n
   ​th
   ​​  number is more than n-1n−1 which isn't possible, since the new number can be added at (n-1)^{th}(n−1)
   ​th
   ​​  position at most from the right.

   We start filling the dpdp in a row-wise order starting from the first row. At the end, the value of dp[n][k]dp[n][k] gives the required result.

   The following animation shows how the dpdp is filled for n=4 and k=5:

   Complexity Analysis

   Time complexity : O(n^2*k). dpdp of size nnxkk is filled once. Filling each dpdp entry takes O(n)O(n) time.

   Space complexity : O(n*k)O(n∗k). dpdp array of size nnxkk is used.
   */

  public class Solution3 {

    public int kInversePairs(int n, int k) {
      int[][] dp = new int[n + 1][k + 1];
      for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= k; j++) {
          if (j == 0)
            dp[i][j] = 1;
          else {
            for (int p = 0; p <= Math.min(j, i - 1); p++)
              dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
          }
        }
      }
      return dp[n][k];
    }
  }

  /**
   * Approach #4 Dynamic Programming with Cumulative Sum[Accepted]:

   Algorithm

   From the last approach, we've observed that we need to traverse back to some limit in the previous row of the dpdp array to fill in the current dpdp entry. Instead of doing this traversal to find the sum of the required elements, we can ease the process if we fill the cumulative sum upto the current element in a row in any dpdp entry, instead of the actual value.

   Thus, now, dp[i][j]=count(i,j)+\sum_{k=0}^{j-1} dp[i][k]dp[i][j]=count(i,j)+∑
   ​k=0
   ​j−1
   ​​ dp[i][k]. Here, count(i,j)count(i,j) refers to the number of arrangements with ii elements and exactly jj inverse pairs. Thus, each entry contains the sum of all the previous elements in the same row along with its own result.

   Now, we need to determine the value of count(i,j)count(i,j) to be added to the sum of previous elements in a row, in order to update the dp[i][j]dp[i][j] entry. But, we need not traverse back in the previous row , since it contains entries representing the cumulative sums now. Thus, to obtain the sum of elements from dp[i-1][j-i+1]dp[i−1][j−i+1] to dp[i-1][j]dp[i−1][j](including both), we can directly use dp[i-1][j] - dp[i-1][j-i]dp[i−1][j]−dp[i−1][j−i].

   Now, to reflect the condition \text{min}(j, i-1)min(j,i−1) used in the previous approaches, we can note that, we need to take the sum of only ii elements in the previous row, if ii elements exist till we reach the end of the array while traversing backwards. Otherwise, we simply take the sum of all the elements.

   Only ii elements are considered because for generating jj new inverse pairs, by adding ii as the new number at the j^{th}j
   ​th
   ​​  position, jj could reach only upto i-1i−1, as discussed in the last approaches as well. Thus, we need to consider the sum of elements from dp[i-1][j-(i-1)]dp[i−1][j−(i−1)] to dp[i-1][j]dp[i−1][j](including both) using dp[i-1][j] - dp[i-1][j-i]dp[i−1][j]−dp[i−1][j−i] if j−i≥0j−i≥0.

   Otherwise, we add all the elements of the previous row upto the current column jj being considered. In other words, we can use dp[i-1][j]dp[i−1][j] directly as the required sum.

   At the end, while returning the result, we need to return dp[n][k]-dp[n][k-1]dp[n][k]−dp[n][k−1] to obtain the required result from the cumulative sums.

   The following animation illustrates the process of filling the dpdp array.

   Complexity Analysis

   Time complexity : O(n*k)O(n∗k). dpdp array of size nnxkk is filled once.

   Space complexity : O(n*k)O(n∗k). dpdp array of size nnxkk is used.


   */

  public class Solution4 {
    public int kInversePairs(int n, int k) {
      int[][] dp = new int[n + 1][k + 1];
      int M = 1000000007;
      for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= k; j++) {
          if (j == 0)
            dp[i][j] = 1;
          else {
            int val = (dp[i - 1][j] + M - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % M;
            dp[i][j] = (dp[i][j - 1] + val) % M;
          }
        }
      }
      return ((dp[n][k] + M - (k > 0 ? dp[n][k - 1] : 0)) % M);
    }
  }

  /**
   * Approach #5 Another Optimized Dynamic Programming Approach[Accepted]:

   Algorithm

   Another way to use the Dynamic Programming Approach could be if we can somehow directly store the required count(i,j)count(i,j) in dp[i][j]dp[i][j] entry, but still we should not need to traverse back in the previous row to find the sum of the required elements.

   To do so, we can note that for the i^{th}i
   ​th
   ​​  row, we need to add the elements from dp[i-1][j-i+1]dp[i−1][j−i+1] to dp[i-1][j]dp[i−1][j](including both) if (j-1) > 0(j−1)>0. Otherwise, we need to add all the elements from dp[i-1][0]dp[i−1][0] to dp[i-1][j]dp[i−1][j]. This has already been discussed previously.

   Now, when we go for filling in dp[i][j+1]dp[i][j+1] after filling dp[i][j]dp[i][j], we know dp[i][j]dp[i][j] already corresponds to the sum of the elements from dp[i-1][j-i+1]dp[i−1][j−i+1] to dp[i-1][j]dp[i−1][j]. But, for filling dp[i][j+1]dp[i][j+1], we require the sum of the elements from dp[i-1][(j-i+1)+1]dp[i−1][(j−i+1)+1] to dp[i-1][j+1]dp[i−1][j+1].

   We can observe that this sum only excludes dp[i-1][j-i+1]dp[i−1][j−i+1] from the previous sum(dp[i][j]dp[i][j]) and requires addition of only one new element(dp[i-1][j+1]dp[i−1][j+1]) to the to this sum. If the value j-i+1<0j−i+1<0, we need not remove any value.

   Thus, we can directly obtain dp[i][j]dp[i][j] value as dp[i][j] = dp[i-1][j] - dp[i-1][j-i] + dp[i-1][j]dp[i][j]=dp[i−1][j]−dp[i−1][j−i]+dp[i−1][j], if j−i≥0j−i≥0. Otherwise, we can use: dp[i][j] = dp[i-1][j] + dp[i-1][j]dp[i][j]=dp[i−1][j]+dp[i−1][j].

   We can also note that, since, here jj represents the number of inverse pairs that need to be currently considered, we can place another upper limit on jj as well. The maximum number of inverse pairs for any arbitrary nn occur only when the array is sorted in descending order leading to [n,n-1,....,3,2,1] as the arrangement.

   This arrangement has a total of n*(n-1)/2n∗(n−1)/2 inverse pairs. Thus, for an array with ii as the number of elements, the maximum number of inverse pairs possible is i*(i-1)/2i∗(i−1)/2 only. Thus, for fillling in the i^{th}i
   ​th
   ​​  row of dpdp, we can place this limit on jj's value.

   The following animation shows the dpdp filling process.

   Complexity Analysis

   Time complexity : O(n*k)O(n∗k). dpdp array of size (n+1)(n+1)x(k+1)(k+1) is filled once.

   Space complexity : O(n*k)O(n∗k). dpdp array of size (n+1)(n+1)x(k+1)(k+1) is used.

   */

  public class Solution5 {
    public int kInversePairs(int n, int k) {
      int[][] dp = new int[n + 1][k + 1];
      int M = 1000000007;
      for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= k && j <= i * (i - 1) / 2; j++) {
          if (i == 1 && j == 0) {
            dp[i][j] = 1;
            break;
          } else if (j == 0)
            dp[i][j] = 1;
          else {
            int val = (dp[i - 1][j] + M - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % M;
            dp[i][j] = (dp[i][j - 1] + val) % M;
          }
        }
      }
      return dp[n][k];
    }
  }

  /**
   * Approach #6 Once Again Memoization [Accepted]:

   Algorithm

   The Dynamic Programming solution discussed in Approach 5 can also be written down in the form of a recursive solution. But, again, that will include a lot of duplicate function calls. Thus, a better solution would be to use memoization to store the results of the previous function calls.
   Complexity Analysis

   Time complexity : O(n*k)O(n∗k). nnxkk entries in the memomemo array are filled once.

   Space complexity : O(1)O(1). memomemo array of constant size 10011001x10011001 is used.
   */

  public class Solution6 {
    Integer[][] memo = new Integer[1001][1001];
    int M = 1000000007;
    public int kInversePairs(int n, int k) {
      return ((inv(n, k) + M - (k > 0 ? inv(n, k - 1) : 0)) % M);
    }
    public int inv(int n, int k) {
      if (n == 0)
        return 0;
      if (k == 0)
        return 1;
      if (memo[n][k] != null)
        return memo[n][k];
      int val = (inv(n - 1, k) + M - ((k - n) >= 0 ? inv(n - 1, k - n) : 0)) % M;
      memo[n][k] = (inv(n, k - 1) + val) % M;
      return memo[n][k];
    }
  }

  /**
   * Approach #7 1-D dynamic Programmming [Accepted]:

   Algorithm

   From the Dynamic Programming solution, we can also note that we only need the values of the previous row in the dpdp array, and not any other row. Thus, instead of storing the whole 2-D dpdp in memory, we can make use of a 1-D dpdp to store the previous row's entries only. The updations can be done in a 1-D temptemp array of the same size as dpdp and dpdp can be updated using this temptemp everytime a row is finished.
   *Complexity Analysis

   Time complexity : O(n*k)O(n∗k). dpdp array of size k+1k+1 is filled n+1n+1 times.

   Space complexity : O(k)O(k). dpdp array of size (k+1)(k+1) is used.
   */

  public class Solution7 {
    public int kInversePairs(int n, int k) {
      int[] dp = new int[k + 1];
      int M = 1000000007;
      for (int i = 1; i <= n; i++) {
        int[] temp = new int[k + 1];
        temp[0] = 1;
        for (int j = 1; j <= k ; j++) {
          int val = (dp[j] + M - ((j - i) >= 0 ? dp[j - i] : 0)) % M;
          temp[j] = (temp[j - 1] + val) % M;
        }
        dp = temp;
      }
      return ((dp[k] + M - (k > 0 ? dp[k - 1] : 0)) % M);
    }
  }

}
