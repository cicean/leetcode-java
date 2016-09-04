import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 340. Longest Substring with At Most K Distinct Characters  QuestionEditorial Solution  My Submissions
 Total Accepted: 5763
 Total Submissions: 14748
 Difficulty: Hard
 Given a string, find the length of the longest substring T that contains at most k distinct characters.

 For example, Given s = ��eceba�� and k = 2,

 T is "ece" which its length is 3.

 Hide Company Tags Google
 Hide Tags Hash Table String
 Hide Similar Problems (H) Longest Substring with At Most Two Distinct Characters

 */
public class LongestSubstringwithAtMostKDistinctCharacters {

    /** ��Ŀ��չһ�£������������ַ�������K����ͬ�ַ����Ǹ���ô���أ�
     * ע�⵽�����K���Ժܴ��������ϴ������ѭ�����������O(K)����O(1)��
     * �����������Ӷȱ����O(N*K)����ˣ�������Ҫ�����Map�ڲ������Բ��ҡ�
     * ���⣬���������±���Map�ﴦ��value��λ�ã�
     * �����޷�ʹ��Heap����TreeMap��������

    ��ʱ���Ի���˼·��������1��������2���Ľ����������һ�����У�
     ��������1��ֱ�Ӵ�����������ַ���ɾ����һֱɾ��ĳ���ַ������ٳ��֡�
     ��������2�������Ǵ��������ɾ������������ѡ�õ��ǱȽ����ķ��������������κ��ַ������ǵ������ڵ�ʱ�临�Ӷ�û�б䡣

    ����Ҫ��������ɾ����������Ҫ��Map��value������ݸĸģ�
     �ĳ��ַ��ڴ����г��ֵĴ���������һ�����ж��ַ���ɾ��Ϳ������Ƿ��Ϊ��0.

 */
 public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int start = 0;
    int maxLen = 0;

    // Key: letter; value: the number of occurrences.
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    int i;
    for (i = 0; i < s.length(); ++i) {
        char c = s.charAt(i);
        if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
        } else {
            map.put(c, 1);
            while (map.size() > k) {
                char startChar = s.charAt(start++);
                int count = map.get(startChar);
                if (count > 1) {
                    map.put(startChar, count - 1);
                } else {
                    map.remove(startChar);
                }
            }
        }

        maxLen = Math.max(maxLen, i - start + 1);
    }

    return maxLen;
}

}
