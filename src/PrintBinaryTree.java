import java.util.*;

/**
 * 655. Print Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Print a binary tree in an m*n 2D string array following these rules:

 The row number m should be equal to the height of the given binary tree.
 The column number n should always be an odd number.
 The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 Each unused space should contain an empty string "".
 Print the subtrees following the same rules.
 Example 1:
 Input:
 1
 /
 2
 Output:
 [["", "1", ""],
 ["2", "", ""]]
 Example 2:
 Input:
 1
 / \
 2   3
 \
 4
 Output:
 [["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
 Example 3:
 Input:
 1
 / \
 2   5
 /
 3
 /
 4
 Output:

 [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 Note: The height of binary tree is in the range of [1, 10].
 */
public class PrintBinaryTree {

  /**

  Approach #1 Recursive Solution[Accepted]

  We start by initializing a resres array with the dimensions being heightheightx2^{height}-12
      ​height
​​ −1. Here, heightheight refers to the number of levels in the given tree. In order to fill this resres array with the required elements, initially, we fill the complete array with "" . After this we make use of a recursive function fill(res, root, i, l, r) which fills the resres array such that the current element has to be filled in i^{th}i
​th
​​  row, and the column being the middle of the indices ll and rr, where ll and rr refer to the left and the right boundaries of the columns in which the current element can be filled.

  In every recursive call, we do as follows:

  If we've reached the end of the tree, i.e. if root==null, return.

  Determine the column in which the current element(rootroot) needs to be filled, which is the middle of ll and rr, given by say, jj. The row number is same as ii. Put the current element at res[i][j]res[i][j].

  Make the recursive call for the left child of the rootroot using fill(res, root.left, i + 1, l, (l + r) / 2).

  Make the recursive call for the right child of the rootroot using fill(res, root.right, i + 1, (l + r + 1) / 2, r).

  Note, that in the last two recursive calls, we update the row number(level of the tree). This ensures that the child nodes fit into the correct row. We also update the column boundaries appropriately based on the ll and rr values.

  Further, to determine the heightheight also, we make use of recursive funtion getHeight(root), which returns the height of the tree starting from the rootroot node. We traverse into all the branches possible in the tree recursively and find the depth of the longest branch.

  At the end, we convert the resres array into the required list format, before returning the results.

   Complexity Analysis

   Time complexity : O(h*2^h)O(h∗2​h). We need to fill the resres array of size h*2^h −1 . Here, h refers to the height of the given tree.

   Space complexity : O(h*2^h)O(h∗2h). resres array of size h*2^h −1 is used.

   */

  public class Solution {
    public List<List<String>> printTree(TreeNode root) {
      int height = getHeight(root);
      String[][] res = new String[height][(1 << height) - 1];
      for(String[] arr:res)
        Arrays.fill(arr,"");
      List<List<String>> ans = new ArrayList<>();
      fill(res, root, 0, 0, res[0].length);
      for(String[] arr:res)
        ans.add(Arrays.asList(arr));
      return ans;
    }
    public void fill(String[][] res, TreeNode root, int i, int l, int r) {
      if (root == null)
        return;
      res[i][(l + r) / 2] = "" + root.val;
      fill(res, root.left, i + 1, l, (l + r) / 2);
      fill(res, root.right, i + 1, (l + r + 1) / 2, r);
    }
    public int getHeight(TreeNode root) {
      if (root == null)
        return 0;
      return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
  }

  /**
  Approach #2 Using queue(BFS)[Accepted]

  Algorithm

  We can also solve the problem by making use of Breadth First Search's idea. For this, we make use of a class ParamsParams which stores the parameters of a nodenode of the tree, including its value, its level in the tree(ii), and the left(ll) and right(rr) boundaries of the columns in which this element can be filled in the result to be returned.

  We start by initializing a resres array as in the previous approach. After this, we add the parametrized rootroot of the tree into a queuequeue. After this, we do the following at every step.

  Remove an element, $$p$, from the front of the queuequeue.

  Add this element at its correct position in the resres array given by res[p.i][(p.l + p.r) / 2]res[p.i][(p.l+p.r)/2]. Here, the values ii, ll and rr refer to the column/level number, and the left and right boundaries permissible for putting the current node into resres. These are obtained from the node's parameters, which have been associated with it before putting it into the queuequeue.

  If the left child of pp exists, put it at the back of the queuequeue, in a parametized form, by appropriately updating the level as the next level and the boundaries permissible as well.

  If the right child of pp exists, put it at the back of the queuequeue, in a parametized form, by appropriately updating the level as the next level and the boundaries permissible as well.

  Continue steps 1. to 4. till the queuequeue becomes empty.

  At the end, we again convert the resres array into the required list format, before returning the results.

  Java

   Complexity Analysis

   Time complexity : O(h*2^h)O(h∗2^h). We need to fill the resres array of size h*2^h−1. Here, h refers to the height of the given tree.

   Space complexity : O(h*2^h)O(h∗2^​h). resres array of size h*2^h - 1 is used.

  public class Solution
  /**
   * Definition for a binary tree node.
   * public class TreeNode {
   *     int val;
   *     TreeNode left;
   *     TreeNode right;
   *     TreeNode(int x) { val = x; }
   * }
   */
  public class Solution_2 {
    class Params {
      Params(TreeNode n, int ii, int ll, int rr) {
        root = n;
        i = ii;
        l = ll;
        r = rr;
      }
      TreeNode root;
      int i, l, r;
    }
    public List <List< String >> printTree(TreeNode root) {
      int height = getHeight(root);
      System.out.println(height);
      String[][] res = new String[height][(1 << height) - 1];
      for (String[] arr: res)
        Arrays.fill(arr, "");
      List < List < String >> ans = new ArrayList < > ();
      fill(res, root, 0, 0, res[0].length);
      for (String[] arr: res)
        ans.add(Arrays.asList(arr));
      return ans;
    }
    public void fill(String[][] res, TreeNode root, int i, int l, int r) {
      Queue < Params > queue = new LinkedList();
      queue.add(new Params(root, 0, 0, res[0].length));
      while (!queue.isEmpty()) {
        Params p = queue.remove();
        res[p.i][(p.l + p.r) / 2] = "" + p.root.val;
        if (p.root.left != null)
          queue.add(new Params(p.root.left, p.i + 1, p.l, (p.l + p.r) / 2));
        if (p.root.right != null)
          queue.add(new Params(p.root.right, p.i + 1, (p.l + p.r + 1) / 2, p.r));
      }
    }
    public int getHeight(TreeNode root) {
      Queue < TreeNode > queue = new LinkedList();
      queue.add(root);
      int height = 0;
      while (!queue.isEmpty()) {
        height++;
        Queue < TreeNode > temp = new LinkedList();
        while (!queue.isEmpty()) {
          TreeNode node = queue.remove();
          if (node.left != null)
            temp.add(node.left);
          if (node.right != null)
            temp.add(node.right);
        }
        queue = temp;
      }
      return height;
    }
  }


}
