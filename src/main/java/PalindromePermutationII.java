import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 	267	Palindrome Permutation II 	22.1%	Medium
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form. 

For example: 

Given s = "aabb", return ["abba", "baab"]. 

Given s = "abc", return []. 

[����] 
�Ȱ���Palindrome Permutation �ķ����ж������ַ��� s �ܷ����г�Ϊһ�����Ĵ���ͨ����鿪ʼ������̣�������˼·�� 
˼·1������leetcode����ʾ�����ǽ��蹹����ĵ�ǰ�벿�֡�������ǰ���жϹ����е�map��û���ǰ�벿�����е��ַ�����ͬ�ַ�������һ��Ȼ����Permutation IIһ���˼·���ǰ�벿�����е���������������ϻ��ĵ�ǰ�󲿷ּ��������� 
˼·2�����м������˵ݹ鹹����Ĵ��� 

[ref] 
https://leetcode.com/discuss/53638/0ms-easy-c-solution 
https://leetcode.com/discuss/53613/my-accepted-java-solution 

 * @author cicean
 *
 */
public class PalindromePermutationII {
	
	// Method 1  
    public List<String> generatePalindromes(String s) {  
        List<String> ret = new ArrayList<String>();  
        if (s == null || s.length() == 0) return ret;  
        if (s.length() == 1) {ret.add(s); return ret;}  
        int[] map = new int[256];  
        for (int i = 0; i < s.length(); i++)  
            map[s.charAt(i)]++;  
        int oddCount = 0;  
        int oddIdx = -1;  
        StringBuilder half = new StringBuilder();  
        for (int i = 0; i < 256; i++) {  
            if ((map[i] & 1) == 1) {  
                oddIdx = i;  
                oddCount++;  
                if (oddCount == 2) return ret;  
            }  
            int halfCount = map[i] / 2;  
            for (int j = 0; j < halfCount; j++)  
                half.append((char)i);  
        }  
        List<String> halfPermutation = new ArrayList<String>();  
        getPermutation(half.toString().toCharArray(), new boolean[half.length()], new StringBuilder(), halfPermutation);  
        for (String curr : halfPermutation) {  
            StringBuilder curr2 = new StringBuilder(curr);  
            if (oddIdx != -1)  
                curr += (char)oddIdx;  
            ret.add(curr + curr2.reverse().toString());   
        }  
        return ret;  
    }  
      
    public void getPermutation(char[] chars, boolean[] used, StringBuilder item, List<String> result) {  
        if (item.length() == chars.length) {  
            result.add(item.toString());  
        } else {  
            for (int i = 0; i < chars.length; i++) {  
                if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]))  
                    continue;  
                item.append(chars[i]);  
                used[i] = true;  
                getPermutation(chars, used, item, result);  
                used[i] = false;  
                item.deleteCharAt(item.length() - 1);  
            }  
        }  
    }  
    // Method 2  
    public List<String> generatePalindromes2(String s) {  
        List<String> ret = new ArrayList<String>();  
        int[] map = new int[256];  
        for (int i = 0; i < s.length(); i++)  
            map[s.charAt(i)]++;  
        int oddCount = 0;  
        int oddIdx = -1;  
        for (int i = 0; i < 256; i++) {  
            if ((map[i] & 1) == 1) {  
                oddIdx = i;  
                oddCount++;  
                if (oddCount == 2) return ret;  
            }  
        }  
        String curr = "";  
        if (oddIdx != -1) {  
            curr += (char)oddIdx;  
            map[oddIdx]--;  
        }  
        dfs(curr, map, s.length(), ret);  
        return ret;  
    }  
    public void dfs(String curr, int[] map, int n, List<String> ret) {  
        if (curr.length() < n) {  
            for (int i = 0; i < map.length; i++) {  
                if (map[i] > 0) {  
                    curr = (char)i + curr + (char)i;  
                    map[i] -= 2;  
                    dfs(curr, map, n, ret);  
                    curr = curr.substring(1, curr.length() - 1);  
                    map[i] += 2;  
                }  
            }  
        } else {  
            ret.add(curr);  
        }  
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
