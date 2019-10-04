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
 
 �����Ҫ����еĶ������������������ʵ�����������������ȡ����ֻҪ����������������Ҫ��Ϳ��ԡ�
 �Ӵ���������ĽǶ�������ѡȡһ�����Ϊ�����Ͱѽ���г�������������������Ϊ���Ŀ��ж������������������������ж����������ĳ˻���
 �����ܵ������ǽ������н��Ϊ���Ŀ��н���ۼ�������д�ɱ��ʽ���£�

��Ϥ�������������ѿ����Ѿ������ˣ������ǿ���������һ�ֶ��巽ʽ����һ�����͵Ķ�̬�滮�Ķ��巽ʽ��������ʵ�����͵���ʽ���������
����˼·Ҳ����ȷ�ˣ�ά����res[i]��ʾ����i�����Ķ����������������������������ʽ�������1��n�ĵĽ�����ɡ�
ʱ����ÿ�����i�����Ķ����������������Ҫһ��i����ѭ��������Ҫ��n�Σ�������ʱ�临�Ӷ���O(1+2+...+n)=O(n^2)��
�ռ�����Ҫһ��������ά����������Ҫǰi����������Ϣ��������O(n)
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
