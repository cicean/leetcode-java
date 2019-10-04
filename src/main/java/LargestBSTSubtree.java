/**
 * Created by cicean on 8/30/2016.
 * 333. Largest BST Subtree  QuestionEditorial Solution  My Submissions
 Total Accepted: 6198 Total Submissions: 22047 Difficulty: Medium
 Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

 Note:
 A subtree must include all of its descendants.
 Here's an example:
 10
 / \
 5  15
 / \   \
 1   8   7
 The Largest BST Subtree in this case is the highlighted one.
 The return value is the subtree's size, which is 3.
 Hint:

 You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
 Follow up:
 Can you figure out ways to solve it with O(n) time complexity?

 Hide Company Tags Microsoft
 Hide Tags Tree

 */
public class LargestBSTSubtree {

    /**
     * Valid BST
     */
    class Solution {
        public int largestBSTSubtree(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            if (isValid(root, null, null)) return countNode(root);
            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
        }

        public boolean isValid(TreeNode root, Integer min, Integer max) {
            if (root == null) return true;
            if (min != null && min >= root.val) return false;
            if (max != null && max <= root.val) return false;
            return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
        }

        public int countNode(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            return 1 + countNode(root.left) + countNode(root.right);
        }
    }

    /**
     * Since this is not an overall boolean check, and each subtree can decide if itself is a BST, and update a global size variable, I have chosen to decide BST at each subtree, and pass a 3-element array up. If subtree is not BST, size will be -1, and parent tree will not be BST

     time complexity is O(n), since each node is visited exactly once
     */

    class Solution2 {

        private int largestBSTSubtreeSize = 0;
        public int largestBSTSubtree(TreeNode root) {
            helper(root);
            return largestBSTSubtreeSize;
        }

        private int[] helper(TreeNode root) {
            // return 3-element array:
            // # of nodes in the subtree, leftmost value, rightmost value
            // # of nodes in the subtree will be -1 if it is not a BST
            int[] result = new int[3];
            if (root == null) {
                return result;
            }
            int[] leftResult = helper(root.left);
            int[] rightResult = helper(root.right);
            if ((leftResult[0] == 0 || leftResult[0] > 0 && leftResult[2] <= root.val) &&
                    (rightResult[0] == 0 || rightResult[0] > 0 && rightResult[1] >= root.val)) {
                int size = 1 + leftResult[0] + rightResult[0];
                largestBSTSubtreeSize = Math.max(largestBSTSubtreeSize, size);
                int leftBoundary = leftResult[0] == 0 ? root.val : leftResult[1];
                int rightBoundary = rightResult[0] == 0 ? root.val : rightResult[2];
                result[0] = size;
                result[1] = leftBoundary;
                result[2] = rightBoundary;
            } else {
                result[0] = -1;
            }
            return result;
        }
    }
}
