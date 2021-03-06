import java.util.*;

/*
144	Binary Tree Preorder Traversal	36.3%	Medium
Problem:    Binary Tree Preorder Traversal
Difficulty: Easy
Source:     http://oj.leetcode.com/problems/binary-tree-preorder-traversal/
Notes:
Given a binary tree, return the preorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].
Note: Recursive solution is trivial, could you do it iteratively?
Solution: 1. Iterative way (stack).   Time: O(n), Space: O(n).
          2. Recursive solution.      Time: O(n), Space: O(n).
          3. Threaded tree (Morris).  Time: O(n), Space: O(1).
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




public class BinaryTreePreorderTraversal {
	
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        res.add(root.val);
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);
        res.addAll(left);
        res.addAll(right);
        return res;
    }
    public void preorderTraversalRe(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorderTraversalRe(root.left, res);
        preorderTraversalRe(root.right, res);
    }
    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        preorderTraversalRe(root, res);
        return res;
    }
    public List<Integer> preorderTraversal_3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (stk.isEmpty() == false) {
            TreeNode cur = stk.pop();
            res.add(cur.val);
            if (cur.right != null) stk.push(cur.right);
            if (cur.left != null) stk.push(cur.left);
        }
        return res;
    }
    public List<Integer> preorderTraversal_4(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode cur = root;
        while (stk.isEmpty() == false || cur != null) {
            if (cur != null) {
                stk.push(cur);
                res.add(cur.val);
                cur = cur.left;
            } else {
                cur = stk.pop();
                cur = cur.right;
            }
        }
        return res;
    }
    public List<Integer> preorderTraversal_morris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur)
                    node = node.right;
                if (node.right == null) {
                    node.right = cur;
                    res.add(cur.val);
                    cur = cur.left;
                } else {
                    node.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal_jiuzhang_morris(TreeNode root) {
        // morris traversal
        List<Integer> nums = new ArrayList<>();
        TreeNode cur = null;
        while (root != null) {
            if (root.left != null) {
                cur = root.left;
                // find the predecessor of root node
                while (cur.right != null && cur.right != root) {
                    cur = cur.right;
                }
                if (cur.right == root) {
                    cur.right = null;
                    root = root.right;
                } else {
                    nums.add(root.val);
                    cur.right = root;
                    root = root.left;
                }
            } else {
                nums.add(root.val);
                root = root.right;
            }
        }
        return nums;
    }

    // with parent node
    public List<Integer> preorderTraversal_databricks(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode last = null;

        while (cur != null) {
            if (last == cur.parent) {
                res.add(cur.val);
                last = cur;

                if (cur.left != null) {
                    cur = cur.left;
                } else if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur = cur.parent;
                }
            } else if (last == cur.left) {
                last = cur;
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur = cur.parent;
                }
            } else {
                last = cur;
                cur = cur.parent;
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
		
		
		
		BinaryTreePreorderTraversal slt = new BinaryTreePreorderTraversal();
		List<Integer> res = slt.preorderTraversal_5(t1);
		
		System.out.println(res);
		
		
	}

}
