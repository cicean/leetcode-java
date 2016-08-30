/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    Same Tree
 Difficulty: easy
 Source:     http://leetcode.com/onlinejudge#question_100
 Notes:
 Given two binary trees, write a function to check if they are equal or not.
 Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 Solution: recursion.
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

public class SameTree {
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if ((p != null && q == null) || (p == null && q != null)) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(1);
		TreeNode t5 = new TreeNode(2);
		TreeNode t6 = new TreeNode(3);
		t1.left = t2;
		t1.right = t3;
		t4.left = t5;
		t4.right = t6;
		
		SameTree slt = new SameTree();
		System.out.println(slt.isSameTree(t1,t4));
	}

}
