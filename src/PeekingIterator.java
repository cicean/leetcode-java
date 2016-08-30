import java.util.Iterator;

/**
 * 284 Peeking Iterator 31.6% Medium
 * 
 * @author cicean Given an Iterator class interface with methods: next() and
 *         hasNext(), design and implement a PeekingIterator that support the
 *         peek() operation -- it essentially peek() at the element that will be
 *         returned by the next call to next().
 * 
 *         Here is an example. Assume that the iterator is initialized to the
 *         beginning of the list: [1, 2, 3].
 * 
 *         Call next() gets you 1, the first element in the list.
 * 
 *         Now you call peek() and it returns 2, the next element. Calling
 *         next() after that still return 2.
 * 
 *         You call next() the final time and it returns 3, the last element.
 *         Calling hasNext() after that should return false.
 * 
 *         Hint:
 * 
 *         Think of "looking ahead". You want to cache the next element. Is one
 *         variable sufficient? Why or why not? Test your design with call order
 *         of peek() before next() vs next() before peek(). For a clean
 *         implementation, check out Google's guava library source code. Follow
 *         up: How would you extend your design to be generic and work with all
 *         types, not just integer?
 * 
 *         Credits: Special thanks to @porker2008 for adding this problem and
 *         creating all test cases.
 * 
 *         Hide Tags Design Hide Similar Problems (M) Binary Search Tree
 *         Iterator (M) Flatten 2D Vector (M) Zigzag Iterator
 *         这道题让我们实现一个顶端迭代器，在普通的迭代器类Iterator的基础上增加了peek的功能
 *         ，就是返回查看下一个值的功能，但是不移动指针，next
 *         ()函数才会移动指针，那我们可以定义一个变量专门来保存下一个值，再用一个bool型变量标记是否保存了下一个值
 *         ，再调用原来的一些成员函数，就可以实现这个顶端迭代器了，参见代码如下：
 */
public class PeekingIterator implements Iterator<Integer> {

	private Iterator<Integer> iter;
	private Integer v;

	public PeekingIterator(Iterator<Integer> iterator) {
		this.iter = iterator;
		v = null;

	}

	public Integer peek() {
		if (v == null) {
			v = iter.next();
		}
		return v;
	}

	@Override
	public Integer next() {
		Integer tmp = null;
		if (v == null) {
			v = iter.next();
			tmp = v;
			v = null;
			return tmp;
		}
		tmp = v;
		v = null;
		return tmp;

	}

	@Override
	public boolean hasNext() {
		if (v != null) {
			return true;
		}
		return iter.hasNext();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
