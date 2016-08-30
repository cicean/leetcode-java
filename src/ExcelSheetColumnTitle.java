import java.util.Scanner;

/*
 168	Excel Sheet Column Title	18.1%	Easy
 Problem:    Excel Sheet Column Title 
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/excel-sheet-column-title/
 Notes:
 Given a non-zero positive integer, return its corresponding column title as appear in an Excel sheet.
 For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 Solution: 1. Iteration.
           2&3. recursion.
 */

public class ExcelSheetColumnTitle {
	
	public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while (n > 0) {
            sb.insert(0,(char)((n - 1)%26 + 'A'));
            n  = (n - 1) / 26;
        }
        return sb.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExcelSheetColumnTitle slt = new ExcelSheetColumnTitle();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please inpute a Excel Shee tColumn Number");
		int n = sc.nextInt();
		System.out.print(slt.convertToTitle(n));
		
	}

}
