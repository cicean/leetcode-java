import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 340. Longest Substring with At Most K Distinct Characters  QuestionEditorial Solution  My Submissions
 * Total Accepted: 5763
 * Total Submissions: 14748
 * Difficulty: Hard
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * For example, Given s = ��eceba�� and k = 2,
 * <p>
 * T is "ece" which its length is 3.
 * <p>
 * Hide Company Tags Google
 * Hide Tags Hash Table String
 * Hide Similar Problems (H) Longest Substring with At Most Two Distinct Characters
 */
public class LongestSubstringwithAtMostKDistinctCharacters {

    /**
     * ��Ŀ��չһ�£������������ַ�������K����ͬ�ַ����Ǹ���ô���أ�
     * ע�⵽�����K���Ժܴ��������ϴ������ѭ�����������O(K)����O(1)��
     * �����������Ӷȱ����O(N*K)����ˣ�������Ҫ�����Map�ڲ������Բ��ҡ�
     * ���⣬���������±���Map�ﴦ��value��λ�ã�
     * �����޷�ʹ��Heap����TreeMap��������
     * <p>
     * ��ʱ���Ի���˼·��������1��������2���Ľ����������һ�����У�
     * ��������1��ֱ�Ӵ�����������ַ���ɾ����һֱɾ��ĳ���ַ������ٳ��֡�
     * ��������2�������Ǵ��������ɾ������������ѡ�õ��ǱȽ����ķ��������������κ��ַ������ǵ������ڵ�ʱ�临�Ӷ�û�б䡣
     * <p>
     * ����Ҫ��������ɾ����������Ҫ��Map��value������ݸĸģ�
     * �ĳ��ַ��ڴ����г��ֵĴ���������һ�����ж��ַ���ɾ��Ϳ������Ƿ��Ϊ��0.
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

    public int lengthOfLongestSubstringKDistinct_1(String s, int k) {
        if (s == null || k == 0) return 0;

        List<Character> list = new ArrayList<>();
        int len = 0;
        String res = "";
        int i = 0;
        int j = 0;
        while (i < s.length() && j < s.length() && s.length() - i > len) {
        	System.out.println("i = " + i + ", j = " + j);
            if (!list.contains(s.charAt(j))) {
                list.add(s.charAt(j));
            }

            if (list.size() <= k) {
                j++;
            } 
            
            if (list.size() > k || j == s.length()) {
                if (len < (j - i)) {
                    len = j - i;
                    res = s.substring(i, j);
                }
                i = j - 1;
                list.removeAll(list);
            }
           
        }

        return len;
    }

    class Solution {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            if (s == null || "".equals(s) || k == 0) return 0;
            int[] map = new int[256];
            int start = 0, end = 0, count = 0, maxLength = 0;
            char[] chs = s.toCharArray();
            while (end < chs.length) {
                if (map[chs[end++]]++ == 0) count++;
                while (count > k) {
                    if (--map[chs[start++]] == 0) count--;
                }
                maxLength = Math.max(maxLength, end-start);
            }
            return maxLength;
        }
    }
    
    
    
    
    public static void main(String[] args) {
		LongestSubstringwithAtMostKDistinctCharacters slt = new LongestSubstringwithAtMostKDistinctCharacters();
		System.out.println(slt.lengthOfLongestSubstringKDistinct_1("bacc", 2));
		
	}

}
