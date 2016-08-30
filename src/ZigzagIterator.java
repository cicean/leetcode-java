import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
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
 * 复杂度

时间 O(N) 空间 O(1)

思路

该题实际上就是轮流取两个列表的下一个元素。我们存下两个列表的迭代器，
然后用一个递增的turns变量和取模的方法来判断该取哪一个列表的元素。
 * Q：如果输入是k个列表呢？
A：使用一个迭代器的列表来管理这些迭代器。用turns变量和取模来判断我们该取列表中的第几个迭代器。不同点在于，一个迭代器用完后，我们要将其从列表中移出，这样我们下次就不用再找这个空的迭代器了。同样，由于每用完一个迭代器后都要移出一个，
turns变量也要相应的更新为该迭代器下标的上一个下标。如果迭代器列表为空，说明没有下一个了。
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
        // 如果没有下一个则返回0
        if(!hasNext()){
            return 0;
        }
        turns++;
        // 如果是第奇数个，且第一个列表也有下一个元素时，返回第一个列表的下一个
        // 如果第二个列表已经没有，返回第一个列表的下一个
        if((turns % 2 == 1 && it1.hasNext()) || (!it2.hasNext())){
            return it1.next();
        // 如果是第偶数个，且第二个列表也有下一个元素时，返回第二个列表的下一个
        // 如果第一个列表已经没有，返回第二个列表的下一个
        } else if((turns % 2 == 0 && it2.hasNext()) || (!it1.hasNext())){
            return it2.next();
        }
        return 0;
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
    
    /**
     * Q：如果输入是k个列表呢？
	A：使用一个迭代器的列表来管理这些迭代器。用turns变量和取模来判断我们该取列表中的第几个迭代器。不同点在于，一个迭代器用完后，我们要将其从列表中移出，这样我们下次就不用再找这个空的迭代器了。同样，由于每用完一个迭代器后都要移出一个，
	turns变量也要相应的更新为该迭代器下标的上一个下标。如果迭代器列表为空，说明没有下一个了。
     * @param args
     */
    
    List<Iterator<Integer>> itlist;
   // int turns;

    public ZigzagIterator(List<Iterator<Integer>> list) {
        this.itlist = new LinkedList<Iterator<Integer>>();
        // 将非空迭代器加入列表
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
        // 算出本次使用的迭代器的下标
        int pos = turns % itlist.size();
        Iterator<Integer> curr = itlist.get(pos);
        res = curr.next();
        // 如果这个迭代器用完，就将其从列表中移出
        if(!curr.hasNext()){
            itlist.remove(turns % itlist.size());
            // turns变量更新为上一个下标
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
