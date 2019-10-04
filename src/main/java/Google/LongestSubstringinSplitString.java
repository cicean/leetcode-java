package Google;

import java.util.HashSet;
import java.util.Set;

import Bloomberg.MaximumSumPathinTwoArrays;

/**
 * Given a string "hello", each cahracter can split the string into several pieces:
h -> "", "ello"
e -> "h", "llo"
l -> "he", "", "o". 留学申请论坛-丢�亩三分地
o -> "hell", ""-google 1point3acres
For all the split results, find one whose longest substring is shortest among all results, return the splitting character. (should return 'l' in this case)
Follow up: optimize space complexity
 * @author cicean
 *
 */

public class LongestSubstringinSplitString {
	
	public char LongestSubstringinSplitString(String s) {
		char res = '0';
		if (s == null) return res;
		int shortest = Integer.MAX_VALUE;
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			if (set.contains(s.charAt(i))) continue;
			String[] split = s.split(String.valueOf(s.charAt(i)));
			int longestsubstring = 0;
			for (String sub : split) {
				longestsubstring = Math.max(longestsubstring, sub.length());
			}
			if (longestsubstring < shortest) {
				shortest = longestsubstring;
				res = s.charAt(i);
			}
			set.add(s.charAt(i));
		}
		return res;
	}
	
	public static void main(String[] args) {
		LongestSubstringinSplitString slt = new LongestSubstringinSplitString();
		System.out.println(slt.LongestSubstringinSplitString("hello"));
	}

}
