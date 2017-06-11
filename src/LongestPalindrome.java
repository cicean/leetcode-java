import java.util.BitSet;

/**
 * Created by cicean on 10/16/2016.
 *
 * 409. Longest Palindrome   QuestionEditorial Solution  My Submissions
 Total Accepted: 8351
 Total Submissions: 18715
 Difficulty: Easy
 Contributors: Admin
 Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

 This is case sensitive, for example "Aa" is not considered a palindrome here.

 Note:
 Assume the length of given string will not exceed 1,010.

 Example:

 Input:
 "abccccdd"

 Output:
 7

 Explanation:
 One longest palindrome that can be built is "dccaccd", whose length is 7.
 Hide Company Tags Google
 Hide Tags Hash Table
 Hide Similar Problems (E) Palindrome Permutation

 */
public class LongestPalindrome {

    // use BitSet
    public int longestPalindrome(String s) {
        BitSet bs = new BitSet();
        for (byte b : s.getBytes()) {
            bs.flip(b);
        }
        return bs.cardinality() == 0 ? s.length() : s.length() - bs.cardinality() + 1;
    }

    public int longestPalindrome_1(String s) {
        int[] lowercase = new int[26];
        int[] uppercase = new int[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++){
            char temp = s.charAt(i);
            if (temp >= 97) lowercase[temp-'a']++;
            else uppercase[temp-'A']++;
        }
        for (int i = 0; i < 26; i++){
            res+=(lowercase[i]/2)*2;
            res+=(uppercase[i]/2)*2;
        }
        return res == s.length() ? res : res+1;

    }


}
