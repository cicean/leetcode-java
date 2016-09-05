import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal  QuestionEditorial Solution  My Submissions
 Total Accepted: 9071 Total Submissions: 27748 Difficulty: Medium
 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples:

 Given binary tree [3,9,20,null,null,15,7],
 3
 /\
 /  \
 9  20
 /\
 /  \
 15   7
 return its vertical order traversal as:
 [
 [9],
 [3,15],
 [20],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7],
 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 return its vertical order traversal as:
 [
 [4],
 [9],
 [3,0,1],
 [8],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 /\
 /  \
 5   2
 return its vertical order traversal as:
 [
 [4],
 [9,5],
 [3,0,1],
 [8,2],
 [7]
 ]
 Hide Company Tags Google Snapchat Facebook
 Hide Tags Hash Table
 Hide Similar Problems (E) Binary Tree Level Order Traversal

 */

// desgin a new data stucture call TreeNode with the index 
// use map to store the index level and the this level all node
// use linkedlist queque to traversal tree 

public class BinaryTreeVerticalOrderTraversal {

	public int index = 0;
	public TreeMap<Integer, List<Integer>> tm;

	public class Pair {
		TreeNode node;
		int index;
		public Pair(TreeNode n, int i) {
			node = n;
			index = i;
		}
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		tm = new TreeMap<Integer, List<Integer>>();
		if (root == null) return res;

		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(root, 0));

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			if (!tm.containsKey(cur.index)) tm.put(cur.index, new ArrayList<Integer>());
			tm.get(cur.index).add(cur.node.val);

			if (cur.node.left != null) q.offer(new Pair(cur.node.left, cur.index-1));
			if (cur.node.right != null) q.offer(new Pair(cur.node.right, cur.index+1));
		}

		for (int key : tm.keySet()) res.add(tm.get(key));
		return res;
	}

	
	// no hashmap use two queque
	private int min = 0, max = 0;
	public List<List<Integer>> verticalOrder_queue(TreeNode root) {
	    List<List<Integer>> list = new ArrayList<>();
	    if(root == null)    return list;
	    computeRange(root, 0);
	    for(int i = min; i <= max; i++) list.add(new ArrayList<>());
	    Queue<TreeNode> q = new LinkedList<>();
	    Queue<Integer> idx = new LinkedList<>();
	    idx.add(-min);
	    q.add(root);
	    while(!q.isEmpty()){
	        TreeNode node = q.poll();
	        int i = idx.poll();
	        list.get(i).add(node.val);
	        if(node.left != null){
	            q.add(node.left);
	            idx.add(i - 1);
	        }
	        if(node.right != null){
	            q.add(node.right);
	            idx.add(i + 1);
	        }
	    }
	    return list;
	}
	private void computeRange(TreeNode root, int idx){
	    if(root == null)    return;
	    min = Math.min(min, idx);
	    max = Math.max(max, idx);
	    computeRange(root.left, idx - 1);
	    computeRange(root.right, idx + 1);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
