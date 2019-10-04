import java.util.*;

/**
 * Created by cicean on 9/2/2016.
 *
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed  QuestionEditorial Solution  My Submissions
 Total Accepted: 3102 Total Submissions: 11166 Difficulty: Hard
 Design a data structure that supports all following operations in average O(1) time.

 Note: Duplicate elements are allowed.
 insert(val): Inserts an item val to the collection.
 remove(val): Removes an item val from the collection if present.
 getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 Example:

 // Init an empty collection.
 RandomizedCollection collection = new RandomizedCollection();

 // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 collection.insert(1);

 // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 collection.insert(1);

 // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 collection.insert(2);

 // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 collection.getRandom();

 // Removes 1 from the collection, returns true. Collection now contains [1,2].
 collection.remove(1);

 // getRandom should return 1 and 2 both equally likely.
 collection.getRandom();
 Hide Company Tags Yelp
 Hide Tags Array Hash Table Design
 Hide Similar Problems (H) Insert Delete GetRandom O(1)
 要求实现一个数据结构（数允许重复），可以支持插入，删除，随机数生成。 他们的复杂度均要求O(1)
 */
public class InsertDeleteGetRandomO1Duplicatesallowed {

    /**
     * I modified the code by replacing HashSet with LinkedHashSet because the set.iterator() might be costly when a number has too many duplicates.
     * Using LinkedHashSet can be considered as O(1) if we only get the first element to remove.
     */
    public class RandomizedCollection {

        ArrayList<Integer> result;
        HashMap<Integer, LinkedHashSet<Integer>> map;

        public RandomizedCollection() {
            result = new ArrayList<Integer>();
            map = new HashMap<Integer, LinkedHashSet<Integer>>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            // Add item to map if it doesn't already exist.
            boolean alreadyExists = map.containsKey(val);
            if(!alreadyExists) {
                map.put(val, new LinkedHashSet<Integer>());
            }
            map.get(val).add(result.size());
            result.add(val);
            return !alreadyExists;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)) {
                return false;
            }
            // Get arbitary index of the ArrayList that contains val
            LinkedHashSet<Integer> valSet = map.get(val);
            int indexToReplace = valSet.iterator().next();

            // Obtain the set of the number in the last place of the ArrayList
            int numAtLastPlace = result.get(result.size() - 1);
            LinkedHashSet<Integer> replaceWith = map.get(numAtLastPlace);

            // Replace val at arbitary index with very last number
            result.set(indexToReplace, numAtLastPlace);

            // Remove appropriate index
            valSet.remove(indexToReplace);

            // Don't change set if we were replacing the removed item with the same number
            if(indexToReplace != result.size() - 1) {
                replaceWith.remove(result.size() - 1);
                replaceWith.add(indexToReplace);
            }
            result.remove(result.size() - 1);

            // Remove map entry if set is now empty, then return
            if(valSet.isEmpty()) {
                map.remove(val);
            }
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            // Get linearly random item
            return result.get((int)(Math.random() * result.size()));
        }
    }

}
