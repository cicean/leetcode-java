import java.util.*;

/**
 * 1349. Maximum Students Taking Exam
 * Hard
 *
 * 179
 *
 * 6
 *
 * Add to List
 *
 * Share
 * Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.
 *
 * Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..
 *
 * Students must be placed in seats in good condition.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: seats = [["#",".","#","#",".","#"],
 *                 [".","#","#","#","#","."],
 *                 ["#",".","#","#",".","#"]]
 * Output: 4
 * Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.
 * Example 2:
 *
 * Input: seats = [[".","#"],
 *                 ["#","#"],
 *                 ["#","."],
 *                 ["#","#"],
 *                 [".","#"]]
 * Output: 3
 * Explanation: Place all students in available seats.
 *
 * Example 3:
 *
 * Input: seats = [["#",".",".",".","#"],
 *                 [".","#",".","#","."],
 *                 [".",".","#",".","."],
 *                 [".","#",".","#","."],
 *                 ["#",".",".",".","#"]]
 * Output: 10
 * Explanation: Place students in available seats in column 1, 3 and 5.
 *
 *
 * Constraints:
 *
 * seats contains only characters '.' and'#'.
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 * Accepted
 * 3,349
 * Submissions
 * 9,090
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * thuss
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * SAP
 * |
 * 2
 * Students in row i only can see exams in row i+1.
 * Use Dynamic programming to compute the result given a (current row, bitmask people seated in previous row).
 */
public class MaximumStudentsTakingExam {

    /**
     * Bitmasking DP rarely appears in weekly contests. This tutorial will introduce my own perspective of bitmasking DP as well as several coding tricks when dealing with bitmasking problems. I will also give my solution to this problem at the end of this tutorial.
     *
     * What is bitmasking? Bitmasking is something related to bit and mask. For the bit part, everything is encoded as a single bit, so the whole state can be encoded as a group of bits, i.e. a binary number. For the mask part, we use 0/1 to represent the state of something. In most cases, 1 stands for the valid state while 0 stands for the invalid state.
     *
     * Let us consider an example. There are 4 cards on the table and I am going to choose several of them. We can encode the 4 cards as 4 bits. Say, if we choose cards 0, 1 and 3, we will use the binary number "1011" to represent the state. If we choose card 2, we will use the binary number "0100" then. The bits on the right represent cards with smaller id.
     *
     * As we all know, integers are stored as binary numbers in the memory but appear as decimal numbers when we are coding. As a result, we tend to use a decimal number instead of a binary number to represent a state. In the previous example, we would use "11" and "4" instead of "1011" and "0100".
     *
     * When doing Bitmasking DP, we are always handling problems like "what is the i-th bit in the state" or "what is the number of valid bits in a state". These problems can be very complicated if we do not handle them properly. I will show some coding tricks below which we can make use of and solve this problem.
     *
     * We can use (x >> i) & 1 to get i-th bit in state x, where >> is the right shift operation. If we are doing this in an if statement (i.e. to check whether the i-th bit is 1), we can also use x & (1 << i), where the << is the left shift operation.
     *
     * We can use (x & y) == x to check if x is a subset of y. The subset means every state in x could be 1 only if the corresponding state in y is 1.
     *
     * We can use (x & (x >> 1)) == 0 to check if there are no adjancent valid states in x.
     *
     * Now we can come to the problem. We can use a bitmask of n bits to represent the validity of each row in the classroom. The i-th bit is 1 if and only if the i-th seat is not broken. For the first example in this problem, the bitmasks will be "010010", "100001" and "010010". When we arrange the students to seat in this row, we can also use n bits to represent the students. The i-th bit is 1 if and only if the i-th seat is occupied by a student. We should notice that n bits representing students must be a subset of n bits representing seats.
     *
     * We denote dp[i][mask] as the maximum number of students for the first i rows while the students in the i-th row follow the masking mask. There should be no adjancent valid states in mask. The transition function is:
     *
     * dp[i][mask] = max(dp[i - 1][mask']) + number of valid bits(mask)
     * where mask' is the masking for the (i-1)-th row. To prevent students from cheating, the following equation must hold:
     *
     * (mask & (mask' >> 1)) == 0, there should be no students in the upper left position for every student.
     *
     * ((mask >> 1) & mask') == 0, there should be no students in the upper right position for every student.
     *
     * If these two equation holds and dp[i - 1][mask'] itself is valid, we could then transit from dp[i - 1][mask'] to dp[i][mask] according to the transition function.
     *
     * And the last question is, how can we compute the number of valid bits in a masking efficiently? In C++, we can simply use the built-in function __builtin_popcount(mask). For other programming languages,
     * we can pre-compute by using count[i] = count[i/2] + (i % 2 == 1) and store them in an array.
     *
     */

    class Solution {
        public int maxStudents(char[][] seats) {
            int m = seats.length, n = seats[0].length;
            int[] validRows = new int[m];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    validRows[i] = (validRows[i] << 1) + (seats[i][j] == '.' ? 1 : 0);
            int stateSize = 1 << n; // There are 2^n states for n columns in binary format
            int[][] dp = new int[m][stateSize];
            for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < stateSize; j++) {
                    // (j & valid) == j: check if j is a subset of valid
                    // !(j & (j >> 1)): check if there is no adjancent students in the row
                    if (((j & validRows[i]) == j) && ((j & (j >> 1)) == 0)) {
                        if (i == 0) {
                            dp[i][j] = Integer.bitCount(j);
                        } else {
                            for (int k = 0; k < stateSize; k++) {
                                // !(j & (k >> 1)): no students in the upper left positions
                                // !((j >> 1) & k): no students in the upper right positions
                                // dp[i-1][k] != -1: the previous state is valid
                                if ((j & (k >> 1)) == 0 && ((j >> 1) & k) == 0 && dp[i-1][k] != -1)  {
                                    dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + Integer.bitCount(j));
                                }
                            }
                        }
                        ans = Math.max(ans, dp[i][j]);
                    }
                }
            }
            return ans;
        }
    }

    public final class Solution {
        static class DP {
            int[] marks = new int[128];
            int[] counts = new int[128];
            int size = 0;
            int max_count;

            int get_max(int deny) {
                if (deny == 0)
                    return max_count;
                int res = -1;
                for (int i = 0; i < size; i++) {
                    if (0 == (marks[i] & deny))
                        res = Math.max(res, counts[i]);
                }
                return res;
            }

            void add(int mask, int count) {
                marks[size] = mask;
                counts[size++] = count;
                if (count > max_count)
                    max_count = count;
            }

            void reset() {
                size = 0;
                max_count = 0;
            }
        }

        DP cur;
        DP next;
        void walk(char[] line, int index, int mask, int deny, int count) {
            if (index < 0) {
                int res = next.get_max(deny);
                if (res >= 0) {
                    cur.add(mask, count + res);
                }
            } else {
                walk(line, index - 1, mask, deny, count);
                if (line[index] == '.' && 0 == (mask & (1 << (index + 1)))) {
                    int mask1 = mask | (1 << index);
                    int deny1 = deny | (1 << (index + 1));
                    if (index > 0)
                        deny1 |= (1 << (index - 1));
                    walk(line, index-1, mask1, deny1, count + 1);
                }
            }
        }

        public int maxStudents(char[][] seats) {
            this.cur = new DP();
            this.next = new DP();
            this.cur.add(0, 0);
            for (int i = seats.length - 1; i >= 0; i--) {
                DP dp = this.cur;
                this.cur = this.next;
                this.next = dp;
                this.cur.reset();
                walk(seats[i], seats[i].length - 1, 0, 0, 0);
            }
            return this.cur.max_count;
        }
    }

}
