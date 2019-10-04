/*
 26	Remove Duplicates from Sorted Array	31.3%	Easy
 Problem:    Remove Duplicates from Sorted Array
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array/
 Notes:
 Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 Do not allocate extra space for another array, you must do this in place with constant memory.
 For example,
 Given input array A = [1,1,2],
 Your function should return length = 2, and A is now [1,2].
 Solution: Update 7/16/2013: Let j start from 0 for better understanding.
 */


import java.util.Scanner;


public class RemoveDuplicatesfromSortedArray {
	public int removeDuplicates(int[] A) {
        int N = A.length;
        int idx = 0;
        for (int i = 0; i < N; ++i) {
            if (i == 0 || A[i] != A[i - 1]) {
                A[idx++] = A[i];
            }
        }
        return idx;
    }
	
	public int removeDuplicates_1(int[] A) {
	    if (A.length==0) return 0;
	    int j=0;
	    for (int i=0; i<A.length; i++)
	        if (A[i]!=A[j]) A[++j]=A[i];
	    return ++j;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveDuplicatesfromSortedArray slt=  new RemoveDuplicatesfromSortedArray();
		int [] A = {4,4,4,5};
		//Scanner sc =  new Scanner(System.in);
    	//System.out.println("Please input an integer");
    	//String s= sc.nextLine();
    	int res = slt.removeDuplicates(A);
    	System.out.println(res);
    	//int N =A.length;
    	
    	for (int i=0; i<res; i++){System.out.print(A[i]+",");}
    	
		
	}
}
