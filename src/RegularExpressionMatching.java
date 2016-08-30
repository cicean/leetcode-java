import java.util.Scanner;

/*
 10	Regular Expression Matching	20.7%	Hard
 
 Problem:    Regular Expression Matching
 Difficulty: Hard
 Source:     https://oj.leetcode.com/problems/regular-expression-matching/
 Notes:
 Implement regular expression matching with support for '.' and '*'.
 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).
 The function prototype should be:
 bool isMatch(const char *s, const char *p)
 Some examples:
 isMatch("aa","a") ? false
 isMatch("aa","aa") ? true
 isMatch("aaa","aa") ? false
 isMatch("aa", "a*") ? true
 isMatch("aa", ".*") ? true
 isMatch("ab", ".*") ? true
 isMatch("aab", "c*a*b") ? true
 Solution: 1. Recursion.
           2. DP.
*/

public class RegularExpressionMatching {

	 public boolean isMatch_1(String s, String p) {
	        if (p.length() == 0) return s.length() == 0;
	        if (p.length() == 1) {
	            if (s.length() != 1) return false;
	            return (s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.');
	        }
	        if (s.length() != 0 && (p.charAt(0) == s.charAt(0) || (p.charAt(0) == '.'))) {
	            if (p.charAt(1) == '*')
	                return isMatch_1(s.substring(1),p) || isMatch_1(s, p.substring(2));
	            return isMatch_1(s.substring(1), p.substring(1));
	        }
	        return p.charAt(1) == '*' && isMatch_1(s, p.substring(2));
	    }
	 
	 /**
	  * �������DP���˼·��Ƚ���������Ϊǰ���ַ���������ϵ�ġ�
	  * ���ǿ�����һ��boolean��ά��������ʾ�����ַ���ǰ�����ַ�֮�����ϵ������
	  * dp[i][j]��ʾ�ַ���s��ǰi���ַ����ַ���p��ǰj���ַ���ɵ������ַ����Ƿ�ƥ��
	  * dp[s.length()][p.length()]�����ַ���s���ַ���p�Ƿ�ƥ�䡣
	  * �ַ���p�г��ֵ��ַ��������¼��������
	  * 
	  * 1. �����ַ���������'*'Ҳ����'.'
���ֱȽϺô���ֻҪ�����ַ�����ǰһλ�ܹ�ƥ���ͬʱ���ַ������һ���ַ����, �ɵõ� 

dp[i][j] = dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)

2. �ַ�Ϊ'.'
�������Ҳ�ȽϺô���ֻҪ�����ַ�����ǰһλ�ܹ�ƥ�伴�ɣ���Ϊ'.'��ƥ���ַ���s�������ַ������Կɵõ�

dp[i][j] = dp[i - 1][j - 1]

3. �ַ�Ϊ'*'
���ֱȽϸ��ӣ���Ϊ'*'��ƥ�����������ǰ����ַ���
�������ǿ����ȿ����ܹ�ƥ��0����1��ǰ���ַ��ļ����������

ƥ��1����ʾȡ0��ǰ���ַ���ζ�ź����Լ����ɵ�
dp[i][j] = dp[i][j - 1];

ƥ��0����ʾ���Ժ����Լ���ǰ���Ǹ��ַ����ɵ�
dp[i][j] = dp[i][j - 2];

�ٿ���ƥ��2����2������ǰ���ַ���������������������ô����:
���dp[i][j] = true��'*'ƥ��k(k>=2)��ǰ���ַ��Ľ������ô'*'ƥ��k-1��ǰ���ַ��Ľ��Ҳ������true����������֮һ����dp[i - 1][j] == true������һ����������s�����һ���ַ�����ƥ��p��'*'ǰ����ַ������Եõ�
dp[i][j] = dp[i -1][j] && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.'
	  * 
	  * �����ؼ��Ƕ�'*'ƥ��pǰ���ַ�����⼰���������dp����ĳ�ʼ��Ҳ��Ҫע��ģ����ǱȽ�ϲ����dp[0][j]��dp[i][0]�������ǣ���ȻҲ���Է��ڴ�loop�ﴦ��
	  * 
	  * O(n^2), space: O(n^2)
	  * @param s
	  * @param p
	  * @return
	  */
	    public boolean isMatch_2(String s, String p) {
	        if (p.length() == 0) return s.length() == 0;
	        int sLen = s.length(), pLen = p.length();
	        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
	        dp[0][0] = true;
	        for (int i = 2; i <= pLen; ++i) {
	            dp[0][i] = dp[0][i-2] && p.charAt(i-1) == '*';
	        }
	        for (int i = 1; i <= sLen; ++i) {
	            for (int j = 1; j <= pLen; ++j) {
	                char ch1 = s.charAt(i-1), ch2 = p.charAt(j-1);
	                if (ch2 != '*') dp[i][j] = dp[i-1][j-1] && (ch1 == ch2 || ch2 == '.');
	                else {
	                    dp[i][j] = dp[i][j-2];
	                    if (ch1 == p.charAt(j-2) || p.charAt(j-2) == '.')
	                        dp[i][j] = dp[i][j] | dp[i-1][j];
	                }
	            }
	        }
	        return dp[sLen][pLen];
	    }
	    
	    public static void main(String[] args)
		{
	    	
	    	RegularExpressionMatching slt = new RegularExpressionMatching();
	    	Scanner sc =  new Scanner(System.in);
	    	System.out.println("Please input the frist String");
	    	String s=sc.nextLine();
	    	System.out.println("Please input the target String");
	    	String p=sc.nextLine();
	    	boolean res = slt.isMatch_1(s,p);
	    	System.out.print(res);
			
		}  
}
