import java.util.*;

/**
 * 1008. Construct Binary Search Tree from Preorder Traversal
 * Medium
 *
 * 532
 *
 * 20
 *
 * Add to List
 *
 * Share
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 *
 *
 *
 * Example 1:
 *
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 *
 *
 *
 * Note:
 *
 * 1 <= preorder.length <= 100
 * The values of preorder are distinct.
 * Accepted
 * 36,004
 * Submissions
 * 48,008
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * shwetat
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 3
 *
 * Oracle
 * |
 * 2
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    /**
     * Approach 1: Construct binary tree from preorder and inorder traversal
     * Intuition
     *
     * This approach is not the optimal one because of \mathcal{O}(N \log N)O(NlogN) time complexity, but very straightforward.
     *
     * Let's use here two facts:
     *
     * Binary tree could be constructed from preorder and inorder traversal.
     *
     * Inorder traversal of BST is an array sorted in the ascending order.
     *
     * Algorithm
     *
     * Construct inorder traversal by sorting the preorder array.
     *
     * Construct binary tree from preorder and inorder traversal: the idea is to peek the elements one by one from the preorder array and try to put them as a left or as a right child if it's possible. If it's impossible - just put null as a child and proceed further. The possibility to use an element as a child is checked by an inorder array: if it contains no elements for this subtree, then the element couldn't be used here, and one should use null as a child instead.
     *
     * Implementation
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N \log N)O(NlogN). \mathcal{O}(N \log N)O(NlogN) to sort preorder array and \mathcal{O}(N)O(N) to construct the binary tree.
     * Space complexity : \mathcal{O}(N)O(N) the inorder traversal and the tree.
     *
     */

    class Solution_preorder {
        // start from first preorder element
        int pre_idx = 0;
        int[] preorder;
        HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

        public TreeNode helper(int in_left, int in_right) {
            // if there is no elements to construct subtrees
            if (in_left == in_right)
                return null;

            // pick up pre_idx element as a root
            int root_val = preorder[pre_idx];
            TreeNode root = new TreeNode(root_val);

            // root splits inorder list
            // into left and right subtrees
            int index = idx_map.get(root_val);

            // recursion
            pre_idx++;
            // build left subtree
            root.left = helper(in_left, index);
            // build right subtree
            root.right = helper(index + 1, in_right);
            return root;
        }

        public TreeNode bstFromPreorder(int[] preorder) {
            this.preorder = preorder;
            int [] inorder = Arrays.copyOf(preorder, preorder.length);
            Arrays.sort(inorder);

            // build a hashmap value -> its index
            int idx = 0;
            for (Integer val : inorder)
                idx_map.put(val, idx++);
            return helper(0, inorder.length);
        }
    }

    /**
     * Approach 2: Recursion
     * Intuition
     *
     * It's quite obvious that the best possible time complexity for this problem is \mathcal{O}(N)O(N) and hence the approach 1 is not the best one.
     *
     * Basically, the inorder traversal above was used only to check if the element could be placed in this subtree. Since one deals with a BST here, this could be verified with the help of lower and upper limits for each element as for the validate BST problem. This way there is no need in inorder traversal and the time complexity is \mathcal{O}(N)O(N).
     *
     * Algorithm
     *
     * Initiate the lower and upper limits as negative and positive infinity because one could always place the root.
     *
     * Start from the first element in the preorder array idx = 0.
     *
     * Return helper(lower, upper):
     *
     * If the preorder array is used up idx = n then the tree is constructed, return null.
     *
     * If current value val = preorder[idx] is smaller then lower limit, or larger than upper limit, return null.
     *
     * If the current value is in the limits, place it here root = TreeNode(val) and proceed to construct recursively left and right subtrees: root.left = helper(lower, val) and root.right = helper(val, upper).
     *
     * Return root.
     *
     * Implementation
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) since we visit each node exactly once.
     * Space complexity : \mathcal{O}(N)O(N) to keep the entire tree.
     */

    class Solution_Recursion {
        int idx = 0;
        int[] preorder;
        int n;

        public TreeNode helper(int lower, int upper) {
            // if all elements from preorder are used
            // then the tree is constructed
            if (idx == n) return null;

            int val = preorder[idx];
            // if the current element
            // couldn't be placed here to meet BST requirements
            if (val < lower || val > upper) return null;

            // place the current element
            // and recursively construct subtrees
            idx++;
            TreeNode root = new TreeNode(val);
            root.left = helper(lower, val);
            root.right = helper(val, upper);
            return root;
        }

        public TreeNode bstFromPreorder(int[] preorder) {
            this.preorder = preorder;
            n = preorder.length;
            return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    /**
     * Approach 3: Iteration
     * Algorithm
     *
     * The recursion above could be converted into the iteration with the help of stack.
     *
     * Pick the first preorder element as a root root = new TreeNode(preorder[0]) and push it into stack.
     *
     * Use for loop to iterate along the elements of preorder array :
     *
     * Pick the last element of the stack as a parent node, and the the current element of preorder as a child node.
     *
     * Adjust the parent node : pop out of stack all elements with the value smaller than the child value. Change the parent node at each pop node = stack.pop().
     *
     * If node.val < child.val - put the child as a right child of the node : node.right = child.
     *
     * Else - as a left child : node.left = child.
     *
     * Push child node into the stack.
     *
     * Return root.
     *
     * Implementation
     *
     * Current
     * 1 / 12
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) since we visit each node exactly once.
     *
     * Space complexity : \mathcal{O}(N)O(N) to keep the stack and the tree.
     */

    class Solution_Iteration {
        public TreeNode bstFromPreorder(int[] preorder) {
            int n = preorder.length;
            if (n == 0) return null;

            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
            deque.push(root);

            for (int i = 1; i < n; i++) {
                // take the last element of the deque as a parent
                // and create a child from the next preorder element
                TreeNode node = deque.peek();
                TreeNode child = new TreeNode(preorder[i]);
                // adjust the parent
                while (!deque.isEmpty() && deque.peek().val < child.val)
                    node = deque.pop();

                // follow BST logic to create a parent-child link
                if (node.val < child.val) node.right = child;
                else node.left = child;
                // add the child into deque
                deque.push(child);
            }
            return root;
        }
    }
}
