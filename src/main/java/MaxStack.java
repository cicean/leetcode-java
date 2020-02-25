import java.util.*;

/**
 * 716. Max Stack
 * Easy
 *
 * 579
 *
 * 111
 *
 * Add to List
 *
 * Share
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * Note:
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 * Accepted
 * 43,972
 * Submissions
 * 104,603
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * fallcreek
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * LinkedIn
 * |
 * 12
 *
 * Lyft
 * |
 * 5
 *
 * Bloomberg
 * |
 * 2
 * Min Stack
 * Easy
 */

class MaxStack_Solution {

    /**
     * Approach #1: Two Stacks [Accepted]
     * Intuition and Algorithm
     *
     * A regular stack already supports the first 3 operations, so we focus on the last two.
     *
     * For peekMax, we remember the largest value we've seen on the side. For example if we add [2, 1, 5, 3, 9], we'll remember [2, 2, 5, 5, 9]. This works seamlessly with pop operations, and also it's easy to compute: it's just the maximum of the element we are adding and the previous maximum.
     *
     * For popMax, we know what the current maximum (peekMax) is. We can pop until we find that maximum, then push the popped elements back on the stack.
     *
     * Our implementation in Python will showcase extending the list class.
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N) for the popMax operation, and O(1)O(1) for the other operations, where NN is the number of operations performed.
     *
     * Space Complexity: O(N)O(N), the maximum size of the stack.
     */

    class MaxStack {
        Stack<Integer> stack;
        Stack<Integer> maxStack;

        public MaxStack() {
            stack = new Stack();
            maxStack = new Stack();
        }

        public void push(int x) {
            int max = maxStack.isEmpty() ? x : maxStack.peek();
            maxStack.push(max > x ? max : x);
            stack.push(x);
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = peekMax();
            Stack<Integer> buffer = new Stack();
            while (top() != max) buffer.push(pop());
            pop();
            while (!buffer.isEmpty()) push(buffer.pop());
            return max;
        }
    }

    /**
     * Approach #2: Double Linked List + TreeMap [Accepted]
     * Intuition
     *
     * Using structures like Array or Stack will never let us popMax quickly. We turn our attention to tree and linked-list structures that have a lower time complexity for removal, with the aim of making popMax faster than O(N)O(N) time complexity.
     *
     * Say we have a double linked list as our "stack". This reduces the problem to finding which node to remove, since we can remove nodes in O(1)O(1) time.
     *
     * We can use a TreeMap mapping values to a list of nodes to answer this question. TreeMap can find the largest value, insert values, and delete values, all in O(\log N)O(logN) time.
     *
     * Algorithm
     *
     * Let's store the stack as a double linked list dll, and store a map from value to a List of Node.
     *
     * When we MaxStack.push(x), we add a node to our dll, and add or update our entry map.get(x).add(node).
     *
     * When we MaxStack.pop(), we find the value val = dll.pop(), and remove the node from our map, deleting the entry if it was the last one.
     *
     * When we MaxStack.popMax(), we use the map to find the relevant node to unlink, and return it's value.
     *
     * The above operations are more clear given that we have a working DoubleLinkedList class. The implementation provided uses head and tail sentinels to simplify the relevant DoubleLinkedList operations.
     *
     * A Python implementation was not included for this approach because there is no analog to TreeMap available.
     * Complexity Analysis
     *
     * Time Complexity: O(\log N)O(logN) for all operations except peek which is O(1)O(1), where NN is the number of operations performed. Most operations involving TreeMap are O(\log N)O(logN).
     *
     * Space Complexity: O(N)O(N), the size of the data structures used.
     */
    class MaxStack_1 {
        TreeMap<Integer, List<Node>> map;
        DoubleLinkedList dll;

        public MaxStack_1() {
            map = new TreeMap();
            dll = new DoubleLinkedList();
        }

        public void push(int x) {
            Node node = dll.add(x);
            if(!map.containsKey(x))
                map.put(x, new ArrayList<Node>());
            map.get(x).add(node);
        }

        public int pop() {
            int val = dll.pop();
            List<Node> L = map.get(val);
            L.remove(L.size() - 1);
            if (L.isEmpty()) map.remove(val);
            return val;
        }

        public int top() {
            return dll.peek();
        }

        public int peekMax() {
            return map.lastKey();
        }

        public int popMax() {
            int max = peekMax();
            List<Node> L = map.get(max);
            Node node = L.remove(L.size() - 1);
            dll.unlink(node);
            if (L.isEmpty()) map.remove(max);
            return max;
        }
    }

    class DoubleLinkedList {
        Node head, tail;

        public DoubleLinkedList() {
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }

        public Node add(int val) {
            Node x = new Node(val);
            x.next = tail;
            x.prev = tail.prev;
            tail.prev = tail.prev.next = x;
            return x;
        }

        public int pop() {
            return unlink(tail.prev).val;
        }

        public int peek() {
            return tail.prev.val;
        }

        public Node unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }

    class Node {
        int val;
        Node prev, next;
        public Node(int v) {val = v;}
    }

    /**
     * use two stack
     * store the relational current max value in the maxStack
     * popMax(): O(n) 需要遍历一遍，找到max value
     * other operation are all O(1)
     */
    class MaxStack_2 {
        Deque<Integer> stack;
        Deque<Integer> maxStack;

        public MaxStack_2() {
            stack = new ArrayDeque<Integer>();
            maxStack = new ArrayDeque<Integer>();
        }

        public void push(int x) {
            int curMax = stack.isEmpty() ? Integer.MIN_VALUE : maxStack.peekFirst();
            if (x > curMax) {
                curMax = x;
            }
            stack.offerFirst(x);
            maxStack.offerFirst(curMax);
        }

        public int pop() {
            maxStack.pollFirst();
            return stack.pollFirst();
        }

        public int top() {
            return stack.peekFirst();
        }

        public int peekMax() {
            return maxStack.peekFirst();
        }

        public int popMax() {
            Deque<Integer> temp = new ArrayDeque<>();
            int maxValue = maxStack.peekFirst();
            while (!stack.isEmpty()) {
                int cur = stack.pollFirst();
                maxStack.pollFirst();
                if (cur != maxValue) {
                    temp.offerFirst(cur);
                } else {
                    break;
                }
            }
            while (!temp.isEmpty()) {
                push(temp.pollFirst());
            }
            return maxValue;
        }
    }


    PriorityQueue priorityQueue = new PriorityQueue();

}
