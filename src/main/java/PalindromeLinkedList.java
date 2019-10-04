import datastructure.ListNode;

/*
 * 234	Palindrome Linked List	23.4%	Eas
 * Given a singly linked list, determine if it is a palindrome.

	Follow up:
	Could you do it in O(n) time and O(1) space?
	
	����һ���������ж����Ƿ��ǻ��ġ�
	��һ��˼����
	�������O(n)ʱ�临�ӶȺ�O(1)�ռ临�Ӷ������

	����˼·��
	1). ʹ�ÿ���ָ��Ѱ�������е�
	2). ������ĺ�벿�־͵����ã�Ȼ��ȶ�ǰ�������Ԫ���Ƿ�һ��
	3). �ָ�ԭʼ����Ľṹ����ѡ��
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


  class Solution {
    public boolean isPalindrome(ListNode head) {
      if(head == null || head.next == null)
        return true;
      if(head.val == head.next.val && head.next.next == null)
        return true;
      ListNode slow = head;
      ListNode cur = head.next;
      while(cur.next != null) {
        if(slow.val == cur.next.val) {
          if(cur.next.next != null)
            return false;
          cur.next = null;
          slow = slow.next;
          cur = slow.next;
          if(cur == null || slow.val == cur.val)
            return true;
        } else
          cur = cur.next;
      }
      return false;
    }
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
