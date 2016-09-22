import datastructure.PrintList;

import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 *
 * 347. Top K Frequent Elements
 *
 * QuestionEditorial Solution My Submissions Total Accepted: 27943 Total
 * Submissions: 63529 Difficulty: Medium Given a non-empty array of integers,
 * return the k most frequent elements.
 * 
 * For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is
 * the array's size. Hide Company Tags Pocket Gems Yelp Hide Tags Hash Table
 * Heap Hide Similar Problems (M) Word Frequency (M) Kth Largest Element in an
 * Array 给定一个数组，返回其出现次数最多的k个元素，要求复杂度小于O(nlogn)
 */
public class TopKFrequentElements {

	/**
	 * 首先扫一遍数组进行计数。
	 * 
	 * 接着用一个长度为k 堆，存储出现次数最多的元素（堆顶的元素最小，每次和堆顶的元素比较即可）
	 */

	// Bucket Sort
	public class Solution {
		public List<Integer> topKFrequent(int[] nums, int k) {
			int n = nums.length;
			Map<Integer, Integer> map = new HashMap<>();
			for (int num : nums) {
				if (map.containsKey(num))
					map.put(num, map.get(num) + 1);
				else
					map.put(num, 1);
			}
			List<Integer>[] freq = new ArrayList[n + 1];
			for (int num : map.keySet()) {
				int i = map.get(num);
				if (freq[i] == null) {
					freq[i] = new ArrayList<>();
				}
				freq[i].add(num);
			}
			List<Integer> res = new ArrayList<>();
			for (int index = freq.length - 1; index >= 0 && res.size() < k; index--) {
				if (freq[index] != null) {
					res.addAll(freq[index]);
				}
			}
			return res;
		}
	}

	public class Solution2 {

	}

	// remove hashset combine with bucket select
	public List<Integer> topKFrequent2(int[] nums, int k) {
		// is sort array or not ?
		// if the the number have same Frequenrt what order will such add if
		// 1,1,2,2,3,3 k= 2 is out put 1, 2, 3?
		// remove hashmap
		// O(nlogn + n + k)
		List<Integer> res = new ArrayList<>();

		if (nums == null || nums.length == 0)
			return res;
		if (nums.length == 1)
			res.add(nums[0]);

		int n = nums.length;
		int tmp = 1;
		Arrays.sort(nums);
		List<Integer>[] freq = new ArrayList[n + 1];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] == nums[i]) {
				tmp++;
			} else {
				if (freq[tmp] == null)
					freq[tmp] = new ArrayList<>();
				freq[tmp].add(nums[i - 1]);
				tmp = 1;
			}
			if (i == nums.length - 1) {
				if (freq[tmp] == null)
					freq[tmp] = new ArrayList<>();
				freq[tmp].add(nums[i]);
			}
		}

		for (int index = freq.length - 1; index >= 0 && res.size() < k; index--) {
			if (freq[index] != null && !freq[index].isEmpty()) {
				res.addAll(freq[index]);
			}
		}
		return res;
	}

	/**
	 * Idea is very straightforward:
	 * 
	 * build a counter map that maps a num to its frequency build a
	 * heap/priority queue that keeps track of k most significant entries
	 * iterate through the final heap and get the keys
	 */

	// *Java* straightforward O(N + (N-k)lg k) solution
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> counterMap = new HashMap<>();
		for (int num : nums) {
			int count = counterMap.getOrDefault(num, 0);
			counterMap.put(num, count + 1);
		}

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a,
				b) -> a.getValue() - b.getValue());
		for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
			pq.offer(entry);
			if (pq.size() > k)
				pq.poll();
		}

		List<Integer> res = new LinkedList<>();
		while (!pq.isEmpty()) {
			res.add(0, pq.poll().getKey());
		}
		return res;
	}

	/**
	 * Find top K frequency words in words list
	 * Bloomberg
	 * @param args
     */
	public List<String> topKFrequent(String[] wordsList, int k) {
		List<String> res = new ArrayList<>();

		if (wordsList == null || wordsList.length == 0) return res;
		
		if (wordsList.length == 1) res.add(wordsList[0]);

		int n = wordsList.length;
		int tmp = 1;
		Arrays.sort(wordsList);
		List<String>[] freq = new ArrayList[n + 1];
		for (int i = 1; i < wordsList.length; i++) {
			if (wordsList[i - 1] == wordsList[i]) {
				tmp++;
			} else {
				if (freq[tmp] == null)
					freq[tmp] = new ArrayList<>();
				freq[tmp].add(wordsList[i - 1]);
				tmp = 1;
			}
			
			if (i == wordsList.length - 1) {
				if (freq[tmp] == null)
					freq[tmp] = new ArrayList<>();
				freq[tmp].add(wordsList[i]);
			}
		}

		for (int index = freq.length - 1; index >= 0 && res.size() < k; index--) {
			if (freq[index] != null && !freq[index].isEmpty()) {
				res.addAll(freq[index]);
			}
		}
		return res;
	}


	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 2, 2, 3 , 3};
		int k = 2;
		PrintList<Integer> res = new PrintList<>();
		TopKFrequentElements slTopKFrequentElements = new TopKFrequentElements();
		res.printList(slTopKFrequentElements.topKFrequent2(nums, k));

		String[] wordsList = {"ab","bc","ab","abc","abc","ab"};

		PrintList<String> print = new PrintList<>();
		print.printList(slTopKFrequentElements.topKFrequent(wordsList, k));

	}

}
