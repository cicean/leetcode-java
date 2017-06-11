/*
 82	Remove Duplicates from Sorted List II	25.0%	Medium
 Problem:    Remove Duplicates from Sorted Array II
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 Notes:
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Hide Tags Linked List

 Solution: ...
 */

import datastructure.ListNode;

public class RemoveDuplicatesfromSortedLisII {
	/**
	 * recursive version
	 * @param head
	 * @return
	 */

	public ListNode deleteDuplicates(ListNode head) {
	    if (head == null) return null;

	    if (head.next != null && head.val == head.next.val) {
	        while (head.next != null && head.val == head.next.val) {
	            head = head.next;
	        }
	        return deleteDuplicates(head.next);
	    } else {
	        head.next = deleteDuplicates(head.next);
	    }
	    return head;
	}

	/**
	 * we skip all the 1's and start the loop from 2
	 * 
	 * and also skip all the 2's, and now head.val == 3;
	 * 
	 * ponit d.next to the tail, end the loop
	 * 
	 * @param node
	 */
	
	public ListNode deleteDuplicates_2(ListNode head) {
	    ListNode dummy = new ListNode(0);
	    ListNode d = dummy;
	    while (head != null) {
	        if (head.next != null && head.val == head.next.val) {
	            while (head.next != null && head.val == head.next.val)
	                head = head.next;
	        } else {
	            d.next = head;
	            d = d.next;
	        }
	        head = head.next;
	    }
	    d.next = null;
	    return dummy.next;
	}

	public void print(ListNode node) {
		while (node != null) {
			if (node.next != null) {
				System.out.print(node.val + "->");
				node = node.next;
			} else if (node.next == null) {
				System.out.println(node.val);
				node = node.next;
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveDuplicatesfromSortedLisII slt = new RemoveDuplicatesfromSortedLisII();
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(2);
		ListNode d = new ListNode(2);
		ListNode e = new ListNode(3);
		ListNode f = new ListNode(4);
		ListNode g = new ListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		f.next = g;

		slt.print(slt.deleteDuplicates(a));
	}
}
