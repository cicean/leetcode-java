/**
 * Created by cicean on 8/30/2016.
 * 335. Self Crossing  QuestionEditorial Solution  My Submissions
 Total Accepted: 8832 Total Submissions: 39750 Difficulty: Hard
 You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.

 Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

 Example 1:
 Given x = [2, 1, 1, 2],
 ┌───┐
 │   │
 └───┼──>
 │

 Return true (self crossing)
 Example 2:
 Given x = [1, 2, 3, 4],
 ┌──────┐
 │      │
 │
 │
 └────────────>

 Return false (not self crossing)
 Example 3:
 Given x = [1, 1, 1, 1],
 ┌───┐
 │   │
 └───┼>

 Return true (self crossing)
 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Tags Math
 给定一个数组x，代表行走的距离，最初的方向是北，每走一次就按逆时针顺序变化方向（北、西、南、东）

 要求只遍历一次x并且用O(1)的存储空间 判断走过的路径是否交叉。

 */
public class SelfCrossing {

    /**
     * 感觉有点像海龟作图→_→

     There are only 3 scenarios where it won’t cross itself.

     The distances of the moves parallel to each other keeps going up (growing spiral).
     The distances of the moves parallel to each other keeps going down (shrinking spiral).
     The distances of the moves parallel to each other first keeps going up, then keeps going down (shrinking spiral inside of the growing spiral), and never goes up.
     from  https://leetcode.com/discuss/88038/java-o-n-o-1-0ms-solution-with-explanation

     不交叉的有以下三种情况

     平行移动的距离是不断的增加的（螺旋形上升）
     平行移动的距离是不断的减少的（螺旋形下降）
     平行移动的距离先增加后减少，并且再也不增加。
     */
    public class Solution {
        public boolean isSelfCrossing(int[] x) {
            int n = x.length;
            if (n < 4) return false;
            int t1 = 0, t2 = x[0], t3 = x[1], t4 = x[2], t5;
            boolean increase = t4 > t2 ? true : false;
            for (int i = 3; i < n; i++) {
                t5 = x[i];
                if (increase && t3 >= t5) {
                    if (t5 + t1 < t3 || i + 1 < n && x[i + 1] + t2 < t4)
                        increase = false;
                    else if (i + 1 < n)
                        return true;
                }
                else if (!increase && t3 <= t5)
                    return true;
                t1 = t2;
                t2 = t3;
                t3 = t4;
                t4 = t5;
            }
            return false;
        }
    }
}
