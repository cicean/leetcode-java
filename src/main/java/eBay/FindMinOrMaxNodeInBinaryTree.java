package eBay;

import java.util.*;

/**
 * Find maximum (or minimum) in Binary Tree
 1.9
 Given a Binary Tree, find maximum(or minimum) element in it. For example, maximum in the following Binary Tree is 11.

 tree1

 Recommended: Please solve it on “PRACTICE” first, before moving on to the solution.
 In Binary Search Tree, we can find maximum by traversing right pointers until we reach rightmost node. But in Binary Tree, we must visit every node to figure out maximum. So the idea is to traverse the given tree and for every node return maximum of 3 values.
 1) Node’s data.
 2) Maximum in node’s left subtree.
 3) Maximum in node’s right subtree.

 http://www.geeksforgeeks.org/find-the-minimum-element-in-a-binary-search-tree/
 http://www.geeksforgeeks.org/find-maximum-or-minimum-in-binary-tree/

 Below is the implementation of above approach.
 */

// A binary tree node
class Node {
  int data;
  Node left, right;

  public Node(int data)
  {
    this.data = data;
    left = right = null;
  }
}

public class FindMinOrMaxNodeInBinaryTree {

// Java code to Find maximum (or minimum) in
// Binary Tree



  class BinaryTree {
    Node root;

    // Returns the max value in a binary tree
    static int findMax(Node node)
    {
      if (node == null)
        return Integer.MIN_VALUE;

      int res = node.data;
      int lres = findMax(node.left);
      int rres = findMax(node.right);

      if (lres > res)
        res = lres;
      if (rres > res)
        res = rres;
      return res;
    }

    /* Driver program to test above functions */
    public static void main(String args[])
    {
      BinaryTree tree = new BinaryTree();
      tree.root = new Node(2);
      tree.root.left = new Node(7);
      tree.root.right = new Node(5);
      tree.root.left.right = new Node(6);
      tree.root.left.right.left = new Node(1);
      tree.root.left.right.right = new Node(11);
      tree.root.right.right = new Node(9);
      tree.root.right.right.left = new Node(4);

      System.out.println("Maximum element is " +
          tree.findMax(tree.root));
    }
  }

// This code is contributed by Kamal Rawal

  // Returns the min value in a binary tree
  static int findMin(Node node)
  {


    if (node == null)
      return Integer.MAX_VALUE;

    int res = node.data;
    int lres = findMin(node.left);
    int rres = findMin(node.right);

    if (lres < res)
      res = lres;
    if (rres < res)
      res = rres;
    return res;
  }

}

class BinaryTree {

  static Node head;

  /* Given a binary search tree and a number,
   inserts a new node with the given number in
   the correct place in the tree. Returns the new
   root pointer which the caller should then use
   (the standard trick to avoid using reference
   parameters). */
  Node insert(Node node, int data) {

        /* 1. If the tree is empty, return a new,
         single node */
    if (node == null) {
      return (new Node(data));
    } else {

      /* 2. Otherwise, recur down the tree */
      if (data <= node.data) {
        node.left = insert(node.left, data);
      } else {
        node.right = insert(node.right, data);
      }

      /* return the (unchanged) node pointer */
      return node;
    }
  }

  /* Given a non-empty binary search tree,
   return the minimum data value found in that
   tree. Note that the entire tree does not need
   to be searched. */
  int minvalue(Node node) {
    Node current = node;

    /* loop down to find the leftmost leaf */
    while (current.left != null) {
      current = current.left;
    }
    return (current.data);
  }

  // Driver program to test above functions
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();
    Node root = null;
    root = tree.insert(root, 4);
    tree.insert(root, 2);
    tree.insert(root, 1);
    tree.insert(root, 3);
    tree.insert(root, 6);
    tree.insert(root, 5);

    System.out.println("The minimum value of BST is " + tree.minvalue(root));
  }
}
