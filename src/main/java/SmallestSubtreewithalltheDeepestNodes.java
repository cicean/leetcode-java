import java.util.*;

/**
 * 865. Smallest Subtree with all the Deepest Nodes
 * Medium
 *
 * 544
 *
 * 168
 *
 * Add to List
 *
 * Share
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 *
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is that node, plus the set of all descendants of that node.
 *
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation:
 *
 *
 *
 * We return the node with value 2, colored in yellow in the diagram.
 * The nodes colored in blue are the deepest nodes of the tree.
 * The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
 * The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
 * Both the input and output have TreeNode type.
 *
 *
 * Note:
 *
 * The number of nodes in the tree will be between 1 and 500.
 * The values of each node are unique.
 * Accepted
 * 29,695
 * Submissions
 * 50,035
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 4
 */

public class SmallestSubtreewithalltheDeepestNodes {

    /**
     * Approach 1: Paint Deepest Nodes
     * Intuition
     *
     * We try a straightforward approach that has two phases.
     *
     * The first phase is to identify the nodes of the tree that are deepest. To do this, we have to annotate the depth of each node. We can do this with a depth first search.
     *
     * Afterwards, we will use that annotation to help us find the answer:
     *
     * If the node in question has maximum depth, it is the answer.
     *
     * If both the left and right child of a node have a deepest descendant, then the answer is this parent node.
     *
     * Otherwise, if some child has a deepest descendant, then the answer is that child.
     *
     * Otherwise, the answer for this subtree doesn't exist.
     *
     * Algorithm
     *
     * In the first phase, we use a depth first search dfs to annotate our nodes.
     *
     * In the second phase, we also use a depth first search answer(node), returning the answer for the subtree at that node, and using the rules above to build our answer from the answers of the children of node.
     *
     * Note that in this approach, the answer function returns answers that have the deepest nodes of the entire tree, not just the subtree being considered.
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the number of nodes in the tree.
     *
     * Space Complexity: O(N)O(N).
     *
     */

    class Solution {
        Map<TreeNode, Integer> depth;
        int max_depth;
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            depth = new HashMap();
            depth.put(null, -1);
            dfs(root, null);
            max_depth = -1;
            for (Integer d: depth.values())
                max_depth = Math.max(max_depth, d);

            return answer(root);
        }

        public void dfs(TreeNode node, TreeNode parent) {
            if (node != null) {
                depth.put(node, depth.get(parent) + 1);
                dfs(node.left, node);
                dfs(node.right, node);
            }
        }

        public TreeNode answer(TreeNode node) {
            if (node == null || depth.get(node) == max_depth)
                return node;
            TreeNode L = answer(node.left),
                    R = answer(node.right);
            if (L != null && R != null) return node;
            if (L != null) return L;
            if (R != null) return R;
            return null;
        }
    }

    class Solution_dfs {
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            int H = height(root);
            int Lheight = height(root.left);
            int Rheight = height(root.right);

            if (Lheight < Rheight) {
                return subtreeWithAllDeepest(root.right);
            }
            if (Rheight < Lheight) {
                return subtreeWithAllDeepest(root.left);
            }
            return root;
        }

        private int height(TreeNode root) {
            return root == null ? 0 : Math.max(height(root.left), height(root.right)) + 1;
        }
    }
}
