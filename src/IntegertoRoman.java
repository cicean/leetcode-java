import java.util.Scanner;


/*
 12	Integer to Roman	35.0%	Medium
 
 Problem:    Integer to Roman
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/integer-to-roman/
 Notes:
 Given an integer, convert it to a roman numeral.
 Input is guaranteed to be within the range from 1 to 3999.
 Solution: Buffer the roman numbers.
 */


public class IntegertoRoman {
	public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };  
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" }; 
        StringBuilder result = new StringBuilder();
        for(int i=0; i<values.length;i++)
        {
            while(num>=values[i])
            {
                num-=values[i];
                result.append(numerals[i]);
            }
        }
        return result.toString();
    }
	
	/**
	 * got from leecode discusstion 
	 * @param num
	 * @return
	 */
	public static String intToRoman_1(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
	
	public static void main(String[] args)
	{
		IntegertoRoman slt = new IntegertoRoman();
		Scanner sc =  new Scanner(System.in);
    	System.out.println("Please input an integer");
    	int num=sc.nextInt();
    	String res = slt.intToRoman(num);
    	System.out.print(res);
		
	}  
	
	
}
