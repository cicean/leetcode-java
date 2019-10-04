import java.util.*;

/**
 * 521. Longest Uncommon Subsequence I
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

 A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

 The input will be two strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

 Example 1:
 Input: "aba", "cdc"
 Output: 3
 Explanation: The longest uncommon subsequence is "aba" (or "cdc"),
 because "aba" is a subsequence of "aba",
 but not a subsequence of any other strings in the group of two strings.
 Note:

 Both strings' lengths will not exceed 100.
 Only letters from a ~ z will appear in input strings.
 */

public class LongestUncommonSubsequenceI {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]

   In the brute force approach we will generate all the possible 2^n2
   ​n
   ​​  subsequences of both the strings and store their number of occurences in a hashmap.
   Longest subsequence whose frequency is equal to 11 will be the required subsequence.
   And, if it is not found we will return -1−1.
   Complexity Analysis

   Time complexity : O(2^x+2^y). where xx and yy are the lengths of strings aa and bb respectively . Number of subsequences will be 2^x+2^y.
   Space complexity : O(2^x+2^y). subsequences will be generated.
   */

  public class Solution {
    public int findLUSlength(String a, String b) {
      HashMap < String, Integer > map = new HashMap < > ();
      for (String s: new String[] {a, b}) {
        for (int i = 0; i < (1 << s.length()); i++) {
          String t = "";
          for (int j = 0; j < s.length(); j++) {
            if (((i >> j) & 1) != 0)
              t += s.charAt(j);
          }
          if (map.containsKey(t))
            map.put(t, map.get(t) + 1);
          else
            map.put(t, 1);
        }
      }
      int res = -1;
      for (String s: map.keySet()) {
        if (map.get(s) == 1)
          res = Math.max(res, s.length());
      }
      return res;
    }
  }

  /**
   * Approach #2 Simple Solution[Accepted]

   Algorithm

   Simple analysis of this problem can lead to an easy solution.

   These three cases are possible with string aa and bb:-

   a=ba=b. If both the strings are identical, it is obvious that no subsequence will be uncommon. Hence, return -1.

   length(a)=length(b)length(a)=length(b) and a \ne ba≠b. Example: abcabc and abdabd. In this case we can consider any string i.e. abcabc or abdabd as a required subsequence, as out of these two strings one string will never be a subsequence of other string. Hence, return length(a)length(a) or length(b)length(b).

   length(a) \ne length(b)length(a)≠length(b). Example abcdabcd and abcabc. In this case we can consider bigger string as a required subsequence because bigger string can't be a subsequence of smaller string. Hence, return max(length(a),length(b))max(length(a),length(b)).

   Complexity Analysis

   Time complexity : O(min(x,y))O(min(x,y)). where xx and yy are the lengths of strings aa and bb respectively. Here equals method will take min(x,y)min(x,y) time .

   Space complexity : O(1)O(1). No extra space required.

   */

  public class Solution2 {
    public int findLUSlength(String a, String b) {
      if (a.equals(b))
        return -1;
      return Math.max(a.length(), b.length());
    }
  }


}
