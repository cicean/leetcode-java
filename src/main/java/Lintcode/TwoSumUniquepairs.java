package Lintcode;

import java.util.*;

/**
 *
 *
 */

public class TwoSumUniquepairs {
    /**
     * 本参考程序来自九章算法，由 @九章算法 提供。版权所有，转发请注明出处。
     * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
     * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
     * - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
     * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
     */

    public class Solution {
        /**
         * @param nums an array of integer
         * @param target an integer
         * @return an integer
         */
        public int twoSum6(int[] nums, int target) {
            // Write your code here
            if (nums == null || nums.length < 2)
                return 0;

            Arrays.sort(nums);
            int cnt = 0;
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int v = nums[left] + nums[right];
                if (v == target) {
                    cnt ++;
                    left ++;
                    right --;
                    while (left < right && nums[right] == nums[right + 1])
                        right --;
                    while (left < right && nums[left] == nums[left - 1])
                        left ++;
                } else if (v > target) {
                    right --;
                } else {
                    left ++;
                }
            }
            return cnt;
        }
    }

    //九章硅谷求职算法集训营版本
    public class Solution2 {
        /*
         * @param nums an array of Integer
         * @param target = nums[index1] + nums[index2]
         * @return [index1 + 1, index2 + 1] (index1 < index2)
         */
        public int twoSum6(int[] A, int T) {
            if (A == null || A.length <= 1) {
                return 0;
            }

            Arrays.sort(A);
            int i, n = A.length;
            int j = n - 1;
            int res = 0;

            for (i = 0; i < n; ++i) {
                // item1 should be the first among its duplicates
                if (i > 0 && A[i] == A[i - 1]) {
                    continue;
                }

                // item2 should be the last among its duplicates
                while (j > i && A[j] > T - A[i]) {
                    --j;
                }

                // this is to avoid that i==j, A[i]+A[j]=T, which does not count
                if (j > i && A[j] == T - A[i]) {
                    ++res;
                }
            }

            return res;
        }
    }
}
