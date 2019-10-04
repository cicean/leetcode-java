import java.util.Scanner;

/*
 43	Multiply Strings	21.0%	Medium
 Problem:    Multiply Strings
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/multiply-strings/
 Notes:
 Given two numbers represented as strings, return multiplication of the numbers as a string.
 Note: The numbers can be arbitrarily large and are non-negative.
 Solution: Just like what we do when multiplying integers.
 */

public class MultiplyStrings {
	
	/**
	 * ���Ӷ�
	 * ʱ�� O(NM) �ռ� O(N+M)
	 * ˼·
	 * ����ļ������ڸ���ͬһ������������ϴεļ���������Ϊ������ÿһλ���ֺͳ�����˵Ľ�������δ��ģ����Ծ�û���⡣
	 * ע��
	 * ת����StringǰҪ�Ȱ�ǰ���0ȥ��������һλ��0��ȥ��
	 * @param num1
	 * @param num2
	 * @return
	 */
	
	public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null) return null;
        int len1 = num1.length(), len2 = num2.length();
        // �����λ������������������λ��֮��
        int len3 = len1 + len2;
        int[] res = new int[len3];
        int carry = 0, i = 0, j = 0;
        for(i = len1 - 1; i >= 0; i--){
            // ����carryλΪ0
            carry = 0;
            for(j = len2 - 1; j >= 0; j--){
                // ÿһλ�ĳ˻�������֮ǰ��һλ�Ľ��������carry���ټ�����һλ�ͳ����ĳ˻�
                int product = carry + res[i + j + 1] + (num1.charAt(i) - '0')*(num2.charAt(j) - '0');
                res[i + j + 1] = product % 10;
                carry = product / 10;
            }
            // ������carryλ����
            res[i + j + 1] = carry;
        }
        StringBuilder resstr = new StringBuilder();
        i = 0;
        // ����ǰ�����õ�0
        while(i < len3 - 1 && res[i] == 0){
            i++;
        }
        for(; i < len3; i++){
            resstr.append(res[i]);
        }
        return resstr.toString();
    }
	
	/**
	 * Start from right to left, perform multiplication on every pair of digits, and add them together. Let's draw the process! From the following draft, we can immediately conclude:

 `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]` 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply_2(String num1, String num2) {
	    int m = num1.length(), n = num2.length();
	    int[] pos = new int[m + n];
	   
	    for(int i = m - 1; i >= 0; i--) {
	        for(int j = n - 1; j >= 0; j--) {
	            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
	            int p1 = i + j, p2 = i + j + 1;
	            int sum = mul + pos[p2];

	            pos[p1] += sum / 10;
	            pos[p2] = (sum) % 10;
	        }
	    }  
	    
	    StringBuilder sb = new StringBuilder();
	    for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
	    return sb.length() == 0 ? "0" : sb.toString();
	}
	
	public String multiply_3(String num1, String num2) {
        int l1 = num1.length(), l2 = num2.length();
        if (l1 == 0 || l2 == 0) return new String("");
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') return new String("0");
        StringBuffer sb = new StringBuffer();
        int[] res = new int[l1+l2];
        for (int i = 0; i < l1; ++i) {
            for (int j = 0; j < l2; ++j) {
                res[i+j+1] += (num1.charAt(i)-'0') *(num2.charAt(j)-'0');
            }
        }
        int c = 0;
        for (int i = res.length - 1; i>=1; --i) {
            res[i] += c;
            c = res[i] / 10;
            res[i] = res[i] % 10;
            sb.insert(0,res[i]);
        }
        if (c !=0 || res[0] != 0) {
            sb.insert(0,c+res[0]);
        }
        return sb.toString();
    }
	
	public static void main(String[] args)
	{
		MultiplyStrings slt = new MultiplyStrings();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input an string number 1");
		String num1 = sc.nextLine();
		System.out.println("Please input an string number 2");
		String num2 = sc.nextLine();
		String res = slt.multiply(num1, num2);
		System.out.println(res);
		
    	
	}  
}
