import datastructure.ListNode;

import java.util.*;

/**
 * 1019. Next Greater Node In Linked List
 * Medium
 *
 * 568
 *
 * 37
 *
 * Add to List
 *
 * Share
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 *
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
 *
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 *
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 * Example 3:
 *
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 *
 *
 * Note:
 *
 * 1 <= node.val <= 10^9 for each node in the linked list.
 * The given list has length in the range [0, 10000].
 * Accepted
 * 33,813
 * Submissions
 * 59,723
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * anupkmr03
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 2
 * We can use a stack that stores nodes in monotone decreasing order of value. When we see a node_j with a larger value,
 * every node_i in the stack has next_larger(node_i) = node_j .
 */
public class NextGreaterNodeInLinkedList {
    /**
     * Monotonic Stack
     */

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int[] nextLargerNodes(ListNode head) {
            ArrayList<Integer> A = new ArrayList<>();
            for (ListNode node = head; node != null; node = node.next)
                A.add(node.val);
            int[] res = new int[A.size()];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < A.size(); ++i) {
                while (!stack.isEmpty() && A.get(stack.peek()) < A.get(i))
                    res[stack.pop()] = A.get(i);
                stack.push(i);
            }
            return res;
        }
    }

}
