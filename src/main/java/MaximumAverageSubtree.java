/**
 * 1120. Maximum Average Subtree
 * Medium
 *
 * 124
 *
 * 4
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 *
 * (A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 *
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 5000.
 * Each node will have a value between 0 and 100000.
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 * Accepted
 * 7,963
 * Submissions
 * 12,861
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 2
 *
 * Atlassian
 * |
 * 2
 * Can you find the sum of values and the number of nodes for every sub-tree ?
 * Can you find the sum of values and the number of nodes for a sub-tree given the sum of values and the number of nodes of it's left and right sub-trees ?
 * Use depth first search to recursively find the solution for the children of a node then use their solutions to compute the current node's solution.
 *
 */
public class MaximumAverageSubtree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public double maximumAverageSubtree(TreeNode root) {
            return helper(root)[2];
        }
        private double[] helper(TreeNode n) {
            if (n == null)  // base case.
                return new double[]{0, 0, 0}; // sum, count  & average of nodes
            double[] l = helper(n.left), r = helper(n.right); // recurse to children.
            double child = Math.max(l[2], r[2]); // larger of the children.
            double sum = n.val + l[0] + r[0], cnt = 1 + l[1] + r[1]; // sum & count of subtree rooted at n.
            double maxOfThree = Math.max(child, sum  / cnt); // largest out of n and its children.
            return new double[]{sum, cnt, maxOfThree};
        }
    }
}
