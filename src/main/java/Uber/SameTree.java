package Uber;

import datastructure.TreeNode;

/**
 * Created by cicean on 9/11/2018.
 */
public class SameTree {
    /**
     * 给两颗树 两树的节点一一对应相同 其中只有一个节点是不同的 返回这个不同的节点,问了时间复杂度 --LC100变形呢, Same Tree,
     */


    public TreeNode isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return null;
        if (p != null && q == null) return p;
        if (p == null && q != null) return q;
        if (p.val != q.val) return p;
        TreeNode left = isSameTree(p.left, q.left);
        TreeNode right = isSameTree(p.right, q.right);
        return left == null ? right : left;
    }

}
