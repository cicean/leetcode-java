package Dropbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/26/2018.
 */
public class BuySoda {

    /**
     * // Let's assume sodas are sold in packages of 1, 2, 6, 12, 24.
     // e.g. if N = 10, you could buy 1 x 10,  6 + 2 + 2, 6 + 1 + 1 + 1 + 1, ....
     // input : N
     // output : all possible combinations, for instance N=10 { {1,1,...,1}, {6,2,2}, {6,1,1,1,1}, {6,2,1,1}, {2,1,..,1}, .... }
     (don't include {2,2,6} since we have {6,2,2})
     */

    public int buySoda(int[] list, int N) {
        int[] dp = new int[N + 1];
        dp[0] = 1;
        for (int i = 0; i < list.length; i++) {
            for (int j = list[i]; j <= N; j++) {
                dp[j] = dp[j - list[i]] + dp[j];
            }
        }

        return dp[N];
    }

    public List<List<Integer>> buySodaII(int[] list, int N) {
        List<List<Integer>> results = new ArrayList<>();

        if (list == null || list.length == 0) {
            return results;
        }

        helper(list, 0, N, new ArrayList<Integer>(), results);
        return results;
    }

    private void helper(int[] list, int start, int N,
                        List<Integer> sub, List<List<Integer>> results) {
        if (N == 0) {
            results.add(new ArrayList<Integer>(sub));
            return;
        }

        for (int i = start; i < list.length && N >= list[i]; i++) {
            sub.add(list[i]);
            helper(list, i, N - list[i], sub, results);
            sub.remove(sub.size() - 1);
        }
    }
}
