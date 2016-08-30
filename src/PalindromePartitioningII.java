import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 132	Palindrome Partitioning II	19.7%	Hard
  Problem:    Palindrome Partitioning II
 Difficulty: Hard
 Source:     https://oj.leetcode.com/problems/palindrome-partitioning-ii/
 Notes:
 Given a string s, partition s such that every substring of the partition is a palindrome.
 Return the minimum cuts needed for a palindrome partitioning of s.
 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 Solution: dp.
 */
 

public class PalindromePartitioningII {
	 public int minCut_1(String s) {
	        int n = s.length();
	        int[] dp = new int[n+1];
	        dp[n] = -1;
	        boolean[][] isP = new boolean[n][n];
	        for (int i = n - 1; i >= 0; --i) {
	            dp[i] = n - 1 - i;
	            for (int j = i; j < n; ++j) {
	                if (s.charAt(i) == s.charAt(j) && (j < i + 2 || isP[i+1][j-1])) isP[i][j] = true;
	                if (isP[i][j] == true) {
	                    dp[i] = Math.min(dp[i], 1 + dp[j+1]);
	                }
	            }
	        }
	        return dp[0];
	    }
    
    public int minCut(String s) {
        int n = s.length();
     
    	boolean dp[][] = new boolean[n][n];
    	int cut[] = new int[n];
     
    	for (int j = 0; j < n; j++) {
    		cut[j] = j; //set maximum # of cut
    		for (int i = 0; i <= j; i++) {
    			if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i+1][j-1])) {
    				dp[i][j] = true;
     
    				// if need to cut, add 1 to the previous cut[i-1]
    				if (i > 0){
    					cut[j] = Math.min(cut[j], cut[i-1] + 1);
    				}else{
    				// if [0...j] is palindrome, no need to cut    
    					cut[j] = 0; 
    				}	
    			}
    		}
    	}
     
    	return cut[n-1];
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PalindromePartitioningII slt = new PalindromePartitioningII();
		//PalindromePartitioning slt = new PalindromePartitioning();
		String s = "aab";
		int res = slt.minCut_1(s);
		System.out.print(res);
	}

}
