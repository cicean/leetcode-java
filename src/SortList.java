/*
 148	Sort List	22.0%	Medium
 Problem:    Sort List
 Difficulty: Medium
 Source:     http://oj.leetcode.com/problems/sort-list/
 Notes:
 Sort a linked list in O(nlogn) time using constant space complexity.
 Solution: merge sort.
 */

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * class datastructure.ListNode {
 *     int val;
 *     datastructure.ListNode next;
 *     datastructure.ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class SortList {

	public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(fast);
        return mergeTwoLists(l1, l2);
    }
	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode a = new ListNode(3);
		ListNode b = new ListNode(1);
		ListNode c = new ListNode(2);
		ListNode d = new ListNode(3);
		ListNode e = new ListNode(2);
		ListNode f = new ListNode(1);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		SortList slt = new SortList();
		ListNode res = slt.sortList(a);
		while (null != res) {
			if(res.next != null){
				System.out.print(res.val+"->");
			}else{
			System.out.print(res.val);}
			
			res = res.next;
		}
	}

}
