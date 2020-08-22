/**
 * 818. Race Car
 * Hard
 *
 * 434
 *
 * 49
 *
 * Add to List
 *
 * Share
 * Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
 *
 * Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
 *
 * When you get an instruction "A", your car does the following: position += speed, speed *= 2.
 *
 * When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
 *
 * For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
 *
 * Now for some target position, say the length of the shortest sequence of instructions to get there.
 *
 * Example 1:
 * Input:
 * target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0->1->3.
 * Example 2:
 * Input:
 * target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0->1->3->7->7->6.
 *
 *
 * Note:
 *
 * 1 <= target <= 10000.
 * Accepted
 * 16,820
 * Submissions
 * 44,419
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 */
public class RaceCar {

    /**
     * Approach Framework
     * Explanation
     *
     * Let A^kA
     * k
     *   denote the command A A A \cdots AAAA⋯A (k times).
     *
     * Starting with an "R" command doesn't help, and as the final sequence does not end on an "R", so we have some sequence like R A^{k_1} R A^{k_2} \cdots R A^{k_n}RA
     * k
     * 1
     * ​
     *
     *  RA
     * k
     * 2
     * ​
     *
     *  ⋯RA
     * k
     * n
     * ​
     *
     *   which could be instead A^{k_2} R A^{k_3} R \cdots A^{k_n} R R A^{k_1}A
     * k
     * 2
     * ​
     *
     *  RA
     * k
     * 3
     * ​
     *
     *  R⋯A
     * k
     * n
     * ​
     *
     *  RRA
     * k
     * 1
     * ​
     *
     *   for the same final position of the car. (Here, k_i \geq 0k
     * i
     * ​
     *  ≥0, where A^0A
     * 0
     *   means no command.)
     *
     * So let's suppose our command is always of the form A^{k_1} R A^{k_2} R \cdots A^{k_n}A
     * k
     * 1
     * ​
     *
     *  RA
     * k
     * 2
     * ​
     *
     *  R⋯A
     * k
     * n
     * ​
     *
     *  . Note that under such a command, the car will move to final position (2^{k_1} - 1) - (2^{k_2} - 1) + (2^{k_3} - 1) - \cdots(2
     * k
     * 1
     * ​
     *
     *  −1)−(2
     * k
     * 2
     * ​
     *
     *  −1)+(2
     * k
     * 3
     * ​
     *
     *  −1)−⋯.
     *
     * Without loss of generality, we can say that (k_ik
     * i
     * ​
     *  , ii odd) is monotone decreasing, and (k_ik
     * i
     * ​
     *  , ii even) is also monotone decreasing.
     *
     * Also because terms will cancel out, we can also ignore the possibility that k_i = k_jk
     * i
     * ​
     *  =k
     * j
     * ​
     *   (for i, ji,j with different parity).
     *
     * A key claim is that k_ik
     * i
     * ​
     *   is bounded by a+1a+1, where aa is the smallest integer such that 2^a \geq \text{target}2
     * a
     *  ≥target - basically, if you drive past the target, you don't need to keep driving. This is because it adds another power of two (as 2^{k_i} - 1 = \sum_{j < k_i} 2^j2
     * k
     * i
     * ​
     *
     *  −1=∑
     * j<k
     * i
     * ​
     *
     * ​
     *  2
     * j
     *  ) to the position that must get erased by one or more negative terms later (in whole or in part), as it is not part of the target.
     *
     * Approach #1: Dijkstra's [Accepted]
     * Intuition
     *
     * With some target, we have different moves we can perform (such as k_1 = 0, 1, 2, \cdotsk
     * 1
     * ​
     *  =0,1,2,⋯, using the notation from our Approach Framework), with different costs.
     *
     * This is an ideal setup for Dijkstra's algorithm, which can help us find the shortest cost path in a weighted graph.
     *
     * Algorithm
     *
     * Dijkstra's algorithm uses a priority queue to continually searches the path with the lowest cost to destination, so that when we reach the target, we know it must have been through the lowest cost path. Refer to this link for more detail.
     *
     * Back to the problem, as described above, we have some barrier where we are guaranteed to never cross. We will also handle negative targets; in total we will have 2 * barrier + 1 nodes.
     *
     * After, we could move walk = 2**k - 1 steps for a cost of k + 1 (the 1 is to reverse). If we reach our destination exactly, we don't need the R, so it is just k steps.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(T \log T)O(TlogT). There are O(T)O(T) nodes, we process each one using O(\log T)O(logT) work (both popping from the heap and adding the edges).
     *
     * Space Complexity: O(T)O(T).
     */
    class Solution {
        public int racecar(int target) {
            int K = 33 - Integer.numberOfLeadingZeros(target - 1);
            int barrier = 1 << K;
            int[] dist = new int[2 * barrier + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[target] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<Node>(
                    (a, b) -> a.steps - b.steps);
            pq.offer(new Node(0, target));

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int steps = node.steps, targ1 = node.target;
                if (dist[Math.floorMod(targ1, dist.length)] > steps) continue;

                for (int k = 0; k <= K; ++k) {
                    int walk = (1 << k) - 1;
                    int targ2 = walk - targ1;
                    int steps2 = steps + k + (targ2 != 0 ? 1 : 0);

                    if (Math.abs(targ2) <= barrier && steps2 < dist[Math.floorMod(targ2, dist.length)]) {
                        pq.offer(new Node(steps2, targ2));
                        dist[Math.floorMod(targ2, dist.length)] = steps2;
                    }
                }
            }

            return dist[0];
        }
    }

    class Node {
        int steps, target;
        Node(int s, int t) {
            steps = s;
            target = t;
        }
    }

     /** Approach #2: Dynamic Programming [Accepted]
     * Intuition and Algorithm
     *
     * As in our Approach Framework, we've framed the problem as a series of moves k_ik
     * i
     * ​
     *  .
     *
     * Now say we have some target 2**(k-1) <= t < 2**k and we want to know the cost to go there, if we know all the other costs dp[u] (for u < t).
     *
     * If t == 2**k - 1, the cost is just k: we use the command A^kA
     * k
     *  , and clearly we can't do better.
     *
     * Otherwise, we might drive without crossing the target for a position change of 2^{k-1} - 2**j2
     * k−1
     *  −2∗∗j, by the command A^{k-1} R A^{j} RA
     * k−1
     *  RA
     * j
     *  R, for a total cost of k - 1 + j + 2k−1+j+2.
     *
     * Finally, we might drive 2^k - 12
     * k
     *  −1 which crosses the target, by the command A^k RA
     * k
     *  R, for a total cost of k + 1k+1.
     *
     * We can use dynamic programming together with the above recurrence to implement the code below.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(T \log T)O(TlogT). Each node i does \log ilogi work.
     *
     * Space Complexity: O(T)O(T).
     */

    class Solution {
        public int racecar(int target) {
            int[] dp = new int[target + 3];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0; dp[1] = 1; dp[2] = 4;

            for (int t = 3; t <= target; ++t) {
                int k = 32 - Integer.numberOfLeadingZeros(t);
                if (t == (1<<k) - 1) {
                    dp[t] = k;
                    continue;
                }
                for (int j = 0; j < k-1; ++j)
                    dp[t] = Math.min(dp[t], dp[t - (1<<(k-1)) + (1<<j)] + k-1 + j + 2);
                if ((1<<k) - 1 - t < t)
                    dp[t] = Math.min(dp[t], dp[(1<<k) - 1 - t] + k + 1);
            }

            return dp[target];
        }
    }

    class Solution {
        int[] dp = new int[10001];
        public int racecar(int t) {
            if (dp[t] > 0) return dp[t];
            int n = (int)(Math.log(t) / Math.log(2)) + 1;
            if (1 << n == t + 1) dp[t] = n;
            else {
                dp[t] = racecar((1 << n) - 1 - t) + n + 1;
                for (int m = 0; m < n - 1; ++m)
                    dp[t] = Math.min(dp[t], racecar(t - (1 << (n - 1)) + (1 << m)) + n + m + 1);
            }
            return dp[t];
        }
    }
}
