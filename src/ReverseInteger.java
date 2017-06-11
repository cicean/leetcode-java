/**
 7. Reverse Integer  QuestionEditorial Solution  My Submissions
 Total Accepted: 165107
 Total Submissions: 694049
 Difficulty: Easy
 Reverse digits of an integer.

 Example1: x = 123, return 321
 Example2: x = -123, return -321

 click to show spoilers.

 Have you thought about this?
 Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

 If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

 Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

 For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

 Update (2014-11-10):
 Test cases had been added to test the overflow behavior.

 Hide Company Tags Bloomberg Apple
 Hide Tags Math
 Hide Similar Problems (E) String to Integer (atoi)

 Solution: Use % and / iteratively.
 
 ����û�������Щ�أ�
������һЩ����֮ǰҪ�ʵĺ����⡣������Ѿ���������Щ���������ָ��㣡
������������һλ��0��Ӧ�����ʲô������10��100�������
����û��ע�⵽��ת���������������������������һ��32λ����������1000000003��ת�������Ӧ����δ������������
�׳�һ���쳣������������������׳��쳣�أ���ô�����������ƺ����������һ������Ĳ�������
 
 */

public class ReverseInteger {
	
	public int reverse_1 (int x){
		long res = 0;
		while (x !=0){
			res = res*10+ Long.valueOf(x%10);
			x = x/10;
		}
		if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) return 0;
		return (int) res;
		
	}
	
	public int reverse_2(int x) {
		// Note: The Solution object is instantiated only once and is reused by
		// each test case.

		if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) return 0;

		int result = 0;

		int flag = 0;
		if (x < 0) {
			flag = 1;
			x = -x;
		}

		int lastDigit = 0;
		
		while (x > 0) {
			lastDigit = x - x / 10 * 10;
			result = result * 10 + lastDigit;
			x /= 10;
		}

		if (flag == 1) {
			result = -result;
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		ReverseInteger slt = new ReverseInteger();

		int x = 10;
		int res = slt.reverse_1(x);
		System.out.print(res);
	}
	
	

}
