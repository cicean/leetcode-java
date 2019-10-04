package Airbnb;

import datastructure.TreeNode;

/**
 * Created by cicean on 10/23/2016.
 * ElementPresentinTree
 */
public class ElementPresentinTree {
    private static int isPresent(TreeNode root, int val){
/* For your reference
class Node {
		Node left, right;
		int data;
                Node(int newData) {
			left = right = null;
			data = newData;
		}
	}
*/
        if (root == null) return 0;
        if (root.val == val) return 1;
        if (root.val < val) return isPresent(root.right, val);
        if (root.val > val) return isPresent(root.left, val);

        return 0;
    }
}
