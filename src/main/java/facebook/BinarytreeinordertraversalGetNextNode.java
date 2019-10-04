package facebook;

/**
 * Created by cicean on 9/5/2016.
 *
 */
public class BinarytreeinordertraversalGetNextNode {
    //binary tree inorder traversal

    class Node {
        Node left;
        Node right;
        Node parent;

        public Node(Node left, Node right, Node parent) {
            this.left = left;
            this.right = right;
            this.parent = parent;

        }

    }

    Node getNext (Node current) {
        if (current == null)  return null;

        if (current.right == null) {
            while (current.parent != null && current.parent.right == current) {
                current = current.parent;
            }

            if (current.parent == null)  return null;
            else return current.parent;
        } else {
            Node c = current.right;
            while (c.left != null) {
                c = c.left;
            }
            return c;
        }
    }
}
