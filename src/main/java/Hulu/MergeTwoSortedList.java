package Hulu;

/**
 * ������������ĺϲ�����������⣬�����±߽����⣬������O(n)ʱ��,O(1)�ռ��
 * ����д���˵�Ҵ����и�Сbug��Ȼ�����ۺ��֮��������㷨�����������²�work��
 * ������ã�ͻȻ���һ�֣�������������л��������������п����й����ڵ㡣���ܿ�
 * �ģ�˵�ܾ�û������ͬʱ�������case�ˡ�
 */
public class MergeTwoSortedList {
    /**
     * @param l1: ListNode l1 is the head of the linked list
     * @param l2: ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode head = new ListNode(0);
        ListNode node = head;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        if (l1 != null) {
            node.next = l1;
        }

        if (l2 != null) {
            node.next = l2;
        }

        return head.next;
    }
}
