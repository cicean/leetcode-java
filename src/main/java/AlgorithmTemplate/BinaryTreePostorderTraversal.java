package AlgorithmTemplate;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {

    class Solution_stack_interative {
        public List<Integer> postorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode prev = null; // previously traversed node
            TreeNode curr = root;

            if (root == null) {
                return result;
            }

            stack.push(root);
            while (!stack.empty()) {
                curr = stack.peek();
                if (prev == null || prev.left == curr || prev.right == curr) { // traverse down the tree
                    if (curr.left != null) {
                        stack.push(curr.left);
                    } else if (curr.right != null) {
                        stack.push(curr.right);
                    }
                } else if (curr.left == prev) { // traverse up the tree from the left
                    if (curr.right != null) {
                        stack.push(curr.right);
                    }
                } else { // traverse up the tree from the right
                    result.add(curr.val);
                    stack.pop();
                }
                prev = curr;
            }

            return result;
        }
    }

    //Version 1: Traverse
    class Solution_dfs_recursive {
        public ArrayList<Integer> postorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            traverse(root, result);
            return result;
        }
        // 把root为跟的preorder加入result里面
        private void traverse(TreeNode root, ArrayList<Integer> result) {
            if (root == null) {
                return;
            }

            traverse(root.left, result);
            traverse(root.right, result);
            result.add(root.val);
        }
    }

    //Version 2: Divide & Conquer
    class Solution_recursive_divide_conquer {
        public ArrayList<Integer> postorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();

            if (root == null) {
                return result;
            }

            result.addAll(postorderTraversal(root.left));
            result.addAll(postorderTraversal(root.right));
            result.add(root.val);

            return result;
        }
    }
}
