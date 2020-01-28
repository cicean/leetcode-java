/**
 * Roku(电话面): 给一个String，容许最多一个char修改（包括删除，替换），判断是否是valid palindrome
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
public class ValidPalindromeEditOneChar {
    public boolean validPalindrome(String s) {
        //two pointer
            int left = 0;
            int right = s.length() - 1;
            int count = 0;
            while(left < right) {

                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    if (s.charAt(left+1) == s.charAt(right)) {
                        count++;
                        left++;
                    } else if (s.charAt(left) == s.charAt(right+1)) {
                        count++;
                        right--;
                    } else {
                        count++;
                        left++;
                        right--;
                    }
                }

                if (count > 1) {
                    return false;
                }
            }

        return true;
    }
}
