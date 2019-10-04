import java.util.Scanner;

/*
 171	Excel Sheet Column Number	36.5%	Easy
 Problem:    Excel Sheet Column Number
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/excel-sheet-column-number/
 Notes:
 Related to question Excel Sheet Column Title
 Given a column title as appear in an Excel sheet, return its corresponding column number.
 For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
 Solution: 1. ...
*/

public class ExcelSheetColumnNumber {

	public int titleToNumber(String s) {
        int res = 0;
        if (s.length() == 0) return 0;
        for (int i = 0; i < s.length(); ++i) {
            res = res * 26 + s.charAt(i) - 'A' + 1;
        }
        return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExcelSheetColumnNumber slt = new ExcelSheetColumnNumber();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please inpute Excel sheet column title");
		String s = sc.nextLine();
		System.out.print(slt.titleToNumber(s));
		
	}

}
