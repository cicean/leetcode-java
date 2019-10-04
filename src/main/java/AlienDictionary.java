import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 269 Alien Dictionary 15.7% Hard There is a new alien language which uses the
 * latin alphabet. However, the order among letters are unknown to you. You
 * receive a list of words from the dictionary, wherewords are sorted
 * lexicographically by the rules of this new language. Derive the order of
 * letters in this language.
 * 
 * For example, Given the following words in dictionary,
 * 
 * [ "wrt", "wrf", "er", "ett", "rftt" ] The correct order is: "wertf".
 * 
 * Note:
 * 
 * You may assume all letters are in lowercase. If the order is invalid, return
 * an empty string. There may be multiple valid order of letters, return any one
 * of them is fine.����
 * 
 * @author cicean [����]
 *         �������빹���һ������ͼ��ͼ�Ľڵ����ַ�����ָʾ�ַ�˳�򡣿����������ַ�����ÿ���ַ�������ȫͼ�����赱ǰ������ַ���c1,
 *         c2��c1��ǰ�浥��ĳλ�õ��ַ���c2�Ǻ��浥��ͬλ�ô����ַ�������c1��c2���ȣ�����ͼ�м���һ��c1ָ��c2�ıߡ�
 *         Ȼ������������򣬲���¼�����������α��������ַ�
 *         ��������������ַ���ͼ�нڵ���һ��˵����ͼ��Ȧ��Ҳ���������ֵ�Ϸ��������������ý����Ϊ�ֵ����
 */
public class AlienDictionary {
	/**
	 * 
	 * @param words
	 * @return
	 */
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0)
			return "";
		if (words.length == 1)
			return words[0];
		Map<Character, Set<Character>> graph = buildGraph(words);
		Map<Character, Integer> indegree = computeIndegree(graph);
		StringBuilder order = new StringBuilder();
		LinkedList<Character> queue = new LinkedList<Character>();
		for (Character c : indegree.keySet()) {
			if (indegree.get(c) == 0)
				queue.offer(c);
		}
		while (!queue.isEmpty()) {
			char c = queue.poll();
			order.append(c);
			for (Character adj : graph.get(c)) {
				if (indegree.get(adj) - 1 == 0)
					queue.offer(adj);
				else
					indegree.put(adj, indegree.get(adj) - 1);
			}
		}
		return order.length() == indegree.size() ? order.toString() : "";
	}

	public Map<Character, Set<Character>> buildGraph(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
		int N = words.length;
		for (int i = 1; i < N; i++) {
			String word1 = words[i - 1];
			String word2 = words[i];
			int len1 = word1.length(), len2 = word2.length(), maxLen = Math
					.max(len1, len2);
			boolean found = false;
			for (int j = 0; j < maxLen; j++) {
				char c1 = j < len1 ? word1.charAt(j) : ' ';
				char c2 = j < len2 ? word2.charAt(j) : ' ';
				if (c1 != ' ' && !map.containsKey(c1))
					map.put(c1, new HashSet<Character>());
				if (c2 != ' ' && !map.containsKey(c2))
					map.put(c2, new HashSet<Character>());
				if (c1 != ' ' && c2 != ' ' && c1 != c2 && !found) {
					map.get(c1).add(c2);
					found = true;
				}
			}
		}
		return map;
	}

	public Map<Character, Integer> computeIndegree(
			Map<Character, Set<Character>> graph) {
		Map<Character, Integer> indegree = new HashMap<Character, Integer>();
		for (Character prev : graph.keySet()) {
			if (!indegree.containsKey(prev))
				indegree.put(prev, 0);
			for (Character succ : graph.get(prev)) {
				if (!indegree.containsKey(succ))
					indegree.put(succ, 1);
				else
					indegree.put(succ, indegree.get(succ) + 1);
			}
		}
		return indegree;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
