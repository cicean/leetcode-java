package Uber;

import java.util.*;

/**
 * K’th Largest Element in BST when modification to BST is not allowed
 2.5
 Given a Binary Search Tree (BST) and a positive integer k, find the k’th largest element in the Binary Search Tree.

 For example, in the following BST, if k = 3, then output should be 14, and if k = 5, then output should be 10.


 We have discussed two methods in this post. The method 1 requires O(n) time. The method 2 takes O(h) time where h is height of BST, but requires augmenting the BST (storing count of nodes in left subtree with every node).

 Can we find k’th largest element in better than O(n) time and no augmentation?

 Recommended: Please solve it on “PRACTICE” first, before moving on to the solution.

 In this post, a method is discussed that takes O(h + k) time. This method doesn’t require any change to BST.

 The idea is to do reverse inorder traversal of BST. The reverse inorder traversal traverses all nodes in decreasing order. While doing the traversal, we keep track of count of nodes visited so far. When the count becomes equal to k, we stop the traversal and print the key.
 */

public class KthLargestElementinBSTwhenmodificationtoBSTisnotallowed {

  // Java code to find k'th largest element in BST

  // A binary tree node
  class Node {

    int data;
    Node left, right;

    Node(int d)
    {
      data = d;
      left = right = null;
    }
  }

  class BinarySearchTree {

    // Root of BST
    Node root;

    // Constructor
    BinarySearchTree() {
      root = null;
    }

    // function to insert nodes
    public void insert(int data) {
      this.root = this.insertRec(this.root, data);
    }

    /* A utility function to insert a new node
    with given key in BST */
    Node insertRec(Node node, int data) {
        /* If the tree is empty, return a new node */
      if (node == null) {
        this.root = new Node(data);
        return this.root;
      }

      if (data == node.data) {
        return node;
      }

        /* Otherwise, recur down the tree */
      if (data < node.data) {
        node.left = this.insertRec(node.left, data);
      } else {
        node.right = this.insertRec(node.right, data);
      }
      return node;
    }

    // class that stores the value of count
    public class count {

      int c = 0;
    }

    // utility function to find kth largest no in
    // a given tree
    void kthLargestUtil(Node node, int k, count C) {
      // Base cases, the second condition is important to
      // avoid unnecessary recursive calls
      if (node == null || C.c >= k)
        return;

      // Follow reverse inorder traversal so that the
      // largest element is visited first
      this.kthLargestUtil(node.right, k, C);

      // Increment count of visited nodes
      C.c++;

      // If c becomes k now, then this is the k'th largest
      if (C.c == k) {
        System.out.println(k + "th largest element is " +
            node.data);
        return;
      }

      // Recur for left subtree
      this.kthLargestUtil(node.left, k, C);
    }

    // Method to find the kth largest no in given BST
    void kthLargest(int k) {
      count c = new count(); // object of class count
      this.kthLargestUtil(this.root, k, c);
    }
  }


  }
