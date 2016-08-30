/*
 27	Remove Element	32.2%	Easy
 Problem:    Remove Element
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/remove-element/
 Notes:
 Given an array and a value, remove all instances of that value in place and return the new length.
 The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 Solution: Refactor: Update solution. Use two pointers.
 */

public class RemoveElement {
	public int removeElement(int[] A, int elem) {
        int N = A.length;
        int idx = 0;
        for (int i = 0; i < N; ++i) {
            if (A[i] != elem) {
                A[idx++] = A[i];
            }
        }
        return idx;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveElement slt=  new RemoveElement();
		int [] A = {1,1,2,3};
		int elem = 1;
		//Scanner sc =  new Scanner(System.in);
    	//System.out.println("Please input an integer");
    	//String s= sc.nextLine();
    	int res = slt.removeElement(A,elem);
    	System.out.println(res);
    	//int N =A.length;
    	
    	for (int i=0; i<res; i++){System.out.print(A[i]+",");}
    	
		
	}
}
