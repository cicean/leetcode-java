import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,

Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = "makes", word2 = "coding", return 1. Given word1 = "makes", word2 = "makes", return 3.

Note:

You may assume word1 and word2 are both in the list.
 * 
 */
public class ShortestWordDistanceIII {

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

	public int shortestWordDistance_III(String[] words, String word1, String word2) {
		//find index of word1
		//start from index-1 to left && index + 1 to right search for word2
		//find word2 --> label minDist
		//find next index of word1
		//search left and right in range [1, minDist]

		int minDist=Integer.MAX_VALUE;
		for (int i=0;i<words.length;i++){
			if (words[i].equals(word1)){
				for (int j=1;j<minDist;j++){
					int left=i-j;
					int right=i+j;
					if (left>=0 && left<words.length && words[left].equals(word2)){
						minDist=j;
						break;

					}
					if (right>=0 && right<words.length && words[right].equals(word2)){
						minDist=j;
						break;
					}
				}
			}
		}
		return minDist;
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
