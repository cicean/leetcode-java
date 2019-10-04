package Google;

import datastructure.TreeNode;

/**
 * Created by cicean on 9/24/2016.
 * Check if a binary tree is subtree of another binary tree | Set 1
 Given two binary trees, check if the first tree is subtree of the second one. A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T. The subtree corresponding to the root node is the entire tree; the subtree corresponding to any other node is called a proper subtree.

 For example, in the following case, tree S is a subtree of tree T.

 Tree 2
 10
 /    \
 4       6
 \
 30


 Tree 1
 26
 /   \
 10     3
 /    \     \
 4       6      3
 \
 30
 Solution: Traverse the tree T in preorder fashion. For every visited node in the traversal, see if the subtree rooted with this node is identical to S.

 Following is the implementation for this.
 */
public class IsSubTree {
    /* A utility function to check whether trees with roots as root1 and
       root2 are identical or not */
    boolean areIdentical(TreeNode root1, TreeNode root2)
    {

        /* base cases */
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        /* Check if the data of both roots is same and data of left and right
           subtrees are also same */
        return (root1.val == root2.val
                && areIdentical(root1.left, root2.left)
                && areIdentical(root1.right, root2.right));
    }

    /* This function returns true if S is a subtree of T, otherwise false */
    boolean isSubtree(TreeNode T, TreeNode S)
    {
        /* base cases */
        if (S == null)
            return true;

        if (T == null)
            return false;

        /* Check the tree with root as current node */
        if (areIdentical(T, S))
            return true;

        /* If the tree with root as current node doesn't match then
           try left and right subtrees one by one */
        return isSubtree(T.left, S)
                || isSubtree(T.right, S);
    }

    public boolean isSubTree(TreeNode root, TreeNode p) {
    	
    	 /* base cases */
        if (root == null && p == null)
            return true;

        if (root == null || p == null)
            return false;
        
        if (root.val == p.val
                && isSubTree(root.left, p.left)
                && isSubTree(root.right, p.right)) {
        		return true;
        }

        /* Check if the data of both roots is same and data of left and right
           subtrees are also same */
        
        return isSubTree(root.left, p) || isSubTree(root.right, p);
    }

    public static void main(String args[])
    {
        IsSubTree tree = new IsSubTree();

        // TREE 1
        /* Construct the following tree
              26
             /   \
            10     3
           /    \     \
          4      6      3
           \
            30  */

        TreeNode root1 = new TreeNode(26);
        root1.right = new TreeNode(3);
        root1.right.right = new TreeNode(3);
        root1.left = new TreeNode(10);
        root1.left.left = new TreeNode(4);
        root1.left.left.right = new TreeNode(30);
        root1.left.right = new TreeNode(6);

        // TREE 2
        /* Construct the following tree
           10
         /    \
         4      6
          \
          30  */
        TreeNode root2 = new TreeNode(10);
        root2.right = new TreeNode(6);
        root2.left = new TreeNode(4);
        root2.left.right = new TreeNode(30);

        if (tree.isSubTree(root1, root2))
            System.out.println("Tree 2 is subtree of Tree 1 ");
        else
            System.out.println("Tree 2 is not a subtree of Tree 1");
    }

}
