package Airbnb;
import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 */
public class CollatzConjecture {

    /**
     * 题目是给你公式，比如偶数的话除2，奇数的话就变成3*n+1，对于任何一个正数，
     * 数学猜想是最终他会变成1。每变一步步数加1，给一个上限，让找出范围内最长步数。
     */

    Map<Integer, Integer> map = new HashMap<>();

    public int findLongestSteps(int num) {
        if (num < 1) return 0;
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int t = findSteps(i);
            map.put(i, t);
            res = Math.max(res, t);
        }
        return res;
    }

    private int findSteps(int num) {
        if (num <= 1) {
            return 1;
        }

        if (map.containsKey(num)) {
            return map.get(num);
        }

        if (num % 2 == 0) {
            return 1 + findSteps(num / 2);
        }

        return 1 + findSteps(3 * num + 1);
    }

}
