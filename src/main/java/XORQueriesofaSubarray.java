/**
 * 1310. XOR Queries of a Subarray
 * Medium
 *
 * 85
 *
 * 3
 *
 * Add to List
 *
 * Share
 * Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri], for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ). Return an array containing the result for the given queries.
 *
 *
 * Example 1:
 *
 * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * Output: [2,7,14,8]
 * Explanation:
 * The binary representation of the elements in the array are:
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * The XOR values for queries are:
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * Example 2:
 *
 * Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * Output: [8,0,4,4]
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 10^9
 * 1 <= queries.length <= 3 * 10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] < arr.length
 * Accepted
 * 6,427
 * Submissions
 * 9,907
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Airtel
 * |
 * LeetCode
 * What is the result of x ^ y ^ x ?
 * Compute the prefix sum for XOR.
 * Process the queries with the prefix sum values.
 *
 */

public class XORQueriesofaSubarray {
    /**
     * Explanation
     * In-place calculate the prefix XOR of input A.
     *
     * For each query [i, j],
     * if i == 0, query result = A[j]
     * if i != 0, query result = A[i - 1] ^ A[j]
     *
     *
     * Complexity
     * Time O(N)
     * O(1) extra space
     * O(Q) for output
     */

    public int[] xorQueries(int[] A, int[][] queries) {
        int[] res = new int[queries.length], q;
        for (int i = 1; i < A.length; ++i)
            A[i] ^= A[i - 1];
        for (int i = 0; i < queries.length; ++i) {
            q = queries[i];
            res[i] = q[0] > 0 ? A[q[0] - 1] ^ A[q[1]] : A[q[1]];
        }
        return res;
    }
}
