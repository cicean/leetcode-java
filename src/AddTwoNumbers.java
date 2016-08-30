/*
 2	Add Two Numbers	21.5%	Medium
 Source:     https://oj.leetcode.com/problems/add-two-numbers/
 Notes:
 You are given two linked lists representing two non-negative numbers. 
 The digits are stored in reverse order and each of their nodes contain a single digit. 
 Add the two numbers and return it as a linked list.
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Solution: dummy head...
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// LeetCode, Add Two Numbers
// 跟Add Binary 很类似
// 时间复杂度O(m+n)，空间复杂度O(1)

import java.io.*;

public class AddTwoNumbers {
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode dummy = new ListNode(0);
	        ListNode cur =dummy;
	        int carry = 0;
	        while (l1 != null || l2 != null || carry != 0)
	        {
	        	int sum = carry;
	        	if(l1 != null)
	        	{
	        		sum +=l1.val;
	        		l1 =l1.next;
	        	}
	        	if(l2 != null)
	        	{
	        		sum +=l2.val;
	        		l2 =l2.next;
	        	}
	        	carry =sum/10;
	        	sum = sum%10;
	        	ListNode node = new ListNode(sum);
	        	cur.next = node;
	        	cur = cur.next;
	        	
	        }
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
		 
		 AddTwoNumbers slt = new AddTwoNumbers();
		 ListNode result = slt.addTwoNumbers(l1, l2);
		 slt.print(result);
	 }
	 
}

