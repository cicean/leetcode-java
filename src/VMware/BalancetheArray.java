package VMware;

/**
 * balance array, find a[i], 让左边的和等于右边的和 leftsum(a[0] ... a[i-1]) == rightsum(a[i+1]...a[n-1]),
 * 本**在这卡很久，最后一个test case过不了，因为我以为 i 只能从 1..n-2, 原来leftsum或者rightsum可以没有数，就是等于0. F
 */

public class BalancetheArray {

  private int blancetheArray(int[] A) {
    int[] left = new int[A.length];
    int[] right = new int[A.length];
    left[0] = 0;
    right[0] = 0;
    for (int i = 0; i < A.length; i++) {
      left[i+1] = left[i] + A[i];
      right[i+1] = right[i] + A[A.length - i -1];
    }

    if (left[A.length] == 0) {
      return 0;
    }

    for (int i = 1;i < A.length; i++) {
      if (left[i] == right[A.length - i] ) {
        return A[i - 1];
      }
    }

    return -1;
  }

}
