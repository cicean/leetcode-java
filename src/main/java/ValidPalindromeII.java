/**
 * 680. Valid Palindrome II
 * Created by miaowang on 9/18/17.
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True
 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.
 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        //two pointer


            int left = 0;
            int count = 0;
            while(left < s.length()/2) {
                if (s.charAt(left) == s.charAt(s.length() - left -1)) {
                    left++;
                } else {
                    if (s.charAt(left+1) == s.charAt(s.length() - left -1)) {
                        count++;
                        left=left+2;
                    } else if (s.charAt(left) == s.charAt(s.length() - left -1)) {
                        count++;
                        left++;
                    } else {
                        return false;
                    }

                }

                if (count > 1) {
                    return false;
                }
            }

        return true;
    }

    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }
    public boolean validPalindrome_1(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                    isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }
}
