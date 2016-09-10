package facebook;

/**
 * Created by cicean on 9/6/2016.
 *
 * Diameter of a Binary Tree
 The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two leaves in the tree. The diagram below shows two trees each with diameter nine, the leaves that form the ends of a longest path are shaded (note that there is more than one path in each tree of length nine, but no path longer than nine nodes).



 The diameter of a tree T is the largest of the following quantities:

 * the diameter of T¡¯s left subtree
 * the diameter of T¡¯s right subtree
 * the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)
 *
 */
public class DiameterofaBinaryTree {

    //Time Complexity: O(n^2)
    class solution1 {
        // Recursive optimized Java program to find the diameter of a
// Binary Tree

        /* Class containing left and right child of current
         node and key value*/
        class Node
        {
            int data;
            Node left, right;

            public Node(int item)
            {
                data = item;
                left = right = null;
            }
        }

        /* Class to print the Diameter */
        class BinaryTree
        {
            Node root;

            /* Method to calculate the diameter and return it to main */
            int diameter(Node root)
            {
        /* base case if tree is empty */
                if (root == null)
                    return 0;

        /* get the height of left and right sub trees */
                int lheight = height(root.left);
                int rheight = height(root.right);

        /* get the diameter of left and right subtrees */
                int ldiameter = diameter(root.left);
                int rdiameter = diameter(root.right);

        /* Return max of following three
          1) Diameter of left subtree
         2) Diameter of right subtree
         3) Height of left subtree + height of right subtree + 1 */
                return Math.max(lheight + rheight + 1,
                        Math.max(ldiameter, rdiameter));

            }

            /* A wrapper over diameter(Node root) */
            int diameter()
            {
                return diameter(root);
            }

            /*The function Compute the "height" of a tree. Height is the
              number f nodes along the longest path from the root node
              down to the farthest leaf node.*/
            int height(Node node)
            {
        /* base case tree is empty */
                if (node == null)
                    return 0;

        /* If tree is not empty then height = 1 + max of left
           height and right heights */
                return (1 + Math.max(height(node.left), height(node.right)));
            }


        }
    }

    /**
     * Optimized implementation: The above implementation can be optimized by calculating the height in the same recursion rather than calling a height() separately.
     * Thanks to Amar for suggesting this optimized version.
     * This optimization reduces time complexity to O(n).
     */
    class solution2 {
        // Recursive Java program to find the diameter of a
// Binary Tree

        /* Class containing left and right child of current
         node and key value*/
        class Node
        {
            int data;
            Node left, right;

            public Node(int item)
            {
                data = item;
                left = right = null;
            }
        }

        // A utility class to pass heigh object
        class Height
        {
            int h;
        }

        /* Class to print the Diameter */
        class BinaryTree {
            Node root;

            /* define height =0 globally and  call diameterOpt(root,height)
               from main */
            int diameterOpt(Node root, Height height) {
        /* lh --> Height of left subtree
           rh --> Height of right subtree */
                Height lh = new Height(), rh = new Height();

                if (root == null) {
                    height.h = 0;
                    return 0; /* diameter is also 0 */
                }

        /* ldiameter  --> diameter of left subtree
           rdiameter  --> Diameter of right subtree */
        /* Get the heights of left and right subtrees in lh and rh
         And store the returned values in ldiameter and ldiameter */
                lh.h++;
                rh.h++;
                int ldiameter = diameterOpt(root.left, lh);
                int rdiameter = diameterOpt(root.right, rh);

        /* Height of current node is max of heights of left and
         right subtrees plus 1*/
                height.h = Math.max(lh.h, rh.h) + 1;

                return Math.max(lh.h + rh.h + 1, Math.max(ldiameter, rdiameter));
            }

            /* A wrapper over diameter(Node root) */
            int diameter() {
                Height height = new Height();
                return diameterOpt(root, height);
            }

            /*The function Compute the "height" of a tree. Height is the
              number f nodes along the longest path from the root node
              down to the farthest leaf node.*/
            int height(Node node) {
        /* base case tree is empty */
                if (node == null)
                    return 0;

        /* If tree is not empty then height = 1 + max of left
           height and right heights */
                return (1 + Math.max(height(node.left), height(node.right)));
            }
        }
    }

}
