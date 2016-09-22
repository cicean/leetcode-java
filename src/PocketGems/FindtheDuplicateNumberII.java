package PocketGems;

import java.util.List;

/**
 * һ������Χ�ڣ�0�� n] ÿ���������ڣ� �е�������һ�Σ� �е����������Σ�������г������ε��� O(n) time, O(1) space
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
