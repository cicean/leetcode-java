
/**
 * 5. Longest Palindromic Substring
 * Medium
 *
 * 5660
 *
 * 476
 *
 * Add to List
 *
 * Share
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 * Accepted
 * 818,666
 * Submissions
 * 2,842,844
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 124
 *
 * Microsoft
 * |
 * 30
 *
 * Adobe
 * |
 * 14
 *
 * Bloomberg
 * |
 * 10
 *
 * Facebook
 * |
 * 9
 *
 * Apple
 * |
 * 9
 *
 * Google
 * |
 * 8
 *
 * Oracle
 * |
 * 5
 *
 * Cisco
 * |
 * 4
 *
 * Uber
 * |
 * 2
 *
 * SAP
 * |
 * 2
 * Shortest Palindrome
 * Hard
 * Palindrome Permutation
 * Easy
 * Palindrome Pairs
 * Hard
 * Longest Palindromic Subsequence
 * Medium
 * Palindromic Substrings
 * Medium
 * How can we reuse a previously computed palindrome to compute a larger palindrome?
 * If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
 * Complexity based hint:
 * If we use brute-force and check whether for every start and end position a substring is a palindrome we have O(n^2)
 * start - end pairs and O(n) palindromic checks. Can we reduce the time for palindromic checks to O(1)
 * by reusing some previous computation.
 Source:     https://oj.leetcode.com/problems/longest-palindromic-substring/
 Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Hide Tags String
Hide Similar Problems (H) Shortest Palindrome

 Solution: 1. Time O(n^2), Space O(n^2)
           2. Time O(n^2), Space O(n)
           3. Time O(n^2), Space O(1) (actually much more efficient than 1 & 2)
           4. Time O(n), Space O(n) (Manacher's Algorithm)
           5. Time O(n), Smaller Space than solution 4. (Manacher's Algorithm)
 */

import java.lang.*;
import java.util.Arrays;


public class LongestPalindromicSubstring {

	/**
	 * Approach 3: Dynamic Programming
	 * To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation while validating palindromes. Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome since the two left and right end letters are the same.
	 *
	 * We define P(i,j)P(i,j) as following:
	 *
	 * P(i,j) = \begin{cases} \text{true,} &\quad\text{if the substring } S_i \dots S_j \text{ is a palindrome}\\ \text{false,} &\quad\text{otherwise.} \end{cases}P(i,j)={
	 * true,
	 * false,
	 * ​
	 *
	 * if the substring S
	 * i
	 * ​
	 *  …S
	 * j
	 * ​
	 *   is a palindrome
	 * otherwise.
	 * ​
	 *
	 *
	 * Therefore,
	 *
	 * P(i, j) = ( P(i+1, j-1) \text{ and } S_i == S_j )P(i,j)=(P(i+1,j−1) and S
	 * i
	 * ​
	 *  ==S
	 * j
	 * ​
	 *  )
	 *
	 * The base cases are:
	 *
	 * P(i, i) = trueP(i,i)=true
	 *
	 * P(i, i+1) = ( S_i == S_{i+1} )P(i,i+1)=(S
	 * i
	 * ​
	 *  ==S
	 * i+1
	 * ​
	 *  )
	 *
	 * This yields a straight forward DP solution, which we first initialize the one and two letters palindromes,
	 * and work our way up finding all three letters palindromes, and so on...
	 *
	 * Complexity Analysis
	 *
	 * Time complexity : O(n^2)O(n
	 * 2
	 *  ). This gives us a runtime complexity of O(n^2)O(n
	 * 2
	 *  ).
	 *
	 * Space complexity : O(n^2)O(n
	 * 2
	 *  ). It uses O(n^2)O(n
	 * 2
	 *  ) space to store the table.
	 *
	 * Additional Exercise
	 * @param s
	 * @return
	 */
	 public String longestPalindrome_1(String s) {
	        int n = s.length();
	        boolean[][] dp = new boolean[n][n];
	        int idx = 0, maxLen = 0;
	        for (int k = 0; k < n; ++k) {
	            for (int i = 0; i + k < n; ++i) {
	                if (k == 0 || k == 1) dp[i][i+k] = (s.charAt(i) == s.charAt(i+k));
	                else dp[i][i+k] = (s.charAt(i) == s.charAt(i+k)) ? dp[i+1][i+k-1] : false;
	                if (dp[i][i+k] == true && (k+1) > maxLen) {
	                    idx = i; maxLen = k + 1;
	                }
	            }
	        }
	        return s.substring(idx, idx + maxLen);
	    }
	    public String longestPalindrome_2(String s) {
	        int n = s.length();
	        boolean[][] dp = new boolean[2][n];
	        int idx = 0, maxLen = 0;
	        int cur = 1, last = 0;
	        for (int i = 0; i < n; ++i) {
	            cur = cur + last - (last = cur);
	            for (int j = i; j >=0; --j) {
	                if (j == i || j == i - 1)
	                    dp[cur][j] = (s.charAt(i) == s.charAt(j));
	                else dp[cur][j] = (s.charAt(i) == s.charAt(j)) && dp[last][j + 1];
	                if (dp[cur][j] && (i - j + 1) > maxLen) {
	                    idx = j; maxLen = i - j + 1;
	                }
	            }
	        }
	        return s.substring(idx, idx + maxLen);
	    }
	    public String longestPalindrome_3(String s) {
	        int n = s.length();
	        int idx = 0, maxLen = 0;
	        for (int i = 0; i < n; ++i) {
	            for (int j = 0; j <= 1; ++j) {
	                boolean isP = true;
	                for (int k = 0; i - k >= 0 && i + j + k < n && isP; ++k) {
	                    isP = (s.charAt(i - k) == s.charAt(i + j + k));
	                    if (isP && (j + 1 + k*2) > maxLen) {
	                        idx = i - k; maxLen = j + 1 + k*2;
	                    }
	                }
	            }
	        }
	        return s.substring(idx, idx + maxLen);
	    }
	    public String longestPalindrome_4(String s) {
	        int n = s.length();
	        int idx = 0, maxLen = 0;
	        StringBuffer sb = new StringBuffer();
	        sb.append('^');
	        for (int i = 0; i < n; ++i) {
	            sb.append('#');
	            sb.append(s.charAt(i));
	        }
	        sb.append("#$");
	        n = 2 * n + 3;
	        int mx = 0, id = 0;
	        int[] p = new int[n];
	        Arrays.fill(p,0);
	        for (int i = 1; i < n - 1; ++i) {
	            p[i] = (mx > i) ? Math.min(p[2 * id - i], mx - i) : 0;
	            while (sb.charAt(i + 1 + p[i]) == sb.charAt(i - 1 - p[i])) ++p[i];
	            if (i + p[i] > mx) {
	                id = i; mx = i + p[i];
	            }
	            if (p[i] > maxLen) {
	                idx = i; maxLen = p[i];
	            }
	        }
	        idx = (idx - maxLen - 1) / 2;
	        return s.substring(idx, idx + maxLen);
	    }
	    public String longestPalindrome_5(String s) {
	        int n = s.length();
	        int idx = 0, maxLen = 0;
	        int mx = 0, id = 0;
	        int[] p = new int[2*n+1];
	        Arrays.fill(p,0);
	        for (int i = 0; i < 2*n+1; ++i) {
	            p[i] = (mx > i) ? Math.min(p[2*id-i], mx - i) : 0;
	            int left = i - 1 - p[i],  right = i + 1 + p[i];
	            while (left>=0 && right <= 2*n) {
	                if (left % 2 == 0 || s.charAt(left/2) == s.charAt(right/2)) {
	                    ++p[i];
	                } else break;
	                --left;
	                ++right;
	            }
	            if (i + p[i] > mx) {
	                id = i; mx = i + p[i];
	            }
	            if (p[i] > maxLen) {
	                idx = i; maxLen = p[i];
	            }
	        }
	        idx = (idx - maxLen) / 2;
	        return s.substring(idx, idx + maxLen);
	    }
	    
	    public static void main(String[] args) {
			LongestPalindromicSubstring slt = new LongestPalindromicSubstring();
			String len = slt.longestPalindrome_1("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybl");
			System.out.println(len);
		}
}
