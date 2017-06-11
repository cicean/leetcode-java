package Google;

import datastructure.PrintList;

import java.util.ArrayList;
import java.util.List;

/**
 * followup1，找一个字符串中period的字符段，followup2，找到period次数最少的，例如abababab，ab出现了4次，abab出现了2次，返回2
 */

public class RepeatedSubstring {

    public List<String> findRepeatedSubSequences(String s) {
        List<String>  res = new ArrayList<>();
        int i = 0;
        int j = 1;
        while (i < j && j < s.length()) {
        	if (j - i <= s.length() - j) {
        		System.out.println("j = " + j + ", end = " + (2 * j - i) + ",String = " + s.substring(j, 2 * j - i));
        		if ( s.substring(i, j).equals(s.substring(j, 2 * j - i)) && !res.contains(s.substring(i, j))) res.add(s.substring(i, j));
        		j++;
        	} else {
        		i++;
        		j = i + 1;
        	}
        }
        return res;
    }
    
    public String findeLessRepeatedSubSequence(String s) {
    	String reString = "";
    	
    	if (s == null)  return reString;
    	
    	int repeat = Integer.MAX_VALUE;
    	int i = 0;
    	int j = 1;
    	while (i < j && j < s.length()) {
    		System.out.println("i = " + i + ", j = " + j + ",String = " + s.substring(i, j));
        	if (j - i <= s.length() - j) {
        		
        		int tmp = j;
        		int len = j - i;
        		int times = 0;
        		while (tmp <= s.length() - len && s.substring(i, j).equals(s.substring(tmp, tmp + len))) {
        			System.out.println("tmp = " + tmp + ", length = " + len + ",tmpString" + s.substring(tmp, tmp + len));
        			times++;
        			tmp += len;
        		}
        		
        		
        		if ( times > 0 && times < repeat ) {
        			repeat = times;
        			reString = s.substring(i, j);
        		}
        		
        		j++;
        	} else {
        		i++;
        		j = i + 1;
        	}
        }
    	
    	return reString;
    	
    }

   
    
    public static void main(String[] args) {
		RepeatedSubstring slt = new RepeatedSubstring();
		//slt.findRepeatedSubSequences("abababcabcabab");
		PrintList<String> res = new PrintList<>();
		res.printList(slt.findRepeatedSubSequences("abababcabcabab"));
		System.out.println(slt.findeLessRepeatedSubSequence("abababcabcabab"));
	}
    
    

}
