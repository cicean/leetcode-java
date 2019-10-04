import java.util.*;

/**
 * 671. Second Minimum Node In a Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

 Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

 If no such second minimum value exists, output -1 instead.

 Example 1:
 Input:
 2
 / \
 2   5
 / \
 5   7

 Output: 5
 Explanation: The smallest value is 2, the second smallest value is 5.
 Example 2:
 Input:
 2
 / \
 2   2

 Output: -1
 Explanation: The smallest value is 2, but there isn't any second smallest value.

 */

public class SecondMinimumNodeInaBinaryTree {

  /*
  Approach #1: Brute Force [Accepted]

Intuition and Algorithm

Traverse the tree with a depth-first search, and record every unique value in the tree using a Set structure uniques.

Then, we'll look through the recorded values for the second minimum. The first minimum must be \text{root.val}root.val.

Complexity Analysis

Time Complexity: O(N)O(N), where NN is the total number of nodes in the given tree. We visit each node exactly once, and scan through the O(N)O(N) values in unique once.

Space Complexity: O(N)O(N), the information stored in uniques.
   */

  public void dfs(TreeNode root, Set<Integer> uniques) {
    if (root != null) {
      uniques.add(root.val);
      dfs(root.left, uniques);
      dfs(root.right, uniques);
    }
  }
  public int findSecondMinimumValue(TreeNode root) {
    Set<Integer> uniques = new HashSet<Integer>();
    dfs(root, uniques);

    int min1 = root.val;
    long ans = Long.MAX_VALUE;
    for (int v : uniques) {
      if (min1 < v && v < ans) ans = v;
    }
    return ans < Long.MAX_VALUE ? (int) ans : -1;
  }

  /*
  Approach #2: Ad-Hoc [Accepted]

Intuition and Algorithm

Let \text{min1 = root.val}min1 = root.val. When traversing the tree at some node, \text{node}node, if node.val > min1node.val > min1, we know all values in the subtree at \text{node}node are at least \text{node.val}node.val, so there cannot be a better candidate for the second minimum in this subtree. Thus, we do not need to search this subtree.

Also, as we only care about the second minimum \text{ans}ans, we do not need to record any values that are larger than our current candidate for the second minimum, so unlike Approach #1 we can skip maintaining a Set of values(uniques) entirely.

Complexity Analysis

Time Complexity: O(N)O(N), where NN is the total number of nodes in the given tree. We visit each node at most once.

Space Complexity: O(N)O(N). The information stored in \text{ans}ans and \text{min1}min1 is O(1)O(1), but our depth-first search may store up to O(h) = O(N)O(h)=O(N) information in the call stack, where hh is the height of the tree.
   */

  int min1;
  long ans = Long.MAX_VALUE;

  public void dfs(TreeNode root) {
    if (root != null) {
      if (min1 < root.val && root.val < ans) {
        ans = root.val;
      } else if (min1 == root.val) {
        dfs(root.left);
        dfs(root.right);
      }
    }
  }
  public int findSecondMinimumValue_2(TreeNode root) {
    min1 = root.val;
    dfs(root);
    return ans < Long.MAX_VALUE ? (int) ans : -1;
  }

}
