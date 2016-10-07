
/**
 * 270	Closest Binary Search Tree Value 	28.3%	Easy
 * @author cicean
 * Problem Description:

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Well, this problem can be solved easily using recursion: just find the closest value in the left and right subtrees and compare them with root -> val. 
The code is as follows.
 */
public class ClosestBinarySearchTreeValue {

	public int closestValue(TreeNode root, double target) {
		if(root == null) { return target > 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE; }
		if(target < root.val) {
			int l = closestValue(root.left, target);
			return Math.abs(root.val - target) < Math.abs(l - target) ? root.val : l;
		} else {
			int r = closestValue(root.right, target);
			return Math.abs(root.val - target) < Math.abs(r - target) ? root.val : r;
		}
	}

	private double min = Double.MAX_VALUE;
	private int result = 0;
	
	public int closestValue_1(TreeNode root, double target) {
		if (root == null) {
			return Integer.MAX_VALUE;
		}
		
		helper(root, target);
		
		return result;
	}
	
	private void helper(TreeNode root, double target) {
		if (root == null) {
			return ;
		}
		
		if (Math.abs((double)root.val - target) < min) {
			min = Math.abs((double)root.val - target);
			result = root.val;
		}
		
		if (root.val > target) {
			helper(root.left, target);
		} else if (root.val < target) {
			helper(root.right, target);
		}
	}
	
}
