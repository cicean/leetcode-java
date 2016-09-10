/*
 88	Merge Sorted Array	29.7%	Easy
 Problem:    Merge Sorted Array
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/merge-sorted-array/
 Notes:
 Given two sorted integer arrays A and B, merge B into A as one sorted array.
 Note:
 You may assume that A has enough space to hold additional elements from B. 
 The number of elements initialized in A and B are m and n respectively.
 Solution: From back to forth.
 */

public class MergeSortedArray {

	//O(M+N) Ê±¼ä O(1) ¿Õ¼ä
	public void merge(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        int x = m + n - 1;
        while (i >= 0 && j >= 0)
            if (A[i] >= B[j]) A[x--] = A[i--];
            else A[x--] = B[j--];
        while (j >= 0) A[x--] = B[j--];
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSortedArray slt = new MergeSortedArray();
		int[] A = {1, 2, 3 ,5};
		int[] B = {3,8, 9 , 10};
		int m = 2;
		int n =2 ;
		slt.merge(A, m, B, n);
		for(int i = 0; i<m+n;i++){
			System.out.print(A[i] + " ");
		}
				
		
	}

}
