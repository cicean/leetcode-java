/**
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
            int right = s.length();
            int count = 0;
            while(left < right) {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    if (s.charAt(left+1) == s.charAt(right)) {
                        count++;
                        left=left+2;
                        right--;
                        System.out.print(s.charAt(left+1) + "=" + s.charAt(right));
                    } else if (s.charAt(left) == s.charAt(right-1)) {
                        count++;
                        left++;
                        right=right-2;
                        System.out.print(s.charAt(left) + "=" + s.charAt(right+1));
                    } else {
                        System.out.print(s.charAt(left) + "!=" + s.charAt(right));
                        return false;
                    }

                    if (count > 1) {
                        return false;
                    }
                }
            }

        return count <= 1 ? true : false;
    }
}
