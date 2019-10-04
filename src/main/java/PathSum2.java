import java.util.ArrayList;
import java.util.List;

/*
 Author:     King, higuige@gmail.com
 Date:       Oct 7, 2014
 Problem:    Path Sum 2
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/path-sum-ii/
 Notes:
 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 For example:
 Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
 return
 [
   [5,4,11,2],
   [5,8,4,5]
 ]
 
 Solution: DFS. 
  /**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class PathSum2 {
	 public List<List<Integer>> pathSum(TreeNode root, int sum) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        ArrayList<Integer> path = new ArrayList<Integer>();
	        pathSumRe(root, sum, res, path);
	        return res; 
	    }
	    public void pathSumRe(TreeNode root, int sum, List<List<Integer>> res, ArrayList<Integer> path)
	    {
	        if (root == null) return;
	        path.add(root.val);
	        if (root.left == null && root.right == null && root.val == sum){
	            ArrayList<Integer> tmp = new ArrayList<Integer>(path);
	            res.add(tmp);
	        }
	        pathSumRe(root.left, sum - root.val, res, path);
	        pathSumRe(root.right, sum - root.val, res, path);
	        path.remove(path.size()-1);
	    }
	    
	    public static void print(List<List<Integer>> res) {
			for (List<Integer> l : res) {
				System.out.print("[");
				for (Integer i : l) {
					System.out.print(i + ",");
				}
				System.out.println("]");
			}
		}
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(5);
		TreeNode t2 = new TreeNode(4);
		TreeNode t3 = new TreeNode(8);
		TreeNode t4 = new TreeNode(11);
		TreeNode t5 = new TreeNode(13);
		TreeNode t6 = new TreeNode(4);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(2);
		TreeNode t9 = new TreeNode(1);
		
		t1.left = t2;
		t1.right = t3;
		t2.left =  t4;
		t3.left = t5;
		t3.right = t6;
		t4.left = t7;
		t4.right = t8;
		t6.right = t9;
		
		PathSum2 slt = new PathSum2();
		int sum = 22;
		print(slt.pathSum(t1, sum));
	}

}
