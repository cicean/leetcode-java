/*
 * 	209	Minimum Size Subarray Sum	23.0%	Medium
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum �� s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.

Analysis

We can use 2 points to mark the left and right boundaries of the sliding window. When the sum is greater than the target, 
shift the left pointer; 
when the sum is less than the target, shift the right pointer.

solution:
  1.1 points O(n)�ⷨ���������ڷ���ʹ�������±�start��end��ʶ���ڣ������飩�����ұ߽�
  
  4.1 O(nlogn)�ⷨ������ö�ٴ𰸣�ÿ���жϵ�ʱ�临�Ӷ�ΪO(n)
 */
public class MinimumSizeSubarraySum {
	
	/**
	 * ���Ӷ�
	 * ʱ�� O(N) �ռ� O(1)
	 * ˼·
	 * ����������ָ��ά��һ�����ڣ���֤������ڵ��ڵĺ���С��Ŀ�����ġ�
	 * ��������������Ϻ󣬺ʹ���Ŀ�꣬��Ƚ��µ�ǰ���ڳ��Ⱥ���̴��ڳ��ȣ�
	 * ������߽����ơ��������С��Ŀ�������򽫴����ұ߽����ơ�ע�������˳���������
	 * �ұ߽���С�ڵ��ڳ��ȣ���Ϊ���Ǵ��ڵ������Ҳ�ʱ������Ҫ����������߽�������û�и��ŵĽⷨ��
	 * ���⣬�����߽�����ұ߽�ʱ��˵������Ӵ��ĳ����Ѿ�С�ڵ���1�����ǾͲ����ٲ����ˡ�
	 * ע��
	 * ѭ�����ж�������right <= nums.length && left <= right
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
	                // ���ұ߽���ڳ���ʱ������Ҫ���һ�֣��ȴ���߽����ƣ���ʱ���ܼ�
	                if(right < nums.length){
	                    sum += nums[right];
	                }
	                right++;
	            } else {
	                // ���ʹ��ڵ���Ŀ��ʱ����鳤�Ȳ����Ʊ߽�
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
        //���û������ڵķ���
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
        //����һ��sum���飬���д�ŵ�ǰλ��֮ǰ������Ԫ��֮��
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
