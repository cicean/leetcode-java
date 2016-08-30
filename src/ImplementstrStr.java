/*
 28	Implement strStr()	22.2%	Easy
 Problem:    Implement strStr()
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/implement-strstr/
 Notes:
 Implement strStr().
 Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
 Solution: 1. Check in the haystack one by one. If not equal to needle, reset the pointer.(TLE) 算法时间复杂度为O((n-m+1)*m)=O(n*m)，空间复杂度是O(1)
           2. Classice KMP solution.
           3. Simplified RK Soluiton. Thanks for [wenyuanhust, wenyuanhust@gmail.com]
 */


public class ImplementstrStr {

	/**
	 *
	 * @param haystack
	 * @param needle
     * @return
     */
	public int strStr(String haystack, String needle) {
		int index = -1;
		if (haystack == null || needle == null) {
			return -1;
		}

		if (needle.length() != 0) {
			for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
				if (haystack.substring(i, i + needle.length()).equals(needle)) {
					index = i;
					break;
				}
			}
		} else {
			return 0;
		}

		return index;
	}

	/**
	 * 复杂度
	 时间 O(N^2) 空间 O(1)

	 思路
	 本题有很多高级算法可以在O(N)时间内解决问题，然而这已经超出面试的范畴。
	 本题在面试中出现的作用就是考察基本的编程素养，以及边界条件的考虑。我们用暴力法即可。
	 * @param haystack
	 * @param needle
     * @return
     */
	 public int strStr_1(String haystack, String needle) {
	        int sLen = haystack.length(), tLen = needle.length();
	        if(tLen == 0) return 0;
	        if (haystack==null || needle==null || sLen==0) return -1;
	        int i = 0, j = 0;
	        while (i < sLen) {
	            for (j = 0; j < tLen && (i+j) < sLen && haystack.charAt(i+j) == needle.charAt(j); ++j);
	            if (j == tLen) return i;
	            ++i;
	        }
	        return (int)-1;
	    }

	/**
	 * P算法
	 复杂度
	 时间 O(N+M) 空间 O(M)

	 思路
	 KMP算法是较为高级的算法。它使用一个next数组，这个数组记录了模式串needle自身的前缀和后缀的重复情况。同样是双指针进行匹配，当失配时可以根据这个数组找到应该将模式串向后位移多少位，避免一些重复的比较。具体的解法这里有两篇文章比较好，一篇是详细讲解KMP算法。

	 如果看完对产生next数组的方法还不太明白，还有一篇是讲解next数组怎么得到的。

	 代码
 	 * @param T
	 * @param next
     */

	 // kmp
	    void getNext(String T, int[] next){
	        int i=0, j=-1;
	        next[0]=-1;
	        int n = next.length;
	        while(i < n - 1){
	            while(j>-1&&T.charAt(j)!=T.charAt(i)) j = next[j];
	            ++i; ++j;
	            if(i < n - 1 && j < n - 1 && T.charAt(j)==T.charAt(i)) next[i]=next[j];
	            else next[i]=j;
	        }
	    }
	    
	    
	    public int strStr_2(String haystack, String needle) {
	        int sLen = haystack.length(), tLen = needle.length();
	        if(tLen == 0) return 0;
	        if (haystack==null || needle==null || sLen==0) return -1;
	        int[] next = new int[tLen+1];
	        getNext(needle, next);
	        int i = 0, j = 0;
	        while (i < sLen) {
	            while (j > -1 && needle.charAt(j) != haystack.charAt(i)) j = next[j];
	            ++i; ++j;
	            if (j == tLen) return i - j;
	        }
	        return -1;
	    }
	    
	    
	    public int strStr_3(String haystack, String needle) {
	        int sLen = haystack.length(), tLen = needle.length();
	        if (tLen == 0) return 0;
	        if (haystack==null || needle==null || sLen==0 || sLen < tLen) return -1;
	        long fh = 0, fn = 0;
	        int head = 0, tail = tLen - 1;
	        for (int i = 0; i < tLen; ++i) {
	            fh += haystack.charAt(i);
	            fn += needle.charAt(i);
	        }
	        while (tail < sLen) {
	            if (fn == fh) {
	                int i = 0;
	                while (i < tLen && needle.charAt(i) == haystack.charAt(i + head)){
	                    ++i;
	                }
	                if (i == tLen) return head;
	            }
	            if (tail == sLen - 1) return -1;
	            fh -= haystack.charAt(head++);
	            fh += haystack.charAt(++tail);
	        }
	        return -1;
	    }
	    
	    public static void main(String[] args) {
	    	ImplementstrStr slt = new ImplementstrStr();
			System.out.println(slt.strStr_2("abcdabc", "d"));
		}
	    
	    
}
