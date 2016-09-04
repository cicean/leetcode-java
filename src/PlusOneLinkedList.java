/**
 * Created by cicean on 9/1/2016.
 * 369. Plus One Linked List  QuestionEditorial Solution  My Submissions
 Total Accepted: 4361 Total Submissions: 8660 Difficulty: Medium
 Given a non-negative number represented as a singly linked list of digits, plus one to the number.

 The digits are stored such that the most significant digit is at the head of the list.

 Example:
 Input:
 1->2->3

 Output:
 1->2->4
 Hide Company Tags Google
 Hide Tags Linked List
 Hide Similar Problems (E) Plus One

 */
public class PlusOneLinkedList {

    //方法：两次反转链表。
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public class Solution {
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode current = head;
            while (current != null) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }
        public ListNode plusOne(ListNode head) {
            if (head == null) return null;
            ListNode reversed = reverse(head);
            reversed.val ++;
            ListNode current = reversed;
            while (current != null && current.val >= 10) {
                current.val -= 10;
                if (current.next == null) {
                    current.next = new ListNode(1);
                } else {
                    current.next.val ++;
                }
                current = current.next;
            }
            reversed = reverse(reversed);
            return reversed;
        }
    }
}
