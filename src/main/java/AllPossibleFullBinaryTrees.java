/**
 * 894. All Possible Full Binary Trees
 * Medium
 *
 * 731
 *
 * 70
 *
 * Add to List
 *
 * Share
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
 *
 * Each node of each tree in the answer must have node.val = 0.
 *
 * You may return the final list of trees in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Explanation:
 *
 *
 *
 * Note:
 *
 * 1 <= N <= 20
 * Accepted
 * 31,719
 * Submissions
 * 43,063
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * drUnderwood
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 *
 * Oracle
 * |
 * 2
 */
public class AllPossibleFullBinaryTrees {
    /**
     * Approach 1: Recursion
     * Intuition and Algorithm
     *
     * Let \text{FBT}(N)FBT(N) be the list of all possible full binary trees with NN nodes.
     *
     * Every full binary tree TT with 3 or more nodes, has 2 children at its root. Each of those children left and right are themselves full binary trees.
     *
     * Thus, for N \geq 3N≥3, we can formulate the recursion: \text{FBT}(N) =FBT(N)= [All trees with left child from \text{FBT}(x)FBT(x) and right child from \text{FBT}(N-1-x)FBT(N−1−x), for all xx].
     *
     * Also, by a simple counting argument, there are no full binary trees with a positive, even number of nodes.
     *
     * Finally, we should cache previous results of the function \text{FBT}FBT so that we don't have to recalculate them in our recursion.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(2^N)O(2
     * N
     *  ). For odd NN, let N = 2k + 1N=2k+1. Then, \Big| \text{FBT}(N) \Big| = C_k
     * ∣
     * ∣
     * ∣
     * ​
     *  FBT(N)
     * ∣
     * ∣
     * ∣
     * ​
     *  =C
     * k
     * ​
     *  , the kk-th catalan number; and \sum\limits_{k < \frac{N}{2}} C_k
     * k<
     * 2
     * N
     * ​
     *
     * ∑
     * ​
     *  C
     * k
     * ​
     *   (the complexity involved in computing intermediate results required) is bounded by O(2^N)O(2
     * N
     *  ). However, the proof is beyond the scope of this article.
     *
     * Space Complexity: O(2^N)O(2
     * N
     *  ).
     */

    class Solution {
        Map<Integer, List<TreeNode>> memo = new HashMap();

        public List<TreeNode> allPossibleFBT(int N) {
            if (!memo.containsKey(N)) {
                List<TreeNode> ans = new LinkedList();
                if (N == 1) {
                    ans.add(new TreeNode(0));
                } else if (N % 2 == 1) {
                    for (int x = 0; x < N; ++x) {
                        int y = N - 1 - x;
                        for (TreeNode left: allPossibleFBT(x))
                            for (TreeNode right: allPossibleFBT(y)) {
                                TreeNode bns = new TreeNode(0);
                                bns.left = left;
                                bns.right = right;
                                ans.add(bns);
                            }
                    }
                }
                memo.put(N, ans);
            }

            return memo.get(N);
        }
    }

    class Solution {
        public List<TreeNode> allPossibleFBT(int N) {
            List<TreeNode> res = new ArrayList<>();
            if(N==1){
                res.add(new TreeNode(0));
                return res;
            }
            N=N-1;
            for(int i=1; i<N;i+=2){
                List<TreeNode> left = allPossibleFBT(i);
                List<TreeNode> right = allPossibleFBT(N-i);
                for(TreeNode nl: left){
                    for(TreeNode nr:right){
                        TreeNode cur = new TreeNode(0);
                        cur.left=nl;
                        cur.right=nr;
                        res.add(cur);
                    }
                }
            }
            return res;
        }

    }

    class Solution {
        public List<TreeNode> allPossibleFBT(int N) {
            List<TreeNode> res = new ArrayList<>();
            if (N % 2 == 0) {
                return res;
            }
            if (N == 1) {
                res.add(new TreeNode(0));
            }
            for (int i = 1; i < (N - 1) / 2 + 1; i += 2) {
                List<TreeNode> left = allPossibleFBT(i);
                List<TreeNode> right = allPossibleFBT(N - i - 1);
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode node = new TreeNode(0);
                        node.left = l;
                        node.right = r;
                        res.add(node);
                        if (i < (N - 1) / 2) {
                            TreeNode node1 = new TreeNode(0);
                            node1.left = r;
                            node1.right = l;
                            res.add(node1);
                        }
                    }
                }
            }
            return res;
        }
    }
