import java.util.HashMap;

/**
 * Maximum Size Subarray Sum Equals k
 * 
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
 * @author cicean
 *
 */
public class MaximumSizeSubarraySumEqualsk {

    /**
     * 上来一看感觉和209.Minimum Size Subarray Sum有点像，细思之后发现两个根本不是一回事，209里的数全是正数，这里不是，没什么规律。
     前缀和可以解决，需要O(N^2)的时间。需要优化，于是可以把前缀和存在hashmap里，就只需要O(N)的时间了。
     hashmap的key是前缀和，value是到第几个数。注意key(表示前缀和)有可能重复(因为有负数)。
     注意这道题不要先生成hashmap再遍历map找和，因为key有可能重复，就会把之前的相同key覆盖，越靠前的key产生的subarray越长，就会漏掉答案。
     正确的作法是，一边扫描数组生成hashmap一边找和，这样能确保找的都是前面的；
     同时如果遇到key重复了，先记录下当前sum是否可以找到和为k，可以的话记录max,然后我们丢掉这个key,保留最早的那个，
     因为最早的可以生成的size更大，当前这个我们也已经examine过了。
     * @param nums
     * @param k
     * @return
     */
	
	//O(N) 时间 O(N) 空间
	public int maxSubArrayLen(int[] nums, int k) {
		int sum = 0, max = 0;
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int i = 0; i < nums.length; i++) {
	        sum = sum + nums[i];
	        if (sum == k) max = i + 1;
	        else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
	        if (!map.containsKey(sum)) map.put(sum, i);
	    }
	    return max;
    }
	
	
	//O(n^2)
	public int maxSubArrayLen_1(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        int[] sums = new int[n];// sums[i] stores the sum of subarray starting at i
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                sums[j] += nums[i];
                if (sums[j] == k){
                    max = Math.max(max, i - j + 1);
                }
            }
        }
        return max;
    }

}
