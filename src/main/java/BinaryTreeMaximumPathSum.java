/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 28, 2015
 Problem:    Binary Tree Maximum Path Sum
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/binary-tree-maximum-path-sum/
 Notes:
 Given a binary tree, find the maximum path sum.
 The path may start and end at any node in the tree.
 For example:
 Given the below binary tree,
   1
  / \
 2   3
 Return 6.
 Solution: Recursion...
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

public class BinaryTreeMaximumPathSum {

	int maxValue;

	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
	}

	private int maxPathDown(TreeNode node) {
		if (node == null) return 0;
		int left = Math.max(0, maxPathDown(node.left));
		int right = Math.max(0, maxPathDown(node.right));
		maxValue = Math.max(maxValue, left + right + node.val);
		return Math.max(left, right) + node.val;
	}
	
	 public int maxPathSum(TreeNode root) {
	        int[] res = new int[1];
	        res[0] = Integer.MIN_VALUE;
	        maxPathSumRe(root, res);
	        return res[0];
	    }
	    int maxPathSumRe(TreeNode root, int[] res) {
	        if (root == null) return 0;
	        int left = maxPathSumRe(root.left, res);
	        int right = maxPathSumRe(root.right, res);
	        res[0] = Math.max(res[0], root.val + Math.max(left, 0) + Math.max(right, 0));
	        return Math.max(root.val, root.val + Math.max(left, right));
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 =new TreeNode(1);
		TreeNode t2 =new TreeNode(2);
		TreeNode t3 =new TreeNode(3);
		
		t1.left=t2;t1.right=t3;
		
		BinaryTreeMaximumPathSum slt =new BinaryTreeMaximumPathSum();
		System.out.print(slt.maxPathSum(t1));

	}

}
