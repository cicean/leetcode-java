/**
 * Created by cicean on 8/31/2016.
 *
 * 360. Sort Transformed Array  QuestionEditorial Solution  My Submissions
 Total Accepted: 3227 Total Submissions: 7843 Difficulty: Medium
 Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

 The returned array must be in sorted order.

 Expected time complexity: O(n)

 Example:
 nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

 Result: [3, 9, 15, 33]

 nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

 Result: [-23, -5, 1, 7]
 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Math Two Pointers

 */
public class SortTransformedArray {

    /**
     * 复杂度
     O(N) 时间 O(N) 空间

     思路
     抛物线中点: -b/2a
     分这么几种情况：
     a > 0，
     a < 0,
     a == 0 && b >= 0,
     a == 0 && b < 0
     给的x数组是排序的，搞两个指针从两边比较

     注意
     维护一个nextIndex可以免去用linkedlist从而节省时间
     * @param num
     * @param a
     * @param b
     * @param c
     * @return
     */

    //还是数学解法，根据这个方程图形的特征来判断最大最小值的取向
    public int function(int num, int a, int b, int c) {
        return a * num * num + b * num + c;
    }

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int len = nums.length;
        int[] result = new int[len];
        int index = a >= 0 ? len - 1 : 0;
        int i = 0, j = len - 1;
        while (i <= j) {
            if (a >= 0) {
                result[index--] = function(nums[i], a, b, c) >= function(nums[j], a, b, c) ? function(nums[i++], a, b, c) : function(nums[j--], a, b, c);
            } else {
                result[index++] = function(nums[i], a, b, c) <= function(nums[j], a, b, c) ? function(nums[i++], a, b, c) : function(nums[j--], a, b, c);
            }
        }
        return result;
    }
}
