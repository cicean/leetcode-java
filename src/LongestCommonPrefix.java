
/*
 14	Longest Common Prefix	25.7%	Easy
 Problem:    Longest Common Prefix
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/longest-common-prefix/
 Notes:
 Write a function to find the longest common prefix string amongst an array of strings.
 Solution: ...
 */

public class LongestCommonPrefix {
 
	public String longestCommonPrefix(String[] strs) {
		if (strs != null && strs.length == 0) {
			return "";
		}

		for (int i = 0; i < strs[0].length(); ++i) {
			for (int j = 1; j < strs.length; ++j) {
				if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
					return strs[0].substring(0, i);
				}
			}
		}

		return strs[0];
	}
	
	public static void main(String[] args) {
		String[] strs = { "abc", "acd", "ade" };

		LongestCommonPrefix slt = new LongestCommonPrefix();
		System.out.println(slt.longestCommonPrefix(strs));
	}
	
}
