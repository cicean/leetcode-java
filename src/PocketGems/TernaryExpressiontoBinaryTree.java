package PocketGems;

import datastructure.TreeNode;

/**
 * Created by cicean on 9/22/2016.
 *
 * 举个例?子，就是
 a?b?c:d:e转化成
 a
 / \
 b e
 / \
 c d
 这题的基本思路就是?用stack，遇到”?"，?入栈，遇到":"，出栈。
 我?用递归写的，time complexity O(nlogn), space O(1). 时间复杂度?高是因为要
 查找String的分割点，这个每次都要O(N)，不知道有么有什么优化？
 *
 */
public class TernaryExpressiontoBinaryTree {

    public TreeNode  constructorBinarytree(String s) {
        if (s == null || s.length() == 0) return null;
        if(s.length() == 1) return new TreeNode(s.charAt(0));
        int flag =0; int mid = 0;
        for (int i = 2; i < s.length() - 1; i++) {
            if (s.charAt(i) == '?') {
                flag++;
            } else if (s.charAt(i) == ':') {
                if (flag == 0) {
                    mid = i;
                    break;
                }
                else flag--;
            }
        }

        TreeNode head = new TreeNode(s.charAt(0));
        TreeNode temp_left = constructorBinarytree(s.substring(2,mid));
        TreeNode temp_right = constructorBinarytree(s.substring(mid,s.length()));
        head.left = temp_left;
        head.right = temp_right;

        return head;
    }

}
