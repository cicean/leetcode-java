/*
 91	Decode Ways	16.4%	Medium
 Problem:    Decode Ways
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/decode-ways/
 Notes:
 A message containing letters from A-Z is being encoded to numbers using the following mapping:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.
 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 The number of ways decoding "12" is 2.
 Solution: 1. dp. Time : O(n); Space : O(1).
 
 	�����ַ�ʽ����һ���¼ӽ��������ֲ�Ȼ�����Լ��Ƚϱ�ʾһ���ַ�����ô�����ķ�ʽ��res[i-1]�֣��ڶ��־����¼ӽ��������ֺ�ǰһ�����ִճ�һ���ַ���
 	�����ķ�ʽ��res[i-2]�֣���Ϊ��һ���ַ����Լ��ճ���һ��������Ȼ����Ҫ�ж�ǰ��˵����������ܷ�ճ�һ���ַ���Ҳ���Ƿ�Χ���жϣ�
 	������Բ��ж�Ӧ�Ľ�����ʽ��������У���ô����0�����ս�����ǰ������������Ӧ�Ľ�����ʽ��ӡ�������԰ѷ�Χ�ֳɼ������䣺
	��1��00��res[i]=0���޷�������û�п��н�����ʽ����
	��2��10, 20��res[i]=res[i-2]��ֻ�еڶ��������������
	��3��11-19, 21-26��res[i]=res[i-1]+res[i-2]��������������У���
	��4��01-09, 27-99��res[i]=res[i-1]��ֻ�е�һ��������У���
	����ʽ���ǰ�������Ĺ������õ�������������ֻҪ����һ��ɨ�裬Ȼ�����εõ�ά�����Ϳ����ˣ��㷨��ʱ�临�Ӷ���O(n)��
	�ռ��Ͽ��Կ�������ÿ��ֻ��Ҫǰ��λ����ʷ��Ϣ������ֻ��Ҫά����������Ȼ�������ֵ�Ϳ����ˣ����Կռ临�Ӷ���O(1)
 */


public class DecodeWays {
	
	public static int numDecodings_1(String s) {
    	if ((0 == s.length()) || (1 == s.length() && '0' == s.charAt(0))) return 0;
    	if (1 == s.length()) return 1;
    	char a = s.charAt(s.length() - 1);
    	char b = s.charAt(s.length() - 2);
    	int c = (b - '0') * 10 + a - '0';
    	if (2 == s.length()) {
    		if ('0' == a) return 1;
    		else if (c > 10 && c <= 26) {
    			return 2;
    		} else {
    			return 1;
    		}
    	} 
    	if ('0' == a) {
    		if (2 == s.length()) {
    			return 1;
    		} else {
    			return numDecodings_1(s.substring(0, s.length() - 2));
    		}
    	} else if (c > 26 || c < 10) {
    		return numDecodings_1(s.substring(0, s.length() - 1));
    	} else {
    		return numDecodings_1(s.substring(0, s.length() - 1)) + numDecodings_1(s.substring(0, s.length() - 2));
    	}
    }    
    
    public static int numDecodings3(String s) {
    	if (null == s || 0 == s.length()) return 0;
    	int len = s.length();
    	if (1 == len) {
    		if ('0' == s.charAt(0)) return 0;
    		else return 1;
    	}
    	int[] num = new int[len];
    	if ('0' == s.charAt(0)) {
    		return 0;
    	} else {
    		num[0] = 1;
    	}
    	char a = s.charAt(0);
    	char b = s.charAt(1);
    	int c = (a - '0') * 10 + b - '0';
    	if ('0' == s.charAt(1)) {
    		if (c >= 10 && c < 27) {
    			num[1] = 1;
    		} else {
    			return 0;
      		}
    	} else if (c >= 10 && c < 27) {
    		num[1] = 2;
    	} else {
    		num[1] = 1;
    	}
    	
    	for (int i = 2; i < len; i++) {
        	char d = s.charAt(i - 1);
        	char e = s.charAt(i);
        	int f = (d - '0') * 10 + e - '0';
        	if ('0' == e) {
        		if (f >= 10 && f < 27) {
        			num[i] = num[i - 2];
        		} else {
        			return 0;
        		}
        	} else if (f >= 10 && f < 27) {
        		num[i] = num[i - 1] + num[i - 2];
        	} else {
        		num[i] = num[i - 1];
        	}
    	}
    	return num[len - 1];
    }
	
	 public int numDecodings(String s) {
	        if (s.length() == 0 || s.charAt(0) == '0') return 0;
	        int N = s.length();
	        int f0 = 1, f1 = 1;
	        for (int i = 1; i < N; ++i) {
	            if (s.charAt(i) == '0') f1 = 0;
	            int num = s.charAt(i) - '0' + (s.charAt(i-1) - '0') * 10;
	            if (num < 10 || num > 26) {
	                f0 = 0;
	            }
	            int tmp = f1;
	            f1 = f1 + f0;
	            f0 = tmp;
	        }
	        return f1;
	    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecodeWays slt = new DecodeWays();
		String s= "2201";
		int res = slt.numDecodings3(s);
		System.out.print(res);
	}

}
