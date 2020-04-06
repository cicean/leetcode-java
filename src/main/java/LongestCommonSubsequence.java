/**
 * 
 * Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Have you met this question in a real interview? Yes
Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.
 * @author cicean
 *
 */
public class LongestCommonSubsequence {

	 public int longestCommonSubsequence(String A, String B) {
	        // write your code here
	        if(A == null || A.length() == 0 || B == null || B.length() == 0) return 0;
	        int[][] f = new int[A.length()+1][B.length()+1];
	        for(int i=0; i<=A.length(); i++){
	            f[i][0] = 0;
	        }
	        for(int j=0; j<B.length(); j++){
	            f[0][j] = 0;
	        }
	        for(int i=1; i<=A.length(); i++){
	            for(int j=1; j<=B.length(); j++){
	                if(A.charAt(i-1) == B.charAt(j-1)){
	                    f[i][j] = f[i-1][j-1]+1;
	                }else{
	                    f[i][j] = Math.max(f[i-1][j], f[i][j-1]);
	                }
	            }
	        }
	        return f[A.length()][B.length()];
	    }

	class Solution {
		public int longestCommonSubsequence(String big, String small) {
			if(big == null || small == null)
				return 0;
			if(small.length() > big.length()) {
				return longestCommonSubsequence(small, big);
			}

			int[] mem = new int[small.length()];
			for(int b = 0; b < big.length(); b++) {
				mem[0] = (big.charAt(b) == small.charAt(0)) ? 1 : mem[0];
				for(int s = small.length() - 1; s >= 1; s--) {
					if(big.charAt(b) == small.charAt(s))
						mem[s] = mem[s - 1] + 1;
				}

				for(int s = 1; s < small.length(); s++) {
					mem[s] = Math.max(mem[s], mem[s - 1]);
				}
			}

			int res = 0;
			for(int r : mem)
				res = Math.max(res, r);

			return res;
		}
	}

	class Solution_dp {
		public int longestCommonSubsequence(String text1, String text2) {
			int nlen = text1.length(), mlen = text2.length();
			char[] ncha = text1.toCharArray(), mcha = text2.toCharArray();
			int[] dp = new int[mlen + 1];
			int temp = 0, temp2 = 0;
			for (int i = 0; i < nlen; i++) {
				temp = 0;
				for (int j = 0; j < mlen; j++) {
					temp2 = dp[j + 1];
					if (ncha[i] == mcha[j]) {
						dp[j + 1] = temp + 1;
					} else {
						dp[j + 1] = Math.max(dp[j], dp[j + 1]);
					}
					temp = temp2;
				}
			}
			return dp[mlen];
		}
	}

	class Solution_2D {
		public int longestCommonSubsequence(String text1, String text2) {
			if(text1 == null || text2 == null
					|| text1.length() == 0 || text2.length() == 0) {
				return 0;
			}
			int m = text1.length();
			int n = text2.length();
			char[] chs1 = text1.toCharArray();
			char[] chs2 = text2.toCharArray();
			int[][] dp = new int[m + 1][n + 1];
			for(int i = 0; i < m; i++) {
				for(int j = 0; j < n; j++) {
					dp[i+1][j+1] = chs1[i] == chs2[j]
							? dp[i][j] + 1 : Math.max(dp[i+1][j], dp[i][j+1]);
				}
			}
			return dp[m][n];
		}
	}
	
}
