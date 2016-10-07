import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 138	Copy List with Random Pointer	25.1%	Hard
 Problem:    Copy List with Random Pointer
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/copy-list-with-random-pointer/
 Notes:
 A linked list is given such that each node contains an additional random pointer 
 which could point to any node in the list or null.
 Return a deep copy of the list.
 Solution:  1. HashMap
            2. uses constant extra space.
            3. Recursive. (Stack)-->StackOverflow in Java.
            4. Iterative. (Queue)
*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

public class CopyListwithRandomPointer {
	public RandomListNode copyRandomList_1(RandomListNode head) {
        if (head == null) return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode curNew = dummy, cur = head;
        while (cur != null) {
            if (!map.containsKey(cur)) {
                map.put(cur, new RandomListNode(cur.label));
            }
            if (cur.random != null && !map.containsKey(cur.random)) {
                map.put(cur.random, new RandomListNode(cur.random.label));
            }
            curNew.next = map.get(cur);
            curNew.next.random = map.get(cur.random);
            curNew = curNew.next;
            cur = cur.next;
        }
        return dummy.next;
    }
    public RandomListNode copyRandomList_2(RandomListNode head) {
        if (head == null) return null;
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode newnode = new RandomListNode(cur.label);
            newnode.next = cur.next;
            cur.next = newnode;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode curNew = dummy;
        cur = head;
        while (cur != null) {
            curNew.next = cur.next;
            curNew = curNew.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return dummy.next;
    }
    public RandomListNode copyRandomList_3(RandomListNode head) {/*StackOverflowError*/
        if (head == null) return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        return copy(head, map);
    }
    public RandomListNode copy(RandomListNode root, HashMap<RandomListNode, RandomListNode> map) {
        if (root == null) return null;
        if (map.containsKey(root)) {
            return map.get(root);
        }
        RandomListNode newnode = new RandomListNode(root.label);
        map.put(root, newnode);
        newnode.next = copy(root.next, map);
        newnode.random = copy(root.random, map);
        return newnode;
    }
    public RandomListNode copyRandomList_4(RandomListNode head) {
        if (head == null) return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        Queue<RandomListNode> queue = new LinkedList<RandomListNode>();
        queue.offer(head);
        map.put(head, new RandomListNode(head.label));
        while (!queue.isEmpty()) {
            RandomListNode cur = queue.poll();
            if (cur.next != null && !map.containsKey(cur.next)) {
                RandomListNode newnode = new RandomListNode(cur.next.label);
                map.put(cur.next, newnode);
                queue.offer(cur.next);
            }
            map.get(cur).next = map.get(cur.next);
            if (cur.random != null && !map.containsKey(cur.random)) {
                RandomListNode newnode = new RandomListNode(cur.random.label);
                map.put(cur.random, newnode);
                queue.offer(cur.random);
            }
            map.get(cur).random = map.get(cur.random);
        }
        return map.get(head);
    }
    
    public static void print(RandomListNode a) {
		while (null != a) {
			int next = -1;
			int rand = -1;
			if (null != a.next) next = a.next.label;
			if (null != a.random) rand = a.random.label;
			System.out.println(a.label + "(" + next + "," + rand +")");
			a = a.next;
		}
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomListNode a = new RandomListNode(1);
		RandomListNode b = new RandomListNode(2);
		RandomListNode c = new RandomListNode(3);
		RandomListNode d = new RandomListNode(4);
		RandomListNode e = new RandomListNode(5);
		RandomListNode f = new RandomListNode(6);
		RandomListNode g = new RandomListNode(7);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		f.next = g;
		a.random = c;
		b.random = e;
		c.random = null;
		d.random = a;
		e.random = b;
		f.random = g;
		g.random = c;
		
		CopyListwithRandomPointer slt = new CopyListwithRandomPointer();
		print(a);
		RandomListNode res = slt.copyRandomList_1(a);
		print(res);
	}

}
