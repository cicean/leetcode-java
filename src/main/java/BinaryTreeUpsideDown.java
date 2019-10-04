import java.util.List;

/*
 * Given a binary tree 
 * where all the right nodes are either leaf nodes with a sibling 
 * (a left node that shares the same parent node) or empty, 
 * flip it upside down and turn it into a tree 
 * where the original right nodes turned into left leaf nodes.
 *  Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
1
/ \
2 3
/ \
4 5

return the root of the binary tree [4,5,2,#,#,3,1].
4
/ \
5 2
  / \
 3 1

这题有一个重要的限制就是，整个数的任何一个右孩子都不会再生枝节，
基本就是一个梳子的形状。对于树类型的题目，
首先可以想到一种递归的思路：把左子树继续颠倒，颠倒完后，
原来的那个左孩子的左右孩子指针分别指向原来的根节点以及原来的右兄弟节点即可。
 */
public class BinaryTreeUpsideDown {

	 public TreeNode UpsideDownBinaryTree_1(TreeNode root){
		 if(root == null) return null;
		 TreeNode parent = root, left = root.left, right = root.right;
		 if(left != null){
			 TreeNode ret = UpsideDownBinaryTree_1(left);
			 left.left = right;
			 left.right = parent;
			 return ret;
		 }
		 return root;
	 }
	 
	 public TreeNode UpsideDownBinaryTree_2(TreeNode root){
		 TreeNode node = root, parent = null, right = null;
		 while(node != null){
			 TreeNode left = node.left;
			 node.left = right;
			 right = node.right;
			 node.right = parent;
			 parent = node;
			 node = left;
		 }
		 return parent;
	 }
	 

	 
	 private TreeNode out = null;
	 public TreeNode UpsideDownBinaryTree_3(TreeNode root){
		 TreeNode dummy = new TreeNode(0);
		 dummy.left = new TreeNode(0);
		 out = dummy;
		 postorder(root);
		 return dummy.right;
	 }
	
	 private void postorder(TreeNode root){
		 if(root == null) return;
		 postorder(root.left);
		 postorder(root.right);
		 if(out.left == null){
			 out.left = root;
			 out.left.left = null;
			 out.left.right = null;
			 		 }else if (out.right == null){
			 out.right = root;
			 out.right.left = null;
			 out.right.left = null;
		 }
		 
		 if (out.left != null && out.right != null){
			 out = out.right;
		 } 
	 }
	 
	 public static void print(TreeNode root) {
			while (null != root) {
				System.out.println(root.val);
				System.out.println("["
						+ (null == root.left ? "null" : root.left.val) + ","
						+ (null == root.right ? "null" : root.right.val) + "]");
				if(root.left != null){
					root = root.left;
			    }else if (root.right != null){
				    root = root.right;	   					
					   				}else break;
			   
			
			}
		}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 =new TreeNode(1);
		TreeNode t2 =new TreeNode(2);
		TreeNode t3 =new TreeNode(3);
		TreeNode t4 =new TreeNode(4);
		TreeNode t5 =new TreeNode(5);
		
		t1.left=t2;t1.right=t3;
		t2.left = t4; t3.right = t5;
		
		BinaryTreeUpsideDown slt = new BinaryTreeUpsideDown();
		slt.UpsideDownBinaryTree_1(t1);
		print(t1);
		
		
	}

}
