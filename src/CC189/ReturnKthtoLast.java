package CC189;

import datastructure.ListNode;

/**
 * Created by cicean on 10/11/2016.
 * CC189 2.2
 */
public class ReturnKthtoLast {

   

    //if the Linkedlist size is known
	int index = 0;
    public ListNode printKthToLast(ListNode head, int k) {
    	
        return printKthToLastRe(head, k , index);
    }

    private ListNode printKthToLastRe(ListNode head, int k, int index) {
        if (head == null) return null;
        
        ListNode node = printKthToLastRe(head.next, k , index);
        System.out.print(head.val);
        index++;
        System.out.println(", index = " + index);
        if (index == k) return  head;
        return node;
    }
    
 public ListNode printKthToLast_2(ListNode head, int k) {
    	
	 ListNode p1 = head;
	 ListNode p2 = head;
	 
	 for (int i = 0; i < k; i++) {
		 if (p1 == null) return null;
		 p1 = p1.next;
	 }
	 
	 while (p1 != null) {
		 p1 = p1.next;
		 p2 = p2.next;
	 }
	 
	 return p2;
        
    }
    
    
    public static void main(String[] args) {
		ReturnKthtoLast slt = new ReturnKthtoLast();
		ListNode node1 = new ListNode(1);
		node1.next = new ListNode(2);
		node1.next.next = new ListNode(3);
		node1.next.next.next = new ListNode(4);
		node1.next.next.next.next = new ListNode(5);
		ListNode res = slt.printKthToLast(node1, 1);
		System.out.println( (res != null ? res.val : "null"));
		
	}
}
