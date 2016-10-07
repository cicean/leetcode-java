/*
 129	Sum Root to Leaf Numbers	30.3%	Medium
 Problem:    Sum Root to Leaf Numbers
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/sum-root-to-leaf-numbers/
 Notes:
 Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 An example is the root-to-leaf path 1->2->3 which represents the number 123.
 Find the total sum of all root-to-leaf numbers.
 For example,
   1
  / \
 2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.
 Return the sum = 12 + 13 = 25.
 Solution: 1. Recursion (add to sum when reaching the leaf).
 */


/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class SumRoottoLeafNumbers {

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return sumNumbersRe(root, 0);
    }

    public int sumNumbersRe(TreeNode root, int last) {
        if (root == null) return 0;
        int res = last * 10 + root.val;
        if (root.left == null && root.right == null) return res;
        if (root.left == null) return sumNumbersRe(root.right, res);
        if (root.right == null) return sumNumbersRe(root.left, res);
        return sumNumbersRe(root.left, res) + sumNumbersRe(root.right, res);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.left = t2;
        t1.right = t3;

        SumRoottoLeafNumbers slt = new SumRoottoLeafNumbers();
        int res = slt.sumNumbers(t1);
        System.out.print(res);

    }

}
