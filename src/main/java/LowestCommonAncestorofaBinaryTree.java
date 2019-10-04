import java.util.*;

/*
 * 236	Lowest Common Ancestor of a Binary Tree	26.6%	Medium
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

	According to the definition of LCA on Wikipedia: ��The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).��

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

 *  �������BST followup. �������ͨ������, ������BST.  ��Ӧ�ñ����ڵ�, ���ҵ�p,q. ͬʱ��¼�´�root���ü����·��.   
 *  ֮��Ƚ�·��,���һ����ͬ�Ľڵ����LCA.
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
	 * ������ȱ��
	 ���Ӷ�
	 ʱ�� O(h) �ռ� O(h) �ݹ�ջ�ռ�

	 ˼·
	 ���ǿ��������������������Ҷ�ӽڵ����ϣ���������г���Ŀ��ڵ�������
	 �����������Ŀ��ڵ㣬���Ϊ�Ǹ�Ŀ��ڵ㣬
	 ���û�У����Ϊnull����Ȼ����������������������б�ǣ�
	 ˵�����Ѿ��ҵ���С���������ˡ�����ڸ��ڵ�Ϊp��������������p��q�Ĺ������ȣ�
	 ��ض���p����

	 �����Ƕȣ�������ô�룺���һ���ڵ�������������Ŀ��ڵ��е�һ����
	 ������û�У�������ڵ�϶�������С�������ȡ�
	 ���һ���ڵ�������������Ŀ��ڵ��е�һ����������û�У�
	 ������ڵ�϶�Ҳ������С�������ȡ�
	 ֻ��һ���ڵ������������У�������Ҳ�е�ʱ�򣬲�����С�������ȡ�
	 * @param args
     */

	public class Solution {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			//����Ŀ��ڵ���ͨ������ֵ��Ǹ�����������ĳ��Ŀ����
			if(root == null || root == p || root == q) return root;
			//�鿴���������Ƿ���Ŀ���㣬û��Ϊnull
			TreeNode left = lowestCommonAncestor(root.left, p, q);
			//�鿴�������Ƿ���Ŀ��ڵ㣬û��Ϊnull
			TreeNode right = lowestCommonAncestor(root.right, p, q);
			//����Ϊ�գ�˵��������������Ŀ���㣬�򹫹����Ⱦ��Ǳ���
			if(left!=null&&right!=null) return root;
			//���������Ŀ��ڵ㣬��������ϱ��Ϊ��Ŀ��ڵ�
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
