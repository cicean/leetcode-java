package Google;

import datastructure.PrintList;
import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/25/2016.
 */
public class PrintBSTKthlevelalternatively {

    public List<Integer> printBSTKthlevel(TreeNode root, int k) {
    	if (k == 0 && root != null) return new ArrayList<Integer>(root.val);
    	List<Integer> res  = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        dfs(root.left, k - 1, res1);
        dfs(root.right, k - 1, res2);
        int i = 0;
        int j = res2.size() - 1;
        while (i < res1.size() || j >= 0) {
        	if (i < res1.size()) res.add(res1.get(i++));
        	if (j >= 0) res.add(res2.get(j--));	
        }
        
        return res;
    }

    private void dfs(TreeNode node, int k, List<Integer> res) {
        if (node == null) return;
        if (k  == 0) {
            res.add(node.val);
            return;
        }
        System.out.println(node.val);

        dfs(node.left, k - 1, res);
        dfs(node.right,k - 1, res);
    }

    public static void main(String[] args) {
		 TreeNode root = new TreeNode(4);
		 root.left = new TreeNode(2);
		 root.right = new TreeNode(6);
		 root.left.right = new TreeNode(3);
		 root.left.left = new TreeNode(1);
		 root.right.left = new TreeNode(5);
		 root.right.right = new TreeNode(7);

		 PrintBSTKthlevelalternatively slt = new PrintBSTKthlevelalternatively();
		 PrintList<Integer> res = new PrintList<>();
		 res.printList(slt.printBSTKthlevel(root, 2));
        
	}

}
