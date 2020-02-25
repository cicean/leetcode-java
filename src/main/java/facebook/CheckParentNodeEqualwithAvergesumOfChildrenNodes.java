package facebook;

import datastructure.TreeNode;

public class CheckParentNodeEqualwithAvergesumOfChildrenNodes {

    public boolean checkValid(TreeNode root) {
        return helper(root)[2] == 1 ? true : false;
    }

    public double[] helper(TreeNode n) {
        if (n == null)  // base case.
            return new double[]{0, 0, -1}; // sum, count  & average of nodes

        double[] l = helper(n.left), r = helper(n.right); // recurse to children.
        int checkTure;
        if (l[2] == -1 && r[2] != -1) {
            checkTure = (int)r[2] & (n.val == r[0]/r[1] ? 1 : 0);
        }

        if (l[2] != -1 && r[2] == -1) {
            checkTure = (int)l[2] & (n.val == l[0]/l[1] ? 1 : 0);
        }

        if (l[2] == -1 && r[2] == -1) {
            return new double[]{n.val, 1, 1};
        }

        checkTure = (int)l[2] & (int)r[2] & (n.val == (l[0] + r[0])/(l[1] + r[1]) ? 1 : 0);

        double sum = n.val + l[0] + r[0], cnt = 1 + l[1] + r[1]; // sum & count of subtree rooted at n.

        return new double[]{sum, cnt, checkTure};
    }
}
