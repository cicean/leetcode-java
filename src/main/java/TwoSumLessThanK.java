/**
 * 1099. Two Sum Less Than K
 * Easy
 *
 * 123
 *
 * 13
 *
 * Favorite
 *
 * Share
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [34,23,1,24,75,33,54,8], K = 60
 * Output: 58
 * Explanation:
 * We can use 34 and 24 to sum 58 which is less than 60.
 * Example 2:
 *
 * Input: A = [10,20,30], K = 15
 * Output: -1
 * Explanation:
 * In this case it's not possible to get a pair sum less that 15.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 * Accepted
 * 11,689
 * Submissions
 * 19,566
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * luisvasquez-Boxer
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 10
 * Two Sum
 * Easy
 * Two Sum II - Input array is sorted
 * Easy
 * 3Sum Smaller
 * Medium
 * Subarray Product Less Than K
 * Medium
 * What if we have the array sorted?
 * Loop the array and get the value A[i] then we need to find a value A[j] such that A[i] + A[j] < K which means A[j] < K - A[i]. In order to do that we can find that value with a binary search.
 */

public class TwoSumLessThanK {
}
