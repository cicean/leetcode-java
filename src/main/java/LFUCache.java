import java.util.*;

/**
 * 460. LFU Cache
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

 Follow up:
 Could you do both operations in O(1) time complexity?
 */

public class LFUCache {

  /**
   * 460. LFU Cache
   DescriptionHintsSubmissionsDiscussSolution
   Discuss Pick One
   Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

   get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
   put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

   Follow up:
   Could you do both operations in O(1) time complexity?

   Example:

   LFUCache cache = new LFUCache( 2  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
   */

  public class LFUCache1 {
    HashMap<Integer, Integer> vals;
    HashMap<Integer, Integer> counts;
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    int cap;
    int min = -1;
    public LFUCache1(int capacity) {
      cap = capacity;
      vals = new HashMap<>();
      counts = new HashMap<>();
      lists = new HashMap<>();
      lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
      if(!vals.containsKey(key))
        return -1;
      int count = counts.get(key);
      counts.put(key, count+1);
      lists.get(count).remove(key);
      if(count==min && lists.get(count).size()==0)
        min++;
      if(!lists.containsKey(count+1))
        lists.put(count+1, new LinkedHashSet<>());
      lists.get(count+1).add(key);
      return vals.get(key);
    }

    public void set(int key, int value) {
      if(cap<=0)
        return;
      if(vals.containsKey(key)) {
        vals.put(key, value);
        get(key);
        return;
      }
      if(vals.size() >= cap) {
        int evit = lists.get(min).iterator().next();
        lists.get(min).remove(evit);
        vals.remove(evit);
      }
      vals.put(key, value);
      counts.put(key, 1);
      min = 1;
      lists.get(1).add(key);
    }
  }

  /**
   * Java O(1) Accept Solution Using HashMap, DoubleLinkedList and LinkedHashSet
   Two HashMaps are used, one to store <key, value> pair, another store the <key, node>.
   I use double linked list to keep the frequent of each key. In each double linked list node, keys with the same count are saved using java built in LinkedHashSet. This can keep the order.
   Every time, one key is referenced, first find the current node corresponding to the key, If the following node exist and the frequent is larger by one, add key to the keys of the following node, else create a new node and add it following the current node.
   All operations are guaranteed to be O(1).
   */

  public class LFUCache2 {
    private Node head = null;
    private int cap = 0;
    private HashMap<Integer, Integer> valueHash = null;
    private HashMap<Integer, Node> nodeHash = null;

    public LFUCache2(int capacity) {
      this.cap = capacity;
      valueHash = new HashMap<Integer, Integer>();
      nodeHash = new HashMap<Integer, Node>();
    }

    public int get(int key) {
      if (valueHash.containsKey(key)) {
        increaseCount(key);
        return valueHash.get(key);
      }
      return -1;
    }

    public void set(int key, int value) {
      if ( cap == 0 ) return;
      if (valueHash.containsKey(key)) {
        valueHash.put(key, value);
      } else {
        if (valueHash.size() < cap) {
          valueHash.put(key, value);
        } else {
          removeOld();
          valueHash.put(key, value);
        }
        addToHead(key);
      }
      increaseCount(key);
    }

    private void addToHead(int key) {
      if (head == null) {
        head = new Node(0);
        head.keys.add(key);
      } else if (head.count > 0) {
        Node node = new Node(0);
        node.keys.add(key);
        node.next = head;
        head.prev = node;
        head = node;
      } else {
        head.keys.add(key);
      }
      nodeHash.put(key, head);
    }

    private void increaseCount(int key) {
      Node node = nodeHash.get(key);
      node.keys.remove(key);

      if (node.next == null) {
        node.next = new Node(node.count+1);
        node.next.prev = node;
        node.next.keys.add(key);
      } else if (node.next.count == node.count+1) {
        node.next.keys.add(key);
      } else {
        Node tmp = new Node(node.count+1);
        tmp.keys.add(key);
        tmp.prev = node;
        tmp.next = node.next;
        node.next.prev = tmp;
        node.next = tmp;
      }

      nodeHash.put(key, node.next);
      if (node.keys.size() == 0) remove(node);
    }

    private void removeOld() {
      if (head == null) return;
      int old = 0;
      for (int n: head.keys) {
        old = n;
        break;
      }
      head.keys.remove(old);
      if (head.keys.size() == 0) remove(head);
      nodeHash.remove(old);
      valueHash.remove(old);
    }

    private void remove(Node node) {
      if (node.prev == null) {
        head = node.next;
      } else {
        node.prev.next = node.next;
      }
      if (node.next != null) {
        node.next.prev = node.prev;
      }
    }

    class Node {
      public int count = 0;
      public LinkedHashSet<Integer> keys = null;
      public Node prev = null, next = null;

      public Node(int count) {
        this.count = count;
        keys = new LinkedHashSet<Integer>();
        prev = next = null;
      }
    }
  }

}
