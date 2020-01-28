package Lintcode;
/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * Medium
 *
 * 677
 *
 * 75
 *
 * Add to List
 *
 * Share
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
 * For a circular doubly linked list, the predecessor of the first element is the last element,
 * and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation,
 * the left pointer of the tree node should point to its predecessor,
 * and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,5,1,3]
 *
 *
 * Output: [1,2,3,4,5]
 *
 * Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 *
 * Example 2:
 *
 * Input: root = [2,1,3]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 * Explanation: Input is an empty tree. Output is also an empty Linked List.
 * Example 4:
 *
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * -1000 <= Node.val <= 1000
 * Node.left.val < Node.val < Node.right.val
 * All values of Node.val are unique.
 * 0 <= Number of Nodes <= 2000
 * Accepted
 * 55,098
 * Submissions
 * 97,712
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 21
 *
 * Lyft
 * |
 * 4
 *
 * Databricks
 * |
 * 2
 *
 * Amazon
 * |
 * 2
 * Binary Tree Inorder Traversal
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

class Solution {
    // the smallest (first) and the largest (last) nodes
    private Node first = null;
    private Node last = null;

    private void helper(Node node) {
        if (node != null) {
            // left
            helper(node.left);
            // node
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            }
            else {
                // keep the smallest node
                // to close DLL later on
                first = node;
            }
            last = node;
            // right
            helper(node.right);
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }
}
