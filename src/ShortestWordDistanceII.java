import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/*
 * 244	Shortest Word Distance II 	34.4%	Medium
 * LeetCode: Shortest Word Distance II
AUG 9 2015

This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,

Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.

Note:

You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 
 * The key idea is to store the indexes of each word using a hash map. 
 * Then the function shortest() is just to find the minimum difference between two sorted lists (see here for a method).
 */
public class ShortestWordDistanceII {
	
	private Map<String, List<Integer>> map = new HashMap<>();
	
	public void WordDistance(String[] words) {
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
	}
	
	public int shortest(String word1, String word2) {
		List<Integer> l1 = map.get(word1);
		List<Integer> l2 = map.get(word2);
		
		int min = Integer.MAX_VALUE;
		
		for (int a : l1) {
			for (int b : l2) {
				min = Math.min(Math.abs(b - a), min);
			}
		}
		
		return min;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShortestWordDistanceII slt = new ShortestWordDistanceII();
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		String word1 = "coding";
		String word2 = "practice";
		
		slt.WordDistance(words);
		System.out.println(slt.shortest(word1, word2));
	}

}
