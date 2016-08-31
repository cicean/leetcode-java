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
 * ����������ǽ���ѹƽǶ������ĵ�����������Ƕ����������ݽṹ���������Nested List Weight Sum�У����ǵ������õĵݹ�ķ�������ģ�
 * ��������һ�㶼���õ����ķ�������ģ����ݹ�һ�㶼����ջ����������������ջ�ĺ���ȳ������ԣ������ڶ�����������ʱ�򣬴Ӻ���ǰ�Ѷ���ѹ��ջ�У�
 * ��ô��һ���������ѹ��ջ�ͻ��һ��ȡ�����������ǵ�hasNext()������Ҫ����ջ�������д���
 * ���ջ��Ԫ����������ֱ�ӷ���true��������ǣ���ô�Ƴ�ջ��Ԫ�أ�����ʼ�������ȡ����list�����ǴӺ���ǰѹ��ջ��ѭ��ֹͣ������ջΪ�գ�����
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
