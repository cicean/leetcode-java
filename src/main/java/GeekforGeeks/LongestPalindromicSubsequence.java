package GeekforGeeks;

/**
 * Created by cicean on 9/29/2016.
 * Dynamic Programming | Set 12 (Longest Palindromic Subsequence)
 Given a sequence, find the length of the longest palindromic subsequence in it.
 For example, if the given sequence is ¡°BBABCBCAB¡±,
 then the output should be 7 as ¡°BABCBAB¡± is the longest palindromic subseuqnce in it.
 ¡°BBBBB¡± and ¡°BBCBB¡± are also palindromic subsequences of the given sequence, but not the longest ones.

 The naive solution for this problem is to generate all subsequences of the given sequence and find the longest palindromic subsequence.
 This solution is exponential in term of time complexity. Let us see how this problem possesses both important properties of a Dynamic Programming (DP) Problem and can efficiently solved using Dynamic Programming.

 1) Optimal Substructure:
 Let X[0..n-1] be the input sequence of length n and L(0, n-1) be the length of the longest palindromic subsequence of X[0..n-1].

 If last and first characters of X are same, then L(0, n-1) = L(1, n-2) + 2.
 Else L(0, n-1) = MAX (L(1, n-1), L(0, n-2)).

 Following is a general recursive solution with all cases handled.

 // Everay single character is a palindrom of length 1
 L(i, i) = 1 for all indexes i in given sequence

 // IF first and last characters are not same
 If (X[i] != X[j])  L(i, j) =  max{L(i + 1, j),L(i, j - 1)}

 // If there are only 2 characters and both are same
 Else if (j == i + 1) L(i, j) = 2

 // If there are more than two characters, and first and last
 // characters are same
 Else L(i, j) =  L(i + 1, j - 1) + 2
 2) Overlapping Subproblems
 Following is simple recursive implementation of the LPS problem.
 The implementation simply follows the recursive structure mentioned above.
 *
 */
public class LongestPalindromicSubsequence {

    public int longestPalindromicSubsequence(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp =  new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for (int l = 2; l <= len; l++) {
            for (int i = 0; i < len - l + 1; i++) {
                int j = i + l - 1;
                if (l == 2 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][len - 1];
    }

    public int calculateRecursive(char str[],int start,int len){
        if(len == 1){
            return 1;
        }
        if(len ==0){
            return 0;
        }
        if(str[start] == str[start+len-1]){
            return 2 + calculateRecursive(str,start+1,len-2);
        }else{
            return Math.max(calculateRecursive(str, start+1, len-1), calculateRecursive(str, start, len-1));
        }
    }


    public static void main(String args[]){
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String str = "agbdba";
        int r1 = lps.calculateRecursive(str.toCharArray(), 0, str.length());
        int r2 = lps.longestPalindromicSubsequence(str);
        System.out.print(r1 + " " + r2);
    }
}
