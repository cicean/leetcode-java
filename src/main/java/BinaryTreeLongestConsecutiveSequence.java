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
     * 递归法
     复杂度
     时间O(n) 空间O(h)

     思路
     因为要找最长的连续路径，我们在遍历树的时候需要两个信息，
     一是目前连起来的路径有多长，二是目前路径的上一个节点的值。
     我们通过递归把这些信息代入，然后通过返回值返回一个最大的就行了。
     这种需要遍历二叉树，然后又需要之前信息的题目思路都差不多，
     比如Maximum Depth of Binary Tree和Binary Tree Maximum Path Sum。
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
        // 判断当前是否连续
        int currLen = preVal + 1 == root.val ? length + 1 : 1;
        // 返回当前长度，左子树长度，和右子树长度中较大的那个
        return Math.max(currLen, Math.max(findLongest(root.left, currLen, root.val), findLongest(root.right, currLen, root.val)));
    }

    /**
     * 这种题思路比较直接，就是维护一个最大值，DFS遍历整个树，不断更新最大值。函数里可以包含父节点的值及当前连续长度，如果发现本身值跟父节点值不连续，当前连续长度变为1，否则增加当前连续长度。

     当然，这道题也可以要求返回的不是长度，而是连续的数字。只要在原来代码基础上再增加一个tail变量即可，每次更新max时，也更新tail，这样最后可以更具tail值即连续序列最后一个数字的值及整个序列长度构造出整个连续序列。

     复杂度
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

            // 与父节点值连续，当前连续长度自增，否则恢复成1
            if (node.val == prev + 1)
                curr++;
            else
                curr = 1;

            // 更新最大值
            max[0] = Math.max(max[0], curr);
            helper(node.left, node.val, curr, max);
            helper(node.right, node.val, curr, max);
        }
    }


}
