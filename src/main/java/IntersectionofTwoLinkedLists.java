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

public class IntersectionofTwoLinkedLists {

    /**
     * I found most solutions here preprocess linkedlists to get the difference in len.
     Actually we don't care about the "value" of difference,
     we just want to make sure two pointers reach the intersection node at the same time.

     We can use two iterations to do that.
     In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist after it reaches the tail node.
     In the second iteration, we will move two pointers until they points to the same node.
     Our operations in first iteration will help us counteract the difference.
     So if two linkedlist intersects,
     the meeting point in second iteration must be the intersection point.
     If the two linked lists have no intersection at all,
     then the meeting pointer in second iteration must be the tail node of both lists,
     which is null
     * @param headA
     * @param headB
     * @return
     */
    //Bloomberg 店面
    //leetcode, linked list intersection. 一个fast一个slow指针秒,  follow up是如果两个list都可能有环, 该怎么做? 傻了几分钟, 回答hashset,
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }
	
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
