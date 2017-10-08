import java.util.*;

/**
 * 653. Two Sum IV - Input is a BST
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a Binary Search Tree and a target number, return true if there exist two elements in the BST
 such that their sum is equal to the given target.

 Example 1:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 9

 Output: True
 Example 2:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 28

 Output: False
 See
 */

public class TwoSumIV {

  /**

  Approach #1 Using HashSet[Accepted]

  The simplest solution will be to traverse over the whole tree and consider every possible pair of nodes to determine if they can form the required sum kk. But, we can improve the process if we look at a little catch here.

  If the sum of two elements x + yx+y equals kk, and we already know that xx exists in the given tree, we only need to check if an element yy exists in the given tree, such that y = k - xy=k−x. Based on this simple catch, we can traverse the tree in both the directions(left child and right child) at every step. We keep a track of the elements which have been found so far during the tree traversal, by putting them into a setset.

  For every current node with a value of pp, we check if k-pk−p already exists in the array. If so, we can conclude that the sum kk can be formed by using the two elements from the given tree. Otherwise, we put this value pp into the setset.

  If even after the whole tree's traversal, no such element pp can be found, the sum kk can't be formed by using any two elements.

   Complexity Analysis

   Time complexity : O(n). The entire tree is traversed only once in the worst case. Here, nn refers to the number of nodes in the given tree.

   Space complexity : O(n). The size of the setset can grow upto nn in the worst case.

  */

  public class Solution {
    public boolean findTarget(TreeNode root, int k) {
      Set< Integer > set = new HashSet();
      return find(root, k, set);
    }
    public boolean find(TreeNode root, int k, Set < Integer > set) {
      if (root == null)
        return false;
      if (set.contains(k - root.val))
        return true;
      set.add(root.val);
      return find(root.left, k, set) || find(root.right, k, set);
    }
  }


  /**
  Approach #2 Using BFS and HashSet [Accepted]

  Algorithm

  In this approach, the idea of using the setset is the same as in the last approach. But, we can carry on the traversal in a Breadth First Search manner, which is a very common traversal method used in Trees. The way BFS is used can be summarized as given below. We start by putting the root node into a queuequeue. We also maintain a setset similar to the last approach. Then, at every step, we do as follows:

  Remove an element, pp, from the front of the queuequeue.

  Check if the element k-pk−p already exists in the setset. If so, return True.

      Otherwise, add this element, pp to the setset. Further, add the right and the left child nodes of the current node to the back of the queuequeue.

  Continue steps 1. to 3. till the queuequeue becomes empty.

      Return false if the queuequeue becomes empty.

  By following this process, we traverse the tree on a level by level basis.

   Complexity Analysis

   Time complexity : O(n). We need to traverse over the whole tree once in the worst case. Here, nn refers to the number of nodes in the given tree.

   Space complexity : O(n). The size of the setset can grow atmost upto nn.

  */

  public class Solution_2 {
    public boolean findTarget(TreeNode root, int k) {
      Set < Integer > set = new HashSet();
      Queue < TreeNode > queue = new LinkedList();
      queue.add(root);
      while (!queue.isEmpty()) {
        if (queue.peek() != null) {
          TreeNode node = queue.remove();
          if (set.contains(k - node.val))
            return true;
          set.add(node.val);
          queue.add(node.right);
          queue.add(node.left);
        } else
          queue.remove();
      }
      return false;
    }
  }

  /**
   *

  Approach #3 Using BST [Accepted]

  Algorithm

  In this approach, we make use of the fact that the given tree is a Binary Search Tree. Now, we know that the inorder traversal of a BST gives the nodes in ascending order. Thus, we do the inorder traversal of the given tree and put the results in a listlist which contains the nodes sorted in ascending order.

  Once this is done, we make use of two pointers ll and rr pointing to the beginning and the end of the sorted listlist. Then, we do as follows:

  Check if the sum of the elements pointed by ll and rr is equal to the required sum kk. If so, return a True immediately.

      Otherwise, if the sum of the current two elements is lesser than the required sum kk, update ll to point to the next element. This is done, because, we need to increase the sum of the current elements, which can only be done by increasing the smaller number.

      Otherwise, if the sum of the current two elements is larger than the required sum kk, update rr to point to the previous element. This is done, because, we need to decrease the sum of the current elements, which can only be done by reducing the larger number.

  Continue steps 1. to 3. till the left pointer ll crosses the right pointer rr.

  If the two pointers cross each other, return a False value.

  Note that we need not increase the larger number or reduce the smaller number in any case. This happens because, in case, a number larger than the current list[r]list[r] is needed to form the required sum kk, the right pointer could not have been reduced in the first place. The similar argument holds true for not reducing the smaller number as well.

  */

  public class Solution_3 {
    public boolean findTarget(TreeNode root, int k) {
      List < Integer > list = new ArrayList();
      inorder(root, list);
      int l = 0, r = list.size() - 1;
      while (l < r) {
        int sum = list.get(l) + list.get(r);
        if (sum == k)
          return true;
        if (sum < k)
          l++;
        else
          r--;
      }
      return false;
    }
    public void inorder(TreeNode root, List < Integer > list) {
      if (root == null)
        return;
      inorder(root.left, list);
      list.add(root.val);
      inorder(root.right, list);
    }
  }


}
