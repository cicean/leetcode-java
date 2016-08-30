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
	
}
