import java.util.HashMap;

/**
 * 325。Maximum Size Subarray Sum Equals k
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
     * ����һ���о���209.Minimum Size Subarray Sum�е���ϸ˼֮����������������һ���£�209�����ȫ�����������ﲻ�ǣ�ûʲô���ɡ�
     ǰ׺�Ϳ��Խ������ҪO(N^2)��ʱ�䡣��Ҫ�Ż������ǿ��԰�ǰ׺�ʹ���hashmap���ֻ��ҪO(N)��ʱ���ˡ�
     hashmap��key��ǰ׺�ͣ�value�ǵ��ڼ�������ע��key(��ʾǰ׺��)�п����ظ�(��Ϊ�и���)��
     ע������ⲻҪ������hashmap�ٱ���map�Һͣ���Ϊkey�п����ظ����ͻ��֮ǰ����ͬkey���ǣ�Խ��ǰ��key������subarrayԽ�����ͻ�©���𰸡�
     ��ȷ�������ǣ�һ��ɨ����������hashmapһ���Һͣ�������ȷ���ҵĶ���ǰ��ģ�
     ͬʱ�������key�ظ��ˣ��ȼ�¼�µ�ǰsum�Ƿ�����ҵ���Ϊk�����ԵĻ���¼max,Ȼ�����Ƕ������key,����������Ǹ���
     ��Ϊ����Ŀ������ɵ�size���󣬵�ǰ�������Ҳ�Ѿ�examine���ˡ�
     * @param nums
     * @param k
     * @return
     */
	
	//O(N) ʱ�� O(N) �ռ�
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

    class Solution {
        public int maxSubArrayLen(int[] nums, int k) {
            int len = nums.length;
            int[] prefixSum = new int[len + 1];
            for (int i = 0; i < len; i++) {
                prefixSum[i+1] = prefixSum[i] + nums[i];
            }

            // System.out.println(Arrays.toString(prefixSum));
            HashMap<Integer, Integer> map = new HashMap<>();
            int max = 0;
            for (int i = 0; i <= len; i++) {
                int cur = prefixSum[i];
                Integer left = map.get(cur - k);
                if (left != null) {
                    max = Math.max(max, i - left);
                }
                if (!map.containsKey(cur)) {
                    map.put(cur, i);
                }
                if (cur == k) max = i;
            }
            return max;
        }
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

    /**
     * google Longest Subarray having sum of elements atmost k
     */

    public int maxSubArrayLen_google(int[] nums, int k) {
        int n = nums.length;
        int max = 0; int last_sum = nums[0];
        int[] sums = new int[n];// sums[i] stores the sum of subarray starting at i
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                sums[j] += nums[i];
                if (Math.abs(sums[j] - k) < Math.abs(last_sum - k)){
                    max = Math.max(max, i - j + 1);
                    last_sum = nums[j];
                }
            }
        }
        return max;
    }

}
