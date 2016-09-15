

/*
 * 237	Delete Node in a Linked List	48.1%	Easy
 * 
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

	Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 
	the linked list should become 1 -> 2 -> 4 after calling your function.
 * 
 *  链表基本操作，记待删除节点为node

	令node.val = node.next.val，node.next = node.next.next即可
 */

import datastructure.ListNode;

public class DeleteNodeinaLinkedList {

	
	public void deleteNode(ListNode node) {
		if(node == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
	
	public  void print(ListNode node){
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
		 ListNode n6 = new ListNode(6);
		 ListNode n7 = new ListNode(6);
		 
		 l1.next = n2;
		 n2.next = n6;
		 n6.next = n3;
		 n3.next = n4;
		 n4.next = n5;
		 n5.next = n7;
		 
		 DeleteNodeinaLinkedList slt = new DeleteNodeinaLinkedList();
		 slt.print(l1);
		 slt.deleteNode(n3);
		 slt.print(l1);
	}

}
