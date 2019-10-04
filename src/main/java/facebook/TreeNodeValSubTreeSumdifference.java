package facebook;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by cicean on 9/7/2016.
 * 给一个tree，返回每个点左右子树的和与自己值的差，用递归做，还问
 了不递归怎么做
 */
public class TreeNodeValSubTreeSumdifference {

    public ArrayList<Integer> treeNodeWithSubtreeSum_Re(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        ArrayList<Integer> path = new ArrayList<>();





    }

    public void subTreeNodesum(TreeNode root, ArrayList<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        subTreeNodesum(root.left, res);
        subTreeNodesum(root.right, res);
        res.remove(res.size() - 1);
    }


    //首先是用DFS
    public static void path(TreeNode root, StringBuilder sb){
        if(root==null)
            return;
        sb.append(root.val);
        if(root.left==null && root.right==null){
            System.out.println(sb.toString());
        }
        path(root.left, sb);
        path(root.right, sb);
        sb.deleteCharAt(sb.length()-1);
    }

    //写了一个把返回值存在arraylist的实现 请指正 差值 = node.val - (左子树+右子树)
    public static ArrayList<Integer> treeNodeWithSubtreeSum(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (root == null)
            return ret;
        Stack<TreeNode> s = new Stack<TreeNode>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        s.add(root);
        TreeNode pre = null;
        while (!s.isEmpty()) {
            TreeNode cur = s.peek();
            if (pre == null || pre.left == cur || pre.right == cur) {
                if (cur.left != null) {
                    s.push(cur.left);
                } else if (cur.right != null) {
                    s.push(cur.right);
                } else {
                    s.pop();
                    map.put(cur.val, 0);
                    ret.add(cur.val - map.get(cur.val));
                }
            } else if (cur.left == pre) {
                if (cur.right != null) {
                    map.put(cur.val, map.get(pre.val) + pre.val);
                    s.push(cur.right);
                } else {
                    s.pop();
                    map.put(cur.val, map.get(pre.val) + pre.val);
                    ret.add(cur.val - map.get(cur.val));
                }
            } else if (cur.right == pre) {
                s.pop();
                int tmp = 0;
                if (map.containsKey(cur.val))
                    tmp = map.get(cur.val);
                tmp += map.get(pre.val) + pre.val;
                map.put(cur.val, tmp);
                ret.add(cur.val - map.get(cur.val));
            }
            pre = cur;
        }
        return ret;
    }
}
