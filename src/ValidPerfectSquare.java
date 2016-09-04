/**
 * Created by cicean on 9/1/2016.
 *367. Valid Perfect Square  QuestionEditorial Solution  My Submissions
 Total Accepted: 15264 Total Submissions: 41759 Difficulty: Medium
 Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Returns: True
 Example 2:

 Input: 14
 Returns: False
 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.

 Hide Company Tags LinkedIn
 Hide Tags Binary Search Math
 Hide Similar Problems (M) Sqrt(x)

 * 题意：给定一个数n，要求不使用sqrt函数判断该数是否为完全平方数
 */
public class ValidPerfectSquare {

    //Newton's method, close to O(1)
    public class Solution {
        public boolean isPerfectSquare(int num) {
            if (num < 1) return false;
            long t = num/2+1;
            while (t*t > num) {
                t = (t+num/t)/2;
            }
            return t*t == num;
        }
    }

    //Binary-Search method, O(logn)
    //思路: 二分查找即可. 话说linkedin好像又开始招人了,
    // 可能前段时间被微软收购之后又有钱了, 哈哈!
    public class Solution2 {
        public boolean isPerfectSquare(int num) {
            long start = 1, end = num;
            while (start <= end) {
                long mid = start + (end-start)/2;
                long t = mid * mid;
                if (t == num) return true;
                else if (t < num) start = mid+1;
                else end = mid-1;
            }
            return false;
        }
    }
}
