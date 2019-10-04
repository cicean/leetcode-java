import java.util.*;

/**
 * 205	Isomorphic Strings	24.2%	Easy
 *
 * 0.Problem:
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while 
 * preserving the order of characters. No two characters may map to the same 
 * character but a character may map to itself.
 * 
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * Note:
 * You may assume both s and t have the same length.
 * 
 * 1.Refer.:
 * 1.1 最直接的方法就是见两个HashMap维护住字符的对应关系，O(n)
 *     使用两个哈希表sourceMap和targetMap维护两个字符串中字符的映射关系。
       sourceMap[ t[x] ] = s[x]
	   targetMap[ s[x] ] = t[x]
	           当出现映射不一致的情形时，返回False
	           否则返回True
* 2.1  判断给定字符串是否是同构的。规定同构的字符保证s1中的每个字符都存在唯一的映射到s2对应的字符。并且这种映射要求不能一对多，也不能多对一。
*      如 ab aa . a映射给a  b就不能再映射给a了。
 */


public class IsomorphicStrings {

	public  boolean isIsomorphic_1(String s, String t) {
	    if (null == s && null == t) return true;
	    if (null == s || null == t) return false; 
	    if (s.length() != t.length()) return false;
	    int length = s.length();
	    if (0 == length) return true;  
	    Map<Character, Character> maptables = new HashMap<Character, Character>();
	    Map<Character, Character> norepeate = new HashMap<Character, Character>();
	    for (int i = 0; i < length; i++) {
	      char sc = s.charAt(i);
	      char tc = t.charAt(i);
	      Character val = maptables.get(sc);
	      if (val == null) {
	        if (norepeate.get(tc) == null) { 
	          maptables.put(sc, tc);
	          norepeate.put(tc, sc);
	        } else {
	          return false;
	        }
	      } else if (val.charValue() != tc) {
	        return false;
	      }
	    }
	    return true;
	  }
	
	public  boolean isIsomorphic_2(String s, String t) {
        char[] t1 = s.toCharArray();
        char[] t2 = t.toCharArray();
        Map<Character,Character> map = new HashMap<Character,Character>();
 
        for(int i=0;i<t1.length;i++){
        	if(map.containsKey(t1[i])){ //when t1[i] already has map
        		char temp = map.get(t1[i]);
        		if(temp == t2[i])
        			t1[i] = temp;
        		else return false;
        	}else if(map.containsValue(t2[i])){ //make sure there no other values map to t2[i]
        		return false;
        	}else
        		map.put(t1[i], t2[i]);
        }
        return true;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IsomorphicStrings slt = new IsomorphicStrings();
        System.out.println(slt.isIsomorphic_1("", ""));
        System.out.println(slt.isIsomorphic_1("foo", "app"));
        System.out.println(slt.isIsomorphic_1("foo", "ppp"));
        System.out.println(slt.isIsomorphic_1("ofo", "app"));
        System.out.println(slt.isIsomorphic_1("bar", "foo"));
        System.out.println(slt.isIsomorphic_1("turtle", "tletur"));
        System.out.println(slt.isIsomorphic_1("tletur", "turtle"));
        System.out.println(slt.isIsomorphic_1("turtle", "tletur"));
        System.out.println(slt.isIsomorphic_1("tletur", "turtle"));
	}

}
