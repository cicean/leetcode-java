/*
 117	Populating Next Right Pointers in Each Node II	32.0%	Hard
 Problem:    Populating Next Right Pointers in Each Node II
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 Notes:
 Follow up for problem "Populating Next Right Pointers in Each Node".
 What if the given tree could be any binary tree? Would your previous solution still work?
 Note:
 You may only use constant extra space.
 For example,
 Given the following binary tree,
     1
    /  \
   2    3
  / \    \
 4   5    7
 After calling your function, the tree should look like:
     1 -> NULL
    /  \
   2 -> 3 -> NULL
  / \    \
 4-> 5 -> 7 -> NULL
 Solution: 1. iterative way with CONSTANT extra space.
           2. iterative way + queue. Contributed by SUN Mian(����).
           3. recursive solution.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

public class PopulatingNextRightPointersinEachNodeII {

	public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode dummy = new TreeLinkNode(-1);
        TreeLinkNode pre = dummy;
        TreeLinkNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                pre.next = cur.left;
                pre = pre.next;
            }
            if (cur.right != null) {
                pre.next = cur.right;
                pre = pre.next;
            }
            cur = cur.next;
        }
        connect(dummy.next);
    }

	class Solution {
		public Node connect(Node root) {
			if(root!=null) {
				connect(root, null);
			}
			return root;
		}

		void connect(Node node, Node right) {

			node.next = right;

			if(node.left==null)
				return;
			connect(node.left, node.right);
			connect(node.right, right == null ? null : right.left);
		}
	}

	public void connect_1(TreeLinkNode root) {
		Map<Integer, TreeLinkNode> res = new HashMap<>();
		connectRe(root, 0, res);
		return;
	}

	private void connectRe(TreeLinkNode node, int level, Map<Integer, TreeLinkNode> res) {
		if (node == null) return;

		if (res.containsKey(level)) {
			res.get(level).next = node;
		}
		res.put(level, node);
		level++;
		connectRe(node.left, level, res);
		connectRe(node.right, level, res);
	}
	
	public static  void print(TreeLinkNode node) {
    	if (null != node) {
    		System.out.print(node.val);
    		System.out.print("[");
    		if (null == node.left) {
    			System.out.print("null");
    		} else {
    			System.out.print(node.left.val);
    		}
    		System.out.print(",");
    		if (null == node.right) {
    			System.out.print("null");
    		} else {
    			System.out.print(node.right.val);
    		}
    		System.out.print(",");
    		if (null == node.next) {
    			System.out.print("null");
    		} else {
    			System.out.print(node.next.val);
    		}
    		System.out.println("]");
    		print(node.left);
    		print(node.right);
    	}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeLinkNode n0 = new TreeLinkNode(1);
		TreeLinkNode n1 = new TreeLinkNode(2);
		TreeLinkNode n2 = new TreeLinkNode(3);
		TreeLinkNode n3 = new TreeLinkNode(4);
		TreeLinkNode n4 = new TreeLinkNode(5);
		//TreeLinkNode n5 = new TreeLinkNode(6);
		TreeLinkNode n6 = new TreeLinkNode(7);
		n0.left = n1; n0.right = n2;
		n1.left = n3; n1.right = n4;
		n2.right = n6;
		
		PopulatingNextRightPointersinEachNode slt = new PopulatingNextRightPointersinEachNode();
		print(n0);
		slt.connect(n0);
		print(n0);
		
	}

}
