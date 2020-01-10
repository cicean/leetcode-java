import java.util.*;

/**
 * 957. Prison Cells After N Days
 * Medium
 *
 * 214
 *
 * 401
 *
 * Favorite
 *
 * Share
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Note:
 *
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 * Accepted
 * 25,891
 * Submissions
 * 67,810
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 23
 */

public class PrisonCellsAfterNDays {

    /**
     * Approach 1: Simulation
     * Intuition
     *
     * We simulate each day of the prison.
     *
     * Because there are at most 256 possible states for the prison, eventually the states repeat into a cycle rather quickly.
     * We can keep track of when the states repeat to find the period t of this cycle, and skip days in multiples of t.
     *
     * Algorithm
     *
     * Let's do a naive simulation, iterating one day at a time. For each day, we will decrement N, the number of days remaining,
     * and transform the state of the prison forward (state -> nextDay(state)).
     *
     * If we reach a state we have seen before, we know how many days ago it occurred, say t. Then, because of this cycle,
     * we can do N %= t. This ensures that our algorithm only needs O(2**{\text{cells.length}})O(2∗∗cells.length) steps.
     * Complexity Analysis
     *
     * Time Complexity: O(2^N)O(2
     * N
     *  ), where NN is the number of cells in the prison.
     *
     * Space Complexity: O(2^N * N)O(2
     * N
     *  ∗N).
     */

    class Solution {
        public int[] prisonAfterNDays(int[] cells, int N) {
            Map<Integer, Integer> seen = new HashMap();

            // state  = integer representing state of prison
            int state = 0;
            for (int i = 0; i < 8; ++i) {
                if (cells[i] > 0)
                    state ^= 1 << i;
            }

            // While days remaining, simulate a day
            while (N > 0) {
                // If this is a cycle, fast forward by
                // seen.get(state) - N, the period of the cycle.
                if (seen.containsKey(state)) {
                    N %= seen.get(state) - N;
                }
                seen.put(state, N);

                if (N >= 1) {
                    N--;
                    state = nextDay(state);
                }
            }

            // Convert the state back to the required answer.
            int[] ans = new int[8];
            for (int i = 0; i < 8; ++i) {
                if (((state >> i) & 1) > 0) {
                    ans[i] = 1;
                }
            }

            return ans;
        }

        public int nextDay(int state) {
            int ans = 0;

            // We only loop from 1 to 6 because 0 and 7 are impossible,
            // as those cells only have one neighbor.
            for (int i = 1; i <= 6; ++i) {
                if (((state >> (i-1)) & 1) == ((state >> (i+1)) & 1)) {
                    ans ^= 1 << i;
                }
            }

            return ans;
        }
    }
}
