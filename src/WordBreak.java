import java.util.HashSet;
import java.util.Set;

/*
 139	Word Break	23.0%	Medium
 Problem:    Word Break
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/word-break/
 Notes:
 Given a string s and a dictionary of words dict, determine if s can be segmented into 
 a space-separated sequence of one or more dictionary words.
 For example, given
 s = "leetcode",
 dict = ["leet", "code"].
 Return true because "leetcode" can be segmented as "leet code".
 Solution: dp.
*/

public class WordBreak {
	
	/**
	 * 典型的DP题，dp[i]表示前i个字符是否可分解成单词，那么

dp[i] = dp[j] && dict.contains(s.substring(j, i)) (j = 0, 1, ..., i-1, 只要任意一个满足即可);
这道题不推荐用DFS，时间复杂度会很高，worst case达到O(2^n)。
	 * @param s
	 * @param dict
	 * @return
	 */
	//O(n^2), space: O(n)
	public boolean wordBreak(String s, Set<String> dict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (dict.contains(s.substring(i,j+1)) && dp[j+1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordBreak slt = new WordBreak();
		String str ="leetcode";
		String[] dicts = {"leet", "code"};
		Set<String> dict = new HashSet<String>();
		for(String s: dicts){
			dict.add(s);
		}
		System.out.print(slt.wordBreak(str,dict));
	}

}
