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
