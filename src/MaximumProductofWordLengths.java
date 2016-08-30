import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by cicean on 8/29/2016.
 * 318. Maximum Product of Word Lengths  QuestionEditorial Solution  My Submissions
 Total Accepted: 31310 Total Submissions: 76496 Difficulty: Medium
 Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

 Example 1:
 Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 Return 16
 The two words can be "abcw", "xtfn".

 Example 2:
 Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 Return 4
 The two words can be "ab", "cd".

 Example 3:
 Given ["a", "aa", "aaa", "aaaa"]
 Return 0
 No such pair of words.

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Bit Manipulation

 Follow up:
 Could you do better than O(n2), where n is the number of words?

 ���⣺����һ���ַ������飬�������У�Ԫ�ظ�����ͬ���ַ������ȳ˻����ֵ��

 ������1�У�abcw �� xtfn �ɼ�Ϊ4*4 =16 ��Ԫ�ػ�����ͬ�� ��abcw �� abcdefΪ0����Ϊ����ͬ��Ԫ��

 */
public class MaximumProductofWordLengths {

    /**
     * ����
     ����������뵽�ķ�����brute force, ����for loop���������������ʿ��Ƿ��й�ͬ��ĸ��
     ���product���ɣ���Ȼ���ַ���ʱ�临�Ӷ���O(n^2*k), k��ʾ����ƽ�����ȡ�

     Ȼ�����ǿ��Լ򻯶�������������product��һ������������������Ԥ��������һ�鵥�ʣ�����ÿ�����ʣ�������һ��Integer��ʾ�京�е���ĸ�����
     Ԥ����֮�󣬶���������������ֻ��Ҫ������int��������������֪�����������Ƿ������ͬ��ĸ��
     ���ַ��������ǿ��Խ�ʱ�临�ӶȽ���O(n^2)��

     ��Ȼ�������Ϸ����Ļ����ϣ����ǿ�����һЩС�Ż���
     �������ȶԵ��ʸ��ݳ������ճ��ȴӴ�С��������������for loop���������ǿ��Ը��ݾ������ֱ��������
     �����ټ�������������ɼ����롣
     */

    //time: O(n^2), space: O(n)
    public class Solution {
        public int maxProduct(String[] words) {
            int[] maps = new int[words.length];

            // �����ʰ��ճ��ȴӳ���������
            Arrays.sort(words, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s2.length() - s1.length();
                }
            });

            // ����ÿ�����ʣ�������Ӧ��int����ʾ������ĸ���
            for (int i = 0; i < words.length; i++) {
                int bits = 0;
                for (int j = 0; j < words[i].length(); j++) {
                    char c = words[i].charAt(j);
                    // ע��bit�������ȼ�
                    bits = bits | (1 << (c - 'a'));
                }
                maps[i] = bits;
            }

            int max = 0;
            for (int i = 0; i < words.length; i++) {
                // ��ǰ������û��Ҫ����loop
                if (words[i].length() * words[i].length() <= max)
                    break;
                for (int j = i + 1; j < words.length; j++) {
                    if ((maps[i] & maps[j]) == 0) {
                        max = Math.max(max, words[i].length() * words[j].length());
                        // ����Ľ��ֻ���С��û��Ҫ����loop
                        break;
                    }
                }
            }
            return max;
        }
    }

    /**
     * ֱ�ӿ���ÿ���ַ������������ĸ��ַ���Ȼ��һһö���Ƿ��н�����

     �н�������˻�Ϊ0
     �޽������˻�Ϊ words[i].length() * words[j].length()
     ����д�����´���
     */

    public class Solution2 {
        public int maxProduct(String[] words) {
            int n = words.length;
            int[][] elements = new int[n][26];
            for (int i = 0; i < n; i++) {
                for (int j = 0;j < words[i].length(); j++) {
                    elements[i][words[i].charAt(j) - 'a'] ++;
                }
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    boolean flag = true;
                    for (int k = 0; k < 26; k++) {
                        if (elements[i][k] != 0 && elements[j][k] != 0) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag && words[i].length() * words[j].length()  > ans)
                        ans = words[i].length() * words[j].length();
                }
            }
            return ans;

        }
    }
}

