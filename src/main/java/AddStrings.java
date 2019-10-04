/**
 * Created by cicean on 10/16/2016.
 * 415. Add Strings   QuestionEditorial Solution  My Submissions
 Total Accepted: 5666
 Total Submissions: 13309
 Difficulty: Easy
 Contributors: Admin
 Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:

 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 Hide Company Tags Google Airbnb
 Hide Tags Math
 Hide Similar Problems (M) Add Two Numbers (M) Multiply Strings

 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 && j >= 0) {
            int sum = num1.charAt(i) - '0' + (num2.charAt(j) - '0') + carry;
            res.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }

        while (i >= 0) {
            int sum = num1.charAt(i) - '0' + carry;
            res.append(sum % 10);
            carry = sum / 10;
            i--;
        }

        while (j >= 0) {
            int sum = num2.charAt(j) - '0' + carry;
            res.append(sum % 10);
            carry = sum / 10;
            j--;
        }

        if (carry > 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }
}
