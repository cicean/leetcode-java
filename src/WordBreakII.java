import java.util.*;

/*
 140	Word Break II	17.7%	Hard
 Problem:    Word Break II
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/word-break-ii/
 Notes:
 Given a string s and a dictionary of words dict, add spaces in s to 
 construct a sentence where each word is a valid dictionary word.
 Return all such possible sentences.
 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].
 A solution is ["cats and dog", "cat sand dog"].
 Solution: check before constructing the sentences.
*/

public class WordBreakII {
	
	/**
	 * ���Ҫ����������ϵĻ������ǿ��Կ������ַ�����һ����DP��ʱ�临�ӶȽϵͣ����ǱȽϺ��ڴ棬��ζ�Ŷ���ÿ��Index, ���ǿ��ܶ�Ҫ�����Ӧ���н⡣��һ����DFS���ռ临�ӶȽϵͣ�����ʱ��ʱ�临�ӶȽϸߣ����ǿ��Բ���memorization�Ż�ʱ�临�Ӷȡ�

ע��DP������Ϊ�˹�OJ��һ��edge case, ��Word Break�Ľⷨ�ȼ�������ַ����Ƿ��ܹ��ֽ⣬���ܵĻ���û�б�Ҫ�������ˡ�

���Ӷ�


	 * @param s
	 * @param dict
	 * @return
	 */
	
	//DP: time: O(n^2*k), space: O(nk), ����k��ʾƽ��ÿ�����ȶ�Ӧ��ĸ���
	public class Solution {
	    public List<String> wordBreak(String s, Set<String> wordDict) {
	        // �ж��Ƿ��ܹ��ֽ�
	        if (!helper(s, wordDict)) {
	            return new ArrayList<String>();
	        }
	        
	        // ��¼�ַ���s.substring(0, i)��Ӧ�Ľ�
	        HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
	        map.put(0, new ArrayList<>());
	        map.get(0).add("");
	        
	        for (int i = 1; i <= s.length(); i++) {
	            for (int j = 0; j < i; j++) {
	                if (map.containsKey(j) && wordDict.contains(s.substring(j, i))) {
	                    if (!map.containsKey(i))
	                        map.put(i, new ArrayList<>());
	                    for (String str : map.get(j)) {
	                        map.get(i).add(str + (str.equals("") ? "" : " ") + s.substring(j, i));
	                    }
	                }
	            }
	        }
	        
	        return map.get(s.length());
	    }
	    
	    private boolean helper(String s, Set<String> wordDict) {
	        boolean dp[] = new boolean[s.length() + 1];
	        dp[0] = true;
	        for (int i = 1; i <= s.length(); i++) {
	            for (int j = 0; j < i; j++) {
	                if (dp[j] && wordDict.contains(s.substring(j, i))) {
	                    dp[i] = true;
	                }
	            }
	        }
	        return dp[s.length()];
	    }
	}
	
	//DFS: time: O(2^n), space: O(n)
	public List<String> wordBreak(String s, Set<String> dict) {
        List<String> res = new ArrayList<String>();
        int n = s.length();
        boolean[] canBreak = new boolean[n+1];
        canBreak[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (dict.contains(s.substring(i,j+1)) && canBreak[j+1]) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        if (canBreak[0] == false) return res;
        wordBreakRe(s, dict, "", 0, res);
        return res;
    }
    public void wordBreakRe(String s, Set<String> dict, String path, int start, List<String> res) {
        if (start == s.length()) {
            res.add(path);
            return;
        }
        if (path.length() != 0) path = path + " ";
        for (int i = start; i < s.length(); ++i) {
            String word = s.substring(start, i + 1);
            if (dict.contains(word) == false) continue;
            wordBreakRe(s, dict, path + word, i + 1, res);
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordBreakII slt = new WordBreakII();
		String str ="catsanddog";
		String[] dicts = {"cat", "cats", "and", "sand", "dog"};
		Set<String> dict = new HashSet<String>();
		for(String s: dicts){
			dict.add(s);
		}
		
		List<String> res = slt.wordBreak(str, dict);
		System.out.print(res);
	}

}
