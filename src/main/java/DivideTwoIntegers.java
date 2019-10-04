import java.util.Scanner;

/*
 29	Divide Two Integers	15.0%	Medium
 Problem:    Divide Two Integers
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/divide-two-integers/
 Notes:
 Divide two integers without using multiplication, division and mod operator.
 Solution: Use << operator.
*/

public class DivideTwoIntegers {

	public int divide(int dividend, int divisor) {
        boolean flag = dividend < 0 ^ divisor < 0;
        long Dividend = Math.abs((long)dividend);
        long Divisor = Math.abs((long)divisor);
        long res = 0;
        while (Dividend >= Divisor) {
            long c = Divisor;
            for (int i = 0; (c << i) <= Dividend; ++i) {
                Dividend -= (c << i);
                res += (1 << i);
            }
        }
        if (flag == true) res = -res;
        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int)res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DivideTwoIntegers slt=  new DivideTwoIntegers();
		
		Scanner dividend =  new Scanner(System.in);
    	System.out.println("Please input an integer as dividend");
    	int divend= dividend.nextInt();
    	
    	Scanner divisor =  new Scanner(System.in);
    	System.out.println("Please input an integer as dividor");
    	int divr= divisor.nextInt();
    	
    	
    	int res = slt.divide(divend,divr);
    	System.out.println(res);
    	
		
	}
}
