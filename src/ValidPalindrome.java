/*
 125	Valid Palindrome	22.0%	Easy
 Problem:    Valid Palindrome
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/valid-palindrome/
 Notes:
 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.
 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.
 For the purpose of this problem, we define empty string as valid palindrome.
 Solution: traverse from both side.
 */

public class ValidPalindrome {
	
	public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("\\W", "");
        System.out.println(s);
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;   
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ValidPalindrome slt=new ValidPalindrome();
		String s ="A man, a plan, a canal: Panama";
		System.out.print(slt.isPalindrome(s));
	}

    /**
     * Have you consider that the string might be empty? This is a good question to ask during an interview.

     For the purpose of this problem, we define empty string as valid palindrome.

     �ص���.toLowerCase()��Character.isLetterOrDigit()����������ʹ�ã�
     �Լ�ָ���ڱ��������ƶ����continue��䡣
     * O(n) time without extra memory. ----> ָ���
     */

    public class Solution {
        public boolean isPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return true;
            }
            s = s.toLowerCase();
            int start = 0, end = s.length() - 1;
            while (start < end) {
                if (!Character.isLetterOrDigit(s.charAt(start))) {
                    start++;
                    continue;
                }
                if (!Character.isLetterOrDigit(s.charAt(end))) {
                    end--;
                    continue;
                }
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }

    }




}
