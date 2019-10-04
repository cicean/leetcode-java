/*
 8	String to Integer (atoi)	13.1%	Easy
 Problem:    String to Integer (atoi)
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/string-to-integer-atoi/solution/
 Notes:
 Implement atoi to convert a string to an integer.
 Hint: Carefully consider all possible input cases. If you want a challenge, please do not 
 see below and ask yourself what are the possible input cases.
 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
 You are responsible to gather all the input requirements up front.
 Requirements for atoi:
 The function first discards as many whitespace characters as necessary until the first 
 non-whitespace character is found. Then, starting from this character, takes an optional
 initial plus or minus sign followed by as many numerical digits as possible, and interprets 
 them as a numerical value.
 The string can contain additional characters after those that form the integral number, which 
 are ignored and have no effect on the behavior of this function.
 If the first sequence of non-whitespace characters in str is not a valid integral number, or 
 if no such sequence exists because either str is empty or it contains only whitespace characters, 
 no conversion is performed.
 If no valid conversion could be performed, a zero value is returned. If the correct value is out 
 of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 Solution: 1. To deal with overflow, inspect the current number before multiplication. 
            If the current number is greater than 214748364, we know it is going to overflow.
            On the other hand, if the current number is equal to 214748364, 
            we know that it will overflow only when the current digit is greater than or equal to 8..
 */

public class StringtoIntegeratoi {

    public int reverse_atoi(String str) {
        if (str == null) return 0;
        int i = 0;
        boolean sign = true;
        str = str.trim();
        int n = str.length();
        if (i < n && (str.charAt(0) == '+' || str.charAt(0) == '-')) {
            if (str.charAt(0) == '+') sign = true;
            else sign = false;
            ++i;
        }
        int res = 0;
        while (i < n && Character.isDigit(str.charAt(i))) {
            if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > Integer.MAX_VALUE % 10)){
                return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE ;
            }
            res = res * 10 + str.charAt(i) - 48;
            ++i;
        }
        return sign ? res : -res;
    }
    
    public int reverse_atoi_2(String str) {
		String s = str.trim();

		if (s.length() == 0) {
			return 0;
		}

		int INT_MAX = Integer.MAX_VALUE;
		int INT_MIN = Integer.MIN_VALUE;
		
		int sign = 1;
		int res = 0;
		
		int i = 0;
		if (s.charAt(0) == '-') {
			sign = -1;
			++i;
		} else if (s.charAt(0) == '+') {
			++i;
		}

		for (; i < s.length(); ++i) {
			char digit = s.charAt(i);
			if (digit >= '0' && digit <= '9') {
				if (res > INT_MAX / 10 || digit - '0' > INT_MAX - res * 10) {
					return sign == -1 ? INT_MIN : INT_MAX;
				}

				res = res * 10 + (digit - '0');
			} else {
				break;
			}
		}

		return sign * res;
	}
    
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        //1. Empty string
        if(str.length() == 0) return 0;

        //2. Remove Spaces
        while(str.charAt(index) == ' ' && index < str.length())
            index ++;

        //3. Handle signs
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }

        //4. Convert number and avoid overflow
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }

    public int myAtoi_1(String str) {
        str = str.trim();
        if (str.isEmpty()) 
            return 0;
        int sign = 1; int i = 0;
        if (str.charAt(0) == '-' || str.charAt(0) == '+'){
            sign = (str.charAt(0) == '-')? -1 : 1;
            if (str.length() < 2 || !Character.isDigit(str.charAt(1))) {
                return 0;
            }
            i++;
         }
        int n = 0;
        while (i < str.length()) {
            if (Character.isDigit(str.charAt(i))) {
                int d = str.charAt(i) - '0';
                if (n > (Integer.MAX_VALUE - d) / 10) { //Detect the integer overflow.
                    n = (sign == -1)? Integer.MIN_VALUE : Integer.MAX_VALUE;
                    return n;
                }
                n = n*10 + d;
            } else {
                break;
            }
            i++;
        }
        return sign * n;
    }
    
    public static void main(String[] args) {
    	StringtoIntegeratoi slt = new StringtoIntegeratoi();
		String str = "-2147483649";
		int result = slt.reverse_atoi(str);
		System.out.print(result);
	}
}
