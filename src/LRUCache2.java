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
 �������һ�����ݽṹ����⣬��leetcode�������ôһ��������ͦ�����һ���⣬���Ժúÿ�����

�����Ҫ�����ʵ��LRU cache�����ݽṹ��ʵ��set��get���ܡ�ѧϰ������ϵͳ�Ķ�Ӧ��֪����cache��Ϊ������԰������ٴ�ȡ���ݣ�����ȷ����������С�������Ҫ��ʵ�ֵ�cache������LRU��LRU�Ļ���˼����ǡ�����õ������ݱ����õĸ��ʱȽ����õ��Ĵ�Ķࡱ����һ�ָ��Ӹ�Ч��cache���͡�

��������ķ����ǣ�˫������+HashMap��

��Ϊ���ܹ�����ɾ�����û�з��ʵ�������Ͳ������µ���������ǽ�˫����������Cache�е���������ұ�֤����ά���������������ʵ���ɷ��ʵ�˳�� ÿ���������ѯ��ʱ���������������ƶ�������ͷ����O(1)��ʱ�临�Ӷȣ����������ڽ��й���β��Ҳ����������ʹ�ù������ݾ��������ͷ�ƶ�����û �б�ʹ�õ����ݾ�������ĺ����ƶ�������Ҫ�滻ʱ����������λ�þ���������ٱ�ʹ�õ����������ֻ��Ҫ�����µ��������������ͷ������Cache�� ʱ����̭��������λ�þ����ˡ� ��

 ��ע�� ����˫�������ʹ�ã������������ǡ�

            ������Cache�п�����п���������ģ���Load������˳���޹ء�

         ��Σ�˫��������롢ɾ���ܿ죬�������ĵ����໥��Ĵ���ʱ�临�Ӷ�ΪO(1)����

�����LRU�����ԣ����ڿ������㷨��ʱ�临�Ӷȡ�Ϊ���ܼ����������ݽṹ��ʱ�临�Ӷȣ���Ҫ���ٲ��ҵ�ʱ�临�Ӷȣ�������������HashMap����������ʱ����զ������O(1)��

 ���Զ��ڱ�����˵��

get(key): ���cache�в�����Ҫget��ֵ������-1�����cache�д���Ҫ�ҵ�ֵ��������ֵ��������ԭ������ɾ����Ȼ������Ϊͷ��㡣

set(key,value)����Ҫset��keyֵ�Ѿ����ڣ��͸�����value�� ������ԭ������ɾ����Ȼ������Ϊͷ��㣻��ҩset��keyֵ�����ڣ����½�һ��node�������ǰlen<capacity,�ͽ������hashmap�У���������Ϊͷ��㣬����len���ȣ�����ɾ���������һ��node���ٽ������hashmap����Ϊͷ��㣬��len�����¡�
*/


public class LRUCache2 {
	
		 private HashMap<Integer, DoubleLinkedListNode> map = new HashMap<Integer, DoubleLinkedListNode>();
	      private DoubleLinkedListNode head;
	      private DoubleLinkedListNode end;
	      private int capacity;
	      private int len;
	   
	      public void LRUCache (int capacity) {
	          this.capacity = capacity;
	         len = 0;
	     }
	  
	     public int get(int key) {
	         if (map.containsKey(key)) {
	             DoubleLinkedListNode latest = map.get(key);
	             removeNode(latest);
	            setHead(latest);
	            return latest.val;
	        } else {
	             return -1;
	         }
	     }
	     public void set(int key, int value) {
	         if (map.containsKey(key)) {
	             DoubleLinkedListNode oldNode = map.get(key);
	             oldNode.val = value;
	             removeNode(oldNode);
	             setHead(oldNode);
	         } else {
	             DoubleLinkedListNode newNode = 
	                 new DoubleLinkedListNode(key, value);
	             if (len < capacity) {
	                setHead(newNode);
	                 map.put(key, newNode);
	                 len++;
	             } else {
	                 map.remove(end.key);
	                 end = end.pre;
	                 if (end != null) {
	                     end.next = null;
	                 }
	 
	                setHead(newNode);
	                map.put(key, newNode);
	            }
	        }
	     }
	     public void removeNode(DoubleLinkedListNode node) {
	         DoubleLinkedListNode cur = node;
	         DoubleLinkedListNode pre = cur.pre;
	         DoubleLinkedListNode post = cur.next;
	  
	         if (pre != null) {
	             pre.next = post;
	         } else {
	             head = post;
	         }
	  
	         if (post != null) {
	             post.pre = pre;
	         } else {
	             end = pre;
	         }
	     }
	  
	     public void setHead(DoubleLinkedListNode node) {
	         node.next = head;
	         node.pre = null;
	         if (head != null) {
	             head.pre = node;
	         }
	  
	         head = node;
	         if (end == null) {
	             end = node;
	         }
	     }
	  
	     
	 
	     public static void main(String[] args) {
	 		// TODO Auto-generated method stub

	 	}
}

class DoubleLinkedListNode {
     public int val;
     public int key;
     public DoubleLinkedListNode pre;
     public DoubleLinkedListNode next;
  
     public DoubleLinkedListNode(int key, int value) {
        val = value;
        this.key = key;
    }
}
