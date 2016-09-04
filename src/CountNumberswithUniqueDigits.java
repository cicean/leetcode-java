/**
 * Created by cicean on 8/31/2016.
 * 357. Count Numbers with Unique Digits  QuestionEditorial Solution  My Submissions
 Total Accepted: 13974 Total Submissions: 32033 Difficulty: Medium
 Given a non-negative integer n, count all numbers with unique digits, x, where 0 �� x < 10n.

 Example:
 Given n = 2, return 91. (The answer should be the total numbers in the range of 0 �� x < 100, excluding [11,22,33,44,55,66,77,88,99])

 Hint:

 A direct way is to use the backtracking approach.
 Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which represent which number is marked as visited so far in the current number). Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
 This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
 Let f(k) = count of numbers with unique digits with length equals k.
 f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 Credits:
 Special thanks to @memoryless for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Dynamic Programming Backtracking Math
 �����Ǹ�������n������ 0 �� x < 10n  �У��ж���ÿ��λ�ϵ����ֻ�����ͬ������
 �� n =2 ʱ����ΧΪ[0,100]�� ����91����������11,22,33,44,55,66,77,88,99��
 */
public class CountNumberswithUniqueDigits {

    /**
     *������������Ŀ������

     ��ʾ��

     һ��ֱ�ӵİ취��ʹ�û��ݷ���
     ����Ӧ����������״̬����ǰ���֣��õ���������Ҫ�Ĳ������Լ�һ����������������ʾ��ǰ�Ѿ����ʹ���Щλ������״̬��0,0,0����ʼ��������ֱ��10^nΪֹ����Ч���ָ�����
     �������Ҳ����ʹ�ö�̬�滮������һЩ��ϵ�֪ʶ����⡣
     f(k) = ����Ϊk�Ĳ��ظ���λ�����ָ�����
     f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [�׸�������9����Ϊ���ֲ��ܴ�0��ʼ].
     */
    public class Solution {
        public int countNumbersWithUniqueDigits(int n) {
            n = Math.min(n,10);
            int[] dp = new int[n+1];
            dp[0] = 1;
            for(int i = 1;i<=n;i++){
                dp[i] = 9;
                for(int x = 9; x >= 9 - i + 2;x--){
                    dp[i] *= x;
                }
            }
            int ans = 0;
            for(int i= 0;i<dp.length;i++) ans += dp[i];
            return ans;
        }
    }

    /**
     * general term formula
     * n=0  f(0) = 2(0,1)
     * n=1  f(1) = 10
     * n=2  f(2) = 10 + 9 * 9
     * n=3  f(3) = 10 + 9 * 9 + 9 * 9 * 8
     * n=4  f(4) = 10 + 9 * 9 + 9 * 9 * 8 + 9 * 9 * 8 * 7
     * ...
     * n=10 f(10)= f(9) + 9*(9!)
     * ...
     * f(n) = f(10), when n>10

*/
    public class Solution2 {
        public int countNumbersWithUniqueDigits(int n) {

            if(n==0) return 1;
            if(n==1) return 10;
            n = Math.min(10, n);
            int ans = 10;
            int base = 9;
            for(int i = 1; i < n; i++) {
                base *= 10-i;
                ans += base;
            }
            return ans;
        }
    }
}
