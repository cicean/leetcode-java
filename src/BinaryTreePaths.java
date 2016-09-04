import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 257	Binary Tree Paths	20.5%	Easy
 * @author cicean
 * 
 * LeetCode: Binary Tree Paths
AUG 16 2015

Given a binary tree, return all root-to-leaf paths.

For example

Given the following binary tree:

1
   1
 /   \
2     3
 \
  5
All root-to-leaf paths are: ["1->2->5", "1->3"]

 */
public class BinaryTreePaths {

	//

	public class Solution {
		public List<String> binaryTreePaths(TreeNode root) {

			List<String> res = new ArrayList<>();

			if (root == null) return res;

			dfs(root, res, "");

			return res;

		}

		private void dfs(TreeNode root, List<String> res, String path) {

			if (root.left == null && root.right == null) {
				res.add(path + root.val);
				return;
			}

			path = path + root.val + "->";

			if (root.left != null) dfs(root.left, res, path);

			if (root.right != null) dfs(root.right, res, path);

		}
	}
	
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> ret =  new ArrayList<String>();
		
		if (root == null) {
			return ret;
		}
		
		dfs(root, new StringBuilder(), ret);
		
		return ret;
	}
	
	public void dfs(TreeNode root, StringBuilder sb, List<String> ret) {
		
		if (root.left == null && root.right == null) {
			sb.append(root.val);
			ret.add(sb.toString());
			return;
		}
		
		sb.append(root.val);
		sb.append("->");
		
		if (root.left != null) {
			dfs(root.left, new StringBuilder(sb), ret);
		}
		
		if (root.right != null) {
			dfs(root.right,  new StringBuilder(sb), ret);
		}
		
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
        
        BinaryTreePaths slt = new BinaryTreePaths();
        System.out.println(slt.binaryTreePaths(root));
	}

}
