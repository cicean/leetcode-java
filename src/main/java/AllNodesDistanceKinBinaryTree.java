import java.util.*;

/**
 * 863. All Nodes Distance K in Binary Tree
 * Medium
 *
 * 1386
 *
 * 32
 *
 * Add to List
 *
 * Share
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 * Note:
 *
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 * Accepted
 * 51,279
 * Submissions
 * 97,632
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * tguitars
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 10
 *
 * Amazon
 * |
 * 8
 *
 * Microsoft
 * |
 * 5
 *
 * Uber
 * |
 * 5
 *
 * Oracle
 * |
 * 3
 */
public class AllNodesDistanceKinBinaryTree {

    /**
     * Approach 1: Annotate Parent
     * Intuition
     *
     * If we know the parent of every node x, we know all nodes that are distance 1 from x. We can then perform a breadth first search from the target node to find the answer.
     *
     * Algorithm
     *
     * We first do a depth first search where we annotate every node with information about it's parent.
     *
     * After, we do a breadth first search to find all nodes a distance K from the target.
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the number of nodes in the given tree.
     *
     * Space Complexity: O(N)O(N).
     */
    class Solution {
        Map<TreeNode, TreeNode> parent;
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            parent = new HashMap();
            dfs(root, null);

            Queue<TreeNode> queue = new LinkedList();
            queue.add(null);
            queue.add(target);

            Set<TreeNode> seen = new HashSet();
            seen.add(target);
            seen.add(null);

            int dist = 0;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    if (dist == K) {
                        List<Integer> ans = new ArrayList();
                        for (TreeNode n: queue)
                            ans.add(n.val);
                        return ans;
                    }
                    queue.offer(null);
                    dist++;
                } else {
                    if (!seen.contains(node.left)) {
                        seen.add(node.left);
                        queue.offer(node.left);
                    }
                    if (!seen.contains(node.right)) {
                        seen.add(node.right);
                        queue.offer(node.right);
                    }
                    TreeNode par = parent.get(node);
                    if (!seen.contains(par)) {
                        seen.add(par);
                        queue.offer(par);
                    }
                }
            }

            return new ArrayList<Integer>();
        }

        public void dfs(TreeNode node, TreeNode par) {
            if (node != null) {
                parent.put(node, par);
                dfs(node.left, node);
                dfs(node.right, node);
            }
        }
    }

    /**
     * Approach 2: Percolate Distance
     * Intuition
     *
     * From root, say the target node is at depth 3 in the left branch. It means that any nodes that are distance K - 3 in the right branch should be added to the answer.
     *
     * Algorithm
     *
     * Traverse every node with a depth first search dfs. We'll add all nodes x to the answer such that node is the node on the path from x to target that is closest to the root.
     *
     * To help us, dfs(node) will return the distance from node to the target. Then, there are 4 cases:
     *
     * If node == target, then we should add nodes that are distance K in the subtree rooted at target.
     *
     * If target is in the left branch of node, say at distance L+1, then we should look for nodes that are distance K - L - 1 in the right branch.
     *
     * If target is in the right branch of node, the algorithm proceeds similarly.
     *
     * If target isn't in either branch of node, then we stop.
     *
     * In the above algorithm, we make use of the auxillary function subtree_add(node, dist) which adds the nodes in the subtree rooted at node that are distance K - dist from the given node.
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the number of nodes in the given tree.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution_2 {
        List<Integer> ans;
        TreeNode target;
        int K;
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            ans = new LinkedList();
            this.target = target;
            this.K = K;
            dfs(root);
            return ans;
        }

        // Return vertex distance from node to target if exists, else -1
        // Vertex distance: the number of vertices on the path from node to target
        public int dfs(TreeNode node) {
            if (node == null)
                return -1;
            else if (node == target) {
                subtree_add(node, 0);
                return 1;
            } else {
                int L = dfs(node.left), R = dfs(node.right);
                if (L != -1) {
                    if (L == K) ans.add(node.val);
                    subtree_add(node.right, L + 1);
                    return L + 1;
                } else if (R != -1) {
                    if (R == K) ans.add(node.val);
                    subtree_add(node.left, R + 1);
                    return R + 1;
                } else {
                    return -1;
                }
            }
        }

        // Add all nodes 'K - dist' from the node to answer.
        public void subtree_add(TreeNode node, int dist) {
            if (node == null) return;
            if (dist == K)
                ans.add(node.val);
            else {
                subtree_add(node.left, dist + 1);
                subtree_add(node.right, dist + 1);
            }
        }
    }
}
