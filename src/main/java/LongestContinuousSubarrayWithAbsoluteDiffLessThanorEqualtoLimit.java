/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Medium
 *
 * 241
 *
 * 5
 *
 * Add to List
 *
 * Share
 * Given an array of integers nums and an integer limit, return the size of the longest continuous subarray such that the absolute difference between any two elements is less than or equal to limit.
 *
 * In case there is no subarray satisfying the given condition return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 *
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 *
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 * Accepted
 * 8,795
 * Submissions
 * 22,665
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * LeetCode
 * Use a sliding window approach keeping the maximum and minimum value using a data structure like a multiset from STL in C++.
 * More specifically, use the two pointer technique, moving the right pointer as far as
 * possible to the right until the subarray is not valid (maxValue - minValue > limit),
 * then moving the left pointer until the subarray is valid again (maxValue - minValue <= limit). Keep repeating this process.
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {

    class Solution {
        public int longestSubarray(int[] nums, int limit) {

            int l = 0,r=0,max = 0,min = 0,size = 1;
            while(r<nums.length) {
                if(nums[r]<nums[min]) {
                    min = r;
                }
                else if(nums[r]>nums[max]) {
                    max = r;
                }
                if(nums[max]-nums[min]>limit) {
                    l = Math.min(max,min)+1;
                    if(max<l){
                        max = l;
                        for(int i = l+1;i<=r;i++) {
                            if(nums[max]<=nums[i]) {
                                max = i;
                            }
                        }
                    }
                    if(min<l) {
                        min = l;
                        for(int i = l+1;i<=r;i++) {
                            if(nums[min]>=nums[i]) {
                                min = i;
                            }
                        }
                    }
                }
                size = Math.max(size,r-l+1);
                r++;
            }
            return size;
        }
    }


    class Solution {
        public int longestSubarray(int[] nums, int limit) {

            int i = 0, j = 0, res = 0;
            int subMin = Integer.MAX_VALUE;
            int subMax = Integer.MIN_VALUE;

            while(i <= j && j < nums.length){

                subMin = nums[i];
                subMax = nums[j];
                while(Math.abs(subMax - subMin) <= limit){
                    res = Math.max(res, j - i + 1);
                    j++;
                    if(j == nums.length)
                        break;
                    subMin = Math.min(subMin, nums[j]);
                    subMax = Math.max(subMax, nums[j]);
                }
                i++;
                j=i;
                if(res == nums.length)
                    return res;
            }

            return res;
        }
    }

    class Solution {
        public int longestSubarray(int[] nums, int limit) {

            int i = 0, j = 0, res = 0;
            int subMin = Integer.MAX_VALUE;
            int subMax = Integer.MIN_VALUE;

            while(i <= j && j < nums.length){

                subMin = nums[i];
                subMax = nums[j];
                while(Math.abs(subMax - subMin) <= limit){
                    res = Math.max(res, j - i + 1);
                    j++;
                    if(j == nums.length)
                        break;
                    subMin = Math.min(subMin, nums[j]);
                    subMax = Math.max(subMax, nums[j]);
                }
                i++;
                j=i;
                if(res == nums.length)
                    return res;
            }

            return res;
        }
    }
}
