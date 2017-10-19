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

  class Solution {
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

}
