import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 	243	Shortest Word Distance 	38.1%	Easy
 * Problem Description:

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = "coding", word2 = "practice", return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * 
 */
public class ShortestWordDistance {

	public int shortestDistance(String[] words, String word1, String word2) {
			Map<String, List<Integer>> map = new HashMap<>();
			
			for (int i = 0; i < words.length; i++) {
				String s = words[i];
				List<Integer> list;
				if (map.containsKey(s)) {
					list = map.get(s);
				} else {
					list = new ArrayList<>();
				}
				list.add(i);
				map.put(s, list);
			}
			List<Integer> l1 = map.get(word1);
			List<Integer> l2 = map.get(word2);
			
			int min = Integer.MAX_VALUE;
			
			for (int a : l1) {
				for (int b : l2) {
					min = Math.min(Math.abs(b-a), min);
				}
			}
			
			return min;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShortestWordDistance slt = new ShortestWordDistance();
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		String word1 = "coding";
		String word2 = "practice";
		
		System.out.println(slt.shortestDistance(words, word1, word2));
		
	}

}
