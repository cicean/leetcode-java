import java.util.*;

/**
 * 522. Longest Uncommon Subsequence II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

 A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

 The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

 Example 1:
 Input: "aba", "cdc", "eae"
 Output: 3
 Note:

 All the given strings' lengths will not exceed 10.
 The length of the given list will be in the range of [2, 50].

 */

public class LongestUncommonSubsequenceII {

  /**
   * Approach #1 Brute Force[Accepted]

   In the brute force approach we will generate all the possible 2^n2
   ​n
   ​​  subsequences of all the strings and store their number of occurences in a hashmap.
   Longest subsequence whose frequency is equal to 11 will be the required subsequence.
   And, if it is not found we will return -1−1.

   Complexity Analysis

   Time complexity : O(n*2^x). where xx is the average length of the strings and nn is the total number of given strings.
   Space complexity : O(n*2^x). Hashmap of size n*2^xis used.

   */

  public class Solution {
    public int findLUSlength(String[] strs) {
      HashMap < String, Integer > map = new HashMap < > ();
      for (String s: strs) {
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
   * Approach #2 Checking Subsequence [Accepted]

   Algorithm

   By some analysis, we can note that if longest uncommon subsequence is there, then it will always be one of the string from the given list of strings. Using this idea, we can check each string that whether it is a subsequence of any other string. If a string is not a subsequence of any other string i.e. it is uncommon , we will return maximum length string out of them. If no string found, we will return -1−1.

   To understand the method, look at the example given below:
   Complexity Analysis

   Time complexity : O(x*n^2). where nn is the number of strings and xx is the average length of the strings.

   Space complexity : O(1)O(1). Constant space required.
   */

  public class Solution2 {
    public boolean isSubsequence(String x, String y) {
      int j = 0;
      for (int i = 0; i < y.length() && j < x.length(); i++)
        if (x.charAt(j) == y.charAt(i))
          j++;
      return j == x.length();
    }
    public int findLUSlength(String[] strs) {
      int res = -1;
      for (int i = 0, j; i < strs.length; i++) {
        for (j = 0; j < strs.length; j++) {
          if (j == i)
            continue;
          if (isSubsequence(strs[i], strs[j]))
            break;
        }
        if (j == strs.length)
          res = Math.max(res, strs[i].length());
      }
      return res;
    }
  }

  /**
   * Approach #3 Sorting and Checking Subsequence [Accepted]

   Algorithm

   In the last approach, we needed to compare all the given strings and compare them for the subsequence criteria.
   We can save some computations if we sort the given set of strings based on their lengths initially.

   In this approach, firstly we sort the given strings in decreasing order of their lengths.
   Then, we start off by comparing the longest string with all the other strings. If none of the other strings happens to be the subsequence of the longest string, we return the length of the longest string as the result without any need of further comparisons.
   If some string happens to be a subsequence of the longest string, we continue the same process by
   choosing the second largest string as the first string and repeat the process, and so on.

   Complexity Analysis

   Time complexity : O(x*n^2). where nn is the number of strings and xx is the average length of the strings.

   Space complexity : O(1)O(1). Constant space required.
   */

  public class Solution3 {
    public boolean isSubsequence(String x, String y) {
      int j = 0;
      for (int i = 0; i < y.length() && j < x.length(); i++)
        if (x.charAt(j) == y.charAt(i))
          j++;
      return j == x.length();
    }
    public int findLUSlength(String[] strs) {
      Arrays.sort(strs, new Comparator < String > () {
        public int compare(String s1, String s2) {
          return s2.length() - s1.length();
        }
      });
      for (int i = 0, j; i < strs.length; i++) {
        boolean flag = true;
        for (j = 0; j < strs.length; j++) {
          if (i == j)
            continue;
          if (isSubsequence(strs[i], strs[j])) {
            flag = false;
            break;
          }
        }
        if (flag)
          return strs[i].length();
      }
      return -1;
    }
  }

}
