import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 250	Count Univalue Subtrees 	32.6%	Medium
 * @author cicean
 * 
 * LeetCode: Count Univalue Subtrees
AUG 8 2015

Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:

Given binary tree,

1
    5
   / \
  1   5
 / \   \
5   5   5
return 4.
 * 
 *
 */
public class CountUnivalueSubtrees {

	public int countUnivalueSubtrees(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		if (root.left == null && root.right == null) {
			return 1;
		}
		
		int[] ret = new int[] {0};
		helper(root, ret);
		return ret[0];
	}
	
	private boolean helper(TreeNode root, int[] ret) {
		if (root.right == null & root.left == null) {
			ret[0]++;
			return true;
		} else if (root.right != null && root.left == null) {
			if (helper(root.right, ret) && root.val == root.right.val) {
				ret[0]++;
				return true;
			} else {
				return false;
			}
		} else {
			boolean l = helper(root.right, ret);
			boolean r = helper(root.left, ret);
			if (l && r && root.val == root.left.val && root.val == root.right.val) {
			   ret[0]++;
			   return true;
			} else {
				return false;
			}
			
			}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root =new TreeNode(5);
		TreeNode l1 =new TreeNode(1);
		TreeNode r1 =new TreeNode(5);
		TreeNode ll =new TreeNode(5);
		TreeNode lr =new TreeNode(5);
		TreeNode rr =new TreeNode(5);
		
		root.left = l1;
		root.right = r1;
		l1.left = ll;
		l1.right = lr;
		r1.right = rr;
		
		CountUnivalueSubtrees slt = new CountUnivalueSubtrees();
		
		System.out.println(slt.countUnivalueSubtrees(root));
		
	}

}

class Vector2D {
	  
	      Iterator<List<Integer>> outterIter;
	      Iterator<Integer> innerIter = Collections.emptyIterator();
	  
	      public Vector2D(List<List<Integer>> vec2d) {
	          outterIter = vec2d.iterator();
	      }
	  
	     public int next() {
	         return innerIter.next();
	     }
	 
	     public boolean hasNext() {
	         if(innerIter.hasNext()){
	             return true;
	         }
	 
	         if(!outterIter.hasNext()){
	             return false;
	         }
	 
	         innerIter = outterIter.next().iterator();
	 
	         return hasNext();
	     }
	 }
