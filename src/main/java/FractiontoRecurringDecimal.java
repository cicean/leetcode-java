import java.util.HashMap;
import java.util.Map;

/**
 * 166	Fraction to Recurring Decimal	12.7%	Medium
 *
 * 0.Problem:
 * Given two integers representing the numerator and denominator of 
 * a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part 
 * in parentheses.
 * 
 * For example,
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 * 
 * 1.Refer.:
 * 正常计算，保存余数至HashMap记录循环起至位置
 * 【中文解析】
难点：如何识别循环体？

解决方法：用一个HashMap记录每一个余数，当出现重复的余数时，那么将会进入循环，两个重复余数之间的部分就是循环体。

示例：1/13=0.076923076923076923...，当小数部分第二次出现0时，就意味着开始了循环，那么需要把076923用括号括起来，结果为0.(076923)。

涉及技巧：1）在不断相除的过程中，把余数乘以10再进行下一次相除，保证一直是整数相除；2）HashMap的key和value分别是<当前余数, 对应结果下标>，这样获取076923时就可根据value值来找。

注意点1：考虑正负数，先判断符号，然后都转化为正数；

注意点2：考虑溢出，如果输入为Integer.MIN_VALUE，取绝对值后会溢出。
 * 
 */

public class FractiontoRecurringDecimal {

	public String fractionToDecimal(int numerator, int denominator) {  
        if (numerator == 0) return "0";  
        if (denominator == 0) return "";  
          
        String ans = "";  
          
        //if the result is negative  
        if ((numerator < 0) ^ (denominator < 0)) {  
            ans += "-";  
        }  
          
        //下面要把两个数都转为正数，为避免溢出，int转为long  
        long num = numerator, den = denominator;  
        num = Math.abs(num);  
        den = Math.abs(den);  
          
        //结果的整数部分  
        long res = num / den;  
        ans += String.valueOf(res);  
          
        //如果能够整除，返回结果  
        long rem = (num % den) * 10;  
        if (rem == 0) return ans;  
          
        //结果的小数部分  
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();  
        ans += ".";  
        while (rem != 0) {  
            //如果前面已经出现过该余数，那么将会开始循环  
            if (map.containsKey(rem)) {  
                int beg = map.get(rem); //循环体开始的位置  
                String part1 = ans.substring(0, beg);  
                String part2 = ans.substring(beg, ans.length());  
                ans = part1 + "(" + part2 + ")";  
                return ans;  
            }  
              
            //继续往下除  
            map.put(rem, ans.length());  
            res = rem / den;  
            ans += String.valueOf(res);  
            rem = (rem % den) * 10;  
        }  
          
        return ans;  
    }  
	
	public static String fractionToDecimal_1(int numerator, int denominator) {
        if (0 == denominator) return null;
        int sign = 0;
        if ((0 > numerator && 0 > denominator) || (0 < numerator && 0 < denominator)) {
            sign = 1;
        } else if ((0 > numerator && 0 < denominator) || (0 < numerator && 0 > denominator)) {
            sign = -1;
        }
        long num = numerator < 0 ? -1L * numerator : numerator;
        long den = denominator < 0 ? -1L * denominator : denominator;
        long dev = num / den;
        long rem = num % den;
        Map<Long,Integer> rems = new HashMap<Long, Integer>();
        StringBuffer sb = new StringBuffer();
        int index = 0;
        int start = 0;
        int end = 0;
        while (0 != rem) {
            Integer last = rems.get(rem);
            if (null != last) {
                start = last;
                end = index;
                break;
            }
            rems.put(rem, index);
            rem = rem * 10;
            sb.append(rem / den);
            rem = rem % den;
            index = index + 1;
        }
        StringBuffer ret = new StringBuffer();
        if (sign < 0) {
            ret.append("-");
        }
        ret.append(dev);
        if (0 == start && 0 == end) {
            if (0 < sb.length()) {
                ret.append(".");
                ret.append(sb);
            }
        } else {
            ret.append(".");
            ret.append(sb.substring(0, start));
            ret.append("(");
            ret.append(sb.substring(start));
            ret.append(")");
        }
        return ret.toString();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ns = {-1,1,0,2,3,6,7,11,-1,1};
        int[] ds = {-2147483648,0,1,3,3,2,2,3,2,-2};
        FractiontoRecurringDecimal slt = new FractiontoRecurringDecimal();
        for (int i = 0; i < ns.length; i++) {
            System.out.println(slt.fractionToDecimal(ns[i],ds[i]));
        }
	}

}
