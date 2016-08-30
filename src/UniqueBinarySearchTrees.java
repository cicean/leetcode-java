import java.util.List;

/*
 96	Unique Binary Search Trees	36.0%	Medium
 Problem:    Unique Binary Search Trees
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/unique-binary-search-trees/
 Notes:
 Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 For example,
 Given n = 3, there are a total of 5 unique BST's.
 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 Solution: dp.
 
 这道题要求可行的二叉查找树的数量，其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以。
 从处理子问题的角度来看，选取一个结点为根，就把结点切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，
 所以总的数量是将以所有结点为根的可行结果累加起来。写成表达式如下：

熟悉卡特兰数的朋友可能已经发现了，这正是卡特兰数的一种定义方式，是一个典型的动态规划的定义方式（根据其实条件和递推式求解结果）。
所以思路也很明确了，维护量res[i]表示含有i个结点的二叉查找树的数量。根据上述递推式依次求出1到n的的结果即可。
时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，所以总时间复杂度是O(1+2+...+n)=O(n^2)。
空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n)
*/

public class UniqueBinarySearchTrees {
	public int numTrees_1(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j] * dp[i-j-1];
        return dp[n];
    }
    public int numTrees(int n) {
        if (n < 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2;i <= n; ++i){
            dp[i] = dp[i-1] * (4 * i - 2)/(i + 1);
        }
        return dp[n];
    }
    public static void print(TreeNode root) {
		if (null != root) {
			System.out.println(root.val + "["
					+ (null == root.left ? "null" : root.left.val) + ","
					+ (null == root.right ? "null" : root.right.val) + "]");
			print(root.left);
			print(root.right);
		}
	}
    
public static void main(String[] args) {
	// TODO Auto-generated method stub
	UniqueBinarySearchTreesII slt = new UniqueBinarySearchTreesII();
	int n = 3;
	List<TreeNode> res = slt.generateTrees(n);
	for(TreeNode root : res) {
		print(root);
		System.out.println("---------");
	}
	
			
}

}
