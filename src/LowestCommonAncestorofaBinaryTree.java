import java.util.*;

/*
 * 236	Lowest Common Ancestor of a Binary Tree	26.6%	Medium
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

	According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
	For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

	Hide Tags Tree
	Hide Similar Problems (E) Lowest Common Ancestor of a Binary Search Tree

 *  这道题是BST followup. 如果是普通二叉树, 而不是BST.  则应该遍历节点, 先找到p,q. 同时记录下从root到该几点的路径.   
 *  之后比较路径,最后一个相同的节点便是LCA.
 */
public class LowestCommonAncestorofaBinaryTree {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
		if(root == null || p == null || q == null) return null;
		List<TreeNode> pathp = new ArrayList<>();
		List<TreeNode> pathq = new ArrayList<>();
		pathp.add(root);
		pathq.add(root);
		
		getPath(root,p,pathp);
		getPath(root,q,pathq);
		
		TreeNode lca = null;
		for(int i=0; i<pathp.size() && i<pathq.size(); i++){
			if(pathp.get(i) == pathq.get(i)) lca = pathp.get(i);
			else break;
		}
		return lca;
		
		
    }
	
	private boolean getPath(TreeNode root, TreeNode n, List<TreeNode> path){
		if(root == n){ return true;}
		if(root.left != null){
			path.add(root.left);
			if(getPath(root.left,n,path)) return true;
			path.remove(path.size()-1);
		}
		if(root.right != null){
			path.add(root.right);
			if((getPath(root.right,n,path))) return true;
			path.remove(path.size()-1);
		}
		return false;
	}

	/**
	 * 深度优先标记
	 复杂度
	 时间 O(h) 空间 O(h) 递归栈空间

	 思路
	 我们可以用深度优先搜索，从叶子节点向上，标记子树中出现目标节点的情况。
	 如果子树中有目标节点，标记为那个目标节点，
	 如果没有，标记为null。显然，如果左子树、右子树都有标记，
	 说明就已经找到最小公共祖先了。如果在根节点为p的左右子树中找p、q的公共祖先，
	 则必定是p本身。

	 换个角度，可以这么想：如果一个节点左子树有两个目标节点中的一个，
	 右子树没有，那这个节点肯定不是最小公共祖先。
	 如果一个节点右子树有两个目标节点中的一个，左子树没有，
	 那这个节点肯定也不是最小公共祖先。
	 只有一个节点正好左子树有，右子树也有的时候，才是最小公共祖先。
	 * @param args
     */

	public class Solution {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			//发现目标节点则通过返回值标记该子树发现了某个目标结点
			if(root == null || root == p || root == q) return root;
			//查看左子树中是否有目标结点，没有为null
			TreeNode left = lowestCommonAncestor(root.left, p, q);
			//查看右子树是否有目标节点，没有为null
			TreeNode right = lowestCommonAncestor(root.right, p, q);
			//都不为空，说明做右子树都有目标结点，则公共祖先就是本身
			if(left!=null&&right!=null) return root;
			//如果发现了目标节点，则继续向上标记为该目标节点
			return left == null ? right : left;
		}
	}

	//iteratival
	public class Solution2 {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			Map<TreeNode, TreeNode> parent = new HashMap<>();
			Deque<TreeNode> stack = new ArrayDeque<>();
			parent.put(root, null);
			stack.push(root);

			while (!parent.containsKey(p) || !parent.containsKey(q)) {
				TreeNode node = stack.pop();
				if (node.left != null) {
					parent.put(node.left, node);
					stack.push(node.left);
				}
				if (node.right != null) {
					parent.put(node.right, node);
					stack.push(node.right);
				}
			}
			Set<TreeNode> ancestors = new HashSet<>();
			while (p != null) {
				ancestors.add(p);
				p = parent.get(p);
			}
			while (!ancestors.contains(q))
				q = parent.get(q);
			return q;
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(3);
	      TreeNode t1 = new TreeNode(5);
	      TreeNode t2 = new TreeNode(1);
	      TreeNode t3 = new TreeNode(6);
	      TreeNode t4 = new TreeNode(2);
	      TreeNode t5 = new TreeNode(0);
	      TreeNode t6 = new TreeNode(8);
	      TreeNode t7 = new TreeNode(7);
	      TreeNode t8 = new TreeNode(4);
	      
	      root.left=t1; 
	      root.right =t2;
	      t1.left =t3;
	      t1.right =t4;
	      t2.left = t5;
	      t2.right = t6;
	      t4.left =t7;
	      t4.right =t8;
	      
	      LowestCommonAncestorofaBinaryTree slt= new LowestCommonAncestorofaBinaryTree();
	      
	      slt.lowestCommonAncestor(root, t7, t8);
	}

}
