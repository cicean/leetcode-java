/*
 141	Linked List Cycle	36.3%	Medium
 Problem:    Linked List Cycle
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/linked-list-cycle/
 Notes:
 Given a linked list, determine if it has a cycle in it.
 Follow up:
 Can you solve it without using extra space?
 Solution: two pointers.
 1.Refer.:
  1.0 两个指针，其中一个每次走一步，另一个每次走两步，如果相遇则有cycle;
  1.1 设从头节点开始到环的开始点的长度为a,环的开始点到相遇点的长度为b，
  相遇点到环的开始点的长度为c，则：
  	2a+2b=a+2b+c => a = c
  从相遇点开始一个指针，头开始一个指针，两者相遇即为环开始点
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

public class LinkedListCycle {
	
	public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return false;
        return true;
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
		
		LinkedListCycle slt = new LinkedListCycle();
		System.out.print(slt.hasCycle(a));
		
	//	datastructure.ListNode r = detectCycle(a);
	//	if (null != r)
	//		System.out.println(r.val);
	}

}
