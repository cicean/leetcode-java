package datastructure;

/**
 * Created by cicean on 9/12/2016.
 */
public class ListNode {
    public int val;

    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public void deleteNode(ListNode node) {
        if (node != null) {
            if (node.next == null) {
                node = null;
            } else {
                node.val = node.next.val;
                node.next = node.next.next;
            }

        }
    }


}
