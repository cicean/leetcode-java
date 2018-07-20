package Lintcode;

import java.util.*;

/**
 * 382. Triangle Count
 * Description
 * Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?
 *
 * Have you met this question in a real interview?
 * Example
 * Given array S = [3,4,6,7], return 3. They are:
 *
 * [3,4,6]
 * [3,6,7]
 * [4,6,7]
 * Given array S = [4,4,4,4], return 4. They are:
 *
 * [4(1),4(2),4(3)]
 * [4(1),4(2),4(4)]
 * [4(1),4(3),4(4)]
 * [4(2),4(3),4(4)]
 * 给定一个整数数组，在该数组中，寻找三个数，分别代表三角形三条边的长度，问，可以寻找到多少组这样的三个数来组成三角形？
 */

public class TriangleCount {
    public class Solution {
        /**
         * @param S: A list of integers
         * @return: An integer
         */
        public int triangleCount(int S[]) {
            // write your code here
            int left = 0, right = S.length - 1;
            int ans = 0;
            Arrays.sort(S);
            for(int i = 0; i < S.length; i++) {
                left = 0;
                right = i - 1;
                while(left < right) {
                    if(S[left] + S[right] > S[i]) {
                        ans = ans + (right - left);
                        right --;
                    } else {
                        left ++;
                    }
                }
            }
            return ans;
        }
    }

    // 九章硅谷求职算法集训营版本
    public class Solution2 {
        /**
         * @param A: A list of integers
         * @return: An integer
         */
        public int triangleCount(int A[]) {
            // write your code here

            int w, i, j, res = 0;
            Arrays.sort(A);
            int n = A.length;
            for (w = 2; w < n; ++w) {
                j = w - 1;
                for (i = 0; i < w; ++i) {
                    while (j >= 0 && A[i] + A[j] > A[w]) {
                        --j;
                    }

                    res += w - Math.max(i + 1, j + 1);
                }
            }

            return res;
        }
    }
}
