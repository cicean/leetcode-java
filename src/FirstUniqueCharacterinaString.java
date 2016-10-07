/**
 * Created by cicean on 9/2/2016.
 * 387. First Unique Character in a String  QuestionEditorial Solution  My Submissions
 Total Accepted: 11473
 Total Submissions: 26669
 Difficulty: Easy
 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 Note: You may assume the string contain only lowercase letters.

 Hide Company Tags Amazon
 题意：给定一个仅由小写字母组成的字符串，寻找第一个只在字符串中出现的字母的下标

 */
public class FirstUniqueCharacterinaString {

    public class Solution {
        public int firstUniqChar(String s) {
            if (s == null || s.equals("")) return -1;
            char[] c = s.toCharArray();
            int[] cnt = new int[256];

            for (int i = 0; i < c.length; i++) {
                cnt[c[i]]++;
            }

            for (int i = 0; i < c.length; i++) {
                if (cnt[c[i]] == 1) return i;
            }
            return -1;
        }


    }
}
