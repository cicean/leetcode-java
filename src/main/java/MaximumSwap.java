import java.util.*;

/**
 * 670. Maximum Swap
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

 Example 1:
 Input: 2736
 Output: 7236
 Explanation: Swap the number 2 and the number 7.
 Example 2:
 Input: 9973
 Output: 9973
 Explanation: No swap.
 Note:
 The given number is in the range [0, 10^8]
 */

public class MaximumSwap {

  /*
  Approach #1: Brute Force [Accepted]

Intuition

The number only has at most 8 digits, so there are only {}^{8}\text{C}_{2}
​8
​​ C
​2
​​  = 28 available swaps. We can easily brute force them all.

Algorithm

We will store the candidates as lists of length \text{len(num)}len(num). For each candidate swap with positions \text{(i, j)}(i, j), we swap the number and record if the candidate is larger than the current answer, then swap back to restore the original number.

The only detail is possibly to check that we didn't introduce a leading zero. We don't actually need to check it, because our original number doesn't have one.

Complexity Analysis

Time Complexity: O(N^3)O(N
​3
​​ ), where NN is the total number of digits in the input number. For each pair of digits, we spend up to O(N)O(N) time to compare the final sequences.

Space Complexity: O(N)O(N), the information stored in \text{A}A.
   */

  public int maximumSwap(int num) {
    char[] A = Integer.toString(num).toCharArray();
    char[] ans = Arrays.copyOf(A, A.length);
    for (int i = 0; i < A.length; i++) {
      for (int j = i+1; j < A.length; j++) {
        char tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
        for (int k = 0; k < A.length; k++){
          if (A[k] != ans[k]){
            if (A[k] > ans[k]) {
              ans = Arrays.copyOf(A, A.length);
            }
            break;
          }
        }
        A[j] = A[i];
        A[i] = tmp;
      }
    }
    return Integer.valueOf(new String(ans));
  }

  /*
  Approach #2: Greedy [Accepted]

Intuition

At each digit of the input number in order, if there is a larger digit that occurs later, we know that the best swap must occur with the digit we are currently considering.

Algorithm

We will compute \text{last[d] = i}last[d] = i, the index \text{i}i of the last occurrence of digit \text{d}d (if it exists).

Afterwards, when scanning the number from left to right, if there is a larger digit in the future, we will swap it with the largest such digit; if there are multiple such digits, we will swap it with the one that occurs the latest.

Complexity Analysis

Time Complexity: O(N)O(N), where NN is the total number of digits in the input number. Every digit is considered at most once.

Space Complexity: O(1)O(1). The additional space used by \text{last}last only has up to 10 values.
   */

  public int maximumSwap_2(int num) {
    char[] A = Integer.toString(num).toCharArray();
    int[] last = new int[10];
    for (int i = 0; i < A.length; i++) {
      last[A[i] - '0'] = i;
    }

    for (int i = 0; i < A.length; i++) {
      for (int d = 9; d > A[i] - '0'; d--) {
        if (last[d] > i) {
          char tmp = A[i];
          A[i] = A[last[d]];
          A[last[d]] = tmp;
          return Integer.valueOf(new String(A));
        }
      }
    }
    return num;
  }

}
