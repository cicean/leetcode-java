/**
 * 560. Subarray Sum Equals K
 * Medium
 *
 * 2701
 *
 * 71
 *
 * Favorite
 *
 * Share
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * Accepted
 * 157,252
 * Submissions
 * 364,950
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * just_not_over_12
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 44
 *
 * Amazon
 * |
 * 14
 *
 * Microsoft
 * |
 * 8
 *
 * Google
 * |
 * 4
 *
 * Goldman Sachs
 * |
 * 2
 *
 * Bloomberg
 * |
 * 2
 *
 * Oracle
 * |
 * 2
 *
 * eBay
 * |
 * 2
 * Two Sum
 * Easy
 * Continuous Subarray Sum
 * Medium
 * Subarray Product Less Than K
 * Medium
 * Find Pivot Index
 * Easy
 * Subarray Sums Divisible by K
 * Medium
 * Will Brute force work here? Try to optimize it.
 * Can we optimize it by using some extra space?
 * What about storing sum frequencies in a hash table? Will it be useful?
 * sum(i,j)=sum(0,j)-sum(0,i), where sum(i,j) represents the sum of all the elements from index i to j-1. Can we use this property to optimize it.
 */
public class SubarraySumEqualsK {

    /**
     * O(n*n)
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += subarraySum_help(nums, i, k);
            System.out.println("count round" + i + ", count :" + count);
        }
        return count;
    }

    private int subarraySum_help(int[] nums, int start, int k) {
        int sum = nums[start];
        int count = 0;
        start += 1;
        System.out.println("count :" + count);
        while (start < nums.length) {
            if (sum == k) {
                count++;
                System.out.println("count round" + start + ", count :" + count);
            }
            sum += nums[start];
            start++;
        }

        if (sum == k) {
            count++;
        }

        return count;

    }
}
