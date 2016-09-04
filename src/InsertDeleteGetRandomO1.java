import java.util.*;

/**
 * Created by cicean on 9/2/2016.
 * 380. Insert Delete GetRandom O(1)  QuestionEditorial Solution  My Submissions
 Total Accepted: 5560 Total Submissions: 15795 Difficulty: Hard
 Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 1 is the only number in the set, getRandom always return 1.
 randomSet.getRandom();
 Hide Company Tags Google Uber Twitter Amazon Yelp Pocket Gems
 Hide Tags Array Hash Table Design
 Hide Similar Problems (H) Insert Delete GetRandom O(1) - Duplicates allowed
 ��Ŀ���⣺ Ҫ��ʵ��һ�����ݽṹ������֧�ֲ��룬ɾ������������ɡ� ���ǵĸ��ӶȾ�Ҫ��O(1)
 */
public class InsertDeleteGetRandomO1 {

    /**
     * ��hash��¼�±꣬ ɾ��ֻ��Ҫ��ĩβԪ�ص�������
     * O(1)�Ĳ���ɾ�����Կ�����hash set��map��set��û��getRandom������Ȼ�뵽��hash map

     ��ΪҪgetRandom()����key�����������ģ���ɾ��ĳԪ��ʱ���ܵ���key��������
     ���Ե�ɾ��Ԫ��ʱҪ��֤key�ռ��������򵥵ģ����������Ԫ�������ɾ��Ԫ�ص�λ�ã�ʹ��key�ռ�������
     */
    //We can use two hashmaps to solve this problem.
    // One uses value as keys and the other uses index as the keys.
    public class RandomizedSet {
        ArrayList<Integer> nums;
        HashMap<Integer, Integer> locs;
        java.util.Random rand = new java.util.Random();
        /** Initialize your data structure here. */
        public RandomizedSet() {
            nums = new ArrayList<Integer>();
            locs = new HashMap<Integer, Integer>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contain = locs.containsKey(val);
            if ( contain ) return false;
            locs.put( val, nums.size());
            nums.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean contain = locs.containsKey(val);
            if ( ! contain ) return false;
            int loc = locs.get(val);
            if (loc < nums.size() - 1 ) { // not the last one than swap the last one with this val
                int lastone = nums.get(nums.size() - 1 );
                nums.set( loc , lastone );
                locs.put(lastone, loc);
            }
            locs.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return nums.get( rand.nextInt(nums.size()) );
        }
    }
}
