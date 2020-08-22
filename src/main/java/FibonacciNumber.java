/**
 * 509. Fibonacci Number
 * Easy
 *
 * 502
 *
 * 185
 *
 * Add to List
 *
 * Share
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), for N > 1.
 * Given N, calculate F(N).
 *
 *
 *
 * Example 1:
 *
 * Input: 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 *
 * Input: 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 *
 * Input: 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 * Note:
 *
 * 0 ≤ N ≤ 30.
 *
 * Accepted
 * 171,851
 * Submissions
 * 256,644
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 7
 *
 * Microsoft
 * |
 * 5
 *
 * Apple
 * |
 * 5
 *
 * Barclays
 * |
 * 4
 *
 * Facebook
 * |
 * 3
 * Climbing Stairs
 * Easy
 * Split Array into Fibonacci Sequence
 * Medium
 * Length of Longest Fibonacci Subsequence
 * Medium
 * N-th Tribonacci Number
 * Easy
 */
public class FibonacciNumber {

    /**
     * Solution
     * Approach 1: Recursion
     * Intuition
     *
     * Use recursion to compute the Fibonacci number of a given integer.
     *
     * fib(5) Recursion diagram
     *
     * Figure 1. An example tree representing what fib(5) would look like
     *
     * Algorithm
     *
     * Check if the provided input value, N, is less than or equal to 1. If true, return N.
     *
     * Otherwise, the function fib(int N) calls itself, with the result of the 2 previous numbers being added to each other, passed in as the argument. This is derived directly from the recurrence relation: F_{n} = F_{n-1} + F_{n-2}F
     * n
     * ​
     *  =F
     * n−1
     * ​
     *  +F
     * n−2
     * ​
     *
     *
     * Do this until all numbers have been computed, then return the resulting answer.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(2^N)O(2
     * N
     *  ). This is the slowest way to solve the Fibonacci Sequence because it takes exponential time. The amount of operations needed, for each level of recursion, grows exponentially as the depth approaches N.
     *
     * Space complexity : O(N)O(N). We need space proportionate to N to account for the max size of the stack, in memory. This stack keeps track of the function calls to fib(N). This has the potential to be bad in cases that there isn't enough physical memory to handle the increasingly growing stack, leading to a StackOverflowError. The Java docs have a good explanation of this, describing it as an error that occurs because an application recurses too deeply.
     *
     */

     /** Approach 2: Bottom-Up Approach using Memoization
     * Intuition
     *
     * Improve upon the recursive option by using iteration, still solving for all of the sub-problems and returning the answer for N, using already computed Fibonacci values. In using a bottom-up approach, we can iteratively compute and store the values, only returning once we reach the result.
     *
     * Algorithm
     *
     * If N is less than or equal to 1, return N
     * Otherwise, iterate through N, storing each computed answer in an array along the way.
     * Use this array as a reference to the 2 previous numbers to calculate the current Fibonacci number.
     * Once we've reached the last number, return it's Fibonacci number.
     *
     * Complexity Analysis
     *
     * Time complexity : O(N)O(N). Each number, starting at 2 up to and including N, is visited, computed and then stored for O(1)O(1) access later on.
     *
     * Space complexity : O(N)O(N). The size of the data structure is proportionate to N.
     *
     */

     /** Approach 3: Top-Down Approach using Memoization
     * Intuition
     *
     * Solve for all of the sub-problems, use memoization to store the pre-computed answers, then return the answer for N. We will leverage recursion, but in a smarter way by not repeating the work to calculate existing values.
     *
     * Algorithm
     *
     * Check if N <= 1. If it is, return N.
     * Call and return memoize(N)
     * If N exists in the map, return the cached value for N
     * Otherwise set the value of N, in our mapping, to the value of memoize(N-1) + memoize(N-2)
     *
     * Complexity Analysis
     *
     * Time complexity : O(N)O(N). Each number, starting at 2 up to and including N, is visited, computed and then stored for O(1)O(1) access later on.
     *
     * Space complexity : O(N)O(N). The size of the stack in memory is proportionate to N.
     *
     **/

     /** Approach 4: Iterative Top-Down Approach
     * Intuition
     *
     * Let's get rid of the need to use all of that space and instead use the minimum amount of space required. We can achieve O(1)O(1) space complexity by only storing the value of the two previous numbers and updating them as we iterate to N.
     *
     * Algorithm
     *
     * Check if N <= 1, if it is then we should return N.
     * Check if N == 2, if it is then we should return 1 since N is 2 and fib(2-1) + fib(2-2) equals 1 + 0 = 1.
     * To use an iterative approach, we need at least 3 variables to store each state fib(N), fib(N-1) and fib(N-2).
     * Preset the initial values:
     * Initialize current with 0.
     * Initialize prev1 with 1, since this will represent fib(N-1) when computing the current value.
     * Initialize prev2 with 1, since this will represent fib(N-2) when computing the current value.
     * Iterate, incrementally by 1, all the way up to and including N. Starting at 3, since 0, 1 and 2 are pre-computed.
     * Set the current value to fib(N-1) + fib(N-2) because that is the value we are currently computing.
     * Set the prev2 value to fib(N-1).
     * Set the prev1 value to current_value.
     * When we reach N+1, we will exit the loop and return the previously set current value.
     *
     * Complexity Analysis
     *
     * Time complexity : O(N)O(N). Each value from 2 to N will be visited at least once. The time it takes to do this is directly proportionate to N where N is the Fibonacci Number we are looking to compute.
     *
     * Space complexity : O(1)O(1). This requires 1 unit of Space for the integer N and 3 units of Space to store the computed values (curr, prev1 and prev2) for every loop iteration. The amount of Space doesn't change so this is constant Space complexity.
     *
     */

     /** Approach 5: Matrix Exponentiation
     * Intuition
     *
     * Use Matrix Exponentiation to get the Fibonacci number from the element at (0, 0) in the resultant matrix.
     *
     * In order to do this we can rely on the matrix equation for the Fibonacci sequence, to find the Nth Fibonacci number: \begin{pmatrix} 1\,\,1 \\ 1\,\,0 \end{pmatrix}^{n}=\begin{pmatrix} \: F_{(n+1)}\;\;\:F_{(n)}\\ \: F_{(n)}\;\;\:F_{(n-1)} \end{pmatrix}(
     * 11
     * 10
     * ​
     *  )
     * n
     *  =(
     * F
     * (n+1)
     * ​
     *  F
     * (n)
     * ​
     *
     * F
     * (n)
     * ​
     *  F
     * (n−1)
     * ​
     *
     * ​
     *  )
     *
     * Algorithm
     *
     * Check if N is less than or equal to 1. If it is, return N.
     * Use a recursive function, matrixPower, to calculate the power of a given matrix A. The power will be N-1, where N is the Nth Fibonacci number.
     * The matrixPower function will be performed for N/2 of the Fibonacci numbers.
     * Within matrixPower, call the multiply function to multiply 2 matrices.
     * Once we finish doing the calculations, return A[0][0] to get the Nth Fibonacci number.
     *
     * Complexity Analysis
     *
     * Time complexity : O(\log N)O(logN). By halving the N value in every matrixPower's call to itself, we are halving the work needed to be done.
     *
     * Space complexity : O(\log N)O(logN). The size of the stack in memory is proportionate to the function calls to matrixPower plus the memory used to account for the matrices which takes up constant space.
     *
     */

     /** Approach 6: Math
     * Intuition Using the golden ratio, a.k.a Binet's forumula: \varphi = \frac{1 + \sqrt{5}}{2} \approx 1.6180339887....φ=
     * 2
     * 1+
     * 5
     * ​
     *
     * ​
     *  ≈1.6180339887....
     *
     * Here's a link to find out more about how the Fibonacci sequence and the golden ratio work.
     *
     * We can derive the most efficient solution to this problem using only constant time and constant space!
     *
     * Algorithm
     *
     * Use the golden ratio formula to calculate the Nth Fibonacci number.
     *
     * Complexity Analysis
     *
     * Time complexity : O(1)O(1). Constant time complexity since we are using no loops or recursion and the time is based on the result of performing the calculation using Binet's formula.
     *
     * Space complexity : O(1)O(1). The space used is the space needed to create the variable to store the golden ratio formula.
     */
}
