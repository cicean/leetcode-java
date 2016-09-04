package facebook;

import java.util.*;

/**
 * Created by cicean on 9/2/2016.
 */
public class facebook08132016 {

    //subarray sum to K      在一个array中，
    // 找出是否有连续的subarray sums to K，返回true or false
    // Maximum Size Subarray Sum Equals k
    //209. Minimum Size Subarray Sum
    
    public boolean isContainSubarray2(int[] nums, int k) {
        int sum = 0, max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k || set.contains(sum - k)) {
            	return true;
            }
            if (!set.contains(sum)) set.add(sum);
        }
        return false;

    }

    //product of the array   给定一个array，返回里面元素乘积的所有可能值。
    // 例如给定array:[1,2,3,4]   应该返回 [1, 2, 3, 4, 6, 8, 12, 24]

    public List<Integer> productofArray(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0)  return res;
        backtracking(nums, 0, 1, res);
        Collections.sort(res);
        return res;
    }

    private void backtracking(int[] nums, int start, int product, List<Integer> res) {
        if (res.contains(product)) return;
        res.add(product);
        for (int i = start; i < nums.length; i++) {
            product *= nums[i];
            backtracking(nums, i + 1, product, res);
            product /= nums[i];
            //System.out.println(product);
        }
    }
    
 public static void main(String[] args) {
	facebook08132016 slt = new facebook08132016();
	int[] nums = new int[] {1,2,3,4};
	System.out.println(slt.isContainSubarray2(nums, 2));
//	List<Integer> res = slt.productofArray(nums);
//	
//	for(Integer i : res) {
//		System.out.println(i);
//	}
	
	
}


}
