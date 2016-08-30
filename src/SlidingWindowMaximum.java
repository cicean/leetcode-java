import java.util.Deque;
import java.util.LinkedList;

/*
 * 
 * 	239	Sliding Window Maximum	22.9%	Hard
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.
Hide Tags Heap
Hide Similar Problems (H) Minimum Window Substring (E) Min Stack (H) Longest Substring with At Most Two Distinct Characters

 * 维护一个deque来保存index，保证：
（1）从 deque.peekLast() 到 deque.peekFirst() 对应的nums[i] (deque.peekFirst() <= i <= deque.peekLast()) 的值递减
（2）对于第i个滑动窗口，deque.peekLast() > i - k，即deque的最后一个元素一定在当前的第 i 个滑动窗口内。
（3）对于第i个滑动窗口，对应的maximum值一定是 deque.peekLast()。
 */
public class SlidingWindowMaximum {

	public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null|| nums.length ==0) return new int[0];
        
        int[] result = new int[nums.length-k+1];
        
        Deque<Integer> deque = new LinkedList<Integer>();
        for(int i = 0, j= 0; j< result.length;i++){
        	while(deque.size() !=0 &&nums[deque.peekFirst()]<nums[i]){
        		deque.removeFirst();
        	}
        	deque.addFirst(i);
        	if(i<k-1) continue;
        	if(deque.peekLast()<= i-k) deque.removeLast();
        	result[j++]  = nums[deque.peekLast()];
        }
        return result;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SlidingWindowMaximum slt = new SlidingWindowMaximum();
		int[] nums = {1,3,-1,-3,5,3,6,7};
		int k =3;
		int[] res = slt.maxSlidingWindow(nums, k);
		for  (int i= 0; i<res.length;i++){
			System.out.print(res[i]+",");
		}
	}

}
