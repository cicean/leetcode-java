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
 ���⣺������a���������ʾ��һ���ܴ����b����a^b % 1337
 */
public class SuperPow {

    /**
     * ˼·��

     һ����e����д��������ʽ��

     e=\sum _{i=0}^{n-1}a_{i}2^{i}

     ��Ȼ������b��e�η����У�

     b^{e}=b^{\left(\sum _{i=0}^{n-1}a_{i}2^{i}\right)}=\prod _{i=0}^{n-1}\left(b^{2^{i}}\right)^{a_{i}}

     c\equiv \prod _{i=0}^{n-1}\left(b^{2^{i}}\right)^{a_{i}}\ ({\mbox{mod}}\ m)



     ���⣬���У�

     c mod m = (a ? b) mod m  = [(a mod m) ? (b mod m)] mod m

     ����wiki :https://en.wikipedia.org/wiki/Modular_exponentiation

     �����������ʽ�Ӻ󣬻ص����⣬����b�������ʾ����ʵ���ǰ��������e��2��Ϊ10���ɡ�
     */

    /**
     * ���������������һ�����ĺܴ�Ĵη���1337ȡ���ֵ��
     * ��ʼһֱ�������1337��ʲô������ΪɶͻȻ����ôһ�������о���ͻأ������������ȥҲû�����Ϊɶ��
     * ���ƾ����½��̫���޷���ʾ������Ҹ���ȡ��ɡ�
     * ��ô������֮ǰ�ǵ�Pow(x, n)�Ľⷨ�����ƣ����Ƕ��ö԰���С����ͬ���Ǻ��涼Ҫ���϶�1337ȡ�ࡣ
     * ���ڸ�����ָ��b��һ��һά����ı�ʾ����������Ҫ���۰���С���������϶�ʮ�ֲ����㣬
     * �������ǲ��ð�λ����������223 = (22)10 * 23, �������ǿ��Դ�b�����λ��ʼ��������������res��
     * Ȼ����һλ�ǣ�res��ʮ�η��ٳ���a�ĸ�λ�η��ٶ�1337ȡ�࣬
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
