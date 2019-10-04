/*
 19	Remove Nth Node From End of List	27.2%	Easy
 Problem:    Remove Nth Node From End of List
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
 Notes:
 Given a linked list, remove the nth node from the end of list and return its head.
 For example,
 Given linked list: 1->2->3->4->5, and n = 2.
 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.
 Solution: head---back------front------>NULL
                   |          |
                   ---> n <----
 */

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * 
class datastructure.ListNode {
	      int val;
	      datastructure.ListNode next;
	     datastructure.ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
 */




public class RemoveNthNodeFromEndofList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode back = dummy, front = dummy;
        dummy.next = head;
        while(n-- != 0) front = front.next;
        while(front.next != null) {
            front = front.next;
            back = back.next;
        }
        back.next = back.next.next;
        return dummy.next;
    }
	
	public void print(ListNode node){
		 while (node != null)
		 {
			 if(node.next != null)
			 {
				 System.out.print(node.val + "->");
				 node = node.next; 
			 }
			 else if(node.next == null)
			 {
				 System.out.print(node.val);
				 node = node.next; 
			 }
		 }
		 
	 }
	
	 public static void main(String[] args){
		 ListNode l1 = new ListNode(1);
		 ListNode cur= l1;
		 for(int i=0; i<4; i++)
		 {
			 ListNode temp = new ListNode(i+2);
			 cur.next=temp;
			 cur =temp;
		 }
		// datastructure.ListNode n2 = new datastructure.ListNode(2);
		// datastructure.ListNode n3 = new datastructure.ListNode(3);
		// datastructure.ListNode n4 = new datastructure.ListNode(4);
		// datastructure.ListNode n5 = new datastructure.ListNode(5);
		 
		 int n=2;
		 		 
		 RemoveNthNodeFromEndofList slt = new RemoveNthNodeFromEndofList();
		 ListNode result = slt.removeNthFromEnd(l1, n);
		 slt.print(result);
	 }
}


