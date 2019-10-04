package facebook;

import datastructure.TreeNode;
import java.util.*;

/**
 * Shortest distance between two nodes in BST
 2.8
 Given a Binary Search Tree and two keys in it. Find the distance between two nodes with
 given two keys. It may be assumed that both keys exist in BST.

 BST



 Input :  Root of above tree
 a = 3, b = 9
 Output : 4
 Distance between 3 and 9 in
 above BST is 4.

 Input :  Root of above tree
 a = 9, b = 25
 Output : 3
 Distance between 9 and 25 in
 above BST is 3.
 Recommended: Please try your approach on {IDE} first, before moving on to the solution.
 We have discussed distance between two nodes in binary tree. The time complexity of this solution is O(n)

 In case of BST, we can find distance faster. We start from root and for every node, we do following.

 If both keys are greater than current node, we move to right child of current node.
 If both keys are smaller than current node, we move to left child of current node.
 If one keys is smaller and other key is greater, current node is Lowest Common Ancestor (LCA)
 of two nodes. We find distances of current node from two keys and return sum of the distances.
 */

public class ShortestdistancebetweentwonodesinBST {

  public int distanceBetween2(TreeNode root, TreeNode A, TreeNode B) {
    if (root == null) return 0;

    //both Keys lie in left
    if (root.val > A.val && root.val > B.val) {
      return distanceBetween2(root.left, A, B);
    }

    //both Keys lie in right
    if (root.val < A.val && root.val < B.val) {
      return distanceBetween2(root.right, A, B);
    }

    // Lie in opposite directions (Root is
    // LCA of two nodes)
    if (root.val >= A.val && root.val <= B.val) {
      return distanceFromRoot(root, A) + distanceFromRoot(root, B);
    }

    return -1;
  }

  // This function returns distance of x from
  // root. This function assumes that x exists
  // in BST and BST is not NULL.
  public int distanceFromRoot(TreeNode root, TreeNode p) {
    if (root.val == p.val) return 0;
    else if (root.val > p.val) return 1 + distanceFromRoot(root.left, p);
    return  1 + distanceFromRoot(root.right, p);
  }

  // This function make sure that a is smaller
  // than b before making a call to findDistWrapper()
  public int ShortestdistancebetweentwonodesinBST(TreeNode root, TreeNode A, TreeNode B) {
    if (A.val > B.val) return distanceBetween2(root, B, A);
    return distanceBetween2(root, A, B);
  }

}
