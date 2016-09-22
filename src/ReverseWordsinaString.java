import java.util.HashMap;

/*
 151	Reverse Words in a String	15.1%	Medium
 Problem:    Reverse Words in a String 
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/reverse-words-in-a-string/
 Notes:
 Given an input string, reverse the string word by word.
 For example,
 Given s = "the sky is blue",
 return "blue is sky the".
 
 Solution: 1. Reverse the raw string and reverse each word.
           2. Cannot do it In Place by Java. oops~.~
*/

/**
 * Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

Hide Company Tags Microsoft Snapchat Apple Bloomberg Yelp
Hide Tags String
Hide Similar Problems (M) Reverse Words in a String II

 * @author cicean
 *
 */

public class ReverseWordsinaString {
	
	/**
	 * 复杂度
时间 O(N) 空间 O(N)

思路
将单词根据空格split开来存入一个字符串数组，然后将该数组反转即可。

注意
先用trim()将前后无用的空格去掉
用正则表达式" +"来匹配一个或多个空格
	 * @param s
	 * @return
	 */
	 public String reverseWords(String s) { 
	        StringBuffer sb = new StringBuffer();
	        for (int i = s.length() - 1; i >= 0;) {
	            while (i >= 0 && s.charAt(i) == ' ') --i;
	            StringBuffer temp = new StringBuffer();
	            while (i >= 0 && s.charAt(i) != ' ') {
	                temp.append(s.charAt(i--));
	            }
	            temp.reverse();
	            if (sb.length() > 0 && temp.length() > 0) sb.append(" ");
	            sb.append(temp);
	        }
	        return sb.toString();
	    } 
	 
	 public String reverseWords_(String s) {
	        String[] words = s.trim().split(" +");
	        int len = words.length;
	        StringBuilder result = new StringBuilder();
	        for(int i = len -1; i>=0;i--){
	            result.append(words[i]);
	            if(i!=0) result.append(" ");
	        }
	        return result.toString();
	    }
	 
	 /**
	  * 
	  * @param s
	  * @return
	  */
	 public String reverseWords_1(String s) { 
	       String[] tokens = s.split(" ");
	       for (int i=0; i < tokens.length; i++) {
	    	   System.out.print(tokens[i] + " ");
	       }
	       HashMap<Integer,String> hm = new HashMap<Integer, String>();
	       StringBuffer sb = new StringBuffer();
	       String res = "";
	       int i = 0;
	       for(String words : tokens) {
	    	   hm.put(i, words);
	    	   i++;
	       }
	       System.out.print(i);
	       
	       for (int j = i-1; j >= 0; j --) {
	    	   if (hm.isEmpty()){
	    		   return res;
	    	   } else {
	    	   sb.append(hm.get(j) + " ");
	       }
	    	   
	       }
	    	   
	       return res = sb.toString();
	       
	    }

	public String reverseWords_mw(String s) {
		if (s == null || s.length() == 0) return "";

		String res = "";
		String[] split = s.trim().split(" +");
		for (int i = split.length - 1; i >=0; i--) {
			if ( i == 0) {
				res += split[i];
			} else {
				res += split[i] + " ";
			}
		}

		return res;
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseWordsinaString slt = new ReverseWordsinaString();
		String s = "the sky is blue";
		System.out.print(slt.reverseWords_1(s));
		
		}

}
