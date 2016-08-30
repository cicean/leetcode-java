/**
 * Created by cicean on 8/29/2016.
 *
 * 303. Range Sum Query - Immutable  QuestionEditorial Solution  My Submissions
 Total Accepted: 41747 Total Submissions: 161688 Difficulty: Easy
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.
 Hide Company Tags Palantir
 Hide Tags Dynamic Programming
 Hide Similar Problems (M) Range Sum Query 2D - Immutable (M) Range Sum Query - Mutable (M) Maximum Size Subarray Sum Equals k

 */
public class RangeSumQueryImmutable {

    /**
     * 思路： 求区间的和就想到前n项和，即sumRange(i, j) = sum[j + 1] - sum[i]. 为了方便， 前n项和数组一般比原数组开辟的多一个， 因为前0项和为0.

     时间复杂度： O(n)
     空间复杂度： O(n)
     */
    public class NumArray {
        private int[] sum;
        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            sum[0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);


}
