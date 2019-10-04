/*
 * 235	Lowest Common Ancestor of a Binary Search Tree	38.7%	Easy
 * Lowest Common Ancestor of a Binary Search Tree Total Accepted: 203 Total Submissions: 511
	Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
	According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allowa node to be a descendant of itself).”
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
	For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
	[思路]
	如果如果p,q 比root小, 则LCA必定在左子树, 如果p,q比root大, 则LCA必定在右子树. 如果一大一小, 则root即为LCA.
	[CODE]
 * 
 */
public class LowestCommonAncestorofaBinarySearchTree {

    /**
     * 二分法
     复杂度
     时间 O(h) 空间 O(h) 递归栈空间

     思路
     对于二叉搜索树，公共祖先的值一定大于等于较小的节点，小于等于较大的节点。
     换言之，在遍历树的时候，如果当前结点大于两个节点，
     则结果在当前结点的左子树里，如果当前结点小于两个节点，则结果在当前节点的右子树里。
     * @param root
     * @param p
     * @param q
     * @return
     */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p==null || q== null){
        	return null;
        }
        	if(Math.max(p.val, q.val)< root.val){
        		return lowestCommonAncestor(root.left,p,q);
        	}else if(Math.min(p.val, q.val)>root.val){
        		return lowestCommonAncestor(root.right,p,q);
        	}else return root;
        
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      TreeNode root = new TreeNode(6);
      TreeNode t1 = new TreeNode(2);
      TreeNode t2 = new TreeNode(8);
      TreeNode t3 = new TreeNode(0);
      TreeNode t4 = new TreeNode(4);
      TreeNode t5 = new TreeNode(7);
      TreeNode t6 = new TreeNode(9);
      TreeNode t7 = new TreeNode(3);
      TreeNode t8 = new TreeNode(5);
      
      root.left=t1; 
      root.right =t2;
      t1.left =t3;
      t1.right =t4;
      t2.left = t5;
      t2.right = t6;
      t4.left =t7;
      t4.right =t8;
      
      LowestCommonAncestorofaBinarySearchTree slt= new LowestCommonAncestorofaBinarySearchTree();
      
      slt.lowestCommonAncestor(root, t7, t8);
      
      
	}

}
