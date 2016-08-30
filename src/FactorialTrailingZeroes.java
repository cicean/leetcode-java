import java.util.Scanner;

/*
 172	Factorial Trailing Zeroes	28.5%	Easy
 Problem:    Factorial Trailing Zeroes
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/factorial-trailing-zeroes/
 Notes:
 Given an integer n, return the number of trailing zeroes in n!.
 Note: Your solution should be in logarithmic time complexity.
 Solution: ...
 */

public class FactorialTrailingZeroes {

	public int trailingZeroes_1(int n) {
        int res = 0;
        while (n != 0) {
            res += n / 5;
            n = n / 5;
        }
        return res;
    }
    public int trailingZeroes_2(int n) {
        if (n == 0) return 0;
        return n / 5 + trailingZeroes_2(n / 5);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FactorialTrailingZeroes slt = new FactorialTrailingZeroes();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please inpute a  integer ");
		int n = sc.nextInt();
		System.out.print(slt.trailingZeroes_1(n));
	}

}
