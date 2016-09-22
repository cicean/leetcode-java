package PocketGems;

import java.util.List;

/**
 * 一组数范围在［0， n] 每个数都存在， 有的数出现一次， 有的数出现两次，输出所有出现两次的数 O(n) time, O(1) space
 * 
 * @author cicean
 *
 */

public class FindtheDuplicateNumberII {

	public List<Integer> findDuplicate(int[] nums) {
		 int fast, slow;

		    fast = slow = nums[0];
		    
		    while ()

		    do {
		        fast = nums[nums[fast]];
		        slow = nums[slow];
		    } while (fast != slow);

		    slow = nums[0];
		    while (fast != slow) {
		        fast = nums[fast];
		        slow = nums[slow];
		    }

		    return fast;
	}
}
