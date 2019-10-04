/*
 * 	214	Shortest Palindrome	16.9%	Hard
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example, given "aacecaaa", return "aaacecaaa"; given "abcd", return "dcbabcd".

Analysis

1.1We can solve this problem by using one of the methods which is used to solve the longest palindrome substring problem.

Specifically, we can start from the center and scan two sides. If read the left boundary, then the shortest palindrome is identified.
 
1.2 从某个char开始向两边扩展(左右两边的字符相等), 如果能一直扩展到字符串的头部, 则将末尾余下的reverse,再加到原字符串的头部,即可. 
tips:  1. 中轴字符选从中间开始,这样找到的即为最短的. 2. 中轴字符可能为一个, 也可能为两个. 

第二种方法“是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙，例如aba是回文，abba也是回文，
这两种情况要分情况考虑）往两边同时进 行扫描，直到不是回文串为止。假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。
对于每个中心往两边扫描的复杂 度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)。”
引自Code ganker（http://codeganker.blogspot.com/2014/02/longest-palindromic-substring-leetcode.html）
 */
public class ShortestPalindrome {

	public String shortestPalindrome_1(String s) {
		if (s == null || s.length() <= 1)
			return s;
	 
		String result = null;
	 
		int len = s.length();
		int mid = len / 2;	
	 
		for (int i = mid; i >= 1; i--) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				if ((result = scanFromCenter(s, i - 1, i)) != null)
					return result;
			} else {
				if ((result = scanFromCenter(s, i - 1, i - 1)) != null)
					return result;
			}
		}
	 
		return result;
	}
	 
	private String scanFromCenter(String s, int l, int r) {
		int i = 1;
	 
		//scan from center to both sides
		for (; l - i >= 0; i++) {
			if (s.charAt(l - i) != s.charAt(r + i))
				break;
		}
	 
		//if not end at the beginning of s, return null 
		if (l - i >= 0)
			return null;
	 
		StringBuilder sb = new StringBuilder(s.substring(r + i));
		sb.reverse();
	 
		return sb.append(s).toString();
	}
	
	public String shortestPalindrome_2(String s) {  
        if(s.length()<=1 ) return s;  
        int center = (s.length()-1)/2;  
        String res="";  
        for(int i=center; i>=0; i--) {  
            if(s.charAt(i) == s.charAt(i+1)) {  
                if( (res = check1(s, i, i+1)) !=null) return res;  
            }  
            if( (res = check1(s, i, i)) !=null) return res;  
  
        }  
        return res;  
    }  
    //aabaac  
    private String check1(String s, int l, int r) {  
        int i=1;  
        for(; l-i>=0; i++) {  
            if(s.charAt(l-i) != s.charAt(r+i) ) break;  
        }  
          
        if(l-i>=0) return null;   
        StringBuilder sb = new StringBuilder(s.substring(r+i));  
        sb.reverse();  
        return sb+s;  
    } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShortestPalindrome slt= new ShortestPalindrome();
		System.out.println(slt.shortestPalindrome_2("aacecaaa"));
		System.out.println(slt.shortestPalindrome_2("abcd"));
	}

}
