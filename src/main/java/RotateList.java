
/*
 61	Rotate List	21.7%	Medium
 Problem:    Rotate List
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/rotate-list/
 Notes:
 Given a list, rotate the list to the right by k places, where k is non-negative.
 For example:
 Given 1->2->3->4->5->NULL and k = 2,
 return 4->5->1->2->3->NULL.
 Solution: Notice that k can be larger than the list size (k % list_size).
           This solution traverses the list twice at most.
*/

/**
 * Definition for singly-linked list.
 * struct datastructure.ListNode {
 *     int val;
 *     datastructure.ListNode *next;
 *     datastructure.ListNode(int x) : val(x), next(NULL) {}
 * };
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

public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int n = 1;
        ListNode tail = head, cur = head;
        while (tail.next != null) {
            tail = tail.next;
            ++n;
        }
        k = k % n;
        if (k == 0) return head;
        for (int i = 0; i < n - k - 1; ++i) 
            cur = cur.next;
        ListNode newHead = cur.next;
        tail.next = head;
        cur.next = null;
        return newHead;
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
		 ListNode n2 = new ListNode(2);
		 ListNode n3 = new ListNode(3);
		 ListNode n4 = new ListNode(4);
		 ListNode n5 = new ListNode(5);
		 
		 l1.next = n2;
		 n2.next = n3;
		 n3.next = n4;
		 n4.next = n5;
		 
		 int k = 2;
		 
		 RotateList slt = new RotateList();
		 ListNode result = slt.rotateRight(l1,k);
		 slt.print(result);
	 }
	
}
