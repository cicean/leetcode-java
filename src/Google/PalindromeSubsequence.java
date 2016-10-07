package Google;

/**
 * Created by cicean on 9/27/2016.
 */
public class PalindromeSubsequence {

    public int maximumPalindromeSubsequence(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int max_number = 0;
        //initail the initialize the statues from length of string s
        // from length = 1
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
            max_number++;
        }

        for (int j = 1; j < s.length(); j++) {
            // this loop is check length = j - 0 + 1 this char from 0 - s.length() -1
            // how many pail it will have;
            for (int i = 0; i < s.length(); i++) {
                for (int k = 0; k < s.length(); k++) {
                    if (i != k)
                }
            }
        }
    }
}
