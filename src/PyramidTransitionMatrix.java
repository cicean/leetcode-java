import java.util.*;

/**
 * 756. Pyramid Transition Matrix
 * DescriptionHintsSubmissionsDiscussSolution
 * We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.
 *
 * For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.
 *
 * We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
 *
 * Return true if we can build the pyramid all the way to the top, otherwise false.
 *
 * Example 1:
 * Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
 * Output: true
 * Explanation:
 * We can stack the pyramid like this:
 *     A
 *    / \
 *   D   E
 *  / \ / \
 * X   Y   Z
 *
 * This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
 * Example 2:
 * Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
 * Output: false
 * Explanation:
 * We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 * Note:
 * bottom will be a string with length in range [2, 8].
 * allowed will have length in range [0, 200].
 * Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
 */
public class PyramidTransitionMatrix {

    /**
     * Approach #2: Depth-First Search [Accepted]
     * Intuition
     *
     * We exhaustively try every combination of blocks.
     *
     * Algorithm
     *
     * We can work in either strings or integers, but we need to create a transition map T from the list of allowed triples.
     * This map T[x][y] = {set of z} will be all possible parent blocks for a left child of x and a right child of y.
     * When we work in strings, we use Set, and when we work in integers, we will use the set bits of the result integer.
     *
     * Afterwards, to solve a row, we generate every possible combination of the next row and solve them.
     * If any of those new rows are solvable, we return True, otherwise False.
     *
     * We can also cache intermediate results, saving us time. This is illustrated in the comments for Python.
     * For Java, all caching is done with lines of code that mention the integer R.
     * Time Complexity: O(\mathcal{A}^{N})O(A
     * N
     *  ), where NN is the length of bottom, and \mathcal{A}A is the size of the alphabet, and assuming we cache intermediate results. We might try every sequence of letters for each row. [The total complexity is because O(\sum_{k}^n \mathcal{A}^{k})O(¡Æ
     * k
     * n
     * ?
     *  A
     * k
     *  ) is a geometric series equal to O(\frac{\mathcal{A^{n+1}}-1}{\mathcal{A}-1})O(
     * A?1
     * A
     * n+1
     *  ?1
     * ?
     *  ).] Without intermediate caching, this would be O(\mathcal{A}^{N^2})O(A
     * N
     * 2
     *
     *  ).
     *
     * Space Complexity: O(N^2)O(N
     * 2
     *  ) additional space complexity.
     */

    int[][] T;
    Set<Long> seen;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        if (bottom == null || bottom.length() == 0 || allowed == null) {
            return false;
        }

        T = new int[7][7];

        for (String a : allowed) {
            T[a.charAt(0) - 'A'][a.charAt(1) - 'A'] |= 1 << (a.charAt(2) - 'A');
        }

        seen = new HashSet<>();
        int N = bottom.length();
        int[][] A = new int[N][N];
        int j = 0;

        for (char c : bottom.toCharArray()) {
            A[N-1][j++] = c - 'A';
        }

        return solve(A, 0 , N-1, 0);
    }

    private boolean solve(int[][] A, long R, int N, int i) {
        if (N == 1 && i == 1) {
            return true;
        } else if (i == N) {
            if (seen.contains((R))) {
                return false;
            }
            seen.add(R);
            return solve(A, 0, N-1, 0);
        } else {
            int w = T[A[N][i]][A[N][i+1]];
            int j = 0;
            while (j < 7) {
                if (((w >> j) & 1) != 0) {
                    A[N-1][i] = j;
                    if (solve(A, R * 8 + (j + 1), N, i+1)) {
                        return true;
                    }
                }
                j++;
            }
        }

        return false;
    }

}
