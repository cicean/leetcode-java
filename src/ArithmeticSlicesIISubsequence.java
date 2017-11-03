import java.util.*;

/**
 * 446. Arithmetic Slices II - Subsequence
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

 For example, these are arithmetic sequences:

 1, 3, 5, 7, 9
 7, 7, 7, 7
 3, -1, -5, -9
 The following sequence is not arithmetic.

 1, 1, 2, 5, 7

 A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

 A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

 The function should return the number of arithmetic subsequence slices in the array A.

 The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.


 Example:

 Input: [2, 4, 6, 8, 10]

 Output: 7

 Explanation:
 All arithmetic subsequence slices are:
 [2,4,6]
 [4,6,8]
 [6,8,10]
 [2,4,6,8]
 [4,6,8,10]
 [2,4,6,8,10]
 [2,6,10]

 */

public class ArithmeticSlicesIISubsequence {

  /**
   * Detailed explanation for Java O(n^2) solution
   At first glance of the problem description, I had a strong feeling that the solution to the original problem can be built through its subproblems, i.e., the total number of arithmetic subsequence slices of the whole input array can be constructed from those of the subarrays of the input array. While I was on the right track to the final solution, it's not so easy to figure out the relations between the original problem and its subproblems.

   To begin with, let's be ambitious and reformulate our problem as follows: let T(i) denote the total number of arithmetic subsequence slices that can be formed within subarray A[0, i], where A is the input array and 0 <= i < n with n = A.length. Then our original problem will be T(n - 1), and the base case is T(0) = 0.

   To make the above idea work, we need to relate T(i) to all T(j) with 0 <= j < i. Let's take some specific j as an example. If we want to incorporate element A[i] into the subarray A[0, j], what information do we need? As far as I can see, we need to know at least the total number of arithmetic subsequence slices ending at each index k with difference d where 0 <= k <= j and d = A[i] - A[k], (i.e., for each such slice, its last element is A[k] and the difference between every two consecutive elements is d), so that adding A[i] to the end of each such slice will make a new arithmetic subsequence slice.

   However, our original formulation of T(i) says nothing about the the total number of arithmetic subsequence slices ending at some particular index and with some particular difference. This renders it impossible to relate T(i) to all T(j). As a rule of thumb, when there is difficulty relating original problem to its subproblems, it usually indicates something goes wrong with your formulation for the original problem.

   From our analyses above, each intermediate solution should at least contain information about the total number of arithmetic subsequence slices ending at some particular index with some particular difference. So let's go along this line and reformulate our problem as T(i, d), which denotes the total number of arithmetic subsequence slices ending at index i with difference d. The base case and recurrence relation are as follows:

   Base case: T(0, d) = 0 (This is true for any d).
   Recurrence relation: T(i, d) = summation of (1 + T(j, d)) as long as 0 <= j < i && d == A[i] - A[j].
   For the recurrence relation, it's straightforward to understand the T(j, d) part: for each slice ending at index j with difference d == A[i] - A[j], adding A[i] to the end of the slice will make a new arithmetic subsequence slice, therefore the total number of such new slices will be the same as T(j, d). What you are probably wondering is: where does the 1 come from?

   The point here is that to make our recurrence relation work properly, the meaning of arithmetic subsequence slice has to be extended to include slices with only two elements (of course we will make sure these "phony" slices won't contribute to our final count). This is because for each slice, we are adding A[i] to its end to form a new one. If the original slice is of length two, after adding we will have a valid arithmetic subsequence slice with three elements. Our T(i, d) will include all these "generalized" slices. And for each pair of elements (A[j], A[i]), they will form one such "generalized" slice (with only two elements) and thus contribute to one count of T(i, d).

   Before jumping to the solution below, I'd like to point out that there are actually overlapping among our subproblems (for example, both T(i, d) and T(i + 1, d) require knowledge of T(j, d) with 0 <= j < i). This necessitates memorization of the intermediate results. Each intermediate result is characterized by two integers: i and d. The former is bounded (i.e., 0 <= i < n) since they are the indices of the element in the input array while the latter is not as d is the difference of two elements in the input array and can be any value. For bounded integers, we can use them to index arrays (or lists) while for unbounded ones, use of HashMap would be more appropriate. So we end up with an array of the same length as the input and whose element type is HashMap.

   Here is the Java program (with a quick explanation given at the end). Both time and space complexities are O(n^2). Some minor points for improving the time and space performance are:

   Define the type of the difference as Integer type instead of Long. This is because there is no valid arithmetic subsequence slice that can have difference out of the Integer value range. But we do need a long integer to filter out those invalid cases.
   Preallocate the HashMap to avoid reallocation to deal with extreme cases.
   Refrain from using lambda expressions inside loops.

   Quick explanation:

   res is the final count of all valid arithmetic subsequence slices; map will store the intermediate results T(i, d), with i indexed into the array and d as the key of the corresponding HashMap.
   For each index i, we find the total number of "generalized" arithmetic subsequence slices ending at it with all possible differences. This is done by attaching A[i] to all slices of T(j, d) with j less than i.
   Within the inner loop, we first use a long variable diff to filter out invalid cases, then get the counts of all valid slices (with element >= 3) as c2 and add it to the final count. At last we update the count of all "generalized" slices for T(i, d) by adding the three parts together: the original value of T(i, d), which is c1 here, the counts from T(j, d), which is c2 and lastly the 1 count of the "two-element" slice (A[j], A[i]).
   */

  public int numberOfArithmeticSlices(int[] A) {
    int res = 0;
    Map<Integer, Integer>[] map = new Map[A.length];

    for (int i = 0; i < A.length; i++) {
      map[i] = new HashMap<>(i);

      for (int j = 0; j < i; j++) {
        long diff = (long)A[i] - A[j];
        if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;

        int d = (int)diff;
        int c1 = map[i].getOrDefault(d, 0);
        int c2 = map[j].getOrDefault(d, 0);
        res += c2;
        map[i].put(d, c1 + c2 + 1);
      }
    }

    return res;
  }


}
