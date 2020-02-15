import java.util.*;

/**
 * 958. Check Completeness of a Binary Tree
 * Medium
 *
 * 533
 *
 * 10
 *
 * Add to List
 *
 * Share
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 * Note:
 *
 * The tree will have between 1 and 100 nodes.
 * Accepted
 * 36,331
 * Submissions
 * 71,148
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * yangshun
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 8
 *
 * Oracle
 * |
 * 2
 */
public class CheckCompletenessofaBinaryTree {

    /**
     * Solution
     * Approach 1: Breadth First Search
     * Intuition
     *
     * This problem reduces to two smaller problems: representing the "location" of each node as a (depth, position) pair, and formalizing what it means for nodes to all be left-justified.
     *
     * If we have say, 4 nodes in a row with depth 3 and positions 0, 1, 2, 3; and we want 8 new nodes in a row with depth 4 and positions 0, 1, 2, 3, 4, 5, 6, 7; then we can see that the rule for going from a node to its left child is (depth, position) -> (depth + 1, position * 2), and the rule for going from a node to its right child is (depth, position) -> (depth + 1, position * 2 + 1). Then, our row at depth dd is completely filled if it has 2^{d-1}2
     * d−1
     *   nodes, and all the nodes in the last level are left-justified when their positions take the form 0, 1, ... in sequence with no gaps.
     *
     * A cleaner way to represent depth and position is with a code: 1 will be the root node, and for any node with code v, the left child will be 2*v and the right child will be 2*v + 1. This is the scheme we will use. Under this scheme, our tree is complete if the codes take the form 1, 2, 3, ... in sequence with no gaps.
     *
     * Algorithm
     *
     * At the root node, we will associate it with the code 1. Then, for each node with code v, we will associate its left child with code 2 * v, and its right child with code 2 * v + 1.
     *
     * We can find the codes of every node in the tree in "reading order" (top to bottom, left to right) sequence using a breadth first search. (We could also use a depth first search and sort the codes later.)
     *
     * Then, we check that the codes are the sequence 1, 2, 3, ... with no gaps. Actually, we only need to check that the last code is correct, since the last code is the largest value.
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the number of nodes in the tree.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution_BFS {
        public boolean isCompleteTree(TreeNode root) {
            List<ANode> nodes = new ArrayList();
            nodes.add(new ANode(root, 1));
            int i = 0;
            while (i < nodes.size()) {
                ANode anode = nodes.get(i++);
                if (anode.node != null) {
                    nodes.add(new ANode(anode.node.left, anode.code * 2));
                    nodes.add(new ANode(anode.node.right, anode.code * 2 + 1));
                }
            }

            return nodes.get(i-1).code == nodes.size();
        }
    }

    class ANode {  // Annotated Node
        TreeNode node;
        int code;
        ANode(TreeNode node, int code) {
            this.node = node;
            this.code = code;
        }
    }

    class Solution {
        public boolean isCompleteTree(TreeNode root) {
            boolean end = false;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if(cur == null) end = true;
                else{
                    if(end) return false;
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
            return true;
        }
    }

    class Solution_Recursion {
        public boolean isCompleteTree(TreeNode root) {
            return isCompleteTree(root,0,countNodes(root));
        }
        private boolean isCompleteTree(TreeNode root, int i, int n) {
            if (root == null)  return true;
            else if (i >= n) return false;
            return isCompleteTree(root.left, 2 * i + 1, n) && isCompleteTree(root.right, 2 * i + 2, n);
        }
        private  int countNodes(TreeNode root) {
            if (root == null) return 0;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
}
