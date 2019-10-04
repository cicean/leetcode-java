/**
 * Created by cicean on 8/29/2016.
 * 321. Create Maximum Number  QuestionEditorial Solution  My Submissions
 Total Accepted: 9961 Total Submissions: 43962 Difficulty: Hard
 Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

 Example 1:
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 return [9, 8, 6, 5, 3]

 Example 2:
 nums1 = [6, 7]
 nums2 = [6, 0, 4]
 k = 5
 return [6, 7, 6, 0, 4]

 Example 3:
 nums1 = [3, 9]
 nums2 = [8, 9]
 k = 3
 return [9, 8, 9]

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Dynamic Programming Greedy

 给定两个长度为m和n的数组，他们由0~9的数组组成，
 现在，让你通过这两个数组中的数字组建一个长度为k 的数组（k<=m+n) ,原来数组的相对顺序要保留。

 */
public class CreateMaximumNumber {

    /**
     * 枚举数组长度复杂度O(k)，找出最大子数组O(n)，合并的话O(k^2)

     而k最坏是m+n,所以总的复杂度就是O((m+n)^3)
     */

    public class Solution {

        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int n = nums1.length;
            int m = nums2.length;
            int[] ans = new int[k];
            for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
                int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
                if (greater(candidate, 0, ans, 0)) ans = candidate;
            }
            return ans;
        }
        private int[] merge(int[] nums1, int[] nums2, int k) {
            int[] ans = new int[k];
            for (int i = 0, j = 0, r = 0; r < k; ++r)
                ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
            return ans;
        }
        public boolean greater(int[] nums1, int i, int[] nums2, int j) {
            while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
                i++;
                j++;
            }
            return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
        }
        public int[] maxArray(int[] nums, int k) {
            int n = nums.length;
            int[] ans = new int[k];
            for (int i = 0, j = 0; i < n; ++i) {
                while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) j--;
                if (j < k) ans[j++] = nums[i];
            }
            return ans;
        }
    }

}
