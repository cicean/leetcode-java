package AlgorithmTemplate;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    class Solution_stack_interative {
        public ArrayList<Integer> inorderTraversal(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            ArrayList<Integer> result = new ArrayList<>();

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            while (!stack.isEmpty()) {
                TreeNode node = stack.peek();
                result.add(node.val);

                if (node.right == null) {
                    node = stack.pop();
                    while (!stack.isEmpty() && stack.peek().right == node) {
                        node = stack.pop();
                    }
                } else {
                    node = node.right;
                    while (node != null) {
                        stack.push(node);
                        node = node.left;
                    }
                }
            }
            return result;
        }

        public ArrayList<Integer> inorderTraversal_2(TreeNode root) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            ArrayList<Integer> result = new ArrayList<Integer>();
            TreeNode curt = root;
            while (curt != null || !stack.empty()) {
                while (curt != null) {
                    stack.add(curt);
                    curt = curt.left;
                }
                curt = stack.pop();
                result.add(curt.val);
                curt = curt.right;
            }
            return result;
        }
    }

    //Version 1: Traverse
    class Solution_dfs_recursive {
        public ArrayList<Integer> inorderTraversal(TreeNode root) {
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
            result.add(root.val);
            traverse(root.right, result);
        }
    }

    //Version 2: Divide & Conquer
    class Solution_recursive_divide_conquer {
        public ArrayList<Integer> inorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // null or leaf
            if (root == null) {
                return result;
            }

            // Divide
            ArrayList<Integer> left = inorderTraversal(root.left);
            ArrayList<Integer> right = inorderTraversal(root.right);

            // Conquer
            result.addAll(left);
            result.add(root.val);
            result.addAll(right);
            return result;
        }
    }
}
