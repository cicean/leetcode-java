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
 ���⣺����һ������Сд��ĸ��ɵ��ַ�����Ѱ�ҵ�һ��ֻ���ַ����г��ֵ���ĸ���±�

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
