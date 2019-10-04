package Bloomberg;

/**
 * Created by cicean on 9/12/2016.
 * LintCode 130 Heapify
 */
public class Heapify {
    // Version 1: this cost O(n)
    private void siftdown(int[] A, int k) {
        while (k < A.length) {
            int smallest = k;
            if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallest]) {
                smallest = k * 2 + 1;
            }
            if (k * 2 + 2 < A.length && A[k * 2 + 2] < A[smallest]) {
                smallest = k * 2 + 2;
            }
            if (smallest == k) {
                break;
            }
            int temp = A[smallest];
            A[smallest] = A[k];
            A[k] = temp;

            k = smallest;
        }
    }

    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
        // write your code here
        for (int i = A.length / 2; i >= 0; i--) {
            siftdown(A, i);
        } // for
    }

    // Version 2: This cost O(nlogn)
    class Solution {
        /**
         * @param A: Given an integer array
         * @return: void
         */
        private void siftup(int[] A, int k) {
            while (k != 0) {
                int father = (k - 1) / 2;
                if (A[k] > A[father]) {
                    break;
                }
                int temp = A[k];
                A[k] = A[father];
                A[father] = temp;

                k = father;
            }
        }

        public void heapify(int[] A) {
            for (int i = 0; i < A.length; i++) {
                siftup(A, i);
            }
        }
    }
}
