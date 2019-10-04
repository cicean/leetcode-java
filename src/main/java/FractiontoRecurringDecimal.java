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
 * �������㣬����������HashMap��¼ѭ������λ��
 * �����Ľ�����
�ѵ㣺���ʶ��ѭ���壿

�����������һ��HashMap��¼ÿһ���������������ظ�������ʱ����ô�������ѭ���������ظ�����֮��Ĳ��־���ѭ���塣

ʾ����1/13=0.076923076923076923...����С�����ֵڶ��γ���0ʱ������ζ�ſ�ʼ��ѭ������ô��Ҫ��076923�����������������Ϊ0.(076923)��

�漰���ɣ�1���ڲ�������Ĺ����У�����������10�ٽ�����һ���������֤һֱ�����������2��HashMap��key��value�ֱ���<��ǰ����, ��Ӧ����±�>��������ȡ076923ʱ�Ϳɸ���valueֵ���ҡ�

ע���1�����������������жϷ��ţ�Ȼ��ת��Ϊ������

ע���2������������������ΪInteger.MIN_VALUE��ȡ����ֵ��������
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
          
        //����Ҫ����������תΪ������Ϊ���������intתΪlong  
        long num = numerator, den = denominator;  
        num = Math.abs(num);  
        den = Math.abs(den);  
          
        //�������������  
        long res = num / den;  
        ans += String.valueOf(res);  
          
        //����ܹ����������ؽ��  
        long rem = (num % den) * 10;  
        if (rem == 0) return ans;  
          
        //�����С������  
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();  
        ans += ".";  
        while (rem != 0) {  
            //���ǰ���Ѿ����ֹ�����������ô���Ὺʼѭ��  
            if (map.containsKey(rem)) {  
                int beg = map.get(rem); //ѭ���忪ʼ��λ��  
                String part1 = ans.substring(0, beg);  
                String part2 = ans.substring(beg, ans.length());  
                ans = part1 + "(" + part2 + ")";  
                return ans;  
            }  
              
            //�������³�  
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
