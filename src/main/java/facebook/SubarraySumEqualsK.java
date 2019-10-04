package facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cicean on 9/4/2016.
 */
public class SubarraySumEqualsK {

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
}
