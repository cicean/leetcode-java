/**
 * Created by cicean on 9/1/2016.
 * 372. Super Pow  QuestionEditorial Solution  My Submissions
 Total Accepted: 6838 Total Submissions: 22046 Difficulty: Medium
 Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

 Example1:

 a = 2
 b = [3]

 Result: 8
 Example2:

 a = 2
 b = [1,0]

 Result: 1024
 Credits:
 Special thanks to @Stomach_ache for adding this problem and creating all test cases.

 Hide Tags Math
 Hide Similar Problems (M) Pow(x, n)
 题意：给定数a和用数组表示的一个很大的数b，求a^b % 1337
 */
public class SuperPow {

    /**
     * 思路：

     一个数e可以写成如下形式：

     e=\sum _{i=0}^{n-1}a_{i}2^{i}

     显然，对于b的e次方，有：

     b^{e}=b^{\left(\sum _{i=0}^{n-1}a_{i}2^{i}\right)}=\prod _{i=0}^{n-1}\left(b^{2^{i}}\right)^{a_{i}}

     c\equiv \prod _{i=0}^{n-1}\left(b^{2^{i}}\right)^{a_{i}}\ ({\mbox{mod}}\ m)



     此外，还有：

     c mod m = (a ? b) mod m  = [(a mod m) ? (b mod m)] mod m

     参照wiki :https://en.wikipedia.org/wiki/Modular_exponentiation

     看懂了上面的式子后，回到此题，此题b用数组表示，其实就是把上面的数e的2改为10即可。
     */

    /**
     * 这道题题让我们求一个数的很大的次方对1337取余的值，
     * 开始一直在想这个1337有什么玄机，为啥突然给这么一个数，感觉很突兀，后来想来想去也没想出来为啥，
     * 估计就是怕结果太大无法表示，随便找个数取余吧。
     * 那么这道题和之前那道Pow(x, n)的解法很类似，我们都得对半缩小，不同的是后面都要加上对1337取余。
     * 由于给定的指数b是一个一维数组的表示方法，我们要是折半缩小处理起来肯定十分不方便，
     * 所以我们采用按位来处理，比如223 = (22)10 * 23, 所以我们可以从b的最高位开始，算出个结果存入res，
     * 然后到下一位是，res的十次方再乘以a的该位次方再对1337取余，
     */
    public class Solution {
        private int mod = 1337;
        public int superPow(int a, int[] b) {
            int n = b.length;
            int ans = 1;
            for (int i = n - 1; i >= 0; i--) {
                ans = ans * quick_pow(a, b[i]) % mod;
                a = quick_pow(a, 10);
            }
            return ans;
        }

        int quick_pow(int a, int b) {
            int ans = 1;
            a %= mod;
            while (b > 0) {
                if ((b & 1) !=0) ans = ans * a % mod;
                a = a * a % mod;
                b >>= 1;
            }
            return ans;

        }
    }
}
