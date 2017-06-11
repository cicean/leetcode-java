package Google;

import datastructure.TreeNode;

/**
 * Created by cicean on 9/27/2016.
 */
public class FindeSecondLargestinBST {

	int count = 0;
	public TreeNode findsecondLargestinBST(TreeNode root) {
		if (root == null) return null;
		TreeNode res = findsecondLargestinBST(root.right);
		if (count == 2) return res;
		else if (++count == 2) return root;
		return findsecondLargestinBST(root.left);
	}
	
	

	public static void main(String[] args) {

		TreeNode root = new TreeNode(4);
		 root.left = new TreeNode(2);
		 root.right = new TreeNode(6);
		 root.left.right = new TreeNode(3);
		 root.left.left = new TreeNode(1);
		 root.right.left = new TreeNode(5);
		 //root.right.right = new TreeNode(7);
		 
		 FindeSecondLargestinBST slt = new FindeSecondLargestinBST();
		 TreeNode res = slt.findsecondLargestinBST(root);
		 
		 System.out.println(res.val);
		 
	}

}
