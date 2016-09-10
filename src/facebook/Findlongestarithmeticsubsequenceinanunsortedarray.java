package facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Findlongestarithmeticsubsequenceinanunsortedarray {

	// The idea is to maintain a 2d array. Called
	// length[input.length][input.length]
	// length[i][j] means the length of the arithmetic that ends with input[i]
	// and input[j]
	// And a hashMap to record the index of every number
	// We traverse the input from index 1 to the end.
	// Everytime we meet a number, fix this as the end of sequence. Waral
	// 閸楁艾顓归張澶嬫纯婢舵碍鏋冪粩锟�,
	// Then traverse back and try to make every number as second last number.
	// 閻ｆ瑥顒熼悽瀹狀嚞鐠佸搫娼�-娑撯偓娴溾晙绗侀崚鍡楁勾
	// When we fix one as second last one number, we calculate the gap as
	// input[last] - input[secondLast]
	// look into hashmap to find is there a number in the input equals to
	// input[secondLast].
	// And check its index whether it is smaller than secondLast..
	// 娑撯偓娴滐拷-娑撳鍨�-閸﹀府绱濋悪顒�顔嶉崣鎴濈
	// If it is. Then this is the third last number. And we should already have
	// length[thridLast][secondLast]
	// Then length[secondLast][last] = length[thridLast][secondLast] + 1
	// If it is not. We make length[secondLast][last] = 2 -- those two number
	// are the only numbers in the arithmetic
	// 'Time complexity: O(n^2), space complexity: O(n^2) -- for only return
	// length'
	public int findLongest(int[] input) {
		if (input.length <= 2) {
			return 2;
		}
		int maxLen = 2;
		int[][] length = new int[input.length][input.length];
		HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
		for (int i = 0; i < input.length; i++) {
			if (!valueToIndex.containsKey(input[i])) {
				valueToIndex.put(input[i], new ArrayList<Integer>());
			}
			valueToIndex.get(input[i]).add(i);
		}
		for (int index = 1; index < input.length; index++) {
			for (int secondLast = index - 1; secondLast >= 0; secondLast--) {
				int gap = input[index] - input[secondLast];
				int next = input[secondLast] - gap;
				if (valueToIndex.containsKey(next)) {
					int nextIndex = -1;
					for (int j = valueToIndex.get(next).size() - 1; j >= 0; j--) {
						if (valueToIndex.get(next).get(j) < secondLast) {
							nextIndex = valueToIndex.get(next).get(j);
							break;
						}
					}
					if (nextIndex != -1) {
						length[secondLast][index] = length[nextIndex][secondLast] + 1;
						maxLen = Math.max(maxLen, length[secondLast][index]);
					}
				}
				if (length[secondLast][index] == 0) {
					length[secondLast][index] = 2;
				}
			}
		}
		return maxLen;
	}

	// Longest arithmetic progression in a sorted array

	/**
	 * Problem: Given a sorted array, find the longest arithmetic progression in
	 * the same.
	 * 
	 * 
	 * Solution: Before solving this problem, let us solve a different problem
	 * first.
	 * 
	 * How to find if a sorted array contains an arithmetic progression of
	 * length 3?
	 * 
	 * This can be done by looping over the array and then finding numbers 'i'
	 * and 'k' on either side of current number 'j' such that arr[i] + arr[k] =
	 * 2*arr[j] and i < j < k
	 * 
	 * The above algorithm is n*(n + log(n)) = O(n2) and can be extended to find
	 * largest AP in a sorted array as follows:
	 * 
	 * Create a table 'lookup' such that lookup[j][k] will give the length of
	 * the longest AP whose first two elements are arr[j] and arr[k]. Then every
	 * time another element 'i' is found in the same series, we can updated the
	 * lookup as:
	 * 
	 * lookup[i][j] = lookup[j][k] + 1;
	 * 
	 * @param input
	 */

	public int findLongestInsortedArray(int[] input) {
		if (input == null)
			return -1;

		int n = input.length;
		int lookup[][] = new int[n][n];
		int maxAP = 2;

		// Any 2-letter series is an AP
		// Here we initialize only for the last column of lookup because
		// all i and (n-1) pairs form an AP of size 2
		for (int i = 0; i < n; i++)
			lookup[i][n - 1] = 2;

		// Loop over the array and find two elements 'i' and 'k' such that i+k =
		// 2*j
		for (int j = n - 2; j >= 1; j--) {
			int i = j - 1; // choose i to the left of j
			int k = j + 1; // choose k to the right of j

			// printLookup(lookup);
			// System.out.println ("\nChecking out " + input[j]);
			while (i >= 0 && k <= n - 1) {
				int isAP = (input[i] + input[k]) - 2 * input[j];

				if (isAP < 0)
					k++;
				else if (isAP > 0)
					i--;
				else {
					debugState(input, lookup, j, i, k);
					lookup[i][j] = lookup[j][k] + 1;
					maxAP = Math.max(maxAP, lookup[i][j]);
					k++;
					i--;
				}
			}
		}
		// System.out.println (maxAP);
		return maxAP;

	}

	private void debugState(int[] sortedArr, int[][] lookup, int j, int i, int k) {
		System.out.println("    Found " + sortedArr[i] + ", " + sortedArr[j]
				+ ", " + sortedArr[k]);
		System.out.println("    lookup[" + i + "][" + j + "] = lookup[" + j
				+ "][" + k + "] (" + lookup[j][k] + ") + 1");
	}

	void printLookup(int[][] lookup) {
		for (int i = 0; i < lookup.length; i++) {
			for (int j = 0; j < lookup[i].length; j++) {
				System.out.print(String.format("%3d", lookup[i][j]));
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int sortedArr[] = new int[] { 1, 3, 4, 5, 7, 8, 9 };
		int[] unsortedArr = new int[] { 5, 4, 2, 6, 6, -1, -2, 6, -4, 6, -4 };

		Findlongestarithmeticsubsequenceinanunsortedarray slt = new Findlongestarithmeticsubsequenceinanunsortedarray();
		int len = slt.findLongestInsortedArray(sortedArr);
		int len2 = slt.findLongest(unsortedArr);
		System.out.print("sorted = " + len + ", unsorted = " + len2);
	}

}
