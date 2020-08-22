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
	 * ���͵�DP�⣬dp[i]��ʾǰi���ַ��Ƿ�ɷֽ�ɵ��ʣ���ô

dp[i] = dp[j] && dict.contains(s.substring(j, i)) (j = 0, 1, ..., i-1, ֻҪ����һ�����㼴��);
����ⲻ�Ƽ���DFS��ʱ�临�ӶȻ�ܸߣ�worst case�ﵽO(2^n)��
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

	//BFS
	private Set<Integer> set = new HashSet<Integer>();
	public boolean wordBreak2(String s, Set<String> wordDict) {
		if(s.length() == 0) return true;
		if(wordDict.contains(s)) return true;
		for(int i = 1; i < s.length(); i++){
			if(wordDict.contains(s.substring(0,i)) && !set.contains(i)){
				if(wordBreak(s.substring(i,s.length()),wordDict))
					return true;
				else set.add(i);
			}
		}
		return false;
	}


	/**
	 * Recursive and slow version of breaking word problem.
	 * If no words can be formed it returns null
	 */
	public String breakWord(char[] str,int low,Set<String> dictionary){
		StringBuffer buff = new StringBuffer();
		for(int i= low; i < str.length; i++){
			buff.append(str[i]);
			if(dictionary.contains(buff.toString())){
				String result = breakWord(str, i+1, dictionary);
				if(result != null){
					return buff.toString() + " " + result;
				}
			}
		}
		if(dictionary.contains(buff.toString())){
			return buff.toString();
		}
		return null;
	}

	/**
	 * Dynamic programming version for breaking word problem.
	 * It returns null string if string cannot be broken into multipe words
	 * such that each word is in dictionary.
	 * Gives preference to longer words over splits
	 * e.g peanutbutter with dict{pea nut butter peanut} it would result in
	 * peanut butter instead of pea nut butter.
	 */
	public String breakWordDP(String word, Set<String> dict){
		int T[][] = new int[word.length()][word.length()];

		for(int i=0; i < T.length; i++){
			for(int j=0; j < T[i].length ; j++){
				T[i][j] = -1; //-1 indicates string between i to j cannot be split
			}
		}

		//fill up the matrix in bottom up manner
		for(int l = 1; l <= word.length(); l++){
			for(int i=0; i < word.length() -l + 1 ; i++){
				int j = i + l-1;
				String str = word.substring(i,j+1);
				//if string between i to j is in dictionary T[i][j] is true
				if(dict.contains(str)){
					T[i][j] = i;
					continue;
				}
				//find a k between i+1 to j such that T[i][k-1] && T[k][j] are both true
				for(int k=i+1; k <= j; k++){
					if(T[i][k-1] != -1 && T[k][j] != -1){
						T[i][j] = k;
						break;
					}
				}
			}
		}
		if(T[0][word.length()-1] == -1){
			return null;
		}

		//create space separate word from string is possible
		StringBuffer buffer = new StringBuffer();
		int i = 0; int j = word.length() -1;
		while(i < j){
			int k = T[i][j];
			if(i == k){
				buffer.append(word.substring(i, j+1));
				break;
			}
			buffer.append(word.substring(i,k) + " ");
			i = k;
		}

		return buffer.toString();
	}

	/**
	 * Prints all the words possible instead of just one combination.
	 * Reference
	 * https://leetcode.com/problems/word-break-ii/
	 */
	public List<String> wordBreakTopDown(String s, Set<String> wordDict) {
		Map<Integer, List<String>> dp = new HashMap<>();
		int max = 0;
		for (String s1 : wordDict) {
			max = Math.max(max, s1.length());
		}
		return wordBreakUtil(s, wordDict, dp, 0, max);
	}

	private List<String> wordBreakUtil(String s, Set<String> dict, Map<Integer, List<String>> dp, int start, int max) {
		if (start == s.length()) {
			return Collections.singletonList("");
		}

		if (dp.containsKey(start)) {
			return dp.get(start);
		}

		List<String> words = new ArrayList<>();
		for (int i = start; i < start + max && i < s.length(); i++) {
			String newWord = s.substring(start, i + 1);
			if (!dict.contains(newWord)) {
				continue;
			}
			List<String> result = wordBreakUtil(s, dict, dp, i + 1, max);
			for (String word : result) {
				String extraSpace = word.length() == 0 ? "" : " ";
				words.add(newWord + extraSpace + word);
			}
		}
		dp.put(start, words);
		return words;
	}

	/**
	 * Check if any one solution exists.
	 * https://leetcode.com/problems/word-break/
	 */
	public boolean wordBreakTopDownOneSolution(String s, Set<String> wordDict) {
		Map<Integer, Boolean> dp = new HashMap<>();
		int max = 0;
		for (String s1 : wordDict) {
			max = Math.max(max, s1.length());
		}
		return wordBreakTopDownOneSolutionUtil(s, wordDict, 0, max, dp);

	}

	private boolean wordBreakTopDownOneSolutionUtil(String s, Set<String> dict, int start, int max, Map<Integer, Boolean> dp) {
		if (start == s.length()) {
			return true;
		}

		if (dp.containsKey(start)) {
			return dp.get(start);
		}

		for (int i = start; i < start + max && i < s.length(); i++) {
			String newWord = s.substring(start, i + 1);
			if (!dict.contains(newWord)) {
				continue;
			}
			if (wordBreakTopDownOneSolutionUtil(s, dict, i + 1, max, dp)) {
				dp.put(start, true);
				return true;
			}
		}
		dp.put(start, false);
		return false;
	}

	public boolean wordBreakBottomUp(String s, List<String> wordList) {
		boolean[] T = new boolean[s.length() + 1];
		Set<String> set = new HashSet<>();
		for (String word : wordList) {
			set.add(word);
		}
		T[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if(T[j] && set.contains(s.substring(j, i))) {
					T[i] = true;
					break;
				}
			}
		}
		return T[s.length()];
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
