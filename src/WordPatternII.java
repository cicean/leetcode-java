import java.util.HashMap;



/**
 * [LeetCode] Word Pattern II

Problem Description:

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:

pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false. 
Notes:
You may assume both pattern and str contains only lowercase letters.
 * @author cicean
 *
 */
public class WordPatternII {
	public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> mapPtoS = new HashMap<Character, String>();
        HashMap<String, Character> mapStoP = new HashMap<String, Character>();
        
        return dfs(pattern, str, 0,0, mapPtoS, mapStoP);
    }
	
	public boolean dfs(String pattern, String str, int idxPat, int indxStr, 
			HashMap<Character, String> mapPtoS, HashMap<String, Character> mapStoP) {
		
		if (idxPat == pattern.length()) {
			return indxStr == str.length();
		}
		
		char p = pattern.charAt(idxPat);
		if (mapPtoS.containsKey(p)){
			String s = mapPtoS.get(p);
			int len = indxStr + s.length();
			if (s.equals(str.substring(indxStr, len))&& len <= str.length()) {
				return dfs(pattern, str, idxPat + 1, len, mapPtoS, mapStoP);
				
			} else {
				return false;
			}
			
			
		}
		
		for (int i = indxStr + 1; i <= str.length(); i++) {
			String string = str.substring(indxStr, i);
			if (!mapStoP.containsKey(string)) {
				mapPtoS.put(p, string);
				mapStoP.put(string, p);
				
				if (dfs(pattern, string, idxPat + 1, indxStr + string.length(), mapPtoS, mapStoP)) {
					return true;
				}
				
				mapPtoS.remove(p);
				mapStoP.remove(string);
				
			} 
				
		}
		
		return false;
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pattern = "abab";
		String string = "redblueredblue";
		WordPatternII slt = new WordPatternII();
		System.out.println(slt.wordPatternMatch(pattern, string));
		
	}

}
