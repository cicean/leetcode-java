package Bloomberg;

import datastructure.TreeNode;

/**
 * Created by cicean on 9/20/2016.
 * 计算每个二叉树的从root到每个叶子的每条path的sum，找sum最小的那个path，输出这个path。
 */
public class BinaryTreeMinimumPathSum {

	private int minsum = Integer.MAX_VALUE;

	private String res = "";

	public String minPathSum(TreeNode root) {

		if (root == null)
			return res;

		minPathSumDfs(root, 0, new StringBuilder());
		return res;

	}

	private void minPathSumDfs(TreeNode node, int sum, StringBuilder sb) {
		if (node.left == null && node.right == null) {
			sum += node.val;
			sb.append(node.val);

			if (sum < minsum) {
				minsum = sum;
				res = sb.toString();
			}
		}

		sb.append(node.val);
		sb.append("->");
		sum += node.val;

		if (sum > minsum)
			return;

		if (node.left != null)
			minPathSumDfs(node.left, sum, new StringBuilder(sb));
		if (node.right != null)
			minPathSumDfs(node.right, sum, new StringBuilder(sb));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		TreeNode lf1 = new TreeNode(2);
		TreeNode lf2 = new TreeNode(3);
		TreeNode lf3 = new TreeNode(5);
		root.left = lf1;
		root.right = lf2;
		lf1.right = lf3;

		BinaryTreeMinimumPathSum slt = new BinaryTreeMinimumPathSum();
		System.out.println(slt.minPathSum(root));

	}

}
