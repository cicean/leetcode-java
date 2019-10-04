package Uber;

import datastructure.TreeNode;

/**
 * Created by cicean on 9/11/2018.
 */
public class SameTree {
    /**
     * �������� �����Ľڵ�һһ��Ӧ��ͬ ����ֻ��һ���ڵ��ǲ�ͬ�� ���������ͬ�Ľڵ�,����ʱ�临�Ӷ� --LC100������, Same Tree,
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
