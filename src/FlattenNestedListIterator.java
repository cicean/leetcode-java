/**
 * Created by cicean on 5/1/2016.
 * 341. Flatten Nested List Iterator   My Submissions QuestionEditorial Solution
 Total Accepted: 3694 Total Submissions: 16007 Difficulty: Medium
 Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:
 Given the list [1,[4,[6]]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

 Subscribe to see which companies asked this question

 Hide Tags Stack Design
 Hide Similar Problems (M) Flatten 2D Vector (M) Zigzag Iterator
 Have you met this question in a real interview?
 */

import datastructure.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists. // You should
 * not implement it, or speculate about its implementation public interface
 * NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list. public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList(); }
 * 这道题让我们建立压平嵌套链表的迭代器，关于嵌套链表的数据结构最早出现在Nested List Weight Sum中，而那道题是用的递归的方法来解的，
 * 而迭代器一般都是用迭代的方法来解的，而递归一般都需用栈来辅助遍历，由于栈的后进先出的特性，我们在对向量遍历的时候，从后往前把对象压入栈中，
 * 那么第一个对象最后压入栈就会第一个取出来处理，我们的hasNext()函数需要遍历栈，并进行处理，
 * 如果栈顶元素是整数，直接返回true，如果不是，那么移除栈顶元素，并开始遍历这个取出的list，还是从后往前压入栈，循环停止条件是栈为空，返回
 */

class NestedIterator implements Iterator<Integer> {

	private Stack<NestedInteger> stack = new Stack<>();

	public NestedIterator(List<NestedInteger> nestedList) {
		for (NestedInteger nt : nestedList) {
			stack.push(nt);
		}
	}

	@Override
	public Integer next() {
		NestedInteger tInteger = stack.pop();
		return tInteger.getInteger();
	}

	@Override
	public boolean hasNext() {
		while (!stack.empty()) {
			NestedInteger tInteger = stack.peek();
			if (tInteger.getInteger())
				return true;
			stack.pop();
			for (int i = tInteger.getList().size() - 1; i >= 0; i--) {
				stack.push(tInteger.getList().get(i));
			}
		}
		return false;
	}
}

class solution{
	public class NestedIterator implements Iterator<Integer> {

		private ListIterator<NestedInteger> iter;
		private NestedIterator nestedIter;

		public NestedIterator(List<NestedInteger> nestedList) {
			iter = nestedList.listIterator();
			nestedIter = null;
		}

		@Override
		public Integer next() {

			if (nestedIter == null) {
				// No nested iterator, and still hasNext,
				// so we're guarenteed to have a next element in the list.
				NestedInteger n = iter.next();
				if (n.isInteger())
					return n.getInteger();
				else
					nestedIter = new NestedIterator(n.getList());
			}
			// If we have nested iterator, use it's next().
			if (nestedIter.hasNext())
				return nestedIter.next();
			else {
				nestedIter = null;
				return next();
			}
		}

		@Override
		public boolean hasNext() {
			// If we have nested iterator, use it's hasNext().
			if ((nestedIter != null) && nestedIter.hasNext())
				return true;
			// No nested iterator, traverse using listIterator until we have an
			// element that "hasNext",
			// then return to a previous list element for a valid next() call.
			while (iter.hasNext()) {
				NestedInteger tmp = iter.next();
				if (!tmp.isInteger()
						&& (tmp.getList().size() == 0 || !(new NestedIterator(
						tmp.getList()).hasNext())))
					continue;
				iter.previous();
				return true;
			}
			return false;
		}
	}

}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList); while (i.hasNext()) v[f()]
 * = i.next();
 */
