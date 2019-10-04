/**
 * Created by cicean on 9/20/2018.
 * 708. Insert into a Cyclic Sorted List
 DescriptionHintsSubmissionsDiscussSolution
 Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

 If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

 If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.

 The following example may help you understand the problem better:





 In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list.









 The new node should insert between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
 */


public class InsertintoaCyclicSortedList {

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newHead = new Node(insertVal,null);
            newHead.next = newHead;
            return newHead;
        }

        Node current = head;
        while (true) {
            if (current.val < current.next.val) {
                if (current.val <= insertVal && insertVal <= current.next.val) {
                    insertAfter(current, insertVal);
                    break;
                }
            }

            if (current.val > current.next.val) {
                if (current.val <= insertVal || insertVal <= current.next.val) {
                    insertAfter(current, insertVal);
                    break;
                }
            }

            if (current.val == current.next.val) {
                if (current.next == head) {
                    insertAfter(current, insertVal);
                    break;
                }
            }

            current = current.next;
        }

        return head;

    }

    private void insertAfter(Node cur, int val) {
        cur.next = new Node(val, cur.next);
    }
}
