

/*
 * 	222	Count Complete Tree Nodes	19.7%	Medium
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, 
 * is completely filled, and all nodes in the last level are as far left as possible. 
 * It can have between 1 and 2h nodes inclusive at the last level h.
 * 
 * Steps to solve this problem:
 * 1) get the height of left-most part
 * 2) get the height of right-most part
 * 3) when they are equal, the # of nodes = 2^h -1
 * 4) when they are not equal, recursively get # of nodes from left&right sub-trees
 * �ñ�����, recursive��ᳬʱ O(N).   
 * �����ĳ�ڵ�һֱ����ĸ߶� = һֱ���ҵĸ߶�, ��ô�Ըýڵ�Ϊroot������һ����complete binary tree. 
 * �� complete binary tree�Ľڵ���,�����ù�ʽ��� 2^h - 1. 
 * ����߶Ȳ����, ��ݹ���� return countNode(left) + countNode(right) + 1.  
 * ���Ӷ�ΪO(h^2)  
 */
public class CountCompleteTreeNodes {

	public int countNodes(TreeNode root) {
	    if(root==null)
	        return 0;
	 
	    int left = getLeftHeight(root)+1;    
	    int right = getRightHeight(root)+1;
	 
	    if(left==right){
	        return (2<<(left-1))-1;
	    }else{
	        return countNodes(root.left)+countNodes(root.right)+1;
	    }
	}
	 
	public int getLeftHeight(TreeNode n){
	    if(n==null) return 0;
	 
	    int height=0;
	    while(n.left!=null){
	        height++;
	        n = n.left;
	    }
	    return height;
	}
	 
	public int getRightHeight(TreeNode n){
	    if(n==null) return 0;
	 
	    int height=0;
	    while(n.right!=null){
	        height++;
	        n = n.right;
	    }
	    return height;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		t1.left = t2;
		t1.right = t3;
		t2.right = t5;
		t3.right = t4;
		
		CountCompleteTreeNodes slt= new CountCompleteTreeNodes();
		System.out.println(slt.countNodes(t1));
	}

}
