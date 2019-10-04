/*
 186	Reverse Words in a String II 	31.0%	Medium
 Problem:    Reverse Words in a String 
 Difficulty: Medium
 Source:     https://oj.leetcode.com/proble ... g-ii/
 Notes:
 Question: Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces and the words are always separated by a single space.
For example,
Given s = "the sky is blue",
return "blue is sky the".
Could you do it in-place without allocating extra space?

Idea: Reverse twice, both in place.


Time: O(n) Space: O(1).
 Solution: 1）如果是Java，应该跟面试官指出String是immutable，所以需要用char array来做。
2）follow-up问题：k-step reverse。也就是在第二部翻转的时候，把k个单词看作一个长单词，进行翻转
*/

public class ReverseWordsinaStringII {
	
	/**
	 * 复杂度
时间 O(N) 空间 O(1)

思路
这题就是Java版的Inplace做法了，先反转整个数组，再对每个词反转。
	 * @param s
	 */
	public void reverseWords(char[] s) {  
	     if(s==null||s.length==0)  
	       return;  
	     reverse(s,0,s.length-1);  
	     for(int left=0;left<s.length;left++)  
	     {  
	       if(s[left]!=' ')  
	       {  
	         int right=left;  
	         while(right<s.length&&s[right]!=' ')  
	           right++;  
	         reverse(s,left,right-1);  
	         left=right;  
	       }  
	     }  
	   }  
	   public void reverse(char[] chars, int start,int end)  
	   {  
	     for(int i=start,j=end;i<j;i++,j--)  
	     {  
	       char tmp=chars[i];  
	       chars[i]=chars[j];  
	       chars[j]=tmp;  
	     }  
	   }  
	   public static void main(String[] args) {
			// TODO Auto-generated method stub
		   ReverseWordsinaStringII slt = new ReverseWordsinaStringII();
			String s = "the sky is blue";
			char[] ch = s.toCharArray();
			slt.reverseWords(ch);
			for(int i=0;i<ch.length;i++){
				System.out.print(ch[i]);
			}
			
			
			}

}
