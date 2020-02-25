package AlgorithmTemplate;

import Lintcode.ResultType;
import datastructure.TreeNode;

public class DFS {
    //Template 1: Traverse

    class Solution {
        public void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // do something with root
            traverse(root.left);
            // do something with root
            traverse(root.right);
            // do something with root
        }
    }


    //Tempate 2: Divide & Conquer

    class Solution {
        public ResultType traversal(TreeNode root) {
            // null or leaf
            if (root == null) {
                // do something and return;
            }

            // Divide
            ResultType left = traversal(root.left);
            ResultType right = traversal(root.right);

            // Conquer
            ResultType result = Merge from left and right.
            return result;
        }
    }
}
