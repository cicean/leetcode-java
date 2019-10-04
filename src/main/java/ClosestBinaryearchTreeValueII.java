import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 	272	Closest Binary Search Tree Value II 	26.3%	Hard
 * @author cicean
 *Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k �� total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.

˼·1���������нڵ��ҵ��� target��ӽ��� k ��Ԫ�أ��ܷ��и����ݽṹ�ڱ���������ά���ѱ���Ԫ����target������أ�PriorityQueue�߱�����������������Ҫ����С�ѣ���Ԫ����Ҫ����������Ϣ��һ�������ڵ�Ԫ��ֵ��һ�������Ԫ�غ�target�Ĳ�ľ���ֵ����PriorityQueue��û�С��ѵס�����ģ����ѵ�size ������ k �����ɾ���������Ԫ���أ� Ϊʵ��ɾ����С�������ֵ�Ĳ�����������ά��һ�����ѣ�������ͬ�����£���Ҫ�Ӷ���ɾ��Ԫ��ʱһ��ʱɾ�����ѵĶѶ�Ԫ�ء� 
ʵ��ע��㣺 
1��������ѽڵ�Ԫ�ر���ʲô��Ϣ 
2���¶����Pair��Ҫôʵ����Comparable�ӿڣ�Ҫô�ڴ�����ʱ����Ƚ�������������ʱ���쳣��PriorityQueue��JAVA�ĵ�������ô�����ģ�A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so may result in ClassCastException).���� 
3��minHeap ��maxHeap��ɾ��Ԫ�أ�����������������Ԫ��ʱ����ʱͬһ������ 
˼·2���ο�https://leetcode.com/discuss/55240/ac-clean-java-solution-using-two-stacks�� ���߷ǳ������������inorder���������ԣ�inorder�����ɵõ�ĳ��Ԫ�ص�sorted predecessors, ��inorder��ɵõ�sorted successors.ǿ���� 
 */
public class ClosestBinaryearchTreeValueII {

	/**
	 * method 1
	 */
	
	Comparator<Pair> ascendComparator = new Comparator<Pair>() {  
        public int compare(Pair a, Pair b) {  
            return (int)(a.diff - b.diff);  
        }  
    };  
    Comparator<Pair> descendComparator = new Comparator<Pair>() {  
        public int compare(Pair a, Pair b) {  
            return (int)(b.diff - a.diff);  
        }  
    };  
    public List<Integer> closestKValues(TreeNode root, double target, int k) {  
        List<Integer> result = new ArrayList<Integer>();  
        if (root == null) return result;  
          
        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, ascendComparator);  
        PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(k, descendComparator);  
        helper(root, target, k, minHeap, maxHeap);  
          
        Iterator<Pair> iter = minHeap.iterator();  
        while (iter.hasNext()) {  
            result.add(iter.next().value);  
        }  
        return result;  
    }  
    public void helper(TreeNode root, double target, int k, PriorityQueue<Pair> minHeap, PriorityQueue<Pair> maxHeap) {  
        if (root != null) {  
            double currDiff = Math.abs(root.val - target);  
            if (minHeap.size() < k || currDiff < maxHeap.peek().diff) {  
                if (minHeap.size() == k) {  
                    minHeap.remove(maxHeap.poll());  
                }  
                Pair pair = new Pair(currDiff, root.val);  
                minHeap.offer(pair);  
                maxHeap.offer(pair);  
            }  
            helper(root.left, target, k, minHeap, maxHeap);  
            helper(root.right, target, k, minHeap, maxHeap);  
        }  
    }  
      
    class Pair{  
        double diff;  
        int value;  
        public Pair(double diff, int value) {  
            this.diff = diff;  
            this.value = value;  
        }  
    }  
	
    /**
     * method 2
     * @param args
     */
    
    public List<Integer> closestKValues_1(TreeNode root, double target, int k) {
    	  List<Integer> res = new ArrayList<>();

    	  Stack<Integer> s1 = new Stack<>(); // predecessors
    	  Stack<Integer> s2 = new Stack<>(); // successors

    	  inorder(root, target, false, s1);
    	  inorder(root, target, true, s2);

    	  while (k-- > 0) {
    	    if (s1.isEmpty())
    	      res.add(s2.pop());
    	    else if (s2.isEmpty())
    	      res.add(s1.pop());
    	    else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
    	      res.add(s1.pop());
    	    else
    	      res.add(s2.pop());
    	  }

    	  return res;
    	}

    	// inorder traversal
    	void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
    	  if (root == null) return;

    	  inorder(reverse ? root.right : root.left, target, reverse, stack);
    	  // early terminate, no need to traverse the whole tree
    	  if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
    	  // track the value of current node
    	  stack.push(root.val);
    	  inorder(reverse ? root.left : root.right, target, reverse, stack);
    	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
