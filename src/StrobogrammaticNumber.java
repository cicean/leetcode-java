/*
 * 246	Strobogrammatic Number 	31.7%	Easy
 * Question:
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.


Hints:
two pointers
 */
public class StrobogrammaticNumber {

	public  boolean isStrobogrammaticNumber(String num) {
		for (int i = 0; i <= num.length()/2; i++) {
			char a = num.charAt(i);
			char b = num.charAt(num.length() - 1 - i);
			if (!isValid(a, b)) {
				return false;
			}
		
		}
		
		return true;
	}
	
	private boolean isValid(char a, char b) {
		switch (a) {
		case '1':
			return b == '1';
		case '6':
			return b == '9';
		case '9':
			return b == '6';
		case '8':
			return b == '8';
		case '0':
		    return b == '0';
		default:
			return false;
		}
	}

	public boolean isStrobogrammatic(String num) {
		for (int i=0, j=num.length()-1; i <= j; i++, j--)
			if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num = "88";
		StrobogrammaticNumber slt = new StrobogrammaticNumber();
		System.out.println(slt.isStrobogrammaticNumber(num));
	}

}
