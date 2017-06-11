

/*
 21	Merge Two Sorted Lists	32.8%	Easy
 Problem:    Merge Two Sorted Lists
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/merge-two-sorted-lists/
 Notes:
 Merge two sorted linked lists and return it as a new list. 
 The new list should be made by splicing together the nodes of the first two lists.
 Solution: ...
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

public class MergeTwoSortedLists {
	
	 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        ListNode head = new ListNode(0);
	        ListNode cur = head;
	        while (l1 != null && l2 != null) {
	            if (l1.val < l2.val) {
	                cur.next = l1;
	                l1 = l1.next;
	            } else {
	                cur.next = l2;
	                l2 = l2.next;
	            }
	            cur = cur.next;
	        }
	        if (l1 != null) cur.next = l1;
	        if (l2 != null) cur.next = l2;
	        return head.next;
	    }
	 
	 // recursive 
	 public ListNode mergeTwoLists_(ListNode l1, ListNode l2) {
	        if(l1 == null){
	            return l2;
	        }
	        if(l2 == null){
	            return l1;
	        }

	        ListNode mergeHead;
	        if(l1.val < l2.val){
	            mergeHead = l1;
	            mergeHead.next = mergeTwoLists(l1.next, l2);
	        }
	        else{
	            mergeHead = l2;
	            mergeHead.next = mergeTwoLists(l1, l2.next);
	        }
	        return mergeHead;
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
		 ListNode l1 = new ListNode(2);
		 ListNode n2 = new ListNode(4);
		 ListNode n3 = new ListNode(3);
		 
		 l1.next = n2;
		 n2.next = n3;
		 
		 ListNode l2 = new ListNode(5);
		 ListNode n4 = new ListNode(6);
		 ListNode n5 = new ListNode(4);
		 
		 l2.next = n4;
		 n4.next = n5;
		 
		 MergeTwoSortedLists slt = new MergeTwoSortedLists();
		 ListNode result = slt.mergeTwoLists(l1, l2);
		 slt.print(result);
	 }

}
