/*
 	160	Intersection of Two Linked Lists	28.7%	Easy
 Problem:    Intersection of Two Linked Lists 
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/intersection-of-two-linked-lists/
 Notes:
 Write a program to find the node at which the intersection of two singly linked lists begins.
 Hints:
    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) time and use only O(1) memory.
 Solution: Two iteration.
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

public class IntersectionofTwoLinkedLists {
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cur = headA;
        int lenA = 0, lenB = 0;
        while (cur != null) {
            ++lenA;
            cur = cur.next;
        }
        cur = headB;
        while (cur != null) {
            ++lenB;
            cur = cur.next;
        }
        if (lenA >= lenB) {
            int diff = lenA - lenB;
            while (diff > 0) {
                headA = headA.next;
                --diff;
            }
            while (headA != null && headB != null) {
                if(headA == headB) {
                    return headA;
                }
                headA = headA.next;
                headB = headB.next;
            }
        } else {
            int diff = lenB - lenA;
            while (diff > 0) {
                headB = headB.next;
                --diff;
            }
            while (headA != null && headB != null) {
                if(headA == headB) {
                    return headA;
                }
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }
	
	
	public static void print(ListNode node) {
    	if (null != node) System.out.println(node.val);
    	else System.out.println("NULL");
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode headA = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode headB = new ListNode(2);
		ListNode e = new ListNode(3);
		ListNode f = new ListNode(4);
		
		headA.next = b;
		b.next = c;
		
		headB.next = e;
		e.next = f;
		
		IntersectionofTwoLinkedLists slt = new IntersectionofTwoLinkedLists();
		print(slt.getIntersectionNode(headA, headB));
		
		
	}

}
