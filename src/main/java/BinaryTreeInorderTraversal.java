import java.util.*;

/*
 94	Binary Tree Inorder Traversal	36.2%	Medium
 Problem:    Binary Tree Inorder Traversal
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/binary-tree-inorder-traversal/
 Notes:
 Given a binary tree, return the inorder traversal of its nodes' values.
 For example:
 Given binary tree {1,#,2,3},
 1
  \
   2
  /
 3
 return [1,3,2].
 Note: Recursive solution is trivial, could you do it iteratively?
 Solution: 1. Recursive solution.      Time: O(n), Space: O(n).
           2. Iterative way (stack).   Time: O(n), Space: O(n).
           3. Threaded tree (Morris).  Time: O(n), Space: O(1).
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BinaryTreeInorderTraversal {
	public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        inorder(root, res);
        return res;
    }
    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    public class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            List<Integer> left = inorderTraversal(root.left);
            List<Integer> right = inorderTraversal(root.right);
            res.addAll(left);
            res.add(root.val);
            res.addAll(right);

            return res;
        }
    }

    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode cur = root;
        while (!stk.isEmpty() || cur != null) {
            if (cur != null) {
                stk.push(cur);
                cur = cur.left;
            } else {
                cur = stk.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }


    
    //morris traversal
    // O(1) extra space
    public List<Integer> inorderTraversal_morris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur)
                    node  = node.right;
                if (node.right == null) {
                    node.right = cur;
                    cur = cur.left;
                } else {
                    res.add(cur.val);
                    node.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     * ��ݹ��ʹ��ջ�ռ������˼�벻ͬ��Morris �㷨ʹ�ö������е�Ҷ�ڵ��rightָ����������潫Ҫ���ʵĽڵ����Ϣ��
     * �����rightָ��ʹ�����֮���ٽ�����Ϊnull�������ڷ��ʹ�������Щ�ڵ��������Σ�
     * ������ݹ�Ŀռ任ʱ���˼·��ͬ��Morris����ʹ��ʱ�任�ռ��˼�롣
     * 1. �����ǰ�ڵ������Ϊ�գ��������ǰ�ڵ㲢�����Һ�����Ϊ��ǰ�ڵ㡣
     * 2. �����ǰ�ڵ�����Ӳ�Ϊ�գ��ڵ�ǰ�ڵ�����������ҵ���ǰ�ڵ�����������µ�ǰ���ڵ㡣
     * 2.1. ���ǰ���ڵ���Һ���Ϊ�գ��������Һ�������Ϊ��ǰ�ڵ㡣��ǰ�ڵ����Ϊ��ǰ�ڵ�����ӡ�
     * 2.2. ���ǰ���ڵ���Һ���Ϊ��ǰ�ڵ㣬�������Һ���������Ϊ�գ��ָ�������״���������ǰ�ڵ㡣��ǰ�ڵ����Ϊ��ǰ�ڵ���Һ��ӡ�
     * 3. �ظ�1��2����ֱ����ǰ�ڵ�Ϊ�ա�
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_jiuzhang_morris(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        TreeNode cur = null;

        while (root != null) {
            if (root.left != null) {
                cur = root.left;
                while (cur.right != null && cur.right != root) {
                    cur = cur.right;
                }

                if (cur.right == root) {
                    nums.add(root.val);
                    cur.right = null;
                    root = root.right;
                } else {
                    cur.right = root;
                    root = root.left;
                }
            } else {
                nums.add(root.val);
                root = root.right;
            }
        }

        return nums;
    }

    //if give you TreeNode father pointer please traversal in O(1) extra space
    public List<Integer> inorderTraversal_databricks(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode last = null;

        while (cur != null) {
            if (last == cur.parent) {
                last = cur;
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    res.add(cur.val);
                    if (cur.right != null) {
                        cur = cur.right;
                    } else {
                        cur = cur.parent;
                    }

                }
            } else if (last == cur.left){
                last = cur;
                res.add(cur.val);
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur = cur.parent;
                }
            } else {
                last = cur;
                cur = cur.parent;
            }
        }

        return res;
    }



    //facebook if is BST the inoder should be the increasing array;
    public int[] inorderBST(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return new int[0];
        inorder(root, res);
        int[] arr = res.stream().mapToInt(Integer::intValue).toArray();
        return arr;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		 t1.right = t2;
		 t2.left = t3;
		 
		 BinaryTreeInorderTraversal slt = new BinaryTreeInorderTraversal();
		 List<Integer> res = slt.inorderTraversal(t1);
		 System.out.print(res);
	}

}
