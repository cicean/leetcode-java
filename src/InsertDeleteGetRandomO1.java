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
 题目大意： 要求实现一个数据结构，可以支持插入，删除，随机数生成。 他们的复杂度均要求O(1)
 */
public class InsertDeleteGetRandomO1 {

    /**
     * 用hash记录下标， 删除只需要和末尾元素调换即可
     * O(1)的插入删除明显考虑用hash set或map，set存没法getRandom，则自然想到用hash map

     因为要getRandom()，即key必须是连续的，当删除某元素时可能导致key不连续，
     所以当删除元素时要保证key空间连续，简单的，我们让最后元素填补到被删除元素的位置，使得key空间连续。
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
