import java.util.*;

/**
 *
 */
public class RangeSumofBST {

    class Solution {
        public int rangeSumBST(TreeNode root, int L, int R) {
            if (root == null)
                return 0;
            if (root.val < L)
                return rangeSumBST(root.right, L , R);
            if (root.val > R)
                return rangeSumBST(root.left, L , R);
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L , R);
        }
    }

    class Solution_iterative {
        public int rangeSumBST(TreeNode root, int L, int R) {
            int ans = 0;
            Stack<TreeNode> stack = new Stack();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node != null) {
                    if (L <= node.val && node.val <= R)
                        ans += node.val;
                    if (L < node.val)
                        stack.push(node.left);
                    if (node.val < R)
                        stack.push(node.right);
                }
            }
            return ans;
        }
    }

}
