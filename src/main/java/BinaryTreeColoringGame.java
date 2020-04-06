/**
 *
 */
public class BinaryTreeColoringGame {

    class Solution {
        /**
         * Intuition
         * The first player colors a node,
         * there are at most 3 nodes connected to this node.
         * Its left, its right and its parent.
         * Take this 3 nodes as the root of 3 subtrees.
         *
         * The second player just color any one root,
         * and the whole subtree will be his.
         * And this is also all he can take,
         * since he cannot cross the node of the first player.
         *
         *
         * Explanation
         * count will recursively count the number of nodes,
         * in the left and in the right.
         * n - left - right will be the number of nodes in the "subtree" of parent.
         * Now we just need to compare max(left, right, parent) and n / 2
         *
         *
         * Complexity
         * Time O(N)
         * Space O(height) for recursion
         */
        int left, right, val;
        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            val = x;
            count(root);
            return Math.max(Math.max(left, right), n - left - right - 1) > n / 2;
        }

        private int count(TreeNode node) {
            if (node == null) return 0;
            int l = count(node.left), r = count(node.right);
            if (node.val == val) {
                left = l;
                right = r;
            }
            return l + r + 1;
        }
    }


}
