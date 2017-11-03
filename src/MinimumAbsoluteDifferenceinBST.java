import java.util.*;

/**
 * 530. Minimum Absolute Difference in BST
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

 Example:

 Input:

 1
 \
 3
 /
 2

 Output:
 1

 Explanation:
 The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 Note: There are at least two nodes in this BST.
 */

public class MinimumAbsoluteDifferenceinBST {

  /**
   * The most common idea is to first inOrder traverse the tree and compare the delta between each of the adjacent values. It's guaranteed to have the correct answer because it is a BST thus inOrder traversal values are sorted.

   Solution 1 - In-Order traverse, time complexity O(N), space complexity O(1).
   */

  public class Solution {
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
      if (root == null) return min;

      getMinimumDifference(root.left);

      if (prev != null) {
        min = Math.min(min, root.val - prev);
      }
      prev = root.val;

      getMinimumDifference(root.right);

      return min;
    }

    /**
     * What if it is not a BST? (Follow up of the problem) The idea is to put values in a TreeSet and then every time we can use O(lgN) time to lookup for the nearest values.

     Solution 2 - Pre-Order traverse, time complexity O(NlgN), space complexity O(N).
     */

    public class Solution2 {
      TreeSet<Integer> set = new TreeSet<>();
      int min = Integer.MAX_VALUE;

      public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;

        if (!set.isEmpty()) {
          if (set.floor(root.val) != null) {
            min = Math.min(min, root.val - set.floor(root.val));
          }
          if (set.ceiling(root.val) != null) {
            min = Math.min(min, set.ceiling(root.val) - root.val);
          }
        }

        set.add(root.val);

        getMinimumDifference(root.left);
        getMinimumDifference(root.right);

        return min;
      }
    }

  }

}
