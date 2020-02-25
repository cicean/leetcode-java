/**
 * 1026. Maximum Difference Between Node and Ancestor
 * Medium
 *
 * 313
 *
 * 17
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation:
 * We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 *
 *
 * Note:
 *
 * The number of nodes in the tree is between 2 and 5000.
 * Each node will have value between 0 and 100000.
 * Accepted
 * 20,877
 * Submissions
 * 33,283
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * deadmans144
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 2
 * For each subtree, find the minimum value and maximum value of its descendants.
 */

public class MaximumDifferenceBetweenNodeandAncestor {

    class Solution_dfs {
        public int maxAncestorDiff(TreeNode root) {
            return dfs(root, root.val, root.val);
        }

        public int dfs(TreeNode root, int mn, int mx) {
            if (root == null) return mx - mn;
            mx = Math.max(mx, root.val);
            mn = Math.min(mn, root.val);
            return Math.max(dfs(root.left, mn, mx), dfs(root.right, mn, mx));
        }
    }
}
