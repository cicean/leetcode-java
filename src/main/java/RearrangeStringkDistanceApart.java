/**
 * Created by cicean on 8/31/2016.
 * 358. Rearrange String k Distance Apart
 * Hard
 *
 * 405
 *
 * 18
 *
 * Add to List
 *
 * Share
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 *
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 *
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 * Example 2:
 *
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * Example 3:
 *
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 * Accepted
 * 31,423
 * Submissions
 * 91,621
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * elmirap
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * Task Scheduler
 * Medium
 * Reorganize String
 * Medium
 */
public class RearrangeStringkDistanceApart {

    public class Solution {
        //�ȼ�¼str�е�char���������ڴ���������count[]���valid[]����¼���char��С���ֵ�λ�á�
        //ÿһ�ΰ�countֵ������ѡ������append���µ�string����
        //Solution with Two auxiliary array. O(N) time.
        public int selectedValue(int[] count, int[] valid, int i) {
            int select = Integer.MIN_VALUE;
            int val = -1;
            for (int j = 0; j < count.length; j++) {
                if (count[j] > 0 && i >= valid[j] && count[j] > select) {
                    select = count[j];
                    val = j;
                }
            }
            return val;
        }

        public String rearrangeString(String str, int k) {
            int[] count = new int[26];
            int[] valid = new int[26];
            //��ÿ�������˵�char�ĸ���������
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                //ѡ��ʣ����Ҫ���ִ��������������������ĸ������������Ӧ���ȷŵ���
                int curt = selectedValue(count, valid, i);
                //������������������ء���
                if (curt == -1) return "";
                //ѡ��ú�countҪ���٣�validҪ����һ��k distance֮��
                count[curt]--;
                valid[curt] = i + k;
                sb.append((char)('a' + curt));
            }

            return sb.toString();
        }
    }
}
