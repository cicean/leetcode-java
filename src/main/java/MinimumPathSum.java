/*
 64	Minimum Path Sum	32.2%	Medium
 Problem:    Minimum Path Sum
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/minimum-path-sum/
 Notes:
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
 which minimizes the sum of all numbers along its path.
 Note: You can only move either down or right at any point in time.
 Solution: Dynamic Programming. Space O(N).
 */


public class MinimumPathSum {
	
	public int minPathSum(int[][] grid) {
        if (grid.length == 0) return Integer.MIN_VALUE;
        int M = grid.length, N = grid[0].length;
        int[] dp = new int[N];
        dp[0] = grid[0][0];
        for (int i = 1; i < N; ++i)
            dp[i] = grid[0][i] + dp[i-1];
        
        for (int i = 1; i < M; ++i)
        {
            dp[0] += grid[i][0];
            for (int j = 1; j < N; ++j)
                dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
        }
        
        return dp[N-1];  
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinimumPathSum slt =  new MinimumPathSum();
		int[][] grid = {{4,3,1,6,5},
				{9,3,8,6,4},
				{7,2,5,3,6},
				{1,3,5,7,9},
				{2,4,6,8,0}};
		
		int res = slt.minPathSum(grid);
		System.out.println(res);
	}

}

/**
Analyze two paragraphs.

1. For each paragraph, print the number of distinct words in the paragraph
2. For each paragraph, print the Kth word in alphabetical order
3. For the second paragraph only, print the number of distinct words that contain exactly M letters. Label your outputs.

You can input the two paragraphs in whatever way is convenient for you. Do not distinguish between upper and lower case letters. Words are terminated by white space or punctuation (commas, hyphens, colons, and semi-colons). Sentences are terminated by a question mark or a period.

You'll also need to input a value of K for each paragraph and a value of M for the second paragraph. In the sample data below, K = 5 for the first paragraph, and K = 9 and M = 5 in the second paragraph.

Sample Input:

Paragraph 1:

To be or not to be, that's the question.

Paragraph 2:

A wise man is not one who knows all the answers. But one who knows how to find the answers. A foolish man is one who knows neither.

Sample Output:

Output #1:

number of distinct words in paragraph 1: 7

Output #2:

the 5th distinct word in paragraph 1: that's

Output #3:

number of distinct words in paragraph 2: 17

Output #4:

the 9th distinct word in paragraph 2: knows

Output #5:

the number of distinct words in paragraph 2 of length=5: 1
*/
