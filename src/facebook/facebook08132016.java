package facebook;

import java.util.HashMap;

/**
 * Created by cicean on 9/2/2016.
 */
public class facebook08132016 {

    //subarray sum to K      在一个array中，
    // 找出是否有连续的subarray sums to K，返回true or false
    // Maximum Size Subarray Sum Equals k
    //209. Minimum Size Subarray Sum

    public boolean isContainSubarray(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max == 0? false : true;

    }

    //product of the array   给定一个array，返回里面元素乘积的所有可能值。
    // 例如给定array:[1,2,3,4]   应该返回 [1, 2, 3, 4, 6, 8, 12, 24]

    public int[] productofArray(int[] nums) {
        if (nums.length == 0)  return new int[0];
        int n = nums.length;
        int[] res = new int[n*n];
        backtracking(nums, 0, 0,res);
        return res;
    }

    private void backtracking(int[] nums, int start, int index, int[] res) {
        if (start == nums.length - 1) return;
        for (int i = start; i < nums.length; i++) {
            res[index] = nums[start] * nums[i];
            index++;
            backtracking(nums, i, index, res);
        }
    }
    
 public static void main(String[] args) {
	facebook08132016 slt = new facebook08132016();
	int[] nums = new int[] {1,2,3,4};
	System.out.println(slt.isContainSubarray(nums, 6));
}


}
