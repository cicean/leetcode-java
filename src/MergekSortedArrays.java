import java.util.*;


/**
 * Lintcode
 * Given k sorted integer arrays, merge them into one sorted array.
 * @author cicean
 * Example
Given 3 sorted arrays:

[
  [1, 3, 5, 7],
  [2, 4, 6],
  [0, 8, 9, 10, 11]
]
return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].
 *
 * Given k sorted arrays of size n each, merge them and print the sorted output.

Example:

Input:
k = 3, n =  4
arr[][] = { {1, 3, 5, 7},
{2, 4, 6, 8},
{0, 9, 10, 11}} ;

Output: 0 1 2 3 4 5 6 7 8 9 10 11
A simple solution is to create an output array of size n*k and one by one copy all arrays to it. Finally, sort the output array using any O(nLogn) sorting algorithm. This approach takes O(nkLognk) time.

We can merge arrays in O(nk*Logk) time using Min Heap. Following is detailed algorithm.

1. Create an output array of size n*k.
2. Create a min heap of size k and insert 1st element in all the arrays into a the heap
3. Repeat following steps n*k times.
a) Get minimum element from heap (minimum is always at root) and store it in output array.
b) Replace heap root with next element from the array from which the element is extracted. If the array doesn¡¯t have any more elements, then replace root with infinite. After replacing the root, heapify the tree.
 */


public class MergekSortedArrays {
	

	//User merger two N * K * logN
	public List<Integer> mergekSortedArrays(int[][] arrays) {
        // Write your code here
		List<Integer> res = new ArrayList<>();
		int[] A = changeTo(0, arrays);
		for (int i = 1; i < arrays.length; i++) {
			merge(A, A.length, changeTo(i, arrays), changeTo(i, arrays).length);
		}

		return res;
		
    }
	
	public void merge(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        int x = m + n - 1;
        while (i >= 0 && j >= 0)
            if (A[i] >= B[j]) A[x--] = A[i--];
            else A[x--] = B[j--];
        while (j >= 0) A[x--] = B[j--];
    }
	
	public int[] changeTo(int k, int[][] arrays) {
		int[] a = new int[arrays[k].length];
		for (int i = 0; i< arrays[k].length; i++) {
			a[i] = arrays[k][i];
		}
		
		return a;
	}


	// User wrapper and heap
	class Element {
		public int row, col, val;
		Element(int row, int col, int val) {
			this.row = row;
			this.col = col;
			this.val = val;
		}
	}

	//O(n+m)
	public class Solution {
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
