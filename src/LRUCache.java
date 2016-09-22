import java.util.*;

/*
 146	LRU Cache	15.0%	Hard
 Problem:    LRU Cache
 Difficulty: Hard
 Source:     http://oj.leetcode.com/problems/lru-cache/
 Notes:
 Design and implement a data structure for Least Recently Used (LRU) cache. 
 It should support the following operations: get and set. 
 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 
 Solution: Hash + list.
*/


public class LRUCache {
	
	private Map<Integer, Integer> map;
    private int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity + 1);
    }
    
    public int get(int key) {
        Integer val = map.get(key);
        if (val == null) return -1;
        map.remove(key);
        map.put(key, val);
        return val;
    }
    
    public void set(int key, int value) {
        map.remove(key);
        map.put(key, value);
        if (map.size() > capacity)
            map.remove(map.entrySet().iterator().next().getKey());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list.
	 *  One interesting property about double linked list is that the node can remove itself without other reference. 
	 * In addition, it takes constant time to add and remove nodes from the head or tail.

One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary, 
so that we don't need to check the NULL node during the update. This makes the code more concise and clean, and also it is good for the performance as well.
	 * @author cicean
	 *
	 */
	
	
	

}


