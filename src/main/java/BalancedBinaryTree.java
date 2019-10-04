import java.util.List;

/*
 110	Balanced Binary Tree	32.0%	Easy
 Problem:    Balanced Binary Tree
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/balanced-binary-tree/
 Notes:
 Given a binary tree, determine if it is height-balanced.
 For this problem, a height-balanced binary tree is defined as a binary tree in which 
 the depth of the two subtrees of every node never differ by more than 1.
 Solution: DFS.
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

public class BalancedBinaryTree {
	
	public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return isBalancedRe(root) > -1;
    }
    public int isBalancedRe(TreeNode root) {
        if (root == null) return 0;
        int left = isBalancedRe(root.left);
        int right = isBalancedRe(root.right);
        if (left == -1 || right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
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
		
		BalancedBinaryTree slt = new BalancedBinaryTree();
		System.out.print(slt.isBalanced(t1));
		
		
	}

}
