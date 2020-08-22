import java.util.*;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * Medium
 *
 * 818
 *
 * 16
 *
 * Add to List
 *
 * Share
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 * Accepted
 * 99,938
 * Submissions
 * 205,103
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * 2
 * Longest Substring Without Repeating Characters
 * Medium
 * Sliding Window Maximum
 * Hard
 * Longest Substring with At Most K Distinct Characters
 * Hard
 * Subarrays with K Different Integers
 * Hard
 */

/*
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = ��eceba��,

T is "ece" which its length is 3.

��������Խⷨ��ά��һ��sliding window����������ַ���ֻ�����������ͬ�ַ�����Ҫ���һ�����ַ�ʱ����Ҫ��ȫȥ��֮ǰ��ĳһ���ַ������г��֡�����������������Ҫ���ǣ�

1�������е������ַ��У����ѡ���ȥ�������г��ֵ��ַ���

2����ѡ����ȥ�����ַ�����θĵ������ڣ�

��������1��������ʼ�����ַ���һ���ᱻȥ���ģ����Ƿ�����Ӧ��ѡ������ַ�Ȼ��ȥ�������г���ô���ԡ�abac��Ϊ�������Է��ֵ�ɨ�赽cʱ��a��һ���ᱻȥ���ģ��������ȥ�����г��ֹ���a����ô���ֻʣ��"c"�ˡ���ʱӦ����ȥ�����г��ֵ�b��˳��ȥ�����ʼ��a���Ӷ��õ�"ac"���ɴ˹�֮��ѡ���׼Ӧ�����ַ��������ֵ�λ�ã������ֵ�λ��Խ���磩��������ֱ�ȫ��ɾ��������С�ĳ���Խ�١���ˣ�Ӧ��ɾ��������λ����������ַ���

��������2��������Ŀ�涨�˴��������ֻ����2���ַ�����ʵ��ôɾ�����ԣ�����Ŀ��Դ��������ɾ�������Ŀ���ֱ�����´�������ѡ�ַ���������λ�õ���һ���ַ���ͷ��

���´�������һ��Map�ṹ����ʾsliding window��keyΪ�ַ���valueΪ��Ӧ��������λ�á���ʵҲ������ȫ����ʹ��Map����Ϊ�����entry���ֻ���������Ƚ��˷ѿռ䡣�����ǿ��ǵ��˺�������չ�Լ������ά����

����Ϊ�˻��Map�����λ��������ֵ��ַ������������е�entry������ʵ�Ƿǳ���Ч�ģ������ǵ�Map��ʵ��ֻ��2��Ԫ�أ����Ա����Ŀ���Ҳ��С�����Ժ��Բ��ơ�



 */
public class LongestSubstringwithAtMostTwoDistinctCharacters {

	/**
	 * Solution
	 * Approach 1: Sliding Window
	 * Intuition
	 *
	 * To solve the problem in one pass let's use here sliding window approach with two set pointers left and right serving as the window boundaries.
	 *
	 * The idea is to set both pointers in the position 0 and then move right pointer to the right while the window contains not more than two distinct characters. If at some point we've got 3 distinct characters, let's move left pointer to keep not more than 2 distinct characters in the window.
	 *
	 * compute
	 *
	 * Basically that's the algorithm : to move sliding window along the string, to keep not more than 2 distinct characters in the window, and to update max substring length at each step.
	 *
	 * There is just one more question to reply - how to move the left pointer to keep only 2 distinct characters in the string?
	 *
	 * Let's use for this purpose hashmap containing all characters in the sliding window as keys and their rightmost positions as values. At each moment, this hashmap could contain not more than 3 elements.
	 *
	 * compute
	 *
	 * For example, using this hashmap one knows that the rightmost position of character e in "eeeeeeeet" window is 8 and so one has to move left pointer in the position 8 + 1 = 9 to exclude the character e from the sliding window.
	 *
	 * Do we have here the best possible time complexity? Yes, we do - it's the only one pass along the string with N characters and the time complexity is \mathcal{O}(N)O(N).
	 *
	 * Algorithm
	 *
	 * Now one could write down the algortihm.
	 *
	 * Return N if the string length N is smaller than 3.
	 * Set both set pointers in the beginning of the string left = 0 and right = 0 and init max substring length max_len = 2.
	 * While right pointer is less than N:
	 * If hashmap contains less than 3 distinct characters, add the current character s[right] in the hashmap and move right pointer to the right.
	 * If hashmap contains 3 distinct characters, remove the leftmost character from the hashmap and move the left pointer so that sliding window contains again 2 distinct characters only.
	 * Update max_len.
	 * Implementation
	 *
	 * Current
	 * 1 / 29
	 *
	 * Complexity Analysis
	 *
	 * Time complexity : \mathcal{O}(N)O(N) where N is a number of characters in the input string.
	 *
	 * Space complexity : \mathcal{O}(1)O(1) since additional space is used only for a hashmap with at most 3 elements.
	 *
	 * Problem generalization
	 *
	 * The same sliding window approach could be used to solve the generalized problem :
	 * @param s
	 * @return
	 */

	public int lengthOfLongestSubstringTwoDistinct(String s) {  
	    int start = 0;  
	    int maxLen = 0;  
	  
	    // Key: letter; value: the index of the last occurrence.  
	    Map<Character, Integer> map = new HashMap<Character, Integer>();  
	    int i;  
	    for (i = 0; i < s.length(); ++i) {  
	        char c = s.charAt(i);  
	        if (map.size() == 2 && !map.containsKey(c)) {  
	            // Pick the character with the leftmost last occurrence.  
	            char charEndsMostLeft = ' ';  
	            int minLast = s.length();  
	            for (char ch : map.keySet()) {  
	                int last = map.get(ch);  
	                if (last < minLast) {  
	                    minLast = last;  
	                    charEndsMostLeft = ch;  
	                }  
	            }  
	  
	            map.remove(charEndsMostLeft);  
	            start = minLast + 1;  
	        }  
	        map.put(c, i);  
	        maxLen = Math.max(maxLen, i - start + 1);  
	    }  
	    return maxLen;  
	}


	/**
	 * two pointer no map
	 */
	class Solution {
		public int lengthOfLongestSubstringTwoDistinct(String s) {
			char last_char = '1';
			char scecond_last_char = '1';
			int last_char_count = 0;
			int current_max = 0;
			int max = 0;

			for (char ch : s.toCharArray()) {
				if (ch == last_char || ch == scecond_last_char) {
					current_max++;
				} else {
					current_max = last_char_count + 1;
				}

				if (ch == last_char) {
					last_char_count++;
				} else {
					scecond_last_char = last_char;
					last_char = ch;
					last_char_count = 1;
				}

				max = Math.max(current_max, max);
			}

			return max;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "eceba";
		LongestSubstringwithAtMostTwoDistinctCharacters slt = new LongestSubstringwithAtMostTwoDistinctCharacters();
		int len = slt.lengthOfLongestSubstringTwoDistinct(s);
		System.out.print(len);
		
	}

}

/*
 * ��Ŀ��չһ�£������������ַ�������K����ͬ�ַ����Ǹ���ô���أ�ע�⵽�����K���Ժܴ��������ϴ������ѭ�����������O(K)����O(1)�������������Ӷȱ����O(N*K)����ˣ�������Ҫ�����Map�ڲ������Բ��ҡ����⣬���������±���Map�ﴦ��value��λ�ã������޷�ʹ��Heap����TreeMap��������

��ʱ���Ի���˼·��������1��������2���Ľ����������һ�����У���������1��ֱ�Ӵ�����������ַ���ɾ����һֱɾ��ĳ���ַ������ٳ��֡���������2�������Ǵ��������ɾ������������ѡ�õ��ǱȽ����ķ��������������κ��ַ������ǵ������ڵ�ʱ�临�Ӷ�û�б䡣

����Ҫ��������ɾ����������Ҫ��Map��value������ݸĸģ��ĳ��ַ��ڴ����г��ֵĴ���������һ�����ж��ַ���ɾ��Ϳ������Ƿ��Ϊ��0.
 
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
 
 */



