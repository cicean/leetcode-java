import java.util.*;

/*
 102	Binary Tree Level Order Traversal	29.3%	Easy
 Problem:    Binary Tree Level Order Traversal
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/binary-tree-level-order-traversal/
 Notes:
 Given a binary tree, return the level order traversal of its nodes' values. 
 (ie, from left to right, level by level).
 For example:
 Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
 return its level order traversal as:
 [
  [3],
  [9,20],
  [15,7]
 ]
 
 Solution: 1. Use queue. In order to seperate the levels, use 'NULL' as the end indicator of one level.
           2. DFS.
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

public class BinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder_1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(null);
        List<Integer> level = new ArrayList<Integer>();
        
        while(true) {
            TreeNode node = q.poll();
            if (node != null) {
                level.add(node.val);
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            } else {
                res.add(level);
                level = new ArrayList<Integer>();
                if(q.isEmpty()==true) break;
                q.offer(null);
            }
        }
        return res;
    }
    
    public List<List<Integer>> levelOrder_2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        levelOrderRe(root, 0, res);
        return res;
    }
    public void levelOrderRe(TreeNode root, int level, List<List<Integer>> res) {
        if(root == null) return;
        if(level == res.size()) res.add(new ArrayList<Integer>());
        res.get(level).add(root.val);
        levelOrderRe(root.left, level+1, res);
        levelOrderRe(root.right,level+1, res);
    }
    
    public static void print(List<List<Integer>> res) {
    	for (List<Integer> l : res) {
    		System.out.print("[");
    		for (Integer i : l) {
				System.out.print(i + ",");
			}
    		System.out.println("]");
    	}
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
		BinaryTreeLevelOrderTraversal slt = new BinaryTreeLevelOrderTraversal();
		print(slt.levelOrder_1(t1));
	}

}
