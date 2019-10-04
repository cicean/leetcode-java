import java.util.*;

/*
103	Binary Tree Zigzag Level Order Traversal	26.4%	Medium
Problem:    Binary Tree Zigzag Level Order Traversal
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
Notes:
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left 
to right, then right to left for the next level and alternate between).
For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
 / \
15  7
return its zigzag level order traversal as:
[
 [3],
 [20,9],
 [15,7]
]
Solution: 1. Queue + reverse.
          2. Two stacks.
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
public class BinaryTreeZigzagLevelOrderTraversal {
	
	
	public List<List<Integer>> zigzagLevelOrder_1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(null);
        List<Integer> level = new ArrayList<Integer>();
        int depth = 0;
        while(true) {
            TreeNode node = q.poll();
            if (node != null) {
                level.add(node.val);
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            } else {
                if (depth % 2 == 1) Collections.reverse(level);
                res.add(level);
                depth++;
                level = new ArrayList<Integer>();
                if(q.isEmpty()==true) break;
                q.offer(null);
            }
        }
        return res;   
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Stack<TreeNode> cur = new Stack<TreeNode>();
        Stack<TreeNode> last = new Stack<TreeNode>();
        boolean left2right = true;
        last.push(root);
        List<Integer> level = new ArrayList<Integer>();
        while (last.empty() == false) {
            TreeNode node = last.pop(); 
            if (node != null) {
                level.add(node.val);
                if (left2right) {
                    if(node.left!=null) cur.push(node.left);
                    if(node.right!=null) cur.push(node.right);
                } else {
                    if(node.right!=null) cur.push(node.right);
                    if(node.left!=null) cur.push(node.left);
                }
            }
            if (last.empty() == true) {
                if (level.size() != 0)
                    res.add(level);
                level = new ArrayList<Integer>();
                Stack<TreeNode> temp = last;
                last = cur;
                cur = temp;
                left2right = !left2right;
            }
        }
        return res;
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
			BinaryTreeZigzagLevelOrderTraversal slt = new BinaryTreeZigzagLevelOrderTraversal();
			print(slt.zigzagLevelOrder(t1));
		}

}
