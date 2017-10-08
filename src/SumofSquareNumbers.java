/**
 * 633. Sum of Square Numbers
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

 Example 1:
 Input: 5
 Output: True
 Explanation: 1 * 1 + 2 * 2 = 5
 Example 2:
 Input: 3
 Output: False

 */

public class SumofSquareNumbers {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]

   The simplest solution would be to consider every possible combination of integers aa and bb and check if the sum of their squares equals cc. Now, both aa and bb can lie within the range (0,\sqrt{c})(0,√
   ​c
   ​
   ​​ ). Thus, we need to check for the values of aa and bb in this range only.

   Complexity Analysis

   Time complexity : O(c)O(c). Two loops upto \sqrt{c}√
   ​c
   ​
   ​​ . Here, cc refers to the given integer(sum of squares).

   Space complexity : O(1)O(1). Constant extra space is used.

   */

  public class Solution {
    public boolean judgeSquareSum(int c) {
      for (long a = 0; a * a <= c; a++) {
        for (long b = 0; b * b <= c; b++) {
          if (a * a + b * b == c)
            return true;
        }
      }
      return false;
    }
  }

  /**
   * Approach #2 Better Brute Force [Time Limit Exceeded]

   We can improve the last solution, if we make the following observation. For any particular aa chosen, the value of bb required to satisfy the equation a^2 + b^2 = ca
   ​2
   ​​ +b
   ​2
   ​​ =c will be such that b^2 = c - a^2b
   ​2
   ​​ =c−a
   ​2
   ​​ . Thus, we need to traverse over the range (0, \sqrt{c})(0,√
   ​c
   ​
   ​​ ) only for considering the various values of aa. For every current value of aa chosen, we can determine the corresponding b^2b
   ​2
   ​​  value and check if it is a perfect square or not. If it happens to be a perfect square, cc is a sum of squares of two integers, otherwise not.

   Now, to determine, if the number c - a^2c−a
   ​2
   ​​  is a perfect square or not, we can make use of the following theorem: "The square of n^{th}n
   ​th
   ​​  positive integer can be represented as a sum of first nn odd positive integers." Or in mathematical terms:

   n^2 = 1 + 3 + 5 + ... + (2*n-1) = \sum_{1}^{n} (2*i - 1)n
   ​2
   ​​ =1+3+5+...+(2∗n−1)=∑
   ​1
   ​n
   ​​ (2∗i−1).

   To look at the proof of this statement, look at the L.H.S. of the above statement.

   1 + 3 + 5 + ... + (2*n-1)=1+3+5+...+(2∗n−1)=

   (2*1-1) + (2*2-1) + (2*3-1) + ... + (2*n-1)=(2∗1−1)+(2∗2−1)+(2∗3−1)+...+(2∗n−1)=

   2*(1+2+3+....+n) - (1+1+...n times)=2∗(1+2+3+....+n)−(1+1+...ntimes)=

   2*n*(n+1)/2 - n=2∗n∗(n+1)/2−n=

   n*(n+1) - n=n∗(n+1)−n=

   n^2 + n - n = n^2n
   ​2
   ​​ +n−n=n
   ​2
   ​​

   This completes the proof of the above statement.

   Complexity Analysis

   Time complexity : O(c)O(c). The total number of times the sumsum is updated is: 1+2+3+...(\sqrt{c} times) = \sqrt{c}(\sqrt{c}+1)/2 = O(c)1+2+3+...(√
   ​c
   ​
   ​​ times)=√
   ​c
   ​
   ​​ (√
   ​c
   ​
   ​​ +1)/2=O(c).

   Space complexity : O(1)O(1). Constant extra space is used.

   */

  public class Solutionq {
    public boolean judgeSquareSum(int c) {
      for (long a = 0; a * a <= c; a++) {
        int b =  c - (int)(a * a);
        int i = 1, sum = 0;
        while (sum < b) {
          sum += i;
          i += 2;
        }
        if (sum == b)
          return true;
      }
      return false;
    }
  }

  /**
   * Approach #3 Using sqrt function [Accepted]

   Algorithm

   Instead of finding if c - a^2c−a
   ​2
   ​​  is a perfect square using sum of odd numbers, as done in the last approach, we can make use of the inbuilt sqrtsqrt function and check if \sqrt{c - a^2}√
   ​c−a
   ​2
   ​​
   ​
   ​​  turns out to be an integer. If it happens for any value of aa in the range [0, \sqrt{c}][0,√
   ​c
   ​
   ​​ ], we can return a True value immediately.

   Java

   Complexity Analysis

   Time complexity : O\big(\sqrt{c}log(c)\big)O(√
   ​c
   ​
   ​​ log(c)). We iterate over \sqrt{c}√
   ​c
   ​
   ​​  values for choosing aa. For every aa chosen, finding square root of c - a^2c−a
   ​2
   ​​  takes O\big(log(c)\big)O(log(c)) time in the worst case.

   Space complexity : O(1)O(1). Constant extra space is used.
   */

  public class Solution3 {
    public boolean judgeSquareSum(int c) {
      for (long a = 0; a * a <= c; a++) {
        double b = Math.sqrt(c - a * a);
        if (b == (int) b)
          return true;
      }
      return false;
    }
  }

  /**
   * Approach #4 Using Binary Search [Accepted]

   Algorithm

   Another method to check if c - a^2c−a
   ​2
   ​​  is a perfect square, is by making use of Binary Search. The method remains same as that of a typical Binary Search to find a number. The only difference lies in that we need to find an integer, midmid in the range [0, c - a^2][0,c−a
   ​2
   ​​ ], such that this number is the square root of c - a^2c−a
   ​2
   ​​ . Or in other words, we need to find an integer, midmid, in the range [0, c - a^2][0,c−a
   ​2
   ​​ ], such that midmidxmid = c - a^2mid=c−a
   ​2
   ​​ .

   The following animation illustrates the search process for a particular value of c - a^2 = 36c−a
   ​2
   ​​ =36.

   Complexity Analysis

   Time complexity : O\big(\sqrt{c}log(c)\big)O(√
   ​c
   ​
   ​​ log(c)). Binary search taking O\big(log(c)\big)O(log(c)) in the worst case is done for \sqrt{c}√
   ​c
   ​
   ​​  values of aa.

   Space complexity : O(log(c))O(log(c)). Binary Search will take O(log(c))O(log(c)) space.
   */

  public class Solution4 {
    public boolean judgeSquareSum(int c) {
      for (long a = 0; a * a <= c; a++) {
        int b = c - (int)(a * a);
        if (binary_search(0, b, b))
          return true;
      }
      return false;
    }
    public boolean binary_search(long s, long e, int n) {
      if (s > e)
        return false;
      long mid = s + (e - s) / 2;
      if (mid * mid == n)
        return true;
      if (mid * mid > n)
        return binary_search(s, mid - 1, n);
      return binary_search(mid + 1, e, n);
    }
  }

  /**
   * Approach #5 Fermat Theorem [Accepted]:

   Algorithm

   This approach is based on the following statement, which is based on Fermat's Theorem:

   "Any positive number nn is expressible as a sum of two squares if and only if the prime factorization of nn, every prime of the form (4k+3)(4k+3) occurs an even number of times."

   By making use of the above theorem, we can directly find out if the given number cc can be expressed as a sum of two squares.

   To do so we simply find all the prime factors of the given number cc, which could range from [2,\sqrt{c}][2,√
   ​c
   ​
   ​​ ] along with the count of those factors, by repeated division. If at any step, we find out that the number of occurences of any prime factor of the form (4k+3)(4k+3) occurs an odd number of times, we can return a False value.

   In case, cc itself is a prime number, it won't be divisible by any of the primes in the [2,\sqrt{c}][2,√
   ​c
   ​
   ​​ ]. Thus, we need to check if cc can be expressed in the form of 4k+34k+3. If so, we need to return a False value, indicating that this prime occurs an odd number(1) of times.

   Otherwise, we can return a True value.

   The proof of this theorem includes the knowledge of advanced mathematics and is beyond the scope of this article. However, interested reader can refer to this documentation.

   Complexity Analysis

   Time complexity : O\big(\sqrt{c}log(c)\big)O(√
   ​c
   ​
   ​​ log(c)). We find the factors of cc and their count using repeated division. We check for the factors in the range [0, \sqrt{c}][0,√
   ​c
   ​
   ​​ ]. The maximum number of times a factor can occur(repeated division can be done) is log(n)log(n)(considering 2 as the only factor, c=2^xc=2
   ​x
   ​​ . Thus, x=log(c)x=log(c)).

   Space complexity : O(1)O(1). Constant space is used.
   */


  public class Solution5 {
    public boolean judgeSquareSum(int c) {
      for (int i = 2; i * i <= c; i++) {
        int count = 0;
        if (c % i == 0) {
          while (c % i == 0) {
            count++;
            c /= i;
          }
          if (i % 4 == 3 && count % 2 != 0)
            return false;
        }
      }
      return c % 4 != 3;
    }
  }


}
