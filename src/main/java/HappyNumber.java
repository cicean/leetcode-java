import java.util.*;


/**
 * 202	Happy Number	31.4%	Easy
 * 0.Problem:
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the
 * sum of the squares of its digits, and repeat the process until
 * the number equals 1 (where it will stay), or it loops endlessly
 * in a cycle which does not include 1. Those numbers for which
 * this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * <p>
 * 1.Refer.:
 */

public class HappyNumber {

    public boolean isHappy(int n) {
        Map<Integer, Boolean> traverseMap = new HashMap<Integer, Boolean>();
        int res = n;
        while (true) {
            res = assistIsHappy(res);
            if (1 == res) return true;
            if (traverseMap.containsKey(res)) return false;
            traverseMap.put(res, true);
        }
    }

    public int assistIsHappy(int n) {
        int res = 0;
        while (0 != n) {
            int temp = n % 10;
            n = n / 10;
            res = res + temp * temp;
        }
        return res;
    }

    public boolean isHappy_(int n) {
        Set<Integer> inLoop = new HashSet<Integer>();
        int squareSum, remain;
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1)
                return true;
            else
                n = squareSum;

        }
        return false;

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HappyNumber slt = new HappyNumber();
        for (int i = 1; i < 100; i++) {
            if (slt.isHappy(i) == true) {
                System.out.println(i + " is Happy number. ");
            } else {
                System.out.println(i + " is not Happy number ");
            }
        }
    }

}
