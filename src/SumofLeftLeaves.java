import java.util.*;

/**
 * Created by cicean on 9/25/2016.
 * 404. Sum of Left Leaves  QuestionEditorial Solution  My Submissions
 Total Accepted: 1202
 Total Submissions: 2469
 Difficulty: Easy
 Find the sum of all left leaves in a given binary tree.

 Example:

 3
 / \
 9  20
 /  \
 15   7

 There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumofLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        int res = 0;

        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            if (node != null) {
            	if (node.left != null && node.left.left == null && node.left.right == null) {
            		res += node.left.val;
            	}
            	stk.push(node.left);
            	stk.push(node.right);
            }
        }
        return res;
    }
    
    public int sumOfLeftLeaves_rc(TreeNode n) {
        if(n==null ||(n.left==null && n.right ==null))return 0;
        int l=0,r=0;
        if(n.left!=null)l=(n.left.left==null && n.left.right==null)?n.left.val:sumOfLeftLeaves_rc(n.left);
        if(n.right!=null)r=sumOfLeftLeaves_rc(n.right);
        return l+r;
    }
    
    public static void main(String[] args) {
		SumofLeftLeaves slt = new SumofLeftLeaves();
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left= new TreeNode(1);
		root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
		slt.sumOfLeftLeaves(root);
	}
}
