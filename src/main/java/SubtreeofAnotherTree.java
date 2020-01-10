/**
 * 572. Subtree of Another Tree
 * Easy
 *
 * 1480
 *
 * 61
 *
 * Favorite
 *
 * Share
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 * Accepted
 * 133,767
 * Submissions
 * 313,419
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * laowangx
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 30
 *
 * Microsoft
 * |
 * 2
 *
 * Google
 * |
 * 2
 * Count Univalue Subtrees
 * Medium
 * Most Frequent Subtree Sum
 * Medium
 * Which approach is better here- recursive or iterative?
 * If recursive approach is better, can you write recursive function with its parameters?
 * Two trees s and t are said to be identical if their root values are same and their left and right subtrees are identical. Can you write this in form of recursive formulae?
 * Recursive formulae can be: isIdentical(s,t)= s.val==t.val AND isIdentical(s.left,t.left) AND isIdentical(s.right,t.right)
 */

public class SubtreeofAnotherTree {

    /**
     * Complexity Analysis
     *
     * Time complexity : O(m^2+n^2+m*n)O(m
     * 2
     *  +n
     * 2
     *  +m∗n). A total of nn nodes of the tree ss and mm nodes of tree tt are traversed.
     *  Assuming string concatenation takes O(k)O(k) time for strings of length kk and indexOf takes O(m*n)O(m∗n).
     *
     * Space complexity : O(max(m,n))O(max(m,n)). The depth of the recursion tree can go upto nn for tree tt and mm for
     * tree ss in worst case.
     */
    public class Solution {

        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s == null) return false;
            if (isSame(s, t)) return true;
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }

        private boolean isSame(TreeNode s, TreeNode t) {
            if (s == null && t == null) return true;
            if (s == null || t == null) return false;

            if (s.val != t.val) return false;

            return isSame(s.left, t.left) && isSame(s.right, t.right);
        }
    }
}
