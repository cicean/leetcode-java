import java.util.*;

/**
 * 513. Find Bottom Left Tree Value
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, find the leftmost value in the last row of the tree.

 Example 1:
 Input:

 2
 / \
 1   3

 Output:
 1
 Example 2:
 Input:

 1
 / \
 2   3
 /   / \
 4   5   6
 /
 7

 Output:
 7
 Note: You may assume the tree (i.e., the given root node) is not NULL.
 */

public class FindBottomLeftTreeValue {

  /**
   * Right-to-Left BFS (Python + Java)
   Doing BFS right-to-left means we can simply return the last node's value and don't have to keep
   track of the first node in the current row or even care about rows at all. Inspired by @fallcreek's
   solution (not published) which uses two nested loops to go row by row but already had the
   right-to-left idea making it easier. I just took that further.


   */

  public int findLeftMostNode(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      root = queue.poll();
      if (root.right != null)
        queue.add(root.right);
      if (root.left != null)
        queue.add(root.left);
    }
    return root.val;
  }

  public class Solution {
    public int findBottomLeftValue(TreeNode root) {
      return findBottomLeftValue(root, 1, new int[]{0,0});
    }
    public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
      if (res[1]<depth) {res[0]=root.val;res[1]=depth;}
      if (root.left!=null) findBottomLeftValue(root.left, depth+1, res);
      if (root.right!=null) findBottomLeftValue(root.right, depth+1, res);
      return res[0];
    }
  }

}
