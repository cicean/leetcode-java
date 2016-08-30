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
 *         �����������ʵ��һ�����˵�����������ͨ�ĵ�������Iterator�Ļ�����������peek�Ĺ���
 *         �����Ƿ��ز鿴��һ��ֵ�Ĺ��ܣ����ǲ��ƶ�ָ�룬next
 *         ()�����Ż��ƶ�ָ�룬�����ǿ��Զ���һ������ר����������һ��ֵ������һ��bool�ͱ�������Ƿ񱣴�����һ��ֵ
 *         ���ٵ���ԭ����һЩ��Ա�������Ϳ���ʵ��������˵������ˣ��μ��������£�
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
