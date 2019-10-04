import java.util.*;

/*
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.

这题的线性解法是维护一个sliding window，里面的子字符串只含最多两个不同字符。当要添加一个新字符时，需要完全去掉之前的某一个字符的所有出现。这里有两个问题需要考虑：

1）在已有的两个字符中，如何选择该去掉其所有出现的字符？

2）在选定该去掉的字符后，如何改调整窗口？

对于问题1，窗口起始处的字符是一定会被去掉的，但是否总是应该选择这个字符然后去掉其所有出现么？以“abac”为例，可以发现当扫描到c时，a是一定会被去掉的，但是如果去掉所有出现过的a，那么最后只剩下"c"了。这时应该是去掉所有出现的b，顺便去掉了最开始的a，从而得到"ac"。由此观之，选择标准应该是字符的最后出现的位置，最后出现的位置越左（早），则其出现被全部删除后所减小的长度越少。因此，应该删光最后出现位置在最左的字符。

对于问题2，由于题目规定了窗口里最多只会有2种字符，其实怎么删都可以：慢点的可以从左到右逐个删除，快点的可以直接让新窗口以所选字符的最后出现位置的下一个字符打头。

以下代码用了一个Map结构来表示sliding window，key为字符，value为对应的最后出现位置。其实也可以完全避免使用Map，因为里面的entry最多只有两个，比较浪费空间。这里是考虑到了后来的扩展以及代码的维护。

这里为了获得Map的最后位置最早出现的字符，遍历了所有的entry。这其实是非常低效的，但考虑到Map里实际只有2个元素，所以遍历的开销也很小，可以忽略不计。



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
 * 题目扩展一下，如果允许最长子字符串含有K个不同字符，那该怎么办呢？注意到这里的K可以很大，所以以上代码的内循环开销变成了O(K)而非O(1)，进而整个复杂度变成了O(N*K)。因此，我们需要避免对Map内部的线性查找。另外，由于数组下标在Map里处于value的位置，所以无法使用Heap或者TreeMap进行排序。

这时可以换个思路，在问题1）和问题2）的解决方案上做一点折中：对于问题1）直接从左往右逐个字符的删除，一直删到某个字符不会再出现。对于问题2）由于是从左到右逐个删除，调整窗口选用的是比较慢的方法，不会跳过任何字符，但是调整窗口的时间复杂度没有变。

由于要从左往右删除，这里需要把Map里value存的内容改改，改成字符在窗口中出现的次数。这样一来，判断字符被删光就看次数是否减为了0.
 
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



