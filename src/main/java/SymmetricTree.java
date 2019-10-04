/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Dec 25, 2014
 Problem:    Symmetric Tree
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/symmetric-tree/
 Notes:
 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 For example, this binary tree is symmetric:
     1
    / \
   2   2
  / \ / \
 3  4 4  3
 But the following is not:
    1
   / \
  2   2
   \   \
   3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.
 Solution: 1. Recursive solution
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

public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return solve (root.left, root.right);
    }
    public boolean solve(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null && t2 != null || t1 != null && t2 == null || t1.val != t2.val) return false;
        return solve(t1.left, t2.right) && solve(t1.right, t2.left);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(2);
		TreeNode t4 = new TreeNode(3);
		TreeNode t5 = new TreeNode(4);
		TreeNode t6 = new TreeNode(4);
		TreeNode t7 = new TreeNode(3);
		
		t1.left=t2;
		t1.right =t3;
		t2.left = t4;
		t2.right = t5;
		t3.left =t6;
		t3.right =t7;
		
		TreeNode p1 = new TreeNode(1);
		TreeNode p2 = new TreeNode(2);
		TreeNode p3 = new TreeNode(2);
		TreeNode p4 = new TreeNode(3);
		TreeNode p5 = new TreeNode(3);
		
		p1.left=p2;
		p1.right = p3;
		p2.right =p4;
		p3.right = p5;
		
		SymmetricTree slt = new SymmetricTree();
		
		System.out.println(slt.isSymmetric(t1));
		System.out.println(slt.isSymmetric(p1));
		
		
	}

}
