import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 340. Longest Substring with At Most K Distinct Characters  QuestionEditorial Solution  My Submissions
 Total Accepted: 5763
 Total Submissions: 14748
 Difficulty: Hard
 Given a string, find the length of the longest substring T that contains at most k distinct characters.

 For example, Given s = “eceba” and k = 2,

 T is "ece" which its length is 3.

 Hide Company Tags Google
 Hide Tags Hash Table String
 Hide Similar Problems (H) Longest Substring with At Most Two Distinct Characters

 */
public class LongestSubstringwithAtMostKDistinctCharacters {

    /** 题目扩展一下，如果允许最长子字符串含有K个不同字符，那该怎么办呢？
     * 注意到这里的K可以很大，所以以上代码的内循环开销变成了O(K)而非O(1)，
     * 进而整个复杂度变成了O(N*K)。因此，我们需要避免对Map内部的线性查找。
     * 另外，由于数组下标在Map里处于value的位置，
     * 所以无法使用Heap或者TreeMap进行排序。

    这时可以换个思路，在问题1）和问题2）的解决方案上做一点折中：
     对于问题1）直接从左往右逐个字符的删除，一直删到某个字符不会再出现。
     对于问题2）由于是从左到右逐个删除，调整窗口选用的是比较慢的方法，不会跳过任何字符，但是调整窗口的时间复杂度没有变。

    由于要从左往右删除，这里需要把Map里value存的内容改改，
     改成字符在窗口中出现的次数。这样一来，判断字符被删光就看次数是否减为了0.

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
