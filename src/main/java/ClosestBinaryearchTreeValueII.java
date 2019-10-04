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
You may assume k is always valid, that is: k ≤ total nodes.
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

思路1：遍历所有节点找到和 target最接近的 k 个元素，能否有个数据结构在遍历过程中维护已遍历元素离target最近的呢？PriorityQueue具备这种能力。我们需要个最小堆，堆元素需要保存两个信息，一个是树节点元素值，一个是这个元素和target的差的绝对值。但PriorityQueue是没有“堆底”概念的，当堆的size 增长到 k 后，如何删除堆中最大元素呢？ 为实现删除最小堆中最大值的操作，可以再维护一个最大堆，两个堆同步更新，需要从堆中删除元素时一定时删除最大堆的堆顶元素。 
实现注意点： 
1）想清楚堆节点元素保存什么信息 
2）新定义的Pair类要么实现了Comparable接口，要么在创建堆时传入比较器，否则运行时抛异常（PriorityQueue在JAVA文档中是这么声明的：A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so may result in ClassCastException).）。 
3）minHeap 靠maxHeap来删除元素，因此往两个堆中添加元素时必须时同一个对象。 
思路2：参考https://leetcode.com/discuss/55240/ac-clean-java-solution-using-two-stacks。 作者非常巧妙的利用了inorder遍历的特性，inorder遍历可得到某个元素的sorted predecessors, 逆inorder则可得到sorted successors.强悍！ 
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
