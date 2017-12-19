import java.util.*;


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
You may assume k is always valid, ie: 1 �� k �� input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window��s size.
Remove redundant elements and the queue should store only elements that need to be considered.
Hide Tags Heap
Hide Similar Problems (H) Minimum Window Substring (E) Min Stack (H) Longest Substring with At Most Two Distinct Characters

 * ά��һ��deque������index����֤��
��1���� deque.peekLast() �� deque.peekFirst() ��Ӧ��nums[i] (deque.peekFirst() <= i <= deque.peekLast()) ��ֵ�ݼ�
��2�����ڵ�i���������ڣ�deque.peekLast() > i - k����deque�����һ��Ԫ��һ���ڵ�ǰ�ĵ� i �����������ڡ�
��3�����ڵ�i���������ڣ���Ӧ��maximumֵһ���� deque.peekLast()��
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

	//Java Solution runs O(kn) time O(1)
	public int[] maxSlidingWindow_1(int[] nums, int k) {
		int maxIndex = 0;
		int currStorePtr=0;
		for(int i=0;i<nums.length;i++){
			if(i<= maxIndex +k-1){
				if(nums[i]>=nums[maxIndex]) {
					maxIndex =i;
				}
			}
			else{
				maxIndex =i-k+1;
				for(int j=i-k+1;j<=i;j++){
					if(nums[j]>=nums[maxIndex]) maxIndex =j;
				}
			}
			nums[currStorePtr]=nums[maxIndex];

			if(i>=k-1){
				currStorePtr++;
			}
		}
		return Arrays.copyOfRange(nums,0,currStorePtr);
	}

	public static int[] slidingWindowMax(final int[] in, final int w) {
		final int[] max_left = new int[in.length];
		final int[] max_right = new int[in.length];

		max_left[0] = in[0];
		max_right[in.length - 1] = in[in.length - 1];

		for (int i = 1; i < in.length; i++) {
			max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

			final int j = in.length - i - 1;
			max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
		}

		final int[] sliding_max = new int[in.length - w + 1];
		for (int i = 0, j = 0; i + w <= in.length; i++) {
			sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
		}

		return sliding_max;
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
