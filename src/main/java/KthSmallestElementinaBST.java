import java.util.*;


/**
 * 230. Kth Smallest Element in a BST  QuestionEditorial Solution  My Submissions
 Total Accepted: 65128
 Total Submissions: 160872
 Difficulty: Medium
 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 �� k �� BST's total elements.

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 Hint:

 Try to utilize the property of a BST.
 What if you could modify the BST node's structure?
 The optimal runtime complexity is O(height of BST).
 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.

 Hide Company Tags Bloomberg Uber Google
 Hide Tags Binary Search Tree
 Hide Similar Problems (M) Binary Tree Inorder Traversal

 * 
 * 
 * ����һ�ö�����������BST������дһ������kthSmallest�ҳ����е�kС��Ԫ�ء�

ע�⣺

����Լ���k������Ч�ģ� 1 �� k �� BST��Ԫ��������

��һ��˼����

���BST���޸ģ�����/ɾ��������ʮ��Ƶ����������ҪƵ�����ҳ���kС��Ԫ�أ�Ӧ�������Ż�kthSmallest������

��ʾ��

��������BST�����ԡ�

���������޸�BST�ڵ�Ľṹʱ��Ӧ����������

����ʱ�临�Ӷ�Ӧ����O(BST�ĸ߶�)��

����˼·��
BST�����������ʣ�

������������Ԫ�ص�ֵ��С�ڸ��ڵ��ֵ

������������Ԫ�ص�ֵ�����ڸ��ڵ��ֵ

��˲�������������� -> �� -> �ң������Ե���˳�����BST�еĽڵ㣬�Ӷ��õ���kС��Ԫ�أ�ʱ�临�Ӷ�O(k)


1.�ռ任ʱ�� 
BST�������ǣ���������������У��õ��ĵ��������Կ���ʹ��һ��stack�������������ֱ���ҵ���K��Ԫ�أ� 
2. �����Ľ���� 
����ÿ���ڵ�root����������Ϊ���ڵ�����Ľڵ���������S�� 
���S==K������root->val; 
���S > K����root��������������ҵ�KСԪ�أ� 
���S

 * 
 */

public class KthSmallestElementinaBST {

	public int kthSmallest_jiuzhang_iterative(TreeNode root, int k) {
		// write your code here
		if (root == null) {
			return -1;
		}
		int index = 0;

		Stack<TreeNode> stack = new Stack<>();

		TreeNode curt = root;
		while (curt != null || !stack.isEmpty()) {
			while (curt != null) {
				stack.push(curt);
				curt = curt.left;
			}
			curt = stack.pop();
			index++;
			if (index == k)
				return curt.val;
			curt = curt.right;
		}
		//
		return root.val;

	}
	
	Queue<TreeNode> q = new LinkedList<TreeNode>();

	public int kthSmallest(TreeNode root, int k) {
	    inorder(root);
	    int result = 0;
	    TreeNode tmp = null;
	    while(k != 0){
	        tmp = q.poll();
	        k--;
	    }

	    result = tmp.val;
	    return result;
	}

	private void inorder(TreeNode root){
	    if(root != null){
	        inorder(root.left);
	        q.offer(root);
	        inorder(root.right);
	    }
	}

	
	public int kthSmallest_1(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode p=root;
        s.push(p);
        int cnt=0;
        do{
            while(p!=null){
                s.push(p);
                p=p.left;
            }
            TreeNode top=s.pop();
            cnt++;
            if(cnt==k){
                return top.val;
            }
            p=top.right;
        }while(!s.empty());
        return 0;
    }

	public class Solution {
		int ct = 0;
		public int kthSmallest(TreeNode root, int k) {
			if(root == null) return 0;
			int res = kthSmallest(root.left, k);
			if(ct == k) return res;
			else if(++ct == k) return root.val;
			return kthSmallest(root.right, k);
		}
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(3);
		TreeNode t2 = new TreeNode(9);
		TreeNode t3 = new TreeNode(20);
		TreeNode t4 = new TreeNode(15);
		TreeNode t5 = new TreeNode(7);
		t1.left = t2;
		t1.right = t3;
		t3.left = t4;
		t3.right = t5;
		
		int k = 4;
		
		KthSmallestElementinaBST slt = new KthSmallestElementinaBST();
	
		System.out.println(slt.kthSmallest(t1, k));
	
	}

}
