package PureStorage;

import java.util.*;

import java.util.Arrays;

/* Pure storage buddy system bitmap
    Given a complete binary tree with nodes of values of either 1 or 0, the following rules always hold:
    (1) a node's value is 1 if and only if all its subtree nodes' values are 1
    (2) a leaf node can have value either 1 or 0
    Implement the following 2 APIs:
    set_bit(offset, length), set the bits at range from offset to offset+length-1
    clear_bit(offset, length), clear the bits at range from offset to offset+length-1

    i.e. The tree is like:
                 0
              /     \
             0        1
           /  \      /  \
          1    0    1    1
         /\   / \   /
        1  1 1   0 1
        Since it's complete binary tree, the nodes can be stored in an array:
        [0,0,1,1,0,1,1,1,1,1,0,1]
*/


public class BuddyBitMap {

  /*
  * Helper function to help recurse down the tree when setting bits
   */
  public static void set_bit_down(int A[], int x, int n) {
    if (x >= n) return;
    // left child
    if (2*x+1 <= n && A[2*x+1] == 0) {
      A[2*x+1] = 1;
      set_bit_down(A, 2*x+1, n);
    }
    // right child
    if (2*x+2 <= n && A[2*x+2] == 0) {
      A[2*x+2] = 1;
      set_bit_down(A, 2*x+2, n);
    }
  }

  public static void set_bit(int A[], int pos, int length) {
    // not valid, so don't continue
    if (A == null || pos <= 0 || length <= 0) return;

    int n = length - 1; // last index

    for (int x = pos; x < Math.min(n + 1, Math.min(pos + length, 2 * pos + 1)); ++x) {
      // A[x] = 1;
      // set the descendants
      set_bit_down(A, x, n);

      // set the ancestors
      while (x > 0) {
        if ( (x%2 == 0 && A[x-1] == 1) || (x%2==1 && x < n && A[x+1] == 1) ) {
          A[(x-1) / 2] = 1;
        }
        x = (x-1) / 2;
      }
    }
  }

  public static void clear_bit(int A[], int pos, int length) {
    if (A == null || pos <= 0 || length <= 0) return;
    int n = length - 1;

    for (int x = pos; x < Math.min(n + 1, pos + length); ++x) {
//			A[x] = 0;
      // Clear the descendants
      while (2*x+1 <= n) {
        A[2*x+1] = 0;
        x = 2*x+1;
      }

      // Clear the ancestors
      while (x > 0) {
        if (A[2*x-1] == 0) break;
        A[(x-1)/2] = 0;
        x = (x-1)/2;
      }
    }
  }



}


class BuddyBitMap2 {


  public static void clearBit(int[][] matrix,  int offset, int length) {
    int curLevel = matrix.length-1;
    int left = offset;
    int right = offset+length - 1;
    while (curLevel >= 0){
      for (int i = left; i <= right; i++) {
        matrix[curLevel][i] = 0;
      }
      curLevel--;
      left = left/2;
      right = right/2;
    }
  }
  public static void setBit(int[][] bits,  int offset, int length) {
    int curLevel = bits.length-1;
    int left = offset;
    int right = offset+length - 1;
    while (curLevel >= 0) {
      for (int i = left; i <= right; i++) {
        bits[curLevel][i] = 1;
      }
      int leftBuddy = left + (left % 2 == 1 ? -1 : 1);
      int rightBuddy = right + (right % 2 == 1 ? -1 : 1);
      int leftBit = bits[curLevel][left] * bits[curLevel][leftBuddy];
      int rightBit = bits[curLevel][right] * bits[curLevel][rightBuddy];
      curLevel--;
      left /= 2;
      right /= 2;
      if (leftBit == 0) {
        left++;
      }
      if (rightBit == 0) {
        right--;
      }
    }
  }
}

/**
 * Buttercola: Buddy System
 Given a complete binary tree with nodes of values of either 1 or 0, the following rules always hold:
 1. A node's value is 1 iff and only if all its subtree nodes' value are 1
 2. A leaf node can have value either 1 or 0.

 Implement the following 2 APIs:
 set_bit(offset, length), set the bits at range from offset to offset + length - 1
 clear_bit(offset, length), clear the bits at range from offset to offset + length - 1

 这是一个二维数组，不是一个heap(heap就是说这个树存在一个一维数组里，满足A的child是A[2i+1]和A[2i+2])，问题背景起源于内存分配问题
 比如有N个level，第一个level有一个bit，第二个level有2个bit，第三个level有四个bit，调用第x个level的第y个bit直接用A[x][y]. 鍥磋鎴戜滑@1point 3 acres
 那么A[x][y]的孩子包括A[x+1][2y] 和 A[x+1][2y+1]
 题目要求完成的是：
 例如ClearBits(A, 4,9) => 把第N个level的第4位到第9位清0. 当child清0之后， parent也要跟着清0，一直到root
 e.g.
    0

 /       \
 0         0
 /   \      /   \
 1   0    1    0
 / \   / \   / \   / \
 1 1 1 0 1 1 0 0

 After calling the clear_bit(1, 3), the binary tree becomes

 0

 /       \
 0         0
 /   \      /   \
 0   0    1    0
 / \   / \   / \   / \
 1 0 0 0 1 1 0 0


 For this tree, after call set_bit(1, 5), the binary tree becomes:


 Understand the problem:

 What's a buddy system? Here the link gives good explanation:
 https://www.cs.fsu.edu/~engelen/courses/COP402003/p827.pdf
 */

class Solution {

  private int[][] bits;
  private int n;
  private int numNodesLastLevel;

  public Solution (int n) {
    this.n = n;
    this.numNodesLastLevel = (int)Math.pow(2, n-1);
    bits = new int[n][numNodesLastLevel];
  }

  public void setBits(int[][] bits) {
    this.bits = bits;
  }

  public void clearBits(int offset, int len) {
    if (offset < 0 || offset + len > numNodesLastLevel) {
      throw new IndexOutOfBoundsException();
    }
    clearBitsHelper(n-1, offset, offset + len - 1);
  }

  private void clearBitsHelper(int level, int start, int end) {
    if (level < 0 || start > end) return;

    boolean containsOne = setToZero(level, start, end);
    if (containsOne) {
      clearBitsHelper(level - 1, start/2, end/2);
    }
  }

  private boolean setToZero (int level, int start, int end) {
    boolean containOne = false;
    for (int i = start; i <= end; i++) {
      if (bits[level][i] == 1) {
        if (!containOne) {
          containOne = true;
        }
      }
      bits[level][i] = 0;
    }

    return containOne;
  }

  public void setBits(int offset, int len) {
    if (offset < 0 || offset + len > numNodesLastLevel) {
      throw new IndexOutOfBoundsException();
    }

    setBitsHelper(n-1, offset, offset+len - 1);
  }

  private void setBitsHelper(int level, int start, int end) {
    if (level < 0 || start > end) return;
    setToOne(level, start, end);

    //if start index is odd
    if ((start & 1) == 1) {
      start--;
    }

    //if start index is even
    if ((start & 1) == 0) {
      end++;
    }

    //determine the start position of the next level
    int nextstart = Integer.MAX_VALUE;
    for (int i = start; i < end; i +=2) {
      if (bits[level][i] == 1 && bits[level][i+1] == 1) {
        nextstart = i/2;
        break;
      }
    }

    //determine the start position of the next level
    int nextend = Integer.MIN_VALUE;
    for (int i = end; i > start; i -=2) {
      if (bits[level][i] == 1 && bits[level][i-1] == 1) {
        nextend = i/2;
        break;
      }
    }
    setBitsHelper(level -1, nextstart, nextend);
  }

  private void setToOne(int level, int start, int end) {
    for (int i = start; i <= end; i++) {
      if (bits[level][i] == 1) {
        bits[level][i] = 1;
      }
    }
  }

  private void printTree() {

    int nodes = 1;

    for (int[] level : bits) {

      for (int i = 0; i < nodes; i++) {

        System.out.print(level[i] + ", ");

      }



      System.out.println("");

      nodes *= 2;

    }

    System.out.println("");

  }

  public static void main(String[] args) {

    Solution sol = new Solution(4);



    sol.setBits(1, 5);

    sol.printTree();



    sol.clearBits(2, 6);

    sol.printTree();

  }


}
