import java.util.LinkedList;
import java.util.Queue;

/*
 * 
 * 	226	Invert Binary Tree	35.5%	Easy
 * 
 * Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew),
 but you can’t invert a binary tree on a whiteboard so fuck off.
 * 
 * solution
 * 1.recursive 直接进行交换
 * 2.using queue 使用二叉树层次遍历
 * 3.recursive 
 * 4.iterative 
 * 
 */

public class InvertBinarTree {

	public void invertTree_1(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertTree_1(root.left);
        invertTree_1(root.right);
    }
	
	public TreeNode invertTree_2(TreeNode root)
	{
       if(root == null)
        {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //向列队中添加元素
        queue.offer(root);
        while(!queue.isEmpty())
        {
           //获取并移出元素
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if(node.left != null) 
            {
                queue.offer(node.left);
            }
            if(node.right != null) 
            {
                queue.offer(node.right);
            }
        }
        return root;
	}
	
	public TreeNode invertTree_3(TreeNode root) {
	    if(root!=null){
	    	recursive(root);
	    }
	 
	    return root;    
	}
	 
	public void recursive(TreeNode p){
	 
	    TreeNode temp = p.left;
	    p.left = p.right;
	    p.right = temp;
	 
	    if(p.left!=null)
	    	recursive(p.left);
	 
	    if(p.right!=null)
	    	recursive(p.right);
	}
	
	
	public TreeNode invertTree_4(TreeNode root) {
	    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	 
	    if(root!=null){
	        queue.add(root);
	    }
	 
	    while(!queue.isEmpty()){
	        TreeNode p = queue.poll();
	        if(p.left!=null)
	            queue.add(p.left);
	        if(p.right!=null)
	            queue.add(p.right);
	 
	        TreeNode temp = p.left;
	        p.left = p.right;
	        p.right = temp;
	    }
	 
	    return root;    
	}
	
	public  void print(TreeNode root) {
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

		TreeNode root = new TreeNode(4);
		TreeNode l1 = new TreeNode(2);
		TreeNode r1 = new TreeNode(7);
		TreeNode l2 = new TreeNode(1);
		TreeNode r2 = new TreeNode(3);
		TreeNode l3 = new TreeNode(6);
		TreeNode r3 = new TreeNode(9);
		
		root.left =l1;
		root.right=r1;
		l1.left =l2;
		l1.right = r2;
		r1.left =l3;
		r1.right= r3;
		
		InvertBinarTree slt = new InvertBinarTree();
		slt.print(root);
		//slt.print(slt.invertTree_1(root));
		
	}

}
