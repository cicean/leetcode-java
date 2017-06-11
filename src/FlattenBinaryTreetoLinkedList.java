import java.util.Stack;

/**
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 28, 2015
 Problem:    114 Flatten Binary Tree to Linked List
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/flatten-binary-tree-to-linked-list/
 Notes:
 Given a binary tree, flatten it to a linked list in-place.
 For example,
 Given
     1
    / \
   2   5
  / \   \
 3   4   6
 The flattened tree should look like:
 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
 Hints:
 If you notice carefully in the flattened tree, each node's right child points to the next node
 of a pre-order traversal.
 Solution: Recursion. Return the last element of the flattened sub-tree.
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

public class FlattenBinaryTreetoLinkedList {

    // recursive
    public void flatten_1(TreeNode root) {
        if (root == null) return;
        flatten_1(root.left);
        flatten_1(root.right);
        if (root.left == null) return;
        TreeNode node = root.left;
        while (node.right != null) node = node.right;
        node.right = root.right;
        root.right = root.left;
        root.left = null;
    }

    // stack
    public void flatten_2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (stk.empty() == false) {
            TreeNode cur = stk.pop();
            if (cur.right != null) stk.push(cur.right);
            if (cur.left != null) stk.push(cur.left);
            cur.left = null;
            cur.right = stk.empty() == true ? null : stk.peek();
        }
    }

    public void flatten(TreeNode root) {
        flatten_3(root);
    }

    private void flatten_3(TreeNode root) {
        if (root == null) return;
        flattenRe3(root, null);
    }

    private TreeNode flattenRe3(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        root.right = flattenRe3(root.left, flattenRe3(root.right, tail));
        root.left = null;
        return root;
    }
    
    public static void print(TreeNode root) {
		if (null != root) {
			System.out.println(root.val + "["
					+ (null == root.left ? "null" : root.left.val) + ","
					+ (null == root.right ? "null" : root.right.val) + "]");
			print(root.left);
			print(root.right);
		}
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);

		
		t1.left = t2;
		t1.right = t5;
		t2.left = t3;
		t2.right = t4;
		t5.right = t6;
		
		FlattenBinaryTreetoLinkedList slt = new FlattenBinaryTreetoLinkedList();
		
		slt.flatten(t1);
		print(t1);
	}

}
