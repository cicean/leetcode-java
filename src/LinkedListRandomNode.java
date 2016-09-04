import java.util.Random;

/**
 * Created by cicean on 9/2/2016.
 * 382. Linked List Random Node  QuestionEditorial Solution  My Submissions
 Total Accepted: 7050 Total Submissions: 15485 Difficulty: Medium
 Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

 Follow up:
 What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

 Example:

 // Init a singly linked list [1,2,3].
 ListNode head = new ListNode(1);
 head.next = new ListNode(2);
 head.next.next = new ListNode(3);
 Solution solution = new Solution(head);

 // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 solution.getRandom();
 Hide Company Tags Google
 Hide Tags Reservoir Sampling
 ����һ������Ҫ������ȵĸ��ʷ��������ϵĽ�㡣
 */
public class LinkedListRandomNode {

    /**
     * ����֪������len����ôֱ�����һ��0~len-1��Ȼ��������Ǹ���㡣

     �����֪�������أ�

     ����ʵʱ�ļ��㵱ǰ�����˶��ٸ�Ԫ��cnt��Ȼ���� 1/cnt �ĸ���ѡ�� ��ǰ��Ԫ�أ�ֱ������������

     ��������һ�鼴�ɡ�

     Ϊɶ�ǶԵģ�

     �����Ե�2����Ϊ��������head.next.val��

     ѡȡ�ĸ���Ϊ(1/2)* ��2/3��*��3/4��* ������.. (n-1) / n = 1/n   ��ѡȡ��2�����ڳ���Ϊ2��ʱ��Ϊ1/2�������Ķ���Ҫѡ)

     ����������ĵ�x�������ڿ��Ը���ǰ����������У� (1/x) * (x/(x+1)) *����.(n-1) / n = 1/n

     ��n������ֱ��1/n��

     ��Ҷ���1/n~
     */

    public class Solution {
        private ListNode head;
        private Random random;
        /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
        public Solution(ListNode head) {
            this.head = head;
            this.random = new Random();
        }

        /** Returns a random node's value. */
        public int getRandom() {
            int ans = 0;
            ListNode p = head;
            for (int cnt = 1; p != null; cnt++, p = p.next) if (random.nextInt(cnt) == 0) ans = p.val;
            return ans;
        }
    }

}
