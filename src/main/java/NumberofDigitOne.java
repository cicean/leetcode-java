/*
 * 233	Number of Digit One	18.9%	Medium
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
 * ͨ��������о����ǿ��Է��֣�100���ڵ����֣�����10-19֮����11����1��֮�⣬���඼ֻ��1����
 * ������ǲ�����[10, 19]�������Ƕ������10����1���Ļ�����ô�����ڶ�����һ����λ����ʮλ���ϵ�����(��1)�ʹ���1���ֵĸ�����
 * ��ʱ�������ٰѶ����10�����ϼ��ɡ�����56����(5+1)+10=16����
 * ���֪���Ƿ�Ҫ���϶����10���أ����Ǿ�Ҫ��ʮλ�ϵ������Ƿ���ڵ���2���ǵĻ���Ҫ���϶����10��'1'��
 * ��ô���ǾͿ�����(x+8)/10���ж�һ�����Ƿ���ڵ���2��������λ��Ҳ��һ��������[110, 119]֮������10����֮�⣬�����ÿ10���������䶼ֻ��11����1����
 * ��ô���ǿ�������ͬ�ķ������жϲ��ۼ�1�ĸ�����
 * ����������������� һ����ʽ����:
 * (a + 8) / 10 * m + (a % 10 == 1) * (b + 1); 
 */
public class NumberofDigitOne {

	public int countDigitOne(int n) {  
        int ones = 0;  
        for (long m = 1; m <= n; m *= 10) {  
            long a = n/m, b = n%m;  
            ones += (a + 8) / 10 * m;  
            if(a % 10 == 1) ones += b + 1;  
        }  
        return ones;  
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberofDigitOne slt = new NumberofDigitOne();
		System.out.print(slt.countDigitOne(13));
	}

}
