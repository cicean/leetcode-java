package Indeed;

import java.util.*;

/**
 * Created by cicean on 9/26/2018.
 */
public class MergeSortedArray {

    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        if (A == null || B == null || A.length != m + n || B.length != n) {
            return;
        }

        int i = m + n - 1;
        while (m - 1 >= 0 && n - 1 >=0) {
            if (A[m - 1] < B[n - 1]) {
                A[i--] = B[n - 1];
                n--;
            } else {
                A[i--] = A[m - 1];
                m--;
            }
        }

        while (m - 1 >= 0) {
            A[i--] = A[m - 1];
            m--;
        }

        while (n - 1 >= 0) {
            A[i--] = B[n - 1];
            n--;
        }

        return;
    }

    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        if (A == null || B == null) {
            return null;
        }

        int[] result = new int[A.length + B.length];
        int i = 0, j = 0, index = 0;

        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                result[index++] = A[i++];
            } else {
                result[index++] = B[j++];
            }
        }

        while (i < A.length) {
            result[index++] = A[i++];
        }
        while (j < B.length) {
            result[index++] = B[j++];
        }

        return result;
    }

    class Element {
        public int row, col, val;
        Element(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    private Comparator<Element> ElementComparator = new Comparator<Element>() {
        public int compare(Element left, Element right) {
            return left.val - right.val;
        }
    };

    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null) {
            return new int[0];
        }

        int total_size = 0;
        Queue<Element> Q = new PriorityQueue<Element>(
                arrays.length, ElementComparator);

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Element elem = new Element(i, 0, arrays[i][0]);
                Q.add(elem);
                total_size += arrays[i].length;
            }
        }

        int[] result = new int[total_size];
        int index = 0;
        while (!Q.isEmpty()) {
            Element elem = Q.poll();
            result[index++] = elem.val;
            if (elem.col + 1 < arrays[elem.row].length) {
                elem.col += 1;
                elem.val = arrays[elem.row][elem.col];
                Q.add(elem);
            }
        }

        return result;
    }
}
