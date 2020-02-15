import java.util.Arrays;
import java.util.PriorityQueue;

/**

 215. Kth Largest Element in an Array  QuestionEditorial Solution  My Submissions
 Total Accepted: 74878
 Total Submissions: 210780
 Difficulty: Medium
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 �� k �� array's length.

 Credits:
 Special thanks to @mithmatt for adding this problem and creating all test cases.

 Hide Company Tags Facebook Amazon Microsoft Apple Bloomberg Pocket Gems
 Hide Tags Heap Divide and Conquer
 Hide Similar Problems (M) Wiggle Sort II (M) Top K Frequent Elements


Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
Average case time is O(n), worst case time is O(n^2).

 */

public class KthLargestElementinanArray {
	
	/**
	 * This problem is well known and quite often can be found in various text books.
	 * You can take a couple of approaches to actually solve it:
	 * O(N lg N) running time + O(1) memory
	 * The simplest approach is to sort the entire input array and then access the element by it's index (which is O(1)) operation:
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest_1(int[] nums, int k) {
	    Arrays.sort(nums);
	    return nums[nums.length-k];
	}
	
	/**
	 * O(N lg K) running time + O(K) memory
	 * Other possibility is to use a min oriented priority queue that will store the K-th largest values. 
	 * The algorithm iterates over the whole input and maintains the size of priority queue.
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest(int[] nums, int k) {

	    final PriorityQueue<Integer> pq = new PriorityQueue<>();
	    for(int val : nums) {
	        pq.offer(val);

	        if(pq.size() > k) {
	            pq.poll();
	        }
	    }
	    return pq.peek();
	}

	class Solution_quicksort {
		public int findKthLargest(int[] nums, int k) {
			return findKthLargest(nums, k, 0, nums.length - 1);
		}

		private int findKthLargest(int[] nums, int k, int start, int end) {
			if (start >= end) {
				return nums[k - 1];
			}

			int l = start;
			int r = end;
			int temp = nums[(start + end) / 2];
			while (l <= r) {
				while (l <= r && nums[r] < temp) {
					r--;
				}

				while (l <= r && nums[l] > temp) {
					l++;
				}

				if (l <= r) {
					swap(nums, l, r);
					l++;
					r--;
				}
			}

			if (r >= k - 1) {
				return findKthLargest(nums, k, start, r);
			} else if (l <= k - 1) {
				return findKthLargest(nums, k, l, end);
			} else {
				return nums[k - 1];
			}
		}

		private void swap(int[] nums, int m, int n) {
			int temp = nums[m];
			nums[m] = nums[n];
			nums[n] = temp;
		}
	}

	//QuickSelect Java solution avg. O(n) time O(1)

	/**
	 * Time complexity : \mathcal{O}(N)O(N) in the average case, \mathcal{O}(N^2)O(N
	 * 2
	 *  ) in the worst case.
	 * Space complexity : \mathcal{O}(1)O(1).
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest_2(int[] nums, int k) {  
        return findK(nums, nums.length-k, 0, nums.length-1);  
    }  
      
    private int findK(int[] nums, int k, int i, int j) {  
        if(i >= j) return nums[i];
        int m = partition(nums, i, j);  
        if(m == k) return nums[m];
        else if(m<k) {  
            return findK(nums, k, m+1, j);  
        } else {  
            return findK(nums, k, i, m-1);  
        }  
    }  
      
    private int partition(int[] nums, int i, int j) {  
        int x = nums[i];  
        int m = i;  
        int n = i+1;  
          
        while(n<=j){  
            if(nums[n] < x) {
                swap(nums, ++m, n);  
            }  
            ++n;  
        }  
        swap(nums,i, m);  
        return m;  
    }  
      
    private void swap(int[] nums, int i, int j) {  
        int temp = nums[i];  
        nums[i] = nums[j];  
        nums[j] = temp;  
    }  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KthLargestElementinanArray slt = new KthLargestElementinanArray();
		int[] nums = {3,2,1,5,6,4};
		int k = 2;
		System.out.print(slt.findKthLargest_1(nums, k));
		
	}

}
