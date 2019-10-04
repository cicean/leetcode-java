/*
 97	Interleaving String	20.7%	Hard
 Problem:    Interleaving String
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/interleaving-string/
 Notes:
 Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 For example,
 Given:
 s1 = "aabcc",
 s2 = "dbbca",
 When s3 = "aadbbcbcac", return true.
 When s3 = "aadbbbaccc", return false.
 Solution: 1. recusive 
 			2. dp. O(MN) time & space. 'dp[i][j] == true' means that there is at least one way to construct 
              the string s3[0...i+j-1] by interleaving s1[0...j-1] and s2[0...i-1].
 */

public class InterleavingString {
	
	 public boolean isInterleave_1(String s1, String s2, String s3) {  
	        if (s1.length()+s2.length()!=s3.length()) return false;  
	        return rec(s1,0,s2,0,s3,0);  
	    }  
	      
	    public boolean rec(String s1, int p1, String s2, int p2, String s3, int p3){  
	        if (p3==s3.length()) return true;  
	        if (p1==s1.length()) return s2.substring(p2).equals(s3.substring(p3));  
	        if (p2==s2.length()) return s1.substring(p1).equals(s3.substring(p3));  
	        if (s1.charAt(p1)==s3.charAt(p3)&&s2.charAt(p2)==s3.charAt(p3))  
	            return rec(s1,p1+1,s2,p2,s3,p3+1) || rec(s1,p1,s2,p2+1,s3,p3+1);  
	        else if (s1.charAt(p1)==s3.charAt(p3))  
	            return rec(s1,p1+1,s2,p2,s3,p3+1);  
	        else if (s2.charAt(p2)==s3.charAt(p3))  
	            return rec(s1,p1,s2,p2+1,s3,p3+1);  
	        else return false;  
	    } 
	
	public boolean isInterleave_2(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l1 == 0) return s2.compareTo(s3) == 0;
        if (l2 == 0) return s1.compareTo(s3) == 0;
        if (l1 + l2 != l3) return false;
        boolean[][] dp = new boolean[l1+1][l2+1];
        dp[0][0] = true;
        for (int i = 1; i <= l1; ++i) {
            dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
        }
        for (int j = 1; j <= l2; ++j) {
            dp[0][j] = dp[0][j-1] && (s2.charAt(j-1) == s3.charAt(j-1));
        }
        for (int i = 1; i <= l1; ++i) {
            for (int j = 1; j <= l2; ++j) {
                if (s1.charAt(i - 1) == s3.charAt(i+j-1)) dp[i][j] = dp[i-1][j];
                if (s2.charAt(j - 1) == s3.charAt(i+j-1)) dp[i][j] = dp[i][j] | dp[i][j-1];
            }
        }
        return dp[l1][l2];
    }
    public boolean isInterleave_2_1(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l1 == 0) return s2.compareTo(s3) == 0;
        if (l2 == 0) return s1.compareTo(s3) == 0;
        if (l1 + l2 != l3) return false;
        boolean[] dp = new boolean[l2+1];
        dp[0] = true;
        for (int j = 1; j <= l2; ++j) {
            dp[j] = dp[j-1] && (s2.charAt(j-1) == s3.charAt(j-1));
        }
        for (int i = 1; i <= l1; ++i) {
            dp[0] = dp[0] && (s1.charAt(i-1) == s3.charAt(i-1));
            for (int j = 1; j <= l2; ++j) {
                boolean before = dp[j]; dp[j] = false;
                if (s1.charAt(i - 1) == s3.charAt(i+j-1)) dp[j] = before;
                if (s2.charAt(j - 1) == s3.charAt(i+j-1)) dp[j] = dp[j] | dp[j-1];
            }
        }
        return dp[l2];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterleavingString slt = new InterleavingString();
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		String s4 = "aadbbbaccc";
		System.out.println("s1 = "+"aabcc, "+"s1 = "+"aabcc, "+"When s3 = "+ s3 + " Rerturn " +slt.isInterleave_1(s1, s2, s3));
		System.out.println("s1 = "+"aabcc, "+"s1 = "+"aabcc, "+"When s3 = "+ s4 + " Rerturn " +slt.isInterleave_1(s1, s2, s4));
	}

}
