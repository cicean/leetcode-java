/**
 2. Add Two Numbers  QuestionEditorial Solution  My Submissions
 Total Accepted: 178094
 Total Submissions: 716094
 Difficulty: Medium
 You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8

 Hide Company Tags Amazon Microsoft Bloomberg Airbnb Adobe
 Hide Tags Linked List Math
 Hide Similar Problems (M) Multiply Strings (E) Add Binary (E) Sum of Two Integers

 */

import datastructure.ListNode;

import java.util.Stack;

/**
 * Definition for singly-linked list. public class datastructure.ListNode { int
 * val; datastructure.ListNode next; datastructure.ListNode(int x) { val = x; }
 * }
 */

// LeetCode, Add Two Numbers
// 跟Add Binary 很类似
// 时间复杂度O(m+n)，空间复杂度O(1)

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int sum = carry;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			carry = sum / 10;
			sum = sum % 10;
			ListNode node = new ListNode(sum);
			cur.next = node;
			cur = cur.next;

		}
		return dummy.next;
	}

	/**
	 * Bloomberg phone interview 两个LinkedList 代表两个integer
	 * 然后在不reverse，不转成String或int的情况下做相加操作 返回新的LinkedList 前面发面经的同学已经交代了用stack
	 * 
	 * @param l1
	 *            ,l2
	 */

	public ListNode addTwoNumbers_bb(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(0);
		Stack<Integer> stk1 = new Stack<>();
		Stack<Integer> stk2 = new Stack<>();
		Stack<ListNode> stk3 = new Stack<>();
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				stk1.push(l1.val);
				l1 = l1.next;
			}

			if (l2 != null) {
				stk2.push(l2.val);
				l2 = l2.next;
			}
		}
		int carry = 0;
		while (!stk1.empty() || !stk2.empty() || carry != 0) {
			int sum = carry;
			if (!stk1.empty()) {
				sum += stk1.pop();
			}

			if (!stk2.empty()) {
				sum += stk2.pop();
			}

			carry = sum / 10;
			sum = sum % 10;
			ListNode node = new ListNode(sum);
			stk3.push(node);
		}

		ListNode cur = res;
		while (!stk3.empty()) {
			cur.next = stk3.pop();
			cur = cur.next;
		}

		return res.next;
	}

	public void print(ListNode node) {
		while (node != null) {
			if (node.next != null) {
				System.out.print(node.val + "->");
				node = node.next;
			} else if (node.next == null) {
				System.out.print(node.val);
				node = node.next;
			}
		}

	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(3);

		l1.next = n2;
		n2.next = n3;

		ListNode l2 = new ListNode(5);
		ListNode n4 = new ListNode(6);
		ListNode n5 = new ListNode(4);

		l2.next = n4;
		n4.next = n5;

		AddTwoNumbers slt = new AddTwoNumbers();
		ListNode result = slt.addTwoNumbers_bb(l1, l2);
		slt.print(result);
	}

}
