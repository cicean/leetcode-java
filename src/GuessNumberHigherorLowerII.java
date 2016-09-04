/**
 * Created by cicean on 9/1/2016.
 * 375. Guess Number Higher or Lower II  QuestionEditorial Solution  My Submissions
 Total Accepted: 6487 Total Submissions: 20925 Difficulty: Medium
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

 However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

 Example:

 n = 10, I pick 8.

 First round:  You guess 5, I tell you that it's higher. You pay $5.
 Second round: You guess 7, I tell you that it's higher. You pay $7.
 Third round:  You guess 9, I tell you that it's lower. You pay $9.

 Game over. 8 is the number I picked.

 You end up paying $5 + $7 + $9 = $21.
 Given a particular n �� 1, find out how much money you need to have to guarantee a win.

 Hint:

 The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.
 Take a small example (n = 3). What do you end up paying in the worst case?
 Check out this article if you're still stuck.
 The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming.
 As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the worst-case loss?
 *
 * ���⣺����һ������n����1~n��ѡȡһ������y����¡�
 * �ڲµĹ����У���������㵱ǰ�µ�����x�Ǳ�y����С���´�Ļ���
 * ��Ҫ��$x������n,����������Ҫ���ٵ�Ǯ��ȷ�����ǲ³�y�أ�
 */
public class GuessNumberHigherorLowerII {

    /**
     * ˼·���� 374 Guess Number Higher or Lower �У����ǲ��ö��ַ������в��ң������Ⲣ�����ö��ַ���

     ����Ҫ�������ڲ²�����yδ֪������£�1~n����һ��������Ҫ����������������֧�����ٵ�Ǯ��Ҳ����˵Ҫ��������y�������

     ���Ǽٶ�ѡ����һ���������x����1<=x<=n && x!=y ����ô��֪��������Ӧ�ô�[1,x-1 ] ����[x+1,n]�н��в��ҡ� ���������Ѿ������[1,x-1] �� [x+1,n]�������⣬���ǽ����ʾΪsolve(L,x-1) ��solve(x+1,n)����ô����Ӧ��ѡ��max(solve(L,x-1),solve(x+1,n)) ���������������µ���ʧ���ܵ���ʧ���� f(x) = x + max(solve(L,x-1),solve(x+1,n))

     ��ô��x��1~n���б�����ȡʹ�� f(x) �ﵽ��С����ȷ����������С����ʧ��Ҳ�������ǳ�ʼӦ��ѡ���ĸ�����

     �����˵����ʵ��һ���Զ����µĹ��̣�Top-down���������õݹ�������������׵õ����µĴ��루�������˼��仯��������
     */

    public class Solution {
        public int getMoneyAmount(int n) {
            int[][] dp = new int[n+1][n+1];
            return solve(dp, 1, n);
        }
        int solve(int[][] dp, int L, int R) {
            if (L >= R) return 0;
            if (dp[L][R] != 0) return dp[L][R];
            dp[L][R] = 0x7FFFFFFF;
            for (int i = L; i <= R; i++) {
                dp[L][R] = Math.min(dp[L][R], i + Math.max(solve(dp,L,i-1),solve(dp,i+1,R)));
            }
            return dp[L][R];
        }
    }

}
