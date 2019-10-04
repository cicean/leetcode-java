/**
 * 	273	Integer to English Words	15.1%	Medium
 * Problem Description
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Solution
To solve it, we divide the input number into chunks so that each has 3 digits.
One note about this LeetCode problem is that some edge cases such as "101" is considered as "One Hundred One" instead of "One Hundred And One". 
So this should not be correct in real life application!

这题通过率之所以非常低是因为有很多corner case，代码写得不好时就很容易在各种corner case上刷跟头，第二十遍才被Accept，自己还真是有耐心。然后看到讨论区中的解答，一对比，差距啊，努力努力再努力！ 
注意：不需要添加“and” 
大神漂亮的作品： 
https://leetcode.com/discuss/55462/my-clean-java-solution-very-easy-to-understand 

 * @author cicean
 *
 */
public class IntegertoEnglishWords {
	/**
	 * 
	 */
	String[] map1 = new String [] {"", " One", " Two", " Three", " Four", " Five",   
            " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve",   
            " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen",   
            " Eighteen", " Nineteen" };  
              
    String[] map2 = new String[] {"", "", " Twenty", " Thirty", " Forty", " Fifty", " Sixty",   
        " Seventy", " Eighty", " Ninety" };  
          
    String[] map3 = new String[] {"", " Thousand", " Million", " Billion" };  
    final String HUNDRED = " Hundred";  
      
    public String threeDigitToWords(int num){  
        String result = "";  
        if (num > 99){  
            result = map1[num / 100] + HUNDRED;  
        }  
        num %= 100;  
        if(num < 20){  
            result +=  map1[num];  
        }else {  
            result += map2[num/10] + map1[num%10];  
        }  
        return result;  
    }  
      
    public String numberToWords(int num) {  
        if (num == 0) return "Zero";  
        String result = "";  
          
        int i = 0; //check if it is thousand, million, billion  
        while(num != 0){  
            if(num % 1000 != 0)  
                result = threeDigitToWords(num % 1000) + map3[i] + result;  
            i++;  
            num /= 1000;  
        }  
        return result.trim();  
    }  
    
    /**
     * leecode method
     */
    private final String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords_1(int num) {
        if (num == 0)
            return "Zero";
        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                words = helper(num % 1000) + thousands[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return lessThan20[num] + " ";
        else if (num < 100)
            return tens[num / 10] + " " + helper(num % 10);
        else
            return lessThan20[num / 100] + " Hundred " + helper(num % 100);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
