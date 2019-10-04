import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 281. Zigzag Iterator
 * Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18): The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
 * @author cicean
 * 
 * ���Ӷ�

ʱ�� O(N) �ռ� O(1)

˼·

����ʵ���Ͼ�������ȡ�����б����һ��Ԫ�ء����Ǵ��������б�ĵ�������
Ȼ����һ��������turns������ȡģ�ķ������жϸ�ȡ��һ���б��Ԫ�ء�
 * Q�����������k���б��أ�
A��ʹ��һ�����������б���������Щ����������turns������ȡģ���ж����Ǹ�ȡ�б��еĵڼ�������������ͬ�����ڣ�һ�����������������Ҫ������б����Ƴ������������´ξͲ�����������յĵ������ˡ�ͬ��������ÿ����һ����������Ҫ�Ƴ�һ����
turns����ҲҪ��Ӧ�ĸ���Ϊ�õ������±����һ���±ꡣ����������б�Ϊ�գ�˵��û����һ���ˡ�
 */
public class ZigzagIterator {

	/**
	 * use two iterator
	 */
	Iterator<Integer> it1;
    Iterator<Integer> it2;
    int turns;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.it1 = v1.iterator();
        this.it2 = v2.iterator();
        turns = 0;
    }

    public int next() {
        // ���û����һ���򷵻�0
        if(!hasNext()){
            return 0;
        }
        turns++;
        // ����ǵ����������ҵ�һ���б�Ҳ����һ��Ԫ��ʱ�����ص�һ���б����һ��
        // ����ڶ����б��Ѿ�û�У����ص�һ���б����һ��
        if((turns % 2 == 1 && it1.hasNext()) || (!it2.hasNext())){
            return it1.next();
        // ����ǵ�ż�������ҵڶ����б�Ҳ����һ��Ԫ��ʱ�����صڶ����б����һ��
        // �����һ���б��Ѿ�û�У����صڶ����б����һ��
        } else if((turns % 2 == 0 && it2.hasNext()) || (!it1.hasNext())){
            return it2.next();
        }
        return 0;
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
    
    /**
     * Q�����������k���б��أ�
	A��ʹ��һ�����������б���������Щ����������turns������ȡģ���ж����Ǹ�ȡ�б��еĵڼ�������������ͬ�����ڣ�һ�����������������Ҫ������б����Ƴ������������´ξͲ�����������յĵ������ˡ�ͬ��������ÿ����һ����������Ҫ�Ƴ�һ����
	turns����ҲҪ��Ӧ�ĸ���Ϊ�õ������±����һ���±ꡣ����������б�Ϊ�գ�˵��û����һ���ˡ�
     * @param args
     */
    
    List<Iterator<Integer>> itlist;
   // int turns;

    public ZigzagIterator(List<Iterator<Integer>> list) {
        this.itlist = new LinkedList<Iterator<Integer>>();
        // ���ǿյ����������б�
        for(Iterator<Integer> it : list){
            if(it.hasNext()){
                itlist.add(it);
            }
        }
        turns = 0;
    }

    public Integer next_1() {
        if(!hasNext()){
            return 0;
        }
        Integer res = 0;
        // �������ʹ�õĵ��������±�
        int pos = turns % itlist.size();
        Iterator<Integer> curr = itlist.get(pos);
        res = curr.next();
        // ���������������꣬�ͽ�����б����Ƴ�
        if(!curr.hasNext()){
            itlist.remove(turns % itlist.size());
            // turns��������Ϊ��һ���±�
            turns = pos - 1;
        }
        turns++;
        return res;
    }

    public boolean hasNext_1() {
        return itlist.size() > 0;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

public class ZigzagIterator2 {


    int turns;
    List<Iterator<Integer>> itlist;
    // int turns;

    public ZigzagIterator2(List<Iterator<Integer>> list) {
        this.itlist = new LinkedList<Iterator<Integer>>();
        // ���ǿյ����������б�
        for(Iterator<Integer> it : list){
            if(it.hasNext()){
                itlist.add(it);
            }
        }
        turns = 0;
    }

    public Integer next() {
        if(!hasNext()){
            return 0;
        }
        Integer res = 0;
        // �������ʹ�õĵ��������±�
        int pos = turns % itlist.size();
        Iterator<Integer> curr = itlist.get(pos);
        res = curr.next();
        // ���������������꣬�ͽ�����б����Ƴ�
        if(!curr.hasNext()){
            itlist.remove(turns % itlist.size());
            // turns��������Ϊ��һ���±�
            turns = pos - 1;
        }
        turns++;
        return res;
    }

    public boolean hasNext() {
        return itlist.size() > 0;
    }


}

