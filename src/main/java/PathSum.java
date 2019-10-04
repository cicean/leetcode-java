/*
 112	Path Sum	29.8%	Easy
 Problem:    Path Sum
 Difficulty: easy
 Source:     http://www.leetcode.com/onlinejudge
 Notes:
 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up 
 all the values along the path equals the given sum.
 For example:
 Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 
 Solution: Recursion.
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || 
               hasPathSum(root.right, sum - root.val);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(5);
		TreeNode t2 = new TreeNode(4);
		TreeNode t3 = new TreeNode(8);
		TreeNode t4 = new TreeNode(11);
		TreeNode t5 = new TreeNode(13);
		TreeNode t6 = new TreeNode(4);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(2);
		TreeNode t9 = new TreeNode(1);
		
		t1.left = t2;
		t1.right = t3;
		t2.left =  t4;
		t3.left = t5;
		t3.right = t6;
		t4.left = t7;
		t4.right = t8;
		t6.right = t9;
		
		PathSum slt = new PathSum();
		int sum = 22;
		System.out.print(slt.hasPathSum(t1, sum));
	}

}
