import java.util.*;

/**
 * 857. Minimum Cost to Hire K Workers
 * Hard
 *
 * 721
 *
 * 72
 *
 * Add to List
 *
 * Share
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 *
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 *
 * Note:
 *
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 * Accepted
 * 24,827
 * Submissions
 * 50,741
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * mj_coder
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 */
public class MinimumCosttoHireKWorkers {

    /**
     * Solution
     * Approach 1: Greedy
     * Intuition
     *
     * At least one worker will be paid their minimum wage expectation. If not, we could scale all payments down by some factor and still keep everyone earning more than their wage expectation.
     *
     * Algorithm
     *
     * For each captain worker that will be paid their minimum wage expectation, let's calculate the cost of hiring K workers where each point of quality is worth wage[captain] / quality[captain] dollars. With this approach, the remaining implementation is straightforward.
     *
     * Note that this algorithm would not be efficient enough to pass larger test cases.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2 \log N)O(N
     * 2
     *  logN), where NN is the number of workers.
     *
     * Space Complexity: O(N)O(N).
     *
     */

    class Solution {
        public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            int N = quality.length;
            double ans = 1e9;

            for (int captain = 0; captain < N; ++captain) {
                // Must pay at least wage[captain] / quality[captain] per qual
                double factor = (double) wage[captain] / quality[captain];
                double prices[] = new double[N];
                int t = 0;
                for (int worker = 0; worker < N; ++worker) {
                    double price = factor * quality[worker];
                    if (price < wage[worker]) continue;
                    prices[t++] = price;
                }

                if (t < K) continue;
                Arrays.sort(prices, 0, t);
                double cand = 0;
                for (int i = 0; i < K; ++i)
                    cand += prices[i];
                ans = Math.min(ans, cand);
            }

            return ans;
        }
    }

     /** Approach 2: Heap
     * Intuition
     *
     * As in Approach #1, at least one worker is paid their minimum wage expectation.
     *
     * Additionally, every worker has some minimum ratio of dollars to quality that they demand. For example, if wage[0] = 100 and quality[0] = 20, then the ratio for worker 0 is 5.0.
     *
     * The key insight is to iterate over the ratio. Let's say we hire workers with a ratio R or lower. Then, we would want to know the K workers with the lowest quality, and the sum of that quality. We can use a heap to maintain these variables.
     *
     * Algorithm
     *
     * Maintain a max heap of quality. (We're using a minheap, with negative values.) We'll also maintain sumq, the sum of this heap.
     *
     * For each worker in order of ratio, we know all currently considered workers have lower ratio. (This worker will be the 'captain', as described in Approach #1.) We calculate the candidate answer as this ratio times the sum of the smallest K workers in quality.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N \log N)O(NlogN), where NN is the number of workers.
     *
     * Space Complexity: O(N)O(N).
     */
     class Solution_2 {
         public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
             int N = quality.length;
             Worker[] workers = new Worker[N];
             for (int i = 0; i < N; ++i)
                 workers[i] = new Worker(quality[i], wage[i]);
             Arrays.sort(workers);

             double ans = 1e9;
             int sumq = 0;
             PriorityQueue<Integer> pool = new PriorityQueue();
             for (Worker worker: workers) {
                 pool.offer(-worker.quality);
                 sumq += worker.quality;
                 if (pool.size() > K)
                     sumq += pool.poll();
                 if (pool.size() == K)
                     ans = Math.min(ans, sumq * worker.ratio());
             }

             return ans;
         }
     }

    class Worker implements Comparable<Worker> {
        public int quality, wage;
        public Worker(int q, int w) {
            quality = q;
            wage = w;
        }

        public double ratio() {
            return (double) wage / quality;
        }

        public int compareTo(Worker other) {
            return Double.compare(ratio(), other.ratio());
        }
    }

    /**
     * Let's read description first and figure out the two rules:
     *
     * "1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group."
     * So for any two workers in the paid group,
     * we have wage[i] : wage[j] = quality[i] : quality[j]
     * So we have wage[i] : quality[i] = wage[j] : quality[j]
     * We pay wage to every worker in the group with the same ratio compared to his own quality.
     *
     * "2. Every worker in the paid group must be paid at least their minimum wage expectation."
     * For every worker, he has an expected ratio of wage compared to his quality.
     *
     * So to minimize the total wage, we want a small ratio.
     * So we sort all workers with their expected ratio, and pick up K first worker.
     * Now we have a minimum possible ratio for K worker and we their total quality.
     *
     * As we pick up next worker with bigger ratio, we increase the ratio for whole group.
     * Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
     * We calculate the current ratio * total quality = total wage for this group.
     *
     * We redo the process and we can find the minimum total wage.
     * Because workers are sorted by ratio of wage/quality.
     * For every ratio, we find the minimum possible total quality of K workers.
     *
     * Time Complexity
     * O(NlogN) for sort.
     * O(NlogK) for priority queue.
     */
    class Solution_3 {
        public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            int n = quality.length;
            double[][] workers = new double[n][2];
            for(int i=0;i<n;i++){
                workers[i][0] = (double) wage[i]/quality[i];
                workers[i][1] = (double) quality[i];
            }
            Arrays.sort(workers, (o1,o2) -> Double.compare(o1[0], o2[0]));

            double res = Double.MAX_VALUE;
            int qualitySum = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2-o1);
            for(double[] worker: workers){
                qualitySum += worker[1];
                pq.offer((int)worker[1]);
                if(pq.size()>K) qualitySum -= pq.poll();
                if(pq.size()==K) res = Math.min(res, worker[0]*qualitySum);
            }

            return res;

        }
    }
}
