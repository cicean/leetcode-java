/*
 142	Linked List Cycle II	31.4%	Medium
 Problem:    Linked List Cycle II
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/linked-list-cycle-ii/
 Notes:
 Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 Follow up:
 Can you solve it without using extra space?
 Solution: ...
*/

/**
* Definition for singly-linked list.
* class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) {
*         val = x;
*         next = null;
*     }
* }
*/

public class LinkedListCycleII {
	
	public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		ListNode f = new ListNode(6);
		
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		f.next = c;
		
	//	LinkedListCycle slt = new LinkedListCycle();
		LinkedListCycleII slt = new LinkedListCycleII();
	//	System.out.print(slt.hasCycle(a));
		
		ListNode r = slt.detectCycle(a);
		if (null != r){
			System.out.println(r.val);
		}
	}

}
