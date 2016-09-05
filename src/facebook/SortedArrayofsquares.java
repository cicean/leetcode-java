package facebook;

/**
 * Created by cicean on 9/4/2016.
 *We're given a sorted array of integers: [-3, -1, 0, 1, 2].1point3acresç½?
 We want to generate a sorted array of their squares: [0, 1, 1, 4, 9]
 O(n) runtime complexity.
 */
public class SortedArrayofsquares {

    public class Solution {
        public int[] square(int[] nums) {
            if (nums == null || nums.length == 0) return new int[0];
            int left = 0, right = nums.length - 1, index = right;
            int[] res = new int[nums.length];
            while (left <= right) {
                if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                     nums[index--] = nums[left] * nums[left];
                    left++;
                }else if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                    nums[index--] = nums[right] * nums[right];
                    right--;
                }
            }
            return res;
        }
    }
}
