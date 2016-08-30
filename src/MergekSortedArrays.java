import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
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
 */


public class MergekSortedArrays {
	
	
	public List<Integer> mergekSortedArrays(int[][] arrays) {
        // Write your code here
		List<Integer> res = new ArrayList<>();
		int[] A = changeTo(0, arrays);
		for (int i = 1; i < arrays.length; i++) {
			merge(A, A.length, changeTo(i, arrays), changeTo(i, arrays).length);
		}
		;

		return res.addAll(new Arrays<Integer>());
		
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
