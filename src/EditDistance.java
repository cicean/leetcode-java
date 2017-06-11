import java.util.Scanner;

/*
 72	Edit Distance	26.2%	Hard
 Problem:    Edit Distance
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/edit-distance/
 Notes:
 Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 You have the following 3 operations permitted on a word:
 a) Insert a character
 b) Delete a character
 c) Replace a character
 Solution: Dynamic Programming.
           1. Time: O(mn) Space: O(mn)
           2. Time: O(mn) Space: O(n);
 */

public class EditDistance {
	public int minDistance_1(String word1, String word2) {
        if(word1==word2) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        
        for(int i=0;i<=len1;i++)
            dp[i][0] = i;
        for(int i=0;i<=len2;i++)
            dp[0][i] = i;
        
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else{
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        }
        
        for (int i = 0; i <= len1; i++) {
    		for (int j = 0; j <= len2; j ++){
    			
    			if (j == len2)System.out.println(dp[i][j]);
    			else System.out.print(dp[i][j] + ",");
    		}
    	}
        
        return dp[len1][len2];
    }
    public int minDistance(String word1, String word2) {
        if(word1==word2) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2+1];
        
        for(int i=0;i<=len2;i++)
            dp[i] = i;
        
        for(int i=1;i<=len1;i++){
            int upperLeftBak = dp[0];
            dp[0] = i;
            for(int j=1;j<=len2;j++){
                int upperLeft = upperLeftBak;
                upperLeftBak = dp[j];
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[j] = upperLeft;
                else{
                    dp[j] = Math.min(dp[j],Math.min(dp[j-1],upperLeft))+1;
                }
            }
        }
        return dp[len2];
    }
    
    // google followup
    //dit distance变种，replace的cost是2，delete和insert是1
    public int minDistance_GG(String word1, String word2) {
    	if (word1 == null || word2 == null || word1.equals(word2)) return 0;
    	int m = word1.length();
    	int n = word2.length();
    	int[][] dp = new int[m + 1][n + 1];
    	
    	for (int i = 0; i <= m; i++) {
    		dp[i][0] = i;
    	}
    	
    	for (int i = 0; i <= n; i++ ) {
    		dp[0][i] = i;
    	}
    	
    	for (int i = 1; i <= m; i++) {
    		for (int j = 1; j <= n; j++) {
    			if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
    				dp[i][j] = dp[i - 1][j - 1];
    			} else {
    				
    				//int replace = dp[i + 1][j];
    				//word
    				//System.out.println("inster = " + dp[i][j] + ", delete = " + dp[i][j + 1] + ", repalce = " + (dp[i + 1][j]));
    				dp[i][j] = Math.max(dp[i - 1][j - 1] , dp[i][j - 1]) + 1;
    				//dp[i + 1][j + 1] = insert + delete;
    				System.out.println("dp[i][j] = " + dp[i][j]);
    				//dp[i][j]++;
    			}
    		}
    	}
    	for (int i = 0; i <= m; i++) {
    		for (int j = 0; j <= n; j ++){
    			
    			if (j == n)System.out.println(dp[i][j]);
    			else System.out.print(dp[i][j] + ",");
    		}
    	}
    	
    	return dp[m][n];
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EditDistance slt=new EditDistance();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input word1");
		String word1 =sc.nextLine();
		System.out.println("Please input word2");
		String word2 =sc.nextLine();
		int len = slt.minDistance_1(word1, word2);
		int lenGG = slt.minDistance_GG(word1, word2);
		System.out.println("Minimum number of steps required to convert word1 to word2:"+ len);
		System.out.println("edit google replace cost = 2, restult: " + lenGG);
	}

}
