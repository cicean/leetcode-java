import java.util.Arrays;

/**
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
 * Medium
 *
 * 635
 *
 * 42
 *
 * Add to List
 *
 * Share
 * Return any binary tree that matches the given preorder and postorder traversals.
 *
 * Values in the traversals pre and post are distinct positive integers.
 *
 *
 *
 * Example 1:
 *
 * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 *
 *
 * Note:
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 * It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 * Accepted
 * 29,443
 * Submissions
 * 45,719
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 6
 *
 * Problems
 *
 * Pick One
 *
 * Prev
 * /53
 *
 * Next
 */

public class ConstructBinaryTreefromPreorderandPostorderTraversal {

    /**
     * Approach 1: Recursion
     * Intuition
     *
     * A preorder traversal is:
     *
     * (root node) (preorder of left branch) (preorder of right branch)
     * While a postorder traversal is:
     *
     * (postorder of left branch) (postorder of right branch) (root node)
     * For example, if the final binary tree is [1, 2, 3, 4, 5, 6, 7] (serialized), then the preorder traversal is [1] + [2, 4, 5] + [3, 6, 7], while the postorder traversal is [4, 5, 2] + [6, 7, 3] + [1].
     *
     * If we knew how many nodes the left branch had, we could partition these arrays as such, and use recursion to generate each branch of the tree.
     *
     * Algorithm
     *
     * Let's say the left branch has LL nodes. We know the head node of that left branch is pre[1], but it also occurs last in the postorder representation of the left branch. So pre[1] = post[L-1] (because of uniqueness of the node values.) Hence, L = post.indexOf(pre[1]) + 1.
     *
     * Now in our recursion step, the left branch is represnted by pre[1 : L+1] and post[0 : L], while the right branch is represented by pre[L+1 : N] and post[L : N-1].
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the number of nodes.
     *
     * Space Complexity: O(N^2)O(N
     * 2
     *  ).
     */

    class Solution {

        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            int N = pre.length;
            if (N == 0) return null;
            TreeNode root = new TreeNode(pre[0]);
            if (N == 1) return root;

            int L = 0;
            for (int i = 0; i < N; ++i)
                if (post[i] == pre[1])
                    L = i+1;

            root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L+1),
                    Arrays.copyOfRange(post, 0, L));
            root.right = constructFromPrePost(Arrays.copyOfRange(pre, L+1, N),
                    Arrays.copyOfRange(post, L, N-1));
            return root;
        }
    }

    /**
     * Approach 2: Recursion (Space Saving Variant)
     * Explanation
     *
     * We present a variation of Approach 1 that uses indexes to refer to the subarrays of pre and post, instead of passing copies of those subarrays. Here, (i0, i1, N) refers to pre[i0:i0+N], post[i1:i1+N].
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the number of nodes.
     *
     * Space Complexity: O(N)O(N), the space used by the answer.
     */

    class Solution_1 {
        int[] pre, post;
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            this.pre = pre;
            this.post = post;
            return make(0, 0, pre.length);
        }

        public TreeNode make(int i0, int i1, int N) {
            if (N == 0) return null;
            TreeNode root = new TreeNode(pre[i0]);
            if (N == 1) return root;

            int L = 1;
            for (; L < N; ++L)
                if (post[i1 + L-1] == pre[i0 + 1])
                    break;

            root.left = make(i0+1, i1, L);
            root.right = make(i0+L+1, i1+L, N-1-L);
            return root;
        }
    }

    /**
     * Foreword
     * I saw some solutions saying O(N) time, but actually they are not.
     * If it takes already O(N) time to find left part and right part, it could not be O(N).
     *
     *
     * Complexity:
     * Time O(N), as we iterate both pre index and post index only once.
     * Space O(height), depending on the height of constructed tree.
     *
     *
     * Recursive Solution
     * Create a node TreeNode(pre[preIndex]) as the root.
     *
     * Becasue root node will be lastly iterated in post order,
     * if root.val == post[posIndex],
     * it means we have constructed the whole tree,
     *
     * If we haven't completed constructed the whole tree,
     * So we recursively constructFromPrePost for left sub tree and right sub tree.
     *
     * And finally, we'll reach the posIndex that root.val == post[posIndex].
     * We increment posIndex and return our root node.
     */
    class Solution_2 {
        int preIdx = 0;
        int postIdx = 0;
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            TreeNode root = new TreeNode(pre[preIdx++]);
            if (root.val != post[postIdx]) {
                root.left = constructFromPrePost(pre, post);
            }
            if (root.val != post[postIdx]) {
                root.right = constructFromPrePost(pre, post);
            }
            ++postIdx;
            return root;
        }
    }

}
