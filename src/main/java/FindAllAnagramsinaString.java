import java.util.*;

/**
 * 438. Find All Anagrams in a String
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 Example 2:

 Input:
 s: "abab" p: "ab"

 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".

 */

public class FindAllAnagramsinaString {

  /**
   * Same idea from a fantastic sliding window template, please refer:
   https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems

   Time Complexity will be O(n) because the "start" and "end" points will only move from left to right once.
   * @param s
   * @param p
   * @return
   */

  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> list = new ArrayList<>();
    if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
    int[] hash = new int[256]; //character hash
    //record each character in p to hash
    for (char c : p.toCharArray()) {
      hash[c]++;
    }
    //two points, initialize count to p's length
    int left = 0, right = 0, count = p.length();
    while (right < s.length()) {
      //move right everytime, if the character exists in p's hash, decrease the count
      //current hash value >= 1 means the character is existing in p
      if (hash[s.charAt(right++)]-- >= 1) count--;

      //when the count is down to 0, means we found the right anagram
      //then add window's left to result list
      if (count == 0) list.add(left);

      //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
      //++ to reset the hash because we kicked out the left
      //only increase the count if the character is in p
      //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
      if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
    }
    return list;
  }

  /**
   * Approach 1: Sliding Window with HashMap
   * Let's start from the simplest approach: sliding window + two counter hashmaps letter -> its count. The first hashmap is a reference counter pCount for string p, and the second one is a counter sCount for string in the sliding window.
   *
   * The idea is to move sliding window along the string s, recompute the second hashmap sCount in a constant time and compare it with the first hashmap pCount. If sCount == pCount, then the string in the sliding window is a permutation of string p, and one could add its start position in the output list.
   *
   * Algorithm
   *
   * Build reference counter pCount for string p.
   *
   * Move sliding window along the string s:
   *
   * Recompute sliding window counter sCount at each step by adding one letter on the right and removing one letter on the left.
   *
   * If sCount == pCount, update the output list.
   *
   * Return output list.
   * Complexity Analysis
   *
   * Time complexity: \mathcal{O}(N_s + N_p)O(N
   * s
   * ​
   *  +N
   * p
   * ​
   *  ) since it's one pass along both strings.
   *
   * Space complexity: \mathcal{O}(1)O(1), because pCount and sCount contain not more than 26 elements.
   *
   */

  class Solution_hashmap {
    public List<Integer> findAnagrams(String s, String p) {
      int ns = s.length(), np = p.length();
      if (ns < np) return new ArrayList();

      Map<Character, Integer> pCount = new HashMap();
      Map<Character, Integer> sCount = new HashMap();
      // build reference hashmap using string p
      for (char ch : p.toCharArray()) {
        if (pCount.containsKey(ch)) {
          pCount.put(ch, pCount.get(ch) + 1);
        }
        else {
          pCount.put(ch, 1);
        }
      }

      List<Integer> output = new ArrayList();
      // sliding window on the string s
      for (int i = 0; i < ns; ++i) {
        // add one more letter
        // on the right side of the window
        char ch = s.charAt(i);
        if (sCount.containsKey(ch)) {
          sCount.put(ch, sCount.get(ch) + 1);
        }
        else {
          sCount.put(ch, 1);
        }
        // remove one letter
        // from the left side of the window
        if (i >= np) {
          ch = s.charAt(i - np);
          if (sCount.get(ch) == 1) {
            sCount.remove(ch);
          }
          else {
            sCount.put(ch, sCount.get(ch) - 1);
          }
        }
        // compare hashmap in the sliding window
        // with the reference hashmap
        if (pCount.equals(sCount)) {
          output.add(i - np + 1);
        }
      }
      return output;
    }
  }

  /**
   * Approach 2: Sliding Window with Array
   * Algorithm
   *
   * Hashmap is quite complex structure, with known performance issues in Java. Let's implement approach 1 using 26-elements array instead of hashmap:
   *
   * Element number 0 contains count of letter a.
   *
   * Element number 1 contains count of letter b.
   *
   * ...
   *
   * Element number 26 contains count of letter z.
   *
   * Algorithm
   *
   * Build reference array pCount for string p.
   *
   * Move sliding window along the string s:
   *
   * Recompute sliding window array sCount at each step by adding one letter on the right and removing one letter on the left.
   *
   * If sCount == pCount, update the output list.
   *
   * Return output list.
   * Complexity Analysis
   *
   * Time complexity: \mathcal{O}(N_s + N_p)O(N
   * s
   * ​
   *  +N
   * p
   * ​
   *  ) since it's one pass along both strings.
   *
   * Space complexity: \mathcal{O}(1)O(1), because pCount and sCount contain 26 elements each.
   */

  class Solution_array {
    public List<Integer> findAnagrams(String s, String p) {
      int ns = s.length(), np = p.length();
      if (ns < np) return new ArrayList();

      int [] pCount = new int[26];
      int [] sCount = new int[26];
      // build reference array using string p
      for (char ch : p.toCharArray()) {
        pCount[(int)(ch - 'a')]++;
      }

      List<Integer> output = new ArrayList();
      // sliding window on the string s
      for (int i = 0; i < ns; ++i) {
        // add one more letter
        // on the right side of the window
        sCount[(int)(s.charAt(i) - 'a')]++;
        // remove one letter
        // from the left side of the window
        if (i >= np) {
          sCount[(int)(s.charAt(i - np) - 'a')]--;
        }
        // compare array in the sliding window
        // with the reference array
        if (Arrays.equals(pCount, sCount)) {
          output.add(i - np + 1);
        }
      }
      return output;
    }
  }

}
