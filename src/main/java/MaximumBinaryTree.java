/**
 * 654. Maximum Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

 The root is the maximum number in the array.
 The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 Construct the maximum tree by the given array and output the root node of this tree.

 Example 1:
 Input: [3,2,1,6,0,5]
 Output: return the tree root node representing the following tree:

 6
 /   \
 3     5
 \    /
 2  0
 \
 1
 Note:
 The size of the given array will be in the range [1,1000].
 */

public class MaximumBinaryTree {

  /**

  Approach #1 Recursive Solution[Accepted]

  The current solution is very simple. We make use of a function construct(nums, l, r), which returns the maximum binary tree consisting of numbers within the indices ll and rr in the given numsnums array(excluding the r^{th}r
​th
​​  element).

  The algorithm consists of the following steps:

  Start with the function call construct(nums, 0, n). Here, nn refers to the number of elements in the given numsnums array.

  Find the index, max_i, of the largest element in the current range of indices (l:r-1)(l:r−1). Make this largest element, $nums[max_i]nums as the local root node.

  Determine the left child using construct(nums, l, max_i). Doing this recursively finds the largest element in the subarray left to the current largest element.

  Similarly, determine the right child using construct(nums, max_i + 1, r).

  Return the root node to the calling function.

   Complexity Analysis

   Time complexity : O(n^2). The function construct is called nn times.
   At each level of the recursive tree, we traverse over all the nn elements to find the maximum element.
   In the average case, there will be a log(n)log(n) levels leading to a complexity of O(nlog(n)).
   In the worst case, the depth of the recursive tree can grow upto nn, which happens in the case of a sorted numsnums array, giving a complexity of O(n^2).

   Space complexity : O(n). The size of the setset can grow upto nn in the worst case. In the average case, the size will be log(n) for nn elements in nums, giving an average case complexity of O(log(n))

   Analysis written by: @vinod23

  */

  public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
      return construct(nums, 0, nums.length);
    }
    public TreeNode construct(int[] nums, int l, int r) {
      if (l == r)
        return null;
      int max_i = max(nums, l, r);
      TreeNode root = new TreeNode(nums[max_i]);
      root.left = construct(nums, l, max_i);
      root.right = construct(nums, max_i + 1, r);
      return root;
    }
    public int max(int[] nums, int l, int r) {
      int max_i = l;
      for (int i = l; i < r; i++) {
        if (nums[max_i] < nums[i])
          max_i = i;
      }
      return max_i;
    }
  }


}
