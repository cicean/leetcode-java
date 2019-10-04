/*
 * 	209	Minimum Size Subarray Sum	23.0%	Medium
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.

Analysis

We can use 2 points to mark the left and right boundaries of the sliding window. When the sum is greater than the target, 
shift the left pointer; 
when the sum is less than the target, shift the right pointer.

solution:
  1.1 points O(n)解法：滑动窗口法，使用两个下标start和end标识窗口（子数组）的左右边界
  
  4.1 O(nlogn)解法：二分枚举答案，每次判断的时间复杂度为O(n)
 */
public class MinimumSizeSubarraySum {
	
	/**
	 * 复杂度
	 * 时间 O(N) 空间 O(1)
	 * 思路
	 * 我们用两个指针维护一个窗口，保证这个窗口的内的和是小于目标数的。
	 * 如果新来的数加上后，和大于目标，则比较下当前窗口长度和最短窗口长度，
	 * 窗口左边界右移。如果和仍小于目标数，则将窗口右边界右移。注意这里退出的条件，
	 * 右边界是小于等于长度，因为我们窗口到了最右侧时，还需要继续左移左边界来看有没有更优的解法。
	 * 另外，如果左边界大于右边界时，说明最短子串的长度已经小于等于1，我们就不用再查找了。
	 * 注意
	 * 循环的判断条件是right <= nums.length && left <= right
	 * @param s
	 * @param nums
	 * @return
	 */
	
	public class Solution {
	    public int minSubArrayLen(int s, int[] nums) {
	        if(nums.length == 0) return 0;
	        int left = 0, right = 0, sum = 0, minLen = nums.length + 1;
	        while(right <= nums.length && left <= right){
	            if(sum < s){
	                // 当右边界等于长度时，我们要多等一轮，等待左边界左移，这时候不能加
	                if(right < nums.length){
	                    sum += nums[right];
	                }
	                right++;
	            } else {
	                // 当和大于等于目标时，检查长度并左移边界
	                minLen = Math.min(minLen, right - left);
	                sum -= nums[left];
	                left++;
	            }
	        }
	        return minLen <= nums.length ? minLen : 0;
	    }
	}

	public int minSubArrayLen_1(int s, int[] nums) {
	    if(nums == null || nums.length == 0){
	        return 0;
	    }
	 
	    // initialize min length to be the input array length
	    int result = nums.length;
	 
	    int i = 0;
	    int sum = nums[0];
	 
	    for(int j=0; j<nums.length; ){
	        if(i==j){
	            if(nums[i]>=s){ 
	                return 1; //if single elem is large enough
	            }else{
	               j++;
	 
	               if(j<nums.length){
	                    sum = sum + nums[j];
	               }else{
	                    return result;
	               }
	            }    
	        }else{
	            //if sum is large enough, move left cursor
	            if(sum >= s){
	                result = Math.min(j-i+1, result);
	                sum = sum - nums[i]; 
	                i++;
	            //if sum is not large enough, move right cursor
	            }else{
	                j++;
	 
	                if(j<nums.length){
	                    sum = sum + nums[j];
	                }else{
	                    if(i==0){ 
	                        return 0;
	                    }else{    
	                        return result;
	                    }
	                }
	            }
	        }
	    }
	 
	    return result;
	}
	
	public int minSubArrayLen_2(int s, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int re = nums.length + 1;
        while (right < nums.length) {
                sum += nums[right];
                while (sum >= s) {
                        if (right - left + 1 == 1)
                                return 1;
                        re = Math.min(re, right - left + 1);
                        sum -= nums[left++];
                }
                right++;
        }
        return re == nums.length + 1 ? 0 : re;
}
	
	public int minSubArrayLen_3(int s, int[] nums) {
        //采用滑动窗口的方法
        if(nums.length==0 || nums==null) return 0;
        int head=0,tail=0,cursum=0;
        int min=Integer.MAX_VALUE;
        while(tail<nums.length)
        {
            cursum+=nums[tail];
            while(cursum>=s){
                min=Math.min(min,tail-head+1);
                cursum-=nums[head];
                head++;
            }
            tail++;
        }
        return min==Integer.MAX_VALUE?0:min;
    }
	
	
	/**
	 * solution of which the time complexity is O(n log n).
	 * @param s
	 * @param nums
	 * @return
	 */
	public int minSubArrayLen_4(int s, int[] nums) {
        if(nums.length==0 || nums==null) return 0;
        //定义一个sum数组，其中存放当前位置之前的左右元素之和
        int min=Integer.MAX_VALUE;
        int[] sum=new int[nums.length+1];
        for(int i=0;i<nums.length;i++)
        {
            sum[i+1]=sum[i]+nums[i];
            if(sum[i+1]>=s){
                int j=binarySearch(0,i,sum[i+1]-s+1,sum);
                if(j>-1){
                    min=Math.min(min,i-j+1);
                }
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }
	
	int binarySearch(int left, int right, int target, int[] sum) {  
        int result = -1;  
        while (left < right-1) {  
            int m = left + (right-left)/2;  
            if (sum[m] >= target) {  
                right = m-1;  
            } else if (sum[m] < target) {  
                left = m;  
            }  
        }  
        if (sum[right] < target) {  
            return right;  
        } else if (sum[left] < target) {  
            return left;  
        } else {  
            return -1;  
        }  
    }  

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      int[] nums = {2,3,1,2,4,3};
      int s = 7;
      MinimumSizeSubarraySum slt = new MinimumSizeSubarraySum();
      
      System.out.print(slt.minSubArrayLen_4(s, nums));
      
	}

}
