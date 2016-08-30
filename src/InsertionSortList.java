/*
 147	Insertion Sort List	26.4%	Medium
 Problem:    Insertion Sort List
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/insertion-sort-list/
 Notes:
 Sort a linked list using insertion sort.
 Solution: ...
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class InsertionSortList {
	
	public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode cur = head.next;
        head.next = null;
        while (cur != null) {
            ListNode tmp = dummy;
            while (tmp.next != null && tmp.next.val <= cur.val) tmp = tmp.next;
            ListNode next = cur.next;
            cur.next = tmp.next;
            tmp.next = cur;
            cur = next;
        }
        return dummy.next;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		
		head.next =l3;
		l3.next =l2;
		l2.next =l4;
		l4.next =l5;
		
		InsertionSortList slt = new InsertionSortList();
	/*	
		while (null != head) {
			if(head.next != null){
				System.out.print(head.val+"->");
			}else{
			System.out.print(head.val);}
			
			head = head.next;
		}
		
		*/
		
		ListNode res = slt.insertionSortList(head);
		
		while (null != res) {
			if(res.next != null){
				System.out.print(res.val+"->");
			}else{
			System.out.print(res.val);}
			
			res = res.next;
		}
		
	}

}
