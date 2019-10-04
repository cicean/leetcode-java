/**
 * Created by cicean on 9/25/2016.
 * 405. Convert a Number to Hexadecimal  QuestionEditorial Solution  My Submissions
 Total Accepted: 842
 Total Submissions: 1864
 Difficulty: Easy
 Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two¡¯s complement method is used.

 Note:

 All letters in hexadecimal (a-f) must be in lowercase.
 The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 The given number is guaranteed to fit within the range of a 32-bit signed integer.
 You must not use any method provided by the library which converts/formats the number to hex directly.
 Example 1:

 Input:
 26

 Output:
 "1a"
 Example 2:

 Input:
 -1

 Output:
 "ffffffff"
 */
public class ConvertaNumbertoHexadecimal {

    public String toHex(int num) {
        if (num == 0) return "0";
        StringBuilder res = new StringBuilder();

        while (num != 0) {
            int digit = num & 0xf;
            res.append(digit < 10 ? (char)(digit + '0') : (char)(digit - 10 + 'a'));
            System.out.println(num);
            num >>>= 4;
            System.out.println(num);
        }

        return res.reverse().toString();
    }
    
    //Java solution num & 0xffffffffL to long
    public String toHex_changeTolong(int num) {
        return num == 0 ? "0" : toHex(num & 0xffffffffL);
    }
    
    public String toHex(long num) {
        return num < 16 ? hexdigit(num) : toHex(num / 16) + hexdigit(num % 16);
    }
    
    private String hexdigit(long num) {
        assert num < 16;
        return num < 10 ? Character.toString((char)(num + '0')) : Character.toString((char)(num - 10 + 'a'));
    }
    
    public static void main(String[] args) {
		ConvertaNumbertoHexadecimal slt = new ConvertaNumbertoHexadecimal();
		slt.toHex(2046);
	}


}
