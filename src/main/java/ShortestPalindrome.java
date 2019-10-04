/*
 * 	214	Shortest Palindrome	16.9%	Hard
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example, given "aacecaaa", return "aaacecaaa"; given "abcd", return "dcbabcd".

Analysis

1.1We can solve this problem by using one of the methods which is used to solve the longest palindrome substring problem.

Specifically, we can start from the center and scan two sides. If read the left boundary, then the shortest palindrome is identified.
 
1.2 ��ĳ��char��ʼ��������չ(�������ߵ��ַ����), �����һֱ��չ���ַ�����ͷ��, ��ĩβ���µ�reverse,�ټӵ�ԭ�ַ�����ͷ��,����. 
tips:  1. �����ַ�ѡ���м俪ʼ,�����ҵ��ļ�Ϊ��̵�. 2. �����ַ�����Ϊһ��, Ҳ����Ϊ����. 

�ڶ��ַ������Ƕ���ÿ���Ӵ������ģ�������һ���ַ��������������ַ��ļ�϶�����紮abc,���Ŀ�����a,b,c,������ab�ļ�϶��bc�ļ�϶������aba�ǻ��ģ�abbaҲ�ǻ��ģ�
���������Ҫ��������ǣ�������ͬʱ�� ��ɨ�裬ֱ�����ǻ��Ĵ�Ϊֹ�������ַ����ĳ���Ϊn,��ô���ĵĸ���Ϊ2*n-1(�ַ���Ϊ������n������϶��n-1������
����ÿ������������ɨ��ĸ��� ��ΪO(n),����ʱ�临�Ӷ�ΪO((2*n-1)*n)=O(n^2),�ռ临�Ӷ�ΪO(1)����
����Code ganker��http://codeganker.blogspot.com/2014/02/longest-palindromic-substring-leetcode.html��
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
