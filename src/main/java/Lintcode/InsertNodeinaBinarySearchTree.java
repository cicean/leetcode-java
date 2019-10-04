package Lintcode;

import datastructure.TreeNode;
import java.util.*;

/**
 * 85. Insert Node in a Binary Search Tree
 * Description
 * Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.
 *
 * You can assume there is no duplicate values in this tree + node.
 *
 * Have you met this question in a real interview?
 * Example
 * Given binary search tree as follow, after Insert node 6, the tree should be:
 *
 *   2             2
 *  / \           / \
 * 1   4   -->   1   4
 *    /             / \
 *   3             3   6
 * Challenge
 * Can you do it without recursion?
 */

public class InsertNodeinaBinarySearchTree {

    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        if (root.val > node.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }

}
