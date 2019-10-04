/*
 104. Maximum Depth of Binary Tree   QuestionEditorial Solution  My Submissions
Total Accepted: 185221
Total Submissions: 370620
Difficulty: Easy
Contributors: Admin
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Hide Company Tags LinkedIn Uber Apple Yahoo
Hide Tags Tree Depth-first Search
Hide Similar Problems (E) Balanced Binary Tree (E) Minimum Depth of Binary Tree

 */

import java.util.*;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class MaximumDepthofBinaryTree {

	public int maxDepth(TreeNode root) {
		if(root==null){
			return 0;
		}
		return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
	}

	public int maxDepth_I(TreeNode root) {
		if (root == null)
			return 0;

		Deque<TreeNode> stack = new LinkedList<TreeNode>();

		stack.push(root);
		int count = 0;

		while (!stack.isEmpty()) {
			int size = stack.size();
			while (size-- > 0) {
				TreeNode cur = stack.pop();
				if (cur.left != null)
					stack.addLast(cur.left);
				if (cur.right != null)
					stack.addLast(cur.right);
			}
			count++;

		}
		return count;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(3);
		TreeNode t2 = new TreeNode(9);
		TreeNode t3 = new TreeNode(20);
		TreeNode t4 = new TreeNode(15);
		TreeNode t5 = new TreeNode(7);
		t1.left = t2;
		t1.right = t3;
		t3.left = t4;
		t3.right = t5;
		MaximumDepthofBinaryTree slt = new MaximumDepthofBinaryTree();
		System.out.print(slt.maxDepth(t1));
	}

}
