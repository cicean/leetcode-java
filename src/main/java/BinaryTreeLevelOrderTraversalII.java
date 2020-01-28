import java.util.*;

/*
 107	Binary Tree Level Order Traversal II	31.1%	Easy
 Problem:    Binary Tree Level Order Traversal II
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/binary-tree-level-order-traversal-ii/
 Notes:
 Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 (ie, from left to right, level by level from leaf to root).
 For example:
 Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
 return its bottom-up level order traversal as:
 [
  [15,7]
  [9,20],
  [3],
 ]
 
 Solution: Queue version. On the basis of 'Binary Tree Level Order Traversal', reverse the final vector.
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

public class BinaryTreeLevelOrderTraversalII {
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
        Collections.reverse(res);
        return res;
    }

    //DFS
    public List<List<Integer>> levelOrderBottom_2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        Collections.reverse(res);
        return res;
    }

    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
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
		BinaryTreeLevelOrderTraversalII slt = new BinaryTreeLevelOrderTraversalII();
		print(slt.levelOrderBottom(t1));
	}

}
