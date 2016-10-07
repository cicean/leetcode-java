package Lintcode; /**
 * Convert binary search tree to doubly linked list with in-order traversal.

Have you met this question in a real interview? Yes
Example
Given a binary search tree:

    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5
 * @author cicean
 *
 */

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for Doubly-datastructure.ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */ 
public class ConvertBinarySearchTreetoDoublyLinkedList {
	
	/**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
	/**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        if (root == null) {
            return null;
        }
        
        ResultType result = helper(root);
        return result.first;
    }
    
    public ResultType helper(TreeNode root) {  
        if (root == null) {
            return null;
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        DoublyListNode node = new DoublyListNode(root.val);
        
        ResultType result = new ResultType(null, null);
        
        if (left == null) {
            result.first = node;
        } else {
            result.first = left.first;
            left.last.next = node;
            node.prev = left.last;
        }
        
        if (right == null) {
            result.last = node;
        } else {
            result.last = right.last;
            right.first.prev = node;
            node.next = right.first;
        }
        
        return result;
    }

    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     * Definition for Doubly-datastructure.ListNode.
     * public class DoublyListNode {
     *     int val;
     *     DoublyListNode next, prev;
     *     DoublyListNode(int val) {
     *         this.val = val;
     *         this.next = this.prev = null;
     *     }
     * }
     */
    public class Solution {
        /**
         * @param root: The root of tree
         * @return: the head of doubly list node
         */

        public DoublyListNode bstToDoublyList(TreeNode root) {
            if(null == root) return null;
            DoublyListNode result = bstToDoublyList2(root);
            while(result.prev != null)
                result = result.prev;
            return result;
        }

        public DoublyListNode bstToDoublyList2(TreeNode root) {
            if(null == root) return null;
            DoublyListNode node = new DoublyListNode(root.val);
            if(null != root.left) {
                DoublyListNode left = bstToDoublyList2(root.left);
                while(left.next != null)
                    left = left.next;
                node.prev = left;
                left.next = node;
            }
            if(null != root.right) {
                DoublyListNode right = bstToDoublyList2(root.right);
                while(right.prev != null)
                    right = right.prev;
                node.next = right;
                right.prev = node;
            }
            return node;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class ResultType {
    DoublyListNode first, last;
    
    public ResultType(DoublyListNode first, DoublyListNode last) {
        this.first = first;
        this.last = last;
    }
}
