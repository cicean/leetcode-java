package VMware;

public class RotatedArrayFindTheMaxValueIndex {

  /**
   * 整体来看不算特别难1. 给一个int array，然后给另一个array是表示向左rotate这个array多少个position，
   * 然后输出每次进行这样操作后最大值所在的Index
   * @param A
   * @param R
   * @return
   */

  private int findtheMaxIndex(int[] A, int[] R) {
    int index = 0;
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < A.length; i++) {
      if (A[i] > max) {
        max = A[i];
        index = i;
      }
    }

    int total = 0;

    for (int i = 0; i < R.length; i++) {
      total = total + R[i];
    }

    index = A.length - ((index - total) % A.length);


    return index

  }

}
