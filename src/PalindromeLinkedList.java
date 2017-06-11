import datastructure.ListNode;

/*
 * 234	Palindrome Linked List	23.4%	Eas
 * Given a singly linked list, determine if it is a palindrome.

	Follow up:
	Could you do it in O(n) time and O(1) space?
	
	给定一个单链表，判断它是否是回文。
	进一步思考：
	你可以在O(n)时间复杂度和O(1)空间复杂度完成吗？

	解题思路：
	1). 使用快慢指针寻找链表中点
	2). 将链表的后半部分就地逆置，然后比对前后两半的元素是否一致
	3). 恢复原始链表的结构（可选）
 */
public class PalindromeLinkedList {

	public boolean isPalindrome(ListNode head) {
		if(head == null){
			return true;
		}
        ListNode current = head;
        ListNode fast = current.next;
        while(fast != null && fast.next !=null && fast.next.next != null ){
        	current =current.next;
        	fast = fast.next.next;
        }
        ListNode p = current.next;
        ListNode q;
        ListNode end = null;
        while(p != null){
        	q = p.next;
        	p.next = end;
        	end = p;
        	p = q;
        }
        
        while(head != null && end != null){
        	if(head.val != end.val){
        		return false;
        	}
        	head = head.next;
        	end = end.next;
        }
        return true;
        
        
        
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       ListNode l1 = new ListNode('l');
       ListNode l2 = new ListNode('e');
       ListNode l3 = new ListNode('v');
       ListNode l4 = new ListNode('e');
       ListNode l5 = new ListNode('l');
       
       l1.next = l2;
       l2.next = l3;
       l3.next = l4;
       l4.next = l5;
       
       PalindromeLinkedList slt = new PalindromeLinkedList();
       System.out.println(slt.isPalindrome(l1));
       
	}

}
