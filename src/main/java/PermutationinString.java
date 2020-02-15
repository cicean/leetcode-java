/**
 * 567. Permutation in String
 * Medium
 *
 * 991
 *
 * 52
 *
 * Add to List
 *
 * Share
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Note:
 *
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 * Accepted
 * 75,014
 * Submissions
 * 187,242
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * fallcreek
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 4
 *
 * Google
 * |
 * 3
 *
 * Microsoft
 * |
 * 2
 *
 * Amazon
 * |
 * 2
 *
 * Uber
 * |
 * 2
 * Minimum Window Substring
 * Hard
 * Find All Anagrams in a String
 * Medium
 * Obviously, brute force will result in TLE. Think of something else.
 * How will you check whether one string is a permutation of another string?
 * One way is to sort the string and then compare. But, Is there a better way?
 * If one string is a permutation of another string then they must one common metric. What is that?
 * Both strings must have same character frequencies, if one is permutation of another. Which data structure should be used to store frequencies?
 * What about hash table? An array of size 26?
 */
public class PermutationinString {
    /**
     * Approach #4 Using Array [Accepted]
     * Algorithm
     *
     * Instead of making use of a special HashMap datastructure just to store the frequency of occurence of characters, we can use a simpler array data structure to store the frequencies. Given strings contains only lowercase alphabets ('a' to 'z'). So we need to take an array of size 26.The rest of the process remains the same as the last approach.
     * **Complexity Analysis**
     * Time complexity : O(l_1+26*l_1*(l_2-l_1))O(l
     * 1
     * ​
     *  +26∗l
     * 1
     * ​
     *  ∗(l
     * 2
     * ​
     *  −l
     * 1
     * ​
     *  )), where l_1l
     * 1
     * ​
     *   is the length of string l_1l
     * 1
     * ​
     *   and l_2l
     * 2
     * ​
     *   is the length of string l_2l
     * 2
     * ​
     *  .
     *
     * Space complexity : O(1)O(1). s1maps1map and s2maps2map of size 26 is used.
     */

    public class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length())
                return false;
            int[] s1map = new int[26];
            for (int i = 0; i < s1.length(); i++)
                s1map[s1.charAt(i) - 'a']++;
            for (int i = 0; i <= s2.length() - s1.length(); i++) {
                int[] s2map = new int[26];
                for (int j = 0; j < s1.length(); j++) {
                    s2map[s2.charAt(i + j) - 'a']++;
                }
                if (matches(s1map, s2map))
                    return true;
            }
            return false;
        }
        public boolean matches(int[] s1map, int[] s2map) {
            for (int i = 0; i < 26; i++) {
                if (s1map[i] != s2map[i])
                    return false;
            }
            return true;
        }
    }

    /**
     *
     */

    class Solution_1 {
        public boolean checkInclusion(String s1, String s2) {
            int[] charCount = new int[26];
            int count = 0, lenS1 = s1.length(), lenS2 = s2.length();
            for (int i = 0; i < lenS1; i++) {
                charCount[s1.charAt(i) - 'a']++;
                count++;
            }

            for (int l = 0, r = 0; r < lenS2; r++) {
                if (charCount[s2.charAt(r) - 'a']-- > 0) {
                    count--;
                }
                if (r - l + 1 > lenS1 && charCount[s2.charAt(l++) - 'a']++ >= 0) {
                    count++;
                }
                if (count == 0) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Approach #5 Sliding Window [Accepted]:
     * Algorithm
     *
     * Instead of generating the hashmap afresh for every window considered in s2s2,
     * we can create the hashmap just once for the first window in s2s2.
     * Then, later on when we slide the window,
     * we know that we add one preceding character and add a new succeeding character to the new window considered.
     * Thus, we can update the hashmap by just updating the indices associated with those two characters only.
     * Again, for every updated hashmap,
     * we compare all the elements of the hashmap for equality to get the required result.
     * Complexity Analysis
     *
     * Time complexity : O(l_1+26*(l_2-l_1))O(l
     * 1
     * ​
     *  +26∗(l
     * 2
     * ​
     *  −l
     * 1
     * ​
     *  )), where l_1l
     * 1
     * ​
     *   is the length of string l_1l
     * 1
     * ​
     *   and l_2l
     * 2
     * ​
     *   is the length of string l_2l
     * 2
     * ​
     *  .
     *
     * Space complexity : O(1)O(1). Constant space is used.
     */

    public class Solution_Sliding_Window {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length())
                return false;
            int[] s1map = new int[26];
            int[] s2map = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                s1map[s1.charAt(i) - 'a']++;
                s2map[s2.charAt(i) - 'a']++;
            }
            for (int i = 0; i < s2.length() - s1.length(); i++) {
                if (matches(s1map, s2map))
                    return true;
                s2map[s2.charAt(i + s1.length()) - 'a']++;
                s2map[s2.charAt(i) - 'a']--;
            }
            return matches(s1map, s2map);
        }
        public boolean matches(int[] s1map, int[] s2map) {
            for (int i = 0; i < 26; i++) {
                if (s1map[i] != s2map[i])
                    return false;
            }
            return true;
        }
    }

    /**
     * Approach #6 Optimized Sliding Window [Accepted]:
     * Algorithm
     *
     * The last approach can be optimized, if instead of comparing all the elements of the hashmaps for every updated s2maps2map corresponding to every window of s2s2 considered, we keep a track of the number of elements which were already matching in the earlier hashmap and update just the count of matching elements when we shift the window towards the right.
     *
     * To do so, we maintain a countcount variable, which stores the number of characters(out of the 26 alphabets), which have the same frequency of occurence in s1s1 and the current window in s2s2. When we slide the window, if the deduction of the last element and the addition of the new element leads to a new frequency match of any of the characters, we increment the countcount by 1. If not, we keep the countcount intact. But, if a character whose frequency was the same earlier(prior to addition and removal) is added, it now leads to a frequency mismatch which is taken into account by decrementing the same countcount variable. If, after the shifting of the window, the countcount evaluates to 26, it means all the characters match in frequency totally. So, we return a True in that case immediately.
     * Complexity Analysis
     *
     * Time complexity : O(l_1+(l_2-l_1))O(l
     * 1
     * ​
     *  +(l
     * 2
     * ​
     *  −l
     * 1
     * ​
     *  )). where l_1l
     * 1
     * ​
     *   is the length of string l_1l
     * 1
     * ​
     *   and l_2l
     * 2
     * ​
     *   is the length of string l_2l
     * 2
     * ​
     *  .
     *
     * Space complexity : O(1)O(1). Constant space is used.
     */

    public class Solution_Optimized {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length())
                return false;
            int[] s1map = new int[26];
            int[] s2map = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                s1map[s1.charAt(i) - 'a']++;
                s2map[s2.charAt(i) - 'a']++;
            }
            int count = 0;
            for (int i = 0; i < 26; i++)
                if (s1map[i] == s2map[i])
                    count++;
            for (int i = 0; i < s2.length() - s1.length(); i++) {
                int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
                if (count == 26)
                    return true;
                s2map[r]++;
                if (s2map[r] == s1map[r])
                    count++;
                else if (s2map[r] == s1map[r] + 1)
                    count--;
                s2map[l]--;
                if (s2map[l] == s1map[l])
                    count++;
                else if (s2map[l] == s1map[l] - 1)
                    count--;
            }
            return count == 26;
        }
    }

}
