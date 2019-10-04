/*
 116	Populating Next Right Pointers in Each Node	36.2%	Medium
 Problem:    Populating Next Right Pointers in Each Node
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_116
 Notes:
 Given a binary tree
 struct TreeLinkNode {
    TreeLinkNode *left;
    TreeLinkNode *right;
    TreeLinkNode *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 Initially, all next pointers are set to NULL.
 Note:
 You may only use constant extra space.
 You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 For example,
 Given the following perfect binary tree,
      1
    /  \
   2    3
  / \  / \
 4  5  6  7
 After calling your function, the tree should look like:
      1 -> NULL
    /  \
   2 -> 3 -> NULL
  / \  / \
 4->5->6->7 -> NULL
 Solution: 1. Iterative: Two 'while' loops.
           2. Iterative: Queue. Use extra space.
           3. Recursive: DFS. Defect: Use extra stack space for recursion.
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

public class PopulatingNextRightPointersinEachNode {

	public void connect(TreeLinkNode root) {
		if(root == null) return;

		recur(root.left, root.right);
	}

	public void recur(TreeLinkNode left, TreeLinkNode right) {
		if(left != null && right != null) {
			left.next = right;
			if(left.right != null) left.right.next = right.left;
			recur(left.right, right.left);
			recur(left.left, left.right);
			recur(right.left, right.right);
		}
	}

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
		TreeLinkNode n5 = new TreeLinkNode(6);
		TreeLinkNode n6 = new TreeLinkNode(7);
		n0.left = n1; n0.right = n2;
		n1.left = n3; n1.right = n4;
		n2.left = n5; n2.right = n6;
		
		PopulatingNextRightPointersinEachNode slt = new PopulatingNextRightPointersinEachNode();
		print(n0);
		slt.connect(n0);
		print(n0);
		
	}

}
