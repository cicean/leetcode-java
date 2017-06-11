import java.util.Stack;

/*
 98	Validate Binary Search Tree	20.7%	Medium
 Problem:    Validate Binary Search Tree
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/validate-binary-search-tree/
 Notes:
 Given a binary tree, determine if it is a valid binary search tree (BST).
 Assume a BST is defined as follows:
 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Solution: Recursion. 1. Add lower & upper bound. O(n)
 2. Inorder traversal with one additional parameter (value of predecessor). O(n)
 */

/**
 * Definition for binary tree public class TreeNode { int val; TreeNode left;
 * TreeNode right; TreeNode(int x) { val = x; } }
 */

public class ValidateBinarySearchTree {

	// recursive

	public class Solution {
		public boolean isValidBST(TreeNode root) {
			return isValidBSTRe(root, null, null);
		}

		private boolean isValidBSTRe(TreeNode node, TreeNode left,
				TreeNode right) {
			if (node == null)
				return true;
			return (left == null || left.val < node.val)
					&& (right == null || right.val > node.val)
					&& isValidBSTRe(node.left, left, node)
					&& isValidBSTRe(node.right, node, right);
		}
	}

	public boolean isValidBST(TreeNode root) {

		return isValidBSTRe(root, null, null);

	}

	public boolean isValidBSTRe(TreeNode root, Integer left, Integer right) {
		if (root == null)
			return true;
		return (left == null || root.val > left)
				&& (right == null || root.val < right)
				&& isValidBSTRe(root.left, left, root.val)
				&& isValidBSTRe(root.right, root.val, right);
	}

	// inoder recusive
	boolean isValidBST_(TreeNode root) {
		long[] val = new long[1];
		val[0] = (long) Integer.MIN_VALUE - 1;
		return inorder(root, val);
	}

	boolean inorder(TreeNode root, long[] val) {
		if (root == null)
			return true;
		if (inorder(root.left, val) == false)
			return false;
		if (root.val <= val[0])
			return false;
		val[0] = root.val;
		return inorder(root.right, val);
	}

	// inorder non- recusive
	public boolean isValidBST_2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		TreeNode pre = null;
		while (!stack.isEmpty() || cur != null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				TreeNode p = stack.pop();
				if (pre != null && p.val <= pre.val) {
					return false;
				}
				pre = p;
				cur = p.right;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(8);
		TreeNode t2 = new TreeNode(5);
		TreeNode t3 = new TreeNode(Integer.MAX_VALUE);
		TreeNode t4 = new TreeNode(Integer.MIN_VALUE);
		TreeNode t5 = new TreeNode(9);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t3.left = t5;
		ValidateBinarySearchTree slt = new ValidateBinarySearchTree();
		System.out.println(slt.isValidBST(t1));
	}

}
