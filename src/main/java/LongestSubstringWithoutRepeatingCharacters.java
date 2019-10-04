import java.util.*;

/*
 3	Longest Substring Without Repeating Characters	20.8%	Medium
 Source:     https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
 Notes:
 Given a string, find the length of the longest substring without repeating characters. 
 For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
 For "bbbbb" the longest substring is "b", with the length of 1.
 Solution: 1. Pay attention when moving the 'start' pointer forward.
           2. More space, but maybe faster.
 */

public class LongestSubstringWithoutRepeatingCharacters extends Exception{

	 public int lengthOfLongestSubstring_1(String s) {
		   	if (s == null) throw new RuntimeException();
	        boolean[] hash = new boolean[256];
	        Arrays.fill(hash,false);
	        int n = s.length();
	        if (n <= 1) return n;
	        int start = 0, end = 0, res = 0;
	        while (end < n && start + res < n) {
	            if (hash[s.charAt(end)] == false) {
	                hash[s.charAt(end++)] = true;
	            } else {
	                hash[s.charAt(start++)] = false;
	            }
	            res = Math.max(res, end - start);
	        }
	        return res;
	    }

	/**
	 *
	 *
	 Approach #3 Sliding Window Optimized [Accepted]
	 The above solution requires at most 2n steps. In fact, it could be optimized to require only n steps. Instead of using a set to tell if a character exists or not, we could define a mapping of the characters to its index. Then we can skip the characters immediately when we found a repeated character.

	 The reason is that if s[j]s[j] have a duplicate in the range [i, j)[i,j) with index j'j
	 ​′
	 ​​ , we don't need to increase ii little by little. We can skip all the elements in the range [i, j'][i,j
	 ​′
	 ​​ ] and let ii to be j' + 1j
	 ​′
	 ​​ +1 directly.
	 * Java (Assuming ASCII 128)

	 The previous implements all have no assumption on the charset of the string s.

	 If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.

	 Commonly used tables are:

	 int[26] for Letters 'a' - 'z' or 'A' - 'Z'
	 int[128] for ASCII
	 int[256] for Extended ASCII

	 Complexity Analysis

	 Time complexity : O(n)O(n). Index jj will iterate nn times.

	 Space complexity (HashMap) : O(min(m, n))O(min(m,n)). Same as the previous approach.

	 Space complexity (Table): O(m)O(m). mm is the size of the charset.

	 * @param s
	 * @return
	 */

	public int lengthOfLongestSubstring_2(String s) {
	        int[] hash = new int[256];
	        Arrays.fill(hash, -1);
	        int n = s.length();
	        if (n <= 1) return n;
	        hash[s.charAt(0)] = 0;
	        int start = 0, res = 1, cur = 0;
	        while (++cur < n) {
	            if (hash[s.charAt(cur)] >= start) {
	                start = hash[s.charAt(cur)] + 1;
	            }
	            res = Math.max(res, cur - start + 1);
	            hash[s.charAt(cur)] = cur;
	        }
	        return res;
	    }

	/**
	 * Approach #2 Sliding Window [Accepted]
	 Algorithm

	 The naive approach is very straightforward. But it is too slow. So how can we optimize it?

	 In the naive approaches, we repeatedly check a substring to see if it has duplicate character.
	 But it is unnecessary. If a substring s_{ij}from index ii to j - 1j−1 is already checked to have
	 no duplicate characters. We only need to check if s[j]s[j] is already in the substring s_{ij.

	 To check if a character is already in the substring, we can scan the substring, which leads to an O(n^2) algorithm. But we can do better.

	 By using HashSet as a sliding window, checking if a character in the current can be done in O(1)O(1).

	 A sliding window is an abstract concept commonly used in array/string problems. A window is a range of elements in the array/string which usually defined by the start and end indices, i.e. [i, j)[i,j) (left-closed, right-open). A sliding window is a window "slides" its two boundaries to the certain direction. For example, if we slide [i, j)[i,j) to the right by 11 element, then it becomes [i+1, j+1)[i+1,j+1) (left-closed, right-open).

	 Back to our problem. We use HashSet to store the characters in current window [i, j)[i,j) (j = ij=i initially). Then we slide the index jj to the right. If it is not in the HashSet, we slide jj further. Doing so until s[j] is already in the HashSet. At this point, we found the maximum size of substrings without duplicate characters start with index ii. If we do this for all ii, we get our answer.

	 Complexity Analysis

	 Time complexity : O(2n) = O(n)O(2n)=O(n). In the worst case each character will be visited twice by ii and jj.

	 Space complexity : O(min(m, n))O(min(m,n)). Same as the previous approach. We need O(k)O(k) space for the sliding window, where kk is the size of the Set. The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet mm.
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s == null) return 0;
		int i = 0, j = 0, max = 0;
	    Set<Character> set = new HashSet<>();
	    
	    while (j < s.length()) {
	        if (!set.contains(s.charAt(j))) {
	            set.add(s.charAt(j++));
	            max = Math.max(max, set.size());
	        } else {
	            set.remove(s.charAt(i++));
	        }
	    }
	    
	    return max;

	}


	    
	    public static void main(String[] args) {
	    	LongestSubstringWithoutRepeatingCharacters slt = new LongestSubstringWithoutRepeatingCharacters();
			
			String test = "abcabcbb";
			
			System.out.println(slt.lengthOfLongestSubstring(test));
			System.out.println(slt.lengthOfLongestSubstring_1(test));
		}
}
