import java.util.Arrays;
import java.util.HashMap;

/*
 * 242	Valid Anagram	35.4%	Easy
 * Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Hide Tags Hash Table Sort
Hide Similar Problems (M) Anagrams

 * 
 * 易位构词游戏的规则可以参考相关的 维基百科页面 ：易位构词是一类文字游戏。它的规则是将组成一个词或短句的字母重新排列顺序，
 * 原文中所有字母的每次出现都被使用一次，这样构造出另外一些新的词或短句。
 * 现在我们要判断的就是给出两个字符串s和t，判断字符串t能否由s通过易位构词产生。
 * 
 * solution1
 * first sort the two String, and compare each index.
 * Time Complexity: O(nlogn)
 * Space Complexity: O(1)
 * 即循环考察字符串s中的各字符，将字符串t中找到的第一个一样的字符换成字符‘ ‘。如果在替换过程中，
 * 发现t中已经没有合适的字符进行替换，则返回false。如果直到s中的字符循环完都没有返回false，
 * 可认为s与t中所用到的每个字母的数量都是一致的。
 * 如果用Arrays.sort函数对数组进行排序，就可以比自己用两个for循环要快很多。
 * 
 * solution2
 * Using HashMap to store the number of each character in the String
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * solution3,
 * 
 * 最后还有一种方法，思路是我在Discuss中看到的，即通过一个长度为26的整形数组，对应英文中的26个字母a-z。
 * 从前向后循环字符串s和t，s中出现某一字母则在该字母在数组中对应的位置上加1，t中出现则减1。
 * 如果在s和t中所有字符都循环完毕后，整型数组中的所有元素都为0，则可认为s可由易位构词生成t。
 * 
 */
public class ValidAnagram {

	public boolean isAnagram1(String s, String t) {  
		
	    if (s == null || t == null || s.length() != t.length()) {
	            
	    	return false;
	        
	    }

		
        char[] sArr = s.toCharArray();  
        char[] tArr = t.toCharArray();  
          
        Arrays.sort(sArr);  
        Arrays.sort(tArr);  
          
        return String.valueOf(sArr).equals(String.valueOf(tArr));  
    }  
	
	public boolean isAnagram2(String s, String t) {      
		if (s.length() != t.length()) {       
			return false;      
		}      
		
		char[] c1 = s.toCharArray();      
		char[] c2 = t.toCharArray();      
		
		HashMap<Character,Integer> map1 = new HashMap<Character,Integer>();      
		HashMap<Character,Integer> map2 = new HashMap<Character,Integer>();      
		
		for(int i=0;i < s.length();i++) {       
			if (!map1.containsKey(c1[i])) {        
				map1.put(c1[i], 1);       
			} else {        
				map1.put(c1[i], map1.get(c1[i])+1);       
			  }       
			
			if (!map2.containsKey(c2[i])) {        
				map2.put(c2[i], 1);       
				} else {        
					map2.put(c2[i], map2.get(c2[i])+1);       
				  }      
			}      
		
			if (map1.entrySet().containsAll(map2.entrySet()) && map2.entrySet().containsAll(map1.entrySet())) {       
				return true;      
		}        
			
		return false;     
			
	}
	
	/**
     * 判断两个字符串是否为易位构词
     * @param s 字符串1
     * @param t 字符串2
     * @return
     */
	
	public boolean isAnagram3(String s, String t) {  
		if (s == null || t == null || s.length() != t.length()) {
            
	    	return false;
	        
	    }
		
		 //字母统计
        int[] letterCounter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            letterCounter[s.charAt(i) - 'a']++;
            letterCounter[t.charAt(i) - 'a']--;
        }
        
      //如果s的各个字母数量与t一致，那么letterCounter数组的元素应该全部为0
        for (int count : letterCounter) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
