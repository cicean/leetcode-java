/**
 * 669. Trim a Binary Search Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

 Example 1:
 Input:
 1
 / \
 0   2

 L = 1
 R = 2

 Output:
 1
 \
 2
 Example 2:
 Input:
 3
 / \
 0   4
 \
 2
 /
 1

 L = 1
 R = 3

 Output:
 3
 /
 2
 /
 1

 */

public class TrimaBinarySearchTree {

  /*
  Approach #1: Recursion [Accepted]

Intuition

Let trim(node) be the desired answer for the subtree at that node. We can construct the answer recursively.

Algorithm

When node.val > Rnode.val > R, we know that the trimmed binary tree must occur to the left of the node.
Similarly, when node.val < Lnode.val < L, the trimmed binary tree occurs to the right of the node.
Otherwise, we will trim both sides of the tree.

Time Complexity: O(N)O(N), where NN is the total number of nodes in the given tree. We visit each node at most once.

Space Complexity: O(N)O(N). Even though we don't explicitly use any additional memory,
the call stack of our recursion could be as large as the number of nodes in the worst case.
   */

  public TreeNode trimBST(TreeNode root, int L, int R) {
    if (root == null) return root;
    if (root.val > R) return trimBST(root.left, L, R);
    if (root.val < L) return trimBST(root.right, L, R);

    root.left = trimBST(root.left, L, R);
    root.right = trimBST(root.right, L, R);
    return root;
  }

}
