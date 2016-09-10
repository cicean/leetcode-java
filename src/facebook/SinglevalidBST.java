package facebook;

import datastructure.TreeNode;

import java.util.*;

/**
 * Created by cicean on 9/7/2016.
 *
 刚刚结束的FB 电面
 Problem 1: check if a BT is a BST
 Problem 2: Given a set of Tree Nodes, find if they form a single valid BST, if so return the root. more info on 1point3acres.com
 Example:
 Nodes : (Node1, Node 2)
 Tree structre:
 Node1
 /
 Node2

 In this case, return Node1.
 Nodes : (Node1, Node 2)
 Tree structre:
 Node1
 /
 Node3
 */
public class SinglevalidBST {

    public class Solution {
        public  TreeNode findRoot(Set<TreeNode> set, int count) {
            if(set.size() == 0) {
                return null;
            }

            // If there are only one TreeNode left, return it
            if(set.size() == 1) {
                return set.iterator().next();
            }

            // check if all TreeNodes are already visited, if so, return null
            if(count == 0) {
                return null;
            }

            // get any node that has not been visited yet
            Queue<TreeNode> queue = new ArrayDeque<>();
            for (TreeNode treeNode : set) {
                if(!treeNode.visited) {
                    queue.offer(treeNode);
                    break;
                }
            }

            // bfs traverse
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                node.visited = true;
                count--;

                if(node.left != null) {
                    if(set.contains(node.left)) {
                        set.remove(node.left);
                        if(!node.left.visited) {
                            queue.offer(node.left);
                        }
                    } else {
                        return null;
                    }
                }

                if(node.right != null && !node.right.visited) {
                    if(set.contains(node.right)) {
                        set.remove(node.right);
                        if(!node.right.visited) {
                            queue.offer(node.right);
                        }
                    } else {
                        return null;
                    }
                }
            }

            return findRoot(set, count);
        }
    }

    public TreeNode onetree(Set<TreeNode> set) {
        Set<TreeNode> candidate = new HashSet<>(set);
        for (TreeNode tn : set) {
            if (tn.left != null) {
                candidate.remove(tn.left);
            }
            if (tn.right != null) {
                candidate.remove(tn.right);
            }
        }
        for (TreeNode root : candidate) {
            Set<TreeNode> nodes = new HashSet<>(set);
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            nodes.remove(root);
            while (!queue.isEmpty()) {
                TreeNode tn = queue.poll();
                if (tn.left != null) {
                    queue.offer(tn.left);
                    nodes.remove(tn.left);
                }
                if (tn.right != null) {
                    queue.offer(tn.right);
                    nodes.remove(tn.right);
                }
            }
            if (nodes.isEmpty())
                return root;
        }
        return null;
    }
}
