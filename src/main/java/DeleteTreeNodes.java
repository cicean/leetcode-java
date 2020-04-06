/**
 * Delete Tree Nodes
 * Medium
 *
 * 65
 *
 * 23
 *
 * Add to List
 *
 * Share
 * A tree rooted at node 0 is given as follows:
 *
 * The number of nodes is nodes;
 * The value of the i-th node is value[i];
 * The parent of the i-th node is parent[i].
 * Remove every subtree whose sum of values of nodes is zero.
 *
 * After doing so, return the number of nodes remaining in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nodes <= 10^4
 * -10^5 <= value[i] <= 10^5
 * parent.length == nodes
 * parent[0] == -1 which indicates that 0 is the root.
 * Accepted
 * 3,374
 * Submissions
 * 5,414
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * LeetCode
 * Traverse the tree using depth first search.
 * Find for every node the sum of values of its sub-tree.
 * Traverse the tree again from the root and return once you reach a node with zero sum of values in its sub-tree.
 */
public class DeleteTreeNodes {

    /**
     * Solution 2: One Pass
     * Wrote this solution in 2019-11-30,
     * don't have the premium so I'll lose the access to my this post.
     *
     * Hidden condition:
     * parernt[i] < i for all i > 0
     * a parent always has have smaller index of its children.
     *
     *
     * Intuition
     * Don't ask me why, my friend told me.
     * He said it was an intuition.
     * We hardly see a tree problem represented by array.
     * Another observation is tha, no test case contains a tree with sum = 0.
     *
     * The problem writer was lasy and made some random cases.
     * I personally think taht he didn't actually do the work.
     *
     *
     * Complexity
     * Time O(N)
     * Space O(N)
     */

    public int deleteTreeNodes(int n, int[] parent, int[] value) {
        int[] res = new int[n];
        for (int i = n - 1; i > 0; --i) {
            value[parent[i]] += value[i];
            res[parent[i]] += value[i] != 0 ? res[i] + 1 : 0;
        }
        return value[0] != 0 ? res[0] + 1 : 0;
    }

    class Solution {
        public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
            for(int i  = value.length -1; i >= 0; i--) {
                if (parent[i] != -1)
                    value[parent[i]] += value[i];
            }
            int cnt = value[0] == 0 ? 0 : 1;
            for (int i = 1 ; i < value.length;i++){
                if (value[i] != 0 && value[parent[i]] != 0) cnt++;
                else value[i] = 0;
            }


            return cnt;}
    }

}
