/**
 * 853. Car Fleet
 * Medium
 *
 * 388
 *
 * 185
 *
 * Add to List
 *
 * Share
 * N cars are going to the same destination along a one lane road.  The destination is target miles away.
 *
 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
 *
 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
 *
 * The distance between these two cars is ignored - they are assumed to have the same position.
 *
 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
 *
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 *
 *
 * How many car fleets will arrive at the destination?
 *
 *
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 and 8 become a fleet, meeting each other at 12.
 * The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 and 3 become a fleet, meeting each other at 6.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Note:
 *
 * 0 <= N <= 10 ^ 4
 * 0 < target <= 10 ^ 6
 * 0 < speed[i] <= 10 ^ 6
 * 0 <= position[i] < target
 * All initial positions are different.
 * Accepted
 * 24,498
 * Submissions
 * 58,151
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * lee215
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 */
public class CarFleet {
    /**
     * Approach 1: Sort
     * Intuition
     *
     * Call the "lead fleet" the fleet furthest in position.
     *
     * If the car S (Second) behind the lead car F (First) would arrive earlier, then S forms a fleet with the lead car F. Otherwise, fleet F is final as no car can catch up to it - cars behind S would form fleets with S, never F.
     *
     * Algorithm
     *
     * A car is a (position, speed) which implies some arrival time (target - position) / speed. Sort the cars by position.
     *
     * Now apply the above reasoning - if the lead fleet drives away, then count it and continue. Otherwise, merge the fleets and continue.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N \log N)O(NlogN), where NN is the number of cars. The complexity is dominated by the sorting operation.
     *
     * Space Complexity: O(N)O(N), the space used to store information about the cars.
     */

    class Solution {
        public int carFleet(int target, int[] position, int[] speed) {
            int N = position.length;
            Car[] cars = new Car[N];
            for (int i = 0; i < N; ++i)
                cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
            Arrays.sort(cars, (a, b) -> Integer.compare(a.position, b.position));

            int ans = 0, t = N;
            while (--t > 0) {
                if (cars[t].time < cars[t-1].time) ans++; //if cars[t] arrives sooner, it can't be caught
                else cars[t-1] = cars[t]; //else, cars[t-1] arrives at same time as cars[t]
            }

            return ans + (t == 0 ? 1 : 0); //lone car is fleet (if it exists)
        }
    }

    class Car {
        int position;
        double time;
        Car(int p, double t) {
            position = p;
            time = t;
        }
    }

    /**
     * Explanation
     * Sort cars by the start positions pos
     * Loop on each car from the end to the beginning
     * Calculate its time needed to arrive the target
     * cur records the current biggest time (the slowest)
     *
     * If another car needs less or equal time than cur,
     * it can catch up this car fleet.
     *
     * If another car needs more time,
     * it will be the new slowest car,
     * and becomes the new lead of a car fleet.
     *
     *
     * Complexity:
     * O(NlogN) Quick sort the cars by position. (Other sort can be applied)
     * O(N) One pass for all cars from the end to start (another direction also works).
     *
     * O(N) Space for sorted cars.
     * O(1) space is possible if we sort pos inplace.
     */
    class Solution {
        public int carFleet(int target, int[] pos, int[] speed) {
            int N = pos.length, res = 0;
            double[][] cars = new double[N][2];
            for (int i = 0; i < N; ++i)
                cars[i] = new double[] {pos[i], (double)(target - pos[i]) / speed[i]};
            Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
            double cur = 0;
            for (int i = N - 1; i >= 0 ; --i) {
                if (cars[i][1] > cur) {
                    cur = cars[i][1];
                    res++;
                }
            }
            return res;
        }
    }

    /**
     * Java, use TreeMap:
     */
    class Solution {
        public int carFleet(int target, int[] pos, int[] speed) {
            Map<Integer, Double> m = new TreeMap<>(Collections.reverseOrder());
            for (int i = 0; i < pos.length; ++i)
                m.put(pos[i], (double)(target - pos[i]) / speed[i]);
            int res = 0; double cur = 0;
            for (double time : m.values()) {
                if (time > cur) {
                    cur = time;
                    res++;
                }
            }
            return res;
        }
    }

    /**
     *
     */

    class Solution {
        public int carFleet(int target, int[] position, int[] speed) {
            if (position.length <= 1) {
                return position.length;
            }
            double[] time = new double[position.length];
            for (int i = 0; i < time.length; i++) {
                time[i] = (double) (target - position[i]) / speed[i];
            }
            qsort(position, time, 0, position.length - 1);
            int cnt = 1, car = position.length;
            while (--car > 0) {
                if (time[car] < time[car - 1]) {
                    cnt++;
                } else {
                    time[car - 1] = time[car];
                    position[car - 1] = position[car];
                }
            }
            return cnt;
        }

        public void qsort(int[] l1, double[] l2, int i, int j) {
            if (j <= i) {
                return;
            }
            int pivot = (i + j) / 2;
            swap(l1, pivot, j);
            swap(l2, pivot, j);
            int k = partition(l1, l2, i - 1, j, l1[j]);
            swap(l1, k, j);
            swap(l2, k, j);
            qsort(l1, l2, i, k - 1);
            qsort(l1, l2, k + 1, j);
        }

        public int partition(int[] l1, double[] l2, int l, int r, int pivot) {
            do {
                while (l1[++l] < pivot) ;
                while ((l < r) && (pivot < l1[--r])) ;
                swap(l1, l, r);
                swap(l2, l, r);
            } while (l < r);
            return l;
        }

        /**
         * Exchange A[I] and A[J].
         */
        private void swap(int[] a, int i, int j) {
            int swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

        private void swap(double[] a, int i, int j) {
            double swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

    }
}
