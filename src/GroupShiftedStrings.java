
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249	Group Shifted Strings 	22.1%	Easy
 * @author cicean
 * LeetCode: Group Shifted Strings
AUG 8 2015

Given a string, we can ��shift�� each of its letter to its successive letter, for example: ��abc�� -> ��bcd��. We can keep ��shifting�� which forms the sequence:

1
"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example,

given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], Return:

1
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list��s elements must follow the lexicographic order.
 */
public class GroupShiftedStrings {

	public class Solution {
		public List<List<String>> groupStrings(String[] strings) {
			List<List<String>> result = new ArrayList<List<String>>();
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (String str : strings) {
				int offset = str.charAt(0) - 'a';
				String key = "";
				for (int i = 0; i < str.length(); i++) {
					char c = (char) (str.charAt(i) - offset);
					if (c < 'a') {
						c += 26;
					}
					key += c;
				}
				if (!map.containsKey(key)) {
					List<String> list = new ArrayList<String>();
					map.put(key, list);
				}
				map.get(key).add(str);
			}
			for (String key : map.keySet()) {
				List<String> list = map.get(key);
				Collections.sort(list);
				result.add(list);
			}
			return result;
		}
	}

	public List<List<String>> groupString(String[] strings) {
		
		assert strings != null : "null array";
		
		List<List<String>> ret = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		
		for (String s :strings) {
			String code = getFeatureCode(s);
			List<String> val;
			if (!map.containsKey(code)) {
				val = new ArrayList<>();
			} else {
				val = map.get(code);
				
			}
			val.add(s);
			map.put(code,val);
		}
		
		for (String key : map.keySet()) {
			List<String> val = map.get(key);
			Collections.sort(val);
			ret.add(val);
		}
		
		return ret;
	}
	
	private String getFeatureCode(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append("#");
		for (int i = 1; i < s.length(); i++) {
			int tmp = ((s.charAt(i)-s.charAt(i - 1)) + 26)%26;
			sb.append(tmp).append("#");
		
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroupShiftedStrings slt = new GroupShiftedStrings();
		String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
		System.out.println(slt.groupString(strings));
	}

}
