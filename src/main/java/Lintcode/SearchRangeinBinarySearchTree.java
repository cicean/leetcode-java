package Lintcode;

import datastructure.TreeNode;
import java.util.*;

/**
 * 11. Search Range in Binary Search Tree
 * Description
 * Given a binary search tree and a range [k1, k2], return all elements in the given range.
 *
 * Have you met this question in a real interview?
 * Example
 * If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].
 *
 *     20
 *    /  \
 *   8   22
 *  / \
 * 4   12
 */

public class SearchRangeinBinarySearchTree {

    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        if (root.val > k1) {
            left = searchRange(root.left, k1, k2);
            res.addAll(left);
        }

        if (root.val >= k1 && root.val <= k2) {
            res.add(root.val);
        }

        if (root.val < k2) {
            right = searchRange(root.right, k1, k2);
            res.addAll(right);
        }

        return res;
    }


    private ArrayList<Integer> results;
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in increasing order.
     */
    public ArrayList<Integer> searchRange_jiuzhang(TreeNode root, int k1, int k2) {
        results = new ArrayList<Integer>();
        helper(root, k1, k2);
        return results;
    }

    private void helper(TreeNode root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.val > k1) {
            helper(root.left, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            results.add(root.val);
        }
        if (root.val < k2) {
            helper(root.right, k1, k2);
        }
    }
}
