/*
 111	Minimum Depth of Binary Tree	29.1%	Easy
 Problem:    Minimum Depth of Binary Tree
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/minimum-depth-of-binary-tree/
 Notes:
 Given a binary tree, find its minimum depth.
 The minimum depth is the number of nodes along the shortest path from the root node 
 down to the nearest leaf node.
 
 Solution: 1. Recursion. Pay attention to cases when the non-leaf node has only one child.
           PS. 2. Iteration + Queue. 
 */

public class MinimumDepthofBinaryTree {

	//DFS
	public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

	//BFS
	public int minDepth(TreeNode root) {
		if(root == null) return 0;
		int depth = 1;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()){
			int size = q.size();
			// for each level
			for(int i=0;i<size;i++){
				TreeNode node = q.poll();
				if(node.left == null && node.right == null){
					return depth;
				}
				if(node.left != null){
					q.offer(node.left);
				}
				if(node.right != null){
					q.offer(node.right);
				}
			}
			depth++;
		}
		return depth;
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
		
		MinimumDepthofBinaryTree slt = new MinimumDepthofBinaryTree();
		System.out.print(slt.minDepth(t1));
	}

}
