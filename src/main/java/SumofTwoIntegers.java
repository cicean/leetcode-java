/**
 * Created by cicean on 9/1/2016.
 * 371. Sum of Two Integers  QuestionEditorial Solution  My Submissions
 Total Accepted: 30448 Total Submissions: 58866 Difficulty: Easy
 Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

 Example:
 Given a = 1 and b = 2, return 3.

 Credits:
 Special thanks to @fujiaozhu for adding this problem and creating all test cases.

 Hide Company Tags Hulu
 Hide Tags Bit Manipulation
 Hide Similar Problems (M) Add Two Numbers
 题意：给定两个数 a和b，求它们的和。 要求不使用 +和-运算符

 */
public class SumofTwoIntegers {

    /**
     * Given two numbers a and b, a&b returns the number formed by '1' bits on a and b. When it is left shifted by 1 bit, it is the carry.

     For example, given a=101 and b=111 (in binary), the a&b=101. a&b << 1 = 1010.

     a^b is the number formed by different bits of a and b. a&b=10.
     */

    /**
     * 思路：

     首先要知道，异或也被称为“模2和” ，so 这题就是运用异或的位运算啦。

     我们可以每次取最低位来计算, 然后每次右移一位，注意点有：

     进位为两个数字1
     负数的情况下，右移最高位补的是1 ,因此值得注意要取到什么时候为止。
     java有一个无符号右移>>>高位补0，
     因此结束条件可以为a!=0 || b!=0（然而进位会被忽略）
     */
    public class Solution {
        public int getSum(int a, int b) {
            int ans = 0, carry = 0;
            for (int i = 0; i < 32; a >>>= 1, b >>>= 1, i++) {
                int lower_a = a & 1, lower_b = b & 1;
                ans |= (lower_a ^ lower_b ^ carry) << i;
                carry = (carry & lower_a) | (carry & lower_b) | (lower_a & lower_b);
            }
            return ans;
        }
    }

    //
    public int getSum(int a, int b) {

        while(b!=0){
            int c = a&b;
            a=a^b;
            b=c<<1;
        }

        return a;
    }
}
