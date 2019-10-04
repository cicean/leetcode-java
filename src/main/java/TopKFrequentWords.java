import java.util.*;

/**
 * 692. Top K Frequent Words
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty list of words, return the k most frequent elements.

 Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

 Example 1:
 Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 Output: ["i", "love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.
 Example 2:
 Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 Output: ["the", "is", "sunny", "day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 with the number of occurrence being 4, 3, 2 and 1 respectively.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Input words contain only lowercase letters.
 Follow up:
 Try to solve it in O(n log k) time and O(n) extra space.
 Can you solve it in O(n) time with only O(k) extra space?
 Companies
 Amazon Bloomberg Uber Yelp Pocket Gems
 Related Topics
 Hash Table Heap Trie
 Similar Questions
 Top K Frequent Elements
 */

public class TopKFrequentWords {

  /**
   * Approach #1: Sorting [Accepted]

   Intuition and Algorithm

   Count the frequency of each word, and sort the words with a custom ordering relation that uses these frequencies.
   Then take the best k of them.


   */

  class Solution {

    /**
     * Complexity Analysis

     Time Complexity: O(N \log{N})O(NlogN), where NN is the length of words. We count the frequency
     of each word in O(N)O(N) time, then we sort the given words in O(N \log{N})O(NlogN) time.

     Space Complexity: O(N)O(N), the space used to store our candidates.
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
      Map<String, Integer> count = new HashMap();
      for (String word : words) {
        count.put(word, count.getOrDefault(word, 0) + 1);
      }
      List<String> candidates = new ArrayList(count.keySet());
      Collections.sort(candidates, (w1, w2) -> count.get(w1) != count.get(w2) ?
          count.get(w2) - count.get(w1) : w1.compareTo(w2));

      return candidates.subList(0, k);
    }
  }

  /**
   * Approach #2: Heap [Accepted]

   Intuition and Algorithm

   Count the frequency of each word, then add it to heap that stores the best k candidates.
   Here, "best" is defined with our custom ordering relation, which puts the worst candidates
   at the top of the heap. At the end, we pop off the heap up to k times and reverse the result
   so that the best candidates are first.

   In Python, we instead use heapq.heapify, which can turn a list into a heap in linear time,
   simplifying our work.
   */

  class Solution_2 {

    /**
     * Complexity Analysis

     Time Complexity: O(N \log{k})O(Nlogk), where NN is the length of words. We count the frequency of each word in O(N)O(N) time, then we add NN words to the heap, each in O(\log {k})O(logk) time. Finally, we pop from the heap up to kk times. As k \leq Nk≤N, this is O(N \log{k})O(Nlogk) in total.
     In Python, we improve this to O(N + k \log {N})O(N+klogN): our heapq.heapify operation and counting operations are O(N)O(N), and each of kk heapq.heappop operations are O(\log {N})O(logN).

     Space Complexity: O(N)O(N), the space used to store our count.
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
      Map<String, Integer> count = new HashMap();
      for (String word: words) {
        count.put(word, count.getOrDefault(word, 0) + 1);
      }
      PriorityQueue<String> heap = new PriorityQueue<String>(
          (w1, w2) -> count.get(w1) != count.get(w2) ?
              count.get(w1) - count.get(w2) : w2.compareTo(w1) );

      for (String word: count.keySet()) {
        heap.offer(word);
        if (heap.size() > k) heap.poll();
      }

      List<String> ans = new ArrayList();
      while (!heap.isEmpty()) ans.add(heap.poll());
      Collections.reverse(ans);
      return ans;
    }
  }

  class Solution2 {
    public List<String> topKFrequent(String[] words, int k) {
      HashMap<String, Integer> map = new HashMap();
      for(String s : words){
        map.put(s, map.getOrDefault(s, 0) + 1);
      }
      PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey())
          : a.getValue() - b.getValue());
      for(Map.Entry<String, Integer> m : map.entrySet()){
        if(pq.size() < k){
          pq.offer(m);
        }else{
          if(pq.peek().getValue() < m.getValue()){
            pq.poll();
            pq.offer(m);
          }else if(pq.peek().getValue() == m.getValue() && pq.peek().getKey().compareTo(m.getKey()) > 0){
            pq.poll();
            pq.offer(m);
          }
        }
      }
      List<String> rs = new ArrayList<>();
      while(!pq.isEmpty()) rs.add(0, pq.poll().getKey());
      return rs;
    }
  }



  /**
   * Java O(n) solution using HashMap, BucketSort and Trie - 22ms Beat 81%
   This problem is quite similar to the problem Top K Frequent Elements. You can refer to this post for the solution of the problem.

   We can solve this problem with the similar idea:
   Firstly, we need to calculate the frequency of each word and store the result in a hashmap.

   Secondly, we will use bucket sort to store words. Why? Because the minimum frequency is greater than or equal to 1 and the maximum frequency is less than or equal to the length of the input string array.

   Thirdly, we can define a trie within each bucket to store all the words with the same frequency. With Trie, it ensures that the lower alphabetical word will be met first, saving the trouble to sort the words within the bucket.

   From the above analysis, we can see the time complexity is O(n).
   Here is my code:
   */

  class Solution3 {
    public List<String> topKFrequent(String[] words, int k) {
      // calculate frequency of each word
      Map<String, Integer> freqMap = new HashMap<>();
      for(String word : words) {
        freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
      }
      // build the buckets
      TrieNode[] count = new TrieNode[words.length + 1];
      for(String word : freqMap.keySet()) {
        int freq = freqMap.get(word);
        if(count[freq] == null) {
          count[freq] = new TrieNode();
        }
        addWord(count[freq], word);
      }
      // get k frequent words
      List<String> list = new LinkedList<>();
      for(int f = count.length - 1; f >= 1 && list.size() < k; f--) {
        if(count[f] == null) continue;
        getWords(count[f], list, k);
      }
      return list;
    }

    private void getWords(TrieNode node, List<String> list, int k) {
      if(node == null) return;
      if(node.word != null) {
        list.add(node.word);
      }
      if(list.size() == k) return;
      for(int i = 0; i < 26; i++) {
        if(node.next[i] != null) {
          getWords(node.next[i], list, k);
        }
      }
    }

    private boolean addWord(TrieNode root, String word) {
      TrieNode curr = root;
      for(char c : word.toCharArray()) {
        if(curr.next[c - 'a'] == null) {
          curr.next[c - 'a'] = new TrieNode();
        }
        curr = curr.next[c - 'a'];
      }
      curr.word = word;
      return true;
    }

    class TrieNode {
      TrieNode[] next;
      String word;
      TrieNode() {
        this.next = new TrieNode[26];
        this.word = null;
      }
    }
  }

}
