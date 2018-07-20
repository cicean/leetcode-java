package JingChi;

import java.util.*;

public class ProbabilityGettingleastKheadsinNtossesCoins {

    //native
    /**
     * Probability of getting at least K heads in N tosses of Coins
     * Given N number of coins, the task is to find probability of getting at least K number of heads after tossing all the N coins simultaneously.
     *
     * Example :
     *
     * Suppose we have 3 unbiased coins and we have to
     * find the probability of getting at least 2 heads,
     * so there are 23 = 8 ways to toss these
     * coins, i.e.,
     * HHH, HHT, HTH, HTT, THH, THT, TTH, TTT
     *
     * Out of which there are 4 set which contain at
     * least 2 Heads i.e.,
     * HHH, HHT, HH, THH
     *
     * So the probability is 4/8 or 0.5
     * https://www.geeksforgeeks.org/probability-getting-least-k-heads-n-tosses-coins/
     */

    //A Naive approach is to store the value of factorial in dp[] array and call it directly whenever it is required.
    // But the problem of this approach is that we can only able to store it up to certain value,
    // after that it will lead to overflow.
    //
    //Below is the implementation of above approach

    private double fact[];

    public void precompute()
    {
        // Preprocess all factorial only upto 19,
        // as after that it will overflow
        fact[0] = fact[1] = 1;

        for (int i = 2; i < 20; ++i)
            fact[i] = fact[i - 1] * i;
    }

    public double probability(int k, int n)
    {
        fact = new double[100];
        precompute();
        double ans = 0;
        for (int i = k; i <= n; ++ i)

            // Probability of getting exactly i
            // heads out of n heads
            ans += fact[n] / (fact[i] * fact[n-i]);

        // Note: 1 << n = pow(2, n)
        ans = ans / (1 << n);
        return ans;
    }

    /**
     * Method 2 (Dynamic Programming and Log)
     * Another way is to use Dynamic programming and logarithm. log() is indeed useful to store the factorial
     * of any number without worrying about overflow. Let’s see how we use it:
     *
     * At first let see how n! can be written.
     * n! = n * (n-1) * (n-2) * (n-3) * ... * 3 * 2 * 1
     *
     * Now take log on base 2 both the sides as:
     * => log(n!) = log(n) + log(n-1) + log(n-2) + ... + log(3)
     *          + log(2) + log(1)
     *
     * Now whenever we need to find the factorial of any number, we can use
     * this precomputed value. For example:
     * Suppose if we want to find the value of nCi which can be written as:
     * => nCi = n! / (i! * (n-i)! )
     *
     * Taking log2() both sides as:
     * => log2 (nCi) = log2 ( n! / (i! * (n-i)! ) )
     * => log2 (nCi) = log2 ( n! ) - log2(i!) - log2( (n-i)! )  `
     *
     * Putting dp[num] = log2 (num!), we get:
     * => log2 (nCi) = dp[n] - dp[i] - dp[n-i]
     *
     * But as we see in above relation there is an extra factor of 2n which
     * tells the probability of getting i heads, so
     * => log2 (2n) = n.
     *
     * We will subtract this n from above result to get the final answer:
     * => Pi (log2 (nCi)) = dp[n] - dp[i] - dp[n-i] - n
     *
     * Now: Pi (nCi) = 2 dp[n] - dp[i] - dp[n-i] - n
     *
     * Tada! Now the questions boils down the summation of Pi for all i in
     * [k, n] will yield the answer which can be calculated easily without
     * overflow.
     */

    private static int MAX = 100001;
    //dp[i] is going to store Log ( i !) in base 2
    private double[] dp = new double[MAX];

    public double probability_1 (int k, int n) {
        double ans = 0; // Initialize result

        if (n == 0 || k == 0) return 0;

        if (n == 1 || k == 1) return 0.5;

        // Iterate from k heads to n heads
        for (int i=k; i <= n; ++i)
        {
            double res = dp[n] - dp[i] - dp[n-i] - n;
            ans += Math.pow(2.0, res);
        }

        return ans;
    }

    void precompute_1()
    {
        // Preprocess all the logarithm value on base 2
        dp[0] = Integer.MAX_VALUE;
        dp[1] = 0;
        for (int i=2; i < MAX; ++i)
            dp[i] = Math.log(i) / Math.log(2) + dp[i-1];

    }
}
