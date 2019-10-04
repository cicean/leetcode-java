import datastructure.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 23	Merge k Sorted Lists	21.1%	Hard
 Problem:    Merge k Sorted Lists
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/merge-k-sorted-lists/
 Notes:
 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 
 Solution: Find the smallest list-head first using minimum-heap(lgk).
           complexity: O(N*KlgK)
 */
/**
 * Definition for singly-linked list.
 * public class datastructure.ListNode {
 *     int val;
 *     datastructure.ListNode next;
 *     datastructure.ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class MergeKSortedLists {
	
	/**
	 * 复杂度
时间 O(NlogK) 空间 O(K)

思路
当我们归并k个列表时，最简单的方法就是，对于每次插入，我们遍历这K个列表的最前面的元素，找出K个中最小的再加入到结果中。不过如果我们用一个优先队列（堆），将这K个元素加入再找堆顶元素，每次插入只要logK的复杂度。当拿出堆顶元素后，我们再将它所在链表的下一个元素拿出来，放到堆中。这样直到所有链表都被拿完，归并也就完成了。

注意
因为堆中是链表节点，我们在初始化堆时还要新建一个Comparator的类。
	 * @param lists
	 * @return
	 */
	
	public ListNode mergeKLists(ListNode[] lists) {
        Comparator<ListNode> comp = new Comparator<ListNode>() {
            public int compare(ListNode a, ListNode b) {
                if (b.val > a.val) return -1;
                else if (b.val < a.val) return 1;
                else return 0;
            }
        };
        
        Queue<ListNode> q = new PriorityQueue<ListNode>(10, comp);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) q.add(lists[i]);
        }
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            
            while (!q.isEmpty()) {
                ListNode node = q.poll();
                cur = cur.next = node;
                
                if (node.next != null) q.add(node.next);
            }
           return dummy.next; 
        
    }
	
	public ListNode mergeKLists_1(List<ListNode> lists) {
        Comparator<ListNode> comp =  new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b) {
                if(b.val > a.val) {  
                    return -1;  
                }else if(b.val < a.val){  
                    return 1;  
                } else {  
                    return 0;  
                }  
            }  
        };

        Queue<ListNode> q =  new PriorityQueue<ListNode>(10,comp);
        for (int i = 0; i < lists.size(); ++i) {
            if (lists.get(i) != null)
                q.add(lists.get(i));
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!q.isEmpty()) {
            ListNode node = q.poll();
            cur = cur.next = node;
            if (node.next != null)
                q.add(node.next);
        }
        return dummy.next;
    }
	
	/**
	 * 思路
有前面的Merge Two Sorted Lists， 这个题就变得简单了，k个可以每次把两个合并成一个，再把第三个跟刚合并的再合并，依次类推。
时间复杂度$Tn = O(n^2)$, 空间复杂度$O(1)$

上面的分析方法有点问题，如果总是合并成一个链表，会导致此链表很长，遍历时间会加倍，没有充分利用二分的优势， 所以得两两合并效率才高。
	 * @param l1
	 * @param l2
	 * @return
	 */
    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return head.next;
    }
    
    
    
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists.size()==0) return null;
        int sz = lists.size(), end = sz - 1;
        while (end > 0) {
            int begin = 0;
            while (begin < end) {
                ListNode node = mergeTwoLists(lists.get(begin), lists.get(end));
                lists.set(begin, node);
                ++begin; --end;
            }
        }
        return lists.get(0);
    }
    
    

}
