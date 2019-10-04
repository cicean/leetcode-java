/*
 92	Reverse Linked List II	26.1%	Medium
 Problem:    Reverse Linked List II
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/reverse-linked-list-ii/
 Notes:
 Reverse a linked list from position m to n. Do it in-place and in one-pass.
 For example:
 Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 return 1->4->3->2->5->NULL.
 Note:
 Given m, n satisfy the following condition:
 1 <= m <= n <= length of list.
 Solution: in-place & one-pass.
 */

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class datastructure.ListNode {
 *     int val;
 *     datastructure.ListNode next;
 *     datastructure.ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class ReverseLinkedListII {
	
	
	
	 public ListNode reverseBetween(ListNode head, int m, int n) {
	        ListNode dummy = new ListNode(-1);
	        dummy.next = head;
	        ListNode first = dummy;
	        for (int i = 0; i < m - 1; ++i) first = first.next;
	        ListNode cur = first.next;
	        for (int i = 0; i < n - m; ++i) {
	            ListNode move = cur.next;
	            cur.next = move.next;
	            move.next = first.next;
	            first.next = move;
	        }
	        return dummy.next;
	    }
	 
	 public ListNode reverseBetween_2(ListNode head, int m, int n) {
		    if(head == null) return null;
		    ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
		    dummy.next = head;
		    ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
		    for(int i = 0; i<m-1; i++) pre = pre.next;

		    ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
		    ListNode then = start.next; // a pointer to a node that will be reversed

		    // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
		    // dummy-> 1 -> 2 -> 3 -> 4 -> 5

		    for(int i=0; i<n-m; i++)
		    {
		        start.next = then.next;
		        then.next = pre.next;
		        pre.next = then;
		        then = start.next;
		    }

		    // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
		    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

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
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ListNode l1 = new ListNode(1);
		 ListNode n2 = new ListNode(2);
		 ListNode n3 = new ListNode(3);
		 ListNode n4 = new ListNode(4);
		 ListNode n5 = new ListNode(5);
		 
		 l1.next = n2;
		 n2.next = n3;
		 n3.next = n4;
		 n4.next = n5;
		 
		 int m = 2;
		 int n = 4;
		 
		 ReverseLinkedListII slt = new ReverseLinkedListII();
		 ListNode result = slt.reverseBetween(l1,m,n);
		 slt.print(result);
	}

}
