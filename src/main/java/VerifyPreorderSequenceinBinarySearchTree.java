import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 255	Verify Preorder Sequence in Binary Search Tree	Medium
 * @author cicean
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

	You may assume each number in the sequence is unique.
 * 
 */
public class VerifyPreorderSequenceinBinarySearchTree {

	public boolean verifyPreorder(int[] preorder) {
		
		if (preorder == null || preorder.length < 3) {
			return true;
		}
		
		Stack<Integer> stack = new Stack<>();
		List<Integer> list = new ArrayList<>();
		
		for (int i : preorder) {
			if (!list.isEmpty() && i < list.get(list.size() - 1)) {
				
				return false;
				
			}
			
			while (!stack.isEmpty() && i > stack.peek()) {
				
				
				list.add(stack.pop());
				
			}
			
			stack.add(i);
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
