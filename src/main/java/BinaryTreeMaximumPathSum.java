/**
 * 124. Binary Tree Maximum Path Sum
 * Hard
 *
 * 2627
 *
 * 214
 *
 * Add to List
 *
 * Share
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 * Accepted
 * 276,921
 * Submissions
 * 856,454
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 55
 *
 * Amazon
 * |
 * 13
 *
 * Microsoft
 * |
 * 7
 *
 * Google
 * |
 * 4
 *
 * Apple
 * |
 * 4
 *
 * Uber
 * |
 * 2
 * Path Sum
 * Easy
 * Sum Root to Leaf Numbers
 * Medium
 * Path Sum IV
 * Medium
 * Longest Univalue Path
 * Easy
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

public class BinaryTreeMaximumPathSum {

	int maxValue;

	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
	}

	private int maxPathDown(TreeNode node) {
		if (node == null) return 0;
		int left = Math.max(0, maxPathDown(node.left));
		int right = Math.max(0, maxPathDown(node.right));
		maxValue = Math.max(maxValue, left + right + node.val);
		return Math.max(left, right) + node.val;
	}
	
	 public int maxPathSum(TreeNode root) {
	        int[] res = new int[1];
	        res[0] = Integer.MIN_VALUE;
	        maxPathSumRe(root, res);
	        return res[0];
	    }
	    int maxPathSumRe(TreeNode root, int[] res) {
	        if (root == null) return 0;
	        int left = maxPathSumRe(root.left, res);
	        int right = maxPathSumRe(root.right, res);
	        res[0] = Math.max(res[0], root.val + Math.max(left, 0) + Math.max(right, 0));
	        return Math.max(root.val, root.val + Math.max(left, right));
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 =new TreeNode(1);
		TreeNode t2 =new TreeNode(2);
		TreeNode t3 =new TreeNode(3);
		
		t1.left=t2;t1.right=t3;
		
		BinaryTreeMaximumPathSum slt =new BinaryTreeMaximumPathSum();
		System.out.print(slt.maxPathSum(t1));

	}

}
