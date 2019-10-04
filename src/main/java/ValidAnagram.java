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
 * ��λ������Ϸ�Ĺ�����Բο���ص� ά���ٿ�ҳ�� ����λ������һ��������Ϸ�����Ĺ����ǽ����һ���ʻ�̾����ĸ��������˳��
 * ԭ����������ĸ��ÿ�γ��ֶ���ʹ��һ�Σ��������������һЩ�µĴʻ�̾䡣
 * ��������Ҫ�жϵľ��Ǹ��������ַ���s��t���ж��ַ���t�ܷ���sͨ����λ���ʲ�����
 * 
 * solution1
 * first sort the two String, and compare each index.
 * Time Complexity: O(nlogn)
 * Space Complexity: O(1)
 * ��ѭ�������ַ���s�еĸ��ַ������ַ���t���ҵ��ĵ�һ��һ�����ַ������ַ��� ����������滻�����У�
 * ����t���Ѿ�û�к��ʵ��ַ������滻���򷵻�false�����ֱ��s�е��ַ�ѭ���궼û�з���false��
 * ����Ϊs��t�����õ���ÿ����ĸ����������һ�µġ�
 * �����Arrays.sort����������������򣬾Ϳ��Ա��Լ�������forѭ��Ҫ��ܶࡣ
 * 
 * solution2
 * Using HashMap to store the number of each character in the String
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * solution3,
 * 
 * �����һ�ַ�����˼·������Discuss�п����ģ���ͨ��һ������Ϊ26���������飬��ӦӢ���е�26����ĸa-z��
 * ��ǰ���ѭ���ַ���s��t��s�г���ĳһ��ĸ���ڸ���ĸ�������ж�Ӧ��λ���ϼ�1��t�г������1��
 * �����s��t�������ַ���ѭ����Ϻ����������е�����Ԫ�ض�Ϊ0�������Ϊs������λ��������t��
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
     * �ж������ַ����Ƿ�Ϊ��λ����
     * @param s �ַ���1
     * @param t �ַ���2
     * @return
     */
	
	public boolean isAnagram3(String s, String t) {  
		if (s == null || t == null || s.length() != t.length()) {
            
	    	return false;
	        
	    }
		
		 //��ĸͳ��
        int[] letterCounter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            letterCounter[s.charAt(i) - 'a']++;
            letterCounter[t.charAt(i) - 'a']--;
        }
        
      //���s�ĸ�����ĸ������tһ�£���ôletterCounter�����Ԫ��Ӧ��ȫ��Ϊ0
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
