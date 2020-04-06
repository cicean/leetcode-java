/**
 * 298 Binary Tree Longest Consecutive Sequence
 * Created by cicean on 8/29/2016.
 * <p>
 * Total Accepted: 12701 Total Submissions: 33313 Difficulty: Medium
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * <p>
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 * <p>
 * For example,
 * 1
 * \
 * 3
 * / \
 * 2   4
 * \
 * 5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 * 2
 * \
 * 3
 * /
 * 2
 * /
 * 1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 * Hide Company Tags Google
 * Hide Tags Tree
 * Hide Similar Problems (H) Longest Consecutive Sequence
 */

import datastructure.TreeNode;


public class BinaryTreeLongestConsecutiveSequence {

    /**
     * �ݹ鷨
     ���Ӷ�
     ʱ��O(n) �ռ�O(h)

     ˼·
     ��ΪҪ���������·���������ڱ�������ʱ����Ҫ������Ϣ��
     һ��Ŀǰ��������·���ж೤������Ŀǰ·������һ���ڵ��ֵ��
     ����ͨ���ݹ����Щ��Ϣ���룬Ȼ��ͨ������ֵ����һ�����ľ����ˡ�
     ������Ҫ������������Ȼ������Ҫ֮ǰ��Ϣ����Ŀ˼·����࣬
     ����Maximum Depth of Binary Tree��Binary Tree Maximum Path Sum��
     * @param root
     * @return
     */
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return findLongest(root, 0, root.val - 1);
    }

    private int findLongest(TreeNode root, int length, int preVal) {
        if (root == null) {
            return length;
        }
        // �жϵ�ǰ�Ƿ�����
        int currLen = preVal + 1 == root.val ? length + 1 : 1;
        // ���ص�ǰ���ȣ����������ȣ��������������нϴ���Ǹ�
        return Math.max(currLen, Math.max(findLongest(root.left, currLen, root.val), findLongest(root.right, currLen, root.val)));
    }

    /**
     * ������˼·�Ƚ�ֱ�ӣ�����ά��һ�����ֵ��DFS���������������ϸ������ֵ����������԰������ڵ��ֵ����ǰ�������ȣ�������ֱ���ֵ�����ڵ�ֵ����������ǰ�������ȱ�Ϊ1���������ӵ�ǰ�������ȡ�

     ��Ȼ�������Ҳ����Ҫ�󷵻صĲ��ǳ��ȣ��������������֡�ֻҪ��ԭ�����������������һ��tail�������ɣ�ÿ�θ���maxʱ��Ҳ����tail�����������Ը���tailֵ�������������һ�����ֵ�ֵ���������г��ȹ���������������С�

     ���Ӷ�
     time: O(n), space: O(h)
     */
    public class Solution {
        public int longestConsecutive(TreeNode root) {
            int[] max = {0};
            helper(root, Integer.MIN_VALUE, 0, max);
            return max[0];
        }

        private void helper(TreeNode node, int prev, int curr, int[] max) {
            if (node == null)
                return;

            // �븸�ڵ�ֵ��������ǰ������������������ָ���1
            if (node.val == prev + 1)
                curr++;
            else
                curr = 1;

            // �������ֵ
            max[0] = Math.max(max[0], curr);
            helper(node.left, node.val, curr, max);
            helper(node.right, node.val, curr, max);
        }
    }


}
