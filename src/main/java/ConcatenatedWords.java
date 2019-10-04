import java.util.*;

/**
 * 472. Concatenated Words
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.

 A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

 Example:
 Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

 Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

 Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 Note:
 The number of elements of the given array will not exceed 10,000
 The length sum of elements in the given array will not exceed 600,000.
 All the input string will only include lower case letters.
 The returned elements order does not matter.
 */
public class ConcatenatedWords {

  /**
   * Java DP Solution
   Do you still remember how did you solve this problem? https://leetcode.com/problems/word-break/

   If you do know one optimized solution for above question is using DP, this problem is just one more step further. We iterate through each word and see if it can be formed by using other words.

   Of course it is also obvious that a word can only be formed by words shorter than it. So we can first sort the input by length of each word, and only try to form one word by using words in front of it.
   */

  public class Solution {
    public  List<String> findAllConcatenatedWordsInADict(String[] words) {
      List<String> result = new ArrayList<>();
      Set<String> preWords = new HashSet<>();
      Arrays.sort(words, new Comparator<String>() {
        public int compare (String s1, String s2) {
          return s1.length() - s2.length();
        }
      });

      for (int i = 0; i < words.length; i++) {
        if (canForm(words[i], preWords)) {
          result.add(words[i]);
        }
        preWords.add(words[i]);
      }

      return result;
    }

    private boolean canForm(String word, Set<String> dict) {
      if (dict.isEmpty()) return false;
      boolean[] dp = new boolean[word.length() + 1];
      dp[0] = true;
      for (int i = 1; i <= word.length(); i++) {
        for (int j = 0; j < i; j++) {
          if (!dp[j]) continue;
          if (dict.contains(word.substring(j, i))) {
            dp[i] = true;
            break;
          }
        }
      }
      return dp[word.length()];
    }
  }



}
