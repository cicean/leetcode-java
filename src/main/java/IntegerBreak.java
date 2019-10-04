import java.util.Scanner;

/**
 * Created by cicean on 5/1/2016.
 * 343. Integer Break   My Submissions QuestionEditorial Solution
 Total Accepted: 6591 Total Submissions: 16467 Difficulty: Medium
 Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

 For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

 Note: you may assume that n is not less than 2.

 Hint:

 There is a simple O(n) solution to this problem.
 You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 ����:��������n��n>=2���������ֽ�Ϊ���ɸ������ĺͣ�����Щ�����ĳ˻����
 Solution:
 1.dfs
 2.DP

 */
public class IntegerBreak {

//    int res = 0;
//
//
//    public int integerBreak (int n) {
//
//
//
//        if (n < 2 || n > Integer.MAX_VALUE) {
//            return 0;
//        }
//
//
//
//        ArrayList<Integer> integers = new ArrayList<>();
//
//        integerBreakdfs(n,integers);
//
//
//        return res;
//    }
//
//    public void integerBreakdfs(int n, ArrayList<Integer> a) {
//        if (n == 0) {
//            if (a.size() < 2) {
//                return;
//            }
//
//            int tmpInteger = 1;
//            for (int i : a) {
//                tmpInteger *= i;
//            }
//
//            res = Math.max(tmpInteger, res);
//
//            return;
//        }
//
//        for (int i =1; i <= n+1; i++) {
//            a.add(i);
//            integerBreakdfs((n-i), a);
//        }
//
//    }

    //ˮˮ��O(n^2)
    public int integerBreak_1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i ++) {
            for(int j = 1; j < i; j ++) {
                dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j])));
            }
        }
        return dp[n];
    }

    /**
     * DP
     ���Ӷ�
     O(N) ʱ�� O(N) �ռ�

     ˼·
     dp[i]��ʾi������ֲ�ֺ�����˻������Ĵ���dp[n].
     ��ʼ��:
     dp[0] = 0 //�������ν
     dp[1] = 1 //����1���������˷�
     dp[2] = 1,
     dp[i] = Max(dp[k] * dp[i - k], k * (i - k), dp[k] * (i - k), k * (dp[i - k])), kȡֵ��[1, i / 2]������
     * DP beat 40.83% O(n)
     * @param args
     */
    public class Solution {
        public int integerBreak(int n) {
            if (n < 4) return n-1;
            int[] dp = new int[n+1];
            dp[2] = 2;
            dp[3] = 3;
            for (int i = 4; i <= n; i++) {
                dp[i] = Math.max(3*dp[i-3], 2*dp[i-2]);
            }
            return dp[n];
        }
    }

    //beat 40.83% O(log(n))
    public class Solution2 {
        public int integerBreak(int n) {
            if (n < 4) return n-1;
            else if(n%3 == 0)
                return (int)Math.pow(3, n/3);
            else if(n%3 == 1)
                return 4*(int)Math.pow(3, (n-4)/3);
            else
                return 2*(int)Math.pow(3, n/3);
        }
    }

    //A more mathematic solution
    //We can prove that when N is even, N/2 * N/2 is largest; when N is odd, (N - 1)/2 * (N + 1)/2 is the largest
    //Also we have N/2 * N/2 > N --> N > 4
    //             (N - 1)/2 * (N + 1)/2 > N --> N >= 5
    //So when N > 4, we can do the calculation
    //O(n)
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }
        result *= n;
        return result;
    }




    public static void main(String[] args) {
        // TODO Auto-generated method stub

        IntegerBreak slt = new IntegerBreak();
        Scanner sc = new Scanner(System.in);
        System.out.println("Pleas input the Integer ");
        int n = sc.nextInt();
       // slt.integerBreak(n);
        int res = slt.integerBreak_1(n);

        System.out.println("the max = " + res);

    }


}
