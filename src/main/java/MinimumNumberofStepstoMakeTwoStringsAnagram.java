/**
 * 1347. Minimum Number of Steps to Make Two Strings Anagram
 * Medium
 *
 * 75
 *
 * 6
 *
 * Add to List
 *
 * Share
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
 *
 * Return the minimum number of steps to make t an anagram of s.
 *
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bab", t = "aba"
 * Output: 1
 * Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 * Example 2:
 *
 * Input: s = "leetcode", t = "practice"
 * Output: 5
 * Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 * Example 3:
 *
 * Input: s = "anagram", t = "mangaar"
 * Output: 0
 * Explanation: "anagram" and "mangaar" are anagrams.
 * Example 4:
 *
 * Input: s = "xxyyzz", t = "xxyyzz"
 * Output: 0
 * Example 5:
 *
 * Input: s = "friend", t = "family"
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 50000
 * s.length == t.length
 * s and t contain lower-case English letters only.
 * Accepted
 * 11,138
 * Submissions
 * 14,625
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Twitter
 * |
 * LeetCode
 * Count the frequency of characters of each string.
 * Loop over all characters if the frequency of a character in t is less than the frequency of the same character in s
 * then add the difference between the frequencies to the answer.
 */
public class MinimumNumberofStepstoMakeTwoStringsAnagram {

    class Solution {
        public int minSteps(String s, String t) {
            int n = s.length(), ans = 0;
            int[] arr = new int[26];
            for(int i = 0; i < n; i++) {
                arr[s.charAt(i) - 'a']++;
                arr[t.charAt(i) - 'a']--;
            }
            for(int i = 0; i < 26; i++) if(arr[i] > 0) ans += arr[i];
            return ans;
        }
    }

    class Solution {
        public int minSteps(String s, String t) {
            int[] h=new int[26];
            char[] ct=t.toCharArray();
            char[] cs=s.toCharArray();
            for(int i=0;i<=ct.length-1;i++)
            {
                h[ct[i]-'a']++;
            }
            for(int i=0;i<=ct.length-1;i++)
            {
                h[cs[i]-'a']--;
            }
            int sum=0;
            for(int i:h)
                sum=sum+Math.abs(i);
            return sum/2;
        }
    }

}
