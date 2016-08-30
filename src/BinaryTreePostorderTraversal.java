 import java.util.*;

/*
 145	Binary Tree Postorder Traversal	32.4%	Hard
 Problem:    Binary Tree Postorder Traversal 
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/binary-tree-postorder-traversal/
 Notes:
 Given a binary tree, return the postorder traversal of its nodes' values.
 For example:
 Given binary tree {1,#,2,3},
    1
     \
      2
     /
    3
 return [3,2,1].
 Note: Recursive solution is trivial, could you do it iteratively?
 Solution: 1. Iterative way (stack).   Time: O(n), Space: O(n).
           2. Recursive solution.      Time: O(n), Space: O(n).
           3. Threaded tree (Morris).  Time: O(n), Space: O(n/1). 
              Space: O(1) if in-place reverse.
              You may refer to my blog for more detailed explanations: 
              http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
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

public class BinaryTreePostorderTraversal {
	public List<Integer> postorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (stk.isEmpty() == false || cur != null) {
            if (cur != null) {
                stk.push(cur);
                cur = cur.left;
            } else {
                TreeNode peak = stk.peek();
                if (peak.right != null && pre != peak.right) {
                    cur = peak.right;
                } else {
                    res.add(peak.val);
                    stk.pop();
                    pre = peak;
                }
            }
        }
        return res;
    }
    public List<Integer> postorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        List<Integer> left = postorderTraversal_2(root.left);
        List<Integer> right = postorderTraversal_2(root.right);
        res.addAll(left);
        res.addAll(right);
        res.add(root.val);
        return res;
    }
    public List<Integer> postorderTraversal_3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<Integer> stk = new Stack<Integer>();
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        TreeNode cur = dummy;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur)
                    node = node.right;
                if (node.right == null) {
                    node.right = cur;
                    cur = cur.left;
                } else {
                    TreeNode temp = cur.left;
                    while (temp != cur) {
                        stk.push(temp.val);
                        temp = temp.right;
                    }
                    while (stk.isEmpty() == false) res.add(stk.pop());
                    node.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		
		t1.right = t2;
		t2.left = t3;
		
		BinaryTreePostorderTraversal slt = new BinaryTreePostorderTraversal();
		List<Integer> res = slt.postorderTraversal_1(t1);
		
		System.out.println(res);
		
		
	}

}
