import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by cicean on 8/29/2016.
 * 313. Super Ugly Number  QuestionEditorial Solution  My Submissions
 Total Accepted: 21586 Total Submissions: 59833 Difficulty: Medium
 Write a program to find the nth super ugly number.

 Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

 Note:
 (1) 1 is a super ugly number for any given primes.
 (2) The given numbers in primes are in ascending order.
 (3) 0 < k �� 100, 0 < n �� 106, 0 < primes[i] < 1000.

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Math Heap
 Hide Similar Problems (M) Ugly Number II
 ���⣺

 ��������Primes , �������n��super ugly number��

 super ugly number����Ϊ��������������ȫ������primes�С� ע��1Ϊ�����super ugly number��

 ˼·��

 �� leetcode Ugly Number  II ˼·һ����Ҫʹ��super ugly number ��©������ô��ÿ������ȥ�˵�һ����
 ��ǰ���ӳ˻�����С�󣬳�����һ����..�Դ����ơ�

 */
public class SuperUglyNumber {
    /**
     * ��Ugly Number IIһ����˼·��

     ��̬�滮��ugly number�϶���֮ǰ��ugly number����primes�е�����һ�����õ��ġ�

     �������dp�����У�ÿһ��dp�е�����Ҫ����primes�е�����Ѱ�����еĽ����Ҳ����˵primes�е�ÿһ��������Ҫһ��������¼λ�á�

     ������˵primes : [2, 5, 7]��

     һ��ʼ2, 5, 7��ָ��0��λ�á�

     ÿһ��ѭ������2, 5, 7�뵱ǰλ�õ�����ˣ�����С�ķŽ�dp�����С�
     */

    public class Solution {
        /**
         * Theoretically O(kN)
         *
         * @param n      a positive integer
         * @param primes the given prime list
         * @return the nth super ugly number
         */
        public int nthSuperUglyNumber(int n, int[] primes) {
            int[] times = new int[primes.length];
            int[] uglys = new int[n];
            uglys[0] = 1;

            for (int i = 1; i < n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < primes.length; j++) {
                    min = Math.min(min, primes[j] * uglys[times[j]]);
                }
                uglys[i] = min;

                for (int j = 0; j < times.length; j++) {
                    if (uglys[times[j]] * primes[j] == min) {
                        times[j]++;
                    }
                }
            }
            return uglys[n - 1];
        }
    }

    /**
     * If you look at the above solution,
     * it has redundant multiplication can be avoided,
     * and also two for loops can be consolidated into one.
     * This trade-off space for speed. 23 ms, Theoretically O(kN)
     */

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);

        int next = 1;
        for (int i = 0; i < n; i++) {
            ugly[i] = next;

            next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                //skip duplicate and avoid extra multiplication
                if (val[j] == ugly[i]) val[j] = ugly[idx[j]++] * primes[j];
                //find next ugly number
                next = Math.min(next, val[j]);
            }
        }

        return ugly[n - 1];
    }


    /**
         * Can we do better? Theoretically yes,
         * by keep the one candidates for each prime in a heap,
         * it can improve the theoretical bound to O( log(k)N ),
         * but in reality it's 58 ms.
         * I think it's the result of using higher level object instead of primitive.
         * Can be improved by writing an index heap
         */

        public class Solution2 {
            public int nthSuperUglyNumber(int n, int[] primes) {
                int[] ugly = new int[n];

                PriorityQueue<Num> pq = new PriorityQueue<>();
                for (int i = 0; i < primes.length; i++) pq.add(new Num(primes[i], 1, primes[i]));
                ugly[0] = 1;

                for (int i = 1; i < n; i++) {
                    ugly[i] = pq.peek().val;
                    while (pq.peek().val == ugly[i]) {
                        Num nxt = pq.poll();
                        pq.add(new Num(nxt.p * ugly[nxt.idx], nxt.idx + 1, nxt.p));
                    }
                }

                return ugly[n - 1];
            }

            private class Num implements Comparable<Num> {
                int val;
                int idx;
                int p;

                public Num(int val, int idx, int p) {
                    this.val = val;
                    this.idx = idx;
                    this.p = p;
                }

                @Override
                public int compareTo(Num that) {
                    return this.val - that.val;
                }
            }
        }




}
