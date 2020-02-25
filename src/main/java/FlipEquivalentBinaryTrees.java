/**
 * 951. Flip Equivalent Binary Trees
 * Medium
 *
 * 465
 *
 * 18
 *
 * Add to List
 *
 * Share
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 *
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 *
 * Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.
 *
 *
 *
 * Example 1:
 *
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * Flipped Trees Diagram
 *
 *
 * Note:
 *
 * Each tree will have at most 100 nodes.
 * Each value in each tree will be a unique integer in the range [0, 99].
 *
 *
 * Accepted
 * 34,941
 * Submissions
 * 53,331
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * boganc
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 8
 */
public class FlipEquivalentBinaryTrees {

    /**
     * Approach 1: Recursion
     * Intuition
     *
     * If root1 and root2 have the same root value, then we only need to check if their children are equal (up to ordering.)
     *
     * Algorithm
     *
     * There are 3 cases:
     *
     * If root1 or root2 is null, then they are equivalent if and only if they are both null.
     *
     * Else, if root1 and root2 have different values, they aren't equivalent.
     *
     * Else, let's check whether the children of root1 are equivalent to the children of root2. There are two different ways to pair these children.
     * Complexity Analysis
     *
     * Time Complexity: O(min(N_1, N_2))O(min(N
     * 1
     * ​
     *  ,N
     * 2
     * ​
     *  )), where N_1, N_2N
     * 1
     * ​
     *  ,N
     * 2
     * ​
     *   are the lengths of root1 and root2.
     *
     * Space Complexity: O(min(H_1, H_2))O(min(H
     * 1
     * ​
     *  ,H
     * 2
     * ​
     *  )), where H_1, H_2H
     * 1
     * ​
     *  ,H
     * 2
     * ​
     *   are the heights of the trees of root1 and root2.
     */

    class Solution {
        public boolean flipEquiv(TreeNode r1, TreeNode r2) {
            if (r1 == null || r2 == null) return r1 == r2;
            return r1.val == r2.val &&
                    (flipEquiv(r1.left, r2.left) && flipEquiv(r1.right, r2.right) ||
                            flipEquiv(r1.left, r2.right) && flipEquiv(r1.right, r2.left));
        }
    }

    /**
     * Approach 2: Canonical Traversal
     * Intuition
     *
     * Flip each node so that the left child is smaller than the right, and call this the canonical representation. All equivalent trees have exactly one canonical representation.
     *
     * Algorithm
     *
     * We can use a depth-first search to compare the canonical representation of each tree. If the traversals are the same, the representations are equal.
     *
     * When traversing, we should be careful to encode both when we enter or leave a node.
     *
     */

    class Solution_2 {
        public boolean flipEquiv(TreeNode root1, TreeNode root2) {
            List<Integer> vals1 = new ArrayList();
            List<Integer> vals2 = new ArrayList();
            dfs(root1, vals1);
            dfs(root2, vals2);
            return vals1.equals(vals2);
        }

        public void dfs(TreeNode node, List<Integer> vals) {
            if (node != null) {
                vals.add(node.val);
                int L = node.left != null ? node.left.val : -1;
                int R = node.right != null ? node.right.val : -1;

                if (L < R) {
                    dfs(node.left, vals);
                    dfs(node.right, vals);
                } else {
                    dfs(node.right, vals);
                    dfs(node.left, vals);
                }

                vals.add(null);
            }
        }
    }




}
