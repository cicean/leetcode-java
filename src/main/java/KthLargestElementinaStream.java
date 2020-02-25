import java.util.*;

/**
 * 703. Kth Largest Element in a Stream
 * Easy
 *
 * 518
 *
 * 273
 *
 * Add to List
 *
 * Share
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 *
 * Accepted
 * 60,299
 * Submissions
 * 125,093
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 5
 *
 * Facebook
 * |
 * 2
 *
 * Nutanix
 * |
 * 2
 * Kth Largest Element in an Array
 */
public class KthLargestElementinaStream {

    class KthLargest {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            for(int i = 0; i < nums.length; i++){
                add(nums[i]);
            }
        }

        public int add(int val) {
            if(minHeap.size() < k){
                minHeap.add(val);
            }else if(minHeap.peek() < val){
                minHeap.poll();
                minHeap.add(val);
            }

            return minHeap.peek();
        }
    }

    class KthLargest_2 {

        final PriorityQueue<Integer> q;
        final int k;

        public KthLargest_2(int k, int[] a) {
            this.k = k;
            q = new PriorityQueue<>(k);
            for (int n : a)
                add(n);
        }

        public int add(int n) {
            if (q.size() < k)
                q.offer(n);
            else if (q.peek() < n) {
                q.poll();
                q.offer(n);
            }
            return q.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
}
