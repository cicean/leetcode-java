package Google;


import datastructure.TreeNode;

/**
 * Created by cicean on 9/25/2016.
 *
 */
public class LargestSameSubTree {

	public TreeNode largestSameSubtree(TreeNode root1, TreeNode root2) {


	}

	private boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val != q.val)
			return false;
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
				TreeNode t1 = new TreeNode(1);
				TreeNode root1 = new TreeNode(2);
				TreeNode t3 = new TreeNode(3);
				TreeNode t4 = new TreeNode(1);
				TreeNode t5 = new TreeNode(2);
				TreeNode t6 = new TreeNode(3);
				root1.left = t1;
				root1.right = t3;
				TreeNode root2 = new TreeNode(4);
				root2.left = t5;
				t5.left = t4;
				t5.right = t6;
				root2.right = new TreeNode(6);
				root2.right.left = new TreeNode(5);
                root2.right.right = new TreeNode(7);
				
				
				
				
				
				LargestSameSubTree slt = new LargestSameSubTree();
				TreeNode sListNode = slt.largestSameSubtree(t1, root2);
				if (sListNode != null) System.out.println(sListNode.val);
				else System.out.println("No same tree");
	}
}
