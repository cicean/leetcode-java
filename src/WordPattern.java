import java.util.HashMap;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a
 * letter in pattern and a non-empty word in str.
 * 
 * Examples: pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false. pattern =
 * "aaaa", str = "dog cat cat dog" should return false. pattern = "abba", str =
 * "dog dog dog dog" should return false. Notes: You may assume pattern contains
 * only lowercase letters, and str contains lowercase letters separated by a
 * single space.
 * 
 * Credits: Special thanks to @minglotus6 for adding this problem and creating
 * all test cases.
 * 
 * Hide Tags Hash Table Hide Similar Problems (E) Isomorphic Strings (H) Word
 * Pattern II
 * 
 * @author cicean
 *
 */
public class WordPattern {

	public boolean wordPattern(String pattern, String str) {
		String[] strs = str.split(" ");
		HashMap<String, Integer> mapstr = new HashMap<String, Integer>();
		HashMap<Character, Integer> mappattern = new HashMap<Character, Integer>();
		if (pattern.length() != str.length()) {
			return false;
		}
		for (int i = 0; i < pattern.length(); i++) {
			char tmppatternc = pattern.charAt(i);
			String tmpstr = strs[i];
			if((mappattern.containsKey(mappattern) ^ mapstr.containsKey(tmpstr)) || (mappattern.containsKey(mappattern) && mapstr.containsKey(tmpstr) && mappattern.get(mappattern) != mapstr.get(tmpstr))) {
				return false;
			}
			mappattern.put(tmppatternc, i);
			mapstr.put(tmpstr, i);
			
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
