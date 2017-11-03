import java.util.*;
import java.util.Map.Entry;

/*
 49	Group Anagrams	24.4%	Medium
 Problem:    Anagrams
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/anagrams/
 Notes:
 Given an array of strings, return all groups of strings that are anagrams.
 Note: All inputs will be in lower-case.
 Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
 Solution: Sort the string to see if they're anagrams.
 
 */

public class GroupAnagrams {

	public List<String> anagrams(String[] strs) {
        ArrayList<String> res = new ArrayList<String>();
        HashMap<String, ArrayList<String>> group = new HashMap<String, ArrayList<String>>();
        if (strs.length == 0) return res;
        for (int i = 0; i < strs.length; ++i) {
            char[] tmp = strs[i].toCharArray();
            Arrays.sort(tmp);
            String s = String.valueOf(tmp);
            if(group.containsKey(s)) 
                (group.get(s)).add(strs[i]);    
            else {
                ArrayList<String> t = new ArrayList<String>();
                t.add(strs[i]);
                group.put(s,t); 
            }
        }
        Iterator<Entry<String, ArrayList<String>>> iter = group.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            ArrayList<String> val = (ArrayList<String>) entry.getValue();
            if (val.size() > 1) res.addAll(val);
        }
        return res;
    }
	
	 public List<List<String>> groupAnagrams(String[] strs) {
	        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
		 	//out put with order
	        Arrays.sort(strs);
	        for (String s : strs) {
	            char[] ca = s.toCharArray();
	            Arrays.sort(ca);
	            String keyStr = String.valueOf(ca);
	            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
	            map.get(keyStr).add(s);
	        }
	        return new ArrayList<List<String>>(map.values());
	    }

	/**
	 * ask the interview whether need the order of the group by al increasing
	 * ʱ�� O(NKlogK) �ռ� O(N)
	 * k = word length
	 * �ж��������Ƿ��Ǳ��δʣ���򵥵ķ����ǽ������ʰ���ĸ���򣬿�����Ƿ���ͬ��
	 * ����������Ҫ������ͬΪһ�����δʴʸ��Ĵʹ鵽һ�����ķ��������ù�ϣ��
	 * ����������ǽ�Ϲ�ϣ����������ǽ�ÿ��������󣬸��������ֵ��
	 * �ҵ���ϣ������Ӧ���б�����ӽ�ȥ��Ϊ��������Ŀ��ĸ˳���Ҫ��
	 * �������֮ǰ��Ҫ��ÿ���б����ڲ��Ĵ�����һ�¡�
	 * ����ֱ����Java��Collections.sort()���API��
	 */
	public class Solution {
		public List<List<String>> groupAnagrams(String[] strs) {
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for(String str : strs){
				// �����ʰ���ĸ����
				char[] carr = str.toCharArray();
				Arrays.sort(carr);
				String key = new String(carr);
				List<String> list = map.get(key);
				if(list == null){
					list = new ArrayList<String>();
				}
				list.add(str);
				map.put(key, list);
			}
			List<List<String>> res = new ArrayList<List<String>>();
			// ���б���������
			for(String key : map.keySet()){
				List<String> curr = map.get(key);
				Collections.sort(curr);
				res.add(curr);
			}
			return res;
		}
	}
	 
	 
	 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroupAnagrams slt = new GroupAnagrams();
		String[] strs = {"tea","and","ate","eat","den"};
		List<String> res = slt.anagrams(strs);
		for (String str : res) {
			System.out.println(str);
		}
		System.out.println(res);
		
		List<List<String>> result = slt.groupAnagrams(strs);
		System.out.println(result);
	}
	
	
}
