import java.util.*;

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



