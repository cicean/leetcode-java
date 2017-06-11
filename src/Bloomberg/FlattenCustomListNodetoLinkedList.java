package Bloomberg;

/**
 * Created by cicean on 9/20/2016.
 * flatten tree一样不过是linkedList 每个node都有一个next和一个down
 1->2->3->4->5
 ｜
 6->7->8
 |
 9
 要flatten变成126978345

 same with 114 Flatten Binary Tree to Linked List
 */
class CustomLinkedNode {
    public int val;

    public CustomLinkedNode next;

    public CustomLinkedNode down;

    public CustomLinkedNode (int x) {
        this.val = x;
    }

}

public class FlattenCustomListNodetoLinkedList {
    //
    public void flatten(CustomLinkedNode head) {
        if (head == null) return;
        flatten(head.down);
        flatten(head.next);
        if (head.down == null) return;
        CustomLinkedNode node = head.down;
        while (node.next != null) node = node.next;
        node.next = head.next;
        head.next = head.down;
        head.down = null;
    }


}
