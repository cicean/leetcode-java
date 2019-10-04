/**
 * Created by cicean on 8/30/2016.
 * 344. Reverse String  QuestionEditorial Solution  My Submissions
 Total Accepted: 79009
 Total Submissions: 135553
 Difficulty: Easy
 Write a function that takes a string as input and returns the string reversed.

 Example:
 Given s = "hello", return "olleh".

 Hide Tags Two Pointers String
 Hide Similar Problems (E) Reverse Vowels of a String

 */
public class ReverseString {

    //1. Two-Pointer --3ms
    public class Solution {
        public String reverseString(String s) {
            if (s == null || s.length() == 0) return s;
            char[] res = s.toCharArray();
            int i = 0;
            int j = res.length - 1;

            while (i < j) {
                char tmp;
                tmp = res[i];
                res[i] = res[j];
                res[j] = tmp;
                i++;
                j--;
            }

            return new String(res);

        }
    }

    //2. StringBuilder --5ms
    public class Solution2 {
        public String reverseString(String s) {
            return  new StringBuilder(s).reverse().toString();
        }


    }

    //3. Recursion --22ms
    public class Solution3 {
        public String reverseString(String s) {
            int len = s.length();
            if (len <= 1) return s;
            String leftStr = s.substring(0, len / 2);
            String rightStr = s.substring(len / 2, len);
            return reverseString(rightStr) + reverseString(leftStr);
        }
    }


}
