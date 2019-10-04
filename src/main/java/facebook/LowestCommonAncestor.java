package facebook;

import java.util.*;

/**
 * Created by cicean on 9/4/2016.
 *
 * Lowest Common Ancestor
 *
 * In Graph Theory and Computer Science,
 * the Lowest Common Ancestor(LCA) of two nodes u and v in a tree or a directed acyclic graph(DAG) is the lowest node that has both u and v as descendants,where we define each node to be a descendant of itself .
 * Thus , The LCA of u and v is the ancestor of u and v such that it is located farthest from the root .Computation of LCA has many applications such as finding distance between pair of nodes in a tree ,
 * Usage with Suffix trees for string processing and Computational Biology.

 In a tree data structure where each node points to its parent, the lowest common ancestor can be easily determined by finding the first intersection of the paths from u and v to the root .
 This is the main idea behind the LCA-finding techniques that would be discussed in this article .
 The techniques discussed would be :
 Naive algorithm for finding the LCA ,
 Finding LCA using RMQ ,
 Finding LCA with the use of Sparse Table/Dynamic Programming , Finding LCA using Square Root Decomposition of Tree.
 *
 *The above tree is an example of a rooted tree with 10 vertices and rooted at node number 00. You may verify manually that LCA(4,5)=3LCA(4,5)=3 , LCA(4,6)=1LCA(4,6)=1 , LCA(4,1)=1LCA(4,1)=1 , LCA(8,9)=7LCA(8,9)=7 , LCA(4,8)=0LCA(4,8)=0 and LCA(0,0)=0LCA(0,0)=0

 Throughout this article we will assume NN to denote the total number of nodes in the tree.
 */
public class LowestCommonAncestor {


    class TreeNode{
        char val;
        List<TreeNode> child = null;
        public TreeNode(char val){
            this.val = val;
            child = new ArrayList();
        }
    }
    class ReturnType{
        TreeNode node;
        int height;
        public ReturnType(TreeNode n, int h){
            node = n;
            height = h;
        }
    }

    public TreeNode getCommonAn(TreeNode root){
        if(root == null) return null;

        ReturnType res = helper(root);
        System.out.println(res.node.val);
        return res.node;
    }
    private ReturnType helper(TreeNode root){
        if(root.child.size() == 0) return new ReturnType(root, 1);
        int max = 0;
        TreeNode res = null;
        int cnt = 0;
        for(TreeNode node : root.child){
            ReturnType tmp = helper(node);
            if(tmp.height > max){
                max = tmp.height;
                cnt = 1;
                res = tmp.node;
            }else if(tmp.height == max){
                cnt++;
            }
        }
        if(cnt > 1){
            return new ReturnType(root, max + 1);
        }else{
            return new ReturnType(res, max + 1);
        }
    }
}
