/*
 136	Single Number	45.1%	Medium
 Problem:    Single Number
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/single-number/
 Notes:
 Given an array of integers, every element appears twice except for one. 
 Find that single one.
 Your algorithm should have a linear runtime complexity. 
 Could you implement it without using extra memory?
 Solution: XOR.
*/


public class SingleNumber {
	
	public int singleNumber(int[] A) {
        int res = 0;
        for (int i : A) {
            res = res^i;
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {-19,-19,-46, -46};
		//System.out.println(singleNumber(A));
		
		SingleNumber slt = new SingleNumber();
		System.out.println(slt.singleNumber(A));
	}

}
