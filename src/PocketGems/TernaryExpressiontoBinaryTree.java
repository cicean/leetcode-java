package PocketGems;

import datastructure.TreeNode;

/**
 * Created by cicean on 9/22/2016.
 *
 * �ٸ���?�ӣ�����
 a?b?c:d:eת����
 a
 / \
 b e
 / \
 c d
 ����Ļ���˼·����?��stack��������?"��?��ջ������":"����ջ��
 ��?�õݹ�д�ģ�time complexity O(nlogn), space O(1). ʱ�临�Ӷ�?������ΪҪ
 ����String�ķָ�㣬���ÿ�ζ�ҪO(N)����֪����ô��ʲô�Ż���
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
