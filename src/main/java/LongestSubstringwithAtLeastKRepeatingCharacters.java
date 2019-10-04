/**
 * Created by cicean on 9/8/2016.
 * 395. Longest Substring with At Least K Repeating Characters
 * QuestionEditorial Solution My Submissions Total
 * Accepted: 1622 Total Submissions: 5214 Difficulty: Medium Find the length of
 * the longest substring T of a given string (consists of lowercase letters
 * only) such that every character in T appears no less than k times.
 * 
 * Example 1:
 * 
 * Input: s = "aaabb", k = 3
 * 
 * Output: 3
 * 
 * The longest substring is "aaa", as 'a' is repeated 3 times. Example 2:
 * 
 * Input: s = "ababbc", k = 2
 * 
 * Output: 5
 * 
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is
 * repeated 3 times.
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {

	public int longestSubstring(String s, int k) {
		int[] map = new int[26];
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i) - 'a']++;
		}
		char noChar = 'a';
		boolean containsNoChar = false;
		for (int i = 0; i < 26; i++) {
			noChar = (char) ('a' + i);
			if (map[i] < k && map[i] != 0) {
				containsNoChar = true;
				break;
			}
		}
		if (containsNoChar == false)
			return s.length();
		int ans = 0;
		int start = 0;
		while (s.indexOf(noChar, start) != -1) {
			int end = s.indexOf(noChar, start);
			ans = Math.max(ans, longestSubstring(s.substring(start, end), k));
			start = end + 1;
		}
		ans = Math
				.max(ans, longestSubstring(s.substring(start, s.length()), k));
		return ans;
	}

	public class Solution {
		public int longestSubstring(String s, int k) {
			if (s == null || s.length() < 1)
				return 0;

			int[][] letters = new int[26][s.length() + 1];
			for (int i = 0; i < s.length(); i++) {
				for (int c = 0; c < 26; c++) {
					letters[c][i + 1] = letters[c][i];
				}
				letters[s.charAt(i) - 'a'][i + 1] += 1;
			}
			// May also optimize by deleting letters entries with 0 at end

			int longest = 0;
			for (int start = 0; start < s.length(); start++) {
				if (longest >= s.length() - start)
					return longest;
				for (int end = s.length(); end > start; end--) {
					boolean works = true;
					for (int c = 0; c < 26; c++) {
						int num = letters[c][end] - letters[c][start];
						if (num < k && num > 0) {
							works = false;
							end = s.indexOf('a' + c, start) + 1;
							break;
						}
					}
					if (works) {
						if (end - start > longest)
							longest = end - start;
						break;
					}
				}
			}

			return longest;
		}
	}

	// Java O(n^2) iterator and backtracking solution.

}
