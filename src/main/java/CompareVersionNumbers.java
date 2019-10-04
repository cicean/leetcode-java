/*
 165	Compare Version Numbers	15.2%	Easy
 Problem:    Compare Version Numbers
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/compare-version-numbers/
 Notes:
 Compare two version numbers version1 and version1.
 If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 You may assume that the version strings are non-empty and contain only digits and the . character.
 The . character does not represent a decimal point and is used to separate number sequences.
 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 Here is an example of version numbers ordering:
 0.1 < 1.1 < 1.2 < 13.37
 Solution: ...
 */

public class CompareVersionNumbers {

	public int compareVersion(String version1, String version2) {
        long a = 0, b =0;
        int v1len = version1.length(), v2len = version2.length();
        int i = 0, j = 0;
        while (i < v1len || j < v2len) {
        	a = 0; b =0;
        	while (i < v1len && version1.charAt(i) != '.') {
        		a = a * 10 + version1.charAt(i) - '0';
        		++i;
        	}
        	++i;
        	while (j < v2len && version2.charAt(j) != '.') {
        		b = b * 10 + version2.charAt(j) - '0';
        		++j;
        	}
        	++j;
        	if (a > b) return 1;
        	if (a < b) return -1;
        }
    	return 0;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompareVersionNumbers slt = new CompareVersionNumbers();
		 String[] version1 = {null,null,"1","1","1","1.0","1","1.1","1.2","1.2.2","0"};
	        String[] version2 = {null,"1",null,"1","2","1","1.0","1.1","1.3","1.2","1"};
	        int[] res = {0,-1,1,0,-1,0,0,0,-1,1,-1};
	        for (int i = 0; i < version1.length; i++) {
	            System.out.print(slt.compareVersion(version1[i], version2[i]));
	            System.out.print(" ");
	        }
	        System.out.println();
	        for (int i = 0; i < res.length; i++) {
	            System.out.print(res[i]);
	            System.out.print(" ");
	        }
	        System.out.println();
	    }
	

}
