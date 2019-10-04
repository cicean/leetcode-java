import java.util.*;

/**
 * 	199	Binary Tree Right Side View	27.2%	Medium
 *
 * 0.Problem:
 * Given a binary tree, imagine yourself standing on the right side of it, 
 * return the values of the nodes you can see ordered from top to bottom.
 * 
 * For example:
 * Given the following binary tree,
 *    1            <---
 *  /   \
 * 2     3         <---
 * \     \
 *  5     4       <---
 * You should return [1, 3, 4].
 * 
 * 
 * 1.Refer.: 
 * This problem can be solve by using a queue. On each level of the tree, 
 * we add the right-most element to the results.
 * 1.1inter µü´ú½â·¨
 * 2.1 Recursive 
 * 
 */

public class BinaryTreeRightSideView {
	
	public List<Integer> rightSideView_1(TreeNode root) {
	    ArrayList<Integer> result = new ArrayList<Integer>();
	 
	    if(root == null) return result;
	 
	    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.add(root);
	 
	    while(queue.size() > 0){
	        //get size here
	        int size = queue.size();
	 
	        for(int i=0; i<size; i++){
	            TreeNode top = queue.remove();
	 
	            //the first element in the queue (right-most of the tree)
	            if(i==0){
	                result.add(top.val);
	            }
	            //add right first
	            if(top.right != null){
	                queue.add(top.right);
	            }
	            //add left
	            if(top.left != null){
	                queue.add(top.left);
	            }
	        }
	    }
	 
	    return result;
	}
	
	public List<Integer> rightSideView_2(TreeNode root) {
        List<Integer> ret=new ArrayList<Integer>();
        if(root==null) return ret;
        int level=1;
        recursive(root, level, ret);
        return ret;
    }
    
    public void recursive(TreeNode root, int level, List<Integer> ret){
        if(root==null) return;
        if(level>ret.size()){
            ret.add(root.val);
        }
        recursive(root.right, level+1, ret);
        recursive(root.left, level+1, ret);
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
		
		BinaryTreeRightSideView slt = new BinaryTreeRightSideView();
		List<Integer> res = slt.rightSideView_1(t1);
		System.out.print(res);
		
		
	}

}
