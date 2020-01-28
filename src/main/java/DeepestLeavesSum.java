import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1302. Deepest Leaves Sum
 * Medium
 *
 * 123
 *
 * 14
 *
 * Add to List
 *
 * Share
 * Given a binary tree, return the sum of values of its deepest leaves.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 * Accepted
 * 11,345
 * Submissions
 * 13,465
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * LeetCode
 * Traverse the tree to find the max depth.
 * Traverse the tree again to compute the sum required.
 */

public class DeepestLeavesSum {

    Map<Integer, Integer> map = new HashMap<>();

    int deepestLevel = 0;

    public int deepestLeavesSum(TreeNode root) {
        helper(root, 0);
        System.out.print("max value = " + map.getOrDefault(deepestLevel, 0));
        return map.getOrDefault(deepestLevel, 0);
    }

    public void helper(TreeNode node, int level) {
        if (node == null) return;
        deepestLevel = Math.max(deepestLevel, level);
        System.out.println("level = " + level);
        System.out.print("max value = " + map.getOrDefault(deepestLevel, 0));
        map.put(level, map.getOrDefault(level, 0) + node.val);
        helper(node.left, level + 1);
        helper(node.right, level + 1);
    }
}
