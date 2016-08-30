import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by cicean on 8/29/2016.
 * 316. Remove Duplicate Letters  QuestionEditorial Solution  My Submissions
 Total Accepted: 18286 Total Submissions: 67635 Difficulty: Hard
 Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 Example:
 Given "bcabc"
 Return "abc"

 Given "cbacdcbc"
 Return "acdb"

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Stack Greedy

 ���⣺ ����һ��ȫ����Сд��ɵ��ַ�����Ҫ��ɾ�����ظ���Ԫ�أ�ʹ���ֵ�����С

 */
public class RemoveDuplicateLetters {

    /**
     * ̰���㷨��Greedy Algorithm��

     ʱ�临�Ӷ� O(k * n)������kΪ�ַ�����Ψһ�ַ��ĸ�����nΪ�ַ����ĳ���
     time: O(kn), space: O(k), k��ʾԭ�ַ�����unique�ַ��ĸ���
     */

    public class Solution {
        public String removeDuplicateLetters(String s) {
            if (s == null ||s.length() == 0)
                return s;

            // ��¼ÿ���ַ����ֵĴ���
            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); i++) {
                cnt[s.charAt(i) - 'a']++;
            }

            // �ҳ���ǰ��С�ַ�
            int pos = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) < s.charAt(pos))
                    pos = i;

                // �������ַ�����
                if (--cnt[s.charAt(i) - 'a'] == 0)
                    break;
            }

            // ��ȥ�ַ������Ѿ�append���ַ��������ظ�ֵ
            return s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
        }

        //recuricve
        public class Solution3 {
            public String removeDuplicateLetters(String s) {
                int[] cnt = new int[26];
                int pos = 0; // the position for the smallest s[i]
                for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) < s.charAt(pos)) pos = i;
                    if (--cnt[s.charAt(i) - 'a'] == 0) break;
                }
                return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
            }
        }
    }




    /**
     * ��ջ��
     ���ȶ��ַ������ֵĸ�������ͳ�ơ�
     Ȼ����ַ���ɨ�裬ÿ����һ���ַ������ж����Ƿ���ջ�У�������������������� �C 1��
     �������ջ�У���ջ����Ԫ���жϣ�Ҫ�ǵ�ǰջ����Ԫ�رȽϴ����cnt��Ϊ0��Ҳ����˵֮�������Ԫ�أ���
     �Ͱ�ջ��������Ȼ��ѵ�ǰ��Ԫ����ջ��
     ʹ��ջ��Stack�����ݽṹ�������㷨�����Ż�������ʹʱ�临�Ӷ�����ΪO(n)

     �㷨���裺

     ���ȼ����ַ���s��ÿһ���ַ����ֵĴ������õ��ֵ�counter

     �����ַ���s���ǵ�ǰ�ַ�Ϊc����counter[c] - 1

     ���c�Ѿ���ջstack�У���������

     ���ַ�c��ջ��Ԫ��top���бȽϣ���top > c����counter[top] > 0��ջ���ظ��˹���

     ��c��ջ
     �㷨ִ�й����У�ջ��Ԫ�ؾ����ܵı��ֵ���˳��

     ���ջ��ʣ��Ԫ�ؼ�Ϊ�����ַ���
     Time complexity: O(n), n is the number of chars in string.

     Space complexity: O(n) worst case.
     */

    public class Solution2 {
        public String removeDuplicateLetters(String s) {
            int[] freqs = new int[256];

            // ͳ���ַ�Ƶ��
            for (int i = 0; i < s.length(); i++) {
                freqs[s.charAt(i)]++;
            }

            boolean[] visited = new boolean[256]; // ������Ǵ���stack����ַ�
            Deque<Character> q = new ArrayDeque<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                freqs[c]--;
                if (visited[c]) continue;

                // pop��stack���бȵ�ǰ�ַ��󵫺��滹���ڵĵ��ַ���
                while (!q.isEmpty() && q.peek() > c && freqs[q.peek()] > 0) {
                    visited[q.pop()] = false;
                }
                q.push(c);
                visited[c] = true;
            }

            StringBuilder sb = new StringBuilder();
            for (char c : q) {
                sb.append(c);
            }

            return sb.reverse().toString();
        }
    }





}
