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
	  * 这道题用DP解决思路会比较清晰，因为前后字符都是有联系的。
	  * 我们可以用一个boolean二维数组来表示两个字符串前几个字符之间的联系，比如
	  * dp[i][j]表示字符串s的前i个字符与字符串p的前j个字符组成的两个字符串是否匹配
	  * dp[s.length()][p.length()]即是字符串s与字符串p是否匹配。
	  * 字符串p中出现的字符会有以下几种情况：
	  * 
	  * 1. 正常字符，即不是'*'也不是'.'
这种比较好处理，只要两个字符串向前一位能够匹配的同时两字符串最后一个字符相等, 可得到 

dp[i][j] = dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)

2. 字符为'.'
这种相对也比较好处理，只要两个字符串向前一位能够匹配即可，因为'.'能匹配字符串s的任意字符，所以可得到

dp[i][j] = dp[i - 1][j - 1]

3. 字符为'*'
这种比较复杂，因为'*'能匹配任意个数的前面的字符。
所以我们可以先考虑能够匹配0个和1个前面字符的简单情况，比如

匹配1个表示取0个前面字符意味着忽略自己，可到
dp[i][j] = dp[i][j - 1];

匹配0个表示可以忽略自己及前面那个字符，可得
dp[i][j] = dp[i][j - 2];

再考虑匹配2个及2个以上前面字符的情况，这种情况可以这么考虑:
如果dp[i][j] = true是'*'匹配k(k>=2)个前面字符的结果，那么'*'匹配k-1个前面字符的结果也必须是true，所以条件之一便是dp[i - 1][j] == true，另外一个条件便是s的最后一个字符必须匹配p的'*'前面的字符，所以得到
dp[i][j] = dp[i -1][j] && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.'
	  * 
	  * 这道题关键是对'*'匹配p前面字符的理解及处理，另外对dp数组的初始化也是要注意的，我是比较喜欢把dp[0][j]与dp[i][0]单独考虑，当然也可以放在大loop里处理。
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
