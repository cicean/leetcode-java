/**
 * Created by cicean on 9/2/2016.
 * 376. Wiggle Subsequence  QuestionEditorial Solution  My Submissions
 Total Accepted: 8516
 Total Submissions: 24963
 Difficulty: Medium
 A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

 For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

 Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

 Examples:
 Input: [1,7,4,9,2,5]
 Output: 6
 The entire sequence is a wiggle sequence.

 Input: [1,17,5,10,13,15,10,5,16,8]
 Output: 7
 There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

 Input: [1,2,3,4,5,6,7,8,9]
 Output: 2
 Follow up:
 Can you do it in O(n) time?

 Credits:
 Special thanks to @agave and @StefanPochmann for adding this problem and creating all test cases.

 Hide Tags Dynamic Programming Greedy

 题意：给定一个数组，让你求最大摆动序列长度。摆动序列定义为序列中任意相邻的三个数中abc，均有 a < b , b >c 或者a>b b<c

 */
public class WiggleSubsequence {

    /**
     * 方法一，看到这个就想到和LIS差不多，迅速的DP一下，1A，

     设dp[i] 为以i结尾的最大摆动序列长度，sign[i]为i这个数比之前的大还是小（大为1，小-1，初始0），更新条件如下：

     dp[j] + 1 > dp[i] and (sign[j] > 0 and nums[i] < nums[j] or sign[j] < 0 and nums[i] > nums[j] or sign[j] == 0 and nums[i] != nums[j]):
     很好的写出相应的python code：
     */

    /**
     * 那么我们只要找出数组中的“拐点” 即可 举个例子:

     4 5 6  3 2 1这几个数中，4为起点，那么5和6中，我们肯定选6啊~因为之后的数要求小于5，小于5的必定也小于6 比如改为4 5 6 5，之前要是选5就没办法继续往下了。。
     */

    public class Solution {

        //Java Solution DP O(n) time complexity, O(1)
        public int wiggleMaxLength(int[] nums) {
            if (nums.length <= 1) return nums.length;
            int pre_sign = 0;
            int signLen = 0;
            for (int i = 1; i < nums.length; i++) {
                int sign = nums[i] - nums[i-1];
                if (sign == 0 || sign * pre_sign > 0) continue;
                else {
                    signLen++;
                    pre_sign = sign;
                }
            }
            return ++signLen;
        }

        //Greedy solution O(n) time complexity, O(1) space complexity
        public int wiggleMaxLength_gd(int[] nums) {
            if (nums == null) return 0;
            if (nums.length <= 1) return nums.length;
            int f = 1, b = 1; //the first number can be a smaller number or larger number depends on what the next number it is.
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i-1]) f = b + 1;
                else if (nums[i] < nums[i-1]) b = f + 1;
            }
            return Math.max(f, b);
        }
    }


}
