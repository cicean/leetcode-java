/*
 66	Plus One	30.5%	Easy
 Problem:    Plus One
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/plus-one/
 Notes:
 Given a number represented as an array of digits, plus one to the number.
 Solution: ...
 */

public class PlusOne {
	public int[] plusOne(int[] digits) {
        if (digits.length == 0) return digits;
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; --i) {
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }
        if (carry == 0) return digits;
        int[] res = new int[digits.length + 1];
        res[0] = carry;
        System.arraycopy(digits, 0, res, 1, digits.length);
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlusOne slt = new PlusOne();
		int[] digits = {1,9,9,9};
		int[] res = slt.plusOne(digits);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]);
		}
		
	}
}
