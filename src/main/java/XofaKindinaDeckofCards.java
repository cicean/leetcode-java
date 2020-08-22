/**
 * 914. X of a Kind in a Deck of Cards
 * Easy
 *
 * 408
 *
 * 99
 *
 * Add to List
 *
 * Share
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 *
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 *
 *
 * Example 1:
 *
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * Example 2:
 *
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false´
 * Explanation: No possible partition.
 * Example 3:
 *
 * Input: deck = [1]
 * Output: false
 * Explanation: No possible partition.
 * Example 4:
 *
 * Input: deck = [1,1]
 * Output: true
 * Explanation: Possible partition [1,1].
 * Example 5:
 *
 * Input: deck = [1,1,2,2,2,2]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[2,2].
 *
 *
 * Constraints:
 *
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 * Accepted
 * 33,460
 * Submissions
 * 98,282
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * sayaan
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 4
 */
public class XofaKindinaDeckofCards {

    /**
     * Solution
     * Approach 1: Brute Force
     * Intuition
     *
     * We can try every possible X.
     *
     * Algorithm
     *
     * Since we divide the deck of N cards into say, K piles of X cards each, we must have N % X == 0.
     *
     * Then, say the deck has C_i copies of cards with number i. Each group with number i has X copies, so we must have C_i % X == 0. These are necessary and sufficient conditions.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2 \log \log N)O(N
     * 2
     *  loglogN), where NN is the number of cards. It is outside the scope of this article to prove that the number of divisors of NN is bounded by O(N \log \log N)O(NloglogN).
     *
     * Space Complexity: O(N)O(N).
     *
     */
    class Solution {
        public boolean hasGroupsSizeX(int[] deck) {
            int N = deck.length;
            int[] count = new int[10000];
            for (int c: deck)
                count[c]++;

            List<Integer> values = new ArrayList();
            for (int i = 0; i < 10000; ++i)
                if (count[i] > 0)
                    values.add(count[i]);

            search: for (int X = 2; X <= N; ++X)
                if (N % X == 0) {
                    for (int v: values)
                        if (v % X != 0)
                            continue search;
                    return true;
                }

            return false;
        }
    }
     /** Approach 2: Greatest Common Divisor
     * Intuition and Algorithm
     *
     * Again, say there are C_i cards of number i. These must be broken down into piles of X cards each, ie. C_i % X == 0 for all i.
     *
     * Thus, X must divide the greatest common divisor of C_i. If this greatest common divisor g is greater than 1, then X = g will satisfy. Otherwise, it won't.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N \log^2 N)O(Nlog
     * 2
     *  N), where NN is the number of votes. If there are C_iC
     * i
     * ​
     *   cards with number ii, then each gcd operation is naively O(\log^2 C_i)O(log
     * 2
     *  C
     * i
     * ​
     *  ). Better bounds exist, but are outside the scope of this article to develop.
     *
     * Space Complexity: O(N)O(N).
     */

     class Solution {
         public boolean hasGroupsSizeX(int[] deck) {
             int[] count = new int[10000];
             for (int c: deck)
                 count[c]++;

             int g = -1;
             for (int i = 0; i < 10000; ++i)
                 if (count[i] > 0) {
                     if (g == -1)
                         g = count[i];
                     else
                         g = gcd(g, count[i]);
                 }

             return g >= 2;
         }

         public int gcd(int x, int y) {
             return x == 0 ? y : gcd(y%x, x);
         }
     }

    class Solution {
        public boolean hasGroupsSizeX(int[] deck) {
            int max = deck[0];
            for (int d : deck)
                if (d > max)
                    max = d;

            int[] arr = new int[max + 1];
            for (int d : deck)
                arr[d]++;

            int count = 0, gcd = 0;
            for (int a : arr)
                if (a != 0) {
                    if (count == 0)
                        count = a;
                    else {
                        count = getGCD(count, a);
                        if (count == 1)
                            return false;
                    }
                }
            return count != 1;
        }

        private int getGCD(int i, int j) {
            while (i % j != 0) {
                int k = i % j;
                i = j;
                j = k;
            }
            return j;
        }
    }

    class Solution {
        public boolean hasGroupsSizeX(int[] deck) {
            Map<Integer, Integer> count = new HashMap<>();
            int res = 0;
            for (int i : deck) count.put(i, count.getOrDefault(i, 0) + 1);
            for (int i : count.values()) res = gcd(i, res);
            return res > 1;
        }

        public int gcd(int a, int b) {
            return b > 0 ? gcd(b, a % b) : a;
        }
    }
}
