/**
 * 264 Ugly Number II 20.9% Medium 
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number.
 * 
 * Hint:
 * 
 * The naive approach is to call isUgly for every number until you reach the nth
 * one. Most numbers are not ugly. Try to focus your effort on generating only
 * the ugly ones. An ugly number must be multiplied by either 2, 3, or 5 from a
 * smaller ugly number. The key is how to maintain the order of the ugly
 * numbers. Try a similar approach of merging from three sorted lists: L1, L2,
 * and L3. Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 *
 * 2, L2 * 3, L3 * 5). Credits: Special thanks to @jianchao.li.fighter for
 * adding this problem and creating all test cases.
 * 
 * Hide Tags Dynamic Programming Heap Math Hide Similar Problems (H) Merge k
 * Sorted Lists (E) Count Primes (E) Ugly Number (M) Perfect Squares
 * 
 * @author cicean
 *
 */
public class UglyNumberII {
	
	

	public int nthUglyNumber(int n) {
		
		int[] uglyNumbers = new int[n];
		uglyNumbers[0] = 1;
		
		int idx2 = 0;
		int idx3 = 0;
		int idx5 = 0;
		
		int counter = 1;
		
		while (counter < n) {
			int min = minOf(uglyNumbers[idx2] * 2, uglyNumbers[idx3] * 3, uglyNumbers[idx5] * 5);
			if (min == uglyNumbers[idx2] * 2) {
				idx2 ++;
			}
			
			if (min == uglyNumbers[idx3] * 3) {
				idx3 ++;
			}
			
			if (min == uglyNumbers[idx5] * 5) {
				idx5 ++;
			}
			
			uglyNumbers[counter] = min;
			counter ++;
		}
		
		return uglyNumbers[n-1];
	}
	
	public int minOf(int a, int b, int c) {
		int temp = a < b ? a : b;
		return temp < c ? temp : c;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UglyNumberII slt = new UglyNumberII();
		int n = 17;
		System.out.println(slt.nthUglyNumber(n));
		
	}

}
