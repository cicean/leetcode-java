import java.util.*;

/**
 * 524. Longest Word in Dictionary through Deleting
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

 Example 1:
 Input:
 s = "abpcplea", d = ["ale","apple","monkey","plea"]

 Output:
 "apple"
 Example 2:
 Input:
 s = "abpcplea", d = ["a","b","c"]

 Output:
 "a"
 Note:
 All the strings in the input will only contain lower-case letters.
 The size of the dictionary won't exceed 1,000.
 The length of all the strings in the input won't exceed 1,000.
 */

public class LongestWordinDictionarythroughDeleting {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]

   Algorithm

   The idea behind this approach is as follows. We create a list of all the possible strings that can be formed by deleting one or more characters from the given string ss. In order to do so, we make use of a recursive function generate(s, str, i, l) which creates a string by adding and by removing the current character(i^{th}i
   ​th
   ​​ ) from the string ss to the string strstr formed till the index ii. Thus, it adds the i^{th}i
   ​th
   ​​  character to strstr and calls itself as generate(s, str + s.charAt(i), i + 1, l). It also omits the i^{th}i
   ​th
   ​​  character to strstr and calls itself as generate(s, str, i + 1, l).

   Thus, at the end the list ll contains all the required strings that can be formed using ss.
   Then, we look for the strings formed in ll into the dictionary available to see if a match is available.
   Further, in case of a match, we check for the length of the matched string to maximize the length and
   we also take care to consider the lexicographically smallest string in case of length match as well.
   Complexity Analysis

   Time complexity : O(2^n). generate calls itself 2^ntimes. Here, nn refers to the length of string ss.

   Space complexity : O(2^n). List ll contains 2^n strings.
   */

  public class Solution {
    public String findLongestWord(String s, List < String > d) {
      HashSet < String > set = new HashSet < > (d);
      List < String > l = new ArrayList < > ();
      generate(s, "", 0, l);
      String max_str = "";
      for (String str: l) {
        if (set.contains(str))
          if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
            max_str = str;
      }
      return max_str;
    }
    public void generate(String s, String str, int i, List < String > l) {
      if (i == s.length())
        l.add(str);
      else {
        generate(s, str + s.charAt(i), i + 1, l);
        generate(s, str, i + 1, l);
      }
    }
  }

  /**
   * Approach #2 Iterative Brute Force [Time Limit Exceeded]

   Algorithm

   Instead of using recursive generate to create the list of possible strings that can be formed using ss by performing delete operations, we can also do the same process iteratively. To do so, we use the concept of binary number generation.

   We can treat the given string ss along with a binary represenation corresponding to the indices of ss. The rule is that the character at the position ii has to be added to the newly formed string strstr only if there is a boolean 1 at the corresponding index in the binary representation of a number currently considered.

   We know a total of 2^n2
   ​n
   ​​  such binary numbers are possible if there are nn positions to be filled(nn also corresponds to the number of characters in ss). Thus, we consider all the numbers from 00 to 2^n2
   ​n
   ​​  in their binary representation in a serial order and generate all the strings possible using the above rule.

   The figure below shows an example of the strings generated for the given string ss:"sea".

   Longest_Word

   A problem with this method is that the maximum length of the string can be 32 only, since we make use of an integer and perform the shift operations on it to generate the binary numbers.

   Complexity Analysis

   Time complexity : O(2^n). 2^n strings are generated.

   Space complexity : O(2^n). List ll contains 2^n strings.
   */

  public class Solution2 {
    public String findLongestWord(String s, List < String > d) {
      HashSet < String > set = new HashSet < > (d);
      List < String > l = new ArrayList < > ();
      for (int i = 0; i < (1 << s.length()); i++) {
        String t = "";
        for (int j = 0; j < s.length(); j++) {
          if (((i >> j) & 1) != 0)
            t += s.charAt(j);
        }
        l.add(t);
      }
      String max_str = "";
      for (String str: l) {
        if (set.contains(str))
          if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
            max_str = str;
      }
      return max_str;
    }
  }

  /**
   * Approach #3 Sorting and checking Subsequence [Accepted]

   Algorithm

   The matching condition in the given problem requires that we need to consider the matching string in the dictionary with the longest length and in case of same length, the string which is smallest lexicographically. To ease the searching process, we can sort the given dictionary's strings based on the same criteria, such that the more favorable string appears earlier in the sorted dictionary.

   Now, instead of performing the deletions in ss, we can directly check if any of the words given in the dictionary(say xx) is a subsequence of the given string ss, starting from the beginning of the dictionary. This is because, if xx is a subsequence of ss, we can obtain xx by performing delete operations on ss.

   If xx is a subsequence of ss every character of xx will be present in ss. The following figure shows the way the subsequence check is done for one example:
   As soon as we find any such xx, we can stop the search immediately since we've already processed dd to our advantage.

   Complexity Analysis

   Time complexity : O(n*x*logn + n*x)O(n∗x∗logn+n∗x). Here nn refers to the number of strings in list dd and xx refers to average string length. Sorting takes O(nlogn)O(nlogn) and isSubsequence takes O(x)O(x) to check whether a string is a subsequence of another string or not.

   Space complexity : O(logn)O(logn). Sorting takes O(logn)O(logn) space in average case.

   */

  public class Solution3 {
    public boolean isSubsequence(String x, String y) {
      int j = 0;
      for (int i = 0; i < y.length() && j < x.length(); i++)
        if (x.charAt(j) == y.charAt(i))
          j++;
      return j == x.length();
    }
    public String findLongestWord(String s, List < String > d) {
      Collections.sort(d, new Comparator < String > () {
        public int compare(String s1, String s2) {
          return s2.length() != s1.length() ? s2.length() - s1.length() : s1.compareTo(s2);
        }
      });
      for (String str: d) {
        if (isSubsequence(str, s))
          return str;
      }
      return "";
    }
  }

  /**
   * Approach #4 Without Sorting [Accepted]:

   Algorithm

   Since sorting the dictionary could lead to a huge amount of extra effort, we can skip the sorting
   and directly look for the strings xx in the unsorted dictionary dd such that xx is a subsequence in ss.
   If such a string xx is found, we compare it with the other matching strings found till now based on
   the required length and lexicographic criteria.
   Thus, after considering every string in dd, we can obtain the required result.

   Complexity Analysis

   Time complexity : O(n*x)O(n∗x). One iteration over all strings is required. Here nn refers to the number of strings in list dd and xx refers to average string length.

   Space complexity : O(x)O(x). max\_strmax_str variable is used.
   */

  public class Solution4 {
    public boolean isSubsequence(String x, String y) {
      int j = 0;
      for (int i = 0; i < y.length() && j < x.length(); i++)
        if (x.charAt(j) == y.charAt(i))
          j++;
      return j == x.length();
    }
    public String findLongestWord(String s, List < String > d) {
      String max_str = "";
      for (String str: d) {
        if (isSubsequence(str, s)) {
          if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
            max_str = str;
        }
      }
      return max_str;
    }
  }

  /**
   * trie
   */

  class Solution {
    class Trie {
      Trie[] branches = new Trie[26];
      Trie get(char letter) {
        if (branches[letter-'a'] == null)
          branches[letter-'a'] = new Trie();

        return branches[letter-'a'];
      }
      boolean has(char letter) {
        return branches[letter-'a'] != null;
      }
      public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 26; i++)
          if (branches[i] != null)
            str.append((char)(i + 'a'));
        return str.toString();
      }
    }
    Trie root = new Trie();
    void build(String word) {
      char[] w = word.toCharArray();
      int size = word.length();
      for (int i = 0; i < size; i++) {
        Trie node = root.get(w[i]);
        for (int k = i+1; k < size; k++) {
          node.get(w[k]);
        }
      }
    }
    boolean search(String word) {
      Trie node = root;
      for (char letter: word.toCharArray()) {
        if (!node.has(letter)) return false;
        node = node.get(letter);
      }
      return true;
    }
    public String findLongestWord(String s, List<String> d) {
      if (s == null || s.length() == 0) return "";
      int size = s.length();
      int[][] map = new int[size][26];
      char[] word = s.toCharArray();
      Arrays.fill(map[size-1], -1);
      map[size-1][word[size-1]-'a'] = size-1;
      for (int i = size-2; i >= 0; i--) {
        map[i] = Arrays.copyOf(map[i+1], 26);
        map[i][word[i]-'a'] = i;
      }
      String max = "";
      for (String w: d) {
        int index = 0;
        int i = 0;
        for (; i < w.length(); i++) {
          if (index == size || map[index][w.charAt(i)-'a'] == -1) break;
          index = map[index][w.charAt(i)-'a']+1;
        }
        if (i == w.length()) {
          if (w.length() > max.length() || (w.length() == max.length() && w.compareTo(max) < 0)) {
            max = w;
          }
        }
      }
      return max;


        /*
        build(s);
        Collections.sort(d, (a, b) -> {
            if (a.length() == b.length()) return a.compareTo(b);
            return b.length() - a.length();
        });
        for (String word: d) {
            if (search(word)) return word;
        }

        return "";  */
    }
  }

  class Solution {
    public String findLongestWord(String s, List<String> d) {
      String res = new String();
      if(s == null || s.length() == 0 || d == null || d.size() == 0){
        return res;
      }
      int n = s.length();
      int[][] dp = new int[n+1][26];
      Arrays.fill(dp[n], -1);

      for(int i = n-1; i >= 0; i--){
        for(int j = 0; j < 26; j++){
          dp[i][j] = dp[i+1][j];
        }
        dp[i][s.charAt(i) - 'a'] = i;
      }

      for(String word: d){
        if(word.length() >= res.length()){
          if(isSubseq(word, s, dp)){
            if(word.length() >  res.length() || (word.length() == res.length() && word.compareTo(res) < 0)){
              res = word;
            }
          }
        }
      }
      return res;
    }

    private boolean isSubseq(String word, String s, int[][] dp){
      int i = 0;
      int j = 0;
      int n = s.length();
      int m = word.length();
      int k = 0;
      while(i < s.length() && j < word.length()){
        k = word.charAt(j) - 'a';
        if(dp[i][k] != -1){
          i = dp[i][k];
        }
        else{
          break;
        }
        j++;
        i++;
      }
      return j == m;
    }
  }

}
