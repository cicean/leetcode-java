import datastructure.ListNode;

/**
 * 	83	Remove Duplicates from Sorted List	34.7%	Easy
 * @author cicean
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

Hide Tags Linked List

 */
public class RemoveDuplicatesfromSortedList {
	
	 public ListNode deleteDuplicates(ListNode head) {
	        ListNode list = head;

	         while(list != null) {
	             if (list.next == null) {
	                 break;
	             }
	             if (list.val == list.next.val) {
	                 list.next = list.next.next;
	             } else {
	                 list = list.next;
	             }
	         }

	         return head;
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
		RemoveDuplicatesfromSortedList slt = new RemoveDuplicatesfromSortedList();
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(2);
		ListNode d = new ListNode(2);
		ListNode e = new ListNode(3);
		ListNode f = new ListNode(4);
		ListNode g = new ListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		f.next = g;
	
		
		slt.print(slt.deleteDuplicates(a));
	}

}
