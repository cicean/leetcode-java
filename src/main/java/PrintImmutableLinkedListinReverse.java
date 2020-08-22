/**
 * 1265. Print Immutable Linked List in Reverse
 * Medium
 *
 * 100
 *
 * 21
 *
 * Add to List
 *
 * Share
 * You are given an immutable linked list, print out all values of each node in reverse with the help of the following interface:
 *
 * ImmutableListNode: An interface of immutable linked list, you are given the head of the list.
 * You need to use the following functions to access the linked list (you can't access the ImmutableListNode directly):
 *
 * ImmutableListNode.printValue(): Print value of the current node.
 * ImmutableListNode.getNext(): Return the next node.
 * The input is only given to initialize the linked list internally. You must solve this problem without modifying the linked list. In other words, you must operate the linked list using only the mentioned APIs.
 *
 *
 *
 * Follow up:
 *
 * Could you solve this problem in:
 *
 * Constant space complexity?
 * Linear time complexity and less than linear space complexity?
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4]
 * Output: [4,3,2,1]
 * Example 2:
 *
 * Input: head = [0,-4,-1,3,-5]
 * Output: [-5,3,-1,-4,0]
 * Example 3:
 *
 * Input: head = [-2,0,6,4,4,-6]
 * Output: [-6,4,4,6,0,-2]
 *
 *
 * Constraints:
 *
 * The length of the linked list is between [1, 1000].
 * The value of each node in the linked list is between [-1000, 1000].
 * Accepted
 * 6,089
 * Submissions
 * 6,446
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 */

/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation.
 * interface ImmutableListNode {
 *     public void printValue(); // print the value of this node.
 *     public ImmutableListNode getNext(); // return the next node.
 * };
 */
public class PrintImmutableLinkedListinReverse {

    //Iterative - Recursive

    class Solution {
        public void printLinkedListInReverse(ImmutableListNode head) {
            if (head == null) {
                return;
            }
            printLinkedListInReverse(head.getNext());
            head.printValue();
        }
    }
    //Iterative - With Stack

    class Solution2 {
        public void printLinkedListInReverse(ImmutableListNode head) {
            Stack<ImmutableListNode> stack = new Stack<>();
            while (head != null) {
                stack.push(head);
                head = head.getNext();
            }
            while (!stack.isEmpty()) {
                stack.pop().printValue();
            }
        }
    }

    //Follow Up - Constant space complexity (Time: O(n^2))

    class Solution3 {
        public void printLinkedListInReverse(ImmutableListNode head) {
            int numNodesCount = getNumNodesCount(head);
            for (int i = numNodesCount; i >= 1; i--) {
                printNthNode(head, i);
            }
        }

        private void printNthNode(ImmutableListNode head, int index) {
            ImmutableListNode node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            node.printValue();
        }

        private int getNumNodesCount(ImmutableListNode head) {
            int count = 0;
            ImmutableListNode node = head;
            while (node != null) {
                count++;
                node = node.getNext();
            }
            return count;
        }
    }
    //Follow Up - Linear time complexity and less than linear space complexity
    //Idea - Split the list to sqrt(n) equal-size small list. Print each part in reverse order.

    class Solution4 {
        public void printLinkedListInReverse(ImmutableListNode head) {
            // Time: O(n)
            int numNodesCount = getNumNodesCount(head);

            // Time: O(n) Space: O(sqrt(n))
            int step = (int)Math.sqrt(numNodesCount) + 1;
            Stack<ImmutableListNode> headNodes = new Stack<>();
            addNodeWithStep(head, step, headNodes);

            // Time: O(n) Space: O(sqrt(n))
            printEachHeadNodesInReverseOrder(headNodes);
        }

        private int getNumNodesCount(ImmutableListNode head) {
            int count = 0;
            ImmutableListNode node = head;
            while (node != null) {
                count++;
                node = node.getNext();
            }
            return count;
        }

        private void addNodeWithStep(ImmutableListNode head, int step, Stack<ImmutableListNode> headNodes) {
            ImmutableListNode node = head;
            int i = 0;
            while (node != null) {
                if (i % step == 0) {
                    headNodes.push(node);
                }
                node = node.getNext();
                i++;
            }
        }

        private void printEachHeadNodesInReverseOrder(Stack<ImmutableListNode> headNodes) {
            ImmutableListNode startNode = null;
            ImmutableListNode endNode = null;
            ImmutableListNode tempNode = null;

            while (!headNodes.isEmpty()) {
                endNode = startNode;
                startNode = headNodes.pop();
                tempNode = startNode;

                Stack<ImmutableListNode> stack = new Stack<>();
                while (tempNode != endNode) {
                    stack.push(tempNode);
                    tempNode = tempNode.getNext();
                }

                while (!stack.isEmpty()) {
                    stack.pop().printValue();
                }
            }
        }
    }
}
