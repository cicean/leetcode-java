/**
 * 549. Binary Tree Longest Consecutive Sequence II
 * Medium
 *
 * 495
 *
 * 31
 *
 * Add to List
 *
 * Share
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 * Example 1:
 *
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 *
 * Example 2:
 *
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *
 * Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 *
 * Accepted
 * 22,232
 * Submissions
 * 48,249
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * nagasupreeth
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 3
 * Binary Tree Longest Consecutive Sequence
 * Medium
 */
public class BinaryTreeLongestConsecutiveSequenceII {
    /**
     * Approach #2 Single traversal [Accepted]
     * Algorithm
     *
     * This solution is very simple. With every node, we associate two values/variables named inrinr and dcrdcr, where incrincr represents the length of the longest incrementing branch below the current node including itself, and dcrdcr represents the length of the longest decrementing branch below the current node including itself.
     *
     * We make use of a recursive function longestPath(node) which returns an array of the form [inr, dcr][inr,dcr] for the calling node. We start off by assigning both inrinr and dcrdcr as 1 for the current node. This is because the node itself always forms a consecutive increasing as well as decreasing path of length 1.
     *
     * Then, we obtain the length of the longest path for the left child of the current node using longestPath[root.left]. Now, if the left child is just smaller than the current node, it forms a decreasing sequence with the current node. Thus, the dcrdcr value for the current node is stored as the left child's dcrdcr value + 1. But, if the left child is just larger than the current node, it forms an incrementing sequence with the current node. Thus, we update the current node's inrinr value as left\_child(inr) + 1left_child(inr)+1.
     *
     * Then, we do the same process with the right child as well. But, for obtaining the inrinr and dcrdcr value for the current node, we need to consider the maximum value out of the two values obtained from the left and the right child for both inrinr and dcrdcr, since we need to consider the longest sequence possible.
     *
     * Further, after we've obtained the final updated values of inrinr and dcrdcr for a node, we update the length of the longest consecutive path found so far as maxval = \text{max}(inr + dcr - 1)maxval=max(inr+dcr−1).
     *
     * The following animation will make the process clear:
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n). The whole tree is traversed only once.
     * Space complexity : O(n)O(n). The recursion goes upto a depth of nn in the worst case.
     *
     */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution_single_traversal {
        int maxval = 0;
        public int longestConsecutive(TreeNode root) {
            longestPath(root);
            return maxval;
        }
        public int[] longestPath(TreeNode root) {
            if (root == null)
                return new int[] {0,0};
            int inr = 1, dcr = 1;
            if (root.left != null) {
                int[] l = longestPath(root.left);
                if (root.val == root.left.val + 1)
                    dcr = l[1] + 1;
                else if (root.val == root.left.val - 1)
                    inr = l[0] + 1;
            }
            if (root.right != null) {
                int[] r = longestPath(root.right);
                if (root.val == root.right.val + 1)
                    dcr = Math.max(dcr, r[1] + 1);
                else if (root.val == root.right.val - 1)
                    inr = Math.max(inr, r[0] + 1);
            }
            maxval = Math.max(maxval, dcr + inr - 1);
            return new int[] {inr, dcr};
        }
    }
}
