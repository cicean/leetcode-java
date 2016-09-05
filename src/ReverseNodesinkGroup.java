/**
 * Created by cicean on 9/4/2016.
 * 25. Reverse Nodes in k-Group  QuestionEditorial Solution  My Submissions
 Total Accepted: 68390
 Total Submissions: 238352
 Difficulty: Hard
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5

 Hide Company Tags Microsoft Facebook
 Hide Tags Linked List
 Hide Similar Problems (E) Swap Nodes in Pairs

 */
public class ReverseNodesinkGroup {

    /**
     *
     * �Ƚ����·�ת�����д����
     1. ��������һ��ǰ�ýڵ㣬��ǰ�ýڵ��next����Ϊͷ�ڵ㣬��ͷ�ڵ�Ϊ��ǰ�ڵ㣬��ʼѭ��
     2. ����ǰ�ڵ��next����һ����ʱ�ڵ㣬Ȼ�󽫵�ǰ�ڵ��nextָ��ǰ�ýڵ㣬�������λ��ǰ�ýڵ�ָ��͵�ǰ�ڵ�ָ�룺ǰ�ýڵ�ָ��ָ��ǰ�ڵ㣬��ǰ�ڵ�ָ��ָ����ʱ�ڵ㣬�����������һ��ѭ��
     3. ��ǰ�ýڵ�ָ��ָ��β�ڵ�ʱ��ѭ������

     �и������ת����֮��ֻҪ���������ѭ�������������Ȳ�kʱ��ָ�����ǰ�������������ȵ���kʱ����ͷβ�ڵ���Ϊ�������뷭ת�������з�ת��Ȼ������ƴ�ӵ�ԭ�����С�ֱ����������ĩβ��
     */

    //ʱ�� O(N) �ռ� O(1)

    public class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (k == 1 || head == null || head.next == null)
                return head;
            ListNode first = head, last = head;
            ListNode preHead = new ListNode(-1);
            preHead.next = head;
            ListNode preGroup = preHead, nextGroup = preHead;
            int count = 1;
            while (last != null) {
                if (count == k) {
                    nextGroup = last.next;
                    reverseList(first, last);
                    preGroup.next = last;
                    preGroup = first;
                    first.next = nextGroup;
                    first = nextGroup;
                    last = nextGroup;
                    count = 1;
                    continue;
                }
                last = last.next;
                count++;
            }
            return preHead.next;
        }

        private void reverseList(ListNode head, ListNode tail) {
            ListNode pre = new ListNode(-1), node = head;
            pre.next = head;
            while (pre != tail) {
                ListNode temp = node.next;
                node.next = pre;
                pre = node;
                node = temp;
            }
        }
    }

    //iteratival

    /**
     * The basic idea is for each step,we set the the node after head as the list's new head, so that head then is tail. After reversing k nodes, we update the references and iterate through the whole list. If the size of the list is a multiple of k, the list is safely returned. Otherwise, a recursive call is made on the left-out nodes to undo the reverse. So the whole iteration times will be (n + n%k)

     Here is an example of how it works(case of K = 3):

     Initial:

     sentinel -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 ->...
     |       |    |
     dummy    tail newHead
     Set the node after tail @newHead as the new head. And the list:

     sentinel -> 2 -> 1 -> 3 -> 4 -> 5 -> 6 ->...
     |            |    |
     dummy        tail newHead
     Set node after tail as new Head:

     sentinel -> 3 -> 2 -> 1 -> 4 -> 5 -> 6 ->...
     |                  |
     dummy               tail
     3 nodes are reversed. Update the references:

     sentinel -> 3 -> 2 -> 1 -> 4 -> 5 -> 6 ->...
     |    |    |
     dummy tail newHead
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2 || head == null) return head;
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode dummy = sentinel, tail = head, newHead;
        while (true) {
            int count = k - 1;
            while (count > 0) {
                if (tail.next != null) {
                    newHead = tail.next;
                    tail.next = newHead.next;
                    newHead.next = dummy.next;
                    dummy.next = newHead;
                    count--;
                } else {
                    /// list size is not multiple of k, a recursive call on the left-out nodes to undo the reverse
                    dummy.next = reverseKGroup(dummy.next, k - count);
                    return sentinel.next;
                }
            }
            if (tail.next == null) return sentinel.next; /// list size is multiple of k, safely return
            dummy = tail;
            tail = tail.next;
        }
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup1(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

}
