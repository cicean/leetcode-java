/**
 * 753. Cracking the Safe
 * Hard
 *
 * 389
 *
 * 498
 *
 * Add to List
 *
 * Share
 * There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
 *
 * While entering a password, the last n digits entered will automatically be matched against the correct password.
 *
 * For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.
 *
 * Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 *
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 *
 *
 * Note:
 *
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 *
 *
 * Accepted
 * 24,929
 * Submissions
 * 50,091
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * We can think of this problem as the problem of finding an Euler path (a path visiting every edge exactly once)
 * on the following graph: there are $$k^{n-1}$$ nodes with each node having $$k$$ edges.
 * It turns out this graph always has an Eulerian circuit (path starting where it ends.)
 * We should visit each node in "post-order" so as to not get stuck in the graph prematurely.
 */
public class CrackingtheSafe {

    /**
     * Approach #1: Hierholzer's Algorithm [Accepted]
     * Intuition
     *
     * We can think of this problem as the problem of finding an Euler path (a path visiting every edge exactly once) on the following graph: there are k^{n-1}k
     * n−1
     *   nodes with each node having kk edges.
     *
     * For example, when k = 4, n = 3, the nodes are '00', '01', '02', ..., '32', '33' and each node has 4 edges '0', '1', '2', '3'. A node plus edge represents a complete edge and viewing that substring in our answer.
     *
     * Any connected directed graph where all nodes have equal in-degree and out-degree has an Euler circuit (an Euler path ending where it started.) Because our graph is highly connected and symmetric, we should expect intuitively that taking any path greedily in some order will probably result in an Euler path.
     *
     * This intuition is called Hierholzer's algorithm: whenever there is an Euler cycle, we can construct it greedily. The algorithm goes as follows:
     *
     * Starting from a vertex u, we walk through (unwalked) edges until we get stuck. Because the in-degrees and out-degrees of each node are equal, we can only get stuck at u, which forms a cycle.
     *
     * Now, for any node v we had visited that has unwalked edges, we start a new cycle from v with the same procedure as above, and then merge the cycles together to form a new cycle u \rightarrow \dots \rightarrow v \rightarrow \dots \rightarrow v \rightarrow \dots \rightarrow uu→⋯→v→⋯→v→⋯→u.
     *
     * Algorithm
     *
     * We will modify our standard depth-first search: instead of keeping track of nodes, we keep track of (complete) edges: seen records if an edge has been visited.
     *
     * Also, we'll need to visit in a sort of "post-order", recording the answer after visiting the edge. This is to prevent getting stuck. For example, with k = 2, n = 2, we have the nodes '0', '1'. If we greedily visit complete edges '00', '01', '10', we will be stuck at the node '0' prematurely. However, if we visit in post-order, we'll end up visiting '00', '01', '11', '10' correctly.
     *
     * In general, during our Hierholzer walk, we will record the results of other subcycles first, before recording the main cycle we started from, just as in our first description of the algorithm. Technically, we are recording backwards, as we exit the nodes.
     *
     * For example, we will walk (in the "original cycle") until we get stuck, then record the node as we exit. (Every edge walked is always marked immediately so that it can no longer be used.) Then in the penultimate node of our original cycle, we will do a Hierholzer walk and then record this node; then in the third-last node of our original cycle we will do a Hierholzer walk and then record this node, and so on.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(n * k^n)O(n∗k
     * n
     *  ). We visit every edge once in our depth-first search, and nodes take O(n)O(n) space.
     *
     * Space Complexity: O(n * k^n)O(n∗k
     * n
     *  ), the size of seen.
     */
    class Solution {
        Set<String> seen;
        StringBuilder ans;

        public String crackSafe(int n, int k) {
            if (n == 1 && k == 1) return "0";
            seen = new HashSet();
            ans = new StringBuilder();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n-1; ++i)
                sb.append("0");
            String start = sb.toString();

            dfs(start, k);
            ans.append(start);
            return new String(ans);
        }

        public void dfs(String node, int k) {
            for (int x = 0; x < k; ++x) {
                String nei = node + x;
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    dfs(nei.substring(1), k);
                    ans.append(x);
                }
            }
        }
    }
     /** Approach #2: Inverse Burrows-Wheeler Transform [Accepted]
     * Explanation
     *
     * If we are familiar with the theory of combinatorics on words, recall that a Lyndon Word L is a word that is the unique minimum of it's rotations.
     *
     * One important mathematical result (due to Fredericksen and Maiorana), is that the concatenation in lexicographic order of Lyndon words with length dividing n, forms a de Bruijin sequence: a sequence where every every word (from the k^nk
     * n
     *   available) appears as a substring of length n (where we are allowed to wrap around.)
     *
     * For example, when n = 6, k = 2, all the Lyndon words with length dividing n in lexicographic order are (spaces for convenience): 0 000001 000011 000101 000111 001 001011 001101 001111 01 010111 011 011111 1. It turns out this is the smallest de Bruijin sequence.
     *
     * We can use the Inverse Burrows-Wheeler Transform (IBWT) to generate these Lyndon words. Consider two sequences: S is the alphabet repeated k^{n-1}k
     * n−1
     *   times: S = 0123...0123...0123...., and S' is the alphabet repeated k^{n-1}k
     * n−1
     *   times for each letter: S' = 00...0011...1122.... We can think of S' and S as defining a permutation, where the j-th occurrence of each letter of the alphabet in S' maps to the corresponding j-th occurrence in S. The cycles of this permutation turn out to be the corresponding smallest de Bruijin sequence (link).
     *
     * Under this view, the permutation S' \rightarrow SS
     * ′
     *  →S [mapping permutation indices (i * k^{n-1} + q) \rightarrow (q * k + i)(i∗k
     * n−1
     *  +q)→(q∗k+i)] form the desired Lyndon words.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(k^n)O(k
     * n
     *  ). We loop through every possible substring.
     *
     * Space Complexity: O(k^n)O(k
     * n
     *  ), the size of P and ans.
     */

     class Solution {
         public String crackSafe(int n, int k) {
             int M = (int) Math.pow(k, n-1);
             int[] P = new int[M * k];
             for (int i = 0; i < k; ++i)
                 for (int q = 0; q < M; ++q)
                     P[i*M + q] = q*k + i;

             StringBuilder ans = new StringBuilder();
             for (int i = 0; i < M*k; ++i) {
                 int j = i;
                 while (P[j] >= 0) {
                     ans.append(String.valueOf(j / M));
                     int v = P[j];
                     P[j] = -1;
                     j = v;
                 }
             }

             for (int i = 0; i < n-1; ++i)
                 ans.append("0");
             return new String(ans);
         }
     }


}
