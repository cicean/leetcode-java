/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    100 Same Tree
 Difficulty: easy
 Source:     http://leetcode.com/onlinejudge#question_100
 Notes:
 Given two binary trees, write a function to check if they are equal or not.
 Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 Solution: recursion.
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

public class SameTree {
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if ((p != null && q == null) || (p == null && q != null)) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

	//DFS
	public boolean isSameTree_dfs(TreeNode p, TreeNode q) {
		Stack<TreeNode> stack_p = new Stack <> ();
		Stack<TreeNode> stack_q = new Stack <> ();
		if (p != null) stack_p.push( p ) ;
		if (q != null) stack_q.push( q ) ;
		while (!stack_p.isEmpty() && !stack_q.isEmpty()) {
			TreeNode pn = stack_p.pop() ;
			TreeNode qn = stack_q.pop() ;
			if (pn.val != qn.val) return false ;
			if (pn.right != null) stack_p.push(pn.right) ;
			if (qn.right != null) stack_q.push(qn.right) ;
			if (stack_p.size() != stack_q.size()) return false ;
			if (pn.left != null) stack_p.push(pn.left) ;
			if (qn.left != null) stack_q.push(qn.left) ;
			if (stack_p.size() != stack_q.size()) return false ;
		}
		return stack_p.size() == stack_q.size() ;
	}

	//BFS, inspired from above
	public boolean isSameTree_bfs(TreeNode p, TreeNode q) {
		if (p == null || q == null) {
			return p == q;
		}
		Queue<TreeNode> pQueue = new LinkedList<TreeNode>();
		Queue<TreeNode> qQueue = new LinkedList<TreeNode>();
		pQueue.offer(p);
		qQueue.offer(q);
		TreeNode pn;
		TreeNode qn;
		while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
			pn = pQueue.poll();
			qn = qQueue.poll();
			if (pn.val != qn.val) return false;
			if (pn.left != null) pQueue.add(pn.left);
			if (qn.left != null) qQueue.add(qn.left);
			if (pQueue.size() != qQueue.size()) return false;
			if (pn.right != null) pQueue.add(pn.right);
			if (qn.right != null) qQueue.add(qn.right);
			if (pQueue.size() != qQueue.size()) return false;
		}
		return true;
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
