import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 347. Top K Frequent Elements  QuestionEditorial Solution  My Submissions
 Total Accepted: 27943
 Total Submissions: 63529
 Difficulty: Medium
 Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

 Note:
 You may assume k is always valid, 1 �� k �� number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 Hide Company Tags Pocket Gems Yelp
 Hide Tags Hash Table Heap
 Hide Similar Problems (M) Word Frequency (M) Kth Largest Element in an Array
 ����һ�����飬��������ִ�������k��Ԫ�أ�Ҫ���Ӷ�С��O(nlogn)
 */
public class TopKFrequentElements {

    /**
     * ����ɨһ��������м�����

     ������һ������Ϊk �ѣ��洢���ִ�������Ԫ�أ��Ѷ���Ԫ����С��ÿ�κͶѶ���Ԫ�رȽϼ��ɣ�
     */

    public class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {
            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int num: nums) {
                if (map.containsKey(num)) map.put(num, map.get(num)+1);
                else map.put(num, 1);
            }
            List<Integer>[] freq = new ArrayList[n+1];
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

}
