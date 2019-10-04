/*
 76	Minimum Window Substring	18.9%	Hard
 Problem:    Minimum Window Substring
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/minimum-window-substring/
 Notes:
 Given a string S and a string T, find the minimum window in S which will contain all the 
 characters in T in complexity O(n).
 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".
 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".
 If there are multiple such windows, you are guaranteed that there will always be only one unique 
 minimum window in S.
 Solution: 1. Use two pointers: start and end. 
              First, move 'end'. After finding all the needed characters, move 'start'.
           2. Use array as hashtable.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MinimumWindowSubstring {
	
	public String minWindow(String S, String T) {
        int N = S.length(), M = T.length();
        if (N < M) return new String("");
        int[] need = new int[256];
        int[] find = new int[256];
        for (int i = 0; i < M; ++i)
            need[T.charAt(i)]++;
        int count = 0, resStart = -1, resEnd = N;       
        for (int start = 0, end = 0; end < N; ++end)
        {
            if (need[S.charAt(end)] == 0)
                continue;
            if (find[S.charAt(end)] < need[S.charAt(end)])
                count++;
            find[S.charAt(end)]++;
            if (count != M) continue;
            // move 'start'
            for (; start < end; ++start) {
                if (need[S.charAt(start)] == 0) continue;
                if (find[S.charAt(start)] <= need[S.charAt(start)]) break;
                find[S.charAt(start)]--;
            }
            // update result
            if (end - start < resEnd - resStart) {
                resStart = start;
                resEnd = end;
            }
        }
        return (resStart == -1) ? new String("") : S.substring(resStart, resEnd + 1); 
    }

    public String minWindowSubstring(String str, Set<Character> chars) {
        Map<Character, Integer> charToCount = new HashMap<>();
        int windowSize = str.length() + 1, minStart = 0;
        int count = 0;
        for(int start = 0, end = 0; end < str.length(); ++end) {
            char ch = str.charAt(end);
            if(chars.contains(ch)) continue;
            if(charToCount.get(ch) == 0) {count++; charToCount.replace(ch, 1);}
            else charToCount.replace(ch, charToCount.get(ch) + 1);
            if(count == chars.size()) {
                while(chars.contains(str.charAt(start)) || (charToCount.get(str.charAt(start)) > 1)) {
                    if(charToCount.get(str.charAt(start)) > 1) charToCount.replace(ch, charToCount.get(ch) - 1);
                    start++;
                }
               System.out.print("2: count size : " + count + " 2 : charToCount.size() : " + charToCount.size());
                int size = end - start + 1;
                if(windowSize > size) {
                    windowSize = size;
                    minStart = start;
                }
            }
        }
        return windowSize > str.length() ? "" : str.substring(minStart, windowSize);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinimumWindowSubstring slt = new MinimumWindowSubstring();
		String S = "cbcacb";
		String T = "abc";
		System.out.println(slt.minWindow(S, T));
	}
	
}
