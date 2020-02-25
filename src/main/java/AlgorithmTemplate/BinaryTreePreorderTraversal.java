package AlgorithmTemplate;

import datastructure.TreeNode;

import java.util.*;

public class BinaryTreePreorderTraversal {

    class Solution_stack_interative {
        public List<Integer> preorderTraversal(TreeNode root) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            List<Integer> preorder = new ArrayList<Integer>();

            if (root == null) {
                return preorder;
            }

            stack.push(root);
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                preorder.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return preorder;
        }
    }

    //Version 1: Traverse
    class Solution_dfs_recursive {
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            traverse(root, result);
            return result;
        }
        // 把root为跟的preorder加入result里面
        private void traverse(TreeNode root, ArrayList<Integer> result) {
            if (root == null) {
                return;
            }

            result.add(root.val);
            traverse(root.left, result);
            traverse(root.right, result);
        }
    }

    //Version 2: Divide & Conquer
    class Solution_recursive_divide_conquer {
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // null or leaf
            if (root == null) {
                return result;
            }

            // Divide
            ArrayList<Integer> left = preorderTraversal(root.left);
            ArrayList<Integer> right = preorderTraversal(root.right);

            // Conquer
            result.add(root.val);
            result.addAll(left);
            result.addAll(right);
            return result;
        }
    }
}
