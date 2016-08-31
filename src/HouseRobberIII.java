/**
 * Created by cicean on 8/30/2016.
 * 337. House Robber III  QuestionEditorial Solution  My Submissions
 Total Accepted: 22203 Total Submissions: 56124 Difficulty: Medium
 The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:
 3
 / \
 2   3
 \   \
 3   1
 Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 Example 2:
 3
 / \
 4   5
 / \   \
 1   3   1
 Maximum amount of money the thief can rob = 4 + 5 = 9.
 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Uber
 Hide Tags Tree Depth-first Search
 Hide Similar Problems (E) House Robber (M) House Robber II
 题意：给定一棵二叉树，求能获取的权值最大和（相邻的不能同时取）

 */
public class HouseRobberIII {

    /**
     * 题意：给定一棵二叉树，求能获取的权值最大和（相邻的不能同时取）

     思路: 树形dp

     显然有：

     rob_root = max(rob_L + rob_R , no_rob_L + no_nob_R + root.val)
     no_rob_root = rob_L + rob_R
     */

    public class Solution {
        public int rob(TreeNode root) {
            return dfs(root)[0];
        }

        private int[] dfs(TreeNode root) {
            int dp[]={0,0};
            if(root != null){
                int[] dp_L = dfs(root.left);
                int[] dp_R = dfs(root.right);
                dp[1] = dp_L[0] + dp_R[0];
                dp[0] = Math.max(dp[1] ,dp_L[1] + dp_R[1] + root.val);
            }
            return dp;
        }
    }

}
