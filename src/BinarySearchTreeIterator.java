import java.util.*;

/*
 173	Binary Search Tree Iterator	29.3%	Medium
 Problem:    Binary Search Tree Iterator 
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/binary-search-tree-iterator/
 Notes:
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.
 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 Solution: Inorder traversal.
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

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

public class BinarySearchTreeIterator {

    private Stack<TreeNode> stk;
    private TreeNode node;

	public void BSTIterator(TreeNode root) {
        stk = new Stack<TreeNode>();
        node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (stk.isEmpty() == true && node == null) return false;
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        if (stk.isEmpty() == true && node == null)  return 0;
        while (node != null) {
            stk.push(node);
            node = node.left;
        }
        int res = 0;
        node = stk.pop();
        res = node.val;
        node = node.right;
        return res;  
    }

}


 class BSTIterator_2 {

    public void BSTIterator(TreeNode root) {
        node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return node != null;
    }

    /** @return the next smallest number */
    public int next() {
        if (node == null) return 0;
        int res = 0;
        while (node != null) {
            if (node.left == null) {
                res = node.val;
                node = node.right;
                return res;
            }
            TreeNode pre = node.left;
            while (pre.right != null && pre.right != node) 
                pre = pre.right;
            if (pre.right == null) {
                pre.right = node;
                node = node.left;
            } else {
                res = node.val;
                node = node.right;
                pre.right = null;
                return res;
            }
        }
        return res;
    }
    private TreeNode node;

}

// facebook followup
class TwoBinarySearchTreeIterator {

    private Stack<TreeNode> stk1;
    private TreeNode node1;
    private TreeNode node2;
    int a = Integer.MAX_VALUE;
    int b = Integer.MAX_VALUE;

    public TwoBinarySearchTreeIterator(TreeNode root1, TreeNode root2) {
        stk1 = new Stack<TreeNode>();
        this.node1 = root1;
        this.node2 = root2;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (node1 != null || node2 != null || !stk1.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        if (hasNext()) {
            a = nextroot1(node1);
            b = nextroot1(node2);
        }

        return Math.min(a,b);
    }

    private int nextroot1(TreeNode root) {
        while(root != null) {
            stk1.push(root);
            root = root.left;
        }

        root = stk1.pop();
        TreeNode node = root;
        root = root.right;

        return node.val;
    }


}
