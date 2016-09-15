import datastructure.ListNode;

/**
 * 206	Reverse Linked List	31.5%	Easy
 * 
 * Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?

Hide Tags Linked List
Hide Similar Problems (M) Reverse Linked List II (M) Binary Tree Upside Down (E) Palindrome Linked List

 * 
 * 1.Refer.:
 * 1.1 同ReverseNodesInkGroup，区别在于先找到区间，然后仅需要reverse一次，
 * 1.2 Iterative
 * 1.3 Recursive
 */

public class ReverseLinkedList {

	// iterative solution
	public ListNode reverseList_1(ListNode head) {
	    ListNode newHead = null;
	    while(head != null){
	        ListNode next = head.next;
	        head.next = newHead;
	        newHead = head;
	        head = next;
	    }
	    return newHead;
	}
	
	public ListNode reverseList_2(ListNode head) {
	    if(head==null || head.next == null) 
	        return head;
	 
	    ListNode p1 = head;
	    ListNode p2 = head.next;
	 
	    head.next = null;
	    while(p1!= null && p2!= null){
	        ListNode t = p2.next;
	        p2.next = p1;
	        p1 = p2;
	        if (t!=null){
	            p2 = t;
	        }else{
	            break;
	        }
	    }
	 
	    return p2;
	}
	
	// recursive solution
	public ListNode reverseList(ListNode head) {
	    return reverseListInt(head, null);
	}

	public ListNode reverseListInt(ListNode head, ListNode newHead) {
	    if(head == null)
	        return newHead;
	    ListNode next = head.next;
	    head.next = newHead;
	    return reverseListInt(next, head);
	}
	
	
	public ListNode reverseList_3(ListNode head) {
	    if(head==null || head.next == null)
	        return head;
	 
	    //get second node    
	    ListNode second = head.next;
	    //set first's next to be null
	    head.next = null;
	 
	    ListNode rest = reverseList_3(second);
	    second.next = head;
	 
	    return rest;
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
				 System.out.println(node.val);
				 node = node.next; 
			 }
		 }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ListNode l1 = new ListNode(1);
		 ListNode n2 = new ListNode(2);
		 ListNode n3 = new ListNode(3);
		 ListNode n4 = new ListNode(4);
		 ListNode n5 = new ListNode(5);
		 
		 l1.next = n2;
		 n2.next = n3;
		 n3.next = n4;
		 n4.next = n5;
		 
		 int m = 2;
		 int n = 4;
		 
		 ReverseLinkedList slt = new ReverseLinkedList();
		 //slt.print(l1);
		 ListNode res = slt.reverseList_1(l1);
		 slt.print(res);
	}

}
