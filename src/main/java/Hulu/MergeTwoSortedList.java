package Hulu;

/**
 * 两个有序链表的合并。看过这个题，考虑下边界问题，可以用O(n)时间,O(1)空间解
 * 决。写完后，说我代码有个小bug，然后讨论后改之。问这个算法在哪种条件下不work，
 * 想了许久，突然灵光一现，想出可能链表有环或者两个链表有可能有公共节点。他很开
 * 心，说很久没有人能同时想出两个case了。
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
