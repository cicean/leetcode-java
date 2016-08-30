import java.util.ArrayList;
import java.util.List;

/**
 * 	203	Remove Linked List Elements	25.8%	Easy
 *
 * 0.Problem:
 * Remove all elements from a linked list of integers that 
 * have value val.
 * 
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 * 
 * 1.Refer.:
 * 
 * 1.1 recursive Java版递归解法
 * 2.1 The key to solve this problem is using a helper node to track the head of the list.
 * 2.2
 * 4.1 用一个数组（ArrayList）遍历一遍然后存储所有与给定值不等的节点值。然后按顺序创建一条变表即可。
 */

public class RemoveLinkedListElements {
	
	public ListNode removeElements_1(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements_1(head.next, val);
        return head.val == val ? head.next : head;
    }
	
	public ListNode removeElements_2(ListNode head, int val) {
	    ListNode helper = new ListNode(0);
	    helper.next = head;
	    ListNode p = helper;
	 
	    while(p.next != null){
	        if(p.next.val == val){
	            ListNode next = p.next;
	            p.next = next.next; 
	        }else{
	            p = p.next;
	        }
	    }
	 
	    return helper.next;
	}
	
	public ListNode removeElements_3(ListNode head, int val) {  
        ListNode dummy = new ListNode(Integer.MAX_VALUE);  
        dummy.next = head;  
        ListNode cur = dummy;  
        while(cur.next != null) {  
            if(cur.next.val == val)  
                cur.next = cur.next.next;  
            else  
                cur = cur.next;  
        }  
        return dummy.next;  
    }  
	
	public  ListNode removeElements_4(ListNode head, int val) {
    	List<Integer> list = new ArrayList<Integer>();
    	while(head!=null){
    		if(head.val!=val)
    			list.add(head.val);
    		head = head.next;
    	}
    	if(list.isEmpty())
    		return null;
    	ListNode newHead = new ListNode(list.get(0));
    	ListNode result = newHead;
    	for(int i=1;i<list.size();i++){
    		newHead.next = new ListNode(list.get(i));
    		newHead = newHead.next;
    	}
    	return result;
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
		 
		 RemoveLinkedListElements slt = new RemoveLinkedListElements();
		 int val = n6.val;
		 slt.print(l1);
		 slt.removeElements_1(l1, val);
		 slt.print(l1);
	}

}
